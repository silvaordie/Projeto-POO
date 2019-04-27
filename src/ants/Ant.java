package ants;
import java.util.*;

import graphs.*;
public class Ant {
	
	LinkedList<Link> cycle =  new LinkedList<Link>();
	LinkedList<Link> shortest_cycle = new LinkedList<Link>(); 
	private float min_cycle = 9999999;
	private Node start;
	private int n_nodes;
	float alpha;
	float beta;
	float gamma;
	
	Ant(Node _ini, int _n_nodes, float _alpha, float _beta, float _gamma)
	{
		this.n_nodes = _n_nodes;
		this.start= _ini;
		this.alpha = _alpha;
		this.beta = _beta;
		this.gamma = _gamma;
	}
	
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
			weights[k]=(this.alpha + temp.getPh())/(this.beta + temp.getWeight());
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
		float increment = this.gamma * (sum) ;
		Node no = this.start;
		for (k=0; k<this.cycle.size() ;k++)
		{
			link = this.cycle.get(k);
			link.updatePh(increment, no);
			no = link.getNode();
		}
	}
	
	public void moveAnt()
	{
		Link temp;
		Node a;
		Node b;
		
		if(this.cycle.size() > 0)
		{
			temp = this.cycle.getLast();
			a = temp.getNode();
		}
		else
			a = this.start;
			
		LinkedList<Link> adj = a.getAdj();
		LinkedList<Link> val = new LinkedList<Link>();		

		int v;
		int k;
		boolean found;
		boolean empty=false;
		

		
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
			if(val.size()==1)
			{
				temp=val.get(0);
				cycle.add(temp);	
				return;
			}
			
			empty = true;
			val=adj;
		}
		if(val.size()>1)
		{
			int sel = this.getRandomIdx(val);
			temp=val.get(sel);
			if(empty)
			{
				a = temp.getNode();
				if(a.equals(this.start))
				{
					this.cycle.add(temp);
					
					if(this.cycle.size()==this.n_nodes)
						this.checkSize();
					
					this.cycle.clear();
					
					return;
				}

				k=0;
				temp = this.cycle.get(k);
				while(!a.equals(temp.getNode()) )
				{
					temp=this.cycle.get(k);
					k++;
				}
				
				v=this.cycle.size()-1;
				while(v!=k)
				{
					this.cycle.remove(v);
					v--;
				}
				this.cycle.remove(v);			
			}
			else
				this.cycle.add(temp);				
		}
		
		return;
	}

	@Override
	public String toString() {
		String str = new String("Cycle : " + this.start.getId() + ", ");
		Link link;
		Node no;
		
		for(int k=0; k<cycle.size();k++)
		{
			link=this.cycle.get(k);
			no=link.getNode();
			
			str=str + Integer.toString(no.getId()) + ", ";
		}
		
		return str + "\n";
	}
}
