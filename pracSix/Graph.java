import java.util.HashMap;
import java.util.Map;

/**
 * This is a class that given a 
 * starting path returns the minimum
 * cost path that visits every node once 
 * @author sinead urisohn
 * @version 9/05/2015
 *
 */
public class Graph {
	
	private Map <String,Vertex> vertexMap = new HashMap<String,Vertex>();
	
	/**
	 * if vertex is not in map
	 * add it
	 * @param name of vertex
	 * @return the vertex
	 */
	private Vertex getVertex(String name)
	{
		Vertex v =vertexMap.get(name);
		if(v==null)
		{
			v= new Vertex(name);
			vertexMap.put(name,v);
		}
		return v;
	}
	
	/**
	 * add an edge to the graph
	 * @param name of vertex
	 * @param destName of vertex
	 * @param cost of path
	 */
	private void addEdge(String name, String destName, double cost)
	{
		Vertex v = getVertex(name);
	}
	
	
	public int getMinCostPath()
	{
		return 0;
	}
}
