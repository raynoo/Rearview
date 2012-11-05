package edu.cs424.traffic.map.dataset;

import processing.core.PApplet;
import edu.cs424.traffic.central.EnumColor;
import edu.cs424.traffic.central.SettingsLoader;
import edu.cs424.traffic.components.MapPanel;

public class Marker {
	
	float radius;
	EnumColor color;
	Cluster cluster;

	PApplet p;
	
	Marker() {
		
	}
	
	public Marker(Cluster cluster, EnumColor color, int lowestCount, int highestCount, float celly) {
		this.cluster = cluster;
		this.color = color;
		calculateRadius(lowestCount, highestCount, celly);
		p = SettingsLoader.papp;
	}
	
	public Marker(DataPoint data, EnumColor color, int lowestCount, int highestCount, float celly) {
		this.cluster = new Cluster(data);
		this.color = color;
		calculateRadius(lowestCount, highestCount, celly);
		p = SettingsLoader.papp;
	}
	
	void calculateRadius(int lowestCount, int highestCount, float celly) {
		
		if(cluster.getCrashCount() == 1)
			this.radius = SettingsLoader.scaleFactor * 5;
		else
			this.radius = p.map((float)this.cluster.getCrashCount(), 
					(float)lowestCount, (float)highestCount, 0f, (float)(0.45 * celly));
		//scale everything!
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
	
	float scale(float i) {
		return SettingsLoader.scaleFactor * i;
	}
}
