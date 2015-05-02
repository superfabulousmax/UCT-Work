import java.util.ArrayList;
import java.util.List;

/**
 * new hash table implementation of Dictionary using quadratic probing
 * @author sinead urisohn
 * @version 01/05/2015
 *
 */
public class QPHashtable implements Dictionary{

	//constructor
	private final static int DEFAULT_SIZE = 50;

	private Entry[] table;
	private int entries;
	
	
	public QPHashtable() { this(DEFAULT_SIZE); }

	public QPHashtable(int size) { 
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
	 *  sees if the word has valid position in table
	 */
	public boolean containsWord(String word) {
		try {
			if(table[getWordPos(word)]!=null)
				return table[getWordPos(word)].getWord().equals(word);
			else return false;
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Method that performs quadratic probing
	 * using theorem 20.5
	 * @param word
	 * @return position where the search terminates
	 * @throws Exception 
	 */
	private int getWordPos(String word) throws Exception {
		int offset=1;
		//counter for number of probes
		int probes=0;
		int currentPos = (word==null)?
				0 : hashFunction(word);
		//loop while the item is not null
		while(table[currentPos]!=null)
		{
			if(word==null)
			{
				if(table[currentPos].getWord()==null)
					break;
			}

			else if(table[currentPos].getWord().equals(word))
				break;
			//increase number of probes
			probes+=1;
			//compute next probe
			currentPos+=offset;
			offset+=2;
			//do mod
			if(currentPos>=table.length)
				currentPos=currentPos-table.length;
			//exception handling for number of probes
			if(probes>table.length)
				throw new Exception("Probing has failed (i>M)");
				
		}
		return currentPos;
	}

	/**
	 *   Return the entry for the specified word, or null if this dictionary contains no entry for the word.
	 */
	public List<Definition> getDefinitions(String word) {
		if(containsWord(word))
		{

			int pos;
			try {
				pos = getWordPos(word);
				Entry tempEntry = table[pos];
				return tempEntry.getDefinitions();
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
		}
		return null;

	}



	/**
     *   Inserts the given word and definition.
     */
	public void insert(String word, Definition definition){
		//find position for word
		int currentPos=-1;
		try {
			currentPos = getWordPos(word);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//if it is new entry add
		if(!containsWord(word))
		{
			if(table[currentPos]==null)
			{
				//create new definition for word
	        	ArrayList<Definition> newDefinition = new ArrayList<Definition>();
	        	newDefinition.add(definition);
	        	table[currentPos]= new HashEntry(newDefinition, word);
				//increase number of entries
				entries++;
				
			}
		}
		
		//else add to definitions list for entry
		else
		{
        	Entry tempEntry = table[currentPos];
        	for(int i =0;i<tempEntry.getDefinitions().size();i++)
        	{
        		//if it is repeated definition return
        		if(tempEntry.getDefinitions().get(i).equals(definition))
        			return;
        			
        	}
        	//add new definition for word
        	tempEntry.addDefinition(definition);
		}
		
		//ensure load factor less than 0.5 and that table size is prime
		//for best performance and minimal collisions
		if(loadFactor()>=0.5)
			rehash();
	}



	public boolean isEmpty() { return entries == 0; }

	public void empty() { this.table = new Entry[this.table.length]; this.entries=0; }

	public int size() { return this.entries; }
	/**
	 * Obtain the current load factor (entries / table size).
	 */
	public double loadFactor() { return entries/(double)table.length; }

	/*
	 * -----Developing and debugging---------
	 */
	
	/**
     * Print the contents of the given hashtable.
     */
    public static void debug_print(QPHashtable hashtable) {
        Entry[] table = hashtable.table;
        for(int i=0; i<table.length; i++) {
            System.out.printf("\n%4d : %s", i, table[i]);
        }
    }
	/**
	 * method that finds  next prime number for table size
	 * @param number
	 * @return next largest prime number double the parameter number
	 */
	private int nextPrimeNumber(int number)
	{
		if(number%2==0)
			number++;
		for(;!isPrime(number);number+=2);
		return number;

	}
	/**
	 * 
	 * @param number
	 * @return is the number is prime or not
	 */
	private boolean isPrime(int number) {
		int squareRoot=(int)Math.round(Math.sqrt(number));
		for(int i =2;i<=squareRoot;i++)
		{
			if(number%i==0)
				return false;
		}
		if (number>1)
			return true;
		return false;
	}
	
	/**
	 * Method that performs rehashing
	 */
	private void rehash()
	{
		Entry [] oldTable=table;
		//create new table
		table = new Entry[nextPrimeNumber(4*oldTable.length)];
		entries=0;
		
		//copy to new table
		for(int i =0;i<oldTable.length;i++)
			if(oldTable[i]!=null)
			{
				String word=oldTable[i].getWord();
				List <Definition> definitions = getDefinitions(word);
				for(int def=0;def<definitions.size();def++)
					insert(word,definitions.get(def));
			}
	}
}
