import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * Junit test class
 * @author sinead urisohn
 * @version 14/05/2015
 *
 */
public class GraphTest {
	Graph g;
	@Before
	public void setUp() throws Exception {
		//read text file into graph
		g = new Graph( );

		//insert data to test
		/*
		 * A B 10
		 * A D 20
		 * A C 15
		 * B C 35
		 * B D 25
		 * D C 30
		 */
		g.addEdge( "A", "B", 10);
		g.addEdge("B","A",10);//make undirected graph
		
		g.addEdge( "A", "D", 20);
		g.addEdge("D","A",20);//make undirected graph
		
		g.addEdge( "A", "C", 15);
		g.addEdge("C","A",15);//make undirected graph
		
		g.addEdge( "B", "C", 35);
		g.addEdge("C","B",35);//make undirected graph
		
		g.addEdge( "B", "D", 25);
		g.addEdge("D","B",25);//make undirected graph
		
		g.addEdge( "D", "C", 30);
		g.addEdge("C","D",30);//make undirected graph
		

	}//end setup
	/**
	 * Test brute force method
	 */
	@Test
	public void testBruteForceMinPath()
	{
		assertTrue("this should be true", g.getMinCostPath("A")==80);
	}

	/**
	 * Test heuristic nearest neighbour method
	 */
	@Test
	public void testNearestNeighbourMinPath()
	{
		assertTrue("this should be true", g.getMinPathWithNearestNeighbourHeuristic("A")==80);
	}
	/**
	 * Test permutations method
	 */
	@Test
	public void testPermutationsMethod()
	{
		boolean permCheck=true;
		List<String> perm1 = new ArrayList<String>();
		perm1.add("A");
		perm1.add("B");
		List<String> perm2 = new ArrayList<String>();
		perm2.add("B");
		perm2.add("A");
		List<List<String>> permAnswer =new ArrayList<List<String>>();//array list of arrayList of Strings to store permutations
		permAnswer.add(perm1);
		permAnswer.add(perm2);
		List<List<String>> permMethodAnswer =g.getPermutation(new ArrayList<String>(), perm1);
		
		for(int i =0;i<2;i++)
		{
			for(int j =0;j<2;j++)
			{
				if(!permAnswer.get(i).get(j).equals(permMethodAnswer.get(i).get(j)))
				{
					permCheck=false;
				}
			}
		}
		assertTrue("this should be true",permCheck);
	}
	
	/**
	 * 
	 */
	@Test
	public void testPathAndEdgeCost()
	{
		//data to test
		/*
		 * A B 10
		 * A D 20
		 * A C 15
		 * B C 35
		 * B D 25
		 * D C 30
		 */
		
		//test edge cost
		assertTrue("this should be true",g.getCostOfThisEdge("A", "B")==10);
		assertTrue("this should be true",g.getCostOfThisEdge("A", "D")==20);
		assertTrue("this should be true",g.getCostOfThisEdge("A", "C")==15);
		assertTrue("this should be true",g.getCostOfThisEdge("B", "C")==35);
		assertTrue("this should be true",g.getCostOfThisEdge("B", "D")==25);
		assertTrue("this should be true",g.getCostOfThisEdge("D", "C")==30);
		//test total path cost
		List<String> perm = new ArrayList<String>();
		perm.add("D");
		perm.add("A");
		perm.add("C");
		assertTrue("this should be true",g.getPermutationPathCost("B",perm )==95);

	}
	
	
}
