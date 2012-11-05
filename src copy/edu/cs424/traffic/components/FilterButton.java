package edu.cs424.traffic.components;

import edu.cs424.traffic.components.MainPanel.MouseMovements;
import edu.cs424.traffic.gui.Button;

public class FilterButton extends Button
{
	FilterPanel filter;

	public FilterButton(float x0, float y0, float width, float height,
			float parentX0, float parentY0, String text,FilterPanel filter,boolean isVisible) {
		super(x0, y0, width, height, parentX0, parentY0, text,isVisible);
		this.filter = filter;
	}

	
	
	@Override
	public boolean touch(float x, float y, MouseMovements event)
	{
		if(event == MouseMovements.MOUSEDOWN)
		{
			
				isPressed = true;
				filter.handleFilterButtonClicks(text);
			
			
		}
		
		return false;
	}

}
