package edu.cs424.traffic.map.dataset;

import java.util.ArrayList;
import java.util.HashMap;

import com.modestmaps.core.Point2f;
import com.modestmaps.geo.Location;

import edu.cs424.traffic.central.EnumColor;
import edu.cs424.traffic.central.Panel;
import edu.cs424.traffic.central.SettingsLoader;
import edu.cs424.traffic.components.MapPanel;

import processing.core.PApplet;
import processing.core.PVector;

public class Grid extends Panel {

	PVector topLeft;
	PVector bottomRight;
	int gridSize;
	HashMap<Integer, Cell> gridCellMap;
	public static PVector[][] gridLocations;
	ArrayList<Marker> dataMarkers;
	
	static Cell highest, lowest;
	static float cellx, celly;
	
	EnumColor graphColor;
	
	PApplet p;
	
	public Grid() {
		super(0, 0, 0, 0, 0, 0);
	}
	
	public Grid(int gridSize) {
		super(0, 0, 0, 0, 0, 0);
		
		PVector[] p = MapPanel.getBoundaryXY();
		this.topLeft = p[0];
		this.bottomRight = p[1];
		this.gridSize = gridSize;
		this.gridLocations = new PVector[gridSize+1][gridSize+1];
	}
	
	public void setColor(EnumColor color) {
		this.graphColor = color;
	}
	
	void getHighLow() {
		int high = Integer.MIN_VALUE, low = Integer.MAX_VALUE;
		
		for(Cell c : this.gridCellMap.values()) {
			if(high < c.cluster.getCrashCount()) {
				high = c.cluster.getCrashCount();
				highest = c;
			}
			else if(low > c.cluster.getCrashCount()) {
				low = c.cluster.getCrashCount();
				lowest = c;
			}
		}
	}
	
	public void clusterData(ArrayList<DataPoint> data) {
		this.setup();
		gridCellMap = new HashMap<Integer, Cell>();
		
		for(DataPoint d : data) {
			boolean addedToCluster = false;
			Point2f dataxy = MapPanel.map.locationPoint(d.getLocation());

			for(int i = 0; i < gridSize; i++) {
				for(int j = 0; j < gridSize; j++) {
					
					if(dataxy.x >= gridLocations[i][j].x 
							&& dataxy.x <= gridLocations[i+1][j+1].x 
							&& dataxy.y >= gridLocations[i][j].y 
							&& dataxy.y <= gridLocations[i+1][j+1].y) {

						addedToCluster = true;
						int mapKey = (i*gridSize)+j;
						Cell c;

						if(gridCellMap.get(mapKey) != null) {
							c = gridCellMap.get(mapKey);
						}
						else {
							c = new Cell(gridLocations[i][j], gridLocations[i+1][j+1]);
						}
						c.addDataPoint(d);
						gridCellMap.put(mapKey, c);
						break;
					}
				}
				if(addedToCluster)
					break;
			}
		}
		createClusters();
//		createMarkers();
	}
	
	void createClusters() {
		int high = Integer.MIN_VALUE, low = Integer.MAX_VALUE;
		
		for(Cell c: gridCellMap.values()) {
			c.createCluster();
			
			if(high < c.cluster.getCrashCount()) {
				high = c.cluster.getCrashCount();
				highest = c;
			}
			else if(low > c.cluster.getCrashCount()) {
				low = c.cluster.getCrashCount();
				lowest = c;
			}
		}
	}
	
	void createMarkers() {
		dataMarkers = new ArrayList<Marker>();
		
		for(Cell c: gridCellMap.values()) {
			dataMarkers.add(c.getDataMarker(this.graphColor, 
					lowest.cluster.getCrashCount(), highest.cluster.getCrashCount(),
					this.celly));
		}
	}
	
	public ArrayList<Marker> showIndividualPoints(ArrayList<DataPoint> data) {
		dataMarkers = new ArrayList<Marker>();
		
		for(DataPoint d : data) {
			dataMarkers.add(new Marker(d, this.graphColor, this.lowest.cluster.getCrashCount(), 
					this.highest.cluster.getCrashCount(), this.celly));
		}
		return dataMarkers;
	}
	
	public ArrayList<Marker> getMarkers(EnumColor color) {
		createMarkers();
		if(MapPanel.clusterGridMode) //TODO: how to handle state view?
			drawClusterGridLines();
		
		return dataMarkers;
	}
	
	public void drawClusterGridLines() {
		p = SettingsLoader.papp;
		p.pushStyle();
		p.strokeWeight(1f);
		p.stroke(EnumColor.DARK_GRAY.getValue());

		for(int i = 1; i < gridSize; i++) {
			p.line(Grid.gridLocations[i][0].x, Grid.gridLocations[i][0].y, 
					Grid.gridLocations[i][gridSize].x, Grid.gridLocations[i][gridSize].y);
			p.line(Grid.gridLocations[0][i].x, Grid.gridLocations[0][i].y, 
					Grid.gridLocations[gridSize][i].x, Grid.gridLocations[gridSize][i].y);
		}

		p.popStyle();
	}

	@Override
	public void setup() {
		float[] x = new float[gridSize+1];
		float[] y = new float[gridSize+1];
		
		x[0] = topLeft.x; x[gridSize] = bottomRight.x;
		y[0] = topLeft.y; y[gridSize] = bottomRight.y;
		
		//each cell width, height
		cellx = (x[gridSize] - x[0])/gridSize;
		celly = (y[gridSize] - y[0])/gridSize;
		
		for(int i = 1; i < gridSize; i++) {
			x[i] = x[i-1] + cellx;
			y[i] = y[i-1] + celly;
		}
		
		for(int i = 0; i <= gridSize; i++)
			for(int j = 0; j <= gridSize; j++)
				gridLocations[i][j] = new PVector(x[i], y[j]);
	}
}

class Cell {
	PVector topLeft;
	PVector bottomRight;
	Location midpoint;
	Cluster cluster;
	ArrayList<DataPoint> datapoints;
	
	public Cell() {
		
	}
	
	public Cell(PVector topLeft, PVector bottomRight) {
		this.topLeft = topLeft;
		this.bottomRight = bottomRight;
		this.midpoint = calculatePosition();
		this.datapoints = new ArrayList<DataPoint>();
	}
	
	public void addDataPoint(DataPoint d) {
		datapoints.add(d);
	}
	
	public void createCluster() {
		int uid = 0;
		if(this.datapoints.size() == 1)
			uid = Integer.parseInt(this.datapoints.get(0).getUid());
		
		this.cluster = new Cluster((this.topLeft.x + this.bottomRight.x)/2, 
				(this.topLeft.y + this.bottomRight.y)/2, uid);
		
		for(DataPoint d : datapoints)
			this.cluster.addDataPoint(d);
		
	}
	
	public Marker getDataMarker(EnumColor color, int lowestCount, int highestCount, float celly) {
		Marker marker = new Marker(this.cluster, color, lowestCount, highestCount, celly);
		
		return marker;
	}
	
	private Location calculatePosition() {
		return new Location((topLeft.x + bottomRight.x)/2, (topLeft.y + bottomRight.y)/2);
	}
}