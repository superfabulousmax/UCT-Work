import java.util.Scanner;
/**
*Main class that does all the coordination of prints tree and tests similarity
*@author
*@version 01/03/2015
*/
public class TreeMain{
	
	public static void main(String []args)
	{
		
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");//use newline delimiter
        System.out.print("Enter a comma separated list of numbers for tree one: ");
        String line1=scanner.nextLine();
        Scanner numbersOfLine1 = new Scanner(line1).useDelimiter(",");
        System.out.print("Enter a comma separated list of numbers for tree two: ");
        String line2=scanner.nextLine();
        Scanner numbersOfLine2 = new Scanner(line2).useDelimiter(",");
        //System.out.println(line1+"\n"+line2);
        SimpleBST tree1 = new  SimpleBST();
        
       // System.out.println((int)numbersOfLine1.nextInt() instanceof Integer);
        while (numbersOfLine1.hasNextInt()){
            tree1.insert(numbersOfLine1.nextInt());
        }
        SimpleBST tree2 = new  SimpleBST();

         while (numbersOfLine2.hasNextInt() ){
            tree2.insert(numbersOfLine2.nextInt());
        }
        System.out.print(tree1.similar(tree2));
        SimpleBST.print(tree1, new SimpleTreeWriterImpl(System.out));
        
         SimpleBST.print(tree2, new SimpleTreeWriterImpl(System.out));

	}
}