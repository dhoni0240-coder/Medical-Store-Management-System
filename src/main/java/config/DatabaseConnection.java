package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection{

    //Database URL
    private static final String URL = "jdbc:mysql://localhost:3306/medical_store_db";

    //MySQL Username
    private static final String USERNAME = "root";

    //MySQL Password
    private static final String PASSWORD = "ashirvad@123";

    //Establishing Connection
    public static Connection getConnection(){

        try{
            Connection connection = DriverManager.getConnection(
                    URL,
                    USERNAME,
                    PASSWORD
            );
            System.out.println("Connected to the Database");
            return connection;

        }catch(SQLException e){
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return null;

        }
    }
}