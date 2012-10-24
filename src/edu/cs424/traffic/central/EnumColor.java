package edu.cs424.traffic.central;

public enum EnumColor 
{

	SOMERANDOM (133,133,1);
	
	
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
