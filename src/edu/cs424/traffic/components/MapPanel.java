package edu.cs424.traffic.components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import processing.core.PApplet;
import processing.core.PVector;

import com.modestmaps.InteractiveMap;
import com.modestmaps.core.Point2f;
import com.modestmaps.geo.Location;
import com.modestmaps.providers.Microsoft;

import edu.cs424.traffic.central.EnumColor;
import edu.cs424.traffic.central.Panel;
import edu.cs424.traffic.central.SettingsLoader;
import edu.cs424.traffic.map.dataset.DataPoint;
import edu.cs424.traffic.map.dataset.Marker;
import edu.cs424.traffic.map.dataset.StateLatLon;
import edu.cs424.traffic.map.utils.Point;
import edu.cs424.traffic.pubsub.PubSub.Event;
import edu.cs424.traffic.pubsub.Suscribe;
import edu.cs424.traffic.sqliteconn.ConnSqlite;
import edu.cs424.traffic.sqliteconn.FilterData;
import edu.cs424.traffic.central.TouchEnabled;
import edu.cs424.traffic.components.MainPanel.MouseMovements;
import edu.cs424.traffic.gui.Button;
import static edu.cs424.data.helper.AppConstants.*;

public class MapPanel extends Panel implements TouchEnabled,Suscribe
{
	static InteractiveMap map;
	PVector mapSize;
	PVector mapOffset;
	
	//for clusters and grids
	float lat0, lat1, lat2, lat3, lon0, lon1, lon2, lon3;
	Location loc00, loc01, loc02, loc10, loc11, loc12, loc13, loc20, loc21, loc22, loc23, loc31, loc32, loc33;
	public static Location[] loc;

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
	
	Button out, in, aerial, hybrid, road;
	
	ArrayList<Button> buttons = new ArrayList<Button>();
	ArrayList<Marker> markers = new ArrayList<Marker>();
	
	boolean firstIter = true;
	ArrayList<DataPoint> points;
	
	Location locationUSA = new Location(38.962f, -93.928f);

