package simulation;

import ants.AntInterface;
import events.EvAntMove;
import events.EvPhEvaporation;
import events.Event;
import graphs.Link;
import pec.*;

public class Simulation implements SimulationInterface{
	
	private AntInterface [] ants;
	private PEC pec;
	
	private float finalinst;

	Simulation(AntInterface[] _ants, float _finalinst)
	{
		this.ants = _ants;
		this.finalinst = _finalinst;
	}
	
	public void run(float delta)
	{
		float lasTime = 0;
		
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
			
			if( (currenTime - lasTime) > finalinst/20)
				lasTime = printSim(currenTime);
		}
	}
	
	public float printSim(float time)
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
		System.out.println("\nPresent instant: " + time );
		System.out.println("Number of move events: " + EvAntMove.getCount() );
		System.out.println("Number of evaporation events: " + EvPhEvaporation.getCount() );
		if(min_k!=-1)
			System.out.println("Hamiltonian cycle: " + ants[min_k].toString() );
		
		return time;
	}
	
}
