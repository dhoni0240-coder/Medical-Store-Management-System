package dao;

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
}

