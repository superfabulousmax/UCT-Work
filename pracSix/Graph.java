import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

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
	public void addEdge(String name, String destName, int cost)
	{
		Vertex v = getVertex( name );
        Vertex w = getVertex( destName );
        v.getAdjacent().add( new Edge( w, cost ) );
	}
	
	
	

	/**
	 * a.Consider city A as the starting point
	 * b.Generate all possible paths from A through all the nodes in the graph and back to A
	 * c.Calculate the cost of the paths and keep track of the minimum cost path
	 * d.Return the minimum cost path.
	 * @param start name of the vertex 
	 * @return min cost path
	 * @throws Exception 
	 */
	public int getMinCostPath(String startName) 
	{
		Vertex v = vertexMap.get(startName);//get vertex associated with this name
		if(v==null)//exception
		{
			throw new NoSuchElementException("Vertex is null");
		}
		List<Edge> adjEdjes =v.getAdjacent();//get the adjacency list for this vertex
		
		//loop through adjEdjes list and get Strings of names of vertices on edges
		//add names to String list
		List<String> adjVertexNames = new ArrayList<String>();
		for(Edge edge: adjEdjes)
		{
			adjVertexNames.add(edge.getDest().getName());	
		}
		//send adjVertexNames into getPermutation method to get all permutations of paths for
		//brute force naive method
		List<List<String>> permutations = getPermutation(new ArrayList<String>(), adjVertexNames);
		
		//calculate min path that goes through all nodes once
		int minCost=Integer.MAX_VALUE;
		for(int i =0;i<permutations.size();i++)
		{
			int traversingPermutationCost = getPermutationPathCost(startName, permutations.get(i));
			if(traversingPermutationCost<minCost)
				minCost=traversingPermutationCost;
		}



		return minCost;
	}
	/**
	 * 
	 * @param startName of vertex
	 * @param permutation to work with
	 * @return the total cost of travelling the path of this permutation
	 */
	public int getPermutationPathCost(String startName,List<String> permutation)
	{
		
		int sumOfThisPath=0;
		//add cost of going from startname to first vertex in permutation
		sumOfThisPath+=getCostOfThisEdge(startName, permutation.get(0));
		//also add cost of going from last vertex in permutation back to startname vertex
		sumOfThisPath+=getCostOfThisEdge( permutation.get(permutation.size()-1),startName);
		//loop through vertices in permutation
		for(int j=0;j<permutation.size()-1;j++)
		{
			 
			//add costs of going from vertices in permutation
			sumOfThisPath+=getCostOfThisEdge(permutation.get(j), permutation.get(j+1));
		
		}
		return sumOfThisPath;
	}
	/**
	 * 
	 * @param fromVertex
	 * @param toVertex
	 * @return the cost on this edge
	 */
	public int getCostOfThisEdge(String fromVertex,String toVertex)
	{
		
		Vertex currentVertex = vertexMap.get(fromVertex);
		List<Edge> adjEdgesForThisVertex = currentVertex.getAdjacent();
		for(int k=0;k<adjEdgesForThisVertex.size();k++ )
		{
			if(adjEdgesForThisVertex.get(k).getDest().getName().equals(toVertex))
				return adjEdgesForThisVertex.get(k).getCost();
		}
		return 0;
		
	}
	/**
	 * 
	 * @param begArr an empty arrayList
	 * @param endArr an array with list of all vertices to generate permutations with
	 * @return List of list of permutations
	 */
	public List<List<String>> getPermutation(List<String> begArr,List<String> endArr )
	{
		List<List<String>> perm =new ArrayList<List<String>>();//array list of arrayList of Strings to store permutations for naive solution

		if(endArr.size()<=1)//base case
		{
			begArr.addAll(endArr);//concatenate beginning array and ending array
			perm.add(begArr);//add permutation to permutations array
			return perm;//return results
		}



		for(int i =0;i<endArr.size();i++)
		{
			//get first item in array
			//add it to beginning array
			
			//get array elements excluding element i and add to newArr
			List<String> newArr = new ArrayList<String>();
			newArr.addAll(endArr.subList(0,i));
			newArr.addAll(endArr.subList(i+1,endArr.size()));
			
			List<String> newBegArr = new ArrayList<String>();
			newBegArr.addAll(begArr);
			newBegArr.add(endArr.get(i));
			
			perm.addAll(getPermutation(newBegArr, newArr));//add permutations after each recursive call
			
		}
		
		return perm;

	}
	
	/**
	 * Nearest Neighbour algorithm
	 * 1. Select a random city.
	 * 2. Find the nearest unvisited city and go there.
	 * 3. Are there any unvisitied cities left? If yes, repeat step 2.
	 * 4. Return to the first city.
	 * @param start name of the vertex 
	 * @return min cost path
	 * @throws Exception NoSuchElementException
	 */
	public int getMinPathWithNearestNeighbourHeuristic(String startName)
	{
		Vertex v = vertexMap.get(startName);//get vertex associated with this name
		if(v==null)//exception
		{
			throw new NoSuchElementException("Vertex is null");
		}
		List<Edge> adjEdjes =v.getAdjacent();//get the adjacency list for this vertex
		
		//loop through adjEdjes list and get Strings of names of vertices on edges
		//add names to String list
		List<String> pathVertexNames = new ArrayList<String>();
		
		for(Edge edge: adjEdjes)
		{
			pathVertexNames.add(edge.getDest().getName());	
		}
	
		int sumOfPath=0;
		//while there are unvisited nodes
		
		String startNode=startName;
		String endNode =getNextCheapestEdge(startName,pathVertexNames);
		while(pathVertexNames.size()>0)
		{
			
			sumOfPath+=getCostOfThisEdge(startNode, endNode);
			startNode=endNode;
			pathVertexNames.remove(startNode);
			endNode=getNextCheapestEdge(startNode,pathVertexNames);
			if(pathVertexNames.size()==0)
			{
				endNode=startNode;
			}
			
		}
		//return to first city
		sumOfPath+=getCostOfThisEdge(endNode, startName);
		return sumOfPath;
	}
	
	/**
	 * 
	 * @param startName
	 * @param pathVertexNames
	 * @return name of vertex with cheapest edge
	 */
	public String getNextCheapestEdge(String startName, List<String> pathVertexNames)
	{
		Vertex v = vertexMap.get(startName);//get vertex associated with this name
		if(v==null)//exception
		{
			throw new NoSuchElementException("Vertex is null");
		}
		List<Edge> adjEdjes =v.getAdjacent();//get the adjacency list for this vertex
		int min = Integer.MAX_VALUE;
		String nameOfMinVertex="";
		for(Edge edge: adjEdjes)
		{
				if(edge.getCost()<min&pathVertexNames.contains(edge.getDest().getName()))
				{
					min=edge.getCost();
					nameOfMinVertex=edge.getDest().getName();
				}
		}
		return nameOfMinVertex;
	}
	
	
}
