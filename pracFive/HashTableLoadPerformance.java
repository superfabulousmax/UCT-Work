/**
 * program that : 
 * 1. Obtains and outputs the number of probes in each case.
 * 2. Calculates and outputs
 *    the percentage difference between quadratic probing and linear
 *    probing, sequential chaining and linear probing, and sequential chaining and quadratic probing.
 * @author sinead urisohn
 * @version 03/05/2015
 *
 */
public class HashTableLoadPerformance {


	public static void main(String[] args) throws java.io.FileNotFoundException, java.io.IOException {
		/*
		 * Load Factor 1
		 */
		LPHashtable dictionary1 = new LPHashtable(3739);

		QPHashtable dictionary2 = new QPHashtable(3739);

		SCHashtable dictionary3 = new SCHashtable(3739);

		try {
			FileUtil.load(dictionary1, "lexicon.txt");
			FileUtil.load(dictionary2, "lexicon.txt");
			FileUtil.load(dictionary3, "lexicon.txt");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		

		int probesLP= dictionary1.getLpProbeCounter();
		int probesQP=dictionary2.getQpProbeCounter();
		int probesSC=dictionary3.getScProbeCounter();

		double percentageDiffQPAndLP=((probesQP-probesLP)/probesQP)*100;
		double percentageDiffSCAndLP=((probesSC-probesLP)/probesSC)*100;
		double percentageDiffSCAndQP=((probesSC-probesQP)/probesSC)*100;

		//number of probes in each case
		System.out.println("\nLoad factor 1.0\nNumber of probes in each case:\n");
		System.out.printf("%40s %40s %40s \n","Linear Probing probe number","Quadratic Probing probe number","Sequential Chaining probe number");
		System.out.printf("%40d %40d %40d \n",probesLP,probesQP,probesSC);
		//percentage difference between
		System.out.println("Percentage difference between: \n");
		System.out.printf("%40s %40s %40s \n","Quadratic Probing & Linear Probing","Sequential Chaining & Linear Probing","Sequential chaining & Quadratic Probing");
		System.out.printf("%40f %40f %40f \n",percentageDiffQPAndLP,percentageDiffSCAndLP,percentageDiffSCAndQP);

		/*
		 * Load Factor 0.75
		 */
		dictionary1= new LPHashtable(4987);

		dictionary2 = new QPHashtable(4987);

		dictionary3 = new SCHashtable(4987);

		try {
			FileUtil.load(dictionary1, "lexicon.txt");
			FileUtil.load(dictionary2, "lexicon.txt");
			FileUtil.load(dictionary3, "lexicon.txt");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		probesLP= dictionary1.getLpProbeCounter();
		probesQP=dictionary2.getQpProbeCounter();
		probesSC=dictionary3.getScProbeCounter();

		percentageDiffQPAndLP=((probesQP-probesLP)/probesQP)*100;
		percentageDiffSCAndLP=((probesSC-probesLP)/probesSC)*100;
		percentageDiffSCAndQP=((probesSC-probesQP)/probesSC)*100;


		//number of probes in each case
		System.out.println("\nLoad factor 0.75\nNumber of probes in each case:\n");
		System.out.printf("%40s %40s %40s \n","Linear Probing probe number","Quadratic Probing probe number","Sequential Chaining probe number");
		System.out.printf("%40d %40d %40d \n",probesLP,probesQP,probesSC);
		//percentage difference between
		System.out.println("Percentage difference between: \n");
		System.out.printf("%40s %40s %40s \n","Quadratic Probing & Linear Probing","Sequential Chaining & Linear Probing","Sequential chaining & Quadratic Probing");
		System.out.printf("%40f %40f %40f \n",percentageDiffQPAndLP,percentageDiffSCAndLP,percentageDiffSCAndQP);



		/*
		 * Load Factor 0.5
		 */
		dictionary1= new LPHashtable(7481);

		dictionary2 = new QPHashtable(7481);

		dictionary3 = new SCHashtable(7481);

		try {
			FileUtil.load(dictionary1, "lexicon.txt");
			FileUtil.load(dictionary2, "lexicon.txt");
			FileUtil.load(dictionary3, "lexicon.txt");
		} catch (Exception e) {
			
			e.printStackTrace();
		}

		probesLP= dictionary1.getLpProbeCounter();
		probesQP=dictionary2.getQpProbeCounter();
		probesSC=dictionary3.getScProbeCounter();

		percentageDiffQPAndLP=((probesQP-probesLP)/probesQP)*100;
		percentageDiffSCAndLP=((probesSC-probesLP)/probesSC)*100;
		percentageDiffSCAndQP=((probesSC-probesQP)/probesSC)*100;		

		//number of probes in each case
		System.out.println("\nLoad factor 0.5\nNumber of probes in each case:\n");
		System.out.printf("%40s %40s %40s \n","Linear Probing probe number","Quadratic Probing probe number","Sequential Chaining probe number");
		System.out.printf("%40d %40d %40d \n",probesLP,probesQP,probesSC);
		//percentage difference between
		System.out.println("Percentage difference between: \n");
		System.out.printf("%40s %40s %40s \n","Quadratic Probing & Linear Probing","Sequential Chaining & Linear Probing","Sequential chaining & Quadratic Probing");
		System.out.printf("%40f %40f %40f \n",percentageDiffQPAndLP,percentageDiffSCAndLP,percentageDiffSCAndQP);
	}

}
