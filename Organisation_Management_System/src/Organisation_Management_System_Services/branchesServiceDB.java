package Organisation_Management_System_Services;

import Organisation_Management_System_Entities.*;
import Organisation_Management_System_Services.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class branchesServiceDB {
    private static branchesServiceDB instance = null;

    private branchesServiceDB(){}

    public static branchesServiceDB getInstance() {
        if(instance == null)
            instance = new branchesServiceDB();
        return instance;
    }

    public Integer getBranchID(Connection connection, Branch b) throws SQLException {
        Integer id = 0;
        try{
            String country = b.getLocation().getCountry();
            String city = b.getLocation().getCity();
            Statement statement = (Statement) connection.createStatement();
            ResultSet res = statement.executeQuery("SELECT * from INFO_BRANCHES WHERE country = " + "'" + country + "' and city = '" + city + "';");
            while (res.next()){
                id = res.getInt("id");
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return id;
    }

    public Branch readData(projectsServiceDB projectsDB, employeesServiceDB employeesDB, Connection connection, Integer id) {
        Location l = new Location("a", "b", "c", 0);
        Branch branch = new Branch(l,0.0);
        try {
            Statement statement = (Statement) connection.createStatement();
            ResultSet res = statement.executeQuery("SELECT * from INFO_BRANCHES WHERE id = " + id);
            while(res.next()) {
                String country = res.getString("country");
                String city = res.getString("city");
                String street = res.getString("street");
                Integer nr = res.getInt("nr");
                Double impozit = res.getDouble("impozit");

                Location location = new Location(country, city, street, nr);
                branch.setLocation(location);
                branch.setImpozit(impozit);

                List<Department> listDepartment = new ArrayList<>();

                Department_RD department_rd = new Department_RD();
                String listEmployeesRD = res.getString("RD");
                List<String> employeesRD = new ArrayList<>();
                employeesRD = Arrays.asList(listEmployeesRD.split(","));
                for(String e_RD : employeesRD){
                    Employee newEmployee = employeesDB.readData(connection, e_RD);
                    department_rd.addEmployee(newEmployee);
                }
                listDepartment.add(department_rd);

                Department_Production department_production = new Department_Production();
                String listEmployeesProd = res.getString("Production");
                List<String> employeesProd = new ArrayList<>();
                employeesProd = Arrays.asList(listEmployeesProd.split(","));
                for(String e_Prod : employeesProd){
                    Employee newEmployee = employeesDB.readData(connection, e_Prod);
                    department_production.addEmployee(newEmployee);
                }
                listDepartment.add(department_production);

                Department_HR department_hr = new Department_HR();
                String listEmployeesHR = res.getString("HR");
                List<String> employeesHR = new ArrayList<>();
                employeesHR = Arrays.asList(listEmployeesHR.split(","));
                for(String e_HR : employeesHR){
                    Employee newEmployee = employeesDB.readData(connection, e_HR);
                    department_hr.addEmployee(newEmployee);
                }
                listDepartment.add(department_hr);

                Department_Finances department_finances = new Department_Finances();
                String listEmployeesFinances = res.getString("Finances");
                List<String> employeesFin = new ArrayList<>();
                employeesFin = Arrays.asList(listEmployeesFinances.split(","));
                for(String e_Fin : employeesFin){
                    Employee newEmployee = employeesDB.readData(connection, e_Fin);
                    department_finances.addEmployee(newEmployee);
                }
                listDepartment.add(department_finances);

                Department_Marketing department_marketing = new Department_Marketing();
                String listEmployeesMark = res.getString("Marketing");
                List<String> employeesMark = new ArrayList<>();
                employeesMark = Arrays.asList(listEmployeesMark.split(","));
                for(String e_Mark : employeesMark){
                    Employee newEmployee = employeesDB.readData(connection, e_Mark);
                    department_marketing.addEmployee(newEmployee);
                }
                listDepartment.add(department_marketing);

                branch.setDepartmentsList(listDepartment);

                Double income = res.getDouble("income");
                Double cost = res.getDouble("cost");
                FinancialSituation financialSituation = new FinancialSituation();
                financialSituation.setCost(cost);
                financialSituation.setIncome(income);
                branch.setFinancialSituation(financialSituation);

                String listProjects = res.getString("listNameGames");
                List<String> projects = new ArrayList<>();
                projects = Arrays.asList(listProjects.split(","));
                List<Project> projectList = new ArrayList<>();
                for(String p : projects){
                    Project newProject = projectsDB.readDataOne(connection, p);
                    projectList.add(newProject);
                }
                branch.setProjectList(projectList);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return branch;
    }

    public void createData(Connection connection, Branch branch){
        try{
            Statement statement = (Statement) connection.createStatement();
            ResultSet res = statement.executeQuery("SELECT max(id) as id FROM INFO_BRANCHES");
            Integer maxID = 0;
            while (res.next()){
                maxID = res.getInt("id") + 1;
            }

            String country = branch.getLocation().getCountry();
            String city = branch.getLocation().getCity();
            String street = branch.getLocation().getStreet();
            Integer nr = branch.getLocation().getNr();
            Double impozit = branch.getImpozit();
            Double income = branch.getFinancialSituation().getIncome();
            Double cost = branch.getFinancialSituation().getCost();

            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement("INSERT INTO INFO_BRANCHES (id, country, city, street, nr, impozit, RD, Production, HR, Finances, Marketing, income, cost, listNameGames) VALUES (" + maxID + ",'" + country + "'," + "'" + city + "'," + "'" + street + "'," + nr + "," + impozit + "," + "' '," + "' '," + "' '," + "' '," + "' '," + income + "," + cost + ",' '" + ");");
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteData(Connection connection, Branch branch){
        try{
            String country = branch.getLocation().getCountry();
            String city = branch.getLocation().getCity();
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement("DELETE FROM INFO_BRANCHES WHERE country = '" + country + "' and city = '" + city + "';");
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateData(Connection connection, Branch branch){
        try {
            String country = branch.getLocation().getCountry();
            String city = branch.getLocation().getCity();
            String street = branch.getLocation().getStreet();
            Integer nr = branch.getLocation().getNr();
            Double impozit = branch.getImpozit();

            List<Department> departmentList = branch.getDepartmentsList();
            String listRD = new String();
            String listProd = new String();
            String listHR = new String();
            String listFin = new String();
            String listMark = new String();
            for(Department d : departmentList){
                if(d.getDenumire().equals("Research&Dev")){
                    StringBuilder sb = new StringBuilder();
                    List<Employee> employeeList = d.getEmployeeList();
                    for(Employee e : employeeList){
                        sb.append(e.getName());
                        sb.append(",");
                    }
                    listRD = sb.toString();
                }

                if(d.getDenumire().equals("Production")){
                    StringBuilder sb = new StringBuilder();
                    List<Employee> employeeList = d.getEmployeeList();
                    for(Employee e : employeeList){
                        sb.append(e.getName());
                        sb.append(",");
                    }
                    listProd = sb.toString();
                }

                if(d.getDenumire().equals("HR")){
                    StringBuilder sb = new StringBuilder();
                    List<Employee> employeeList = d.getEmployeeList();
                    for(Employee e : employeeList){
                        sb.append(e.getName());
                        sb.append(",");
                    }
                    listHR = sb.toString();
                }

                if(d.getDenumire().equals("Finances")){
                    StringBuilder sb = new StringBuilder();
                    List<Employee> employeeList = d.getEmployeeList();
                    for(Employee e : employeeList){
                        sb.append(e.getName());
                        sb.append(",");
                    }
                    listFin = sb.toString();
                }

                if(d.getDenumire().equals("Marketing")){
                    StringBuilder sb = new StringBuilder();
                    List<Employee> employeeList = d.getEmployeeList();
                    for(Employee e : employeeList){
                        sb.append(e.getName());
                        sb.append(",");
                    }
                    listMark = sb.toString();
                }
            }

            Double income = branch.getFinancialSituation().getIncome();
            Double cost = branch.getFinancialSituation().getCost();

            List<Project> projectList = branch.getProjectList();
            String listGames = new String();
            StringBuilder sb = new StringBuilder();
            for(Project p : projectList){
                sb.append(p.getName());
                sb.append(",");
            }
            listGames = sb.toString();

            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement("UPDATE INFO_BRANCHES set RD = '" + listRD + "'," + "Production = '" + listProd + "'," + "HR = '" + listHR + "'," + "Finances = '" + listFin + "'," + "Marketing = '" + listMark + "'," + "income = " + income + "," + "cost = " + cost + "," + "listNameGames = '" + listGames + "'" + "where country = '" + country + "' and city = '" + city + "';");
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
