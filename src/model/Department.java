package model;

import java.util.ArrayList;
import java.util.List;

public class Department {
    private String name;
    private int id;
    List<Employee> employeeList;

    public Department(String name) {
        this.name = name;
        employeeList = new ArrayList<>();
    }

    public Department(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public void addEmployee(Employee employee) {
        employeeList.add(employee);

    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
