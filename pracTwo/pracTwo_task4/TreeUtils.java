import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



/**
 * Utility procedures for binary tree structures.
 * 
 * @author Sinead Urisohn
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
     * finds data about any letter key
     */
    public static String find(AVLTreeNode node,String letter)
    {

        String out="";//output to return
        Integer key = AVLTree.calculateStringKey(letter);

        HashSet<String> uniqueWords = new HashSet<String>();
        ArrayList<String> wordsToAddToList =new ArrayList<String>();//an array to add words to list separated by commas nicely

        while(node!=null)
        {
            int numberOfWords = node.getNumberOfItems();
            if(key.compareTo(node.getKey())<0)
                node =node.getLeft();
            else if(key.compareTo(node.getKey())>0)
                node =node.getRight();
            else{ //found


                out+="("+numberOfWords+")(";

                //get unique words to print
                for(int i =0;i<numberOfWords;i++)
                {
                    uniqueWords.add(node.getGroupedLetters().get(i));

                }

                //get words in list separated by commas
                for(String word: uniqueWords)
                {
                    wordsToAddToList.add(word);
                }
                for(int i =0;i<wordsToAddToList.size()-1;i++){
                    out+=wordsToAddToList.get(i)+", ";
                }
                out+=wordsToAddToList.get(wordsToAddToList.size()-1)+")";

                return out;
            }
        }

        return "No entry found";
    }


    
    /** 
     * Determine whether the given tree structure contains the String value
     *@param key is the key to search for
     *@param node is the node to match the key with
     *@return boolean value corresponding to if node contains key
     */
    public static boolean contains(AVLTreeNode node, String value) {
        // Your code here.
    	Integer keyOfValue =AVLTree.calculateStringKey(value);
        while(node != null)
        {
            if(keyOfValue.compareTo(node.getKey())<0)
                node =node.getLeft();
            else if(keyOfValue.compareTo(node.getKey())>0)
                node =node.getRight();
            else //found key now determine whether string is same
                return node.getValue().equals(value);

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
    public static AVLTreeNode insert(AVLTreeNode node, String value) {

        int keyOfValue=AVLTree.calculateStringKey(value);
        // Your code here
        //if node is null
        if(node==null||node == AVLTreeNode.EMPTY_NODE){

            node= new AVLTreeNode(keyOfValue,value);
        }
        else if(keyOfValue==node.getKey()){
            //add this to arrayList.....
            node.addGroupedLetters(value);

        }

        //else if key less than key at node
        //insert left
        else if(keyOfValue<node.getKey())
        {
            if(node.hasLeft()){
                node.setLeft(insert(node.getLeft(),value));

            }
            else{

                node.setLeft(new AVLTreeNode(keyOfValue,value));
            }

            //check for and repair imbalance
            if(node.getBalanceFactor()==2)
            {
                return rebalanceLeft(node,keyOfValue);
            }
            else if(node.getBalanceFactor()==-2)
            {

                return rebalanceRight(node,keyOfValue);
            }
            //else if(node.getBalanceFactor()<-1)

        }
        else if(keyOfValue>node.getKey())
        {
            //key greater so insert right

            if(node.hasRight())
            {

                node.setRight(insert(node.getRight(),value));
            }
            else{
                node.setRight(new AVLTreeNode(keyOfValue,value));
            }

            //check for imbalance and repair it
            if(node.getBalanceFactor()==-2)
            {
                return rebalanceRight(node,keyOfValue);
            }
            else if(node.getBalanceFactor()==2)
                return rebalanceLeft(node,keyOfValue);

        }

        //recalculate the node's height
        node.setHeight(node.getHeight());//set node height to max of children+1



        return node;

    }


    /**
     * Deletes a node from tree structure
     *@Param node and key 
     *@return node with new tree structure
     */
    public static AVLTreeNode delete(AVLTreeNode node, Integer key){
    	//The key to delete does not exist
    	if(node==null)
    		return null;
    	//if in left subtree
    	if(key.compareTo(node.getKey())<0)
    	{
    		node.setLeft(delete(node.getLeft(),key));
    		//rebalance tree
    		
    		if(node.getBalanceFactor()==2)
            {
            	
                return rebalanceLeftDelete(node);
            }
            else if(node.getBalanceFactor()==-2)
            {
            	
            	return rebalanceRightDelete(node);//(node,node.getKey());
            }
    		
    				
    	}
    	//go right if in right subtreee
    	else if(key.compareTo(node.getKey())>0)
    	{
    		node.setRight(delete(node.getRight(),key));
    		if(node.getBalanceFactor()==2)
            {
            	
                return rebalanceLeftDelete(node);
            }
            else if(node.getBalanceFactor()==-2)
            {
            	
            	return rebalanceRightDelete(node);//(node,node.getKey());
            }
    		
    	}
    	//otherwise found and remove node
    	else{
    		//if no childs
    		if(node.getLeft()==null&node.getRight()==null)
    		{
    			return null;
    		}
    		//1 child
    		if(node.getLeft()==null)
    		{
    			return node.getRight();//connect right child to parent
    		}
    		if(node.getRight()==null)
    		{
    			return node.getLeft();
    		}
    		//else 2 childs
    		List successorInfo = inOrderSuccessor(node.getRight());//information to replace parent key to be deleted
    		Integer inorderKey=(Integer)successorInfo.get(0);
    		String value = (String)successorInfo.get(1);
    		node.setValue(value);//change node String value
    		node.setKey(inorderKey);//change key
    		node.setRight(delete(node.getRight(), inorderKey));
    		//check balance
    		//check for and repair imbalance
    		if(node.getBalanceFactor()==2)
            {
            	
                return rebalanceLeftDelete(node);
            }
            else if(node.getBalanceFactor()==-2)
            {
            	
            	return rebalanceRightDelete(node);//(node,node.getKey());
            }
    	}
    	node.setHeight(node.getHeight());//adjust height
    	return node;
    	
    }
    
    /**
     * Obtain in-order successor
     * @param node of tree to be searched in
     *@return the smallest key for tree structure
     */
    private static List inOrderSuccessor(AVLTreeNode node)
    {
    	List successorInfo = new ArrayList();
    	if(node.getLeft()==null)
    	{
    		successorInfo.add(node.getKey());
    		successorInfo.add(node.getValue());
    		System.out.println(successorInfo);
    		return successorInfo;
    	}
    	return inOrderSuccessor(node.getLeft());
    }
    

    public static AVLTreeNode rebalanceLeft(AVLTreeNode node , Integer key)
    {
    	SimpleTreeWriterImpl obj = new SimpleTreeWriterImpl(System.out);
    	System.out.println("treeee: ");
    	obj.write(node);
        if(key<node.getLeft().getKey())
        {
            //case  1 applies
        	System.out.println("hello left left");
            return rotateWithLeftChild(node);

        }
        else
        {
        	System.out.println("hello doublerotatewithleftchild");
            //case 2 applies
            return doubleRotateWithLeftChild(node);
        }
    }
    
    public static AVLTreeNode rebalanceLeftDelete(AVLTreeNode node)
    {
    	SimpleTreeWriterImpl obj = new SimpleTreeWriterImpl(System.out);
    	System.out.println("treeee: ");
    	obj.write(node);
    	if(node.getRight()==null){return rotateWithLeftChild(node);}
        if(node.getLeft().getHeight()>node.getRight().getHeight())
        {
            //case  1 applies
        	System.out.println("hello left left");
            return rotateWithLeftChild(node);

        }
        else
        {
        	System.out.println("hello doublerotatewithleftchild");
            //case 2 applies
            return doubleRotateWithLeftChild(node);
        }
    }

    public static AVLTreeNode rebalanceRight(AVLTreeNode node , Integer key)
    {
    	SimpleTreeWriterImpl obj = new SimpleTreeWriterImpl(System.out);
    	System.out.println("TREEEE: "+node);
    	obj.write(node);
        if(key>node.getRight().getKey())
        {
            //case  4 applies
        	System.out.println("hello right right");
        	System.out.println("key "+key+"node key "+node.getKey());
            return rotateWithRightChild(node);

        }
        else
        {
            //case 3 applies
        	System.out.println("hello doublerotatewithrightchild");
            return doubleRotateWithRightChild(node);
        }
    }
    
    public static AVLTreeNode rebalanceRightDelete(AVLTreeNode node)
    {
    	SimpleTreeWriterImpl obj = new SimpleTreeWriterImpl(System.out);
    	System.out.println("TREEEE: "+node);
    	obj.write(node);
    	if(node.getLeft()==null){return rotateWithRightChild(node);}
        if(node.getRight().getHeight()>node.getLeft().getHeight())
        {
            //case  4 applies
        
            return rotateWithRightChild(node);

        }
        else
        {
            //case 3 applies
        	System.out.println("hello doublerotatewithrightchild");
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
        return k2;
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
        return rotateWithRightChild(k1);
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
