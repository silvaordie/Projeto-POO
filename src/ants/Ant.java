package ants;
import java.util.*;

import graphs.*;

/**
 * Represents a single Ant in a bidirectional weighted Graph with pheromone levels in it's Links 
 * @author José
 *
 */
public class Ant implements AntInterface{
	
	LinkedList<Link> cycle =  new LinkedList<Link>();
	LinkedList<Link> shortest_cycle = new LinkedList<Link>(); 
	private float min_cycle = 1999999999;
	private Node start;
	private static int n_nodes;
	private static float graph_weight;
	private static float alpha;
	private static float beta;
	private static float gamma;
	
	/**
	 * Default constructor, sets the Ant's nest node, some simulation parameters, and some graph proprietes
	 * @param _ini Ant's nest Node
	 * @param _n_nodes Number of Node sin the Graph
	 * @param _alpha Parameter concerning the ant move event
	 * @param _beta Parameter concerning the ant move event
	 * @param _gamma Parameter concerning the ant move event
	 * @param _graph_weight Graph's total weight
	 */
	public Ant(Node _ini)
	{
		this.start= _ini;
	}
	
	public static void setParams( int _n_nodes, float _alpha, float _beta, float _gamma, float _graph_weight)
	{
		n_nodes = _n_nodes;
		alpha = _alpha;
		beta= _beta;
		gamma = _gamma;
		graph_weight = _graph_weight;
	}
	
	/**
	 * Based on the possible Nodes the Ant can move to, returns a random index based on the connecting Links weights and pheromone levels
	 * @param val List of Links that connect the current Node to the available Nodes
	 * @return Random index of the input List
	 */
	private int getRandomIdx(LinkedList <Link> val)
	{
		float[] weights = new float[val.size()];
		float count=0;
		Link temp;
		int k=0;
		Random rand = new Random();
		
		for(k=0; k<weights.length; k++)
		{
			temp=val.get(k);
			weights[k]=(alpha + temp.getPh())/(beta + temp.getWeight());
			count=count+weights[k];
		}
		
		for(k=0; k<weights.length; k++)
			weights[k]=weights[k]/count;	
		
		float sel = rand.nextFloat();
		
		weights[0]=weights[0];
		for(k=1; k<weights.length; k++)
			weights[k]=weights[k-1]+weights[k];	
		
		float minpos=1000;
		int minpos_idx=-1;
		
		for(k=0; k<weights.length; k++)
		{
			if(weights[k]-sel >=0 && weights[k]-sel<minpos)
			{
				minpos=weights[k]-sel;
				minpos_idx=k;					
			}
		}
		
		return minpos_idx;
	}
	
	/**
	 * Checks if the current Hamiltonian cycle found is shorter than the one previously found, if so saves the current cycle and weight. Also laysdown pheromones on the cycle's nodes
	 */
	private void checkSize()
	{
		float sum = 0;
		int k;
		Link link;
		
		for(k=0; k<this.cycle.size(); k++)
		{
			link = this.cycle.get(k);
			sum += link.getWeight();
		}
		
		if(sum < this.min_cycle)
		{
			this.shortest_cycle = (LinkedList<Link>)this.cycle.clone();
			this.min_cycle = sum;
		}
		float increment = gamma * (graph_weight/sum) ;
		Node no = this.start;
		for (k=0; k<this.cycle.size() ;k++)
		{
			link = this.cycle.get(k);
			link.updatePh(increment, no);
			no = link.getNode();
		}
	}
	
	/**
	 * Returns the Link that the Ant should traverse to
	 * @return
	 */
	public Link getMove()
	{
		Link temp;
		Node a;
		Node b;
		
		//Cycle's latest node
		if(this.cycle.size() > 0)
		{
			temp = this.cycle.getLast();
			a = temp.getNode();
		}
		else
			a = this.start;
		
		//Grabs the latest node's edges
		LinkedList<Link> adj = a.getAdj();
		//Valid edges
		LinkedList<Link> val = new LinkedList<Link>();		

		int v;
		int k;
		//Checks every edge and if valid, adds it to the valid edges list
		boolean found;
		for(k=0; k<adj.size(); k++)
		{
			temp=adj.get(k);
			a=temp.getNode();
			found=false;
			
			if(a.equals(this.start))
				found=true;
			for(v=0; v < cycle.size() && !found ;v++)
			{
				temp=this.cycle.get(v);
				b=temp.getNode();
				if( b.equals(a) )
					found=true;
			}
			
			if(!found)
				val.add(adj.get(k));
		}
		
		if(val.size()<2)
		{
			//In case of a single valid edge, returns it
			if(val.size()==1)
			{
				return val.get(0);	
			}
			//In case of no valid edge, all available edges are considered valid
			val=adj;
		}
		
		//Returns the most likely edge
		return val.get(this.getRandomIdx(val));
	}
	
	/**
	 * Moves the ant to the desired Node through the given Link and checks if by doing so, found an Hamiltonian cycle
	 * @param link Link the Ant should traverse to
	 * @return True if an Hamiltonian cycle was found with the ant move
	 */
	public boolean moveAnt(Link link)
	{
		Node no = link.getNode();
		boolean remove = false;
		
		//If the next node is the nest
		if(no.equals(this.start))
		{
			this.cycle.add(link);
			//Check if it is a hamiltonian cycle
			if(this.cycle.size()==n_nodes)
			{
				this.checkSize();
				//Clear the cycle
				this.cycle.clear();	
				return true;
			}	
			this.cycle.clear();	
			return false;
		}
		else
		{
			int k;
			//Check if the ant has visited the node
			for(k=0; k<this.cycle.size() ; k++)
			{
				Link aux = this.cycle.get(k);
				if(no.equals(aux.getNode()))
				{
					remove = true;
					break;
				}
			}
			//If it has already visited, clear the nodes that it visited afterwards
			for(int v=this.cycle.size()-1; v<=k && remove; v++)
				this.cycle.remove(v);
			//If not, it moves to the node
			if(!remove)
				this.cycle.add(link);
		}
		
		return false;
	}
	
	/**
	 * Returns the Ant's shortest cycle found
	 * @return
	 */
	public LinkedList<Link> getCycle()
	{
		return this.shortest_cycle;
	}
	
	public Node getFirst()
	{
		return this.start;
	}
	
	/**
	 * Returns the weigh of the shortes cycle found
	 * @return
	 */
	public float getWeight()
	{
		return this.min_cycle;
	}
	
	@Override
	public String toString() {
		String str = new String("{" + this.start.getId());
		Link link;
		Node no;
		
		for(int k=0; k<this.shortest_cycle.size()-1;k++)
		{
			link=this.shortest_cycle.get(k);
			no=link.getNode();
			
			str=str + "," + Integer.toString(no.getId()) ;
		}
		
		return str + "}";
	}
}
