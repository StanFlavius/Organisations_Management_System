package Organisation_Management_System_Services;

import Organisation_Management_System_Entities.*;
import com.sun.org.apache.xpath.internal.operations.Or;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class dataOrganisations {
    private static dataOrganisations instance = null;

    private dataOrganisations(){}

    public static dataOrganisations getInstance() {
        if(instance == null)
            instance = new dataOrganisations();
        return instance;
    }

    public void deleteData(){
        BufferedWriter bufferedWriter;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter("infoOrganisation.csv"));
            bufferedWriter.write("");
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateData(List<Organisation> listUpdated){
        BufferedWriter bufferedWriter;

        try {
            bufferedWriter = new BufferedWriter(new FileWriter("infoOrganisation.csv"));
            bufferedWriter.write("");
            for(Organisation o : listUpdated){
                instance.loadData(o);
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Organisation> getData() throws FileNotFoundException {

        List<Organisation> organisationList = new ArrayList<>();
        BufferedReader bufferedReader;
        try{
            bufferedReader = new BufferedReader(new FileReader("infoOrganisation.csv"));
            String line = bufferedReader.readLine();
            while(line != null){
                //System.out.println(line);
                List<String> listOfData = new ArrayList<>();
                listOfData = Arrays.asList(line.split(","));
                Organisation newOrganisation = new Organisation(listOfData.get(0));
                newOrganisation.setRating(Double.valueOf(listOfData.get(1)));
                newOrganisation.setProfit(Double.valueOf(listOfData.get(2)));
                newOrganisation.setYear(Integer.valueOf(listOfData.get(3)));
                newOrganisation.setMonth(Integer.valueOf(listOfData.get(4)));

                List<Branch> branchList = new ArrayList<>();
                Integer nrBranches = Integer.valueOf(listOfData.get(5));
                Integer index = 6;
                for(int i = 0; i < nrBranches; i++){
                    String country = listOfData.get(index++);
                    String city = listOfData.get(index++);
                    String street = listOfData.get(index++);
                    Integer nr = Integer.valueOf(listOfData.get(index++));
                    Location newLocation = new Location(country, city, street, nr);
                    Double impozit = Double.valueOf(listOfData.get(index++));
                    Branch newBranch = new Branch(newLocation,impozit);
                    List<Department> departmentList = new ArrayList<>();
                    //ADAUG LISTA DE DEPARTAMENTE CU ANGAJATI
                    for(int j = 0; j < 5; j++){
                        if(j == 0){
                            index++;
                            Department_RD department_rd = new Department_RD();
                            Integer nrOfRD = Integer.valueOf(listOfData.get(index++));
                            for(int it3 = 0; it3 < nrOfRD; it3++){
                                String nameEmployee = listOfData.get(index++);
                                Integer salaryEmployee = Integer.valueOf(listOfData.get(index++));
                                Integer taskEmployee = Integer.valueOf(listOfData.get(index++));
                                Employee newEmployee = new Employee(nameEmployee, salaryEmployee);
                                newEmployee.setTask(taskEmployee);
                                department_rd.addEmployee(newEmployee);
                            }
                            departmentList.add(department_rd);
                        }

                        if(j == 1){
                            index++;
                            Department_Production department_production = new Department_Production();
                            Integer nrOfRD = Integer.valueOf(listOfData.get(index++));
                            for(int it3 = 0; it3 < nrOfRD; it3++){
                                String nameEmployee = listOfData.get(index++);
                                Integer salaryEmployee = Integer.valueOf(listOfData.get(index++));
                                Integer taskEmployee = Integer.valueOf(listOfData.get(index++));
                                Employee newEmployee = new Employee(nameEmployee, salaryEmployee);
                                newEmployee.setTask(taskEmployee);
                                department_production.addEmployee(newEmployee);
                            }
                            departmentList.add(department_production);
                        }

                        if(j == 2){
                            index++;
                            Department_HR department_hr = new Department_HR();
                            Integer nrOfRD = Integer.valueOf(listOfData.get(index++));
                            for(int it3 = 0; it3 < nrOfRD; it3++){
                                String nameEmployee = listOfData.get(index++);
                                Integer salaryEmployee = Integer.valueOf(listOfData.get(index++));
                                Integer taskEmployee = Integer.valueOf(listOfData.get(index++));
                                Employee newEmployee = new Employee(nameEmployee, salaryEmployee);
                                newEmployee.setTask(taskEmployee);
                                department_hr.addEmployee(newEmployee);
                            }
                            departmentList.add(department_hr);
                        }

                        if(j == 3){
                            index++;
                            Department_Finances department_finances = new Department_Finances();
                            Integer nrOfRD = Integer.valueOf(listOfData.get(index++));
                            for(int it3 = 0; it3 < nrOfRD; it3++){
                                String nameEmployee = listOfData.get(index++);
                                Integer salaryEmployee = Integer.valueOf(listOfData.get(index++));
                                Integer taskEmployee = Integer.valueOf(listOfData.get(index++));
                                Employee newEmployee = new Employee(nameEmployee, salaryEmployee);
                                newEmployee.setTask(taskEmployee);
                                department_finances.addEmployee(newEmployee);
                            }
                            departmentList.add(department_finances);
                        }

                        if(j == 4){
                            index++;
                            Department_Marketing department_marketing = new Department_Marketing();
                            Integer nrOfRD = Integer.valueOf(listOfData.get(index++));
                            for(int it3 = 0; it3 < nrOfRD; it3++){
                                String nameEmployee = listOfData.get(index++);
                                Integer salaryEmployee = Integer.valueOf(listOfData.get(index++));
                                Integer taskEmployee = Integer.valueOf(listOfData.get(index++));
                                Employee newEmployee = new Employee(nameEmployee, salaryEmployee);
                                newEmployee.setTask(taskEmployee);
                                department_marketing.addEmployee(newEmployee);
                            }
                            departmentList.add(department_marketing);
                        }
                    }
                    newBranch.setDepartmentsList(departmentList);
                    //ADAUGAM SITUATIA FINANCIARA
                    Double income = Double.valueOf(listOfData.get(index++));
                    Double cost = Double.valueOf(listOfData.get(index++));
                    FinancialSituation finsit = new FinancialSituation();
                    finsit.setCost(cost);
                    finsit.setIncome(income);
                    newBranch.setFinancialSituation(finsit);

                    //ADAUGAM LISTA DE PROIECTE
                    List<Project> projectList = new ArrayList<>();
                    Integer nrOfProjects = Integer.valueOf(listOfData.get(index++));
                    for(int j = 0; j < nrOfProjects; j++){
                        String projectName = listOfData.get(index++);
                        Double incomeMonth = Double.valueOf(listOfData.get(index++));
                        Integer time = Integer.valueOf(listOfData.get(index++));
                        Project newProject = new Project(projectName, incomeMonth, time);

                        Integer noOfEmployees = Integer.valueOf(listOfData.get(index++));
                        List<String> employeeList = new ArrayList<>();
                        for(int it3 = 0; it3 < noOfEmployees; it3++){
                            employeeList.add(listOfData.get(index++));
                        }
                        newProject.setEmployeeList(employeeList);
                        projectList.add(newProject);
                    }
                    newBranch.setProjectList(projectList);
                    branchList.add(newBranch);
                }
                newOrganisation.setBranchList(branchList);

                organisationList.add(newOrganisation);
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }

        return organisationList;
    }

    public void loadData(Organisation o){
        BufferedWriter bufferedWriter;
        try{
            bufferedWriter = new BufferedWriter(new FileWriter("infoOrganisation.csv", true));
            StringBuilder sb = new StringBuilder();
            sb.append(o.getName());
            sb.append(',');
            sb.append(o.getRating().toString());
            sb.append(',');
            sb.append(o.getProfit().toString());
            sb.append(',');
            sb.append(o.getYear().toString());
            sb.append(',');
            sb.append(o.getMonth().toString());
            sb.append(',');

            Integer nrBranches = o.getBranchList().size();
            sb.append(nrBranches.toString());
            sb.append(',');
            for(Branch b : o.getBranchList()){
                sb.append(b.getLocation().getCountry());
                sb.append(',');
                sb.append(b.getLocation().getCity());
                sb.append(',');
                sb.append(b.getLocation().getStreet());
                sb.append(',');
                sb.append(b.getLocation().getNr().toString());
                sb.append(',');
                sb.append(b.getImpozit().toString());
                sb.append(',');

                for(Department d : b.getDepartmentsList()){
                    sb.append(d.getDenumire());
                    sb.append(',');
                    Integer nrEmployees = d.getEmployeeList().size();
                    sb.append(nrEmployees.toString());
                    sb.append(',');
                    for(Employee e : d.getEmployeeList()){
                        sb.append(e.getName());
                        sb.append(',');
                        sb.append(e.getSalary().toString());
                        sb.append(',');
                        sb.append(e.getTask().toString());
                        sb.append(',');
                    }
                }

                Double income = b.getFinancialSituation().getIncome();
                sb.append(income.toString());
                sb.append(',');
                Double cost = b.getFinancialSituation().getCost();
                sb.append(cost.toString());
                sb.append(',');

                Integer nrProjects = b.getProjectList().size();
                sb.append(nrProjects.toString());
                sb.append(',');

                for(Project p : b.getProjectList()){
                    sb.append(p.getName());
                    sb.append(',');
                    sb.append(p.getIncome().toString());
                    sb.append(',');
                    sb.append(p.getTime().toString());
                    sb.append(',');
                    Integer nrEmployeesProject = p.getEmployeeList().size();
                    sb.append(nrEmployeesProject.toString());
                    sb.append(',');
                    for(String s : p.getEmployeeList()){
                        sb.append(s);
                        sb.append(',');
                    }
                }
            }
            String str = sb.toString();
            bufferedWriter.write(str);
            bufferedWriter.newLine();
            bufferedWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}