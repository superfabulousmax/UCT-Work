import static org.junit.Assert.*;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
/**
 * Junit test class for all hash table implementations
 * @author sinead urisohn
 * @version 7/05/2015
 *
 */

public class TestHashTables {

	/** -------- TABLES -------**/

	LPHashtable dictionary1 ;

	QPHashtable dictionary2;

	SCHashtable dictionary3 ;
	Random rand;
	//get lexicon file content
		List<String>fileContent;
	//randomon line from file content
	List<String>randomLinesFromFileContent;
	
	@Before
	public void setUp() throws Exception {
		dictionary1 = new LPHashtable(7481);
		dictionary2 = new QPHashtable(7481);
		dictionary3 = new SCHashtable(7481);

		rand =new Random();

		//load data into tables
		try {
			FileUtil.load(dictionary1, "lexicon.txt");
			FileUtil.load(dictionary2, "lexicon.txt");
			FileUtil.load(dictionary3, "lexicon.txt");
		} catch (Exception e) {

			e.printStackTrace();
		}
		fileContent=FileUtil.getFileContent();

		
	}
	/**
	 * Method that loads some lines from lexicon into random lines array
	 */
	public void loadTrueTestData()
	{
		randomLinesFromFileContent= new ArrayList<String>();
		//get 80 words for lexicon.txt
		for(int i =0;i<80;i++)
		{
			//random number between fileContent-1 and 0
			int randomLine = rand.nextInt(fileContent.size());
			randomLinesFromFileContent.add(fileContent.get(randomLine));

		}

	}

	/**
	 * loads nonsense strings into random lines array
	 */
	public void loadFalseTestData()
	{
		randomLinesFromFileContent= new ArrayList<String>();
		//get 20 random nonsense Strings that are not in lexicon
		for(int i =0;i<80;i++)
		{
			String randomNonsenseWord="";
			int randomLoopLength=rand.nextInt(10-4)+4;
			for(int j=0;j<randomLoopLength;j++)
			{
				int randomLetterNumber = rand.nextInt(90-64)+64;
				randomNonsenseWord+=(char)randomLetterNumber;

			}

			//generate a random word type
			String [] wordTypes={"a","n","v"};
			int randomWordType=rand.nextInt(3);

			randomLinesFromFileContent.add(wordTypes[randomWordType]+" : "+randomNonsenseWord+" : "+randomNonsenseWord);
		}


	}
	/**
	 * Test linear probing table
	 */
	@Test 
	public void testLP()
	{	
		loadTrueTestData();//load some lexicon words that exist in table
		boolean testTrue=false;//flag for words that are there
		boolean testFalse=false;//flag for words that are not in table
		//search for words that are there
		for(String fileLine: randomLinesFromFileContent)
		{
			//split string into parts of defintion
			String [] partsOfDefinition = fileLine.split(":",3);

			//get word part of definition at pos 1
			String word= partsOfDefinition[1].trim();

			//search for words
			try{
				testTrue=dictionary1.containsWord(word);
			} catch (Exception e) {

				//e.printStackTrace();
			}


		}
		
		loadFalseTestData();//load some lexicon words that do not exist in table
		//search for words that are not there
		for(String fileLine: randomLinesFromFileContent)
		{
			//split string into parts of defintion
			String [] partsOfDefinition = fileLine.split(":",3);

			//get word part of definition at pos 1
			String word= partsOfDefinition[1].trim();

			//search for words
			try{
				testFalse=dictionary1.containsWord(word);
			} catch (Exception e) {

				//e.printStackTrace();
			}


		}
		
		assertTrue("This should be true",testTrue);
		assertFalse("This should be false",testFalse);
	}

	/**
	 * Test quadratic probing table 
	 */
	@Test 
	public void testQP()
	{

		loadTrueTestData();//load some lexicon words that exist in table
		boolean testTrue=false;//flag for words that are there
		boolean testFalse=false;//flag for words that are not in table
		//search for words that are there
		for(String fileLine: randomLinesFromFileContent)
		{
			//split string into parts of defintion
			String [] partsOfDefinition = fileLine.split(":",3);

			//get word part of definition at pos 1
			String word= partsOfDefinition[1].trim();

			//search for words
			try{
				testTrue=dictionary2.containsWord(word);
			} catch (Exception e) {

				//e.printStackTrace();
			}


		}
		
		loadFalseTestData();//load some lexicon words that do not exist in table
		//search for words that are not there
		for(String fileLine: randomLinesFromFileContent)
		{
			//split string into parts of defintion
			String [] partsOfDefinition = fileLine.split(":",3);

			//get word part of definition at pos 1
			String word= partsOfDefinition[1].trim();

			//search for words
			try{
				testFalse=dictionary2.containsWord(word);
			} catch (Exception e) {

				//e.printStackTrace();
			}


		}
		
		assertTrue("This should be true",testTrue);
		assertFalse("This should be false",testFalse);
	}

	/**
	 * Test sequential chaining table
	 */
	@Test 
	public void testSC()
	{

		loadTrueTestData();//load some lexicon words that exist in table
		boolean testTrue=false;//flag for words that are there
		boolean testFalse=false;//flag for words that are not in table
		//search for words that are there
		for(String fileLine: randomLinesFromFileContent)
		{
			//split string into parts of defintion
			String [] partsOfDefinition = fileLine.split(":",3);

			//get word part of definition at pos 1
			String word= partsOfDefinition[1].trim();

			//search for words
			try{
				testTrue=dictionary3.containsWord(word);
			} catch (Exception e) {

				//e.printStackTrace();
			}


		}
		
		loadFalseTestData();//load some lexicon words that do not exist in table
		//search for words that are not there
		for(String fileLine: randomLinesFromFileContent)
		{
			//split string into parts of defintion
			String [] partsOfDefinition = fileLine.split(":",3);

			//get word part of definition at pos 1
			String word= partsOfDefinition[1].trim();

			//search for words
			try{
				testFalse=dictionary3.containsWord(word);
			} catch (Exception e) {

				//e.printStackTrace();
			}


		}
		
		assertTrue("This should be true",testTrue);
		assertFalse("This should be false",testFalse);
	}

	
}
