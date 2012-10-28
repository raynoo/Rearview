package edu.cs424.traffic.components;

import edu.cs424.data.helper.AppConstants;
import edu.cs424.traffic.central.EnumColor;
import edu.cs424.traffic.central.Panel;
import edu.cs424.traffic.central.TouchEnabled;
import edu.cs424.traffic.map.Map;
import static edu.cs424.data.helper.AppConstants.*;

public class MainPanel extends Panel implements TouchEnabled
{	
	public Map map;	
	public BarGraph graph1,graph2;
	
	boolean isTab1 = true,isTab2;
	boolean isFirstTime = true;
	
	Tab1 tab1;

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


//		map = new Map(40, 60, 620, 280, x0, y0);
//		addTouchSubscriber(map);
		
		tab1 = new Tab1(controlPanelX, controlPanelY, controlPanelWidth, controlPanelHeight, x0, y0);
		addTouchSubscriber(tab1);
				
		graph1  = new BarGraph(graph1X, graph1Y, graph1Width, graph1Height, x0, y0);
		graph2  = new BarGraph(graph2X, graph2Y, graph2Width, graph2Height, x0, y0);		
	}


	@Override
	public void draw() 
	{
		if(isFirstTime)
		{
			background(EnumColor.GOLD);
			fill(EnumColor.BLACK);
			rect(tabPanelX, tabPanelY, tabPanelWidth, tabPanelHeight);
			isFirstTime = false;
		}
		
		if(isTab1)
		{
			tab1.draw();
		}
		else if(isTab2)
		{
			
		}
		
		graph1.draw();
		graph2.draw();

	}

}
