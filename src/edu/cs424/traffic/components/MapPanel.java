package edu.cs424.traffic.components;

import java.util.ArrayList;
import java.util.HashMap;

import processing.core.PApplet;
import processing.core.PVector;

import com.modestmaps.InteractiveMap;
import com.modestmaps.geo.Location;
import com.modestmaps.providers.Microsoft;

import edu.cs424.data.helper.DBCommand;
import edu.cs424.traffic.central.EnumColor;
import edu.cs424.traffic.central.Panel;
import edu.cs424.traffic.central.SettingsLoader;
import edu.cs424.traffic.map.dataset.CrashInfo;
import edu.cs424.traffic.map.dataset.DataPoint;
import edu.cs424.traffic.map.dataset.Grid;
import edu.cs424.traffic.map.dataset.Marker;
import edu.cs424.traffic.map.utils.Point;
import edu.cs424.traffic.pubsub.PubSub.Event;
import edu.cs424.traffic.pubsub.Suscribe;
import edu.cs424.traffic.sqliteconn.ConnSqlite;
import edu.cs424.traffic.central.TouchEnabled;
import edu.cs424.traffic.components.MainPanel.MouseMovements;
import edu.cs424.traffic.gui.Button;
import static edu.cs424.data.helper.AppConstants.*;

public class MapPanel extends Panel implements TouchEnabled, Suscribe {

	//for map
	public static InteractiveMap map;
	PVector mapSize;
	PVector mapOffset;
	static Location locationUSA = new Location(38.962f, -93.928f);
	int initialZoom = 6, stopClusterAtZoom = 9, stateLevelZoom = 5,
			noMoreZoomIn = 15, noMoreZoomOut = 5;//these are optimal for wall
	
	
	//for touch
	PVector lastTouchPos;
	PVector lastTouchPos2;
	PVector initTouchPos;
	PVector initTouchPos2;

	HashMap<Integer,Point> touchList;
	int touchID1, touchID2;

	
	//for cluster and grids
	int gridSize = 4;
	Grid grid;
	public static boolean clusterGridMode = true, clusterGlyphMode = false;
	
	ArrayList<Marker> markersForGraph1 = new ArrayList<Marker>();
	ArrayList<Marker> markersForGraph2 = new ArrayList<Marker>();
	Marker selectedMarker;
	
	boolean isGraph1 = false, isGraph2 = false;
	HashMap<String, ArrayList<DataPoint>> dataForGraph1, dataForGraph2;
	ArrayList<DataPoint> pointsForGraph1 = new ArrayList<DataPoint>();
	ArrayList<DataPoint> pointsForGraph2 = new ArrayList<DataPoint>();
	
	//take highest and lowest from 2 graphs
	int lowestCrashCount, highestCrashCount;

	//for buttons
	Button out, in, up, down, left, right, aerial, hybrid, road;
	Button clusterWithGrid, clusterWithGlyph, graph1Button, graph2Button;
	ArrayList<Button> buttons = new ArrayList<Button>();

	boolean firstIter = true;

	
	public MapPanel(float x0, float y0, float width, float height,
			float parentX0, float parentY0) {
		super(x0, y0, width, height, parentX0, parentY0);
	}

	public boolean touch(int ID, float x, float y, MouseMovements event) {
		if(this.containsPoint(x, y))
		{
			if(MouseMovements.MOUSEDOWN == event)
			{
				//if xy is on the buttons
				if(isOnButton(x, y))
					return false;
				
				//if xy is on the circles
				if(isOnMarker(x, y, event))
					return false;

				//else xy is on the active part of map (offset area)
				else if (x > s(mapOffsetX) && x < s(mapOffsetX+mapOffsetWidth) 
						&& y > s(mapOffsetY) && y < s(mapOffsetY+mapOffsetHeight)) {

					//clear the selected cluster/datapoint
					selectedMarker = null;

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

					return false;
				}
			}
			else if(MouseMovements.MOUSEUP == event)
			{
				//if xy is on the active part of map (offset area)
				if (x > s(mapOffsetX) && x < s(mapOffsetX+mapOffsetWidth) 
						&& y > s(mapOffsetY) && y < s(mapOffsetY+mapOffsetHeight)) {

					if(isOnMarker(x, y, event))
						return false;

					updateVisibleCoordinates();
					touchList.remove(ID);
					return false;
				}
			}
			else if(MouseMovements.MOUSEMOVE == event)
			{
				if(x > s(mapOffsetX) && x < s(mapOffsetX+mapOffsetWidth) 
						&& y > s(mapOffsetY) && y < s(mapOffsetY+mapOffsetHeight)) {
					
					if(isOnMarker(x, y, event))
						return false;
					
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
						fullZoomOut();
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
					Point t = new Point(ID, x, y);
					touchList.put(ID, t);
				}
			}
		}
		return false;
	}
	
