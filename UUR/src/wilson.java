import java.util.ArrayList;
import java.util.Map;


public class wilson {

	public static Double compute_wil(Map<String, ArrayList<Double>> r, String i)
	{
		ArrayList<Double> arry = new ArrayList<Double>();
	    Double fraction=0.0;
	    Double Z_alpha = 1.644853;
	    Double z_alphasqr=0.0;
	    Double n=0.0;
	    Double m=0.0;
	    Double wilson_1=0.0;
	    Double wilson_2=0.0;
	    Double wilson=0.0;
		Double p = 010.0;
		Boolean flag = false;
		for(String v : r.keySet())
		{
		    if(v.equals(i))
			{
				arry= r.get(i);
			    n = arry.get(2);
			    m= arry.get(1);
			    if(n==0)
			    {
			    	n=1.0;
			    }
			    if(arry.get(1)==0)
			    {
			    	m=1.0;
			    }
				
			    
				fraction = m/n;
				
				z_alphasqr =Z_alpha*Z_alpha;
				wilson_1 = fraction + (z_alphasqr/(2*n));
				wilson_2 = Z_alpha*Math.sqrt((fraction*(1-fraction)+(z_alphasqr/(4*n)))/n);
				wilson = (wilson_1-wilson_2)/(1+(z_alphasqr/n));
			    
			
			    flag = true;
		}
		
	}
		if (flag == true)
		return wilson;
		else
		
			return p;	
		
		
}
}
