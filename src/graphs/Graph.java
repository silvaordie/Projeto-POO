package graphs;

public class Graph{
	Node[] nodes;
	
	public Graph(int _nNodes)
	{
		nodes=new Node[_nNodes];
	}

	public void connect(int _1, int _2, float _w )
	{
		Node no1;
		Node no2;
		
		if(nodes[_1-1] == null)
			nodes[_1-1] = new Node(_1);

		no1 = nodes[_1-1];
		
		if(nodes[_2-1]==null)
			nodes[_2-1] = new Node(_2);

		no2 = nodes[_2-1];
		
		nodes[_1-1].newAdj(no2, _w);
		nodes[_2-1].newAdj(no1, _w);			
	}
	
	public Node getNode(int _id)
	{
		return nodes[_id-1];
	}
	
	public int getSize()
	{
		return nodes.length;
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
