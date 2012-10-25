package edu.cs424.traffic.components;

import edu.cs424.traffic.gui.Button;

public class FilterButton extends Button
{
	Filter filter;

	public FilterButton(float x0, float y0, float width, float height,
			float parentX0, float parentY0, String text,Filter filter) {
		super(x0, y0, width, height, parentX0, parentY0, text);
		this.filter = filter;
	}

	@Override
	public void handleClick()
	{
		filter.handleButtonClicks(text, isSelected);
		
	}

}
