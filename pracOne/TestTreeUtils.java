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


	private int [] array4 = {17,11,9,2,3,1};


	private int [] array5 = {8,7,6,15,12,23};

	private int [] array6 = {8,7,6,34,12,23};
	SimpleBST tree1 = new  SimpleBST();
	BinaryTreeNode placeholder = BinaryTreeNode.EMPTY_NODE;//if tree is incomplete


	@test
	public void testPlaceHolder(){
		assertTrue(TreeUtils.isPlaceHolder(placeholder));
	}


	@Test
	public void testSimilar(){

	}

}