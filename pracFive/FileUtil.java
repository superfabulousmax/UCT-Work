import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
/**
 * Module containing utility methods.
 * 
 * @author Sinead Urisohn
 * @version 02/05/2015
 */
public class FileUtil {
	//For caching purposes for performance testing to increase speed
	static String lastFileRead;
	static List<String> lastFileContents;


	private FileUtil() {}

	private static List<String> fileContent;//for search performance testing

	//getters and setters for fileContent
	public static List<String> getFileContent() {
		return fileContent;
	}
	public static void setFileContent(List<String> fileContent) {
		FileUtil.fileContent = fileContent;
	}
	/**
	 * Load the dictionary with the word definitions in the given file. <br>
	 * 
	 * &lt;lexicion&gt; ::= {<entry>} <br>  
	 * &lt;entry&gt; ::=  &lt;word type&gt; ‘:’ &lt;word&gt; ‘:’ [&lt;description&gt;] <br> 
	 * &lt;word type&gt; ::= ‘ a’|’v’|’n’ <br>
	 * &lt;word&gt; ::=  {&lt;letter&gt;}+ <br>
	 * &lt;description&gt; ::=  {&lt;character&gt;} <br>
	 * <br>
	 * The lexicion contains 0 or more entries. <br>
	 * An entry consists of word type followed by a colon, followed by the word, followed by a colon, optionally followed by a description. <br> 
	 * The word type is represented by a single character; either ‘a’, ‘v’, or ‘n’. <br>
	 * A word consists of a sequence of one or more letters. <br>
	 * A description consists of 1 or more characters (generally, it’s a word phrase). <br>
	 */
	public static void load(Dictionary dictionary, String filename) throws FileNotFoundException, IOException ,Exception{
		if (filename.equals(lastFileRead)) {
			fileContent = lastFileContents;
		} else {
			fileContent = new ArrayList<String>();//list of definitions to put into hash table
			File file= new File(filename);
			Scanner sc = new Scanner(file).useDelimiter("\n");

			while(sc.hasNext())
			{
				String line = sc.nextLine();
				fileContent.add(line);
			}
			sc.close();

			lastFileRead = filename;
			lastFileContents = fileContent;
		}
		//put filecontent into hash table
		for(String fileLine: fileContent)
		{
			//split string into parts of defintion
			String [] partsOfDefinition = fileLine.split(":",3);
			String type=partsOfDefinition[0].trim();
			String word= partsOfDefinition[1].trim();

			String description = partsOfDefinition[2].trim();

			Definition def = new Definition(WordType.toWordType(type), description);
			dictionary.insert(word, def);
		}

		// System.out.println(dictionary.size());
		//SCHashtable.debug_print((SCHashtable)dictionary);
		//LPHashtable.debug_print((LPHashtable)dictionary);
	}
	


}
