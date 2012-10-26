package edu.cs424.traffic.map.dataset;

import java.util.HashMap;
import java.util.Map.Entry;

public class DataPoint {
	
	//change to MapLocation
	private String location;
	
	//Filter Name -> Filter Value -> Count
	private HashMap<String, HashMap<String, Integer>> filteredData;
	
	public DataPoint() {
		
	}
	
	public DataPoint(String location, HashMap<String, HashMap<String, Integer>> filteredData) {
		this.location = location;
		this.filteredData = filteredData;
	}
	
	public String toString() {
		System.out.println(this.location);
		for(String key : this.filteredData.keySet()) {
			System.out.println("\t\"" + key + "\"");
			HashMap<String, Integer> a = this.filteredData.get(key);
			for(Entry<String,Integer> entry : a.entrySet()) {
				System.out.println("\t\t\"" + entry.getKey() + "\" -> " + entry.getValue());
			}
		}
		return "";
	}
	
	//add Attribute Category Name (Weather, DUI, Age etc.)
	public void addAttribute(String s) {
		this.filteredData.put(s, null);
	}
	
	//add Attribute Value + corresponding Count
	public void addAttributeValue(String filter, String value, int count) {
		HashMap<String, Integer> a = null;
		
		if(this.filteredData == null) {
			this.filteredData = new HashMap<String, HashMap<String,Integer>>();
			a = new HashMap<String, Integer>();
			
		} else if(this.filteredData.containsKey(filter)) {
			//update existing values
			a = this.filteredData.get(filter);
			
		} else if(!this.filteredData.containsKey(filter)) {
			a = new HashMap<String, Integer>();
		}
		a.put(value, count);
		this.filteredData.put(filter, a);
	}

	//add latitude and longitude of location
	public void setLocation(String location) {
		this.location = location;
	}
	
//	public MapLocation getLocation() {
//		return location;
//	}
	
	public HashMap<String, HashMap<String, Integer>> getFilterData() {
		return filteredData;
	}

	public void setFilterData(HashMap<String, HashMap<String, Integer>> filteredData) {
		this.filteredData = filteredData;
	}
	
}
