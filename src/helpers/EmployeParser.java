package helpers;

import model.Employee;

public class EmployeParser {
    /*
    id,name,age,salary
    1,john doe, 34,3000
    3,jane doe, 23,1000
    4, jimmy,44,5500
     */

    /**
     * @param line resembles this 1,john doe, 34,3000
     * @return an Employee object
     */
    public static Employee parseEmployeeLine(String line) {
        String[] employeeDetails = line.split(","); //returns an array of splitted strings
        return new Employee(employeeDetails[0].trim(),
                employeeDetails[1].trim(),
                Integer.parseInt(employeeDetails[2].trim()),
                Integer.parseInt(employeeDetails[3].trim()));
    }
}
