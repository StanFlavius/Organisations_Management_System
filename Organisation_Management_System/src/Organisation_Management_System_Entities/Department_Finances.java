package Organisation_Management_System_Entities;

import java.util.List;

public class Department_Finances extends Department {

    private String name;

    public Department_Finances(){
        this.maxEmployee = 2;
        name = "Finances";
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