	private void updateVisibleCoordinates() {
		//update visible lat longs for the graph
		Location[] loc = getBoundaryLatLong();
		DBCommand.getInstance().updateVisibleCoordinate(loc[0], loc[1]);
	}

	boolean isOnButton(float x, float y) {
		if(in.containsPoint(x, y) && map.getZoom() < noMoreZoomIn) {
			map.zoomIn();
			updateVisibleCoordinates();
			needRedraw = true;
			return true;
		}
		else if(out.containsPoint(x, y) && map.getZoom() > noMoreZoomOut) {
			map.zoomOut();
			updateVisibleCoordinates();
			needRedraw = true;
			return true;
		}
		else if(up.containsPoint(x, y)) {
			map.panUp();
			updateVisibleCoordinates();
			needRedraw = true;
			return true;
		}
		else if(down.containsPoint(x, y)) {
			map.panDown();
			updateVisibleCoordinates();needRedraw = true;
			return true;
		}
		else if(left.containsPoint(x, y)) {
			map.panLeft();
			updateVisibleCoordinates();
			needRedraw = true;
			return true;
		}
		else if(right.containsPoint(x, y)) {
			map.panRight();
			updateVisibleCoordinates();
			needRedraw = true;
			return true;
		}
		
		
		else if(aerial.containsPoint(x, y)) {
			map.setMapProvider( new Microsoft.AerialProvider() );
			return true;
		}
		else if(hybrid.containsPoint(x, y)) {
			map.setMapProvider( new Microsoft.HybridProvider() );
			return true;
		}
		else if(road.containsPoint(x, y)) {
			map.setMapProvider( new Microsoft.RoadProvider() );
			return true;
		}
		//select and toggle between cluster modes
		else if(clusterWithGrid.containsPoint(x, y) && clusterWithGrid.isPressed == false) {
			clusterGridMode = true;
			clusterWithGrid.setPressed(true);
			clusterGlyphMode = false;
			clusterWithGlyph.setPressed(false);
			
			updateVisibleCoordinates();
			needRedraw = true;
			return true;
		}
		else if(clusterWithGlyph.containsPoint(x, y) && clusterWithGlyph.isPressed == false) {
			clusterGlyphMode = true;
			clusterWithGlyph.setPressed(true);
			clusterGridMode = false;
			clusterWithGrid.setPressed(false);
			
			updateVisibleCoordinates();
			needRedraw = true;
			return true;
		}
		//select graph
		else if(graph1Button.containsPoint(x, y) && graph1Button.isPressed == false) {
			graph1Button.setPressed(true);
			isGraph1 = true;
			isGraph2 = false;
			graph2Button.setPressed(false);
			drawMarkers(markersForGraph1);
			needRedraw = false;
//			updateVisibleCoordinates();
			return true;
		}
		else if(graph2Button.containsPoint(x, y) && graph2Button.isPressed == false) {
			graph2Button.setPressed(true);
			isGraph2 = true;
			isGraph1 = false;
			graph1Button.setPressed(false);
			drawMarkers(markersForGraph2);
			needRedraw = false;
//			updateVisibleCoordinates();
			return true;
		}
		return false;
	}
	
