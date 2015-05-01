import java.util.ArrayList;
import java.util.List;
/**
 * Implementation of Entry ADT
 * @author sinead urison
 * @version 4/29/2015
 *
 */

public class HashEntry implements Entry {
	private ArrayList<Definition> definitions=null; //definition objects
	private String word=null; //word in dictionary
	
	
	//parameterised constructor
	 public HashEntry(ArrayList<Definition> definitions, String word) {
		
		this.definitions = definitions;
		this.word = word;
		
	}

	/**
     * Obtain the word defined in this entry.
     */
	public String getWord() {
		
		return word;
	}

	/**
     * Obtain the definitions for the word defined in this entry.
     */
	public List<Definition> getDefinitions() {
		
		return definitions;
	}


    /**
     * Add a definition consisting of the given word type and word description.
     */
	public void addDefinition(WordType wordType, String description) {
		definitions.add(new Definition(wordType, description));
		
	}

	/**
     * Add the given definition.
     */
	public void addDefinition(Definition definition) {
		definitions.add(definition);
		
	}

	 /**
     * Determine whether this entry is for the given word.
     */
	public boolean isEntryFor(String word) {
		
		return this.word.equals(word);
	}
	/**
	 * Suitable toString method for LPHashtable class printing method
	 */
	public String toString()
	{
		String output="[";
		for(int i =0;i<definitions.size();i++)
		{
			Definition definition = definitions.get(i);
			output+="("+definition.getType()+") "+definition.getDescription();
			if(i<=definitions.size()-2)
				output+=", ";
			else output+="]";
			
		}
		return output;
	}

}
