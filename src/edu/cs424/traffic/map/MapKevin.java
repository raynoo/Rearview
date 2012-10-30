package edu.cs424.traffic.map;

import java.awt.Color;
import java.util.HashMap;
import java.util.Hashtable;

import omicronAPI.OmicronAPI;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PVector;

import com.modestmaps.InteractiveMap;
import com.modestmaps.core.Point2f;
import com.modestmaps.geo.Location;
import com.modestmaps.providers.Microsoft;

import edu.cs424.traffic.central.EnumColor;
import edu.cs424.traffic.central.Main;
import edu.cs424.traffic.central.Panel;
import edu.cs424.traffic.central.TouchListener;
import edu.cs424.traffic.components.MainPanel.MouseMovements;
import edu.cs424.traffic.map.Map.Button;
import edu.cs424.traffic.map.Map.MapTypeButton;
import edu.cs424.traffic.map.Map.ZoomButton;
import edu.cs424.traffic.map.dataset.MapLocation;
import edu.cs424.traffic.map.dataset.StateLatLon;

public class MapKevin 
{
	
	
	public MapKevin() 
	{
		setup();
	}
	static InteractiveMap map;



	PVector mapSize;
	PVector mapOffset;



	public void setup() {

		int scaleFactor = 1;		
		mapSize = new PVector(100, 100);
		mapOffset = new PVector(30, 60);	
		String template = "http://{S}.mqcdn.com/tiles/1.0.0/osm/{Z}/{X}/{Y}.png";
		String[] subdomains = new String[] { "otile1", "otile2", "otile3", "otile4" };		
		map = new InteractiveMap(Main.getPapplet(), new Microsoft.AerialProvider(), mapOffset.x, mapOffset.y, mapSize.x, mapSize.y);
	}



	public void draw() {
		
		map.draw();		
		
	}
	
	public void zoom()
	{
		map.zoomIn();
	}
}