	boolean isOnMarker(float x, float y, MouseMovements event) {
		//touch on marker
		if(isGraph1) {
			for(Marker m : markersForGraph1) {
				if(m.containsPoint(x, y)) {
					if(event == MouseMovements.MOUSEDOWN)
						selectedMarker = m;
					return true;
				}
			}
		}
		else if(isGraph2) {
			for(Marker m : markersForGraph2) {
				if(m.containsPoint(x, y)) {
					if(event == MouseMovements.MOUSEDOWN)
						selectedMarker = m;
					return true;
				}
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
		in = new Button((mapControlPanelX+mapControlPanelWidth/2)-10, mapControlPanelHeight-70, 20, 20, x0, y0, "+", true);
		out = new Button((mapControlPanelX+mapControlPanelWidth/2)-10, mapControlPanelHeight-50, 20, 20, x0, y0, "-", true);

		up = new Button((mapControlPanelX+mapControlPanelWidth/2)-10, mapControlPanelHeight-95, 20, 20, x0, y0, "^", true);
		down = new Button((mapControlPanelX+mapControlPanelWidth/2)-10, mapControlPanelHeight-25, 20, 20, x0, y0, "v", true);
		
		left = new Button((mapControlPanelX+mapControlPanelWidth/2)-35, mapControlPanelHeight-60, 20, 20, x0, y0, "<", true);
		right = new Button((mapControlPanelX+mapControlPanelWidth/2)+15, mapControlPanelHeight-60, 20, 20, x0, y0, ">", true);
		
		
		road = new Button((mapOffsetX+mapOffsetWidth/2)-65, mapControlPanelHeight-25, 40, 20, x0, y0, "Road", true);
		hybrid = new Button((mapOffsetX+mapOffsetWidth/2)-20, mapControlPanelHeight-25, 40, 20, x0, y0, "Hybrid", true);
		aerial = new Button((mapOffsetX+mapOffsetWidth/2)+25, mapControlPanelHeight-25, 40, 20, x0, y0, "Aerial", true);
		
		//control buttons
		clusterWithGrid = new Button((mapOffsetX+mapOffsetWidth/3*2)+30, mapControlPanelHeight-25, 40, 20, x0, y0, "Cluster", true);
		clusterWithGlyph = new Button((mapOffsetX+mapOffsetWidth/3*2)+75, mapControlPanelHeight-25, 40, 20, x0, y0, "Glyph", true);
		
		//select graph
		graph1Button = new Button(mapControlPanelX+20, mapControlPanelHeight-150, 40, 20, x0, y0, "Graph 1", true);
		graph2Button = new Button(mapControlPanelX+65, mapControlPanelHeight-150, 40, 20, x0, y0, "Graph 2", true);

		buttons.add(in);
		buttons.add(out);
		buttons.add(road);
		buttons.add(hybrid);
		buttons.add(aerial);
		buttons.add(clusterWithGrid);
		buttons.add(clusterWithGlyph);
		buttons.add(graph1Button);
		buttons.add(graph2Button);
		buttons.add(up);
		buttons.add(down);
		buttons.add(left);
		buttons.add(right);

		initializeMap();
	}
	
	void drawMapButtons() {
		for(Button b:buttons)
			b.draw();
	}

	public static void fullZoomOut() {
		// Zoom to entire USA
		map.setCenterZoom(locationUSA, 5);
	}
	
	@Override
	public void draw() {
		if(needRedraw) {
			background(EnumColor.DARK_GRAY, 150);
			map.draw();
			drawMapOffsetArea();
			drawMapControlPanel();

			//print texts
			pushStyle();
			fill(EnumColor.BLACK);
			textSize(22);
			text("Rear View Mirror", mapOffsetX, 40);
			popStyle();
			
			//get data points from table (graph side)
			this.getData();
			
			//initialize and calculate grid stuff
			grid = new Grid(gridSize);
			this.lowestCrashCount = 0; this.highestCrashCount = 0;
			
			if(clusterGridMode) {
				clusterWithGrid.setPressed(true);//if its set true at startup
				
				if(isGraph1) {
					grid.setColor(EnumColor.GRAPH1);
					pointsForGraph1.clear();
					markersForGraph1.clear();
					
					for(ArrayList<DataPoint> d : dataForGraph1.values())
						pointsForGraph1.addAll(d);
					
//					System.out.println("MapPanel: Graph 1 points " + pointsForGraph1.size());
					
					grid.clusterData(pointsForGraph1);
					
					if(this.lowestCrashCount > grid.low)
						this.lowestCrashCount = grid.low;
					else if(this.highestCrashCount < grid.high)
						this.highestCrashCount = grid.high;
					
					if(map.getZoom() > stopClusterAtZoom)
						//dont cluster beyond this zoom
						markersForGraph1 = grid.showIndividualPoints(pointsForGraph1, this.lowestCrashCount, this.highestCrashCount);
					else
						markersForGraph1 = grid.getMarkers(EnumColor.GRAPH1, this.lowestCrashCount, this.highestCrashCount);

						drawMarkers(markersForGraph1);
				}
				else if(isGraph2) {
					grid.setColor(EnumColor.GRAPH2);
					pointsForGraph2.clear();
					markersForGraph2.clear();
					
					for(ArrayList<DataPoint> d : dataForGraph2.values())
						pointsForGraph2.addAll(d);
					
//					System.out.println("MapPanel: Graph 2 points " + pointsForGraph2.size());
					
					grid.clusterData(pointsForGraph2);
					
					if(this.lowestCrashCount > grid.low)
						this.lowestCrashCount = grid.low;
					else if(this.highestCrashCount < grid.high)
						this.highestCrashCount = grid.high;
					
					if(map.getZoom() > stopClusterAtZoom)
						//dont cluster beyond this zoom
						markersForGraph2 = grid.showIndividualPoints(pointsForGraph2, this.lowestCrashCount, this.highestCrashCount);
					else
						markersForGraph2 = grid.getMarkers(EnumColor.GRAPH2, this.lowestCrashCount, this.highestCrashCount);
					
					drawMarkers(markersForGraph2);
				}
				
			} else if(clusterGlyphMode) {
				
				if(isGraph1) {
					
					
				}
				else if(isGraph2) {
					
				}
			}
			
			if(selectedMarker != null)
				drawClusterInfo(selectedMarker);
			
			needRedraw = false;
		}
	}
	
	void drawGraph(HashMap<String, ArrayList<DataPoint>> dataForGraph, EnumColor color) {
		
	}
	
	void initializeMap() {
		mapSize = createPvector(mapOffsetWidth, mapOffsetHeight);
		mapOffset = createPvector(mapOffsetX, mapOffsetY);
		map = new InteractiveMap(SettingsLoader.papp, new Microsoft.RoadProvider(), 
				mapOffset.x, mapOffset.y, mapSize.x, mapSize.y);
		map.MAX_IMAGES_TO_KEEP = 256;
		map.setCenterZoom(locationUSA, initialZoom);
	}

	void drawMapControlPanel() {
		pushStyle();
		fill(EnumColor.DARK_GRAY, 160);
		noStroke();
		rect(mapControlPanelX, mapControlPanelY, mapControlPanelWidth, mapControlPanelHeight);
		rect(0, 0, mapOffsetX+mapOffsetWidth, mapOffsetY);
		rect(0, mapOffsetY, mapOffsetX, mapOffsetY+mapOffsetHeight);
		rect(mapOffsetX, mapOffsetY+mapOffsetHeight, mapOffsetWidth, height-(mapOffsetY+mapOffsetHeight));
		//draw 3 more surrounding offset
		popStyle();
		
		drawMapButtons();
	}
	
	public void getData() {
		dataForGraph1 = DBCommand.getInstance().getGraphData(Event.CHANGE_FILTER_GRAPH1);
		dataForGraph2 = DBCommand.getInstance().getGraphData(Event.CHANGE_FILTER_GRAPH2);
	}
	
	void drawMarkers(ArrayList<Marker> markers) {
		for(Marker m: markers)
			m.draw();
	}
	
	void drawClusterInfo(Marker m) {
		pushStyle();
		noFill();
		fill(EnumColor.BLACK);
		textSize(14);
		text("Crash Details", mapControlPanelX+5, 40);
		
		String info = "";
		
		if(selectedMarker.getCluster().getCrashCount() == 1) {
			CrashInfo crashinfo = ConnSqlite.getSingleCrashData(m.getCluster().getDataID());
			info = crashinfo.toString();
		}
		else
			info = "Count: " + selectedMarker.getCluster().getCrashCount();
		
		textSize(8);
		text(info, (float)mapControlPanelX+5, (float)mapOffsetY, 
				mapControlPanelWidth-10, mapOffsetHeight/3*2);
		popStyle();
	}

	void drawMapOffsetArea() {
		pushStyle();
		strokeWeight(1f);
		stroke(EnumColor.DARK_GRAY);
		noFill();
		rect(mapOffsetX, mapOffsetY, mapOffsetWidth, mapOffsetHeight);
		popStyle();
	}

	//return the xy-coordinates of offset area's top left and bottom right points
	public static PVector[] getBoundaryXY() {
		PVector[] p;

		float leftX = mapOffsetX;
		float rightX = mapOffsetX + mapOffsetWidth;
		float topY = mapOffsetY;
		float bottomY = mapOffsetY + mapOffsetHeight;

		p = new PVector[2];
		p[0] = new PVector(s(leftX), s(topY));
		p[1] = new PVector(s(rightX), s(bottomY));
		
		return p;
	}

	//return the lat-longs of offset area's top left and bottom right points
	public static Location[] getBoundaryLatLong() {
		Location[] loc;

		float leftX = mapOffsetX;
		float rightX = mapOffsetX + mapOffsetWidth;
		float topY = mapOffsetY;
		float bottomY = mapOffsetY + mapOffsetHeight;

		loc = new Location[2];
		loc[0] = map.pointLocation(s(leftX), s(topY));
		loc[1] = map.pointLocation(s(rightX), s(bottomY));
		return loc;
	}
	
	void setMapProvider(int newProviderID) {
		switch( newProviderID ) {
		case 0: map.setMapProvider( new Microsoft.RoadProvider() ); break;
		case 1: map.setMapProvider( new Microsoft.HybridProvider() ); break;
		case 2: map.setMapProvider( new Microsoft.AerialProvider() ); break;
		}
	}

	public void forceRedrawAllComponents() {
		for(Button b:buttons)
			b.setReDraw();
		
		needRedraw = true;
	}

	@Override
	public boolean touch(float x, float y, MouseMovements event) {
		return false;
	}
	
	@Override
	public void receiveNotification(Event eventName, Object... object) {
		
	}
}
