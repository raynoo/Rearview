package edu.cs424.traffic.components;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

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

	ArrayList<Point> touchList;

	@Override
	public boolean touch(float x, float y, MouseMovements event) {

		if(this.containsPoint(x, y))
		{
			if(MouseMovements.MOUSEDOWN == event)
			{
				
				map.zoomIn();
//				//				lastTouchPos.x = x;
//				//				lastTouchPos.y = y;				
//
//				if(touchList.size() == 0)
//				{
//					Point p = new Point(x, y);
//					touchList.add(p);
//					//					initTouchPos.x = x;
//					//				    initTouchPos.y = y;
//				}
//				else
//				{
//					touchList.clear();
//				}

			}
			else if(MouseMovements.MOUSEUP == event)
			{
				touchList.clear();
			}
			else // mouse move
			{
				if(touchList.size() == 1)
				{
					map.tx += (x - touchList.get(0).x)/map.sc;
					map.ty += (y - touchList.get(0).y)/map.sc;
				}
			}
		}

		return false;
	}

	@Override
	public void setup() 
	{
		touchList = new ArrayList();
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

}
