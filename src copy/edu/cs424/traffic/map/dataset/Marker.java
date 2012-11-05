package edu.cs424.traffic.map.dataset;

import processing.core.PApplet;
import edu.cs424.traffic.central.EnumColor;
import edu.cs424.traffic.central.SettingsLoader;
import edu.cs424.traffic.components.MapPanel;

public class Marker {
	
	int radius;
	EnumColor color;
	Cluster cluster;

	PApplet p;
	
	Marker() {
		
	}
	
	public Marker(Cluster cluster, EnumColor color, float rFactor) {
		this.cluster = cluster;
		this.color = color;
		calculateRadius(rFactor);
		p = SettingsLoader.papp;
	}
	
	public Marker(DataPoint data, EnumColor color, float rFactor) {
		this.cluster = new Cluster(data);
		this.color = color;
		calculateRadius(rFactor);
		p = SettingsLoader.papp;
	}
	
	void calculateRadius(float rFactor) {
		float factor = 0.01f;
		
		if(MapPanel.map.getZoom() <= 5)
			factor = 0.0005f;
		if(MapPanel.map.getZoom() >= 6)
			factor = 0.0025f;
		if(MapPanel.map.getZoom() >= 9)
			factor = 0.05f;
		if(MapPanel.map.getZoom() >= 13)
			factor = 0.1f;
		
		
		
		
		if(cluster.getCrashCount() == 1)
			this.radius = SettingsLoader.scaleFactor * 5;
		else
//			this.radius = (int) ((float)cluster.getCrashCount() * factor * (float)SettingsLoader.scaleFactor);
			this.radius = (int)(this.cluster.getCrashCount() * rFactor);
	}
	
	public void draw() {
		p.pushStyle();
		p.stroke(EnumColor.DARK_GRAY.getValue());
		p.strokeWeight(1f);
		p.fill(this.color.getValue(), 150);
		p.ellipse(cluster.getLocationXY().x, 
				cluster.getLocationXY().y, 
				2*radius, 2*radius);
		p.popStyle();
	}
	
	public boolean containsPoint(float x, float y) {
		float centerx = cluster.getLocationXY().x;
		float centery = cluster.getLocationXY().y;
		
		if(Math.pow(centerx - x, 2) + Math.pow(centery - y, 2) <= Math.pow(radius, 2))
			return true;
		
		return false;
	}
	
	public Cluster getCluster() {
		return cluster;
	}
	
}
