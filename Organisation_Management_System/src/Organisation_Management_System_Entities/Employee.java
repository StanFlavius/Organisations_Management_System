package Organisation_Management_System_Entities;

public class Employee {
    private String name;
    private Integer salary;
    private Integer task;

    public Employee(String n, Integer sal){
        this.name = n;
        this.salary = sal;
        this.task = 0;
    }

    public Integer getSalary() {
        return salary;
    }

    public String getName() {
        return name;
    }

    public Integer getTask() {
        return task;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public void setTask(Integer task) {
        this.task = task;
    }
}
