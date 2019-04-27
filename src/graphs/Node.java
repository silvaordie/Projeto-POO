package graphs;
import java.util.*;

public class Node{
	
		private int  id;
		LinkedList<Link> adj = new LinkedList<Link>();
		
		Node(int _id)
		{
			this.id = _id;
		}
		
		public void newAdj(Node _n, float _w)
		{
			adj.add( new Link( _n , _w ) );
		}
		
		public LinkedList<Link> getAdj()
		{
			return this.adj;
		}
		
		public int getId()
		{
			return this.id;
		}
		
		@Override
		public String toString() {
			String str = new String("Node " + Integer.toString(this.id) +  ": {");
			Link temp;
			
			for(int k=0; k<adj.size(); k++)
			{
				temp = adj.get(k);
				str= str + temp.toString(); 
			}	
			str=str + "}\n";
			
			return str;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + id;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			if (id != other.id)
				return false;
			
			return true;
		}
		
}
