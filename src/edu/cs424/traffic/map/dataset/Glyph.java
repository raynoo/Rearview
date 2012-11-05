package edu.cs424.traffic.map.dataset;

import java.util.ArrayList;

import com.modestmaps.geo.Location;

import edu.cs424.traffic.central.EnumColor;
import edu.cs424.traffic.central.SettingsLoader;

public class Glyph extends Marker {

	Cluster cluster;
	Location location;
	
	EnumColor color1, color2, color3;
	
	public Glyph(ArrayList<DataPoint> data) {
		super();
		
		
	}
	
	void calculateRadius() {
		this.radius = SettingsLoader.scaleFactor * 5;
	}
	
	public void draw() {
		
	}
	
	public boolean containsPoint(float x, float y) {
		float centerx = cluster.getLocationXY().x;
		float centery = cluster.getLocationXY().y;
		
		if(Math.pow(centerx - x, 2) + Math.pow(centery - y, 2) <= Math.pow(radius, 2))
			return true;
		
		return false;
	}
	
}
