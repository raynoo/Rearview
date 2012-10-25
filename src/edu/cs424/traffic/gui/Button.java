package edu.cs424.traffic.gui;

import processing.core.PApplet;
import edu.cs424.traffic.central.EnumColor;
import edu.cs424.traffic.central.Panel;
import edu.cs424.traffic.central.TouchEnabled;
import edu.cs424.traffic.components.MainPanel.MouseMovements;

public abstract class Button extends Panel implements TouchEnabled
{
	public boolean isSelected = false;
	EnumColor pBackground,pText,uBackground,uText; // p = pressed u = unpressed
	
	int roundCorners = 5;
	float alpha = 150;
	public String text;

	public Button(float x0, float y0, float width, float height,
			float parentX0, float parentY0,String text) 
	{
		super(x0, y0, width, height, parentX0, parentY0);
		this.text = text;
	}

	@Override
	public boolean touch(float x, float y, MouseMovements event) {
		
		if(event == MouseMovements.MOUSEDOWN)
		{
			isSelected = !isSelected;
			setReDraw();
		}	
		
		handleClick();
		return false;
	}
	
	public abstract void handleClick();

	@Override
	public void setup() 
	{
		
		
	}
	
	@Override
	public void draw() 
	{
		if(needRedraw)
		{
			pushStyle();			
			fill(EnumColor.DARK_GRAY, alpha);
			stroke(EnumColor.BLACK);
			rect(0, 0, width, height, roundCorners, roundCorners, roundCorners, roundCorners);				
			if(isSelected)
			{
				fill(EnumColor.DARK_GRAY, alpha);
				fill(EnumColor.WHITE);
			}
			else
			{
				fill(EnumColor.BLACK);
			}
			
			textSize(8);
			textAlign(PApplet.CENTER, PApplet.CENTER);
			text(text, width/2f, height/2f);		
			popStyle();
		}
		
		
	}

}
