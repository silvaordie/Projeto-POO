package events;

import ants.*;

public class EvPrint implements Event,Comparable<Event>{
	
	
	private float time=0;
	private int obs=0;
	
	public EvPrint(float _t, int _ob)
	{
		this.time = _t;
		this.obs = _ob;
	}
	
	public Event[] simulate() 
	{
		AntInterface ant = new Ant();
		System.out.println("\n\\noindent Observation " + this.obs + ":\\\\");
		System.out.println("		\\indent Present instant: " + this.time +"\\\\");
		System.out.println("		\\indent Number of move events: " + EvAntMove.getCount() +"\\\\");
		System.out.println("		\\indent Number of evaporation events: " + EvPhEvaporation.getCount() +"\\\\");
		if(ant.getWeight()<999999999)
			System.out.println("		\\indent Hamiltonian cycle: " + ant.toString() +"\\\\");
		
		return null;	
	}
	
	public float getTime()
	{
		return this.time;
	}
	
	@Override
	public int compareTo(Event event)
	{
		if(this.time<event.getTime())
			return 1 ;
		else return 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(time);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		return false;
	}
	
	

}