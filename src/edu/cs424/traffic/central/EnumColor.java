package edu.cs424.traffic.central;

public enum EnumColor 
{

	SOMERANDOM (133,133,1),
	DARK_GRAY (105,105,105),
	GRAY(190,190,190),
	WHITE(255,255,255),
	BLACK(0,0,0),
	GOLD(255,215,0);
	
	
	int color;
	private EnumColor(int r,int g , int b) 
	{
		color = Main.main.color(r, g, b);
	}
	
	public int getValue()
	{
		return color;
	}
}
