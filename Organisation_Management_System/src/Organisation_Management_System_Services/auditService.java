package Organisation_Management_System_Services;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class auditService {

    public void addInAudit(String nameMathod, String date) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("auditSituation.csv", true));
        StringBuilder sb = new StringBuilder();
        sb.append(nameMathod);
        sb.append(",");
        sb.append(date);
        String str = sb.toString();
        bufferedWriter.write(str);
        bufferedWriter.newLine();
        bufferedWriter.close();
    }
}
