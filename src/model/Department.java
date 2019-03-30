package model;

import java.util.ArrayList;
import java.util.List;

public class Department {
    private String name;
    List<Employee> employeeList;

    public Department(String name) {
        this.name = name;
        employeeList = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employeeList.add(employee);

    }
}
