package edu.cs424.traffic.components;

import java.util.ArrayList;
import java.util.HashMap;

import processing.core.PApplet;
import processing.core.PVector;
import processing.event.MouseEvent;

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
	Location locationUSA = new Location(38.962f, -93.928f);

	
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
	public static boolean clusterGridMode = true, clusterStateMode = false;
	
	ArrayList<Marker> markers = new ArrayList<Marker>();
	Marker selectedMarker;
	
	HashMap<String, ArrayList<DataPoint>> graph1Data, graph2Data;
	ArrayList<DataPoint> points = new ArrayList<DataPoint>();
	boolean isGraph1 = false, isGraph2 = false;
	

	//for buttons
	Button out, in, aerial, hybrid, road;
	Button clusterByGrid, clusterByState, graph1Button, graph2Button;
	ArrayList<Button> buttons = new ArrayList<Button>();

	boolean firstIter = true;

	
	public MapPanel(float x0, float y0, float width, float height,
			float parentX0, float parentY0) {
		super(x0, y0, width, height, parentX0, parentY0);
	}

	//TODO: FIX IT!!
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
				//else xy is on the active part of map (offset area)
				if (x > s(mapOffsetX) && x < s(mapOffsetX+mapOffsetWidth) 
						&& y > s(mapOffsetY) && y < s(mapOffsetY+mapOffsetHeight)) {

					//if xy is on the circles
					if(isOnMarker(x, y, event))
						return false;

					//update visible lat longs
					Location[] loc = getBoundaryLatLong();
					DBCommand.getInstance().updateVisibleCoordinate(loc[0], loc[1]);
					
					touchList.remove(ID);
					return false;
				}
			}
			else if(MouseMovements.MOUSEMOVE == event)
			{
				if(x > s(mapOffsetX) && x < s(mapOffsetX+mapOffsetWidth) 
						&& y > s(mapOffsetY) && y < s(mapOffsetY+mapOffsetHeight)) {
					
					//if xy is on the circles
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
						// Zoom to entire USA
						map.setCenterZoom(locationUSA, 6);
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

	boolean isOnButton(float x, float y) {
		if(in.containsPoint(x, y)) {
			map.zoomIn();
			
			//update visible lat longs
			Location[] loc = getBoundaryLatLong();
			DBCommand.getInstance().updateVisibleCoordinate(loc[0], loc[1]);
			
			return true;
		}
		else if(out.containsPoint(x, y)) {
			map.zoomOut();
			
			//update visible lat longs
			Location[] loc = getBoundaryLatLong();
			DBCommand.getInstance().updateVisibleCoordinate(loc[0], loc[1]);
			
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
		else if(clusterByGrid.containsPoint(x, y) && clusterByGrid.isPressed == false) {
			clusterGridMode = true;
			clusterByGrid.setPressed(true);
			clusterStateMode = false;
			clusterByState.setPressed(false);
			return true;
		}
		else if(clusterByState.containsPoint(x, y) && clusterByState.isPressed == false) {
			clusterStateMode = true;
			clusterByState.setPressed(true);
			clusterGridMode = false;
			clusterByGrid.setPressed(false);
			return true;
		}
		//select graph
		else if(graph1Button.containsPoint(x, y) && graph1Button.isPressed == false) {
			graph1Button.setPressed(true);
			isGraph1 = true;
			isGraph2 = false;
			graph2Button.setPressed(false);
			return true;
		}
		else if(graph2Button.containsPoint(x, y) && graph2Button.isPressed == false) {
			graph2Button.setPressed(true);
			isGraph2 = true;
			isGraph1 = false;
			graph1Button.setPressed(false);
			return true;
		}
		return false;
	}
	
	boolean isOnMarker(float x, float y, MouseMovements event) {
		//touch on marker
		for(Marker m : markers) {
			if(m.containsPoint(x, y)) {
				if(event == MouseMovements.MOUSEDOWN)
					selectedMarker = m;
				return true;
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
		in = new Button(mapControlPanelX+55, mapControlPanelHeight-80, 20, 20, x0, y0, "+", true);
		out = new Button(mapControlPanelX+55, mapControlPanelHeight-55, 20, 20, x0, y0, "-", true);
		road = new Button(mapControlPanelX+30, mapControlPanelHeight-30, 20, 20, x0, y0, "R", true);
		hybrid = new Button(mapControlPanelX+55, mapControlPanelHeight-30, 20, 20, x0, y0, "H", true);
		aerial = new Button(mapControlPanelX+80, mapControlPanelHeight-30, 20, 20, x0, y0, "A", true);

		//control buttons
		clusterByGrid = new Button(mapControlPanelX+30, mapControlPanelHeight-125, 40, 20, x0, y0, "Grid", true);
		clusterByState = new Button(mapControlPanelX+75, mapControlPanelHeight-125, 40, 20, x0, y0, "State", true);
		
		//select graph
		graph1Button = new Button(mapControlPanelX+30, mapControlPanelHeight-150, 40, 20, x0, y0, "Graph 1", true);
		graph2Button = new Button(mapControlPanelX+75, mapControlPanelHeight-150, 40, 20, x0, y0, "Graph 2", true);

		buttons.add(in);
		buttons.add(out);
		buttons.add(road);
		buttons.add(hybrid);
		buttons.add(aerial);
		buttons.add(clusterByGrid);
		buttons.add(clusterByState);
		buttons.add(graph1Button);
		buttons.add(graph2Button);

		initializeMap();
	}
	
	void drawMapButtons() {
		for(Button b:buttons)
			b.draw();
	}

	@Override
	public void draw() 
	{
		if(needRedraw) {
			background(EnumColor.SOMERANDOM);
			map.draw();
			drawMapOffsetArea();
			drawMapControlPanel();

			//get data points from table
			this.getData();
			points.clear();
			
			if(isGraph1)
				for(ArrayList<DataPoint> d : graph1Data.values())
					points.addAll(d);
			else if(isGraph2)
				for(ArrayList<DataPoint> d : graph2Data.values())
					points.addAll(d);
			
			System.out.println("MapPanel: Graph points " + points.size());
			System.out.println("Zoom: " + map.getZoom());
			
			if(clusterGridMode) {
				clusterByGrid.setPressed(true);
				PVector[] p = getBoundaryXY();
				grid = new Grid(p[0], p[1], gridSize);
				grid.clusterData(points);
				
				if(map.getZoom() > 12)
					drawRawData(points);
				else
					drawMarkers(grid.getMarkers());
				
			} else {
				
			}
			
			if(selectedMarker != null)
				drawClusterInfo(selectedMarker);
			
			needRedraw = false;
		}
	}

	void initializeMap() {
		mapSize = createPvector(mapOffsetWidth, mapOffsetHeight);
		mapOffset = createPvector(mapOffsetX, mapOffsetY);
		map = new InteractiveMap(SettingsLoader.papp, new Microsoft.RoadProvider(), 
				mapOffset.x, mapOffset.y, mapSize.x, mapSize.y);
		map.MAX_IMAGES_TO_KEEP = 256;
		map.setCenterZoom(locationUSA, 4);
	}

	void drawMapControlPanel() {
		pushStyle();
		fill(EnumColor.DARK_GRAY, 180);
		noStroke();
		rect(mapControlPanelX, mapControlPanelY, mapControlPanelWidth, mapControlPanelHeight);
		popStyle();
		
		drawMapButtons();
	}
	
	public void getData() {
		graph1Data = DBCommand.getInstance().getGraphData(Event.CHANGE_FILTER_GRAPH1);
		graph2Data = DBCommand.getInstance().getGraphData(Event.CHANGE_FILTER_GRAPH2);
	}

	void drawRawData(ArrayList<DataPoint> data) {
		ArrayList<Marker> ms = new ArrayList<Marker>();
		
		for(DataPoint d : data) {
			ms.add(new Marker(d));
		}
		drawMarkers(ms);
	}
	
	void drawMarkers(ArrayList<Marker> markers) {
		if(this.markers.isEmpty())
			this.markers = markers;
		
		if(! markers.isEmpty()) {
			for(Marker m: markers) {
				m.draw();
			}
		}
	}
	
	void drawClusterInfo(Marker m) {
		CrashInfo crashinfo = ConnSqlite.getSingleCrashData(m.getCluster().getDataID());
		
		pushStyle();
		noFill();
		fill(EnumColor.BLACK);
		textSize(14);
		text("Crash Details", mapControlPanelX+5, mapOffsetY-5);
		textSize(10);
		text(crashinfo.toString(), (float)mapControlPanelX+5, (float)mapOffsetY, mapControlPanelWidth-10, mapOffsetHeight/3*2);
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
//		for(Marker m : markers)
//			m.setReDraw();
		needRedraw = true;
	}

	@Override
	public boolean touch(float x, float y, MouseMovements event) {
		System.out.println("MapPanel.touch()" + "NOT IMPLEMENTED");
		return false;
	}
	
	@Override
	public void receiveNotification(Event eventName, Object... object) {
		
	}
}
