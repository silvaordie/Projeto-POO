package ants;

import java.util.LinkedList;

import graphs.Link;
import graphs.Node;

/**
 * Defines an Ant's behavior, which is, it's movement 
 * @author José
 *
 */
public interface AntInterface {
	/**
	 * Return the ant's next node
	 * @return Ant's next node
	 */
	public abstract Link getMove();
	/**
	 * Moves the ant to it's next node and checks some condition
	 * @param link Ant's next node
	 * @return Condition
	 */
	public abstract boolean moveAnt(Link link);
	
	/**
	 * Returns a path found
	 * @return Path
	 */
	public abstract LinkedList<Link> getCycle();
	
	/**
	 * Returns the Ant's initial node
	 * @return
	 */
	public abstract Node getFirst();
	
	/**
	 * Atributes a value to the Ant's movement 
	 * @return
	 */
	public abstract float getWeight();

}
