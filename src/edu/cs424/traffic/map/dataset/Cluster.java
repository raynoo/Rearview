package edu.cs424.traffic.map.dataset;

import java.util.ArrayList;

import processing.core.PVector;

import com.modestmaps.geo.Location;

public class Cluster {

	static DataPoint cluster = new DataPoint();
	int row, column;
	
	public Cluster() {
		
	}
	
	public static void drawCluster(ArrayList<DataPoint> data, Location center,
			int row, int col, PVector topLeft, PVector bottomRight) {

		cluster.setLocation(center);

		for(DataPoint d : data) {
			cluster.setCrashCount(cluster.getCrashCount() + d.getCrashCount());
		}
		
	}
	
}
