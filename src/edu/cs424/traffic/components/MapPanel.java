package edu.cs424.traffic.components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;
import java.util.Map.Entry;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

import com.modestmaps.InteractiveMap;
import com.modestmaps.core.Point2f;
import com.modestmaps.geo.Location;
import com.modestmaps.providers.Microsoft;

import edu.cs424.traffic.central.EnumColor;
import edu.cs424.traffic.central.Panel;
import edu.cs424.traffic.central.SettingsLoader;
import edu.cs424.traffic.map.dataset.StateLatLon;
import edu.cs424.traffic.map.utils.Point;
import edu.cs424.traffic.pubsub.PubSub.Event;
import edu.cs424.traffic.pubsub.Suscribe;
import edu.cs424.traffic.central.TouchEnabled;
import edu.cs424.traffic.components.MainPanel.MouseMovements;
import edu.cs424.traffic.gui.Button;
import static edu.cs424.data.helper.AppConstants.*;

public class MapPanel extends Panel implements TouchEnabled,Suscribe
{
	static InteractiveMap map;
	PVector mapSize;
	PVector mapOffset;

	public MapPanel(float x0, float y0, float width, float height,
			float parentX0, float parentY0) 
	{
		super(x0, y0, width, height, parentX0, parentY0);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void receiveNotification(Event eventName, Object... object) {
		// TODO Auto-generated method stub

	}

	PVector lastTouchPos;
	PVector lastTouchPos2;
	PVector initTouchPos;
	PVector initTouchPos2;

	HashMap<Integer,Point> touchList;
	int touchID1,touchID2;
	
	// buttons take x,y and width,height:
		Button out, in, aerial, hybrid, road;

		// all the buttons in one place, for looping:
		ArrayList<Button> buttons = new ArrayList<Button>();
	
	Location locationUSA = new Location(38.962f, -93.928f);

	public boolean touch(int ID,float x, float y, MouseMovements event) {

		if(this.containsPoint(x, y))
		{
			
				if(MouseMovements.MOUSEDOWN == event)
				{
					//check if xy is on the buttons
					if(in.containsPoint(x, y))
						map.zoomIn();
					
					else if(out.containsPoint(x, y))
						map.zoomOut();
					
					else if(aerial.containsPoint(x, y))
						map.setMapProvider( new Microsoft.AerialProvider() );
					
					else if(hybrid.containsPoint(x, y))
						map.setMapProvider( new Microsoft.HybridProvider() );
					
					else if(road.containsPoint(x, y))
						map.setMapProvider( new Microsoft.RoadProvider() );

					else {
						lastTouchPos.x = x;
						lastTouchPos.y = y;				
						Point p = new Point(ID,x, y);
						touchList.put(ID,p);

						if(touchList.size() == 1)
						{
							touchID1 = ID;
							initTouchPos.x = x;
							initTouchPos.y = y;
						}
						else if(touchList.size() == 2 )
						{
							touchID2 = ID;
							initTouchPos2.x = x;
							initTouchPos2.y = y;
						}
					}
				}
				else if(MouseMovements.MOUSEUP == event)
				{
					touchList.remove(ID);
				}
				else 
				{
					if(touchList.size() < 2)
					{
						map.tx += (x - lastTouchPos.x)/map.sc;
						map.ty += (y - lastTouchPos.y)/map.sc;					

					}
					else if(touchList.size() == 2)
					{
						float sc = PApplet.dist(lastTouchPos.x, lastTouchPos.y, lastTouchPos2.x, lastTouchPos2.y);
						float initPos = PApplet.dist(initTouchPos.x,initTouchPos.y,initTouchPos2.x,initTouchPos2.y);
						PVector midpoint = new PVector( (lastTouchPos.x+lastTouchPos2.x)/2, (lastTouchPos.y+lastTouchPos2.y)/2 );
						sc -= initPos;
						sc /= 5000;
						sc += 1;

						float mx = (midpoint.x - mapOffset.x) - mapSize.x/2;
						float my = (midpoint.y - mapOffset.y) - mapSize.y/2;
						map.tx -= mx/map.sc;
						map.ty -= my/map.sc;
						map.sc *= sc;
						map.tx += mx/map.sc;
						map.ty += my/map.sc;					
					}
					else if( touchList.size() >= 5 )
					{				    
						// Zoom to entire USA
						map.setCenterZoom(locationUSA, 4);
					}				 
					// Update touch IDs 1 and 2
					if( ID == touchID1 ){
						lastTouchPos.x = x;
						lastTouchPos.y = y;
					} else if( ID == touchID2 ){
						lastTouchPos2.x = x;
						lastTouchPos2.y = y;
					} 

					// Update touch list
					Point t = new Point( ID, x,y );
					touchList.put(ID,t);
				}
		}

		return false;
	}

	@Override
	public void setup() 
	{
		//initialize touch position variables
		touchList = new HashMap<Integer, Point>();
		lastTouchPos = new PVector();
		lastTouchPos2 = new PVector();
		initTouchPos = new PVector();
		initTouchPos2 = new PVector();
		
		//initialize map buttons
		in = new Button(10, 200, 20, 20, x0, y0, "+", true);
		out = new Button(10, 215, 20, 20, x0, y0, "-", true);
		road = new Button(mapControlPanelX+30, mapControlPanelHeight-30, 20, 20, x0, y0, "R", true);
		hybrid = new Button(mapControlPanelX+55, mapControlPanelHeight-30, 20, 20, x0, y0, "H", true);
		aerial = new Button(mapControlPanelX+80, mapControlPanelHeight-30, 20, 20, x0, y0, "A", true);
		
		buttons.add(in);
		buttons.add(out);
		buttons.add(road);
		buttons.add(hybrid);
		buttons.add(aerial);
		
		//initialize map
		mapSize = createPvector(mapOffsetWidth, mapOffsetHeight);
		mapOffset = createPvector(mapOffsetX, mapOffsetY);
		map = new InteractiveMap(SettingsLoader.papp, new Microsoft.RoadProvider(), 
				mapOffset.x, mapOffset.y, mapSize.x, mapSize.y);
		map.MAX_IMAGES_TO_KEEP = 80;
		map.setCenterZoom(locationUSA, 4);
		
		//create control panel
		drawMapControlPanel();
	}
	
	void drawMapControlPanel() {
		pushStyle();
		fill(EnumColor.DARK_GRAY, 95);
		noStroke();
		rect(mapControlPanelX, mapControlPanelY, mapControlPanelWidth, mapControlPanelHeight);
		popStyle();
		
		for(Button b:buttons)
			b.draw();
		
	}
	
	void drawMapButtons() {
		for(Button b:buttons)
			b.draw();
	}

	@Override
	public void draw() 
	{
		if(needRedraw)
		{
			System.out.println("MapPanel.draw()" + "map re drawn");
			background(EnumColor.SOMERANDOM);
			map.draw();
			
			//get lat-long from map-offset boundary
			PVector[] xy = getBoundary();
			HashMap<Location, Integer> states = getDataPoints(xy[0].x, xy[0].y, xy[1].x, xy[1].y);
			drawPointsForStates(states);

			drawMapControlPanel();
			needRedraw = false;
		}
	}

	//supposed to be inside db-querying class
	HashMap<Location, Integer> getDataPoints(float lat1, float lon1, float lat2, float lon2) {
		
		return new StateLatLon().getStates();
	}

	void drawPointsForStates(HashMap<Location, Integer> states) {
		int pointSize = 2;

		for(Entry<Location, Integer> entry : states.entrySet())  {
			Point2f p = map.locationPoint(entry.getKey());
			int crashcount = entry.getValue();
			pointSize = (int) (0.08 * (float)crashcount);

			pushStyle();
			strokeWeight(1.5f);
			stroke(EnumColor.DARK_GRAY);
			fill(EnumColor.LIGHT_RED, 60);
			ellipse(p.x, p.y, pointSize, pointSize);
			popStyle();
		}
	}

	PVector[] getBoundary() {
		Location centerLocation = map.getCenter();
		float leftX = (map.locationPoint(centerLocation).x - mapSize.x/2);
		float rightX = (map.locationPoint(centerLocation).x + mapSize.x/2);
		float topY = (map.locationPoint(centerLocation).y - mapSize.y/2);
		float bottomY = (map.locationPoint(centerLocation).y + mapSize.y/2);

		PVector topLeft = new PVector(leftX, topY);
		PVector bottomRight = new PVector(rightX, bottomY);

		PVector[] xy = {topLeft, bottomRight};
		return xy;
	}

	public void forceRedrawAllComponents()
	{
		for(Button b:buttons)
			b.setReDraw();

		needRedraw = true;
	}

	@Override
	public boolean touch(float x, float y, MouseMovements event) {
		System.out.println("MapPanel.touch()" + "NOT IMPLEMENTED");
		return false;
	}

	void setMapProvider(int newProviderID) {
		switch( newProviderID ) {
		case 0: map.setMapProvider( new Microsoft.RoadProvider() ); break;
		case 1: map.setMapProvider( new Microsoft.HybridProvider() ); break;
		case 2: map.setMapProvider( new Microsoft.AerialProvider() ); break;
		}
	}
}
