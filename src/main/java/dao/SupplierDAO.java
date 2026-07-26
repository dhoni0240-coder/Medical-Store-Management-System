package dao;

import config.DatabaseConnection;
import model.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAO{
    public List<Supplier> getAllSuppliers(){
        List<Supplier> supplierList = new ArrayList<>();

        String sql = "SELECT * FROM suppliers";

        try(
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
                ){
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                int supplierId = resultSet.getInt("supplier_id");
                String supplierName = resultSet.getString("supplier_name");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                String companyName = resultSet.getString("company_name");

                Supplier supplier = new Supplier(
                        supplierId,
                        supplierName,
                        phone,
                        email,
                        address,
                        companyName
                );
                supplierList.add(supplier);
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return supplierList;
    }

    //==================
    //ADD SUPPLIERS
    //==================
    public boolean addSupplier(Supplier supplier){
        String sql = """
                INSERT  INTO SUPPLIERS
                (
                supplier_name,
                phone,
                email,
                address,
                company_name
                ) 
                VALUES
                (?,?,?,?,?)
                """;
        try(
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
                ){

            preparedStatement.setString(1, supplier.getSupplierName());
            preparedStatement.setString(2, supplier.getPhone());
            preparedStatement.setString(3, supplier.getEmail());
            preparedStatement.setString(4, supplier.getAddress());
            preparedStatement.setString(5, supplier.getCompanyName());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    //Get Supplier by ID
    public Supplier getSupplierById(int supplierId){

        String sql = "SELECT * FROM suppliers WHERE supplier_id = ?";

        try(
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
                ){

            preparedStatement.setInt(1, supplierId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                int id = resultSet.getInt("supplier_id");
                String supplierName = resultSet.getString("supplier_name");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                String companyName = resultSet.getString("company_name");

                return new Supplier(
                        id,
                        supplierName,
                        phone,
                        email,
                        address,
                        companyName
                );
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    //SEARCH SUPPLIER BY NAME
    public List<Supplier> searchSupplierByName(String supplierName){
        List<Supplier> supplierList = new ArrayList<>();
        String sql = """
                SELECT * FROM suppliers
                WHERE supplier_name LIKE ?""";
        try(
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
                ){

            preparedStatement.setString(1, "%" +supplierName+ "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                int supplierId = resultSet.getInt("supplier_id");
                String supplierNameResult = resultSet.getString("supplier_name");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                String companyName = resultSet.getString("company_name");

                Supplier supplier = new Supplier(
                        supplierId,
                        supplierNameResult,
                        phone,
                        email,
                        address,
                        companyName
                );
                supplierList.add(supplier);
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return supplierList;
    }

    //Update Supplier
    public boolean updateSupplier(Supplier supplier){
        String sql = """
                UPDATE suppliers
                SET
                supplier_name = ?,
                phone = ?,
                email = ?,
                address = ?,
                company_name = ?
                WHERE supplier_id = ?
                """;

        try(
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
                ){

            preparedStatement.setString(1, supplier.getSupplierName());
            preparedStatement.setString(2, supplier.getPhone());
            preparedStatement.setString(3, supplier.getEmail());
            preparedStatement.setString(4, supplier.getAddress());
            preparedStatement.setString(5, supplier.getCompanyName());
            preparedStatement.setInt(6, supplier.getSupplierId());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //DELETE SUPPLIER
    public boolean deleteSupplier(int supplierId){
        String sql = """
                DELETE FROM suppliers
                WHERE supplier_id = ?
                """;
        try(
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
                ){

            preparedStatement.setInt(1, supplierId);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
}