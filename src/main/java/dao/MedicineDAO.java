package dao;

import com.mysql.cj.protocol.Resultset;
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
}