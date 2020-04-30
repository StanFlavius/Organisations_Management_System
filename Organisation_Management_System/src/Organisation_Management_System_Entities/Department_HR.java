package Organisation_Management_System_Entities;

import java.util.List;

public class Department_HR extends Department {

    private String name;

    public Department_HR(){
        this.maxEmployee = 3;
        this.name = "HR";
    }

    @Override
    public Integer getMaxEmployee() {
        return maxEmployee;
    }

    @Override
    public String getDenumire() {
        return name;
    }

    @Override
    public void addEmployee(Employee e) {
        super.addEmployee(e);
    }

    @Override
    public List<Employee> getEmployeeList() {
        return super.getEmployeeList();
    }
}
