import java.util.Scanner;
public class SimpleTest{
	
	public static void main(String []args)
	{
		 System.out.print("Enter a comma separated sequence of node values: ");
        Scanner scanner = new Scanner(System.in);
        scanner = new Scanner(scanner.nextLine()).useDelimiter("\\s*,\\s*");
        SimpleTreeWriterImpl obj = new SimpleTreeWriterImpl(System.out);
        BinaryTreeNode node = new BinaryTreeNode();

        SimpleBST tree = new  SimpleBST();
        
        while (scanner.hasNextInt() ){
            tree.insert(scanner.nextInt());
        }
        System.out.println(obj.makeSameTextWidth(3,tree.getRoot())+"\n"+tree.getRoot().getLargest());

	}
}