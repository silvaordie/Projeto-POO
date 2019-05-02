package events;

import ants.*;
import graphs.Link;

/**
 * Event that concerns a single Ant Move
 * @author José
 *
 */
public class EvAntMove extends Event{
	
	private Ant ant;
	private boolean cycle = false;
	private Link next_node;
	/**
	 * Default constructor, receives the Event's end time, the Ant which is desired to move the link connecting to the Node the Ant should move to
	 * @param _time Event's end time
	 * @param _ant Event's Ant
	 * @param _next_node Link connecting to the Ant's next Node
	 */
	public EvAntMove(float _time, Ant _ant, Link _next_node)
	{
		super(_time);
		this.ant = _ant;
		this.next_node = _next_node;
	}
	/**
	 * Simulates the Ant Movement
	 */
	public void simulate()
	{
		cycle = this.ant.moveAnt(next_node);
	}
	
	/**
	 * Checks if by moving, the Ant found an Hamiltonian cycle
	 * @return True if the Ant has found a Hamiltonian cycle by moving
	 */
	public boolean foundCycle()
	{
		return cycle;
	}
	
	/**
	 * Returns the Event's Ant
	 * @return Event's Ant
	 */
	public Ant getAnt()
	{
		return this.ant;
	}
}
