import java.util.ArrayDeque;
import java.io.*;
/**
 * Simple binary tree node for integer values.
 * 
 * @author Sinead Urisohn
 * @version 1/03/2015
 */
public class SimpleTreeWriterImpl implements SimpleTreeWriter{
	PrintStream output;

	//default constructor
	public SimpleTreeWriterImpl(PrintStream output)
	{
		setDestination(output);
	}
	/**
	*Set the PrintStream object to be used for output.
	*@param destination PrintStream output
	*/
	//
	public void setDestination(PrintStream output)
	{
		this.output = output;
	}
	/**
	* Print a textual representation of the given tree structure
	* to the PrintStream object specified with setDestination.
	*@param BinaryTreeNode node
	*/

	public void write(BinaryTreeNode node)
	{
		int h=node.getHeight();
		int numOfNodes=0;
		int space=0;
		
		int maxWidth = (""+node.getLargest()).length();
		String placeholderSpace="";
		// increment placeholder space
			for(int i=1;i<=maxWidth;i++)
			{
				placeholderSpace+=" ";
			}

		BinaryTreeNode placeholder = BinaryTreeNode.EMPTY_NODE;//if tree is incomplete
		ArrayDeque q = new ArrayDeque();
		q.addLast(node);



		while(q.size() > 0) {
			BinaryTreeNode curNode = (BinaryTreeNode) q.removeFirst();

			numOfNodes++;
			int l=(int)(Math.log(numOfNodes)/Math.log(2));
			if (l > h) break;
			int firstNodeInLevel=(int)Math.pow(2,l);

			//formula for spacing before first node
			if (numOfNodes==firstNodeInLevel)
			{
				output.println();	
				space= ((int)Math.pow(2,h-l)-1)/2;
			}
			else
			{
				space= ((int)Math.pow(2,h-l)-1);
			}

			String spacetoprint="";
			// increment grid block size
			for(int i=1;i<=space;i++)
			{
				spacetoprint+=placeholderSpace;
			}

		
			if(curNode.hasLeft())//if node is null
			{
				q.addLast(curNode.getLeft());
			}
			else
			{
				q.addLast(placeholder);
			}

			if(curNode.hasRight())
			{
				q.addLast(curNode.getRight());
			}
			else
			{
				q.addLast(placeholder);
			}

			if(curNode!=BinaryTreeNode.EMPTY_NODE)
			{
				output.print(spacetoprint+makeSameTextWidth(curNode.getItem(),maxWidth));
			}
			else
			{
				output.print(spacetoprint+placeholderSpace);	
			}
		}
	}
	/**
	*@param number in node
	*right justifies the number to make uniform blocks
	*/
	public String makeSameTextWidth(int number, int width)
	{
		String textNumber = ""+number;
		int numWidth= textNumber.length();
		int i = width-numWidth;
		for(; i>0;i--)
		{
			textNumber = ' '+textNumber;
		}
		return textNumber;
	}
}