import java.io.File;
import java.io.FileNotFoundException;
import java.io.*;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class homework2 {
	static HashMap<String,Integer> oneHashMap; 
	static HashMap <String, Double> TFmap;
	//mapPrint2(TFmap); 
	static HashMap <String, Double> IDFmap;
	static HashMap <String, Double> TFIDFmap;
	static HashMap<String,Integer> DFMap; 
	
	
	ArrayList<String> top50tokens = get50TopEntries(TFIDFmap); 
	
	public static void mapPrint2 (Map <String, Double> dict) {
		Iterator it = dict.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
            //System.out.println(pair.getKey());
            //System.out.println(pair.getValue());
        }
	}
	public static void mapPrint (Map <String, Integer> dict) {
		Iterator it = dict.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
            //System.out.println(pair.getKey());
            //System.out.println(pair.getValue());
        }
	}
	
	public static void lowerPunc (String[] wordsinLine) {
		for (int l = 0; l < wordsinLine.length; l ++) {  //words is a string of words of a line 
    		
    		wordsinLine[l] = wordsinLine[l].replaceAll("\\p{IsPunct}","");
    		wordsinLine[l] = wordsinLine[l].replaceAll("“", "");
    		wordsinLine[l] = wordsinLine[l].replaceAll("”", "");
    		wordsinLine[l] = wordsinLine[l].replaceAll("—", "");
    		wordsinLine[l] = wordsinLine[l].replace("‘", "");
    		if (wordsinLine[l].endsWith("’")) {
    			wordsinLine[l] = wordsinLine[l].substring(0, wordsinLine[l].length() - 1) + "";
    		}
    		
    	}
	}
	
	
	public static HashMap <String, Double> sortedMap (HashMap <String, Double> map){
		Map<String, Double> unSortedMap = map;   //code below for sorting a hash map by values 
        
    	//System.out.println("Unsorted Map : " + unSortedMap);
    	 
    	//LinkedHashMap preserve the ordering of elements in which they are inserted
    	LinkedHashMap<String, Double> reverseSortedMap = new LinkedHashMap<>();
    	 
    	//Use Comparator.reverseOrder() for reverse ordering
    	unSortedMap.entrySet()
    	    .stream()
    	    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())) //Comparator.reverseOrder()
    	    .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));
    	return reverseSortedMap; 
	}
	
	public static HashMap <String, Integer> sortedMapInt (HashMap <String, Integer> map){
		Map<String, Integer> unSortedMap = map;   //code below for sorting a hash map by values 
        
    	//System.out.println("Unsorted Map : " + unSortedMap);
    	 
    	//LinkedHashMap preserve the ordering of elements in which they are inserted
    	LinkedHashMap<String, Integer> reverseSortedMap = new LinkedHashMap<>();
    	 
    	//Use Comparator.reverseOrder() for reverse ordering
    	unSortedMap.entrySet()
    	    .stream()
    	    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())) //Comparator.reverseOrder()
    	    .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));
    	return reverseSortedMap; 
	}
	// Create a Hashamp of tokens and tf for one doc 
	public static HashMap <String, Integer> masterDocofFreq (int fileNumber) throws FileNotFoundException{
		HashMap<String, Integer> master = new HashMap<>();
		String filePath = "C:\\Users\\19082\\Desktop\\cs380\\docInTokens\\doc" + Integer.toString(fileNumber) + ".txt"; 
		File file = new File(filePath); //90 files
		Scanner sc = new Scanner(file);
		while(sc.hasNextLine()) {
			String line = sc.nextLine().toLowerCase(); 
        	String[] wordsinLine = line.split(" ");
        	//System.out.println(line);
        	lowerPunc(wordsinLine);
        	
        	for (int j = 0; j < wordsinLine.length; j++) {
        	
        		if (!master.containsKey(wordsinLine[j])) {
        			master.put(wordsinLine[j], 1);
        		}
        		else {
        			int currentValue = master.get(wordsinLine[j]);
                	master.replace(wordsinLine[j], currentValue+1);
        		}
        	}
		}
		return master;
	}
	
	public static HashMap <String, Double> IDFmap (HashMap <String, Integer> map, int totalDocs) {
		HashMap<String, Double> IDFmap = new HashMap<>();
		Iterator it = map.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        int numofDocsContainingTerm = (int)pair.getValue();
	        //System.out.println (pair.getKey());
	        //System.out.println (numofDocsContainingTerm);
	        double IDF =(Math.log(totalDocs/numofDocsContainingTerm)/ Math.log(2));
	        IDFmap.put(pair.getKey().toString(), IDF);
	        //System.out.println (pair.getKey());
	        //System.out.println (numofDocsContainingTerm);
	        
	    }
	    return IDFmap;
	}
	
	public static HashMap <String, Double> TFmap (HashMap <String, Integer> masterofFreq) throws FileNotFoundException{
		HashMap<String, Double> TFmap = new HashMap<>();
		Map<String,Integer> map = masterofFreq;
		Map.Entry<String,Integer> mostFrequentTerm = getMapMax(masterofFreq);
		String mostFrequentToken = mostFrequentTerm.getKey(); 
		int highestFreq = mostFrequentTerm.getValue(); 
		System.out.println("Most frequent token:"+ mostFrequentToken);
		System.out.println("highest frequency:"+ highestFreq);
		
		Iterator it = map.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        double freqofWord = (double)(int)pair.getValue();
	        //System.out.println (freqofWord);
	        //System.out.println (freqofWord/highestFreq);
	        double TF = freqofWord/highestFreq;
	        TFmap.put(pair.getKey().toString(), TF);
	        //System.out.println (pair.getKey());
	        //System.out.println (TF);
	    }
	    return TFmap;
	}
	private static Entry<String, Integer> getMapMax(HashMap<String, Integer> map) {
		Map.Entry<String, Integer> maxEntry = null;

		for (Map.Entry<String, Integer> entry : map.entrySet())
		{
		    if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0)
		    {
		        maxEntry = entry;
		    }
		}
		//get key of the token with the highest frequency 
		return maxEntry; 
	}
	private static Entry<String, Double> getMapMaxDouble(HashMap<String, Double> map) {
		Map.Entry<String, Double> maxEntry = null;

		for (Map.Entry<String, Double> entry : map.entrySet())
		{
		    if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0)
		    {
		        maxEntry = entry;
		    }
		}
		//get key of the token with the highest frequency 
		return maxEntry; 
	}
	public static ArrayList<HashMap<String, Integer>> mapForEachDoc (int numberofDocs) throws FileNotFoundException{
		ArrayList<HashMap<String, Integer>> listofDocMaps = new ArrayList<HashMap<String, Integer>>();
		
		for (int i=0; i < numberofDocs; i++) { 
			File file = new File("C:\\Users\\19082\\Desktop\\cs380\\docInTokens\\doc" + Integer.toString(i) + ".txt"); //91 files
	        
			Scanner sc = new Scanner(file);
	        
	        HashMap<String, Integer> dict = new HashMap<>();	//hash map for each document
        	
	        while(sc.hasNextLine()) {
	        	//String line = sc.nextLine();
	        	String line = sc.nextLine().toLowerCase(); 
	        	String[] words = line.split(" ");
	        	lowerPunc(words);
	        	
	        	for (int j = 0; j < words.length; j++) {
	        		
	        		if (!dict.containsKey(words[j])){
	        			dict.put(words[j],1);
	        		
	        		}
	        		else {
	        			int currentValue = dict.get(words[j]);
	        			dict.replace(words[j], currentValue+1);
	        		}
	        		
	        	}
	        	
	        }
	        listofDocMaps.add(dict);
	        
		}
		return listofDocMaps;
	}
	public static HashMap <String, Integer> numofDocsContainingToken (HashMap <String, Integer> masterDocofFreq, int numberofDocs) throws FileNotFoundException{
		HashMap <String, Integer> master = masterDocofFreq;
		HashMap <String, Integer> numofDocsConToken = new HashMap <>();
		int totalDocinCol = numberofDocs; //20
		Iterator it = master.entrySet().iterator();
		ArrayList<HashMap<String, Integer>> listofDocMaps = mapForEachDoc(numberofDocs);
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        int numofDocs = 0;
	        for (HashMap<String, Integer> item : listofDocMaps) {
	        	//System.out.println("inloop");
	        	if (item.containsKey(pair.getKey())) {
	        		//System.out.println("inif");
	        		numofDocs = numofDocs + 1;
	        	}
	        	
	        }
	        numofDocsConToken.put(pair.getKey().toString(), numofDocs);
	        it.remove(); // avoids a ConcurrentModificationException
	    }
	    return numofDocsConToken;
	}
	
	public static HashMap <String, Double> TFIDF (HashMap <String, Double> TFmap, HashMap <String, Double> IDFmap){
		HashMap <String, Double> TFIDFmap = new HashMap <>();
		Iterator it = TFmap.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        if (IDFmap.containsKey(pair.getKey())) {
	        	double TFvalue = (double) pair.getValue(); 
	        	double IDFvalue = IDFmap.get((String) pair.getKey());
	        	//System.out.println(pair.getKey());
	        	//System.out.println(TFvalue);
	        	//System.out.println(IDFvalue);
	        	double TFIDFvalue = TFvalue * IDFvalue;
	        	//System.out.println(TFIDFvalue);
	        	TFIDFmap.put(pair.getKey().toString(), TFIDFvalue);
	        }
	        
	    }
	    return TFIDFmap;
	}

	

	public static void main(String[] args) throws FileNotFoundException {
		
		oneHashMap = masterDocofFreq(0); 
		TFmap = TFmap (masterDocofFreq(0));
		//mapPrint2(TFmap); 
		DFMap = numofDocsContainingToken(masterDocofFreq(0),91); 
		IDFmap = IDFmap (numofDocsContainingToken(masterDocofFreq(0),91), 91);
		TFIDFmap = TFIDF(TFmap, IDFmap);
		
		
		ArrayList<String> top50tokens = get50TopEntries(TFIDFmap); 
		getData(top50tokens); 
		//TFmap (masterDocofFreq(1));
		//mapPrint2(sortedMap(TFIDF(TFmap, IDFmap)));
		//System.out.println(TFIDF(TFmap, IDFmap).size());
		//mapPrint(masterDocofFreq(13));
		//masterDocofFreq(13);
//		mapPrint2(TFmap);
//		System.out.println("new map");
//		mapPrint2(IDFmap); 	
//		System.out.println("new map");
//		mapPrint2(TFIDFmap);
		
	}
	private static void getData(List<String> top50tokens) {
		//token, document frequency, idf weight, tf, tf weight, tf-idf value 
		 for (String item : top50tokens) {
			 System.out.println(item + "," + DFMap.get(item) + "," + IDFmap.get(item) + "," + oneHashMap.get(item)+ "," + TFmap.get(item) + "," + TFIDFmap.get(item));
		 }
		
	}
	private static ArrayList<String> get50TopEntries(HashMap<String, Double> map) {
		ArrayList<String> tokens = new ArrayList<>(); 
		/*Sort map*/ 
		 Comparator<Entry<String, Double>> valueComparator = new Comparator<Entry<String,Double>>() {
	            
	            @Override
	            public int compare(Entry<String, Double> e1, Entry<String, Double> e2) {
	                Double v1 = e1.getValue();
	                Double v2 = e2.getValue();
	                return v1.compareTo(v2);
	            }
	        };	    
		Set<Entry<String, Double>> entries = map.entrySet();
		// Sort method needs a List, so let's first convert Set to List in Java
        List<Entry<String, Double>> listOfEntries = new ArrayList<Entry<String, Double>>(entries);
        
        // sorting HashMap by values using comparator
        Collections.sort(listOfEntries, Collections.reverseOrder(valueComparator));
		
        for (int i = 0; i < 50 ; i++) {
        	Entry <String, Double> entry = listOfEntries.get(i);
        	tokens.add(entry.getKey()); 
        }
		return tokens;
		
	}
	
	

}
