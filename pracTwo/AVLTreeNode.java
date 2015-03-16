
/**
 * Implements a node suitable for building AVL tree structures.
 * 
 * @author Stephan Jamieson
 * @version 3/3/2015
 */
public class AVLTreeNode {

	private Integer key;
	private String value;//String value
	private int balanceFactor;//balance factor
	private int height;
	private AVLTreeNode left;
	private AVLTreeNode right;


	public final static AVLTreeNode EMPTY_NODE = new AVLTreeNode();
	
	private AVLTreeNode() { this.key=null; this.height=-1; this.left=null; this.right=null; }


	/**
	 * Create an AVLTreeNode that contains the given key
	 */
	public AVLTreeNode(Integer key) {
		this(null, key, null);
		value = null;
	}
	/**
	 * Create an AVLTreeNode that contains the given integer key and String value
	 */
	public AVLTreeNode(Integer key, String value) { 
		this(null, key, null);
		
		this.value =value;
		

	}

	private AVLTreeNode(AVLTreeNode left, Integer key, AVLTreeNode right) {
		assert(key!=null);
		this.left=left;
		this.right=right;
		this.key=key;
		this.height=0;
	}

	/* Low level structural operations */  
	/**
	 * return a value given a node
	 */
	public String valueOfKey(AVLTreeNode node,Integer key)
	
	{
		
		String valueString="";
		if(key<this.key) return valueOfKey(node.getLeft(),key);
		else if(key>this.key) return valueOfKey(node.getRight(),key);
		else valueString=node.getValue();
		return valueString;
	}

	/**
	 * Determine whether this node has a left branch.
	 */
	public boolean hasLeft() { return left!=null; }
	/**
	 * Determine whether this node has a right branch.
	 */
	public boolean hasRight() { return right!=null; }

	/** 
	 * Determine whether this node has a key.
	 */
	public boolean hasKey() { return key!=null; }
	/**
	 * Obtain string value of node
	 * @return string value
	 */
	public String getValue() {
		return value;
	}


	/**
	 * Set String value of this node
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * Obtain the key stored in this node.
	 */
	public Integer getKey() { return key; }

	/**
	 * Set key value of this node
	 */

	public void setKey(Integer key){this.key =key;}

	/**
	 * Obtain the height value stored at this node. (Requirs that ka
	 */
	public int getHeight() {
		//return this.height;

		if (this.hasLeft() && this.hasRight()) {
			return Math.max(this.getLeft().getHeight(), this.getRight().getHeight())+1;
		}
		else if (this.hasLeft()) {
			return this.getLeft().getHeight()+1;
		}
		else if (this.hasRight()) {
			return this.getRight().getHeight()+1;
		}
		else {//BASE CASE!!!!
			return 1;
		}
	}
	/**
	 * Return height of this tree structure to calculate balance factor
	 */
	//code to be implemented
	public int treeHeight()
	{
		return 0;
	}
	/**
	 * Obtain the balance factor for this node.
	 */
	public int getBalanceFactor() { 
		int left = (this.hasLeft() ? this.getLeft().getHeight() : 0);
		int right = (this.hasRight() ? this.getRight().getHeight() : 0);
		return left-right;
	}
	/**
	 * Set balance factor for this node
	 */
	public void setBalanceFactor(int bf) {
		this.balanceFactor = bf;
	}


	/**
	 * Obtain this node's left branch. Requires that <code>this.hasLeft()</code>.
	 */
	public AVLTreeNode getLeft() { 
		return this.left; 
	}
	/**
	 * Obtain this node's right branch. Requires that <code>this.hasRight()</code>.
	 */
	public AVLTreeNode getRight() { 
		return this.right; 
	}

	/**
	 * Set the height stored in this node.
	 */
	public void setHeight(int height) { 
		assert(this!=EMPTY_NODE);
		this.height=height; 
	}


	/**
	 * Set this node's left branch.
	 */
	public void setLeft(AVLTreeNode tree) {
		assert(this!=EMPTY_NODE);
		this.left = tree;
	}

	/**
	 * Set this node's right branch.
	 */
	public void setRight(AVLTreeNode tree) {
		assert(this!=EMPTY_NODE);
		this.right = tree;
	}

	/**
	 * Obtain the longest node label for nodes stored in this tree structure.
	 */
	public Integer getLargest() {
		Integer largest = this.toString().length();
		if (this.hasLeft()) 
			largest = Math.max(largest, this.getLeft().getLargest());
		if (this.hasRight()) 
			largest = Math.max(largest, this.getRight().getLargest());

		return largest;
	}


	/**
	 * Obtain a String representation of this node.
	 */
	public String toString() {
		if(value!=null)
		{
			return this.getKey().toString()+"("+this.getBalanceFactor()+")("+value+")";
		}
		else{
			if(this!=EMPTY_NODE)
				return this.getKey().toString()+"("+this.getBalanceFactor()+")";
			else
				return "";
		}
	}








}
