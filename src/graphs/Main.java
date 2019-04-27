package graphs;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Graph graph = new Graph(5);
		Node no;
		Link link;
		
		graph.connect(1, 2, 3);
		graph.connect(1, 3, 6);
		graph.connect(1, 4, 6);
		graph.connect(1, 5, 2);
		
		graph.connect(2, 3, 3);
		graph.connect(2, 4, 2);
		graph.connect(2, 5, 5);
		
		graph.connect(4, 5, 1);
		
		no= graph.getNode(2);
		
		for(int k=0; k<no.adj.size(); k++)
		{
			link = no.adj.get(k);
			link.updatePh(2, no);
		}
		
		System.out.println(graph.toString());
	}

}
