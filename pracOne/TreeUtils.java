import java.util.*;

public class TreeUtils{
	/**
	*Determine whether node is a place holder
	*/
	public static boolean isPlaceHolder(BinaryTreeNode node) 
	{
		return node == BinaryTreeNode.EMPTY_NODE;
	}
	/**
	*Obtain a list containing the root node of the given structure i.e.
	*/
	static List<BinaryTreeNode> levelZero(BinaryTreeNode tNode) 
	{
		LinkedList<BinaryTreeNode> list = new LinkedList<BinaryTreeNode>();
		list.push(tNode);
		return list;
	}
	/**
	*Given a list of nodes, obtain the next level.
	*/
	static List<BinaryTreeNode> nextLevel(List<BinaryTreeNode> level) 
	{
		LinkedList<BinaryTreeNode> list = new LinkedList<BinaryTreeNode>();

		for (BinaryTreeNode node : level) {
			if (node.hasLeft()) list.push(node.getLeft());
			else list.push(BinaryTreeNode.EMPTY_NODE);

			if (node.hasRight()) list.push(node.getRight());
			else list.push(BinaryTreeNode.EMPTY_NODE);
		}

		return list;
	}
	/**
	*Determine whether one tree node structure is similar (has the same structure) as another.
	*/
	static boolean similar(BinaryTreeNode treeStructOne, BinaryTreeNode treeStructTwo) 
	{
		if (isPlaceHolder(treeStructOne) || isPlaceHolder(treeStructTwo)) {
			if (!isPlaceHolder(treeStructOne) || !isPlaceHolder(treeStructTwo)) return false;
			else return true;
		}

		if (treeStructOne.hasRight()) {
			if (!treeStructTwo.hasRight()) return false;
			if (!similar(treeStructOne.getRight(), treeStructTwo.getRight())) return false;
		}

		if (treeStructOne.hasLeft()) {
			if (!treeStructTwo.hasLeft()) return false;
			if (!similar(treeStructOne.getLeft(), treeStructTwo.getLeft())) return false;
		}

		return true;
	}
}