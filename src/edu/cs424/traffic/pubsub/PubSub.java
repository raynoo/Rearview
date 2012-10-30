package edu.cs424.traffic.pubsub;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class PubSub 
{
	public enum Event{
		ATTRIBUTE_SELECT_DESELECT_GRAPH1,
		ATTRIBUTE_SELECT_DESELECT_GRAPH2;
	}
	
	private static HashMap<Event , ArrayList<Suscribe> > listners = new HashMap<Event, ArrayList<Suscribe>>();

	public static void suscribeEvent(Event eventName , Suscribe suscriber)
	{
		if(listners.containsKey(eventName))
		{
			listners.get(eventName).add(suscriber);
		}
		else
		{
			ArrayList<Suscribe> toStore = new ArrayList<Suscribe>();
			toStore.add(suscriber);

			listners.put(eventName, toStore);
		}
	}

	public static void publishEvent(Event eventName,Object... object)
	{
		if(listners.containsKey(eventName))
		{
			ArrayList<Suscribe> list = listners.get(eventName);
			for(Suscribe toPublish : list)
			{
				toPublish.receiveNotification(eventName,object);
			}

		}
		else
		{
			System.out.println("event not found");
		}
	}

}
