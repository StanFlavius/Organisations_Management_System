package Organisation_Management_System_Entities;

import java.util.ArrayList;
import java.util.List;

public class Branch {
    private Location location;
    private List<Department> departmentsList;
    private Double impozit;
    private FinancialSituation financialSituation;
    private List<Project> projectList;

    public Branch(Location l, Double imp){
        this.location = l;
        this.impozit = imp;
        this.departmentsList = new ArrayList<Department>();
        this.projectList = new ArrayList<Project>();
    }

    public void addDepertment(Department d) {
        this.departmentsList.add(d);
    }

    public List<Department> getDepartmentsList() {
        return departmentsList;
    }

    public void setDepartmentsList(List<Department> departmentsList) {
        this.departmentsList = departmentsList;
    }

    public List<Project> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }

    public void addProject(Project p){
        this.projectList.add(p);
    }

    public Double getImpozit() {
        return impozit;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setFinancialSituation(FinancialSituation financialSituation) {
        this.financialSituation = financialSituation;
    }

    public FinancialSituation getFinancialSituation() {
        return financialSituation;
    }

    public Location getLocation() {
        return location;
    }
}
