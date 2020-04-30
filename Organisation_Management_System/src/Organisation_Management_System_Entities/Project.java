package Organisation_Management_System_Entities;

import java.util.ArrayList;
import java.util.List;

public class Project {
    private String name;
    private Double incomePerMonth;
    private Integer time;
    private List<String> employeeList;

    public Project(String n, Double inc, Integer t){
        this.name = n;
        this.incomePerMonth = inc;
        this.time = t;
        this.employeeList = new ArrayList<String>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIncome(Double cost) {
        this.incomePerMonth = cost;
    }

    public void setEmployeeList(List<String> employeeList) {
        this.employeeList = employeeList;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public Double getIncome() {
        return incomePerMonth;
    }

    public Integer getTime() {
        return time;
    }

    public List<String> getEmployeeList() {
        return employeeList;
    }
}
