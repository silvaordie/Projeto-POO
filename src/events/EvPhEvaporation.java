package events;

import graphs.*;

public class EvPhEvaporation extends Event{
	
	Link link;
	float ro;
	
	EvPhEvaporation(float _time, Link _link, float _ro)
	{
		super(_time);
		
		this.ro = _ro;
		this.link = _link;
	}

	public void simulate()
	{
		link.evaporatePh(this.ro);
	}
}
