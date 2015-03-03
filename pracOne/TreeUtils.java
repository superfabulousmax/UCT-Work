import java.util.*;
/**
 * 	utility procedures for Binary tree structures.
 * 
 * @author Stephan Jamieson 
 * @version 24/2/2015
 */

public class TreeUtils{
	/**
	*Determine whether node is a place holder
	*@param BinaryTreeNode node
	*/
	public static boolean isPlaceHolder(BinaryTreeNode node) 
	{
		return node == BinaryTreeNode.EMPTY_NODE;
	}
	/**
	*Obtain a list containing the root node of the given structure i.e.
	*@param BinaryTreeNode node
	*/
	static LinkedList<BinaryTreeNode> levelZero(BinaryTreeNode tNode) 
	{
		LinkedList<BinaryTreeNode> list = new LinkedList<BinaryTreeNode>();
		list.push(tNode);
		return list;
	}
	/**
	*Given a list of nodes, obtain the next level.
	*@param LinkedList<BinaryTreeNode> level
	*/
	static LinkedList<BinaryTreeNode> nextLevel(LinkedList<BinaryTreeNode> level) 
	{
		LinkedList<BinaryTreeNode> list = new LinkedList<BinaryTreeNode>();

		for (BinaryTreeNode node : level) {
			if (node.hasLeft()) list.add(node.getLeft());
			else list.add(BinaryTreeNode.EMPTY_NODE);

			if (node.hasRight()) list.add(node.getRight());
			else list.add(BinaryTreeNode.EMPTY_NODE);
		}

		return list;
	}
	/**
	*Determine whether one tree node structure is similar (has the same structure) as another.
	*@param BinaryTreeNode treeStructOne, BinaryTreeNode treeStructTwo
	*/
	static boolean similar(BinaryTreeNode treeStructOne, BinaryTreeNode treeStructTwo) 
	{
		//base case
		if (isPlaceHolder(treeStructOne) || isPlaceHolder(treeStructTwo)) {
			if (!isPlaceHolder(treeStructOne) || !isPlaceHolder(treeStructTwo)) return false;
			else return true;
		}
		//check right
		if (treeStructOne.hasRight()) {
			if (!treeStructTwo.hasRight()) return false;
			if (!similar(treeStructOne.getRight(), treeStructTwo.getRight())) return false;
		}

		//check left
		if (treeStructOne.hasLeft()) {
			if (!treeStructTwo.hasLeft()) return false;
			if (!similar(treeStructOne.getLeft(), treeStructTwo.getLeft())) return false;
		}

		return true;
	}
}