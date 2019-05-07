package graphs;
import java.util.*;


/** 
 *  Class for handling weighted graphs represented by numerical Nodes, each one with a list of linked Nodes.
 * @author José
 *
 */
public class Graph{
	/**
	 * Graphs nodes
	 */
	private Node[] nodes;
	
	/**
	 * Exception thrown when trying to access a non existing node
	 * @author José
	 *
	 */
	public class NoSuchNodeException extends Exception{		
		/**
		 * Default constructor 
		 */
	    public NoSuchNodeException(String x) 
	    {
	    	super(x);
	    }
	}
	/**
	 * Exception thrown when trying to create an already existing link 
	 * @author José
	 *
	 */
	public class ExistingLinkException extends Exception{

		public ExistingLinkException(String x)
		{
			super(x);
		}
		
	}
	
	/**
	 * Default constructor, for graph initialization
	 * @param _nNodes Number of nodes in the graph
	 */
	public Graph(int _nNodes)
	{
		nodes=new Node[_nNodes];
	}

	/**
	 * Connects a pair of nodes present in the graph
	 * @param _1 Numerical identifier of first node
	 * @param _2 Numerical identifier of the second node
	 * @param _w Weight of the connection
	 */
	public void connect(int _1, int _2, float _w ) throws NoSuchNodeException, ExistingLinkException
	{
		if(_1>nodes.length || _1<0 )
			throw new NoSuchNodeException(Integer.toString(_1));
		else if(_2>nodes.length || _2<0)
			throw new NoSuchNodeException(Integer.toString(_2));
		
		Node no1;
		Node no2;
		
		if(nodes[_1-1] == null)
			nodes[_1-1] = new Node(_1);

		no1 = nodes[_1-1];
		
		if(nodes[_2-1]==null)
			nodes[_2-1] = new Node(_2);

		no2 = nodes[_2-1];
		
		LinkedList<Link> adj1 = nodes[_1].getAdj();
		LinkedList<Link> adj2 = nodes[_2].getAdj();
		
		for(int k=0; k<adj1.size(); k++)
			for(int v=0; v<adj2.size(); v++)
			{
				Node one = adj1.get(k).getNode();
				Node two =adj2.get(k).getNode();
				
				if( one.equals(nodes[_2]) || two.equals(nodes[_1]) )
				{
					throw new ExistingLinkException(Integer.toString(_1)+Integer.toString(_2));
				}
			}
		
		nodes[_1-1].newAdj(no2, _w);
		nodes[_2-1].newAdj(no1, _w);			
	}
	
	/**
	 * Returns the node with the specified id (number)
	 * @param _id Node identifier (Node's number)
	 * @return The node
	 */
	public Node getNode(int _id) throws NoSuchNodeException
	{
		if(_id > this.nodes.length )
			throw new NoSuchNodeException(Integer.toString(_id));
		
		return nodes[_id-1];
	}
	
	/**
	 * Returns the number of nodes in the graph
	 * @return Number of nodes in the graph
	 */
	public int getSize()
	{
		return nodes.length;
	}
	
	/** 
	 * Returns the total weight of the graph's connections
	 * @return Sum of all the connections' weight
	 */
	public float getWeight()
	{
		float sum = 0;
		for(int k=0; k<this.nodes.length; k++)
		{
			LinkedList<Link> links = nodes[k].getAdj();
			
			for(int v=0; v<links.size(); v++)
			{
				Link link = links.get(v);
				sum += link.getWeight();
			}
		}
		
		return sum/2;
	}
	
	@Override
	public String toString() {
		String str = new String ("Graph:\n");
		Node temp;
		
		for(int k=0; k< nodes.length; k++)
		{
			temp= nodes[k];
			
			str = str + temp.toString();
		}
		
		return str;
	}
	
	
}
