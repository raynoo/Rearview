package edu.cs424.traffic.map;

import java.awt.Color;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map.Entry;

import omicronAPI.OmicronAPI;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PVector;

import com.modestmaps.InteractiveMap;
import com.modestmaps.core.Point2f;
import com.modestmaps.geo.Location;
import com.modestmaps.providers.Microsoft;

import edu.cs424.traffic.central.EnumColor;
import edu.cs424.traffic.central.Panel;
import edu.cs424.traffic.central.TouchEnabled;
import edu.cs424.traffic.central.TouchListener;
import edu.cs424.traffic.components.MainPanel.MouseMovements;
import edu.cs424.traffic.map.dataset.MapLocation;
import edu.cs424.traffic.map.dataset.StateLatLon;

public class Map extends PApplet implements TouchEnabled {

	static InteractiveMap map;

	// buttons take x,y and width,height:
	ZoomButton out = new ZoomButton(5,365,14,14,false);
	ZoomButton in = new ZoomButton(5,348,14,14,true);
//	PanButton up = new PanButton(14,25,14,14,UP);
//	PanButton down = new PanButton(14,57,14,14,DOWN);
//	PanButton left = new PanButton(5,41,14,14,LEFT);
//	PanButton right = new PanButton(22,41,14,14,RIGHT);
	MapTypeButton aerial = new MapTypeButton(25, 365, 20, 14, "A");
	MapTypeButton hybrid = new MapTypeButton(47, 365, 20, 14, "H");
	MapTypeButton road = new MapTypeButton(69, 365, 20, 14, "R");

	// all the buttons in one place, for looping:
	Button[] buttons = { in, out, aerial, hybrid, road };

	PFont font;

	boolean gui = true;
	boolean onWall = false;
	double tx, ty, sc;

	OmicronAPI omicronManager;
	TouchListener touchListener;

	PVector mapSize;
	PVector mapOffset;

	// Link to this Processing applet - used for touchDown() callback example
	PApplet applet;

	Hashtable touchList;

	// Locations
	Location locationBerlin = new Location(52.5f, 13.4f);
	Location locationLondon = new Location(51.5f, 0f);
	Location locationChicago = new Location(41.9f, -87.6f);
	Location locationUSA = new Location(38.962f, -93.928f);
	
	HashMap<String, MapLocation> states = new StateLatLon().getStates();

	// Providers ( 'm' on keyboard )
	// 0 = Microsoft Road
	// 1 = Microsoft Hybrid
	// 2 = Microsoft Aerial
	int currentProvider = 0;

//	public Map(float x0, float y0, float width, float height, float parentX0,
//			float parentY0) {
//		super(x0, y0, width, height, parentX0, parentY0);
//	}

