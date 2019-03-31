package helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

}
