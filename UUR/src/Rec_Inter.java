import java.util.ArrayList;
import java.util.Map;


public interface Rec_Inter {
	public void buildRecommender(Map<String,Map<String,ArrayList<Double>>> r);
	public Double r_hat(String u, String i,Double thresh);

}
