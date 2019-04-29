package graphs;
import java.util.*;

public class Link {
	float weight;
	float pheromone=0;
	Node node;
	
	Link( Node _n , float _w )
	{
		this.weight = _w;
		this.node = _n;
	}	
	
	public void updatePh(float _value, Node no)
	{
		this.pheromone += _value;
		if(this.pheromone<0)
			this.pheromone = 0;
		
		LinkedList<Link> adj = node.getAdj();
		Link temp;
		
		for(int k=0; k<adj.size(); k++)
		{	
			temp = adj.get(k);
			
			if(temp.node.equals(no))
			{ 
				temp.pheromone += _value;
				if(temp.pheromone < 0)
					temp.pheromone = 0;
				
				return;
			}
		}
	}

	public Node getNode()
	{
		return this.node;
	}
	
	public void evaporatePh(float _value, Node no)
	{
		this.pheromone -= _value;
		if(this.pheromone<0)
			this.pheromone = 0;
		
		LinkedList<Link> adj = node.getAdj();
		Link temp;
		
		for(int k=0; k<adj.size(); k++)
		{	
			temp = adj.get(k);
			
			if(temp.node.equals(no))
			{ 
				temp.pheromone -= _value;
				if(temp.pheromone < 0)
					temp.pheromone = 0;
				
				return;
			}
		}
	}
	
	public float getPh()
	{
		return this.pheromone;
	}

	public float getWeight()
	{
		return this.weight;
	}
	
	@Override
	public String toString() {
		return "[" + Integer.toString(node.getId()) + ", " + Float.toString(weight) + ", "  + Float.toString(pheromone) + "]  ";
	}
	
	
	
}
