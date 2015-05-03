import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Subclass of existing implementation of Entry that implements ChainedEntry interface
 * @author sinead urisohn
 * @version 02/05/2015
 *
 */
public class ChainedHashEntry  extends HashEntry implements ChainedEntry{
	private ChainedEntry node;
	//constructor
	public ChainedHashEntry(ArrayList<Definition> definitions, String word) {
		super(definitions, word);
		
	}

	/**
     * Obtain the word defined in this entry.
     */
	public String getWord() {
		
		return super.getWord();
	}

	/**
     * Obtain the definitions for the word defined in this entry.
     */
	public List<Definition> getDefinitions() {
		
		return super.getDefinitions();
	}


    /**
     * Add a definition consisting of the given word type and word description.
     */
	public void addDefinition(WordType wordType, String description) {
		super.getDefinitions().add(new Definition(wordType, description));
		
	}

	/**
     * Add the given definition.
     */
	public void addDefinition(Definition definition) {
		super.getDefinitions().add(definition);
		
	}

	 /**
     * Determine whether this entry is for the given word.
     */
	public boolean isEntryFor(String word) {
		
		return super.getWord().equals(word);
	}
	/**
	 * Suitable toString method for LPHashtable class printing method
	 */
	public String toString()
	{
		String output="[";
		for(int i =0;i<super.getDefinitions().size();i++)
		{
			Definition definition = super.getDefinitions().get(i);
			output+="("+definition.getType()+") "+definition.getDescription();
			if(i<=super.getDefinitions().size()-2)
				output+=", ";
			else output+="]";
			
		}
		return output;
	}

	//getter and setters
	public ChainedEntry getNext() {
		
		return node;
	}
	
	public void setNode(ChainedEntry node) {
		this.node = node;
	}
	

}
