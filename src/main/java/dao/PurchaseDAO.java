package dao;

import model.Purchase;
import config.DatabaseConnection;
import model.PurchaseItem;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class PurchaseDAO{

    //Create Purchase
    public int createPurchase(Purchase purchase){

        String sql = """
                INSERT INTO purchases
                (supplier_id,user_id,purchase_date,total_amount)
                VALUES(?,?,?,?)
                """;
        try(
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
                ){

            preparedStatement.setInt(1, purchase.getSupplierId());
            preparedStatement.setInt(2, purchase.getUserId());
            preparedStatement.setDate(3, Date.valueOf(purchase.getPurchaseDate()));
            preparedStatement.setDouble(4, purchase.getTotalAmount());

            preparedStatement.executeUpdate();

            try(ResultSet resultSet = preparedStatement.getGeneratedKeys()){
                if(resultSet.next()){
                    return resultSet.getInt(1);
                }
            }
        }catch(SQLException e){
            System.out.println("Error creating purchase!");
            e.printStackTrace();
        }
        return -1;
    }
    public int createPurchase(
            Connection connection,
            Purchase purchase) throws SQLException{
        String sql = """
                INSERT INTO purchases
                (supplier_id,user_id,purchase_date,total_amount)
                VALUES(?,?,?,?)
                """;
        try(
                PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
                ){

            preparedStatement.setInt(1, purchase.getSupplierId());
            preparedStatement.setInt(2, purchase.getUserId());
            preparedStatement.setDate(3, Date.valueOf(purchase.getPurchaseDate()));
            preparedStatement.setDouble(4, purchase.getTotalAmount());

            preparedStatement.executeUpdate();

            try(ResultSet resultSet = preparedStatement.getGeneratedKeys()){
                if(resultSet.next()){
                    return resultSet.getInt(1);
                }
            }
        }
        return -1;
    }

    //Add Purchase Item
    public boolean addPurchaseItem(PurchaseItem purchaseItem){
        String sql = """
                INSERT INTO purchase_items
                (purchase_id,medicine_id,quantity,purchase_price,subtotal)
                VALUES(?,?,?,?,?)
                """;
        try(
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
                ){

            preparedStatement.setInt(1, purchaseItem.getMedicineId());
            preparedStatement.setInt(2, purchaseItem.getMedicineId());
            preparedStatement.setInt(3, purchaseItem.getQuantity());
            preparedStatement.setDouble(4, purchaseItem.getPurchasePrice());
            preparedStatement.setDouble(5, purchaseItem.getSubTotal());

            preparedStatement.executeUpdate();

            return true;

        }catch(SQLException e){
            System.out.println("Error adding purchase item!");
            e.printStackTrace();
        }
        return false;
    }
    public boolean addPurchaseItem(
            Connection connection,
            PurchaseItem purchaseItem) throws SQLException{
        String sql = """
                INSERT INTO purchase_items
                (purchase_id,medicine_id,quantity,purchase_price,subtotal)
                VALUES(?,?,?,?,?)
                """;
        try(
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
                ){

            preparedStatement.setInt(1, purchaseItem.getPurchaseId());
            preparedStatement.setInt(2, purchaseItem.getMedicineId());
            preparedStatement.setInt(3, purchaseItem.getQuantity());
            preparedStatement.setDouble(4, purchaseItem.getPurchasePrice());
            preparedStatement.setDouble(5, purchaseItem.getSubTotal());

            preparedStatement.executeUpdate();

            return true;
        }
    }

    //Update Medicine Stock
    public boolean updateMedicineStock(Connection connection, int medicineId, int quantity){

        String sql = """
                UPDATE medicines
                SET quantity_in_stock = quantity_in_stock + ?
                WHERE medicine_id = ?
                """;
        try(
                PreparedStatement preparedStatement = connection.prepareStatement(sql)

                ){

            preparedStatement.setInt(1,quantity);
            preparedStatement.setInt(2, medicineId);

            int rowsUpdated = preparedStatement.executeUpdate();

            return rowsUpdated > 0;

        }catch(SQLException e){
            System.out.println("Error updating medicine stock!");
            e.printStackTrace();
        }
        return false;
    }

    //Purchase History
    public List<Purchase> getAllPurchases(){

        List<Purchase> purchaseList = new ArrayList<>();

        String sql = """
                SELECT *
                FROM purchases
                ORDER BY purchase_date DESC, purchase_id DESC
                """;
        try(
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery();

                ){
            while(resultSet.next()){
                Purchase purchase = new Purchase(
                        resultSet.getInt("purchase_id"),
                        resultSet.getInt("supplier_id"),
                        resultSet.getInt("user_id"),
                        resultSet.getDate("purchase_date").toLocalDate(),
                        resultSet.getDouble("total_amount")
                );
                purchaseList.add(purchase);
            }

        }catch(SQLException e){
            System.out.println("Error fetching purchase history!");
            e.printStackTrace();
        }
        return purchaseList;
    }
    public List<PurchaseItem> getPurchaseItems(int purchaseId){

        List<PurchaseItem> purchaseItemList = new ArrayList<>();

        String sql = """
                SELECT pi.*, m.medicine_name
                FROM purchase_items pi
                JOIN medicines m
                ON pi.medicine_id = m.medicine_id
                WHERE pi.purchase_id = ?
                ORDER BY pi.purchase_item_id
                """;
        try(
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
                ){
            preparedStatement.setInt(1, purchaseId);

            try(
                    ResultSet resultSet = preparedStatement.executeQuery();
                    ){
                while(resultSet.next()){
                    PurchaseItem item = new PurchaseItem(
                            resultSet.getInt("purchase_item_id"),
                            resultSet.getInt("purchase_id"),
                            resultSet.getInt("medicine_id"),
                            resultSet.getInt("quantity"),
                            resultSet.getDouble("purchase_price"),
                            resultSet.getDouble("subtotal")
                    );
                    item.setMedicineName(resultSet.getString("medicine_name"));
                    purchaseItemList.add(item);
                }
            }
        }catch (SQLException e) {
            System.out.println("Error fetching purchase items!");
            e.printStackTrace();
        }
        return purchaseItemList;
    }
}