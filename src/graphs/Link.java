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
	
	public void updatePh(float _value)
	{
		this.pheromone += _value;
		
		LinkedList<Link> adj = node.getAdj();
		Link temp;
		
		for(int k=0; k<adj.size(); k++)
		{	
			temp = adj.get(k);
			
			if(temp.node.equals(this.node))
			{ 
				temp.pheromone += _value;
				
				return;
			}
		}
		
	}

	public Node getNode()
	{
		return this.node;
	}
	
	public void evaporatePh(float _value)
	{
		this.pheromone -= _value;
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
