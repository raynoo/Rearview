package edu.cs424.traffic.map.dataset;

import java.util.ArrayList;

import com.modestmaps.geo.Location;

public class DataPoint {
	
	//change to MapLocation
	private Location location;
	private int crashCount;
	private boolean isCluster;
	
	//Filter Name -> Filter Value -> Count
	private ArrayList<String> filteredData;
	
	public DataPoint() {
		
	}
	
	//isCluster = false at all times. it is used only during creation of clusters.
	public DataPoint(double lat, double lon, int count, boolean isCluster) {
		this.location = new Location((float)lat, (float)lon);
		this.crashCount = count;
		this.isCluster = isCluster;
	}
	
	public DataPoint(Location location, int crashCount, ArrayList<String> filteredData) {
		this.location = location;
		this.crashCount = crashCount;
		this.filteredData = filteredData;
	}
	
	public void addAttributeValue(String filter) {
		this.filteredData.add(filter);
	}
	
//	public String toString() {
//		System.out.println(this.location);
//		for(String key : this.filteredData.keySet()) {
//			System.out.println("\t\"" + key + "\"");
//			HashMap<String, Integer> a = this.filteredData.get(key);
//			for(Entry<String,Integer> entry : a.entrySet()) {
//				System.out.println("\t\t\"" + entry.getKey() + "\" -> " + entry.getValue());
//			}
//		}
//		return "";
//	}
	
	//add Attribute Category Name (Weather, DUI, Age etc.)
//	public void addAttribute(String s) {
//		this.filteredData.put(s, null);
//	}
	
	//add Attribute Value + corresponding Count
//	public void addAttributeValue(String filter, String value, int count) {
//		HashMap<String, Integer> a = null;
//		
//		if(this.filteredData == null) {
//			this.filteredData = new HashMap<String, HashMap<String,Integer>>();
//			a = new HashMap<String, Integer>();
//			
//		} else if(this.filteredData.containsKey(filter)) {
//			//update existing values
//			a = this.filteredData.get(filter);
//			
//		} else if(!this.filteredData.containsKey(filter)) {
//			a = new HashMap<String, Integer>();
//		}
//		a.put(value, count);
//		this.filteredData.put(filter, a);
//	}
	
	//add latitude and longitude of location
	public void setLocation(Location location) {
		this.location = location;
	}
	
	public Location getLocation() {
		return location;
	}
	
	public ArrayList<String> getFilteredData() {
		return filteredData;
	}

	public void setFilterData(ArrayList<String> filteredData) {
		this.filteredData = filteredData;
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
