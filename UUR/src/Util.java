import java.util.ArrayList;
import java.util.Map;


public class Util {
	public static Double computeMu(Map<String, Map<String, ArrayList<Double>>> r) {
		Double sum = 0.0;
		int count = 0;
		ArrayList<Double> arrayList=new ArrayList<Double>();
		for(String u : r.keySet()) {

			for(String i : r.get(u).keySet()) {
				arrayList= r.get(u).get(i);
				sum += arrayList.get(0);
				count++;
				
			}
		}
		//System.out.print(count);
		return sum/count;
	}

}
