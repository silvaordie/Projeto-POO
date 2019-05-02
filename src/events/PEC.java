package events;

import java.util.*;

/**
 * Responsible for stochastic event handling
 * @author José
 *
 */
public class PEC {
	
	LinkedList<Event> events = new LinkedList<Event>();
	
	/**
	 * Adds a new event do the to event queue, where the first event in queue is the one with a lower time value
	 * @param _event Event to add to the queue
	 */
	public void addEvPEC(Event _event)
	{
		if(_event.geTime()<310)
		{
			if(this.events.size() == 0)
				this.events.add(_event);
			else
			{
				Event event;
				for(int k=0; k<this.events.size(); k++)
				{
					event = events.get(k);
					if(_event.time < event.time)
					{
						if(k==0)
							this.events.addFirst(_event);
						else
							this.events.add(k,_event);
						
						return;
					}
				}
				this.events.addLast(_event);
			}
		}		
	}
	
	/**
	 * Fetches the next event to simulate by removing it from the queue
	 * @return Next event to simulate
	 */
	public Event nextEvPEC()
	{
		return this.events.removeFirst();		
	}
}
