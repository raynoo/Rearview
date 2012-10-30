package edu.cs424.traffic.components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

import processing.core.PApplet;
import processing.core.PVector;

import com.modestmaps.InteractiveMap;
import com.modestmaps.providers.Microsoft;

import edu.cs424.traffic.central.EnumColor;
import edu.cs424.traffic.central.Panel;
import edu.cs424.traffic.central.SettingsLoader;
import edu.cs424.traffic.map.utils.Point;
import edu.cs424.traffic.pubsub.PubSub.Event;
import edu.cs424.traffic.pubsub.Suscribe;
import edu.cs424.traffic.central.TouchEnabled;
import edu.cs424.traffic.components.MainPanel.MouseMovements;
import static edu.cs424.data.helper.AppConstants.*;

public class MapPanel extends Panel implements TouchEnabled,Suscribe
{
	static InteractiveMap map;
	PVector mapSize;
	PVector mapOffset;

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

	PVector lastTouchPos;
	PVector lastTouchPos2;
	PVector initTouchPos;
	PVector initTouchPos2;

	HashMap<Integer,Point> touchList;
	int touchID1,touchID2;

	public boolean touch(int ID,float x, float y, MouseMovements event) {

		if(this.containsPoint(x, y))
		{
			if(MouseMovements.MOUSEDOWN == event)
			{			
				lastTouchPos.x = x;
				lastTouchPos.y = y;				
				Point p = new Point(ID,x, y);
				touchList.put(ID,p);

				if(touchList.size() == 1)
				{
					touchID1 = ID;
					initTouchPos.x = x;
					initTouchPos.y = y;
				}
				else if(touchList.size() == 2 )
				{
					touchID2 = ID;
					initTouchPos2.x = x;
					initTouchPos2.y = y;
				}

			}
			else if(MouseMovements.MOUSEUP == event)
			{
				touchList.remove(ID);
			}
			else 
			{
				if(touchList.size() < 2)
				{
					map.tx += (x - lastTouchPos.x)/map.sc;
					map.ty += (y - lastTouchPos.y)/map.sc;					

				}
				else if(touchList.size() == 2)
				{
					float sc = PApplet.dist(lastTouchPos.x, lastTouchPos.y, lastTouchPos2.x, lastTouchPos2.y);
					float initPos = PApplet.dist(initTouchPos.x,initTouchPos.y,initTouchPos2.x,initTouchPos2.y);
					PVector midpoint = new PVector( (lastTouchPos.x+lastTouchPos2.x)/2, (lastTouchPos.y+lastTouchPos2.y)/2 );
					sc -= initPos;
					sc /= 5000;
					sc += 1;

					float mx = (midpoint.x - mapOffset.x) - mapSize.x/2;
					float my = (midpoint.y - mapOffset.y) - mapSize.y/2;
					map.tx -= mx/map.sc;
					map.ty -= my/map.sc;
					map.sc *= sc;
					map.tx += mx/map.sc;
					map.ty += my/map.sc;					
				}
				else if( touchList.size() >= 5 )
				{				    
					// Zoom to entire USA				   
				}				 
				  // Update touch IDs 1 and 2
				  if( ID == touchID1 ){
				    lastTouchPos.x = x;
				    lastTouchPos.y = y;
				  } else if( ID == touchID2 ){
				    lastTouchPos2.x = x;
				    lastTouchPos2.y = y;
				  } 
				  
				  // Update touch list
				  Point t = new Point( ID, x,y );
				  touchList.put(ID,t);
			}
		}

		return false;
	}

	@Override
	public void setup() 
	{
		touchList = new HashMap<Integer, Point>();
		lastTouchPos = new PVector();
		lastTouchPos2 = new PVector();
		initTouchPos = new PVector();
		initTouchPos2 = new PVector();

		mapSize = createPvector(mapPanelWidth - 2*mapOffsetX, mapPanelHeight - 2*mapOfffsetY);
		mapOffset = createPvector(mapOffsetX, mapPanelY);
		map = new InteractiveMap(SettingsLoader.papp, new Microsoft.RoadProvider(), mapOffset.x, mapOffset.y, mapSize.x, mapSize.y );

	}

	@Override
	public void draw() 
	{
		if(needRedraw)
		{
			System.out.println("MapPanel.draw()" + "map re drawn");
			background(EnumColor.SOMERANDOM);
			map.draw();
			needRedraw = false;
		}
	}

	public void forceRedrawAllComponents()
	{
		needRedraw = true;

	}

	@Override
	public boolean touch(float x, float y, MouseMovements event) {
		System.out.println("MapPanel.touch()" + "NOT IMPLEMENTED");
		return false;
	}

}
