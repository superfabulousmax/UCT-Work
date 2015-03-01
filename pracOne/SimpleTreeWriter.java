import java.io.PrintStream;
/**
 * Abstract definition of an object for printing a simple binary tree structure. 
 * 
 * @author Sinead (your name) 
 * @version 1 March 2015 (a version number or a date)
 */
public interface SimpleTreeWriter{

    public void setDestination(PrintStream stream);
    
    public void write(BinaryTreeNode tree); 
}
