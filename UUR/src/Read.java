import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Read {
	
	public static 	Map<String,Map<String,ArrayList<Double>>> readData(String filename) throws Exception {
		Map<String,Map<String,ArrayList<Double>>> r = new HashMap<String,Map<String,ArrayList<Double>>>();
		    
		System.out.println("Reading file " + filename + " ...");
		BufferedReader br = new BufferedReader( new FileReader(filename) );
		String line;
		while ( (line = br.readLine()) != null )  {
			ArrayList<Double> arraylist = new ArrayList<Double>();
		   String[] array = line.split("\t");
			if(array.length == 1) continue; //ignore empty lines
			String user = array[0];
			String item = array[1];
			Double rating = Double.parseDouble(array[2]);
			Double helpful=Double.parseDouble(array[3]);
			Double outof=Double.parseDouble(array[4]);
		    arraylist.add(rating);
		    arraylist.add(helpful);
		    arraylist.add(outof);
			
			if( !r.containsKey(user) ) r.put(user, new HashMap<String,ArrayList<Double>>());
			
			r.get(user).put(item, arraylist);
		
		}
		
		
		br.close();
		System.out.println("End of reading file " + filename);
		
		return r;
	}
	

}
