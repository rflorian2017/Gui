package model;

import java.util.ArrayList;
import java.util.List;

public class Company {
    private String name;
    private List<Department> departmentList;

    public Company(String name) {
        this.name = name;
        departmentList = new ArrayList<>();
    }
}
