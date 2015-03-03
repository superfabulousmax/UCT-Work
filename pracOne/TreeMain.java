import java.util.Scanner;
import java.io.*;
/**
*Main class that does all the coordination of prints tree and tests similarity
*@author Sinead Urisohn
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
        
        SimpleBST tree1 = new  SimpleBST();
        
       // insert into trees
        while (numbersOfLine1.hasNextInt()){
            tree1.insert(numbersOfLine1.nextInt());
        }
        SimpleBST tree2 = new  SimpleBST();

         while (numbersOfLine2.hasNextInt() ){
            tree2.insert(numbersOfLine2.nextInt());
        }
        System.out.println("Tree one:");
        SimpleBST.print(tree1, new SimpleTreeWriterImpl(System.out));
        System.out.println("Tree two:");
        SimpleBST.print(tree2, new SimpleTreeWriterImpl(System.out));

        //compare similarity
        if(tree1.similar(tree2))
            System.out.println("\nThe trees are similar");
        else
            System.out.println("\nThe trees are not similar");

        //write trees to textfiles
        try{
        SimpleBST.print(tree1, new SimpleTreeWriterImpl(new PrintStream(new File("T1.out"))));
        SimpleBST.print(tree2, new SimpleTreeWriterImpl(new PrintStream(new File("T2.out"))));
        }catch(FileNotFoundException e)
        {
            System.out.println(e);
        }
    }
}