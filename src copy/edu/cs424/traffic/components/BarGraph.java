package edu.cs424.traffic.components;

import static edu.cs424.data.helper.AppConstants.graphAxisHeight;
import static edu.cs424.data.helper.AppConstants.graphAxisWidth;
import static edu.cs424.data.helper.AppConstants.graphAxisX;
import static edu.cs424.data.helper.AppConstants.graphAxisY;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import processing.core.PApplet;
import processing.core.PConstants;
import edu.cs424.data.helper.ButtonData;
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
		//		public int getHighest(){
		//			return highest;
		//		}
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
		backButton = new Button(0, height-15, 15, 15, x0, y0, "<", true);	
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
				ArrayList<String> xLabels;

				if( Type.Year == currentType)
				{
					xLabels = ButtonData.buttonValues.get("Years");
				}
				else if(Type.Month == currentType)
				{
					xLabels = ButtonData.buttonValues.get("Months");
				}
				else
				{
					xLabels = ButtonData.buttonValues.get("Days");
				}

				// get the highest height of bar graph for scaling
				int max = 0;
								
				for( String key : toPlot.keySet() )
				{
					int y = toPlot.get(key).size();
					if( y > max )
					{
						max = y;
					}
				}
				max = max + 50;
				float y;

				float interval = max/12;
				fill(EnumColor.BLACK);
				textAlign(PConstants.LEFT, PConstants.TOP);
				String label;
				DecimalFormat form = new DecimalFormat("0.0");  
				
				for(int x = 1 ; x <= 12 ; x++)
				{
					float val = (float) interval * x;
					y = PApplet.map(val , 0 , max ,0,graphAxisHeight);

					if(val > 1000)
					{

						label = String.valueOf(form.format(val/1000)) + "K";
					}
					else
					{
						label = String.valueOf((int)val);
					}


					text(label, graphAxisX-23, graphAxisY + graphAxisHeight - y);
				}


				for( String key : xLabels )
				{
					textAlign(PConstants.CENTER, PConstants.CENTER);
					y = 0;
					if(toPlot.containsKey(key))
					{
						fill(EnumColor.SOMERANDOM);				
						y = PApplet.map(toPlot.get(key).size() , 0 , max ,0,graphAxisHeight);
						rect(graphAxisX + (i*25), graphAxisY + graphAxisHeight - y , 25-5, y);		
						Rectangle rect = new Rectangle(x0 + graphAxisX + (i*25),y0 + graphAxisY + graphAxisHeight - y , 25-5, y,key);
						currentRectList.add(rect);

						textSize(8);				
						fill(EnumColor.BLACK);
						text(key, graphAxisX + (i*25) + 14 , graphAxisY + graphAxisHeight +10);

						needRedraw = false;
						i++;
					}
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
					DBCommand.getInstance().notifyTimeFilterChange(suscribed, currentType, yearValue, monthValue);

				}
				else if(currentType == Type.Day)
				{
					currentType = Type.Month;
					DBCommand.getInstance().notifyTimeFilterChange(suscribed, currentType, yearValue, monthValue);
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
