package edu.cs424.traffic.components;

import static edu.cs424.data.helper.AppConstants.filterHolderHeight;
import static edu.cs424.data.helper.AppConstants.filterHolderWidth;
import static edu.cs424.data.helper.AppConstants.filterHolderX;
import static edu.cs424.data.helper.AppConstants.filterHolderY;
import static edu.cs424.data.helper.AppConstants.filterValuesX;
import static edu.cs424.data.helper.AppConstants.graph1ButtonX;
import static edu.cs424.data.helper.AppConstants.graph1ButtonY;
import static edu.cs424.data.helper.AppConstants.graph2ButtonX;
import static edu.cs424.data.helper.AppConstants.graph2ButtonY;
import static edu.cs424.data.helper.AppConstants.graphButtonHeight;
import static edu.cs424.data.helper.AppConstants.graphButtonWidth;
import static edu.cs424.data.helper.AppConstants.selectedValuesX;
import static edu.cs424.data.helper.AppConstants.updateButtonHeight;
import static edu.cs424.data.helper.AppConstants.updateButtonWidth;
import static edu.cs424.data.helper.AppConstants.updateButtonX;
import static edu.cs424.data.helper.AppConstants.updateButtonY;
import processing.core.PConstants;
import edu.cs424.traffic.central.EnumColor;
import edu.cs424.traffic.central.Panel;
import edu.cs424.traffic.central.TouchEnabled;
import edu.cs424.traffic.components.MainPanel.MouseMovements;
import edu.cs424.traffic.gui.Button;
import edu.cs424.traffic.pubsub.PubSub.Event;

public class Tab1 extends Panel implements TouchEnabled
{
	public FilterHolder filterHolder1,filterHolder2;
	Button graph1Button,graph2Button,updateButton;

	BarGraph bar1,bar2;

	boolean isHolder1 = true;

	public Tab1(float x0, float y0, float width, float height, float parentX0,
			float parentY0)
	{
		super(x0, y0, width, height, parentX0, parentY0);

		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean touch(float x, float y, MouseMovements event) 
	{
		if(event == MouseMovements.MOUSEDOWN)
		{
			if(graph1Button.containsPoint(x, y) && !graph1Button.isPressed)
			{
				graph1Button.setPressed(true);
				graph2Button.setPressed(false);
				isHolder1 = true;
				forceRedrawAllComponents();				
			}
			else if(graph2Button.containsPoint(x, y) && !graph2Button.isPressed)
			{
				graph2Button.setPressed(true);
				graph1Button.setPressed(false);
				isHolder1 = false;
				forceRedrawAllComponents();
			}
			
			else if(updateButton.containsPoint(x, y))
			{
				if(isHolder1)
				{
					filterHolder1.updateFilter(Event.CHANGE_FILTER_GRAPH1);
				}
				else
				{
					filterHolder2.updateFilter(Event.CHANGE_FILTER_GRAPH2);
				}
			}
			
			else
			{
				if(isHolder1)
					filterHolder1.touch(x, y, event);
				else
					filterHolder2.touch(x, y, event);
			}
		}
		return false;
	}


	public void setup() {

		graph1Button = new Button(graph1ButtonX,graph1ButtonY,graphButtonWidth,graphButtonHeight,x0,y0,"graph 1",true);
		graph1Button.setup();
		graph2Button = new Button(graph2ButtonX,graph2ButtonY,graphButtonWidth,graphButtonHeight,x0,y0,"graph 2",true);
		graph2Button.setup();
		graph1Button.setPressed(true);
		
		updateButton = new Button(updateButtonX, updateButtonY, updateButtonWidth, updateButtonHeight, x0, y0, "Apply", true);
		updateButton.setup();

		filterHolder1 = new FilterHolder(filterHolderX, filterHolderY, filterHolderWidth, filterHolderHeight, x0, y0,this,Event.CHANGE_FILTER_GRAPH1);
		filterHolder1.setup();
		filterHolder2 = new FilterHolder(filterHolderX, filterHolderY, filterHolderWidth, filterHolderHeight, x0, y0,this,Event.CHANGE_FILTER_GRAPH2);
		filterHolder2.setup();
	}

	/*
	 * tabs draw all the time , but because of the framework
	 * sub components draw only what is needed
	 * */
	@Override
	public void draw() 
	{
		if(needRedraw)
		{
			pushStyle();			
			textSize(10);
			textAlign(PConstants.LEFT, PConstants.TOP);
			fill(EnumColor.WHITE);
			text("Select Filter", filterHolderX, 5);
			text("Filter Values",filterHolderX+filterValuesX , 5);
			text("Selected Values",filterHolderX+selectedValuesX , 5);						
			popStyle();

			graph1Button.draw();
			graph2Button.draw();
			updateButton.draw();

			if(isHolder1)
				filterHolder1.draw();
			else
				filterHolder2.draw();

			needRedraw = false;
		}	
	}

	public void forceRedrawAllComponents()
	{
		graph1Button.setReDraw();
		graph2Button.setReDraw();
		updateButton.setReDraw();

		if(isHolder1)
			filterHolder1.forceRedraw();
		else
			filterHolder2.forceRedraw();

		needRedraw = true;


	}

}
