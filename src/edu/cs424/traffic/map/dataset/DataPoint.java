package edu.cs424.traffic.map.dataset;

import com.modestmaps.geo.Location;

public class DataPoint {
	
	private Location location;
	private int crashCount;
	private boolean isCluster;
	
	public DataPoint() {
		
	}
	
	//isCluster = false at all times. it is used only during creation of clusters.
	public DataPoint(double lat, double lon, int count, boolean isCluster) {
		this.location = new Location((float)lat, (float)lon);
		this.crashCount = count;
		this.isCluster = isCluster;
	}
	
	public String toString() {
		return "Location: " + this.location + ", Crash count: " + this.crashCount;
	}
	
	public void setLocation(Location location) {
		this.location = location;
	}
	
	public Location getLocation() {
		return location;
	}
	
	public int getCrashCount() {
		return crashCount;
	}
	
	public void setCrashCount(int count) {
		this.crashCount = count;
	}

	public boolean isCluster() {
		return isCluster;
	}
}
