package events;

import graphs.*;

/**
 * Event that concerns the Pheromone Evaporation in a single Link
 * @author José
 *
 */
public class EvPhEvaporation extends SimulationEvent{
	
	Link link;
	Node no;
	static int eevents = 0;
	/**
	 * Default constructor, receives the Event's end time, the Link that will evaporate pheromones, the starting Node of the given link and the pheromone evaporation value
	 * @param _time Event's end time
	 * @param _link Link that will evaporate it's pheromones
	 * @param _no Link's starting Node
	 * @param _ro Pheromone level decrease value
	 */
	public EvPhEvaporation(float _time, Link _link, Node _no)
	{
		super(_time);
		
		this.link = _link;
		this.no = _no;
	}
	
	public void setMean(float _m)
	{
		eta = _m;		
	}

	
	public static int getCount()
	{
		return eevents; 		
	}

	/**
	 * Simulates the Pheromone Evaporation Event
	 */
	public SimulationEvent[] simulate()
	{
		eevents++;
		SimulationEvent[] events = new SimulationEvent[1];
		this.link.evaporatePh(rho, this.no);
		
		if(this.link.getPh()>0) 
		{
			events[0] = new EvPhEvaporation( expRandom(eta), this.link, this.no);
			return events;		
		}
		else
			return null;
	}

	/**
	 * Returns the Event Link's starting Node
	 * @return
	 */
	public Node getNode()
	{
		return this.no;
	}
	
	/**
	 * Returns the Event's Link
	 * @return
	 */
	public Link getLink()
	{
		return this.link;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((link == null) ? 0 : link.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EvPhEvaporation other = (EvPhEvaporation) obj;
		if (link == null) 
		{
			if (other.link != null)
				return false;
			else
				return true;
		} 
		else 
		{
			EvPhEvaporation evento = other;
			if(this.link.getNode().equals(evento.link.getNode()) && this.no==evento.no)
				return true;
			else
			{
				if(this.no.equals(evento.link.getNode()) && this.link.getNode().equals(evento.no) )
					return true;
				else
					return false;
			}
		}
	}
	
	
}
