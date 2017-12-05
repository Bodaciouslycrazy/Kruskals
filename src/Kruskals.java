//Bodie Malik
//December 4, 2017
//blerg

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.io.*;

//Step by step process:
// 1 - read from ass9_data.csv
// 2 - generate graph
// 3 - apply kruskals algorithm

public class Kruskals {
	
	private final static String filename = "assn9_data.csv";
	private static ArrayList<String> cityNames;
	private static PriorityQueue<Edge> queue;
	private static ArrayList<Edge> edgeList;
	private static EdgeComparator edgeComparator = new EdgeComparator();
	
	private static DisjSets disj;
	
	public static void main(String[] args) throws NumberFormatException, Exception
	{
		FileReader fr = new FileReader(filename);
		BufferedReader br = new BufferedReader(fr);
		
		
		cityNames = new ArrayList<String>();
		
		String line = "";
		
		//assigning an index to each city name
		while( (line = br.readLine()) != null )
		{
			cityNames.add( line.substring(0, line.indexOf(',', 0)) );
		}
		
		//testing
		//PrintAllCityNames();
		
		
		//Create every edge in the graph
		queue = new PriorityQueue<Edge>( cityNames.size(), edgeComparator );
		edgeList = new ArrayList<Edge>(cityNames.size());
		
		//reset the file reader
		fr = new FileReader(filename);
		br = new BufferedReader(fr);
		int lineNumber = 0;
		
		while( (line = br.readLine()) != null)
		{
			String[] strings = line.split(",");
			
			for(int i = 1; i < strings.length; i = i+2)
			{
				Edge newEdge = new Edge(lineNumber, GetIndex(strings[i]), Integer.parseInt(strings[i+1]));
				if(!EdgeExists(newEdge))
				{
					edgeList.add(newEdge);
					queue.add(newEdge);
				}
				
			}
			lineNumber++;
		}
		
		
		//********************
		//Calculate minimum spanning tree
		disj = new DisjSets(cityNames.size());
		ArrayList<Edge> KeptEdges = new ArrayList<Edge>();
		
		while( queue.size() > 0)
		{
			Edge current = queue.poll();
			
			if(disj.find(current.GetV1()) != disj.find(current.GetV2()))
			{
				//keep edge
				KeptEdges.add(current);
				disj.union( disj.find(current.GetV1()), disj.find(current.GetV2()));
			}
		}
		
		
		//**********************
		//Print results
		System.out.println("Edges that make up the minimum spanning tree:");
		int sum = 0;
		for(int i = 0; i < KeptEdges.size(); i++){
			Edge current = KeptEdges.get(i);
			sum += current.GetWeight();
			System.out.printf("%-25s <---> %-25s weight: %d\n", cityNames.get(current.GetV1()), cityNames.get(current.GetV2()), current.GetWeight());
		}
		
		System.out.printf("Total Weight Sum: %d", sum);
		
	}
	
	private static int GetIndex(String name) throws Exception
	{
		for(int i = 0; i < cityNames.size(); i++){
			if(cityNames.get(i).equals(name)) return i;
		}
		
		throw new Exception("City name not found in city list");
	}
	
	private static boolean EdgeExists(Edge e)
	{
		for(int i = 0; i < edgeList.size(); i++)
		{
			if(edgeComparator.compareVertices(e, edgeList.get(i)))
			{
				return true;
			}
		}
		
		return false;
	}
	
	private static void PrintAllCityNames()
	{
		System.out.println("There are "+cityNames.size() + " cities.");
		
		for(int i = 0; i < cityNames.size(); i++)
		{
			System.out.println(cityNames.get(i));
		}
	}
}
