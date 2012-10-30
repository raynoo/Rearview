package edu.cs424.traffic.gui;

import processing.core.PApplet;
import edu.cs424.traffic.central.EnumColor;
import edu.cs424.traffic.central.Panel;
import edu.cs424.traffic.central.TouchEnabled;
import edu.cs424.traffic.components.MainPanel.MouseMovements;

public class Label extends Button
{

	public Label(float x0, float y0, float width, float height, float parentX0,
			float parentY0, String text,boolean isPressed) {
		
		super(x0, y0, width, height, parentX0, parentY0, text, true);
		this.isPressed = isPressed;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void draw()
	{
		if(needRedraw)
		{	
			background(EnumColor.BLACK,EnumColor.WHITE,5);
			pushStyle();		
			if(isPressed)
			{	
				fill(EnumColor.WHITE);
			}
			else
			{					
				fill(EnumColor.GRAY);
			}	
			
			textSize(12);
			textAlign(PApplet.CENTER, PApplet.CENTER);
			text(text, width/2f, height/2f);			
			needRedraw = false;
			popStyle();
		}
		
	}
	
	public void forceRedrawAllComponents()
	{
		needRedraw = true;
	}
	
	
	

}
