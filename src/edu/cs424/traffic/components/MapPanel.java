package edu.cs424.traffic.components;

import edu.cs424.traffic.central.Panel;
import edu.cs424.traffic.central.TouchEnabled;
import edu.cs424.traffic.components.MainPanel.MouseMovements;
import edu.cs424.traffic.pubsub.PubSub.Event;
import edu.cs424.traffic.pubsub.Suscribe;

public class MapPanel extends Panel implements TouchEnabled, Suscribe {

	public MapPanel(float x0, float y0, float width, float height,
			float parentX0, float parentY0) {
		super(x0, y0, width, height, parentX0, parentY0);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void receiveNotification(Event eventName, Object... object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean touch(float x, float y, MouseMovements event) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setup() {
		// TODO Auto-generated method stub
		
	}
	
	public void forceRedrawAllComponents(){
		
	}
	
	public void touch(int ID,float x,float y,MouseMovements event)
	{
		
	}

//	public static InteractiveMap map;
//	PVector mapSize;
//	PVector mapOffset;
//
//	//for clusters and grids
//	int gridSize = 4;
//	Location[][] gridLocations = new Location[gridSize+1][gridSize+1];
//	HashMap<Integer, Cluster> gridClusters;
//
//	PVector lastTouchPos;
//	PVector lastTouchPos2;
//	PVector initTouchPos;
//	PVector initTouchPos2;
//
//	HashMap<Integer,Point> touchList;
//	int touchID1,touchID2;
//
//	Button out, in, aerial, hybrid, road;
//	Button clusterByGrid, clusterByState;
//
//	boolean clusterGridMode = true, clusterStateMode = false;
//
//	ArrayList<Button> buttons = new ArrayList<Button>();
//	ArrayList<Marker> markers = new ArrayList<Marker>();
//
//	boolean firstIter = true;
//	ArrayList<DataPoint> points;
//
//	Location locationUSA = new Location(38.962f, -93.928f);
//
//	public MapPanel(float x0, float y0, float width, float height,
//			float parentX0, float parentY0) {
//		super(x0, y0, width, height, parentX0, parentY0);
//	}
//
//	@Override
//	public void receiveNotification(Event eventName, Object... object) {
//		// TODO Auto-generated method stub
//	}
//
//	public boolean touch(int ID, float x, float y, MouseMovements event) {
//
//		if(this.containsPoint(x, y))
//		{
//			if(MouseMovements.MOUSEDOWN == event)
//			{
//				//check if xy is on the buttons
//				if(in.containsPoint(x, y))
//					map.zoomIn();
//
//				else if(out.containsPoint(x, y))
//					map.zoomOut();
//
//				else if(aerial.containsPoint(x, y))
//					map.setMapProvider( new Microsoft.AerialProvider() );
//
//				else if(hybrid.containsPoint(x, y))
//					map.setMapProvider( new Microsoft.HybridProvider() );
//
//				else if(road.containsPoint(x, y))
//					map.setMapProvider( new Microsoft.RoadProvider() );
//
//				//select and toggle between cluster modes
//				else if(clusterByGrid.containsPoint(x, y) && clusterByGrid.isPressed == false) {
//					clusterGridMode = true;
//					clusterByGrid.setPressed(true);
//					clusterStateMode = false;
//					clusterByState.setPressed(false);
//				}
//
//				else if(clusterByState.containsPoint(x, y) && clusterByState.isPressed == false) {
//					clusterStateMode = true;
//					clusterByState.setPressed(true);
//					clusterGridMode = false;
//					clusterByGrid.setPressed(false);
//				}
//
//				//					
//				//					//touch on marker
//				//					for(Marker m : markers)
//				//						if(m.containsPoint(x, y)) {// && m.getCluster().getCrashCount() == 1) {
//				//							drawClusterInfo(m.getCluster());
//				//							break;
//				//						}
//
//				//else xy is on the map
//				else if (x > s(mapOffsetX) && x < s(mapOffsetX+mapOffsetWidth) 
//						&& y > s(mapOffsetY) && y < s(mapOffsetY+mapOffsetHeight)) {
//					lastTouchPos.x = x;
//					lastTouchPos.y = y;				
//					Point p = new Point(ID,x, y);
//					touchList.put(ID,p);
//
//					if(touchList.size() == 1)
//					{
//						touchID1 = ID;
//						initTouchPos.x = x;
//						initTouchPos.y = y;
//					}
//					else if(touchList.size() == 2 )
//					{
//						touchID2 = ID;
//						initTouchPos2.x = x;
//						initTouchPos2.y = y;
//					}
//				}
//			}
//			else if(MouseMovements.MOUSEUP == event)
//			{
//				touchList.remove(ID);
//			}
//			else if(MouseMovements.MOUSEDOWN == event)
//			{
//				if(x > s(mapOffsetX) && x < s(mapOffsetX+mapOffsetWidth) 
//						&& y > s(mapOffsetY) && y < s(mapOffsetY+mapOffsetHeight)) 
//				{
//					if(touchList.size() < 2)
//					{
//						map.tx += (x - lastTouchPos.x)/map.sc;
//						map.ty += (y - lastTouchPos.y)/map.sc;					
//
//					}
//					else if(touchList.size() == 2)
//					{
//						float sc = PApplet.dist(lastTouchPos.x, lastTouchPos.y, lastTouchPos2.x, lastTouchPos2.y);
//						float initPos = PApplet.dist(initTouchPos.x,initTouchPos.y,initTouchPos2.x,initTouchPos2.y);
//						PVector midpoint = new PVector( (lastTouchPos.x+lastTouchPos2.x)/2, (lastTouchPos.y+lastTouchPos2.y)/2 );
//						sc -= initPos;
//						sc /= 5000;
//						sc += 1;
//
//						float mx = (midpoint.x - mapOffset.x) - mapSize.x/2;
//						float my = (midpoint.y - mapOffset.y) - mapSize.y/2;
//						map.tx -= mx/map.sc;
//						map.ty -= my/map.sc;
//						map.sc *= sc;
//						map.tx += mx/map.sc;
//						map.ty += my/map.sc;					
//					}
//					else if( touchList.size() >= 5 )
//					{
//						// Zoom to entire USA
//						map.setCenterZoom(locationUSA, 4);
//					}
//					// Update touch IDs 1 and 2
//					if( ID == touchID1 ){
//						lastTouchPos.x = x;
//						lastTouchPos.y = y;
//					} else if( ID == touchID2 ){
//						lastTouchPos2.x = x;
//						lastTouchPos2.y = y;
//					}
//
//					// Update touch list
//					Point t = new Point( ID, x,y );
//					touchList.put(ID,t);
//				}
//			}
//		}
//		return false;
//	}
//
//	@Override
//	public void setup() 
//	{
//		//initialize touch position variables
//		touchList = new HashMap<Integer, Point>();
//		lastTouchPos = new PVector();
//		lastTouchPos2 = new PVector();
//		initTouchPos = new PVector();
//		initTouchPos2 = new PVector();
//
//		//initialize map buttons
//		in = new Button(mapControlPanelX+55, mapControlPanelHeight-80, 20, 20, x0, y0, "+", true);
//		out = new Button(mapControlPanelX+55, mapControlPanelHeight-55, 20, 20, x0, y0, "-", true);
//		road = new Button(mapControlPanelX+30, mapControlPanelHeight-30, 20, 20, x0, y0, "R", true);
//		hybrid = new Button(mapControlPanelX+55, mapControlPanelHeight-30, 20, 20, x0, y0, "H", true);
//		aerial = new Button(mapControlPanelX+80, mapControlPanelHeight-30, 20, 20, x0, y0, "A", true);
//
//		//control buttons //add cluster by state, cluster by grid
//		clusterByGrid = new Button(mapControlPanelX+30, mapControlPanelHeight-125, 40, 20, x0, y0, "Grid", true);
//		clusterByState = new Button(mapControlPanelX+75, mapControlPanelHeight-125, 40, 20, x0, y0, "State", true);
//
//		buttons.add(in);
//		buttons.add(out);
//		buttons.add(road);
//		buttons.add(hybrid);
//		buttons.add(aerial);
//		buttons.add(clusterByGrid);
//		buttons.add(clusterByState);
//
//		//initialize map
//		mapSize = createPvector(mapOffsetWidth, mapOffsetHeight);
//		mapOffset = createPvector(mapOffsetX, mapOffsetY);
//		map = new InteractiveMap(SettingsLoader.papp, new Microsoft.RoadProvider(), 
//				mapOffset.x, mapOffset.y, mapSize.x, mapSize.y);
//		map.MAX_IMAGES_TO_KEEP = 80;
//		map.setCenterZoom(locationUSA, 4);
//
//		//get lat-long from map-offset boundary
//		getBoundaryLatLong();
//	}
//
//	void drawMapControlPanel() {
//		pushStyle();
//		fill(EnumColor.DARK_GRAY, 95);
//		noStroke();
//		rect(mapControlPanelX, mapControlPanelY, mapControlPanelWidth, mapControlPanelHeight);
//
//		noFill();
//		strokeWeight(2);
//		stroke(EnumColor.BLACK);
//		textSize(18);
//		text("Details:", mapControlPanelX+30, mapOffsetY);
//		popStyle();
//
//
//	}
//
//	void drawMapButtons() {
//		for(Button b:buttons)
//			b.draw();
//	}
//
//	void drawClusterInfo(Cluster c) {
//		System.out.println("clicked! " + c.getDataID());
//	}
//
//
//	@Override
//	public void draw() 
//	{
//		if(firstIter == true) {
//			points = getData();
//			firstIter = false;
//		}
//
//		if(needRedraw) {
//			background(EnumColor.SOMERANDOM);
//			map.draw();
//			drawMapOffset();
//			drawMapControlPanel();
//			drawMapButtons();
//
//			if(clusterGridMode) {
//				cluster(points, getBoundaryLatLong());
//				drawClusters();
//				drawGridLines();
//
//			} else {
//				//draw indi points
//			}
//
//			System.out.println(map.getZoom());
//
//			needRedraw = false;
//		}
//	}
//
//	ArrayList<DataPoint> getData() {
//		return ConnSqlite.executeQuery(ConnSqlite.getLocations(new FilterData()));
//	}
//
//	void drawPointsForStates(HashMap<Location, Integer> states, PVector[] boundary) {
//		int pointSize = 2;
//
//		for(Entry<Location, Integer> entry : states.entrySet())  {
//			Point2f p = map.locationPoint(entry.getKey());
//			int crashcount = entry.getValue();
//			pointSize = (int) (0.08 * (float)crashcount);
//
//			pushStyle();
//			strokeWeight(1.5f);
//			stroke(EnumColor.DARK_GRAY);
//			fill(EnumColor.RED, 60);
//			ellipse(p.x, p.y, pointSize, pointSize);
//			popStyle();
//		}
//	}
//
//	void initializeGrid(Location[] latlong) {
//		float[] lat = new float[gridSize+1];
//		float[] lon = new float[gridSize+1];
//
//		lat[0] = latlong[0].lat; lat[gridSize] = latlong[1].lat;
//		lon[0] = latlong[0].lon; lon[gridSize] = latlong[1].lon;
//
//		for(int i = 1; i < gridSize; i++) {
//			lat[i] = lat[i-1] - ((lat[0] - lat[gridSize])/gridSize);
//			lon[i] = lon[i-1] - ((lon[0] - lon[gridSize])/gridSize);
//		}
//
//		for(int i = 0; i <= gridSize; i++)
//			for(int j = 0; j <= gridSize; j++)
//				gridLocations[i][j] = new Location(lat[i], lon[j]);
//	}
//
//	void cluster(ArrayList<DataPoint> data, Location[] latlong) {
//
//		initializeGrid(latlong);
//
//		gridClusters = new HashMap<Integer, Cluster>();
//
//		long start = System.currentTimeMillis();
//		for(DataPoint d : data) {
//			boolean addedToCluster = false;
//
//			for(int i = 0; i < gridSize; i++) {
//				for(int j = 0; j < gridSize; j++) {
//
//					if(d.getLocation().lat <= gridLocations[i][j].lat 
//							&& d.getLocation().lat >= gridLocations[i+1][j+1].lat 
//							&& d.getLocation().lon >= gridLocations[i][j].lon 
//							&& d.getLocation().lon <= gridLocations[i+1][j+1].lon) {
//
//						addedToCluster = true;
//						int mapKey = (i*gridSize)+j;
//						Cluster c;
//
//						if(gridClusters.get(mapKey) != null) {
//							c = gridClusters.get(mapKey);
//							c.addDataPoint(d);
//						}
//						else {
//							c = new Cluster(i, j, gridLocations[i][j], gridLocations[i+1][j+1], 0);
//						}
//						gridClusters.put(mapKey, c);
//
//						break;
//					}
//				}
//				if(addedToCluster)
//					break;
//			}
//		}
//		long end = System.currentTimeMillis();
//		System.out.println("\nDone clustering in " + (end - start) + "ms\n");
//	}
//
//	void drawClusters() {
//		for(Cluster c: gridClusters.values()) {
//			Marker m = new Marker(c, null);
//			m.draw();
//			markers.add(m);
//		}
//	}
//
//	void drawGridLines() {
//		pushStyle();
//		strokeWeight(1f);
//		stroke(EnumColor.DARK_GRAY);
//		//		line(map.locationPoint(gridLocations[1][0]).x, map.locationPoint(gridLocations[1][0]).y, 
//		//				map.locationPoint(gridLocations[1][3]).x,map.locationPoint(gridLocations[1][3]).y);
//		//		line(map.locationPoint(gridLocations[2][0]).x, map.locationPoint(gridLocations[2][0]).y,
//		//				map.locationPoint(gridLocations[2][3]).x, map.locationPoint(gridLocations[2][3]).y);
//		//		line(map.locationPoint(gridLocations[0][1]).x, map.locationPoint(gridLocations[0][1]).y, 
//		//				map.locationPoint(gridLocations[3][1]).x,map.locationPoint(gridLocations[3][1]).y);
//		//		line(map.locationPoint(gridLocations[0][2]).x, map.locationPoint(gridLocations[0][2]).y, 
//		//				map.locationPoint(gridLocations[3][2]).x,map.locationPoint(gridLocations[3][2]).y);
//
//		for(int i = 1; i < gridSize; i++) {
//			line(map.locationPoint(gridLocations[i][0]).x, map.locationPoint(gridLocations[i][0]).y, 
//					map.locationPoint(gridLocations[i][gridSize]).x,map.locationPoint(gridLocations[i][gridSize]).y);
//			line(map.locationPoint(gridLocations[0][i]).x, map.locationPoint(gridLocations[0][i]).y, 
//					map.locationPoint(gridLocations[gridSize][i]).x,map.locationPoint(gridLocations[gridSize][i]).y);
//		}
//
//		popStyle();
//	}
//
//	void drawMapOffset() {
//		//draw grid lines
//		pushStyle();
//		strokeWeight(1.5f);
//		stroke(EnumColor.DARK_GRAY);
//		noFill();
//		rect(mapOffsetX, mapOffsetY, mapOffsetWidth, mapOffsetWidth);
//		popStyle();
//	}
//
//	//return the xy coordinates of offset area's top left and bottom right points
//	Point2f[] getBoundaryXY() {
//		Location centerLocation = map.getCenter();
//		float leftX = (map.locationPoint(centerLocation).x - mapSize.x/2);
//		float rightX = (map.locationPoint(centerLocation).x + mapSize.x/2);
//		float topY = (map.locationPoint(centerLocation).y - mapSize.y/2);
//		float bottomY = (map.locationPoint(centerLocation).y + mapSize.y/2);
//
//		Point2f topLeft = new Point2f(leftX, topY);
//		Point2f bottomRight = new Point2f(rightX, bottomY);
//
//		Point2f[] points = {topLeft, bottomRight};
//		return points;
//	}
//
//	//return the lat-longs of offset area's top left and bottom right points
//	Location[] getBoundaryLatLong() {
//		Location[] loc;
//
//		//TODO: is it easier to calculate from offsetX and size?
//		Location centerLocation = map.getCenter();
//		float leftX = ((map.locationPoint(centerLocation).x) - mapSize.x/2);
//		float rightX = ((map.locationPoint(centerLocation).x) + mapSize.x/2);
//		float topY = ((map.locationPoint(centerLocation).y) - mapSize.y/2);
//		float bottomY = ((map.locationPoint(centerLocation).y) + mapSize.y/2);
//
//		Location topLeft = map.pointLocation(leftX, topY);
//		Location bottomRight = map.pointLocation(rightX, bottomY);
//
//		loc = new Location[2];
//		loc[0] = topLeft;
//		loc[1] = bottomRight;
//
//		return loc;
//	}
//
//	public void forceRedrawAllComponents() {
//		for(Button b:buttons)
//			b.setReDraw();
//				for(Marker m : markers)
//					m.setReDraw();
//						needRedraw = true;
//	}
//
//	@Override
//	public boolean touch(float x, float y, MouseMovements event) {
//		System.out.println("MapPanel.touch()" + "NOT IMPLEMENTED");
//		return false;
//	}
//
//	void setMapProvider(int newProviderID) {
//		switch( newProviderID ) {
//		case 0: map.setMapProvider( new Microsoft.RoadProvider() ); break;
//		case 1: map.setMapProvider( new Microsoft.HybridProvider() ); break;
//		case 2: map.setMapProvider( new Microsoft.AerialProvider() ); break;
//		}
//	}
}
