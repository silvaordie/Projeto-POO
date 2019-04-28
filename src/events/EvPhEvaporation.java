package events;

import graphs.*;

public class EvPhEvaporation extends Event{
	
	Graph graph;
	
	EvPhEvaporation(float _time, Graph _graph)
	{
		super(_time);
		
		this.graph = _graph;
	}

	public void simulate()
	{
		
	}
	
}
