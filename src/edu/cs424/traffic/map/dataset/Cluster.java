package edu.cs424.traffic.map.dataset;

import java.util.ArrayList;

import com.modestmaps.geo.Location;


public class Cluster {

	public static DataPoint cluster = new DataPoint();
	
	public Cluster() {
	}

	public static void drawCluster(ArrayList<DataPoint> data, Location center,
			int numOfGrids, float leftX, float rightX, float topY, float bottomY) {
//		int numOfGrids = 1;
		
		if(numOfGrids == 1) {
			cluster.setLocation(center);
			
			for(DataPoint d : data) {
				cluster.setCrashCount(cluster.getCrashCount() + d.getCrashCount());
			}
			//draw it here?
			
		} else {
			//divide space into 3 x 3?
			
		}
		
		
	}
	
	//lat longs shud be of the grids'
	DataPoint clusterPoints(ArrayList<DataPoint> data, float leftLon, float rightLon,
			float topLat, float bottomLat) {
		
		//divide visible area into grids
		//num of grids CAN depend on zoom level -> so that higher zooms can have higher num of grids/clusters
		
		for(DataPoint d : data) {
			
			if(d.getLocation().lat > topLat && d.getLocation().lat < bottomLat
					&& d.getLocation().lon > leftLon && d.getLocation().lon < rightLon) {
				
			}
		}
		
		
		return null;
	}
}
