package events;

import graphs.*;

/**
 * Event that concerns the Pheromone Evaporation in a single Link
 * @author José
 *
 */
public class EvPhEvaporation extends Event{
	
	Link link;
	Node no;
	float ro;
	
	/**
	 * Default constructor, receives the Event's end time, the Link that will evaporate pheromones, the starting Node of the given link and the pheromone evaporation value
	 * @param _time Event's end time
	 * @param _link Link that will evaporate it's pheromones
	 * @param _no Link's starting Node
	 * @param _ro Pheromone level decrease value
	 */
	public EvPhEvaporation(float _time, Link _link, Node _no, float _ro)
	{
		super(_time);
		
		this.ro = _ro;
		this.link = _link;
		this.no = _no;
	}
	
	/**
	 * Simulates the Pheromone Evaporation Event
	 */
	public void simulate()
	{
		this.link.evaporatePh(this.ro, this.no);
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
}
