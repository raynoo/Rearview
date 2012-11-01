package edu.cs424.traffic.map.dataset;

import com.modestmaps.InteractiveMap;

import processing.core.PShape;
import edu.cs424.traffic.central.EnumColor;
import edu.cs424.traffic.central.Panel;

public class Marker extends Panel {
	
	int radius;
	int color;
	DataPoint dataPoint;
	PShape shape;
	InteractiveMap map;

	public Marker(float x0, float y0, float width, float height,
			float parentX0, float parentY0, PShape shape, DataPoint dp, InteractiveMap map) {
		
		super(x0, y0, width, height, parentX0, parentY0);
		
		if(shape != null)
			this.shape = shape;
		
		this.dataPoint = dp;
		this.map = map;
	}

	@Override
	public void setup() {
		
	}
	
	void calculateRadius() {
		float factor = 0.01f;
		
		if(map.getZoom() <= 4)
			factor = 0.001f;
		if(map.getZoom() >= 5)
			factor = 0.002f;
		if(map.getZoom() >= 7)
			factor = 0.03f;
		if(map.getZoom() >= 9)
			factor = 0.05f;
		
		this.radius = (int) ((float)dataPoint.getCrashCount() * factor);
	}
	
	public void draw() {
		
		if(shape == null && radius > -1) {
			calculateRadius();
			
			pushStyle();
			stroke(EnumColor.DARK_GRAY);
			strokeWeight(1.5f);
			fill(EnumColor.RED, 60);
			ellipse(map.locationPoint(dataPoint.getLocation()).x, map.locationPoint(dataPoint.getLocation()).y, 
					2*radius, 2*radius);
			popStyle();
		}
		
	}
	
	public boolean containsPoint(float x, float y) {
		if(Math.pow(x,2) + Math.pow(y, 2) <= Math.pow(radius, 2))
			return true;
		
		return false;
	}
}
