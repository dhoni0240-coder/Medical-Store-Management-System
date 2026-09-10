package dao;

import config.DatabaseConnection;
import model.Medicine;
import java.util.List;
import java.util.ArrayList;
import model.MedicineSalesStats;

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

    //get medicines which are completely out of stock
    public List<Medicine> getOutOfStockMedicines(){
        List<Medicine> medicines = new ArrayList<>();

        String sql = """
                SELECT medicine_id, medicine_name, quantity_in_stock
                FROM medicines
                WHERE quantity_in_stock = 0
                ORDER BY medicine_name ASC
                """;

        try(
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);

                ResultSet resultSet = preparedStatement.executeQuery();
        ){
            while(resultSet.next()){
                Medicine medicine = new Medicine(
                        resultSet.getInt("medicine_id"),
                        resultSet.getString("medicine_name"),
                        resultSet.getInt("quantity_in_stock")
                );
                medicines.add(medicine);
            }

        }catch(Exception e){
            System.out.println("Error fetching out of stock medicines!");
            e.printStackTrace();
        }
        return medicines;
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

    //get medicines with low stock
    public List<Medicine> getLowStockMedicines(){
        List<Medicine> medicines = new ArrayList<>();
        String sql = """
                SELECT medicine_id, medicine_name, quantity_in_stock
                FROM medicines
                WHERE quantity_in_stock > 0
                AND quantity_in_stock <= 10
                ORDER BY quantity_in_stock ASC
                """;

        try(
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);

                ResultSet resultSet = preparedStatement.executeQuery();
                ){

            while(resultSet.next()){
                Medicine medicine = new Medicine(
                        resultSet.getInt("medicine_id"),
                        resultSet.getString("medicine_name"),
                        resultSet.getInt("quantity_in_stock")
                );
                medicines.add(medicine);
            }

        }catch(Exception e){
            System.out.println("Error fetching low stock medicines!");
            e.printStackTrace();
        }
        return medicines;
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

    //get Top selling medicines
    public List<MedicineSalesStats> getTopSellingMedicines(){
        List<MedicineSalesStats> medicines = new ArrayList<>();
        String sql = """
                SELECT m.medicine_id,
                           m.medicine_name,
                            SUM(s.quantity_sold) AS total_sold
                            FROM sales_history s
                            JOIN medicines m
                            ON s.medicine_id = m.medicine_id
                            GROUP BY m.medicine_id, m.medicine_name
                            ORDER BY total_sold DESC
                            LIMIT 5
                """;
        try(
                Connection connection =DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery();
                ){
            while(resultSet.next()){
                int medicineId = resultSet.getInt("medicine_id");
                String medicineName = resultSet.getString("medicine_name");
                int totalSold = resultSet.getInt("total_sold");

                MedicineSalesStats medicine = new MedicineSalesStats(
                        medicineId,
                        medicineName,
                        totalSold
                );
                medicines.add(medicine);
            }

        }catch(Exception e){
            System.out.println("\nError fetching top selling medicines!");
            e.printStackTrace();
        }
        return medicines;
    }

    // get Least Selling Medicines
    public List<MedicineSalesStats> getLeastSellingMedicines() {

        List<MedicineSalesStats> medicines = new ArrayList<>();

        String sql = """
            SELECT m.medicine_id,
            m.medicine_name,
            COALESCE(SUM(s.quantity_sold), 0) AS total_sold
            FROM medicines m
            LEFT JOIN sales_history s
            ON m.medicine_id = s.medicine_id
            GROUP BY m.medicine_id, m.medicine_name
            ORDER BY total_sold ASC
            LIMIT 5
            """;

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery();
        ) {

            while (resultSet.next()) {

                int medicineId = resultSet.getInt("medicine_id");
                String medicineName = resultSet.getString("medicine_name");
                int totalSold = resultSet.getInt("total_sold");

                MedicineSalesStats medicine = new MedicineSalesStats(
                        medicineId,
                        medicineName,
                        totalSold
                );

                medicines.add(medicine);
            }

        } catch (Exception e) {

            System.out.println("\nError fetching least selling medicines!");
            e.printStackTrace();
        }

        return medicines;
    }

    //get medicines expiring within next 30 days
    public List<Medicine> getExpiringMedicines(){
        List<Medicine> medicines = new ArrayList<>();

        String sql = """
                SELECT medicine_id, medicine_name, expiry_date, quantity_in_stock
                FROM medicines
                WHERE expiry_date >= CURRENT_DATE
                AND expiry_date <= DATE_ADD(CURRENT_DATE, INTERVAL 30 DAY)
                ORDER BY expiry_date ASC
                """;

        try(
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);

                ResultSet resultSet = preparedStatement.executeQuery();
                ){
            while(resultSet.next()){
                Medicine medicine = new Medicine(
                        resultSet.getInt("medicine_id"),
                        resultSet.getString("medicine_name"),
                        resultSet.getInt("quantity_in_stock"),
                        resultSet.getString("expiry_date")
                );
                medicines.add(medicine);
            }

        }catch(Exception e){
            System.out.println("Error fetching expiring medicines!");
            e.printStackTrace();
        }
        return medicines;
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