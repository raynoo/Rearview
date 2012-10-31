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
	
	//lat longs shud be of the grids'
//	DataPoint clusterPoints(ArrayList<DataPoint> data, float leftLon, float rightLon,
//			float topLat, float bottomLat) {
//		
//		//divide visible area into grids
//		//num of grids CAN depend on zoom level -> so that higher zooms can have higher num of grids/clusters
//		
//		for(DataPoint d : data) {
//			
//		}
//		return null;
//	}
}
