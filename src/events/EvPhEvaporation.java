package events;

import graphs.*;

public class EvPhEvaporation extends Event{
	
	Link link;
	Node no;
	float ro;
	
	public EvPhEvaporation(float _time, Link _link, Node _no, float _ro)
	{
		super(_time);
		
		this.ro = _ro;
		this.link = _link;
		this.no = _no;
	}

	public void simulate()
	{
		this.link.evaporatePh(this.ro, this.no);
	}
	
	public Node getNode()
	{
		return this.no;
	}
	
	public Link getLink()
	{
		return this.link;
	}
}
