package edu.cs424.traffic.gui;

import processing.core.PApplet;
import edu.cs424.traffic.central.EnumColor;
import edu.cs424.traffic.central.Panel;
import edu.cs424.traffic.central.TouchEnabled;
import edu.cs424.traffic.components.MainPanel.MouseMovements;

public abstract class Button extends Panel implements TouchEnabled
{
	
	public boolean isPressed = false;
	private boolean isVisible = false;
	
	int roundCorners = 5;
	float alpha = 150;
	public String text;

	public Button(float x0, float y0, float width, float height,
			float parentX0, float parentY0,String text,boolean isVisible) 
	{
		super(x0, y0, width, height, parentX0, parentY0);
		this.text = text;
		this.isVisible = isVisible;
	}

	@Override
	public boolean touch(float x, float y, MouseMovements event) {
		
		if(event == MouseMovements.MOUSEDOWN)
		{
			isPressed = !isPressed;
			setReDraw();
		}		
		
		return false;
	}
	
	

	@Override
	public void setup() 
	{
		
		
	}
	
	@Override
	public void draw() 
	{
		if(needRedraw && isVisible)
		{
			
			pushStyle();		
			if(isPressed)
			{
				fill(EnumColor.DARK_GRAY, alpha);
				stroke(EnumColor.BLACK);
				rect(0, 0, width, height, roundCorners, roundCorners, roundCorners, roundCorners);	
				fill(EnumColor.WHITE);
			}
			else
			{
				fill(EnumColor.GRAY, alpha);
				stroke(EnumColor.BLACK);
				rect(0, 0, width, height, roundCorners, roundCorners, roundCorners, roundCorners);	
				fill(EnumColor.BLACK);
			}
		
			
			textSize(8);
			textAlign(PApplet.CENTER, PApplet.CENTER);
			text(text, width/2f, height/2f);
			
			needRedraw = false;
			popStyle();
		}		
	}

	
	@Override
	public boolean containsPoint(float x, float y) {
		if(isVisible)
			return super.containsPoint(x, y);
		else
			return false;
	}
	
	public void setVisibilty(boolean isVisible)
	{
		this.isVisible = isVisible;
		setReDraw();
	}
	
	public void setPressed(boolean isPressed)
	{
		this.isPressed = isPressed;
		setReDraw();
	}
	
	

}
