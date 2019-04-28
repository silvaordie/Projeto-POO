package events;

import java.util.*;

public class PEC {

	LinkedList<Event> events = new LinkedList<Event>();
	
	public void addEvPEC(Event _event)
	{
		this.events.add(_event);
	}
	
	public Event nextEvPEC()
	{
		return this.events.removeFirst();		
	}
}
