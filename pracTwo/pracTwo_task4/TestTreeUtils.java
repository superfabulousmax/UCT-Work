package pracTwo_task4;

/**
*Junit test class for TreeUtils
*@author Sinead Urisohn
*@version 3/08/2015
*/
import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class TestTreeUtils{
	
	private String [] array5 = {"a","b","c"};
	private String [] array6 = {"a","b","c","d"};
	private String [] array7 = {"a","aa","bob","bill","dog","b","c","d"};


	private int [] numberOfAlphabet ={0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26};
	
	@Before
	public void setup(){

		//testRotations(array1);
		
		
	}
	@Test
	public void testCalculateStringKey()
	{
		AVLTree tree = new AVLTree();
		for(int i=1;i<numberOfAlphabet.length;i++)
		{
			assertTrue("This should be true",tree.calculateStringKey(AVLTree.alphabet[i])==numberOfAlphabet[i]);
		}
	}
	@Test
	public void testDelete()

	{
		delete(array7);
	}
	@Test
	public void testFind()
	{
		find(array7);
	}
	public void find(String [] array)
	{
		AVLTree tree = new AVLTree();
		for(int i=0;i<array.length;i++)
		{
			tree.insert(array[i]);
		}
		assertTrue("This should be true",tree.find("a").equals("(2)(a, aa)"));
		assertTrue("This should be true", tree.find("e").equals("No entry found"));

	}

	
	
	public void testRotationsString(String [] array){
		AVLTree tree = new AVLTree();
		for(int i=0;i<array.length;i++)
		{
			tree.insert(array[i]);
		}
		
		assertTrue("This should be true",tree.getRoot().getValue()=="b"&tree.getRoot().getRight().getValue()=="c"&tree.getRoot().getLeft().getValue()=="a");
	}
	@Test
	public void testContains()
	{
		
		testContainsString(array5);
	}
	@Test
	public void testRotateWorks()
	{
		
		testRotationsString(array5);


	}
	
	
	public void testContainsString(String [] array)
	{
		AVLTree tree = new AVLTree();
		for(int i=0;i<array.length;i++)
		{
			tree.insert(array[i]);
		}
		assertTrue("This should be true", tree.contains("a"));
		assertTrue("This should be true", tree.contains("b"));
		assertTrue("This should be true", tree.contains("c"));
		assertFalse("This should be false", tree.contains("kdsjfhsdhbs"));

	}

	public void delete(String [] array)
	{
		AVLTree tree = new AVLTree();
		for(int i=0;i<array.length;i++)
		{
			tree.insert(array[i]);
		}
		tree.delete("a");
		assertFalse("This should be false", tree.contains("a"));
		tree.delete("aa");
		
		assertTrue("This should be true", tree.find("a").equals("No entry found"));
	
		
	}
}