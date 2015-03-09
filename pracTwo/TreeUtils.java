import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Utility procedures for binary tree structures.
 * 
 * @author Stephan Jamieson
 * @version 25/2/2015
 */
public class TreeUtils {
        
    /**
     * Obtain the height value of the given node.
     * @return 0 if <code>node==null</code>, otherwise <code>node.getHeight()</code>.
     */
    public static int height(AVLTreeNode node) {
        if (node==null) {
            return 0;
        }
        else {
            return node.getHeight();
        }
    }

    /** 
     * Determine whether the given tree structure contains the given key.
     *@param key is the key to search for
     *@param node is the node to match the key with
     *@return boolean value corresponding to if node contains key
     */
    public static boolean contains(AVLTreeNode node, Integer key) {
        // Your code here.
        while(node != null)
        {
            if(key.compareTo(node.getKey())<0)
                node =node.getLeft();
            else if(key.compareTo(node.getKey())>0)
                node =node.getRight();
            else //found
                return true;

        }
        //else not found
        return false;

    } 

    /**
     * Recursive implementation of insert on an AVLTreeNode structure.
     *@param node- root node
     *@param key
     *@return a root node that may or may not be the one that was passed in.
     */
    public static AVLTreeNode insert(AVLTreeNode node, Integer key) {
        // Your code here
        //if node is null
        if(node==null)
            node= new AVLTreeNode(key);
        else if(key==node.getKey())
            //do nothing
        //else if key less than key at node
        //insert left
        else if(key<node.getKey())
        {
            node = insert(node.getLeft(),key);
            //check for and repair imbalance
            if(node.getBalanceFactor()==2)
            {
                node = rebalanceLeft(node);
            }
            //else if(node.getBalanceFactor()<-1)

        }
        else
        {
            //key greater so insert right
            node = insert(node.getRight(),key);
            //check for imbalance and repair it
            if(node.getBalanceFactor()==-2)
            {
                node = rebalanceRight(node);
            }

        }

        //recalculate the node's height
        node.setHeight(heightOfAVL(node));//set node height to max of children+1
        


        return node;

    }
    /**
     * Obtain the height of this tree structure.
     *@param node
     *@return height of max of children +1
     */
    public int heightOfAVL(AVLTreeNode node)
    {
        if (node.hasLeft() && node.hasRight()) {
            return Math.max(node.getLeft().getHeight(), node.getRight().getHeight())+1;
        }
        else if (node.hasLeft()) {
            return node.getLeft().getHeight()+1;
        }
        else if (node.hasRight()) {
            return node.getRight().getHeight()+1;
        }
        else {
            return 1;
        }
    }

    public static AVLTreeNode rebalanceLeft(AVLTreeNode node , Integer key)
    {
        if(key<node.getKey())
        {
            //case  1 applies
            return rotateWithLeftChild(node);

        }
        else
        {
            //case 2 applies
            return doubleRotateWithLeftChild(node);
        }
    }

    public static AVLTreeNode rebalanceRight(AVLTreeNode node , Integer key)
    {
        if(key>node.getKey())
        {
            //case  4 applies
            return rotateWithRightChild(node);

        }
        else
        {
            //case 3 applies
            return doubleRotateWithRightChild(node);
        }
    }
    
    /**
     * Rotate binary tree node with left child.
     * This is a single rotation for case 1.
     */
    public static AVLTreeNode rotateWithLeftChild( AVLTreeNode k2 )
    {
        // Your code here
        AVLTreeNode k1 = k2.getLeft();
        k2.setLeft(k1.getRight());
        k1.setRight(k2);
        return k1;
    }

    /**
     * Rotate binary tree node with right child.
     * This is a single rotation for case 4.
     */
    public static AVLTreeNode rotateWithRightChild( AVLTreeNode k1 )
    {
        // Your code here
        AVLTreeNode k2 = k1.getRight();
        k1.setRight(k2.getLeft());
        k2.setLeft(k1);
        return k2;_
    }

    /**
     * Double rotate binary tree node: first rotate k3's left child
     * with its right child; then rotate node k3 with the new left child.
     * This is a double rotation for case 2.
     */
    public static AVLTreeNode doubleRotateWithLeftChild( AVLTreeNode k3 )
    {
        // Your code here.
        k3.setLeft(rotateWithRightChild(k3.getLeft()));
        return rotateWithLeftChild(k3);
    }

    /**
     * Double rotate binary tree node: first rotate k1's right child
     * with its left child; then rotate node k1 with the new right child.
     * This is a double rotation for case 3.
     */
    public static AVLTreeNode doubleRotateWithRightChild( AVLTreeNode k1 )
    {
        // Your code here.
        k1.setRight(rotateWithLeftChild(k1.getRight()));
        return rotateWithRightChild(k1);0
    }

    
    /**
     * Obtain a list containing the root node of the given structure i.e. tNode itself.
     */
    public static List<AVLTreeNode> levelZero(AVLTreeNode tNode) {
        List<AVLTreeNode> level = new ArrayList<AVLTreeNode>();
        level.add(tNode);
        return level;
    }
    
    
    /**
     * Given a list of nodes, obtain the next level. 
     * 
     * <p>
     * If the tree structure is incomplete, <code>AVLTreeNode.EMPTY_NODE</code> is inserted as a place holder for each
     * missing node.
     * </p>
     */
    public static List<AVLTreeNode> nextLevel(List<AVLTreeNode> level) {
        List<AVLTreeNode> nextLevel = new ArrayList<AVLTreeNode>(); 
        
        for (AVLTreeNode node : level) {
            nextLevel.add(node.hasLeft() ? node.getLeft() : AVLTreeNode.EMPTY_NODE); 
            nextLevel.add(node.hasRight() ? node.getRight() : AVLTreeNode.EMPTY_NODE);
        }
        return nextLevel;
    }
    
    /**
     * Determine whether node is a place holder i.e. <code>node==AVLTreeNode.EMPTY_NODE</code>
     */
    public static boolean isPlaceHolder(AVLTreeNode node) {
        return node==AVLTreeNode.EMPTY_NODE;
    }
    
}
