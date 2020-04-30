package Organisation_Management_System_Services;

import Organisation_Management_System_Entities.Project;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class projectService {
    private static projectService instance = null;

    private projectService(){}

    public static projectService getInstance() {
        if(instance == null)
            instance = new projectService();
        return instance;

    }

    public List<Project> getData(){
        List<Project> projectList = new ArrayList<>();
        BufferedReader bufferedReader;
        try{
            bufferedReader = new BufferedReader(new FileReader("infoProjects.csv"));
            String line = bufferedReader.readLine();
            while(line != null){
                List<String> listOfData = new ArrayList<>();
                listOfData = Arrays.asList(line.split(","));

                String projectName = listOfData.get(0);
                Double projectIncome = Double.valueOf(listOfData.get(1));
                Integer projectTime = Integer.valueOf(listOfData.get(2));
                Project newProject = new Project(projectName, projectIncome, projectTime);

                Integer nrEmployees = Integer.valueOf(listOfData.get(3));
                List<String> employees = new ArrayList<>();
                Integer index = 4;
                for(int it = 0; it < nrEmployees; it++){
                    employees.add(listOfData.get(index++));
                }
                newProject.setEmployeeList(employees);

                projectList.add(newProject);

                line = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return projectList;
    }

    public void loadData(Project project){
        BufferedWriter bufferedWriter;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter("infoProjects.csv", true));
            StringBuilder sb = new StringBuilder();
            sb.append(project.getName());
            sb.append(",");
            sb.append(project.getIncome().toString());
            sb.append(",");
            sb.append(project.getTime().toString());
            sb.append(",");
            Integer nrEmployees = project.getEmployeeList().size();
            sb.append(nrEmployees.toString());
            sb.append(",");
            for(String employee : project.getEmployeeList()){
                sb.append(employee);
                sb.append(",");
            }

            String str = sb.toString();
            bufferedWriter.write(str);
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateData(List<Project> projectList){
        BufferedWriter bufferedWriter;

        try {
            bufferedWriter = new BufferedWriter(new FileWriter("infoProjects.csv"));
            bufferedWriter.write("");
            for(Project project : projectList){
                instance.loadData(project);
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
