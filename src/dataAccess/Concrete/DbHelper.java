package dataAccess.Concrete;

import java.sql.*;

public class DbHelper {
    private static DbHelper dbHelper;
    
    private static final String dbUsername = "postgres";
    private static final String dbPassword = "";
    private static final String dbUrl = "jdbc:postgresql://localhost:5432/cooler_smart_device";
    private DbHelper() {}
    
    public static DbHelper createAsSingleton(){
        if(dbHelper == null){
            dbHelper = new DbHelper();
        }
        return dbHelper;
    }
    
    public Connection getConnection() throws SQLException{
        return DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
    }
    
    public void showErrorMessage(SQLException exception){
        System.out.println("Error: " + exception.getMessage());
        System.out.println("Error Code: " + exception.getErrorCode());
    }
}
