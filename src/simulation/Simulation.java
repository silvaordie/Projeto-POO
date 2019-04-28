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
	
	private String read_string="";

	static Graph graph;

	public void startDocument(){
		System.out.println("Parsing...");
	}
	
	public void endDocument(){
		System.out.println("Parsing completed!");
	}
	
	public void startElement(String uri, String name, String tag, Attributes atts){
		if(tag.equals(GRAPH))
			System.out.println(name + "|" + uri);
			
	}
	
	public void endElement(String uri, String name, String tag)
	{
		
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
	
	public static void main(String args[]){
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
			
	}									 
}
