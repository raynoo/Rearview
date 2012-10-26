package edu.cs424.traffic.map.dataset;

public class MapLocation {
	float lat;
	float lon;
	
	public MapLocation(float lat, float lon) {
		this.lat = lat;
		this.lon = lon;
	}

	public float getLat() {
		return lat;
	}

	public float getLon() {
		return lon;
	}
	
}
