package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // Database URL
    private static final String URL = System.getenv()
            .getOrDefault(
                    "DB_URL",
                    "jdbc:mysql://localhost:3306/medical_store_db"
            );

    // MySQL credentials from environment variables
    private static final String USERNAME = System.getenv("DB_USERNAME");
    private static final String PASSWORD = System.getenv("DB_PASSWORD");

    // Establish database connection
    public static Connection getConnection() {

        if (USERNAME == null || USERNAME.isBlank()
                || PASSWORD == null || PASSWORD.isBlank()) {

            System.out.println(
                    "Connection Failed! Set DB_USERNAME and DB_PASSWORD."
            );
            return null;
        }

        try {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);

        } catch (SQLException e) {
            System.out.println("Database connection failed.");
            e.printStackTrace();
            return null;
        }
    }
}