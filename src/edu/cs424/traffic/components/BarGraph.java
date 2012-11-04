package edu.cs424.traffic.components;

import static edu.cs424.data.helper.AppConstants.graphAxisHeight;
import static edu.cs424.data.helper.AppConstants.graphAxisWidth;
import static edu.cs424.data.helper.AppConstants.graphAxisX;
import static edu.cs424.data.helper.AppConstants.graphAxisY;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import processing.core.PApplet;
import processing.core.PConstants;
import edu.cs424.data.helper.DBCommand;
import edu.cs424.traffic.central.EnumColor;
import edu.cs424.traffic.central.Panel;
import edu.cs424.traffic.central.TouchEnabled;
import edu.cs424.traffic.components.MainPanel.MouseMovements;
import edu.cs424.traffic.gui.Button;
import edu.cs424.traffic.map.dataset.DataPoint;
import edu.cs424.traffic.pubsub.PubSub;
import edu.cs424.traffic.pubsub.PubSub.Event;
import edu.cs424.traffic.pubsub.Suscribe;

public class BarGraph extends Panel implements TouchEnabled,Suscribe
{
	public enum Type{
		Month("Months",3609),Day("Days",748),Year("Years",39252);
		private String value;
		int highest;
		private Type(String value,int highest) {
			this.value = value;
			this.highest = highest;
		}
		public String getValue(){
			return value;
		}
		public int getHighest(){
			return highest;
		}
	}

	public HashMap<String, Set<String>> selectedButtonList;
	Event suscribed;
	public Type currentType = Type.Year;
	Button backButton;
	ArrayList<Rectangle> currentRectList;
	String yearValue,monthValue;

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

			HashMap<String, ArrayList<DataPoint>> toPlot= DBCommand.getInstance().getGraphData(suscribed);
			int i = 0 ;

			if(toPlot != null)
			{
				for( String key : toPlot.keySet() )
				{
					textAlign(PConstants.CENTER, PConstants.CENTER);
					float y;

					//				fill(EnumColor.DARK_GRAY, 100);
					//				float y = PApplet.map(totalList.get(i) , 0 , highest,0,graphAxisHeight);
					//				rect(graphAxisX + (i*28), graphAxisY + graphAxisHeight - y , 28-5, y);
					//
					//				fill(EnumColor.BLACK);
					//				textSize(8);
					//				text("10.6%", graphAxisX + (i*28) + 14 , graphAxisHeight - y + 10);

					fill(EnumColor.SOMERANDOM);				
					y = PApplet.map(toPlot.get(key).size() , 0 , currentType.getHighest() ,0,graphAxisHeight);
					rect(graphAxisX + (i*28), graphAxisY + graphAxisHeight - y , 28-5, y);		
					Rectangle rect = new Rectangle(x0 + graphAxisX + (i*28),y0 + graphAxisY + graphAxisHeight - y , 28-5, y,key);
					currentRectList.add(rect);

					textSize(8);				
					fill(EnumColor.BLACK);
					text(key, graphAxisX + (i*28) + 14 , graphAxisY + graphAxisHeight +10);

					needRedraw = false;
					i++;
					
					//because i can plot only 10 bars :)
					if(i >= 10)
						break;
				}
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

		public String getValue()
		{
			return value;
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

				}
				else if(currentType == Type.Day)
				{
					currentType = Type.Month;					
				}
			}
			else
			{
				for(Rectangle temp : currentRectList)
				{
					if(temp.containsPoint(x, y))
					{
						if( currentType == Type.Year )
						{
							yearValue = temp.getValue();							
							currentType = Type.Month;
							monthValue = null;
							DBCommand.getInstance().notifyTimeFilterChange(suscribed, currentType, yearValue, monthValue);
							break;
						}
						else if( currentType == Type.Month )
						{
							monthValue = temp.getValue();
							currentType = Type.Day;
							DBCommand.getInstance().notifyTimeFilterChange(suscribed, currentType, yearValue, monthValue);
							break;
						}
						else
						{
							// if it is a day dont do anything
						}
					}
				}
			}
		}
		return false;
	}


	@Override
	public void receiveNotification(Event eventName , Object... object) 
	{
		// if its a new event we start from year
		if(eventName == suscribed)
		{
			currentType = Type.Year;
			yearValue = null;
			monthValue = null;

			System.out.println("BarGraph.receiveNotification()" + selectedButtonList);
		}		
	}

	public void forceRedrawAllComponents()
	{
		backButton.setReDraw();
		setReDraw();
	}
}
