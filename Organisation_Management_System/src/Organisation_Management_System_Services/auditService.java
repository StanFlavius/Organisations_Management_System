package Organisation_Management_System_Services;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class auditService {

    public static void addInAudit(String nameMathod, String date) throws IOException {
        String threadName = Thread.currentThread().getName();
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("Organisation_Management_System/auditSituation.csv", true));
        StringBuilder sb = new StringBuilder();
        sb.append(nameMathod);
        sb.append(",");
        sb.append(date);
        sb.append(", ");
        sb.append(threadName);
        String str = sb.toString();
        bufferedWriter.write(str);
        bufferedWriter.newLine();
        bufferedWriter.close();
    }
}
