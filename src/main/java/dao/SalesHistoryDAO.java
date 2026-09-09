package dao;

import config.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class SalesHistoryDAO {

    public boolean addSale(int medicineId, int quantitySold) {

        String sql = """
                INSERT INTO sales_history
                (medicine_id, quantity_sold)
                VALUES (?, ?)
                """;

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {

            preparedStatement.setInt(1, medicineId);
            preparedStatement.setInt(2, quantitySold);

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;

        } catch(Exception e) {
            System.out.println("Error saving sales history!");
            e.printStackTrace();

        }
        return false;
    }
}
