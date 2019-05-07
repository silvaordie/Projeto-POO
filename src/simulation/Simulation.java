package simulation;

import ants.AntInterface;
import events.EvAntMove;
import events.EvPhEvaporation;
import events.Event;
import graphs.Link;
import pec.*;

/**
 * Implements the simulation of the proposed problem
 * @author José
 *
 */
public class Simulation implements SimulationInterface{
	
	/**
	 * Ant colony
	 */
	private AntInterface [] ants;
	/**
	 * Pending event container
	 */
	private PEC pec;
	/**
	 * Simulation's final instant
	 */
	private float finalinst;

	/**
	 * Default constructor, receives the ant colony and the simulation's final instant
	 * @param _ants Ant colony
	 * @param _finalinst 
	 */
	Simulation(AntInterface[] _ants, float _finalinst)
	{
		this.ants = _ants;
		this.finalinst = _finalinst;
	}
	
	/**
	 * Runs the simulation
	 * @param delta Ant's mean time to traverse a link
	 */
	public void run(float delta)
	{
		float lasTime = 0;
		int obs = 0;
		
		this.pec = new PEC(finalinst);
		Event[] events = new Event[ants.length];
		float currenTime = 0;
		
		for(int k=0; k< ants.length ; k++)
		{
			Link move = ants[k].getMove();
			events[k]=new EvAntMove( EvAntMove.expRandom((move.getWeight())*delta) , ants[k], move);
		}
		this.pec.addEvPEC(events);
		
		Event currentEvent = this.pec.nextEvPEC();
		
		while(currenTime < finalinst)
		{
			currenTime=currentEvent.getTime();
					
			this.pec.addEvPEC( currentEvent.simulate() );				
			
			currentEvent = this.pec.nextEvPEC();
			
			if( (currenTime - lasTime) > finalinst/21)
				lasTime = printSim(currenTime, ++obs);
		}
	}
	
	/**
	 * Prints the current simulation status and returns the current time
	 * @param time Current time
	 * @param obs Observations made
	 * @return Current time elapsed
	 */
	public float printSim(float time, int obs)
	{
		int min_k=-1;
		float min_w=9999;
		
		for(int k=0; k<ants.length ; k++)
		{
			if(ants[k].getWeight() < min_w)
			{
				min_k=k;
				min_w=ants[k].getWeight();
			}
		}
		System.out.println("\n\\noindent Observation " + obs + ":\\\\");
		System.out.println("		\\indent Present instant: " + time +"\\\\");
		System.out.println("		\\indent Number of move events: " + EvAntMove.getCount() +"\\\\");
		System.out.println("		\\indent Number of evaporation events: " + EvPhEvaporation.getCount() +"\\\\");
		if(min_k!=-1)
			System.out.println("		\\indent Hamiltonian cycle: " + ants[min_k].toString() +"\\\\");
		
		return time;
	}
	
}
