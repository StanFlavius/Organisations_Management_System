package Organisation_Management_System_Services;

import Organisation_Management_System_Entities.Branch;
import Organisation_Management_System_Entities.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class employeesServiceDB {
    private static employeesServiceDB instance = null;

    private employeesServiceDB() {
    }

    public static employeesServiceDB getInstance() {
        if (instance == null)
            instance = new employeesServiceDB();
        return instance;
    }

    public Employee readData(Connection connection, String nameE/*Integer whichBranch, String nameDep*/){
        //List<Employee> employeeList = new ArrayList<>();
        Employee employee = new Employee("a", 0);
        try {
            Statement statement = (Statement) connection.createStatement();
            //ResultSet res = statement.executeQuery("SELECT * from INFO_EMPLOYEES WHERE idBranch = " + whichBranch + " and nameDep = '" + nameDep + "';");
            ResultSet res = statement.executeQuery("SELECT * from INFO_EMPLOYEES WHERE name_employee = " + "'" + nameE + "';");
            while (res.next()){
                String namee = res.getString("name_employee");
                Integer salary = res.getInt("salary");
                Integer task = res.getInt("task");

                String newname = namee.replace(" ", "");
                employee.setName(newname);
                employee.setTask(task);
                employee.setSalary(salary);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return employee;
    }

    public void updateData(Connection connection, Employee employee){
        try{
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement("UPDATE INFO_EMPLOYEES SET task = " + employee.getTask() + "where name_employee = '" + employee.getName() + "';");
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteData(Connection connection, Employee employee){
        try{
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement("DELETE FROM INFO_EMPLOYEES WHERE name_employee = '" + employee.getName() + "';");
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void createData(Connection connection, Employee employee){
        try{
            String name = employee.getName();
            Integer salary = employee.getSalary();
            Integer task = employee.getTask();

            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement("INSERT INTO INFO_EMPLOYEES (name_employee, salary, task) VALUES ('" + name + "', " + salary + "," + task + ");");
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
