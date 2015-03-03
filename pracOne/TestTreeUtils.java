/**
*Junit test class to test similar BSTs
*@author Sinead Urisohn
*@version 3/02/2015
*/
import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class TestTreeUtils{
	//test trees
	private int [] array1 = {1,2,3,4};

	private int [] array2 = {2,3,4,5};

	private int [] array3 ={2,9,7,8};


	private int [] array4 = {9,8,7,14,11,22};


	private int [] array5 = {8,7,6,15,12,23};

	private int [] array6 = {8,7,6,34,12,23};
	private int [] array7= {1,10,6,7};
	SimpleBST tree1 = new  SimpleBST();
	SimpleBST tree2 = new  SimpleBST();
	
	SimpleBST tree3 = new  SimpleBST();
	
	SimpleBST tree4 = new  SimpleBST();
	SimpleBST tree5 = new  SimpleBST();
	
	SimpleBST tree6 = new  SimpleBST();
	SimpleBST tree7 = new  SimpleBST();
	@Before
	public void setup(){
	

	
		for(int i=0;i<array1.length;i++)
		{
			tree1.insert(i);
		}

		for(int i=0;i<array2.length;i++)
		{
			tree2.insert(i);
		}
		for(int i=0;i<array3.length;i++)
		{
			tree3.insert(i);
		}
		for(int i=0;i<array4.length;i++)
		{
			tree4.insert(i);
		}
		for(int i=0;i<array5.length;i++)
		{
			tree5.insert(i);
		}
		for(int i=0;i<array6.length;i++)
		{
			tree6.insert(i);
		}
		for(int i=0;i<array7.length;i++)
		{
			tree7.insert(i);
		}
	}//end setup method
	
	BinaryTreeNode placeholder = BinaryTreeNode.EMPTY_NODE;//if tree is incomplete


	@Test
	public void testPlaceHolder(){
		assertTrue("This should be true",TreeUtils.isPlaceHolder(placeholder));
	}


	@Test
	public void testSimilar1(){
		assertTrue("This should be true", tree1.similar(tree3));
	}
	@Test
	public void testSimilar2(){
		assertTrue("This should be false", tree1.similar(tree2));
	}
	@Test
	public void testSimilar3(){
		assertTrue("This should be false", tree3.similar(tree4));
	}
	@Test
	public void testSimilar4(){
		assertTrue("This should be false", tree5.similar(tree6));
	}
	@Test
	public void testSimilar5(){
		assertTrue("This should be true", tree4.similar(tree5));
	}
	@Test
	public void testSimilar6(){
		assertTrue("This should be true", tree3.similar(tree7));

	}

}