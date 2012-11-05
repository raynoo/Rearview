package edu.cs424.traffic.components;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import edu.cs424.data.helper.ButtonData;
import edu.cs424.data.helper.DBCommand;
import edu.cs424.data.helper.Findings;
import edu.cs424.traffic.central.Panel;
import edu.cs424.traffic.central.TouchEnabled;
import edu.cs424.traffic.components.MainPanel.MouseMovements;
import edu.cs424.traffic.gui.Button;
import edu.cs424.traffic.pubsub.PubSub;
import edu.cs424.traffic.pubsub.PubSub.Event;
import edu.cs424.traffic.pubsub.Suscribe;

public class FilterPanel extends Panel implements TouchEnabled,Suscribe
{	
	FilterHolder filterHolder;

	HashMap<String,FilterButton> filterButtons;
	public HashMap<String, Set<String>> selectedButtonList;
	String currentFilter;
	

	public FilterPanel(float x0, float y0, float width, float height,
			float parentX0, float parentY0,FilterHolder filterHolder)
	{
		super(x0, y0, width, height, parentX0, parentY0);		
		this.filterHolder = filterHolder;	
	}


	public void setup()
	{	
		PubSub.suscribeEvent(Event.LOAD_FILTER, this);
		filterButtons = new HashMap<String, FilterButton>();
		selectedButtonList = new HashMap<String, Set<String>>();

		int count = 0;
		Object[] buttonName = ButtonData.buttonValues.keySet().toArray();

		for ( ;count< buttonName.length ; count++)
		{
			FilterButton button = new FilterButton(count/4*65, (count%4)*25, 60, 20, x0, y0, (String)buttonName[count], this,true);
			button.setup();
			filterButtons.put((String)buttonName[count], button);

			Set<String> subButtonList = new HashSet<String>();
			selectedButtonList.put((String)buttonName[count], subButtonList);
			addTouchSubscriber(button);

		}

	}

	@Override
	public boolean touch(float x, float y, MouseMovements event) 
	{
		propagateTouch(x, y, event);
		return false;
	}

	@Override
	public void draw() 
	{
		if(needRedraw)
		{
			for(Button button : filterButtons.values())
			{
				button.draw();
			}
			needRedraw = false;
		}
	}

	public void handleFilterButtonClicks(String buttontext)
	{
		currentFilter = buttontext;
		filterHolder.filterValues.addFilterValues();

		for(String key : filterButtons.keySet())
		{
			FilterButton temp = filterButtons.get(key);
			if(currentFilter.equals(key))
			{
				temp.setPressed(true);
			}
			else if(!selectedButtonList.get(key).isEmpty())
			{
				temp.setPressed(true);
			}
			else
				temp.setPressed(false);
		}
		setReDraw();
	}


	public void handleFilterValueButtonClick(String buttontext,boolean isPressed)
	{
		if(isPressed)
		{
			selectedButtonList.get(currentFilter).add(buttontext);
		}
		else
		{
			selectedButtonList.get(currentFilter).remove(buttontext);
		}		
	}

	public void selectDeselectbutton(String parent,String subvalue,boolean isPressed)
	{
		if(isPressed)
		{
			selectedButtonList.get(parent).add(subvalue);
		}
		else
		{
			selectedButtonList.get(parent).remove(subvalue);
		}
		
		filterHolder.filterValues.selectDeselectbutton(parent,subvalue,isPressed);
		setReDraw();
	}
	
	public void forceRedrawAllComponents()
	{
		for(Button button : filterButtons.values())
		{
			button.setReDraw();
		}
		setReDraw();		
	}
	
	public void updateFilter(Event event)
	{
		PubSub.publishEvent(event, null);
		DBCommand.getInstance().updateFilter(selectedButtonList, event);
	}


	@Override
	public void receiveNotification(Event eventName, Object... object)
	{
		if(eventName == Event.LOAD_FILTER)
		{
			Findings toShow = (Findings) object[1];
			
			for(String temp : toShow.getFilters())
			{
				selectDeselectbutton( temp.split(" = ")[0], temp.split(" = ")[1], true);
			}
			
			updateFilter((Event)object[0]);
		}
		
		
	}

}
