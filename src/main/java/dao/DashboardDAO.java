package dao;

import config.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DashboardDAO {

    // Total number of medicines
    public int getTotalMedicines() {

        String sql = "SELECT COUNT(*) FROM medicines";

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery()
        ) {

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }

        } catch (Exception e) {
            System.out.println("Error fetching total medicines!");
            e.printStackTrace();
        }

        return 0;
    }

    // Total quantity of medicines in stock
    public int getTotalStock() {

        String sql = """
                SELECT COALESCE(SUM(quantity_in_stock), 0)
                FROM medicines
                """;

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery()
        ) {

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }

        } catch (Exception e) {
            System.out.println("Error fetching total stock!");
            e.printStackTrace();
        }

        return 0;
    }

    // Medicines which are completely out of stock
    public int getOutOfStockCount() {

        String sql = """
                SELECT COUNT(*)
                FROM medicines
                WHERE quantity_in_stock = 0
                """;

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery()
        ) {

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }

        } catch (Exception e) {
            System.out.println("Error fetching out of stock medicines!");
            e.printStackTrace();
        }

        return 0;
    }

    // Medicines with stock <= 10
    public int getLowStockCount() {

        String sql = """
                SELECT COUNT(*)
                FROM medicines
                WHERE quantity_in_stock > 0
                AND quantity_in_stock <= 10
                """;

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery()
        ) {

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }

        } catch (Exception e) {
            System.out.println("Error fetching low stock medicines!");
            e.printStackTrace();
        }

        return 0;
    }

    // Medicines expiring within next 30 days
    public int getExpiringMedicineCount() {

        String sql = """
                SELECT COUNT(*)
                FROM medicines
                WHERE expiry_date >= CURRENT_DATE
                AND expiry_date <= DATE_ADD(CURRENT_DATE, INTERVAL 30 DAY)
                """;

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery()
        ) {

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }

        } catch (Exception e) {
            System.out.println("Error fetching expiring medicines!");
            e.printStackTrace();
        }

        return 0;
    }

    // Total customers
    public int getTotalCustomers() {

        String sql = "SELECT COUNT(*) FROM customers";

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery()
        ) {

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }

        } catch (Exception e) {
            System.out.println("Error fetching total customers!");
            e.printStackTrace();
        }

        return 0;
    }

    // Total suppliers
    public int getTotalSuppliers() {

        String sql = "SELECT COUNT(*) FROM suppliers";

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery()
        ) {

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }

        } catch (Exception e) {
            System.out.println("Error fetching total suppliers!");
            e.printStackTrace();
        }

        return 0;
    }

    // Number of bills generated today
    public int getTodayBills() {

        String sql = """
                SELECT COUNT(*)
                FROM bills
                WHERE DATE(bill_date) = CURRENT_DATE
                """;

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery()
        ) {

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }

        } catch (Exception e) {
            System.out.println("Error fetching today's bills!");
            e.printStackTrace();
        }

        return 0;
    }

    // Today's sales
    public double getTodaySales() {

        String sql = """
                SELECT COALESCE(SUM(final_amount), 0)
                FROM bills
                WHERE DATE(bill_date) = CURRENT_DATE
                """;

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery()
        ) {

            if (resultSet.next()) {
                return resultSet.getDouble(1);
            }

        } catch (Exception e) {
            System.out.println("Error fetching today's sales!");
            e.printStackTrace();
        }

        return 0;
    }

    // Today's purchases
    public double getTodayPurchases() {

        String sql = """
                SELECT COALESCE(SUM(total_amount), 0)
                FROM purchases
                WHERE purchase_date = CURRENT_DATE
                """;

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery()
        ) {

            if (resultSet.next()) {
                return resultSet.getDouble(1);
            }

        } catch (Exception e) {
            System.out.println("Error fetching today's purchases!");
            e.printStackTrace();
        }

        return 0;
    }
}