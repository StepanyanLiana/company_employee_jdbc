package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionProvider {
    private static final DBConnectionProvider INSTANCE = new DBConnectionProvider();
   private Connection connection;
   private static final String DBL_URL = "jdbc:mysql://localhost:3306/company_employee?useUnicode=true";
   private static final String USERNAME = "root";
   private static final String PASSWORD = "root";
    private DBConnectionProvider() {
    }
    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(DBL_URL,USERNAME,PASSWORD);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
        }
        public static DBConnectionProvider getInstance() {
        return INSTANCE;
   }
}
