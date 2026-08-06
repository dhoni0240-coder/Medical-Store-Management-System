package dao;

import model.Bill;
import config.DatabaseConnection;
import java.sql.Connection;
import model.Medicine;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class ReportDAO{

    //Low Stock Alert
    public List<Medicine> lowStockMedicines(){
        List<Medicine> medicineList = new ArrayList<>();

        String sql = """
                SELECT *
                FROM medicines
                WHERE quantity_in_stock <= 10
                ORDER BY quantity_in_stock ASC
                """;

        try(
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
                ){

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                int medicineId = resultSet.getInt("medicine_id");
                String medicineName = resultSet.getString("medicine_name");
                String formula = resultSet.getString("formula");
                String category = resultSet.getString("category");
                String batchNo = resultSet.getString("batch_no");
                String manufactureDate = resultSet.getString("manufacture_date");
                String expiryDate = resultSet.getString("expiry_date");
                double purchasePrice = resultSet.getDouble("purchase_price");
                double sellingPrice = resultSet.getDouble("selling_price");
                int quantityInStock = resultSet.getInt("quantity_in_stock");
                String rackNo = resultSet.getString("rack_no");
                int supplierId = resultSet.getInt("supplier_id");

                Medicine medicine = new Medicine(
                        medicineId,
                        medicineName,
                        formula,
                        category,
                        batchNo,
                        manufactureDate,
                        expiryDate,
                        purchasePrice,
                        sellingPrice,
                        quantityInStock,
                        rackNo,
                        supplierId
                );
                medicineList.add(medicine);
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return medicineList;
    }

    //Out of Stock Medicines
    public List<Medicine> outOfStockMedicines(){

        List<Medicine> medicineList = new ArrayList<>();

        String sql = """
                SELECT *
                FROM medicines
                WHERE quantity_in_stock = 0
                ORDER BY medicine_name
                """;

        try(
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
                ){

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                int medicineId = resultSet.getInt("medicine_id");
                String medicineName = resultSet.getString("medicine_name");
                String formula = resultSet.getString("formula");
                String category = resultSet.getString("category");
                String batchNo = resultSet.getString("batch_no");
                String manufactureDate = resultSet.getString("manufacture_date");
                String expiryDate = resultSet.getString("expiry_date");
                double purchasePrice = resultSet.getDouble("purchase_price");
                double sellingPrice = resultSet.getDouble("selling_price");
                int quantityInStock = resultSet.getInt("quantity_in_stock");
                String rackNo = resultSet.getString("rack_no");
                int supplierId = resultSet.getInt("supplier_id");

                Medicine medicine = new Medicine(
                        medicineId,
                        medicineName,
                        formula,
                        category,
                        batchNo,
                        manufactureDate,
                        expiryDate,
                        purchasePrice,
                        sellingPrice,
                        quantityInStock,
                        rackNo,
                        supplierId
                );
                medicineList.add(medicine);
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return medicineList;
    }

    //Expiring Medicine
    public List<Medicine> expiringMedicines(){

        List<Medicine> medicineList = new ArrayList<>();

        String sql = """
                SELECT *
                FROM medicines
                WHERE expiry_date BETWEEN CURDATE()
                AND DATE_ADD(CURDATE(), INTERVAL 30 DAY)
                ORDER BY expiry_date ASC
                """;

        try(
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
                ){

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                int medicineId = resultSet.getInt("medicine_id");
                String medicineName = resultSet.getString("medicine_name");
                String formula = resultSet.getString("formula");
                String category = resultSet.getString("category");
                String batchNo = resultSet.getString("batch_no");
                String manufactureDate = resultSet.getString("manufacture_date");
                String expiryDate = resultSet.getString("expiry_date");
                double purchasePrice = resultSet.getDouble("purchase_price");
                double sellingPrice = resultSet.getDouble("selling_price");
                int quantityInStock = resultSet.getInt("quantity_in_stock");
                String rackNo = resultSet.getString("rack_no");
                int supplierId = resultSet.getInt("supplier_id");

                Medicine medicine = new Medicine(
                        medicineId,
                        medicineName,
                        formula,
                        category,
                        batchNo,
                        manufactureDate,
                        expiryDate,
                        purchasePrice,
                        sellingPrice,
                        quantityInStock,
                        rackNo,
                        supplierId
                );
                medicineList.add(medicine);
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return medicineList;
    }

    //Daily Stock Report
    public List<Bill> dailyStockReport(){

        List<Bill> billList = new ArrayList<>();

        String sql = """
                SELECT *
                FROM bills
                WHERE DATE(bill_date) = CURDATE()
                ORDER BY bill_date ASC
                """;

        try(
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
                ){

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                int billId = resultSet.getInt("bill_id");
                String billDate = resultSet.getString("bill_date");
                int customerId = resultSet.getInt("customer_id");
                int userId = resultSet.getInt("user_id");
                double totalAmount = resultSet.getDouble("total_amount");
                double discount = resultSet.getDouble("discount");
                double finalAmount = resultSet.getDouble("final_amount");

                Bill bill = new Bill(
                        billId,
                        billDate,
                        customerId,
                        userId,
                        totalAmount,
                        discount,
                        finalAmount
                );
                billList.add(bill);
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return billList;
    }

    //Monthly Sales Report
    public List<Bill> monthlySalesReport(int month, int year){

        List<Bill> billList = new ArrayList<>();
        String sql = """
                SELECT *
                FROM bills
                WHERE MONTH(bill_date) = ?
                AND YEAR(bill_date) = ?
                ORDER BY bill_date ASC
                """;
        try(
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
                ){

            preparedStatement.setInt(1, month);
            preparedStatement.setInt(2, year);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                int billId = resultSet.getInt("bill_id");
                String billDate = resultSet.getString("bill_date");
                int customerId = resultSet.getInt("customer_id");
                int userId = resultSet.getInt("user_id");
                double totalAmount = resultSet.getDouble("total_amount");
                double discount = resultSet.getDouble("discount");
                double finalAmount = resultSet.getDouble("final_amount");

                Bill bill = new Bill(
                        billId,
                        billDate,
                        customerId,
                        userId,
                        totalAmount,
                        discount,
                        finalAmount
                );
                billList.add(bill);
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return billList;
    }

    //Top Selling Medicines
    public List<Object[]> topSellingMedicines(){

        List<Object[]> medicineList = new ArrayList<>();

        String sql = """
                SELECT
                m.medicine_id,
                m.medicine_name,
                SUM(bi.quantity) AS total_quantity_sold
                FROM medicines m
                JOIN bill_items bi
                ON m.medicine_id = bi.medicine_id
                GROUP BY m.medicine_id, m.medicine_name
                ORDER BY total_quantity_sold DESC
                LIMIT 10
                """;
        try(
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
                ){

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){

                Object[] row = new Object[3];

                row[0] = resultSet.getInt("medicine_id");
                row[1] = resultSet.getString("medicine_name");
                row[2] = resultSet.getInt("total_quantity_sold");

                medicineList.add(row);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return medicineList;
    }

    //Profit Report
    public double[] profitReport(){

        double[] report = new double[3];

        String sql = """
                SELECT
                SUM(bi.price * bi.quantity) AS revenue,
                SUM(m.purchase_price * bi.quantity) AS cost,
                SUM((bi.price - m.purchase_price) * bi.quantity) AS profit
                FROM bill_items bi
                JOIN medicines m
                ON bi.medicine_id = m.medicine_id
                """;
        try(
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
                ){

            ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {

                    report[0] = resultSet.getDouble("revenue");
                    report[1] = resultSet.getDouble("cost");
                    report[2] = resultSet.getDouble("profit");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return report;
    }
}

