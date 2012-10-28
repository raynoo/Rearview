package edu.cs424.traffic.components;

import edu.cs424.data.helper.AppConstants;
import edu.cs424.traffic.central.EnumColor;
import edu.cs424.traffic.central.Panel;
import edu.cs424.traffic.central.TouchEnabled;
import edu.cs424.traffic.gui.Label;
import edu.cs424.traffic.map.Map;
import static edu.cs424.data.helper.AppConstants.*;

public class MainPanel extends Panel implements TouchEnabled
{	
	public Map map;	
	public BarGraph graph1,graph2;
	Label label1,label2;

	boolean isTab1 = true,isTab2;
	boolean isFirstTime = true;

	Tab1 tab1;
	Tab2 tab2;

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
		{
			if(label1.containsPoint(x, y) && !label1.isPressed)
			{
				label1.setPressed(true);
				label2.setPressed(false);
				tab1.forceRedrawAllComponents();
				isTab1 = true;
				isTab2 = false;
				
			}
			else if(label2.containsPoint(x, y) && !label2.isPressed)
			{
				label2.setPressed(true);
				label1.setPressed(false);
				tab2.forceRedrawAllComponents();
				isTab1 = false;
				isTab2 = true;
			}
			else
			{
				if(isTab1)
					tab1.propagateTouch(x, y, event);
				else if(isTab2)
					tab2.propagateTouch(x, y, event);
			}
		}

		return false;
	}

	@Override
	public void setup() 
	{
		//		map = new Map(40, 60, 620, 280, x0, y0);
		//		addTouchSubscriber(map);

		tab1 = new Tab1(controlPanelX, controlPanelY, controlPanelWidth, controlPanelHeight, x0, y0);
		addTouchSubscriber(tab1);

		tab2 = new Tab2(controlPanelX, controlPanelY, controlPanelWidth, controlPanelHeight, x0, y0);
		addTouchSubscriber(tab2);

		graph1  = new BarGraph(graph1X, graph1Y, graph1Width, graph1Height, x0, y0);
		graph2  = new BarGraph(graph2X, graph2Y, graph2Width, graph2Height, x0, y0);	

		label1 = new Label(660, 225, 100, tabPanelHeight, x0, y0, "Graph");
		label2 = new Label(775, 225, 100, tabPanelHeight, x0, y0, "events");
	}


	@Override
	public void draw() 
	{
		if(isFirstTime)
		{
			background(EnumColor.GOLD);
			isFirstTime = false;
		}	

		if(isTab1)
		{
			tab1.draw();
		}
		else if(isTab2)
		{
			tab2.draw();
		}

		graph1.draw();
		graph2.draw();
		label1.draw();
		label2.draw();
	}

}
