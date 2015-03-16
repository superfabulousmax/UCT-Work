import java.io.PrintStream;
/**
 * Implementation of an AVL Tree
 * 
 * @author Stephan Jamieson
 * @version 3/3/2015
 */
public class AVLTree {

	private AVLTreeNode root;
	public final static String [] alphabet = {"","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"}; 


	/**
	 * Calculate the value of String based on the position of the alphabet of the first letter
	 * 
	 */
	public static Integer calculateStringKey(String value)
	{
		int key=0;
		for(int i =1;i<alphabet.length;i++)
		{
			if(alphabet[i].equalsIgnoreCase((value.substring(0,1))))
			{
				key=i;
				return key;
			}
		}
		return key;
	}


	/**
	 * Create an empty AVL tree
	 */
	public AVLTree() {
		root = null;
	}

	/**
	 * get height
	 */
	public Integer getHeight()
	{
		if(root == null)
			return 0;
		else
			return root.getHeight();
	}


	/**
	 * Insert the given key into the tree.
	 */
	
	public void insert(String value) {
		
		root = TreeUtils.insert(root,value);

	}

	/**
	 * Use the given PrintStream object to output a textual representation of this tree.
	 */
	public void print(PrintStream printStream) {

		SimpleTreeWriter writer = new SimpleTreeWriterImpl(printStream);
		writer.write(this.root);
	}

	
	/**
	 * Determine whether the tree contains a given String value
	 */
	public boolean contains(String value) {
		if (root==null) {
			return false;
		}
		else {
			return TreeUtils.contains(root, value);
		}
	}
	/**
	 * Delete the given string value from tree structure
-	 */
	public void delete(String value){
		
		root = TreeUtils.delete(root, value);
	}
	/**
	 * Find the given key Letter and return data about the letter key
	 */
	public String find(String letter)
	{
		return TreeUtils.find(root, letter);
	}


}
