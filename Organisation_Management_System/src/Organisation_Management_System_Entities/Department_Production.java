package Organisation_Management_System_Entities;

import java.util.List;

public class Department_Production extends Department {

    private String name;

    public Department_Production(){
        this.maxEmployee = 2;
        this.name = "Production";
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
