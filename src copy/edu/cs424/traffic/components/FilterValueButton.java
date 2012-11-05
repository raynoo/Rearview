package edu.cs424.traffic.components;

import edu.cs424.traffic.components.MainPanel.MouseMovements;
import edu.cs424.traffic.gui.Button;

public class FilterValueButton  extends Button
{
	FilterValuePanel filterValuePanel;

	public FilterValueButton(float x0, float y0, float width, float height,
			float parentX0, float parentY0, String text,boolean isVisible,FilterValuePanel filterValuePanel) {
		super(x0, y0, width, height, parentX0, parentY0, text,isVisible);
		this.filterValuePanel = filterValuePanel;
	}

	@Override
	public boolean touch(float x, float y, MouseMovements event) 
	{
		if(event == MouseMovements.MOUSEDOWN)
		{
			if(isPressed)
			{
				isPressed = false;
			}
			else
			{
				isPressed = true;
			}
			
			setReDraw();
			filterValuePanel.handleFilterValueButtonClick(isPressed,text);
		}
		
		return false;
	}

}
