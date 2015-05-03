import java.util.ArrayList;
import java.util.List;

/**
 * new hash table implementation of Dictionary using sequential chaining
 * @author sinead urisohn
 * @version 03/05/2015
 *
 */
public class SCHashtable implements Dictionary{
	//create an array of linked list
	private ChainedHashEntry [] table;
	private int entries;


	private final static int DEFAULT_SIZE = 50;

	//constructor
	public SCHashtable() { this(DEFAULT_SIZE); }

	public SCHashtable(int size) { 
		this.table = new ChainedHashEntry[size];
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
		int index = hashFunction(word);
		ChainedEntry tempEntry = table[index];
		while(tempEntry!=null)//loop through chains
		{
			if(tempEntry.getWord().equals(word))
				return true;
			//get next node
			tempEntry=tempEntry.getNext();
		}
		return false;
	}

	/**
	 *   Return the entry for the specified word, or null if this dictionary contains no entry for the word.
	 */
	public List<Definition> getDefinitions(String word) {
		int index = hashFunction(word);
		ChainedEntry tempEntry = table[index];
		while(tempEntry!=null)//loop through chains
		{
			if(tempEntry.getWord().equals(word))
				return tempEntry.getDefinitions();
			//get next node
			tempEntry=tempEntry.getNext();
		}
		return null;
	}

	/**
	 *   Inserts the given word and definition.
	 */
	public void insert(String word, Definition definition) {
		int index = hashFunction(word);
		//insert new word
		if(!containsWord(word))
		{
			//create new definition for word
			ArrayList<Definition> newDefinition = new ArrayList<Definition>();
			newDefinition.add(definition);
			if(table[index]==null){



				//head of linked list
				table[index]= new ChainedHashEntry(newDefinition, word);
				//increase number of entries
				entries++;
			}
			else
			{
				//head of linked list
				ChainedEntry tempHead = table[index];//gets head at bucket
				table[index]= new ChainedHashEntry(newDefinition, word);//new entry
				table[index].setNode(tempHead);//set link

			}
		}
		//add new definition
		else
		{

			ChainedEntry tempEntry = table[index];
			while(tempEntry.getNext()!=null)//loop through chains
			{
				for(int i =0;i<tempEntry.getDefinitions().size();i++)
				{
					//if it is repeated definition return
					if(tempEntry.getDefinitions().get(i).equals(definition))
						return;

				}
				//add new definition for word
				tempEntry.addDefinition(definition);
				tempEntry=tempEntry.getNext();//get next node
			}
		}

	}




	public boolean isEmpty() { return entries == 0; }

	public void empty() { this.table = new ChainedHashEntry[this.table.length]; this.entries=0; }

	public int size() { return this.entries; }

	/*
	 * -----Developing and debugging---------
	 */

	/**
	 * Print the contents of the given hashtable.
	 */
	public static void debug_print(SCHashtable hashtable) {
		ChainedEntry[] table = hashtable.table;
		for(int i=0; i<table.length; i++) {
			ChainedEntry temp= table[i];

			while(temp!=null)
			{
				System.out.printf("\n%4d : %s", i, temp);
				temp=temp.getNext();
			}



		}
	}





}
