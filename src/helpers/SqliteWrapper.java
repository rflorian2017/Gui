package helpers;

import java.sql.*;

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

        }
        catch (SQLException ex) {
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

        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return toReturn;
    }

}
