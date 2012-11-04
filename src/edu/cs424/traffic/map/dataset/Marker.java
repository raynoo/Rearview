package edu.cs424.traffic.map.dataset;

import processing.core.PApplet;
import edu.cs424.traffic.central.EnumColor;
import edu.cs424.traffic.central.SettingsLoader;
import edu.cs424.traffic.components.MainPanel.MouseMovements;
import edu.cs424.traffic.components.MapPanel;

public class Marker {
	
	int radius;
	int color;
	Cluster cluster;

	PApplet p;
	
	public Marker(Cluster cluster) {
		this.cluster = cluster;
		calculateRadius();
		p = SettingsLoader.papp;
	}
	
	public Marker(DataPoint data) {
		this.cluster = new Cluster(data);
		calculateRadius();
		p = SettingsLoader.papp;
	}
	
	void calculateRadius() {
		float factor = 0.01f;
		
		if(MapPanel.map.getZoom() <= 5)
			factor = 0.0005f;
		if(MapPanel.map.getZoom() >= 6)
			factor = 0.005f;
		if(MapPanel.map.getZoom() >= 9)
			factor = 0.05f;
		if(MapPanel.map.getZoom() >= 13)
			factor = 0.1f;
		
		if(cluster.getCrashCount() == 1)
			this.radius = SettingsLoader.scaleFactor * 3;
		else
			this.radius = (int) ((float)cluster.getCrashCount() * factor * (float)SettingsLoader.scaleFactor);
	}
	
	public void draw() {
		p.pushStyle();
		p.stroke(EnumColor.DARK_GRAY.getValue());
		p.strokeWeight(1f);
		p.fill(EnumColor.RED.getValue(), 60);
		p.ellipse(cluster.getLocationXY().x, 
				cluster.getLocationXY().y, 
				2*radius, 2*radius);
		p.popStyle();
	}
	
	public boolean containsPoint(float x, float y) {
		float centerx = cluster.getLocationXY().x;
		float centery = cluster.getLocationXY().y;
		
		if((Math.pow(Math.abs((SettingsLoader.scaleFactor*centerx) - (SettingsLoader.scaleFactor*x)), 2) + 
				Math.pow(Math.abs((SettingsLoader.scaleFactor*centery) - (SettingsLoader.scaleFactor*x)), 2)) 
				<= Math.pow(radius, 2))
			return true;
		
		return false;
	}
	
	public boolean touch(int ID, float x, float y, MouseMovements event) {
		return true;
	}

	public Cluster getCluster() {
		return cluster;
	}
	
}
