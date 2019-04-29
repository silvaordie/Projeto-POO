package events;

import java.util.*;

public class PEC {

	LinkedList<Event> events = new LinkedList<Event>();
	
	public void addEvPEC(Event _event)
	{
		if(this.events.size() == 0)
			this.events.add(_event);
		else
		{
			Event event;
			for(int k=0; k<this.events.size(); k++)
			{
				event = events.get(k);
				if(event.time < _event.time)
				{
					this.events.add(k-1,_event);
					return;
				}
			}
		}
	}
	
	public Event nextEvPEC()
	{
		return this.events.removeFirst();		
	}
}
