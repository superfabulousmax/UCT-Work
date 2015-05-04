import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Hash table search performance program implementation that:
 * 1. Runs a series of trials, using a different random sample for each trial.
 * 2. Obtains the total number of probes performed in each trial i.e.
 * 	  the sum of the number of probes for each word search.
 * 3. Sums the total number of probes over all trials and calculates and outputs the average.
 * 4. Calculates and outputs the percentage difference between quadratic probing and linear probing, 
 * 	  sequential chaining and linear probing, and sequential chaining and quadratic probing.
 * @author sinead urisohn
 * @version  04/05/2015
 *
 */
public class HashTableSearchPerformance {
	public static void main(String[] args) throws java.io.FileNotFoundException, java.io.IOException {
		int numberOfTrials =100;


		LPHashtable dictionary1 = new LPHashtable(3739);

		QPHashtable dictionary2 = new QPHashtable(3739);

		SCHashtable dictionary3 = new SCHashtable(3739);

		//variables to compute the average and percentage differences

		int sumOfTrialsLP=0;
		int sumOfTrialsQP=0;
		int sumOfTrialsSC=0;

		int totalNumberInTrialLP= 0;
		int totalNumberInTrialQP=0;
		int totalNumberInTrialSC=0;

		double percentageDiffQPAndLP=0;
		double percentageDiffSCAndLP=0;
		double percentageDiffSCAndQP=0;

		Random rand = new Random();
		List<String> randomLinesFromFileContent = new ArrayList<String>();
		List<String> fileContent = new  ArrayList<String>();

		try {//load lexicon data into tables
			FileUtil.load(dictionary1, "lexicon.txt");
			FileUtil.load(dictionary2, "lexicon.txt");
			FileUtil.load(dictionary3, "lexicon.txt");
		} catch (Exception e) {

			e.printStackTrace();
		}

		//get lexicon file content
		fileContent=FileUtil.getFileContent();

		/*
		 * Load Factor 1
		 */



		//perform 100 trials for load factor of 1
		for(int trial =0;trial<numberOfTrials;trial++)
		{

			//set number of probes to 0 to get search probes only
			dictionary1.setLpProbeCounter(0);
			dictionary2.setQpProbeCounter(0);
			dictionary3.setScProbeCounter(0);

			//get 80 words for lexicon.txt
			for(int i =0;i<80;i++)
			{
				//random number between fileContent-1 and 0
				int randomLine = rand.nextInt(fileContent.size());
				randomLinesFromFileContent.add(fileContent.get(randomLine));

			}

			//get 20 random nonsense Strings that are not in lexicon
			for(int i =0;i<20;i++)
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

			//search for words
			for(String fileLine: randomLinesFromFileContent)
			{
				//split string into parts of defintion
				String [] partsOfDefinition = fileLine.split(":",3);

				//get word part of definition at pos 1
				String word= partsOfDefinition[1].trim();

				//search for words
				try{
					dictionary1.containsWord(word);
					dictionary2.containsWord(word);
					dictionary3.containsWord(word);
				} catch (Exception e) {

					//e.printStackTrace();
				}

			}


			//compute number of probes in trail
			totalNumberInTrialLP= dictionary1.getLpProbeCounter();
			totalNumberInTrialQP=dictionary2.getQpProbeCounter();
			totalNumberInTrialSC=dictionary3.getScProbeCounter();
			//add number of probes to sum
			sumOfTrialsLP+=totalNumberInTrialLP;
			sumOfTrialsQP+=totalNumberInTrialQP;
			sumOfTrialsSC+=totalNumberInTrialSC;


		}
		//calculate the average and percentage difference of sum of trials
		double averageLP=sumOfTrialsLP/numberOfTrials;
		double averageQP=sumOfTrialsQP/numberOfTrials;
		double averageSC=sumOfTrialsSC/numberOfTrials;

		percentageDiffQPAndLP=((averageQP-averageLP)/averageQP)*100;
		percentageDiffSCAndLP=((averageSC-averageLP)/averageSC)*100;
		percentageDiffSCAndQP=((averageSC-averageQP)/averageSC)*100;

		
		//number of probes in each case
		System.out.println("\nLoad factor 1.0\n Average number of probes over "+numberOfTrials+" trials:\n");
		System.out.printf("%40s %40s %40s \n","Linear Probing probe number","Quadratic Probing probe number","Sequential Chaining probe number");
		System.out.printf("%40f %40f %40f \n",averageLP,averageQP,averageSC);
		//percentage difference between
		System.out.println("Percentage difference between: \n");
		System.out.printf("%40s %40s %40s \n","Quadratic Probing & Linear Probing","Sequential Chaining & Linear Probing","Sequential chaining & Quadratic Probing");
		System.out.printf("%40f %40f %40f \n",percentageDiffQPAndLP,percentageDiffSCAndLP,percentageDiffSCAndQP);

		//set probe trial sums to 0 for next load factor test
		sumOfTrialsLP=0;
		sumOfTrialsQP=0;
		sumOfTrialsSC=0;

		/*
		 * Load Factor 0.75
		 */

		dictionary1= new LPHashtable(4987);

		dictionary2 = new QPHashtable(4987);

		dictionary3 = new SCHashtable(4987);
		try {//load lexicon data into tables
			FileUtil.load(dictionary1, "lexicon.txt");
			FileUtil.load(dictionary2, "lexicon.txt");
			FileUtil.load(dictionary3, "lexicon.txt");
		} catch (Exception e) {

			e.printStackTrace();
		}

		//perform 100 trials for load factor of 0.75
		for(int trial =0;trial<numberOfTrials;trial++)
		{

			//set number of probes to 0 to get search probes only
			dictionary1.setLpProbeCounter(0);
			dictionary2.setQpProbeCounter(0);
			dictionary3.setScProbeCounter(0);


			//get 80 words for lexicon.txt
			for(int i =0;i<80;i++)
			{
				//random number between fileContent-1 and 0
				int randomLine = rand.nextInt(fileContent.size());
				randomLinesFromFileContent.add(fileContent.get(randomLine));

			}

			//get 20 random nonsense Strings that are not in lexicon
			for(int i =0;i<20;i++)
			{
				String randomNonsenseWord="";
				int randomLoopLength=rand.nextInt(11-4)+4;
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

			//search for words
			for(String fileLine: randomLinesFromFileContent)
			{
				//split string into parts of defintion
				String [] partsOfDefinition = fileLine.split(":",3);

				//get word part of definition at pos 1
				String word= partsOfDefinition[1].trim();

				//search for words
				try{
					dictionary1.containsWord(word);
					dictionary2.containsWord(word);
					dictionary3.containsWord(word);
				} catch (Exception e) {

					//e.printStackTrace();
				}

			}


			//compute number of probes in trail
			totalNumberInTrialLP= dictionary1.getLpProbeCounter();
			totalNumberInTrialQP=dictionary2.getQpProbeCounter();
			totalNumberInTrialSC=dictionary3.getScProbeCounter();
			//add number of probes to sum
			sumOfTrialsLP+=totalNumberInTrialLP;
			sumOfTrialsQP+=totalNumberInTrialQP;
			sumOfTrialsSC+=totalNumberInTrialSC;


		}
		//calculate the average and percentage difference of sum of trials
		averageLP=sumOfTrialsLP/numberOfTrials;
		averageQP=sumOfTrialsQP/numberOfTrials;
		averageSC=sumOfTrialsSC/numberOfTrials;

		percentageDiffQPAndLP=((averageQP-averageLP)/averageQP)*100;
		percentageDiffSCAndLP=((averageSC-averageLP)/averageSC)*100;
		percentageDiffSCAndQP=((averageSC-averageQP)/averageSC)*100;


		//number of probes in each case
		System.out.println("\nLoad factor 0.75\n Average number of probes over "+numberOfTrials+" trials:\n");
		System.out.printf("%40s %40s %40s \n","Linear Probing probe number","Quadratic Probing probe number","Sequential Chaining probe number");
		System.out.printf("%40f %40f %40f \n",averageLP,averageQP,averageSC);
		//percentage difference between
		System.out.println("Percentage difference between: \n");
		System.out.printf("%40s %40s %40s \n","Quadratic Probing & Linear Probing","Sequential Chaining & Linear Probing","Sequential chaining & Quadratic Probing");
		System.out.printf("%40f %40f %40f \n",percentageDiffQPAndLP,percentageDiffSCAndLP,percentageDiffSCAndQP);

		//set probe trial sums to 0 for next load factor test
		sumOfTrialsLP=0;
		sumOfTrialsQP=0;
		sumOfTrialsSC=0;


		/*
		 * Load Factor 0.5
		 */
		dictionary1= new LPHashtable(7481);

		dictionary2 = new QPHashtable(7481);

		dictionary3 = new SCHashtable(7481);
		try {//load lexicon data into tables
			FileUtil.load(dictionary1, "lexicon.txt");
			FileUtil.load(dictionary2, "lexicon.txt");
			FileUtil.load(dictionary3, "lexicon.txt");
		} catch (Exception e) {

			e.printStackTrace();
		}

		//perform 100 trials for load factor of 0.5
		for(int trial =0;trial<numberOfTrials;trial++)
		{

			//set number of probes to 0 to get search probes only
			dictionary1.setLpProbeCounter(0);
			dictionary2.setQpProbeCounter(0);
			dictionary3.setScProbeCounter(0);


			//get 80 words for lexicon.txt
			for(int i =0;i<80;i++)
			{
				//random number between fileContent-1 and 0
				int randomLine = rand.nextInt(fileContent.size());
				randomLinesFromFileContent.add(fileContent.get(randomLine));

			}

			//get 20 random nonsense Strings that are not in lexicon
			for(int i =0;i<20;i++)
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

			//search for words
			for(String fileLine: randomLinesFromFileContent)
			{
				//split string into parts of defintion
				String [] partsOfDefinition = fileLine.split(":",3);

				//get word part of definition at pos 1
				String word= partsOfDefinition[1].trim();

				//search for words
				try{
					dictionary1.containsWord(word);
					dictionary2.containsWord(word);
					dictionary3.containsWord(word);
				} catch (Exception e) {

					//e.printStackTrace();
				}
			}


			//compute number of probes in trail
			totalNumberInTrialLP= dictionary1.getLpProbeCounter();
			totalNumberInTrialQP=dictionary2.getQpProbeCounter();
			totalNumberInTrialSC=dictionary3.getScProbeCounter();
			//add number of probes to sum
			sumOfTrialsLP+=totalNumberInTrialLP;
			sumOfTrialsQP+=totalNumberInTrialQP;
			sumOfTrialsSC+=totalNumberInTrialSC;

		}
		//calculate the average and percentage difference of sum of trials
		averageLP=sumOfTrialsLP/numberOfTrials;
		averageQP=sumOfTrialsQP/numberOfTrials;
		averageSC=sumOfTrialsSC/numberOfTrials;

		percentageDiffQPAndLP=((averageQP-averageLP)/averageQP)*100;
		percentageDiffSCAndLP=((averageSC-averageLP)/averageSC)*100;
		percentageDiffSCAndQP=((averageSC-averageQP)/averageSC)*100;


		//number of probes in each case
		System.out.println("\nLoad factor 0.5\n Average number of probes over "+numberOfTrials+" trials:\n");
		System.out.printf("%40s %40s %40s \n","Linear Probing probe number","Quadratic Probing probe number","Sequential Chaining probe number");
		System.out.printf("%40f %40f %40f \n",averageLP,averageQP,averageSC);
		//percentage difference between
		System.out.println("Percentage difference between: \n");
		System.out.printf("%40s %40s %40s \n","Quadratic Probing & Linear Probing","Sequential Chaining & Linear Probing","Sequential chaining & Quadratic Probing");
		System.out.printf("%40f %40f %40f \n",percentageDiffQPAndLP,percentageDiffSCAndLP,percentageDiffSCAndQP);

		//set probe trial sums to 0 for next load factor test
		sumOfTrialsLP=0;
		sumOfTrialsQP=0;
		sumOfTrialsSC=0;


	}


}
