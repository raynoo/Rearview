package edu.cs424.traffic.pubsub;

import edu.cs424.traffic.pubsub.PubSub.Event;

public interface Suscribe {

	public void receiveNotification(Event eventName , Object... object);
}
