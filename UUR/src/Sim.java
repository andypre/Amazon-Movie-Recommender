import java.util.ArrayList;
import java.util.Map;

interface Sim {
    public Double calc(Map<String,ArrayList<Double>> u, Map<String,ArrayList<Double>> v);   
}