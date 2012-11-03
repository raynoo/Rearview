package edu.cs424.traffic.map.dataset;

import processing.core.PVector;

import com.modestmaps.geo.Location;

public class Cluster {

//	Location location;
	PVector locationxy;
	int crashCount;
	
	int dataID;
	
	public Cluster() {
		
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

//	public void setLocation(Location loc) {
//		this.location = loc;
//	}
//	
//	public Location getLocation() {
//		return this.location;
//	}
	
	public int getDataID() {
		return dataID;
	}
	
}
