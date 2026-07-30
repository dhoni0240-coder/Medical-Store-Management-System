package dao;

import config.DatabaseConnection;
import model.Bill;
import model.BillItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BillDAO{
    public List<Bill> getAllBills(){
        List<Bill> billList = new ArrayList<>();

        String sql = "SELECT * FROM bills";

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

    //get bill by id
    public Bill getBillById(int billId){

        String sql = """
                SELECT * FROM bills
                WHERE bill_id = ?
                """;

        try(
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
                ){

            preparedStatement.setInt(1, billId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                int id = resultSet.getInt("bill_id");
                String billDate = resultSet.getString("bill_date");
                int customerId = resultSet.getInt("customer_id");
                int userId = resultSet.getInt("user_id");
                double totalAmount = resultSet.getDouble("total_amount");
                double discount = resultSet.getDouble("discount");
                double finalAmount = resultSet.getDouble("final_amount");

                return new Bill(
                        id,
                        billDate,
                        customerId,
                        userId,
                        totalAmount,
                        discount,
                        finalAmount
                );
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //Creating Bill
    public boolean createBill(Bill bill){
        String sql = """
                INSERT INTO bills
                (
                customer_id,
                user_id,
                total_amount,
                discount,
                final_amount
                )
                VALUES
                (?,?,?,?,?)
                """;
        try(
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ){

            preparedStatement.setInt(1, bill.getCustomerId());
            preparedStatement.setInt(2, bill.getUserId());
            preparedStatement.setDouble(3, bill.getTotalAmount());
            preparedStatement.setDouble(4, bill.getDiscount());
            preparedStatement.setDouble(5, bill.getFinalAmount());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //Adding bill Item
    public boolean addBillItem(BillItem billItem){

        String sql = """
                INSERT INTO bill_items
                (
                bill_id,
                medicine_id,
                quantity,
                price,
                subtotal
                )
                VALUES
                (?,?,?,?,?)
                """;
        try(
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
                ){

            preparedStatement.setInt(1, billItem.getBillId());
            preparedStatement.setInt(2, billItem.getMedicineId());
            preparedStatement.setInt(3, billItem.getQuantity());
            preparedStatement.setDouble(4, billItem.getPrice());
            preparedStatement.setDouble(5, billItem.getSubTotal());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //get bill Items
    public List<BillItem> getBillItems(int billId){
        List<BillItem> billItemList = new ArrayList<>();

        String sql = """
                SELECT * FROM bill_items
                WHERE bill_id = ?
                """;
        try(
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
                ){

            preparedStatement.setInt(1, billId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                int billItemId = resultSet.getInt("bill_item_id");
                int billIdResult = resultSet.getInt("bill_id");
                int medicineId = resultSet.getInt("medicine_id");
                int quantity = resultSet.getInt("quantity");
                double price = resultSet.getDouble("price");
                double subTotal = resultSet.getDouble("subtotal");

                BillItem billItem = new BillItem(
                        billItemId,
                        billIdResult,
                        medicineId,
                        quantity,
                        price,
                        subTotal
                );
                billItemList.add(billItem);
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return billItemList;
    }
}