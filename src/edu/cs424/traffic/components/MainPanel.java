package edu.cs424.traffic.components;

import edu.cs424.data.helper.AppConstants;
import edu.cs424.traffic.central.EnumColor;
import edu.cs424.traffic.central.Panel;
import edu.cs424.traffic.central.TouchEnabled;
import edu.cs424.traffic.map.Map;

public class MainPanel extends Panel implements TouchEnabled
{

	public FilterPanel filter;
	public Map map;
	public FilterValuePanel filterValues;
	boolean isFirstTime = true;

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
		if(event == MouseMovements.MOUSEDOWN)
			propagateTouch(x, y, event);
		return false;
	}

	@Override
	public void setup() 
	{
		filter = new FilterPanel(740, 245, 255, 95, x0, y0,this);
		addTouchSubscriber(filter);
		
		filterValues = new FilterValuePanel(AppConstants.filterValuesX, AppConstants.filterValuesY, AppConstants.filterValuesWidth,
				AppConstants.filterValuesHeight, x0, y0,filter);
		addTouchSubscriber(filterValues);

		map = new Map(40, 60, 620, 280, x0, y0);
		addTouchSubscriber(map);

	}


	@Override
	public void draw() 
	{
		if(isFirstTime)
		{
			background(EnumColor.GOLD);
			isFirstTime = false;
		}
		
		filter.draw();
		map.draw();
		filterValues.draw();


	}

}
