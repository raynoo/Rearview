package edu.cs424.traffic.map.dataset;

import java.util.ArrayList;
import java.util.HashMap;

import com.modestmaps.core.Point2f;
import com.modestmaps.geo.Location;

import edu.cs424.traffic.central.EnumColor;
import edu.cs424.traffic.central.Panel;
import edu.cs424.traffic.components.MapPanel;

import processing.core.PVector;

public class Grid extends Panel {

	PVector topLeft;
	PVector bottomRight;
	int gridSize;
	HashMap<Integer, Cell> gridCellMap;
	public static PVector[][] gridLocations;
	
	public Grid() {
		super(0, 0, 0, 0, 0, 0);
	}
	
	public Grid(PVector topLeft, PVector bottomRight, int gridSize) {
		super(0, 0, 0, 0, 0, 0);
		this.topLeft = topLeft;
		this.bottomRight = bottomRight;
		this.gridSize = gridSize;
		this.gridLocations = new PVector[gridSize+1][gridSize+1];
	}
	
	void createGridCells() {
		
		float[] x = new float[gridSize+1];
		float[] y = new float[gridSize+1];
		
		x[0] = topLeft.x; x[gridSize] = bottomRight.x;
		y[0] = topLeft.y; y[gridSize] = bottomRight.y;
		
		//each cell width, height
		float cellx = (x[gridSize] - x[0])/gridSize;
		float celly = (y[gridSize] - y[0])/gridSize;
		
		for(int i = 1; i < gridSize; i++) {
			x[i] = x[i-1] + cellx;
			y[i] = y[i-1] + celly;
		}
		
		for(int i = 0; i <= gridSize; i++)
			for(int j = 0; j <= gridSize; j++)
				gridLocations[i][j] = new PVector(x[i], y[j]);
	}
	
	public void clusterData(ArrayList<DataPoint> data) {
		createGridCells();
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
	}
	
	public ArrayList<Marker> getMarkers() {
		ArrayList<Marker> markers = new ArrayList<Marker>();
		
		for(Cell c: gridCellMap.values()) {
			c.createCluster();
			markers.add(c.getDataMarker());
		}
		return markers;
	}
	
	public void drawClusterGridLines() {
		pushStyle();
		strokeWeight(1f);
		stroke(EnumColor.DARK_GRAY);

		for(int i = 1; i < gridSize; i++) {
			line(Grid.gridLocations[i][0].x, Grid.gridLocations[i][0].y, 
					Grid.gridLocations[i][gridSize].x, Grid.gridLocations[i][gridSize].y);
			line(Grid.gridLocations[0][i].x, Grid.gridLocations[0][i].y, 
					Grid.gridLocations[gridSize][i].x, Grid.gridLocations[gridSize][i].y);
		}

		popStyle();
	}

	@Override
	public void setup() {
		
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
		this.cluster = new Cluster((this.topLeft.x + this.bottomRight.x)/2, 
				(this.topLeft.y + this.bottomRight.y)/2, 0);
		
		for(DataPoint d : datapoints)
			this.cluster.addDataPoint(d);
	}
	
	public Marker getDataMarker() {
		Marker marker = new Marker(this.cluster);
		
		return marker;
	}
	
	private Location calculatePosition() {
		return new Location((topLeft.x + bottomRight.x)/2, (topLeft.y + bottomRight.y)/2);
	}
}