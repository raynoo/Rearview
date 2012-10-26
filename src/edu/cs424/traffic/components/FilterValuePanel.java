package edu.cs424.traffic.components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import edu.cs424.data.helper.ButtonData;
import edu.cs424.traffic.central.EnumColor;
import edu.cs424.traffic.central.Panel;
import edu.cs424.traffic.central.TouchEnabled;
import edu.cs424.traffic.components.MainPanel.MouseMovements;

public class FilterValuePanel extends Panel implements TouchEnabled
{
	
	HashMap<String, ArrayList<FilterValueButton> > buttons;
	FilterPanel filterPanel;

	public FilterValuePanel(float x0, float y0, float width, float height,
			float parentX0, float parentY0,FilterPanel filterPanel) {
		super(x0, y0, width, height, parentX0, parentY0);
		this.filterPanel = filterPanel;
	}

	@Override
	public boolean touch(float x, float y, MouseMovements event) 
	{
		propagateTouch(x, y, event);
		return false;
	}

	@Override
	public void setup() 
	{
		buttons = new HashMap<String, ArrayList<FilterValueButton>>();
		HashMap<String,Set<String>> ButtonValues = ButtonData.buttonValues;

		for(String key : ButtonValues.keySet())
		{
			Object[] values = ButtonValues.get(key).toArray();
			ArrayList<FilterValueButton> ValueButtons = new ArrayList<FilterValueButton>();

			int limit = values.length>6?6:values.length;

			for(int i = 0 ; i < limit ; i++)
			{
				FilterValueButton temp = new FilterValueButton(5, 3 + i*15, 82, 12, x0, y0, (String)values[i], false,this);
				addTouchSubscriber(temp);
				ValueButtons.add(temp);
			}

			limit = values.length>6?values.length-6:0;
			for(int i = 0 ; i < limit ; i++)
			{
				FilterValueButton temp = new FilterValueButton(93, 3 + i*15, 82, 12, x0, y0, (String)values[6+i], false,this);
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
			System.out.println("FilterValuePanel.draw()");
			background(EnumColor.GRAY);			
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
		
		populatePanel();
		
	}
	
	public void  handleFilterValueButtonClick(boolean isPressed,String text)
	{
		populatePanel();
		filterPanel.handleFilterValueButtonClick(text, isPressed);
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

		ArrayList<FilterValueButton> list = buttons.get(filterPanel.currentFilter);
		if(list != null)
		{
			for(FilterValueButton temp : list)
			{
				temp.setVisibilty(true);
			}
		}

		setReDraw();
	}

	public void clearPanel()
	{
		for(String buttonKey : buttons.keySet())
		{
			ArrayList<FilterValueButton> list = buttons.get(buttonKey);
			for(FilterValueButton temp : list)
			{
				temp.setVisibilty(false);
			}
		}
		setReDraw();
	}

}
