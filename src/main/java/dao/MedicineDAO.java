package dao;

import config.DatabaseConnection;
import model.Medicine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MedicineDAO{
    public List<Medicine> getAllmedicines(){
        List<Medicine> medicineList = new ArrayList<>();

        String sql = "SELECT * FROM medicines";

        try(
            Connection connection = DatabaseConnection.getConnection();  //for communication with SQL
            PreparedStatement preparedStatement = connection.prepareStatement(sql) //for execution
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
                String rackNo = resultSet.getString("rack_no");
                int quantityInStock = resultSet.getInt("quantity_in_stock");
                int supplierId = resultSet.getInt("supplier_id");

                Medicine medicine = new Medicine(medicineId,
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
            medicineList.size();

        }catch(Exception e){
            e.printStackTrace();
        }

        return medicineList;
    }

    // ==================================
    // ADD MEDICINE
    // ==================================

    public boolean addMedicine(Medicine medicine){

        String sql = """
                INSERT INTO medicines
                (
                medicine_name,
                formula,
                category,
                batch_no,
                manufacture_date,
                expiry_date,
                purchase_price,
                selling_price,
                quantity_in_stock,
                rack_no,
                supplier_id
                )
                VALUES
                (?,?,?,?,?,?,?,?,?,?,?)
                """;

        try(
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)

                ){

            preparedStatement.setString(1, medicine.getMedicineName());
            preparedStatement.setString(2, medicine.getFormula());
            preparedStatement.setString(3, medicine.getCategory());
            preparedStatement.setString(4, medicine.getBatchNo());
            preparedStatement.setString(5, medicine.getManufactureDate());
            preparedStatement.setString(6, medicine.getExpiryDate());
            preparedStatement.setDouble(7, medicine.getPurchasePrice());
            preparedStatement.setDouble(8, medicine.getSellingPrice());;
            preparedStatement.setInt(9, medicine.getQuantityInStock());
            preparedStatement.setString(10, medicine.getRackNo());
            preparedStatement.setInt(11, medicine.getSupplierId());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
}