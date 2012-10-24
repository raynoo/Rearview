package edu.cs424.traffic.components;

import edu.cs424.traffic.central.Panel;
import edu.cs424.traffic.central.TouchEnabled;

public class MainPanel extends Panel implements TouchEnabled
{
	
	Filter filter;

	public MainPanel(float x0, float y0, float width, float height,
			float parentX0, float parentY0) 
	{
		super(x0, y0, width, height, parentX0, parentY0);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean touch(float x, float y, boolean down)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setup() 
	{
		filter = new Filter(10, 10, 100, 100, x0, y0);
		
	}
	
	
	@Override
	public boolean draw() {
		filter.draw();
		
		return false;
	}

}
