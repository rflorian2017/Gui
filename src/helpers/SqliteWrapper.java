package helpers;

import constants.ApplicationConstants;
import model.Department;
import model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SqliteWrapper {
    /**
     * @return a Connection to a SQLITE db
     */
    private Connection connect() {
        //link to db
        String url = "jdbc:sqlite:c:\\Users\\Roby-L\\JAVATIM2.db";

        Connection conn = null; //create a connection to the SQL db

        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void insert(String name, int age, int salary) {
        String sql = "INSERT INTO employees (name, age, salary)" +
                " VALUES(?,?,?)";
        try {
            Connection conn = this.connect();
            PreparedStatement preparedStatement =
                    conn.prepareStatement(sql);

            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.setInt(3, salary);

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public String selectAll() {
        String sql = "SELECT * FROM employees";
        String toReturn = "";
        try {
            Connection conn = this.connect();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                toReturn += resultSet.getInt("id") + "\t"
                        + resultSet.getString(2) + "\t" +
                        resultSet.getInt("age") + "\t" +
                        resultSet.getInt("salary") + "\n";
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return toReturn;
    }

    public List<Department> selectAllDepartents() {
        String sql = "SELECT * FROM department";
        List<Department> departments = null;
        String toReturn = "";
        try {
            Connection conn = this.connect();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            departments = new ArrayList<>();

            while (resultSet.next()) {
                departments.add(new Department(resultSet.getString("name") + "",
                        resultSet.getInt("id")));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return departments;
    }

    public List<Employee> selectAllAsEmployees() {
        String sql = "SELECT * FROM employees";
        List<Employee> employees = null;
        String toReturn = "";
        try {
            Connection conn = this.connect();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            employees = new ArrayList<>();

            while (resultSet.next()) {
                employees.add(new Employee(resultSet.getInt("id") + "",
                                resultSet.getString(2),
                                resultSet.getInt("age"),
                                resultSet.getInt("salary")));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return employees;
    }

    public HashMap<Integer, Integer> getAllDepartmentsEmployeesMappings() {
        String sql = "SELECT * FROM dept_empl";
        HashMap<Integer, Integer> mapp = new HashMap<>();

        try {
            Connection conn = this.connect();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                mapp.put(resultSet.getInt("id_empl"),
                        resultSet.getInt("id_dept"));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return mapp;
    }

    public void deleteQuery(String criteria, String value) {
        String sql = "DELETE FROM " +
                ApplicationConstants.EMPLOYEE_TABLE +
                " WHERE " + criteria + " = ?";

        Connection connection = this.connect();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            switch (criteria) {
                case "ID":
                    preparedStatement.setInt(1, Integer.parseInt(value));
                    break;
                case "Name":
                    preparedStatement.setString(1, value);
                    break;
                case "age":
                    preparedStatement.setInt(1, Integer.parseInt(value));
                    break;
                case "salary":
                    preparedStatement.setInt(1, Integer.parseInt(value));
                    break;
            }
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
