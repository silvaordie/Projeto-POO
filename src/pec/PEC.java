
package pec;
import events.*;

import java.util.*;

/**
 * Responsible for stochastic event handling
 * @author José
 *
 */
public class PEC {
	/**
	 * List of all pending event sordered by time
	 */
	private LinkedList<Event> events = new LinkedList<Event>();
	/**
	 * Simulation's final instant
	 */
	private float finalinst;
	
	/**
	 * Default constructor, receives the simulation duration
	 * @param _finalinst Simulation duration
	 */
	public PEC(float _finalinst)
	{
		this.finalinst = _finalinst;
	}
	
	/**
	 * Adds a new event do the to event queue, where the first event in queue is the one with a lower time value
	 * @param _event Event to add to the queue
	 */
	public void addEvPEC(Event[] _events)
	{
		Event event;
		Event aux;
		for(int v=0; _events!=null && v<_events.length; v++)
		{
			boolean found = false;
			event = _events[v];
			if( (event != null) && (event.getTime() < (this.finalinst+10)) )
			{
				if(this.events.size() == 0)
					this.events.add(event);
				else
				{
					int k;
					for(k=0;k<this.events.size() && !found; k++)
					{
						aux = events.get(k);
						if(event.equals(aux))
							found=true;
					}

					for(k=0; k<this.events.size() && !found; k++)
					{
						aux = events.get(k);

						if(event.getTime() < aux.getTime())
						{
							if(k==0)
								this.events.addFirst(event);
							else
								this.events.add(k,event);
							
							break;
						}
					}
					if(k==this.events.size())
						this.events.addLast(event);
				}
			}			
		}		
	}
	
	/**
	 * Fetches the next event to simulate by removing it from the queue
	 * @return Next event to simulate
	 */
	public Event nextEvPEC()
	{
		try
		{
			return this.events.removeFirst();
		}catch(NoSuchElementException e)
		{
			System.exit(-5);
			return null;
		}
	}
}
