package edu.cs424.traffic.map.dataset;


public class Marker {
	
//	int radius;
//	int color;
//	
//	Cluster cluster;
//	PShape shape;
//
//	public Marker(Cluster cluster, PShape shape) {
//		this(0, 0, 0, 0, 0, 0);
//
//		this.cluster = cluster;
//		
//		if(shape != null)
//			this.shape = shape;
//		
//		calculateRadius();
//	}
//	
//	public Marker(float x0, float y0, float width, float height,
//			float parentX0, float parentY0) {
//		
//		super(x0, y0, width, height, parentX0, parentY0);
//	}
//
//	@Override
//	public void setup() {
//		
//	}
//	
//	void calculateRadius() {
//		float factor = 0.01f;
//		
//		if(MapPanel.map.getZoom() <= 4)
//			factor = 0.001f;
//		if(MapPanel.map.getZoom() >= 5)
//			factor = 0.002f;
//		if(MapPanel.map.getZoom() >= 7)
//			factor = 0.03f;
//		if(MapPanel.map.getZoom() >= 9)
//			factor = 0.05f;
//		
//		this.radius = (int) ((float)cluster.getCrashCount() * factor);
//	}
//	
//	public void draw() {
//			pushStyle();
//			stroke(EnumColor.DARK_GRAY);
//			strokeWeight(1.5f);
//			fill(EnumColor.RED, 60);
//			ellipse(MapPanel.map.locationPoint(cluster.getLocation()).x, 
//					MapPanel.map.locationPoint(cluster.getLocation()).y, 
//					2*radius, 2*radius);
//			popStyle();
//	}
//	
//	public boolean containsPoint(float x, float y) {
//		float centerx = MapPanel.map.locationPoint(cluster.getLocation()).x;
//		float centery = MapPanel.map.locationPoint(cluster.getLocation()).y;
//		
//		if((Math.pow(Math.abs(centerx - x),2) + Math.pow(Math.abs(centery - y), 2)) <= Math.pow(radius, 2))
//			return true;
//		
//		return false;
//	}
//	
//	public boolean touch(int ID, float x, float y, MouseMovements event) {
//
//		if(this.containsPoint(x, y)) {
//			
//		}
//		return true;
//	}
//
//	public Cluster getCluster() {
//		return cluster;
//	}
	
}
