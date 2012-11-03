package edu.cs424.traffic.map.dataset;

import com.modestmaps.geo.Location;

public class Cluster {

	Location location;
	int crashCount;
	
	int row, column;
	Location topLeft, bottomRight;
	
	int dataID;
	
	public Cluster() {
		
	}
	
	public Cluster(int row, int column, Location topLeft, Location bottomRight, int dataID) {
		this.row = row;
		this.column = column;
		this.topLeft = topLeft;
		this.bottomRight = bottomRight;
		this.setLocation(new Location(topLeft.lat - ((topLeft.lat - bottomRight.lat)/2),
				bottomRight.lon + ((topLeft.lon - bottomRight.lon)/2)));
		this.crashCount = 1;
	}
	
	public void addDataPoint(DataPoint dp) {
		this.crashCount ++;
	}
	
	public String toString() {
		return "Grid cell: " + row + ", " + column + " Crashes: " + crashCount;
	}
	
	public int getCrashCount() {
		return this.crashCount;
	}
	
	public void setCrashCount(int count) {
		this.crashCount = count;
	}

	public void setLocation(Location loc) {
		this.location = loc;
	}
	
	public Location getLocation() {
		return this.location;
	}
	
	public int getRow() {
		return this.row;
	}
	
	public int getColumn() {
		return this.column;
	}

	public int getDataID() {
		return dataID;
	}
	
}
