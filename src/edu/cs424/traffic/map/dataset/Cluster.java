package edu.cs424.traffic.map.dataset;

import edu.cs424.traffic.components.MapPanel;
import processing.core.PVector;

public class Cluster {

	PVector locationxy;
	int crashCount;
	int dataID;
	
	public Cluster() {
		
	}
	
	public Cluster(DataPoint point) {
		this(MapPanel.map.locationPoint(point.getLocation()).x,
				MapPanel.map.locationPoint(point.getLocation()).y, 
				Integer.parseInt(point.uid));
	}
	
	public Cluster(float x, float y, int dataID) {
		this.setLocationXY(x, y);
		this.crashCount = 1;
		this.dataID = dataID;
	}
	
	public void addDataPoint(DataPoint dp) {
		this.crashCount++;
	}
	
	public String toString() {
		return "Cluster " + dataID + ", Crashes: " + crashCount;
	}
	
	public int getCrashCount() {
		return this.crashCount;
	}
	
	public void setCrashCount(int count) {
		this.crashCount = count;
	}
	
	public void setLocationXY(float x, float y) {
		this.locationxy = new PVector(x, y);
	}
	
	public PVector getLocationXY() {
		return this.locationxy;
	}

	public int getDataID() {
		return dataID;
	}
	
}
