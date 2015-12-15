import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;


public class Recommend implements Rec_Inter{

	Map<String,Map<String,ArrayList<Double>>> r ;
   

	Double mu; //mean
	
	Sim simObj = new SimPearson();
	int K = 5; //top number
	
	
	
	public void buildRecommender(Map<String, Map<String, ArrayList<Double>>> r) {
		// TODO Auto-generated method stub
		this.r=r;
	    mu = Util.computeMu(r);
   
		
	}

public Double r_hat(String u, String i,Double thresh ) {
	
	SortedMap<Double, Set<String>>  sorted = this.sortedSimUsers(u,thresh);
	
	ArrayList<Double> arrayList =new ArrayList<Double>();	
	
    
		if (sorted.size() == 0) 
			{
			return mu; 
			}
		Double  rat_sim_sum = 0.0, 
				sim_sum = 0.0;
		int count = 0;
		for (Double sim : sorted.keySet()) {
		
			
			for (String v : sorted.get(sim)) {
				
				
				Double wil= wilson.compute_wil(r.get(v),i);
				if(wil != 010.0)
				{
					count++; if (count > K) return rat_sim_sum/sim_sum;
					
					if (r.get(v).containsKey(i)) {
						arrayList = r.get(v).get(i);
						rat_sim_sum += arrayList.get(0) * sim *wil;
						sim_sum += sim *wil;
						
					}	
					
				}
				
			}
		}
		
		
		if (sim_sum.equals(0.0) )
			{
			
			return mu;
			}
	
		else
			{
			return rat_sim_sum/sim_sum;
			}
		
	}
public Double r_pearson(String u, String i,Double thresh ) {
	SortedMap<Double, Set<String>>  sorted = this.sortedSimUsers(u,thresh);	
	ArrayList<Double> arrayList =new ArrayList<Double>();	
	
    
		if (sorted.size() == 0) 
			{
			return mu; 
			}
		Double  rat_sim_sum = 0.0, 
				sim_sum = 0.0;
		int count = 0;
		for (Double sim : sorted.keySet()) {
		
			
			for (String v : sorted.get(sim)) {
				
				
				Double wil= wilson.compute_wil(r.get(v),i);
				if(wil <= 0.0 && wil != 010.0) wil = (Double)1.0;
				if(wil != 010.0)
				{
					count++; if (count > K) return rat_sim_sum/sim_sum;
					
					if (r.get(v).containsKey(i)) {
						arrayList = r.get(v).get(i);
						rat_sim_sum += arrayList.get(0) * sim;
						sim_sum += sim ;
						
					}	
					
				}
				
			}
		}
		
		
		if (sim_sum.equals(0.0) )
			{
			
			return mu;
			}
	
		else
			{
			return rat_sim_sum/sim_sum;
			}
		
	}

	public SortedMap<Double, Set<String>> sortedSimUsers(String u,Double thresh) {    
		SortedMap<Double, Set<String>> sorted = 
	    		new TreeMap<Double, Set<String>>(Collections.reverseOrder());
	
		for (String v : r.keySet()) {
            if ( v.equals(u) )
            {
            	continue;
           	}
            
            Double sim =  this.simObj.calc(r.get(u),r.get(v));
            
            if ( !sorted.containsKey(sim) && sim>thresh )
            {
            	sorted.put(sim, new HashSet<String>());
            	
            }
            if(sim > thresh)
            {

                sorted.get(sim).add(v);	
            }
        }
        
        return sorted;
    }
    
	
	public static void main(String[] args) throws Exception {
		
		Map<String,Map<String,ArrayList<Double>>> r = Read.readData("C://Users//navpreet//Desktop//data//data33.txt");
		
	     String user = null,item = null;
	     
		Recommend rec = new Recommend();

		rec.buildRecommender(r);
		

		//ArrayList<Double> list_wil = new ArrayList<Double>();
		ArrayList<Double> list_pearson = new ArrayList<Double>();
		


		BufferedReader br = new BufferedReader( new FileReader("C://Users//navpreet//Desktop//data//11331.txt") );
		String line;
		int count=0;
		while ( (line = br.readLine()) != null )  {
			String[] array = line.split("\t");
			if(array.length == 1) continue; //ignore empty lines
		    user = array[0];
			item = array[1];
			count++;
		System.out.println(count);	

		//	list_wil.add(rec.r_hat(user,item,0.1));
			
			list_pearson.add(rec.r_pearson(user,item,0.1));
			
		}
		br.close();
	//	System.out.println(list_wil);
		//System.out.println(list_pearson);
	}

}
