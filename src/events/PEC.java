package events;

import java.util.*;

public class PEC {

	LinkedList<Event> events = new LinkedList<Event>();
	
	
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
	
	public Event nextEvPEC()
	{
		return this.events.removeFirst();		
	}
}
