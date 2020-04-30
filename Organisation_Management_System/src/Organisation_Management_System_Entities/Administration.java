package Organisation_Management_System_Entities;

import java.util.List;

public class Administration extends Department{
    private Integer maxEmployee = 10;

    public Integer getMaxEmployee() {
        return maxEmployee;
    }

    @Override
    public String getDenumire() {
        return "Administration";
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
