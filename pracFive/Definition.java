
/**
 * A word definition has a word type and word description.
 * 
 * @author Sinead Urisohn
 * @version 01/05/2015
 */
public class Definition {
    private WordType wordType;
    private String description;
    
    /**
     * Create a definition with the given word type and description.
     */
    public Definition(WordType wordType, String description) {
        this.wordType = wordType;
        this.description = description;
    }
    
    /**
     * Obtain the word type.
     */
    public WordType getType() { return wordType; }
    
    /**
     * Obtain the description.
     */
    public String getDescription() { return description; }
    
    /**
     * Return a string representation of this definition.
     */
    public String toString() { return "("+wordType+") "+description; }
    
    /**
     * return if definition is equal to other definition object
     */
    public boolean equals(Definition other)
    {
    	return this.wordType==other.wordType&&this.description==other.description;
    }
}
