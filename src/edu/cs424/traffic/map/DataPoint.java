package edu.cs424.traffic.map;

import java.util.HashMap;

public class DataPoint {

	private float lat;
	private float lon;
	//if values are just counts then keep value as int
	private HashMap<String, String> filterData = new HashMap<String, String>();
	
	public DataPoint(float lat, float lon, HashMap<String, String> filterData) {
		this.lat = lat;
		this.lon = lon;
		this.filterData = filterData;
	}
	
	public void displayPoint() {
		
	}

	public float getLat() {
		return lat;
	}

	public void setLat(float lat) {
		this.lat = lat;
	}

	public float getLon() {
		return lon;
	}

	public void setLon(float lon) {
		this.lon = lon;
	}

	public HashMap<String, String> getFilterData() {
		return filterData;
	}

	public void setFilterData(HashMap<String, String> filterData) {
		this.filterData = filterData;
	}
	
}