	@Override
	public void setup() {
//		omicronManager = new OmicronAPI(this);
//		omicronManager.setFullscreen(false);

		int scaleFactor = 1;
		
		mapSize = new PVector(460, 285);
		mapOffset = new PVector(30, 60);

		this.size(1360 * scaleFactor, 384 * scaleFactor, JAVA2D);
		
		String template = "http://{S}.mqcdn.com/tiles/1.0.0/osm/{Z}/{X}/{Y}.png";
		String[] subdomains = new String[] { "otile1", "otile2", "otile3", "otile4" };
		
		map = new InteractiveMap(this, new Microsoft.AerialProvider(), mapOffset.x, mapOffset.y, mapSize.x, mapSize.y);
		
		map.MAX_IMAGES_TO_KEEP = 80;
		setMapProvider(0);
		map.setCenterZoom(locationUSA, 3);
		font = createFont("Helvetica", 12);
		
		// enable the mouse wheel, for zooming
		addMouseWheelListener(new java.awt.event.MouseWheelListener() {
			public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
				mouseWheel(evt.getWheelRotation());
			}
		});
	}

	void setMapProvider(int newProviderID){
		switch( newProviderID ){
		case 0: map.setMapProvider( new Microsoft.RoadProvider() ); break;
		case 1: map.setMapProvider( new Microsoft.HybridProvider() ); break;
		case 2: map.setMapProvider( new Microsoft.AerialProvider() ); break;
		}
	}
	boolean isFirstTime = true;

	@Override
	public void draw() {
		setBackground(new Color(0));
		map.draw();
		
		if(isFirstTime)
		{
			fill(233);
			rect(10, 10, 100, 100);
			isFirstTime = false;
		}
		
		noFill();
		stroke(86,86,86);
		textFont(font, 24);
		text("Rear-View Mirror", 20, 30);

		// Draw a rectangle showing the resized and offset map
		noFill();
		stroke(86,86,86);//dark gray
		strokeWeight(2);
		rect(mapOffset.x, mapOffset.y, mapSize.x, mapSize.y);
		strokeWeight(1);

		// Do not use smooth() on the wall with P2D (JAVA2D ok)
		noSmooth();

		// draw all the buttons and check for mouse-over
		boolean hand = false;
		if (gui) {
			for (int i = 0; i < buttons.length; i++) {
				buttons[i].draw();
				hand = hand || buttons[i].mouseOver();
			}
		}
		
		

//		drawPointsForStates();
		getBoundary();
		
	}
	
	void drawUIControls() {
		
	}

	void drawPointsForStates() {
		int pointSize = (2 * map.getZoom()) + 2;

//		for(Entry<String, MapLocation> state : states.entrySet())  {
		for(MapLocation state : states.values())  {
			Location loc = new Location((float)state.getLat(), (float)state.getLon());
			Point2f p = map.locationPoint(loc);
			
			strokeWeight(1.5f);
			stroke(153,0,0);//dark red
			fill(255,153,204);//light red
			ellipse(p.x, p.y, pointSize, pointSize);
		}
		noFill();
	}

	void getBoundary() {
		Location centerLocation = map.getCenter();
		float leftX = (map.locationPoint(centerLocation).x - mapSize.x/2);
		float rightX = (map.locationPoint(centerLocation).x + mapSize.x/2);
		float topY = (map.locationPoint(centerLocation).y - mapSize.y/2);
		float bottomY = (map.locationPoint(centerLocation).y + mapSize.y/2);
		
//		Location a = map.pointLocation(leftX, topY);
//		Location b = map.pointLocation(rightX, bottomY);
		
		strokeWeight(1.5f);
		stroke(153,0,0);
//		fill(255,153,204);
		ellipse(leftX, topY, 10, 10);
		ellipse(rightX, bottomY, 10, 10);
		noFill();
	}
	
	@Override
	public void keyPressed() {
		if(key == 'c') {
			map.panAndZoomIn(locationChicago);
		}
	}

	public void keyReleased() {
		if (key == 'g' || key == 'G') {
			gui = !gui;
		}
		else if (key == 's' || key == 'S') {
			save("modest-maps-app.png");
		}
		else if (key == 'z' || key == 'Z') {
			map.sc = pow(2, map.getZoom());
		}
		else if (key == ' ') {
			map.sc = 2.0;
			map.tx = -128;
			map.ty = -128; 
		}
		else if (key == 'm') {
			currentProvider++;

			if( currentProvider > 2 )
				currentProvider = 0;

			setMapProvider( currentProvider );
		}
	}


	// see if we're over any buttons, otherwise tell the map to drag
	public void mouseDragged() {
//		boolean hand = false;
//		if (gui) {
//			for (int i = 0; i < buttons.length; i++) {
//				hand = hand || buttons[i].mouseOver();
//				if (hand) break;
//			}
//		}
//		if (!hand) {
//			//map.mouseDragged();
//		}
		
		map.mouseDragged();
	}

	// see if we're over any buttons, and respond accordingly:
	public void mouseClicked() {
		if (in.mouseOver()) {
			map.zoomIn();
		}
		else if (out.mouseOver()) {
			map.zoomOut();
		}
		else if (aerial.mouseOver()) {
			setMapProvider(2);
		}
		else if (hybrid.mouseOver()) {
			setMapProvider(1);
		}
		else if (road.mouseOver()) {
			setMapProvider(0);
		}
		
	}

	// zoom in or out:
	public void mouseWheel(int delta) {
		//zoom only the offset part of the map
		if(mouseX > mapOffset.x && mouseX < (mapOffset.x+mapSize.x)
				&& mouseY > mapOffset.y && mouseY < (mapOffset.y + mapSize.y)) {
			float sc = 1.0f;
			if (delta < 0) {
				sc = 1.2f;
			}
			else if (delta > 0) {
				sc = 1.0f/1.2f; 
			}
			float mx = (mouseX - mapOffset.x) - mapSize.x/2;
			float my = (mouseY - mapOffset.y) - mapSize.y/2;
			map.tx -= mx/map.sc;
			map.ty -= my/map.sc;
			map.sc *= sc;
			map.tx += mx/map.sc;
			map.ty += my/map.sc;
		}
	}

	@Override
	public boolean touch(float x, float y, MouseMovements event) {
		return false;
	}

	@Override
	public boolean containsPoint(float x, float y) {
		// TODO Auto-generated method stub
		return false;
	}



	// See TouchListener on how to use this function call
	// In this example TouchListener draws a solid ellipse
	// Ths functions here draws a ring around the solid ellipse

	// NOTE: Mouse pressed, dragged, and released events will also trigger these
	//	       using an ID of -1 and an xWidth and yWidth value of 10.

	// Touch position at last frame
	/*	PVector lastTouchPos = new PVector();
	PVector lastTouchPos2 = new PVector();
	int touchID1;
	int touchID2;

	PVector initTouchPos = new PVector();
	PVector initTouchPos2 = new PVector();

	void touchDown(int ID, float xPos, float yPos, float xWidth, float yWidth){
	  noFill();
	  stroke(255,0,0);
	  ellipse( xPos, yPos, xWidth * 2, yWidth * 2 );

	  // Update the last touch position
	  lastTouchPos.x = xPos;
	  lastTouchPos.y = yPos;

	  // Add a new touch ID to the list
	  Touch t = new Touch( ID, xPos, yPos, xWidth, yWidth );
	  touchList.put(ID,t);

	  if( touchList.size() == 1 ){ // If one touch record initial position (for dragging). Saving ID 1 for later
	    touchID1 = ID;
	    initTouchPos.x = xPos;
	    initTouchPos.y = yPos;
	  }
	  else if( touchList.size() == 2 ){ // If second touch record initial position (for zooming). Saving ID 2 for later
	    touchID2 = ID;
	    initTouchPos2.x = xPos;
	    initTouchPos2.y = yPos;
	  }
	}// touchDown

	void touchMove(int ID, float xPos, float yPos, float xWidth, float yWidth){
	  noFill();
	  stroke(0,255,0);
	  ellipse( xPos, yPos, xWidth * 2, yWidth * 2 );

	  if( touchList.size() < 2 ){
	    // Only one touch, drag map based on last position
	    map.tx += (xPos - lastTouchPos.x)/map.sc;
	    map.ty += (yPos - lastTouchPos.y)/map.sc;
	  } else if( touchList.size() == 2 ){
	    // Only two touch, scale map based on midpoint and distance from initial touch positions

	    float sc = dist(lastTouchPos.x, lastTouchPos.y, lastTouchPos2.x, lastTouchPos2.y);
	    float initPos = dist(initTouchPos.x, initTouchPos.y, initTouchPos2.x, initTouchPos2.y);

	    PVector midpoint = new PVector( (lastTouchPos.x+lastTouchPos2.x)/2, (lastTouchPos.y+lastTouchPos2.y)/2 );
	    sc -= initPos;
	    sc /= 5000;
	    sc += 1;
	    //println(sc);
	    float mx = (midpoint.x - mapOffset.x) - mapSize.x/2;
	    float my = (midpoint.y - mapOffset.y) - mapSize.y/2;
	    map.tx -= mx/map.sc;
	    map.ty -= my/map.sc;
	    map.sc *= sc;
	    map.tx += mx/map.sc;
	    map.ty += my/map.sc;
	  } else if( touchList.size() >= 5 ){

	    // Zoom to entire USA
	    map.setCenterZoom(locationUSA, 6);  
	  }

	  // Update touch IDs 1 and 2
	  if( ID == touchID1 ){
	    lastTouchPos.x = xPos;
	    lastTouchPos.y = yPos;
	  } else if( ID == touchID2 ){
	    lastTouchPos2.x = xPos;
	    lastTouchPos2.y = yPos;
	  } 

	  // Update touch list
	  Touch t = new Touch( ID, xPos, yPos, xWidth, yWidth );
	  touchList.put(ID,t);
	}// touchMove

	void touchUp(int ID, float xPos, float yPos, float xWidth, float yWidth){
	  noFill();
	  stroke(0,0,255);
	  ellipse( xPos, yPos, xWidth * 2, yWidth * 2 );

	  // Remove touch and ID from list
	  touchList.remove(ID);
	}// touchUp
	 */

	class Button {

		public Button() {
		}

		float x, y, w, h;

		Button(float x, float y, float w, float h) {
			this.x = x;
			this.y = y;
			this.w = w;
			this.h = h;
		} 

		boolean mouseOver() {
			return (mouseX > x && mouseX < x + w && mouseY > y && mouseY < y + h);
		}

		void draw() {
			stroke(80);
			fill(mouseOver() ? 255 : 220);
			rect(x,y,w,h); 
		}

	}

	class ZoomButton extends Button {

		boolean in = false;

		ZoomButton(float x, float y, float w, float h, boolean in) {
			super(x, y, w, h);
			this.in = in;
		}

		void draw() {
			super.draw();
			stroke(0);
			line(x+3,y+h/2,x+w-3,y+h/2);
			if (in) {
				line(x+w/2,y+3,x+w/2,y+h-3);
			}
		}

	}
	class MapTypeButton extends Button {

		String type;
		
		MapTypeButton(float x, float y, float w, float h, String type) {
			super(x, y, w, h);
			this.type = type;
		}

		void draw() {
			super.draw();
			stroke(0);
			textFont(font, 6);
			text(type, x+3, y+h/2);
//			line(x+3,y+h/2,x+w-3,y+h/2);
		}

	}
	class PanButton extends Button {

		int dir = UP;

		PanButton(float x, float y, float w, float h, int dir) {
			super(x, y, w, h);
			this.dir = dir;
		}

		void draw() {
			super.draw();
			stroke(0);
			switch(dir) {
			case UP:
				line(x+w/2,y+3,x+w/2,y+h-3);
				line(x-3+w/2,y+6,x+w/2,y+3);
				line(x+3+w/2,y+6,x+w/2,y+3);
				break;
			case DOWN:
				line(x+w/2,y+3,x+w/2,y+h-3);
				line(x-3+w/2,y+h-6,x+w/2,y+h-3);
				line(x+3+w/2,y+h-6,x+w/2,y+h-3);
				break;
			case LEFT:
				line(x+3,y+h/2,x+w-3,y+h/2);
				line(x+3,y+h/2,x+6,y-3+h/2);
				line(x+3,y+h/2,x+6,y+3+h/2);
				break;
			case RIGHT:
				line(x+3,y+h/2,x+w-3,y+h/2);
				line(x+w-3,y+h/2,x+w-6,y-3+h/2);
				line(x+w-3,y+h/2,x+w-6,y+3+h/2);
				break;
			}
		}
	}
}