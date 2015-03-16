import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
/**
 * Command line program interface.
 * 
 * @author Stephan Jamieson
 * @version 4/3/2015
 */
public class TreeUI {

	private AVLTree target;

	private Map<String, Command> commands;


	public TreeUI() {
		commands = new HashMap<String, Command>();
		commands.put("new", new New());
		commands.put("insert", new Insert());
		commands.put("delete", new Delete());
		commands.put("contains", new Contains());
		commands.put("print", new Print());
		commands.put("write", new Write());
		commands.put("find", new Find());
		commands.put("help", new Help());
		commands.put("quit", new Quit());
		target = new AVLTree();
	}

	public void run() {
		Scanner input = new Scanner(System.in);
		commands.get("help").execute("");

		while (true) {
			System.out.print(">");
			Scanner line = new Scanner(input.nextLine());

			String keyword = (line.hasNext() ? line.next().trim().toLowerCase() : "");
			String argument = (line.hasNext() ? line.next() : "");

			if (commands.containsKey(keyword)) {
                try{
				commands.get(keyword).execute(argument);
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }
			}
			else {
				System.out.println("Sorry, that command is not recognised. Type 'help' for assistance.");
			}
		}
	}


	public static void main(String[] args) {
		(new TreeUI()).run();
	}

	private abstract class Command {
		public abstract String help();

		public abstract void execute(String argument) throws IllegalArgumentException;

	}

	private class New extends Command {
		public String help() { return "new"; }

		public void execute(String argument) throws IllegalArgumentException {
			//create new AVL Tree replacing any previous one 
			target =new AVLTree();
		}
	}
	private class Insert extends Command {
		public String help() { return "insert <key value>"; }

		public void execute(String argument) throws IllegalArgumentException {
			try {
				if(argument==""&argument==null) throw new IllegalArgumentException("Insert "+argument+" : invalid: Form should be insert <key value>");
				if(Character.isLetter(argument.charAt(0)))
					target.insert(argument);
				
			}
			catch (NumberFormatException numFormE) {
				throw new IllegalArgumentException("Insert "+argument+" : argument not an integer.");
			}    
			
		}
	}

	private class Delete extends Command {
		public String help() { return "delete <key value>"; }

		public void execute(String argument) throws IllegalArgumentException {
			//try {
				if(Character.isLetter(argument.charAt(0))){
					
					if( target.contains(argument)==false)
					{
						System.out.println("Error! "+argument+" cannot be found in tree.");
					}
					else target.delete(argument);
					
				}
				else 
					throw new IllegalArgumentException("Delete "+argument+" : argument not a string.");
			//}
//			catch (IllegalArgumentException numFormE) {
//				throw new IllegalArgumentException("Delete "+argument+" : argument not a alphabetical String.");
//			}    
			
		}
	}
	private class Print extends Command {
		public String help() { return "print"; }

		public void execute(String argument) throws IllegalArgumentException {
			//print
			target.print(System.out);
		}
	}

	private class Find extends Command {
		public String help() { return "find <letter of key>"; }

		public void execute(String argument) throws IllegalArgumentException {
			if(argument!=null&argument.length()==1&Character.isLetter(argument.charAt(0)))
			{
				System.out.println(target.find(argument));
			}
			else
				throw new IllegalArgumentException("Insert "+argument+" : not a letter");
			
		}
		
	}
	private class Write extends Command {
		public String help() { return "write <file name>"; }

		public void execute(String argument) throws IllegalArgumentException {
			//write
			try{
				target.print(new PrintStream(new File(argument)));
			}catch(FileNotFoundException e)
			{
				System.out.println(e);
			}
		}
	}


	private class Contains extends Command {
		public String help() { return "contains <key value>"; }

		public void execute(String argument) throws IllegalArgumentException {
			try {
				String response="";
				if(Character.isLetter(argument.charAt(0)))
					response = target.contains(argument) ? "Yes" : "No";
				
				System.out.println(response);
			}
			catch (NumberFormatException numFormE) {
				throw new IllegalArgumentException("Insert "+argument+" : argument not an integer.");
			}    
			catch(IllegalArgumentException e)
			{
				throw new IllegalArgumentException("Insert "+argument+" : argument not alpahabetical");
			}
		}
	}

	private class Help extends Command {
		public String help() { return "help"; }

		public void execute(String argument) throws IllegalArgumentException {

			Iterator<String> keywordIter = commands.keySet().iterator();

			if (keywordIter.hasNext()) {
				System.out.print("Commands: "+commands.get(keywordIter.next()).help());
				while (keywordIter.hasNext()) {
					System.out.print(", "+commands.get(keywordIter.next()).help());
				}
				System.out.println(".");
			}
			else {
				System.out.println("No commands installed.");
			}
		}
	}

	private class Quit extends Command {
		public String help() { return "quit"; }

		public void execute(String argument) throws IllegalArgumentException {
			System.exit(0);
		}
	}


}
