package edu.cs424.traffic.components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import processing.core.PApplet;
import processing.core.PConstants;

import edu.cs424.traffic.central.EnumColor;
import edu.cs424.traffic.central.Panel;
import edu.cs424.traffic.central.TouchEnabled;
import edu.cs424.traffic.components.MainPanel.MouseMovements;
import edu.cs424.traffic.pubsub.PubSub;
import edu.cs424.traffic.pubsub.PubSub.Event;
import edu.cs424.traffic.pubsub.Suscribe;
import static edu.cs424.data.helper.AppConstants.*;

public class BarGraph extends Panel implements TouchEnabled,Suscribe
{
	float highest = 100;
	public HashMap<String, Set<String>> selectedButtonList;
	Event suscribed;

	public BarGraph(float x0, float y0, float width, float height,
			float parentX0, float parentY0,Event toSuscribe) 
	{		
		super(x0, y0, width, height, parentX0, parentY0);
		PubSub.suscribeEvent(toSuscribe, this);
		suscribed = toSuscribe;
		// TODO Auto-generated constructor stub
	}

	public void setup() 
	{
		
		
	}
	
	@Override
	public void draw()
	{
		
		if(needRedraw)
		{
			background(EnumColor.WHITE);			
			pushStyle();			
			rect(graphAxisX, graphAxisY, graphAxisWidth, graphAxisHeight);
			
			ArrayList<Float> dataList = getDummyData();
			ArrayList<Float> totalList = getTotalValue();
			
			for(int i = 0 ; i < dataList.size() ; i++ )
			{
				textAlign(PConstants.CENTER, PConstants.CENTER);
				
				fill(EnumColor.DARK_GRAY, 100);
				float y = PApplet.map(totalList.get(i) , 0 , highest,0,graphAxisHeight);
				rect(graphAxisX + (i*28), graphAxisY + graphAxisHeight - y , 28-5, y);
				
				fill(EnumColor.BLACK);
				textSize(8);
				text("10.6%", graphAxisX + (i*28) + 14 , graphAxisHeight - y + 10);
				
				fill(EnumColor.SOMERANDOM);				
				y = PApplet.map(dataList.get(i) , 0 , highest,0,graphAxisHeight);
				rect(graphAxisX + (i*28), graphAxisY + graphAxisHeight - y , 28-5, y);				
								
				textSize(8);				
				fill(EnumColor.BLACK);
				text("2050", graphAxisX + (i*28) + 14 , graphAxisY + graphAxisHeight +10);
				
				needRedraw = false;
			}
			
			popStyle();
		}		
	}	

	@Override
	public boolean touch(float x, float y, MouseMovements event) {
		// TODO Auto-generated method stub
		return false;
	}

	
	private ArrayList<Float> getDummyData()
	{
		ArrayList<Float> dummy = new ArrayList<Float>();
		dummy.add(new Float(40));
		dummy.add(new Float(90));
		dummy.add(new Float(20));
		dummy.add(new Float(60));
		dummy.add(new Float(70));
		dummy.add(new Float(40));
		dummy.add(new Float(40));
		dummy.add(new Float(20));
		dummy.add(new Float(80));
		dummy.add(new Float(100));
		
		return dummy;
	}
	
	private ArrayList<Float> getTotalValue()
	{
		ArrayList<Float> dummy = new ArrayList<Float>();
		dummy.add(new Float(50));
		dummy.add(new Float(100));
		dummy.add(new Float(60));
		dummy.add(new Float(60));
		dummy.add(new Float(90));
		dummy.add(new Float(70));
		dummy.add(new Float(90));
		dummy.add(new Float(40));
		dummy.add(new Float(90));
		dummy.add(new Float(100));
		
		return dummy;
	}

	@Override
	public void receiveNotification(Event eventName , Object... object) 
	{
		
		if(eventName == suscribed)
		{
			setReDraw();
			selectedButtonList = (HashMap<String, Set<String>>) object[0];
			System.out.println("BarGraph.receiveNotification()" + selectedButtonList);
		}
		
	}
	
	public void forceRedrawAllComponents()
	{
		setReDraw();
	}
}
