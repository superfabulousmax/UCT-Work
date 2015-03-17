/**
*Junit test class for TreeUtils
*@author Sinead Urisohn
*@version 3/08/2015
*/
import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class TestTreeUtils{
	private int [] array1 = {1,2,3};

	private int [] array2 = {3,2,1};

	private int [] array3 ={1,3,2};


	private int [] array4 = {2,3,1};
	private String [] array5 = {"a","b","c"};
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

	
	public void testRotations(int [] array){
		AVLTree tree = new AVLTree();
		for(int i=0;i<array.length;i++)
		{
			tree.insert(array[i]);
		}
		//System.out.println(tree.getRoot().getKey()+"cflgjskjgnsk");rit
		// SimpleTreeWriterImpl obj = new SimpleTreeWriterImpl();
		// obj.write(tree);
		assertTrue("This should be true",tree.getRoot().getKey()==2&tree.getRoot().getRight().getKey()==3&tree.getRoot().getLeft().getKey()==1);
	}
	@Test
	public void testContains()
	{
		testContainsInteger(array2);
		testContainsString(array5);
	}
	@Test
	public void testRotateWorks()
	{
		testRotations(array1);
		testRotations(array2);
		testRotations(array3);
		testRotations(array4);


	}
	public void testContainsInteger(int [] array)
	{
		AVLTree tree = new AVLTree();
		for(int i=0;i<array.length;i++)
		{
			tree.insert(array[i]);
		}
		assertTrue("This should be true", tree.contains(1));
		assertFalse("This should be false", tree.contains(444));

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
}