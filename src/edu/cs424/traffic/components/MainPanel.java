package edu.cs424.traffic.components;

import processing.core.PApplet;
import edu.cs424.data.helper.AppConstants;
import edu.cs424.traffic.central.EnumColor;
import edu.cs424.traffic.central.Panel;
import edu.cs424.traffic.central.TouchEnabled;
import edu.cs424.traffic.gui.Label;
import edu.cs424.traffic.pubsub.PubSub.Event;
import static edu.cs424.data.helper.AppConstants.*;

public class MainPanel extends Panel implements TouchEnabled
{	
	public BarGraph graph1,graph2;
	Label label1,label2,label3;

	boolean isTab1 = true,isTab2 = false;
	Tab1 tab1;
	Tab2 tab2;
	MapPanel mapPanel;

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

	public boolean touch(int ID,float x, float y, MouseMovements event)
	{
		// handle the stuff that should happen on touch
		if(event == MouseMovements.MOUSEDOWN)
		{
			if(label1.containsPoint(x, y) && !label1.isPressed)
			{
				label1.setPressed(true);
				label2.setPressed(false);
				label3.setPressed(false);
				isTab1 = true;
				isTab2 = false;
			}
			else if(label2.containsPoint(x, y) && !label2.isPressed)
			{
				label2.setPressed(true);
				label1.setPressed(false);
				label3.setPressed(false);
				isTab1 = false;
				isTab2 = true;
			}			
		}		

		if(mapPanel.containsPoint(x, y))
		{
			mapPanel.touch(ID,x, y, event);
		}

		// after touch has been handled redraw 
		mapPanel.forceRedrawAllComponents();
		if(isTab1)
		{
			tab1.touch(x, y, event);
			tab1.forceRedrawAllComponents();
		}
		else if(isTab2)
		{
			tab2.touch(x, y, event);
			tab2.forceRedrawAllComponents();
		}

		label1.forceRedrawAllComponents();
		label2.forceRedrawAllComponents();
		label3.forceRedrawAllComponents();
		graph1.forceRedrawAllComponents();
		graph2.forceRedrawAllComponents();
		needRedraw = true;
		return false;
	}

	@Override
	public boolean touch(float x, float y, MouseMovements event) {
		System.out.println("MainPanel.touch() " + "NOT IMPLEMENTED");
		return false;
	}

	public void setup() 
	{	
		tab1 = new Tab1(controlPanelX, controlPanelY, controlPanelWidth, controlPanelHeight, x0, y0);
		tab1.setup();
		addTouchSubscriber(tab1);

		tab2 = new Tab2(controlPanelX, controlPanelY, controlPanelWidth, controlPanelHeight, x0, y0);
		tab2.setup();
		addTouchSubscriber(tab2);

		label1 = new Label(660, 225, 100, tabPanelHeight, x0, y0, "Graph",true);
		label1.setup();
		label2 = new Label(775, 225, 100, tabPanelHeight, x0, y0, "Events",false);	
		label2.setup();
		label3 = new Label(890, 225, 100, tabPanelHeight, x0, y0, "Search",false);	
		label3.setup();

		graph1  = new BarGraph(graph1X, graph1Y, graph1Width, graph1Height, x0, y0,Event.ATTRIBUTE_SELECT_DESELECT_GRAPH1);
		graph1.setup();

		graph2  = new BarGraph(graph2X, graph2Y, graph2Width, graph2Height, x0, y0,Event.ATTRIBUTE_SELECT_DESELECT_GRAPH2);	
		graph2.setup();

		mapPanel = new MapPanel(mapPanelX, mapPanelY, mapPanelWidth, mapPanelHeight, x0, y0);
		mapPanel.setup();
	}


	@Override
	public void draw() 
	{
		if(needRedraw)
		{
			mapPanel.draw();	
			pushStyle();
			// drawing the background for the graph/UI region
			fill(EnumColor.BLACK);
			rect(mapPanelWidth, 0,fullScreenWidth-mapPanelWidth , fullScreenHeight);
			popStyle();

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
			label3.draw();
			needRedraw = false;
		}
	}

}
