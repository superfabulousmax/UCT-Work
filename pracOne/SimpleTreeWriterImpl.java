import java.util.LinkedList;
import java.io.*;
/**
 * Implements binary tree node print stream
 * 
 * @author Sinead Urisohn
 * @version 1/03/2015
 */
public class SimpleTreeWriterImpl implements SimpleTreeWriter{
	PrintStream output;//print stream object

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
		BinaryTreeNode curNode;
		int numOfNodes=0;
		int space=0;
		int l=0;
		int firstNodeInLevel=0;
		int maxWidth = (""+node.getLargest()).length();
		String placeholderSpace="";//spaces for empty nodes
		// increment placeholder space
			for(int i=1;i<=maxWidth;i++)
			{
				placeholderSpace+=" ";
			}
		LinkedList <BinaryTreeNode>curNodeLevel = TreeUtils.levelZero(node);//removes first LinkList level
		for( int j =0;j<h;j++)//loop through levels
		{

			

			numOfNodes+=curNodeLevel.size();
			
		 	firstNodeInLevel=(int)Math.pow(2,j)-1;

		 	
			for(int m=0; m<curNodeLevel.size();m++)
		 	{
		 		 curNode = (BinaryTreeNode) curNodeLevel.get(m);//current node
			 		 //formula for spacing before first node
				if (numOfNodes-curNodeLevel.size()+m==firstNodeInLevel)//
				{

					
					space= ((int)Math.pow(2,h-j)-1)/2;//initial spaces
				}
				else
				{
					space= ((int)Math.pow(2,h-j)-1);//spaces between nodes
				}
				

				String spacetoprint="";
				// increment grid block size
				for(int i=1;i<=space;i++)
				{
					spacetoprint+=placeholderSpace;//each grid block must have space sizes for largest digit
				}

				if(!TreeUtils.isPlaceHolder(curNode))
				{
					output.print(spacetoprint+makeSameTextWidth(curNode.getItem(),maxWidth));

				}
				else
				{
					
					output.print(spacetoprint+placeholderSpace);	
				}
			}
			output.println();//new line
			curNodeLevel=TreeUtils.nextLevel(curNodeLevel);//change levels
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