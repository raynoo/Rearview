package edu.cs424.data.helper;

import java.util.ArrayList;

public class Findings 
{
	ArrayList<String> filters;   // each string should be of the type weather = rainy and so on...
	String interestingFinding;
	String event;
	float lat,longi;
	int zoom;

	public Findings(ArrayList<String> filters, String interestingFinding,String event,float lat,float  longi,int zoom)
	{
		this.filters = filters;
		this.interestingFinding = interestingFinding;
		this.event = event;
		this.lat = lat;
		this.longi = longi;
		this.zoom = zoom;
	}

	public ArrayList<String> getFilters() {
		return filters;
	}

	public String getInterestingFinding() {
		return interestingFinding;
	}

	public String getEvent() {
		return event;
	}

	public float getLat() {
		return lat;
	}

	public float getLongi() {
		return longi;
	}

	public int getZoom() {
		return zoom;
	}
	
	
}
