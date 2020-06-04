package Organisation_Management_System_Services;

import Organisation_Management_System_Entities.*;

import com.sun.jdi.DoubleValue;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class organisationsServiceDB {
    private static organisationsServiceDB instance = null;

    private organisationsServiceDB(){}

    public static organisationsServiceDB getInstance() {
        if(instance == null)
            instance = new organisationsServiceDB();
        return instance;
    }

    public void createData(Organisation organisation, Connection connection){
        try{
            String orgName = organisation.getName();
            Double rating = organisation.getRating();
            Double profit = organisation.getProfit();
            Integer year = organisation.getYear();
            Integer month = organisation.getMonth();
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement("INSERT INTO INFO_ORGANISATIONS (name, rating, profit, year, month, listBranches values ('" + orgName + "'," + rating + "," + profit + "," + year + "," + month + ",' ');");
            preparedStatement.executeUpdate();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Organisation> readData(Connection connection, branchesServiceDB branchesDB, projectsServiceDB projectsDB, employeesServiceDB employeesDB){
        List<Organisation> organisationList = new ArrayList<>();
        try{
            Statement statement = (Statement) connection.createStatement();
            ResultSet res = statement.executeQuery("SELECT * FROM INFO_ORGANISATIONS");
            while(res.next()){
                String nameOrganisation = res.getString("name");
                Double rating = res.getDouble("rating");
                Double profit = res.getDouble("profit");
                Integer year = res.getInt("year");
                Integer month = res.getInt("month");
                String listBranches = res.getString("listBranches");
                Organisation newOrganisation = new Organisation(nameOrganisation);
                newOrganisation.setRating(rating);
                newOrganisation.setProfit(profit);
                newOrganisation.setYear(year);
                newOrganisation.setMonth(month);

                List<Branch> branchList = new ArrayList<>();
                List<String> branches = new ArrayList<>();
                branches = Arrays.asList(listBranches.split(","));
                for(String b : branches){
                    //System.out.println(b);
                    String newstr = b.replace(" ", "");
                    Integer id = Integer.parseInt(newstr);
                    Branch newBranch = branchesDB.readData(projectsDB, employeesDB, connection, id);
                    branchList.add(newBranch);
                }
                newOrganisation.setBranchList(branchList);
                organisationList.add(newOrganisation);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return organisationList;
    }

    public void deleteData(Connection connection, branchesServiceDB branchesDB, projectsServiceDB projectsDB, employeesServiceDB employeesDB, Organisation organisation){
        try{
            String name = organisation.getName();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM INFO_ORGANISATIONS WHERE name = '" + name + "';");
            preparedStatement.executeUpdate();

            List<Branch> branchList = organisation.getBranchList();
            for(Branch b : branchList){
                branchesDB.deleteData(connection, b);
                List<Department> departmentList = b.getDepartmentsList();
                for(Department d : departmentList){
                    if(d.getDenumire().equals("Research&Dev")){
                        List<Employee> employeeList = d.getEmployeeList();
                        for(Employee e : employeeList){
                            employeesDB.deleteData(connection,e);
                        }
                    }

                    if(d.getDenumire().equals("Production")){
                        List<Employee> employeeList = d.getEmployeeList();
                        for(Employee e : employeeList){
                            employeesDB.deleteData(connection,e);
                        }
                    }

                    if(d.getDenumire().equals("HR")){
                        List<Employee> employeeList = d.getEmployeeList();
                        for(Employee e : employeeList){
                            employeesDB.deleteData(connection,e);
                        }
                    }

                    if(d.getDenumire().equals("Finances")){
                        List<Employee> employeeList = d.getEmployeeList();
                        for(Employee e : employeeList){
                            employeesDB.deleteData(connection,e);
                        }
                    }

                    if(d.getDenumire().equals("Marketing")){
                        List<Employee> employeeList = d.getEmployeeList();
                        for(Employee e : employeeList){
                            employeesDB.deleteData(connection,e);
                        }
                    }
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateDataBranches(Connection connection, branchesServiceDB branchesDB, projectsServiceDB projectsDB, employeesServiceDB employeesDB, Organisation organisation, Branch branch) {
        try{
            Statement statement = (Statement) connection.createStatement();
            ResultSet res = statement.executeQuery("SELECT max(id) as id FROM INFO_BRANCHES");
            Integer maxID = 0;
            while (res.next()){
                maxID = res.getInt("id") + 1;
            }

            res = statement.executeQuery("SELECT * from INFO_ORGANISATIONS WHERE name = '" + organisation.getName() + "';");
            String lb = new String();
            while(res.next()){
                lb = res.getString("listBranches");
            }
            StringBuilder sb = new StringBuilder();
            if(lb.endsWith(",")){
                sb.append(lb);
                sb.append(maxID.toString());
                sb.append(",");
            }
            else{
                sb.append(lb);
                sb.append(",");
                sb.append(maxID.toString());
                sb.append(",");
            }

            String listNewBranches = sb.toString();

            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement("UPDATE INFO_ORGANISATIONS SET listBranches = '" + listNewBranches + "' where name = '" + organisation.getName() + "';");
            preparedStatement.executeUpdate();

            branchesDB.createData(connection, branch);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateData(Connection connection, branchesServiceDB branchesDB, projectsServiceDB projectsDB, employeesServiceDB employeesDB, Organisation organisation){
        try{
            Double profit = organisation.getProfit();

            List<Branch> branchList = organisation.getBranchList();

            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement("UPDATE INFO_ORGANISATIONS SET profit = " + profit + "where name = '" + organisation.getName() + "';");
            preparedStatement.executeUpdate();

            for(Branch b : branchList){
                branchesDB.updateData(connection, b);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
