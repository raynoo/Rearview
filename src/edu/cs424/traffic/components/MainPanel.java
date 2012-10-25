package edu.cs424.traffic.components;

import edu.cs424.traffic.central.Panel;
import edu.cs424.traffic.central.TouchEnabled;
import edu.cs424.traffic.map.Map;

public class MainPanel extends Panel implements TouchEnabled
{
	
	Filter filter;
	Map map;
	
	public enum MouseMovements{
		MOUSEUP,
		MOUSEDOWN,
		MOUSEMOVE
	}

	public MainPanel(float x0, float y0, float width, float height,
			float parentX0, float parentY0) 
	{
		super(x0, y0, width, height, parentX0, parentY0);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean touch(float x, float y, MouseMovements event)
	{
		propagateTouch(x, y, event);
		return false;
	}

	@Override
	public void setup() 
	{
		filter = new Filter(740, 245, 255, 95, x0, y0);
		addTouchSubscriber(filter);
		
		map = new Map(40, 60, 620, 280, x0, y0);
		addTouchSubscriber(map);
		
	}
	
	
	@Override
	public void draw() {
		filter.draw();
		map.draw();
		
	}

}
