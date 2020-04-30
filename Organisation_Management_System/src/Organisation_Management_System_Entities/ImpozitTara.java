package Organisation_Management_System_Entities;

import java.util.HashMap;
import java.util.Map;

public class ImpozitTara {
    private Map<String, Double> hm;

    public ImpozitTara(){
        hm = new HashMap<>();
    }

    public Map<String, Double> getHm() {
        return hm;
    }

    public void setHm(Map<String, Double> hm) {
        this.hm = hm;
    }

    public void addNewCountry(String country, Double impozit){
        assert hm != null;
        hm.put(country, impozit);
    }

    public Double getImpozit(String country){
        return hm.get(country);
    }
}
