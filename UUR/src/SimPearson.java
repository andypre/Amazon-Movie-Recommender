import java.util.ArrayList;
import java.util.Map;


class SimPearson implements Sim
{    
    public Double calc(Map<String,ArrayList<Double>> u, Map<String,ArrayList<Double>> v) {
    	
    	ArrayList<Double> arrayListu =new ArrayList<Double>();
    	ArrayList<Double> arrayListv =new ArrayList<Double>();
    	Double sumu = 0.0, sumv = 0.0;
    	int count = 0;
    	for (String i : u.keySet()) {
    		if (v.containsKey(i)) {
    	        arrayListu = u.get(i);
    	       sumu += arrayListu.get(0);
    			arrayListv = v.get(i);
    			sumv += arrayListv.get(0);
    			count++;

    	       
    		}
    	}

    	if (count==0) return 0.0;
    	Double meanu = sumu/count, meanv = sumv/count;

    	Double sumprod = 0.0, sumsqru = 0.0, sumsqrv = 0.0; 
    	for (String i : u.keySet()) {
    		if (v.containsKey(i)) {
    			arrayListu=u.get(i);
    			arrayListv=v.get(i);
    			sumprod += (arrayListu.get(0)-meanu) * (arrayListv.get(0)-meanv);
    			sumsqru += (arrayListu.get(0)-meanu) * (arrayListu.get(0)-meanu);
    			sumsqrv += (arrayListv.get(0)-meanv) * (arrayListv.get(0)-meanv);
    		}
    	}
    	
    	if (sumsqru.equals(0.0) || sumsqrv.equals(0.0)) return 0.0; 
    	return sumprod/(Math.sqrt(sumsqru)*Math.sqrt(sumsqrv));
    }
}
