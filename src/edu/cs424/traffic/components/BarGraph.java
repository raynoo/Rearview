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
import edu.cs424.traffic.gui.Button;
import edu.cs424.traffic.pubsub.PubSub;
import edu.cs424.traffic.pubsub.PubSub.Event;
import edu.cs424.traffic.pubsub.Suscribe;
import static edu.cs424.data.helper.AppConstants.*;

public class BarGraph extends Panel implements TouchEnabled,Suscribe
{
	public enum Type{
		Month("Months"),Day("Days"),Year("Years");
		private String value;
		private Type(String value) {
			this.value = value;
		}
		public String getValue(){
			return value;
		}
	}

	float highest = 100;
	public HashMap<String, Set<String>> selectedButtonList;
	Event suscribed;
	Type currentType = Type.Year;
	Button backButton;
	ArrayList<Rectangle> currentRectList;

	public BarGraph(float x0, float y0, float width, float height,
			float parentX0, float parentY0,Event toSuscribe) 
	{		
		super(x0, y0, width, height, parentX0, parentY0);
		PubSub.suscribeEvent(toSuscribe, this);
		suscribed = toSuscribe;		
	}

	public void setup() 
	{
		backButton = new Button(0, height-15, 15, 10, x0, y0, "<", true);	
		currentRectList = new ArrayList<Rectangle>();
	}

	@Override
	public void draw()
	{
		if(needRedraw)
		{			
			currentRectList.clear();
			pushStyle();			
			background(EnumColor.WHITE);
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
				Rectangle rect = new Rectangle(x0 + graphAxisX + (i*28),y0 + graphAxisY + graphAxisHeight - y , 28-5, y,"2050");
				currentRectList.add(rect);

				textSize(8);				
				fill(EnumColor.BLACK);
				text("2050", graphAxisX + (i*28) + 14 , graphAxisY + graphAxisHeight +10);

				needRedraw = false;
			}

			if(currentType != Type.Year)
			{
				backButton.draw();
			}			
			popStyle();
		}		
	}	


	private class Rectangle
	{
		float x1,y1,width,height;
		String value;	

		public Rectangle(float x,float y ,float width,float height,String value)
		{
			x1 = Panel.s(x);
			y1 = Panel.s(y);
			this.width = Panel.s(width);
			this.height = Panel.s(height);
			this.value = value;
		}

		public boolean containsPoint(float x, float y) {
			return x > x1 && x < x1 + width && y > y1 && y < y1 + height;
		}
	}

	@Override
	public boolean touch(float x, float y, MouseMovements event)
	{
		if(event == MouseMovements.MOUSEDOWN)
		{
			if(backButton.containsPoint(x, y))
			{
				if(currentType == Type.Month)
				{
					currentType = Type.Year;
					setReDraw();
				}
				else if(currentType == Type.Day)
				{
					currentType = Type.Month;
					setReDraw();
				}
			}
			else
			{
				for(Rectangle temp : currentRectList)
				{
					if(temp.containsPoint(x, y))
					{
						System.out.println("BarGraph.touch()" + " ****");
					}
				}
			}
		}
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
		// if its a new event we start from year
		if(eventName == suscribed)
		{
			forceRedrawAllComponents();
			currentType = Type.Year;
			selectedButtonList = (HashMap<String, Set<String>>) object[0];
			System.out.println("BarGraph.receiveNotification()" + selectedButtonList);
		}		
	}

	public void forceRedrawAllComponents()
	{
		backButton.setReDraw();
		setReDraw();
	}
}
