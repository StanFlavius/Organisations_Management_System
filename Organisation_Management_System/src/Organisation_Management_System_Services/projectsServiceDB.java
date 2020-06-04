package Organisation_Management_System_Services;

import Organisation_Management_System_Entities.Project;
import com.sun.jdi.DoubleValue;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class projectsServiceDB {
    private static projectsServiceDB instance = null;

    private projectsServiceDB(){}

    public static projectsServiceDB getInstance() {
        if(instance == null)
            instance = new projectsServiceDB();
        return instance;

    }

    public List<Project> readData(Connection connection){
        List<Project> projectList = new ArrayList<>();
        try {
            Statement statement = (Statement) connection.createStatement();
            ResultSet res = statement.executeQuery("SELECT * from INFO_PROJECTS");
            while(res.next()){
                String projectName = res.getString("project_name");
                Double projectIncome = res.getDouble("income");
                Integer projectTime = res.getInt("time_project");
                Project newProject = new Project(projectName, projectIncome, projectTime);

                Integer nrEmployees = res.getInt("nr_employees");
                String strEmployees = res.getString("list_employees");
                List<String> listEmployees = new ArrayList<>();
                listEmployees = Arrays.asList(strEmployees.split(","));

                newProject.setEmployeeList(listEmployees);

                projectList.add(newProject);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return projectList;
    }

    public Project readDataOne(Connection connection, String project_name){
        Project newProject = new Project("a",0.0,0);
        try {
            Statement statement = (Statement) connection.createStatement();
            ResultSet res = statement.executeQuery("SELECT * from INFO_PROJECTS WHERE project_name = " + "'" + project_name + "';");
            while(res.next()){
                String projectName = res.getString("project_name");
                Double projectIncome = res.getDouble("income");
                Integer projectTime = res.getInt("time_project");
                newProject.setName(projectName);
                newProject.setTime(projectTime);
                newProject.setIncome(projectIncome);

                Integer nrEmployees = res.getInt("nr_employees");
                String strEmployees = res.getString("list_employees");
                List<String> listEmployees = new ArrayList<>();
                listEmployees = Arrays.asList(strEmployees.split(","));

                newProject.setEmployeeList(listEmployees);

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return newProject;
    }

    public void deleteData(Connection connection, String projectName){
        try{
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement("DELETE FROM INFO_PROJECTS WHERE project_name = '" + projectName + "';");
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void createData(Connection connection, Project project){
        try{
            String projectName = project.getName();
            Double income = project.getIncome();
            Integer time = project.getTime();
            List<String> listEmployees = new ArrayList<>();
            listEmployees = project.getEmployeeList();

            Integer nr_employees = listEmployees.size();
            StringBuilder sb = new StringBuilder();
            for(String employee : listEmployees){
                sb.append(employee);
                sb.append(",");
            }
            String str = sb.toString();
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement("INSERT INTO INFO_PROJECTS (project_name, income, time_project, nr_employees, list_employees) VALUES ('" + projectName + "', " + income + "," + time + "," + nr_employees + ",'" + str + "');");
            preparedStatement.executeUpdate();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateData(Connection connection, Project p){
        try {
            String projectName = p.getName();
            Integer time = p.getTime();
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement("UPDATE INFO_PROJECTS SET time_project = " + time + "where project_name = '" + projectName + "';");
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
