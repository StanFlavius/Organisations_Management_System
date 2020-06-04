package Organisation_Management_System_Services;

import Organisation_Management_System_Entities.ImpozitTara;

import java.io.*;
import java.util.*;

public class impozitTariService {
    private static impozitTariService instance = null;

    private impozitTariService(){}

    public static impozitTariService getInstance() {
        if(instance == null)
            instance = new impozitTariService();
        return instance;
    }

    public Map<String, Double> getData(){
        BufferedReader bufferedReader;
        Map<String, Double> mpImpozite = new HashMap<>();
        try{
            bufferedReader = new BufferedReader(new FileReader("Organisation_Management_System/infoImpozitTari.csv"));
            String line = bufferedReader.readLine();
            while(line != null){
                List<String> listOfData = new ArrayList<>();
                listOfData = Arrays.asList(line.split(","));
                String country = listOfData.get(0);
                Double impozit = Double.valueOf(listOfData.get(1));
                mpImpozite.put(country, impozit);
                line = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mpImpozite;
    }

    public void loadData(String country, Double impozit){
        BufferedWriter bufferedWriter;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter("infoImpozitTari.csv", true));
            StringBuilder sb = new StringBuilder();
            sb.append(country);
            sb.append(",");
            sb.append(impozit.toString());
            String str = sb.toString();
            bufferedWriter.write(str);
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteData(){
        BufferedWriter bufferedWriter;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter("infoImpozitTari.csv"));
            bufferedWriter.write("");
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateData(ImpozitTara impozitTara){
        Map<String, Double> hm = impozitTara.getHm();
        BufferedWriter bufferedWriter;

        try {
            bufferedWriter = new BufferedWriter(new FileWriter("infoImpozitTari.csv"));
            bufferedWriter.write("");
            for(Map.Entry<String, Double> entry : hm.entrySet()){
                instance.loadData(entry.getKey(), entry.getValue());
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
