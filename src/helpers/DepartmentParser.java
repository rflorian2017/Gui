package helpers;

import model.Department;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class DepartmentParser {
    public static Department parseDepartmentFile(String departmentFile) throws Exception {
        FileReader file = new FileReader(departmentFile);
        BufferedReader br = new BufferedReader(file);
        String line;

        Department department = new Department(departmentFile);

        while ((line = br.readLine()) != null) {
           // EmployeParser.parseEmployeeLine(line); // returns an Employee
            department.addEmployee(EmployeParser.parseEmployeeLine(line));
        }
        file.close();
        return department;
    }
}
