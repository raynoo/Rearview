package edu.cs424.traffic.components;

import edu.cs424.traffic.central.EnumColor;
import edu.cs424.traffic.central.Panel;
import edu.cs424.traffic.pubsub.PubSub.Event;
import edu.cs424.traffic.pubsub.Suscribe;
import edu.cs424.traffic.central.TouchEnabled;
import edu.cs424.traffic.components.MainPanel.MouseMovements;

public class MapPanel extends Panel implements TouchEnabled,Suscribe
{

	public MapPanel(float x0, float y0, float width, float height,
			float parentX0, float parentY0) 
	{
		super(x0, y0, width, height, parentX0, parentY0);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void receiveNotification(Event eventName, Object... object) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean touch(float x, float y, MouseMovements event) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setup() {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw() 
	{
		if(needRedraw)
		{
			System.out.println("MapPanel.draw()" + "map re drawn");
			background(EnumColor.SOMERANDOM);
			needRedraw = false;
		}
	}

	public void forceRedrawAllComponents()
	{
		needRedraw = true;
		
	}

}
