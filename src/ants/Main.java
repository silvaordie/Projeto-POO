package ants;

import graphs.Graph;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Graph graph = new Graph(5);
		
		graph.connect(1, 2, 3);
		graph.connect(1, 3, 6);
		graph.connect(1, 4, 6);
		graph.connect(1, 5, 2);
		
		graph.connect(2, 3, 3);
		graph.connect(2, 4, 2);
		graph.connect(2, 5, 5);
		
		graph.connect(4, 5, 1);	
		
		Ant ant=new Ant(graph.getNode(0),5,1,1,1);
		
		ant.moveAnt();
		System.out.println(ant.toString());
		ant.moveAnt();
		System.out.println(ant.toString());
		ant.moveAnt();
		System.out.println(ant.toString());
		ant.moveAnt();
		System.out.println(ant.toString());
		ant.moveAnt();
		System.out.println(ant.toString());
		ant.moveAnt();
		System.out.println(ant.toString());
		ant.moveAnt();
		System.out.println(ant.toString());
		ant.moveAnt();
		System.out.println(ant.toString());
		ant.moveAnt();
		System.out.println(ant.toString());
		
		int a = 1;
		a=2;
	}

}
