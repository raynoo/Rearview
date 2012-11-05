package edu.cs424.traffic.components;

import java.util.ArrayList;
import java.util.HashMap;

import edu.cs424.data.helper.ButtonData;
import edu.cs424.traffic.central.EnumColor;
import edu.cs424.traffic.central.Panel;
import edu.cs424.traffic.central.TouchEnabled;
import edu.cs424.traffic.components.MainPanel.MouseMovements;

public class FilterValuePanel extends Panel implements TouchEnabled
{

	public HashMap<String, ArrayList<FilterValueButton> > buttons;
	FilterHolder filterHolder;

	public FilterValuePanel(float x0, float y0, float width, float height,
			float parentX0, float parentY0,FilterHolder filterHolder) {
		super(x0, y0, width, height, parentX0, parentY0);
		this.filterHolder = filterHolder;
	}

	@Override
	public boolean touch(float x, float y, MouseMovements event) 
	{
		propagateTouch(x, y, event);
		return false;
	}

	public void setup() 
	{
		buttons = new HashMap<String, ArrayList<FilterValueButton>>();
		HashMap<String,ArrayList<String>> ButtonValues = ButtonData.buttonValues;

		for(String key : ButtonValues.keySet())
		{
			Object[] values = ButtonValues.get(key).toArray();
			ArrayList<FilterValueButton> ValueButtons = new ArrayList<FilterValueButton>();

			int limit = values.length>6?6:values.length;

			for(int i = 0 ; i < limit ; i++)
			{
				FilterValueButton temp = new FilterValueButton(5, 3 + i*15, 82, 12, x0, y0, (String)values[i], false,this);
				temp.setup();
				addTouchSubscriber(temp);
				ValueButtons.add(temp);
			}

			limit = values.length>6?values.length-6:0;
			for(int i = 0 ; i < limit ; i++)
			{
				FilterValueButton temp = new FilterValueButton(93, 3 + i*15, 82, 12, x0, y0, (String)values[6+i], false,this);
				temp.setup();
				addTouchSubscriber(temp);
				ValueButtons.add(temp);
			}		

			buttons.put(key, ValueButtons);
		}
	}

	@Override
	public void draw()
	{
		if(needRedraw)
		{			
			populatePanel();
			background(EnumColor.BLACK);			
			
			for(String buttonKey : buttons.keySet())
			{
				ArrayList<FilterValueButton> list = buttons.get(buttonKey);
				for(FilterValueButton temp : list)
				{
					temp.draw();
				}
			}
			needRedraw = false;
		}
	}

	public void addFilterValues()
	{		
		setReDraw();
	}


	public void  handleFilterValueButtonClick(boolean isPressed,String text)
	{
		filterHolder.filter.handleFilterValueButtonClick(text, isPressed);
		filterHolder.showSelected.setReDraw();
		setReDraw();
	}

	private void populatePanel()
	{
		for(String buttonKey : buttons.keySet())
		{
			ArrayList<FilterValueButton> list = buttons.get(buttonKey);
			for(FilterValueButton temp : list)
			{
				temp.setVisibilty(false);
			}
		}

		if(filterHolder.filter.currentFilter != null)
		{
			ArrayList<FilterValueButton> list = buttons.get(filterHolder.filter.currentFilter);
			if(list != null)
			{
				for(FilterValueButton temp : list)
				{
					temp.setVisibilty(true);
				}
			}
		}
	}
	
	public void selectDeselectbutton(String parent,String value , boolean isPressed)
	{
		ArrayList<FilterValueButton> subList = buttons.get(parent);

		for(FilterValueButton temp : subList)
		{
			if(temp.text.equals(value))
			{
				temp.isPressed = isPressed;
			}
		}

		setReDraw();
	}
	
	public void forceRedrawAllComponents()
	{
		for(String key : buttons.keySet())
		{
			ArrayList<FilterValueButton> temp = buttons.get(key);
			for( FilterValueButton tempButton : temp)
			{
				tempButton.setReDraw();
			}
		}		
		setReDraw();
	}

}
