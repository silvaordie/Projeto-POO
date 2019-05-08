package simulation;

import ants.AntInterface;
import events.EvAntMove;
import events.EvPrint;
import events.Event;
import graphs.Link;
import pec.*;

class Simulation implements SimulationInterface{
	
	private AntInterface [] ants;
	private PEC pec;
	
	private float finalinst;

	Simulation(AntInterface[] _ants, float _finalinst)
	{
		this.ants = _ants;
		this.finalinst = _finalinst;
	}
	
	public void run(String args[])
	{		
		this.pec = new PEC(finalinst);

		Event[] events = new Event[ants.length];
		for(int k=0; k< ants.length ; k++)
		{
			Link move = ants[k].getMove();
			events[k]=new EvAntMove( EvAntMove.expRandom((move.getWeight())*Float.parseFloat(args[0])) , ants[k], move);
		}
		this.pec.addEvPEC(events);
		
		events = new Event[Integer.parseInt(args[1])+1];
		float t=0;
		for(int k=0; k<events.length; k++)
			events[k] = new EvPrint( t+=finalinst/events.length , k+1) ;
		
		this.pec.addEvPEC(events);
		
		Event currentEvent = this.pec.nextEvPEC();
		float currenTime = 0;
		while(currenTime < finalinst)
		{
			currenTime=currentEvent.getTime();
					
			this.pec.addEvPEC( currentEvent.simulate() );				
			
			currentEvent = this.pec.nextEvPEC();
			
		}
	}
	
}
