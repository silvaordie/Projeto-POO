package ants;

import java.util.LinkedList;

import graphs.Link;
import graphs.Node;

public interface AntInterface{
	public abstract Link getMove();
	public abstract boolean moveAnt(Link link);
	public abstract LinkedList<Link> getCycle();
	public abstract Node getFirst();
	public abstract float getWeight();
}
