package edu.cs424.traffic.map.dataset;

import com.modestmaps.geo.Location;

public class DataPoint {
	
	String uid;
	private Location d;
	String when;  // it can have a year/month/day of week
	
	
	public DataPoint(String uid , Location d,String when) 
	{
		this.uid = uid;
		this.when = when;
		this.d = d;
	}


	public Location getLocation() {
		return d;
	}
	
	public String getUid() {
		return uid;
	}

	public String getWhen() {
		return when;
	}

	public boolean isInside(Location topLeft,Location bottomRight)
	{
		if(d.lat <= topLeft.lat && d.lat >= bottomRight.lat
				&& d.lon >= topLeft.lon && d.lon <= bottomRight.lon) 
		{
			return true;
		}
		else
			return false;
	}
	
}

