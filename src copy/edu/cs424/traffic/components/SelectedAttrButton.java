package edu.cs424.traffic.components;

import edu.cs424.traffic.components.MainPanel.MouseMovements;
import edu.cs424.traffic.gui.Button;

public class SelectedAttrButton extends Button
{
	ShowSelectedAttribute panel;

	public SelectedAttrButton(float x0, float y0, float width, float height,
			float parentX0, float parentY0, String text,ShowSelectedAttribute panel) {
		super(x0, y0, width, height, parentX0, parentY0, text, true);
		this.panel = panel;
	}
	
	@Override
	public boolean touch(float x, float y, MouseMovements event)
	{		
		panel.removeAttribute(text);
		return false;
	}

}
