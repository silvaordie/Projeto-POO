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

public class Simulation extends DefaultHandler{

	private static final String GRAPH = "graph";
	private static final String NODE = "node";
	private static final String WEIGHT = "weigth";
	private static final String EVENTS = "events";
	private static final String MOVE = "move";
	private static final String EVAPORATION = "evaporation";
	private static final String SIMULATION = "simulation";
	
	static Graph graph;

	public void startDocument(){
		System.out.println("Parsing...");
	}
	
	public void endDocument(){
		System.out.println("Parsing completed!");
	}
	
	public void startElement(String uri, String name, String tag, Attributes atts){
		if(tag.equals(GRAPH))
			
	}
	
	public void endElement(String uri, String name, String tag)
	{
		
	}
	
}
