import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;
/**
 * class that reads from graph textfile
 * populates graph
 * and returns result of shortest path from start vertex through all vertices back to start vertex.
 * @author sinead urisohn
 * @version 12/05/2015
 *
 */

public class Main {

	
	public static void main(String[] args) {
		Graph g = new Graph( );
		
		try
		{   	
			FileReader file = new FileReader("Graph1.txt");
			Scanner textFileWithGraph = new Scanner( file );

			// Read the edges and insert
			String line;
			while( textFileWithGraph.hasNextLine( ) )
			{
				line = textFileWithGraph.nextLine( );
				StringTokenizer st = new StringTokenizer( line );

				try
				{
					if( st.countTokens( ) != 3 )
					{
						System.err.println( "Skipping ill-formatted line " + line );
						continue;
					}
					String source  = st.nextToken( );
					String dest    = st.nextToken( );
					int    cost    = Integer.parseInt( st.nextToken( ) );
					g.addEdge( source, dest, cost );
					g.addEdge(dest,source,cost);//make undirected graph
				}
				catch( NumberFormatException e )
				{ System.err.println( "Skipping ill-formatted line " + line ); }
			}
			
			textFileWithGraph.close();
			System.out.println(g.getMinCostPath("A"));
		}
		catch( IOException e )
		{ 
			System.err.println( e ); 
		}


	}

}
