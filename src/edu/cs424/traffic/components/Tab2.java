package edu.cs424.traffic.components;

import java.util.ArrayList;

import edu.cs424.data.helper.Findings;
import edu.cs424.data.helper.Helper;
import edu.cs424.traffic.central.EnumColor;
import edu.cs424.traffic.central.Panel;
import edu.cs424.traffic.central.TouchEnabled;
import edu.cs424.traffic.components.MainPanel.MouseMovements;
import edu.cs424.traffic.gui.Button;
import edu.cs424.traffic.pubsub.PubSub;
import edu.cs424.traffic.pubsub.PubSub.Event;
import static edu.cs424.data.helper.AppConstants.*;

public class Tab2 extends Panel implements TouchEnabled
{
	Button[] buttons;
	ArrayList<Findings> findings;

	public Tab2(float x0, float y0, float width, float height, float parentX0,
			float parentY0) 
	{
		super(x0, y0, width, height, parentX0, parentY0);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean touch(float x, float y, MouseMovements event)
	{
		if( event == MouseMovements.MOUSEDOWN )
		{
			for(Button button : buttons)
			{
				if( button.containsPoint(x, y) )
				{
					button.setPressed(true);
					PubSub.publishEvent(Event.CLEAR_FILTER, Event.CHANGE_FILTER_GRAPH1);
					PubSub.publishEvent(Event.CLEAR_FILTER, Event.CHANGE_FILTER_GRAPH2);
					
					PubSub.publishEvent(Event.LOAD_FILTER, Event.CHANGE_FILTER_GRAPH1,findings.get(0));
					//PubSub.publishEvent(Event.LOAD_FILTER, Event.CHANGE_FILTER_GRAPH2);
				}
				else
				{
					button.setPressed(false);
				}
			}
		}
		
		return false;
	}

	
	public void setup() 
	{
		findings = Helper.getFindings();
		
		buttons = new Button[5];
		for(int i = 0 ; i < 5 ; i++)
		{
			buttons[i] = new Button(eventButtonX,  eventButtonY + i*(eventButtonHeight + eventButtonSpacing),
					eventButtonWidth, eventButtonHeight, x0, y0, "Finding "+i, true);
		}

	}

	@Override
	public void draw() {
		if(needRedraw)
		{
			background(EnumColor.BLACK);
			
			for(Button button : buttons)
			{
				button.draw();
			}
			
			needRedraw = false;
		}
	}
	
	public void forceRedrawAllComponents()
	{
		for(Button button : buttons)
		{
			button.setReDraw();
		}
		needRedraw = true;
	}

}
