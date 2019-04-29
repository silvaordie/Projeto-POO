package simulation;

import java.io.File;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import graphs.*;
import ants.*;
import events.*;

import java.util.*;

public class Simulation extends DefaultHandler{

	private static final String GRAPH = "graph";
	private static final String NODE = "node";
	private static final String WEIGHT = "weight";
	private static final String MOVE = "move";
	private static final String EVAPORATION = "evaporation";
	private static final String SIMULATION = "simulation";
	private static String read_string = "";

	private static int crr_node;
	private static int connecting_node;
	private static int nestNode;
		
	private static Graph graph;
	private static Ant[] ants;
	
	private static float finalinst;
	private static float plevel;
	private static float nestnode;
	
	private static float alpha;
	private static float beta;
	private static float delta;
	private static float eta;
	private static float rho;
	
	public void startDocument(){
		System.out.println("Parsing...");
	}
	
	public void endDocument(){
		System.out.println("Parsing completed!");
	}
	
	public void startElement(String uri, String name, String tag, Attributes atts){
		if(tag.equals(GRAPH))
		{
			graph = new Graph(Integer.parseInt(atts.getValue(atts.getIndex("nbnodes"))));
			nestNode = Integer.parseInt(atts.getValue(atts.getIndex("nestnode")));
		}
		
		if(tag.equals(NODE))
			crr_node = Integer.parseInt(atts.getValue(atts.getIndex("nodeidx")));
		
		if(tag.equals(WEIGHT))
			connecting_node = Integer.parseInt(atts.getValue(atts.getIndex("targetnode")));
	
		if(tag.equals(SIMULATION))
		{
			ants= new Ant[Integer.parseInt(atts.getValue(atts.getIndex("antcolsize")))];
			finalinst = Float.parseFloat(atts.getValue(atts.getIndex("finalinst")));
			plevel = Float.parseFloat(atts.getValue(atts.getIndex("plevel")));
		}
			
		if(tag.equals(MOVE))
		{
			alpha = Float.parseFloat(atts.getValue(atts.getIndex("alpha")));
			beta = Float.parseFloat(atts.getValue(atts.getIndex("beta")));
			delta = Float.parseFloat(atts.getValue(atts.getIndex("delta")));	
		}
			
		if(tag.equals(EVAPORATION))
		{
			eta = Float.parseFloat(atts.getValue(atts.getIndex("eta")));
			rho = Float.parseFloat(atts.getValue(atts.getIndex("rho")));
		}
	}
	
	public void endElement(String uri, String name, String tag)
	{
		if(tag.equals(WEIGHT))
			graph.connect(crr_node, connecting_node, Integer.parseInt(read_string));
	}
	public void characters(char[] ch, int start, int length){
		read_string=new String(ch,start,length);
	}
	
	public void warning(SAXParseException e)throws SAXParseException{
		System.out.println("Warning! "+ e.getMessage());
	}
	
	public void error(SAXParseException e)throws SAXParseException{
		System.out.println("Error! "+ e.getMessage());
	}
	
	public void fatalError(SAXParseException e) throws SAXParseException{
		System.out.println("Fatal error! "+ e.getMessage()+"\nAbortando");
		System.exit(-1);
	}
	
	public static void main(String args[])
	{
		if(args.length != 1){
			System.out.println("Usage: java ArtigoHandler <nome do fich.xml>");
			return;
		}
		SAXParserFactory factory = SAXParserFactory.newInstance();
		factory.setValidating(true);
		try{
			SAXParser parser = factory.newSAXParser();
			parser.parse(new File(args[0]),new Simulation());
		} catch(ParserConfigurationException e){
			System.out.println(e);
		} catch(SAXException e){
			System.out.println(e);
		} catch(IOException e){
			System.out.println(e);
		}
		
		int mevents=0;
		int eevents=0;
		PEC pec = new PEC();
		
		for(int k=0; k< ants.length ; k++)
		{
			ants[k]= new Ant(graph.getNode(nestNode), graph.getSize(), alpha, beta, plevel);
			pec.addEvPEC(new EvAntMove( Event.expRandom(delta) , ants[k]) );
		}
		
		Event currentEvent = pec.nextEvPEC();
		float currenTime = currentEvent.geTime();
		
		while(currenTime < finalinst)
		{
			currentEvent.simulate();
			currenTime=currentEvent.geTime();
			
			if(currentEvent.getClass().equals(EvAntMove.class))
			{
				mevents++;
				if(((EvAntMove)currentEvent).foundCycle())
				{
					Ant ant =  ((EvAntMove)currentEvent).getAnt();
					LinkedList<Link> cycle = ant.getCycle();
					
					for(int k=0; k<cycle.size(); k++)
					{
						Link link = cycle.get(k);
						if(k==0)
						{
							Link aux = cycle.get(cycle.size()-1);
							pec.addEvPEC(new EvPhEvaporation(currenTime + Event.expRandom(eta), link, aux.getNode(), rho));
						}
						else
						{
							Link aux = cycle.get(k-1);
							pec.addEvPEC(new EvPhEvaporation(currenTime + Event.expRandom(eta), link, aux.getNode(), rho));							
						}
					}
				}
						
				pec.addEvPEC(new EvAntMove(currenTime+Event.expRandom(5*delta) , ((EvAntMove)currentEvent).getAnt()));
			}
				
			if(currentEvent.getClass().equals(EvPhEvaporation.class))
			{
				
				eevents++;
				Link link = ((EvPhEvaporation)currentEvent).getLink();
				
				if(link.getPh() > 0)
					pec.addEvPEC( new EvPhEvaporation( currenTime + Event.expRandom(eta) , link , ((EvPhEvaporation)currentEvent).getNode() , rho ) );
			}
			
			currentEvent = pec.nextEvPEC();
			
			if(currenTime%20 < 1)
			{
				int min_k=-1;
				float min_w=9999;
				
				for(int k=0; k<ants.length ; k++)
				{
					if(ants[k].getWeight() < min_w)
					{
						min_k=k;
						min_w=ants[k].getWeight();
					}
				}
				System.out.println("Present instant: " + currenTime );
				System.out.println("Number of move events: " + mevents );
				System.out.println("Number of evaporation events: " + eevents );
				if(min_k!=-1)
					System.out.println("Hamiltonian cycle: " + ants[min_k].toString() );
			}
			
		}

	}									 
}
