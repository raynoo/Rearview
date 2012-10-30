package edu.cs424.traffic.components;

import processing.core.PConstants;
import edu.cs424.traffic.central.EnumColor;
import edu.cs424.traffic.central.Panel;
import edu.cs424.traffic.central.TouchEnabled;
import edu.cs424.traffic.components.MainPanel.MouseMovements;
import edu.cs424.traffic.gui.Button;
import edu.cs424.traffic.pubsub.PubSub.Event;

import static edu.cs424.data.helper.AppConstants.*;

public class Tab1 extends Panel implements TouchEnabled
{
	public FilterHolder filterHolder1,filterHolder2;
	Button graph1Button,graph2Button;
	
	BarGraph bar1,bar2;

	boolean isHolder1 = true;
	boolean isFirstTime = true;
	
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
			else
			{
				if(isHolder1)
					filterHolder1.propagateTouch(x, y, event);
				else
					filterHolder2.propagateTouch(x, y, event);
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
		
		filterHolder1 = new FilterHolder(filterHolderX, filterHolderY, filterHolderWidth, filterHolderHeight, x0, y0,this,Event.ATTRIBUTE_SELECT_DESELECT_GRAPH1);
		filterHolder1.setup();
		filterHolder2 = new FilterHolder(filterHolderX, filterHolderY, filterHolderWidth, filterHolderHeight, x0, y0,this,Event.ATTRIBUTE_SELECT_DESELECT_GRAPH2);
		filterHolder2.setup();
	}

	/*
	 * tabs draw all the time , but because of the framework
	 * sub components draw only what is needed
	 * */
	@Override
	public void draw() 
	{
		if(isFirstTime)
		{
			pushStyle();			
			textSize(10);
			textAlign(PConstants.LEFT, PConstants.TOP);
			fill(EnumColor.BLACK);
			text("Select Filter", filterHolderX, 5);
			text("Filter Values",filterHolderX+filterValuesX , 5);
			text("Selected Values",filterHolderX+selectedValuesX , 5);						
			popStyle();
			
			graph1Button.draw();
			graph2Button.draw();
			isFirstTime = false;
		}	

		if(isHolder1)
			filterHolder1.draw();
		else
			filterHolder2.draw();
	}

	public void forceRedrawAllComponents()
	{
		background(EnumColor.GOLD);
		
		graph1Button.setReDraw();
		graph2Button.setReDraw();
		
		graph1Button.draw();
		graph2Button.draw();
		
		pushStyle();
		
		textSize(10);
		textAlign(PConstants.LEFT, PConstants.TOP);
		text("Select Filter", filterHolderX, 5);
		text("Filter Values",filterHolderX+filterValuesX , 5);
		text("Selected Values",filterHolderX+selectedValuesX , 5);
					
		popStyle();
		
		if(isHolder1)
			filterHolder1.forceRedraw();
		else
			filterHolder2.forceRedraw();


	}

}
