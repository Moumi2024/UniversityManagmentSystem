package UniversityManagmentSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Conn implements AutoCloseable {

    private static final String URL = "jdbc:mysql://localhost:3306/universitymanagmentsystem?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "moumi@2002@BYP";

    public Connection con;        // Instance connection object
    public Statement statement;   // Instance statement object

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("[DEBUG] MySQL JDBC Driver loaded successfully.");
        } catch (ClassNotFoundException e) {
            System.err.println("[ERROR] MySQL JDBC Driver not found: " + e);
        }
    }

    // Constructor that initializes connection and statement
    public Conn() {
        try {
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            statement = con.createStatement();
            System.out.println("[DEBUG] Connection and Statement created successfully.");
        } catch (SQLException e) {
            System.err.println("[ERROR] Failed to establish connection/statement: " + e);
        }
    }

    @Override
    public void close() {
        try {
            if (statement != null) {
                statement.close();
            }
            if (con != null) {
                con.close();
            }
            System.out.println("[DEBUG] Connection and Statement closed.");
        } catch (SQLException e) {
            System.err.println("[ERROR] Failed to close connection/statement: " + e);
        }
    }

    public static Connection getConnection() throws SQLException {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("[DEBUG] Database connection established successfully.");
            return conn;
        } catch (SQLException e) {
            System.err.println("[ERROR] Failed to establish database connection: " + e);
            throw e;
        }
    }

    public static PreparedStatement getPreparedStatement(String sql) throws SQLException {
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            System.out.println("[DEBUG] PreparedStatement created successfully for SQL: " + sql);
            return ps;
        } catch (SQLException e) {
            System.err.println("[ERROR] Failed to create PreparedStatement for SQL: " + sql + " -> " + e);
            throw e;
        }
    }
}