	public boolean touch(int ID,float x, float y, MouseMovements event) {

		if(this.containsPoint(x, y))
		{
			
				if(MouseMovements.MOUSEDOWN == event)
				{
					//check if xy is on the buttons
					if(in.containsPoint(x, y)) {
						map.zoomIn();
					}
					
					else if(out.containsPoint(x, y))
						map.zoomOut();
					
					else if(aerial.containsPoint(x, y))
						map.setMapProvider( new Microsoft.AerialProvider() );
					
					else if(hybrid.containsPoint(x, y))
						map.setMapProvider( new Microsoft.HybridProvider() );
					
					else if(road.containsPoint(x, y))
						map.setMapProvider( new Microsoft.RoadProvider() );
					
					
					//else xy is on the map
					else if (x > mapOffsetX && x < mapOffsetX+mapOffsetWidth 
							&& y > mapOffsetY && y < mapOffsetY+mapOffsetHeight) {
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
				else if (x > mapOffsetX && x < mapOffsetX+mapOffsetWidth 
						&& y > mapOffsetY && y < mapOffsetY+mapOffsetHeight)
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
		in = new Button(mapControlPanelX+55, mapControlPanelHeight-80, 20, 20, x0, y0, "+", true);
		out = new Button(mapControlPanelX+55, mapControlPanelHeight-55, 20, 20, x0, y0, "-", true);
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
		drawMapButtons();
	}
	
	void drawMapControlPanel() {
		pushStyle();
		fill(EnumColor.DARK_GRAY, 95);
		noStroke();
		rect(mapControlPanelX, mapControlPanelY, mapControlPanelWidth, mapControlPanelHeight);
		popStyle();
		
	}
	
	void drawMapButtons() {
		for(Button b:buttons)
			b.draw();
	}

	@Override
	public void draw() 
	{
		if(firstIter == true) {
			points = getData();
			firstIter = false;
		}
		
		if(needRedraw)
		{
			System.out.println("MapPanel.draw()" + "map re drawn");
			background(EnumColor.SOMERANDOM);
			map.draw();
			drawMapOffset();
			
			//get lat-long from map-offset boundary
			Location[] latlong = getBoundaryLatLong();
			cluster(points, latlong);
			
			drawMapControlPanel();
			drawMapButtons();
			
			System.out.println(map.getZoom());
			
			needRedraw = false;
		}
	}

	//supposed to be inside db-querying class
	HashMap<Location, Integer> getDataPoints(float lat1, float lon1, float lat2, float lon2) {
		
		return new StateLatLon().getStates();
	}
	
	ArrayList<DataPoint> getData() {
		return ConnSqlite.executeQuery(ConnSqlite.getCrashes(new FilterData()));
	}

	void drawPointsForStates(HashMap<Location, Integer> states, PVector[] boundary) {
		int pointSize = 2;
		
		for(Entry<Location, Integer> entry : states.entrySet())  {
			Point2f p = map.locationPoint(entry.getKey());
			int crashcount = entry.getValue();
			pointSize = (int) (0.08 * (float)crashcount);

			pushStyle();
			strokeWeight(1.5f);
			stroke(EnumColor.DARK_GRAY);
			fill(EnumColor.RED, 60);
			ellipse(p.x, p.y, pointSize, pointSize);
			popStyle();
		}
	}
	
	void cluster(ArrayList<DataPoint> data, Location[] latlong) {
		
		lat0 = latlong[0].lat;
		lat3 = latlong[1].lat;
		lat1 = lat0 - ((lat0 - lat3) / 3);
		lat2 = lat0 - ((lat0 - lat3) / 3 * 2);
		
		lon0 = latlong[0].lon;
		lon3 = latlong[1].lon;
		lon1 = lon0 - ((lon0 - lon3) / 3);
		lon2 = lon0 - ((lon0 - lon3) / 3 * 2);
		
		loc00 = new Location(lat0, lon0);
		loc01 = new Location(lat0, lon1);
		loc02 = new Location(lat0, lon2);
		
		loc10 = new Location(lat1, lon0);
		loc11 = new Location(lat1, lon1);
		loc12 = new Location(lat1, lon2);
		loc13 = new Location(lat1, lon3);
		
		loc20 = new Location(lat2, lon0);
		loc21 = new Location(lat2, lon1);
		loc22 = new Location(lat2, lon2);
		loc23 = new Location(lat2, lon3);
		
		loc31 = new Location(lat3, lon1);
		loc32 = new Location(lat3, lon2);
		loc33 = new Location(lat3, lon3);
		
		drawGridLines();
		
		drawCluster(createCluster(data, loc00, loc11));
		drawCluster(createCluster(data, loc10, loc21));
		drawCluster(createCluster(data, loc20, loc31));
		
		drawCluster(createCluster(data, loc01, loc21));
		drawCluster(createCluster(data, loc11, loc22));
		drawCluster(createCluster(data, loc21, loc32));
		
		drawCluster(createCluster(data, loc02, loc13));
		drawCluster(createCluster(data, loc12, loc23));
		drawCluster(createCluster(data, loc22, loc33));
		
	}
	
	void drawGridLines() {
		//draw grid lines
		pushStyle();
		strokeWeight(1.5f);
		stroke(EnumColor.DARK_GRAY);
		line(map.locationPoint(loc10).x,map.locationPoint(loc10).y, map.locationPoint(loc13).x,map.locationPoint(loc13).y);
		line(map.locationPoint(loc20).x,map.locationPoint(loc20).y, map.locationPoint(loc23).x,map.locationPoint(loc23).y);
		line(map.locationPoint(loc01).x,map.locationPoint(loc01).y, map.locationPoint(loc31).x,map.locationPoint(loc31).y);
		line(map.locationPoint(loc02).x,map.locationPoint(loc02).y, map.locationPoint(loc32).x,map.locationPoint(loc32).y);
		popStyle();
	}
	
	void drawMapOffset() {
		//draw grid lines
		pushStyle();
		strokeWeight(1.5f);
		stroke(EnumColor.DARK_GRAY);
		noFill();
		rect(mapOffsetX, mapOffsetY, mapSize.x, mapSize.y);
		popStyle();
	}
	
	DataPoint createCluster(ArrayList<DataPoint> data, Location topLeft, Location bottomRight) {

		DataPoint cluster = new DataPoint();
		cluster.setLocation(new Location(topLeft.lat - ((topLeft.lat - bottomRight.lat)/2),
				bottomRight.lon + ((topLeft.lon - bottomRight.lon)/2)));

		for(DataPoint d : data) {
			
			if(d.getLocation().lat <= topLeft.lat && d.getLocation().lat >= bottomRight.lat
					&& d.getLocation().lon >= topLeft.lon && d.getLocation().lon <= bottomRight.lon) {
				
				cluster.setCrashCount(cluster.getCrashCount() + d.getCrashCount());
			}
		}
		return cluster;
	}
	
	void drawCluster(DataPoint cluster) {
		
		Marker m = new Marker(0, 0, 0, 0, x0, y0, null, cluster, map);
		m.draw();
		markers.add(m);
	}
	
	//return the xy coordinates of offset area's top left and bottom right points
	Point2f[] getBoundaryXY() {
		Location centerLocation = map.getCenter();
		float leftX = (map.locationPoint(centerLocation).x - mapSize.x/2);
		float rightX = (map.locationPoint(centerLocation).x + mapSize.x/2);
		float topY = (map.locationPoint(centerLocation).y - mapSize.y/2);
		float bottomY = (map.locationPoint(centerLocation).y + mapSize.y/2);
		
		Point2f topLeft = new Point2f(leftX, topY);
		Point2f bottomRight = new Point2f(rightX, bottomY);
		
		Point2f[] points = {topLeft, bottomRight};
		return points;
	}

	//return the lat-longs of offset area's top left and bottom right points
	Location[] getBoundaryLatLong() {
		//TODO:or is it easier to calculate from offsetX and size?
		Location centerLocation = map.getCenter();
		float leftX = (map.locationPoint(centerLocation).x - mapSize.x/2);
		float rightX = (map.locationPoint(centerLocation).x + mapSize.x/2);
		float topY = (map.locationPoint(centerLocation).y - mapSize.y/2);
		float bottomY = (map.locationPoint(centerLocation).y + mapSize.y/2);

		Location topLeft = map.pointLocation(leftX, topY);
		Location bottomRight = map.pointLocation(rightX, bottomY);

		loc = new Location[2];
		loc[0] = topLeft;
		loc[1] = bottomRight;
		
		return loc;
	}

	public void forceRedrawAllComponents()
	{
		for(Button b:buttons)
			b.setReDraw();
		for(Marker m : markers)
			m.setReDraw();
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
