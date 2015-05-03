import java.util.ArrayList;
import java.util.List;
/**
 * Simple hash table implementation of Dictionary using linear probing.
 * 
 * @author Sinead Urisohn 
 * @version 01/05/2015
 */
public class LPHashtable implements Dictionary
{
    private final static int DEFAULT_SIZE = 50;
 
    private Entry[] table;
    private int entries;
    
    private int lpProbeCounter;//this is a probe counter for performance testing of load performance

	/*
	 * <------------------------
	 *Getters and setters for probe counter
	 */
   
    public int getLpProbeCounter() {
		return lpProbeCounter;
	}

	public void setLpProbeCounter(int lpProbeCounter) {
		this.lpProbeCounter = lpProbeCounter;
	}
	/*
	 * ----------------------->
	 */

	public LPHashtable() { this(DEFAULT_SIZE); }
    
    public LPHashtable(int size) { 
        this.table = new Entry[size];
        this.entries = 0;
    }
    
   /**
    * Hash function for dictionary from 
    * "Data Structures & Problem Solving Using Java - Weiss
    * figure 20.2
    * 
    * This hash function takes advantage of overflow
    * @param key
    * @return index for table
    */
    private int hashFunction(String key) {
    	int hashVal=0;
    	
    	for(int i =0;i< key.length();i++)
    		hashVal= 37*hashVal+key.charAt(i);
    	
    	hashVal%=table.length;
    	
    	//if mod generates a negative value
    	//make it positive
    	if(hashVal<0)
    		hashVal+=table.length;
    	
    	return hashVal;
    }
    
    /**
     *  Returns true if this dictionary contains a definition for the specified word.
     */
    public boolean containsWord(String word) {
    	
    	int pos = hashFunction(word);
    	int tempIndex=pos;//to make sure only wrap around once
    	Entry tempEntry = table[pos];
    	//check at initial hashed position if contains word
    	if(tempEntry!=null)
    		if(tempEntry.isEntryFor(word))
    			return true;
    	//if it does not match then increase index and increment until found or not found
    	pos++;
    	lpProbeCounter+=1;//increase probe counter
    	int i =pos;
    	//in case initial increment requires a wrap
    	if(i==table.length)
			i=0;

    	for(;i!=tempIndex;i++)
    	{
    		
    		tempEntry = table[i];
    		if(tempEntry!=null)
    		{
        		if(tempEntry.isEntryFor(word))
        			return true;
    		}
        	else return false;
    		if(i==table.length-1)
    			i=-1;//since loop will increment it to 0
    		lpProbeCounter++;//increase probe counter
    	}
    	return false;
    }
    
   /**
    *   Return the entry for the specified word, or null if this dictionary contains no entry for the word.
    */
    public List<Definition> getDefinitions(String word) {
    	
        if(containsWord(word))
        {
        	
        	int pos = findWordPos(word);
        	Entry tempEntry = table[pos];
        	return tempEntry.getDefinitions();
        }
        return null;
    }
    
   
    /**
     *   Inserts the given word and definition.
     */
    public void insert(String word, Definition definition) {      
    	
    	int indexToInsert =findPos(word);
    	
        if(!containsWord(word))
        {
        	//create new definition for word
        	ArrayList<Definition> newDefinition = new ArrayList<Definition>();
        	newDefinition.add(definition);
        	table[indexToInsert]= new HashEntry(newDefinition, word);
        	//increase number of entries
        	entries++;
        }
        else{
        	indexToInsert=findWordPos(word);
        	Entry tempEntry = table[indexToInsert];
        	for(int i =0;i<tempEntry.getDefinitions().size();i++)
        	{
        		if(tempEntry.getDefinitions().get(i).equals(definition))
        			return;
        			
        	}
        	//add new definition for word
        	tempEntry.addDefinition(definition);
        	
        	
        }
    }
        
    public boolean isEmpty() { return entries == 0; }
    
    public void empty() { this.table = new Entry[this.table.length]; this.entries=0; }
    
    public int size() { return this.entries; }
    
    /* Hash Table Functions */
    
    /**
     * Linear probing function for collision resolution
     */
    
    public int findPos(String word)
    {
    	int index= hashFunction(word);
    	while(table[index]!=null)
    	{
    		if(index>=table.length-1)//wrap around once reach end of table
    			index=0;
    		
    		index+=1;
    		lpProbeCounter++;//increase probe counter
    	}
    	return index;
    }
    
    
    /**
     * Linear probing function for successful find of word in dictionary
     * returns index of word
     */
    
    public int findWordPos(String word)
    {
    	int index= hashFunction(word);
    	
    	while(table[index]!=null&&!table[index].getWord().equals(word))
    	{
    		if(index>=table.length-1)//wrap around once reach end of table
    			index=0;
    		
    		index+=1;
    		lpProbeCounter++;//increase probe counter
    	}
    	
    	return index;
    }
    

    /**
     * Obtain the current load factor (entries / table size).
     */
    public double loadFactor() { return entries/(double)table.length; }
        
    
    /* DEBUGGING CODE */
    /**
     * Print the contents of the given hashtable.
     */
    public static void debug_print(LPHashtable hashtable) {
        Entry[] table = hashtable.table;
        for(int i=0; i<table.length; i++) {
            System.out.printf("\n%4d : %s", i, table[i]);
        }
    }
            
}
