package Organisation_Management_System_Entities;

import java.util.ArrayList;
import java.util.List;

public class Department {
    protected List<Employee> employeeList;
    protected Integer maxEmployee;

    Department(){
        maxEmployee = 0;
        this.employeeList = new ArrayList<Employee>();
    }

    public Integer getMaxEmployee() {
        return maxEmployee;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public String getDenumire(){
        return "departament";
    }

    public List<Employee> getEmployeeList() {
        return this.employeeList;
    }

    public void addEmployee(Employee e){
        employeeList.add(e);
    }
}
