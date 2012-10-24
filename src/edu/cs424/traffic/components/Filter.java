package edu.cs424.traffic.components;

import edu.cs424.traffic.central.EnumColor;
import edu.cs424.traffic.central.Panel;
import edu.cs424.traffic.central.TouchEnabled;

public class Filter extends Panel implements TouchEnabled
{

	public Filter(float x0, float y0, float width, float height,
			float parentX0, float parentY0)
	{
		super(x0, y0, width, height, parentX0, parentY0);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setup() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean touch(float x, float y, boolean down) 
	{
		// TODO Auto-generated method stub
		return false;
	}
	
	
	@Override
	public boolean draw() {
		background(EnumColor.SOMERANDOM);
		line(0, 0, width, height);
		return false;
	}


	

}
