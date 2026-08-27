package dao;

import config.DatabaseConnection;
import model.User;

import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.util.List;
import java.util.ArrayList;

public class UserDAO{

    public User login(String username, String password){
        String sql = """
                SELECT * FROM users
                WHERE username = ?
                AND password = ?
                """;
        try(
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
                ){

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            try(
                    ResultSet resultSet = preparedStatement.executeQuery();
                    ){
                if(resultSet.next()){
                    return new User(
                            resultSet.getInt("user_id"),
                            resultSet.getString("full_name"),
                            resultSet.getString("username"),
                            resultSet.getString("password"),
                            resultSet.getString("role"),
                            resultSet.getString("phone"),
                            resultSet.getString("email"),
                            resultSet.getTimestamp("created_at")
                    );
                }
            }

        }catch(SQLException e){
            System.out.println("Error during User Login!");
            e.printStackTrace();
        }
        return null;
    }

    //View All Users
    public List<User> getAllUsers(){
        List<User> userList = new ArrayList<>();
        String sql = """
                SELECT *
                FROM users
                ORDER BY user_id
                """;
        try(

                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery()

                ){
            while(resultSet.next()){
                User user = new User(
                        resultSet.getInt("user_id"),
                        resultSet.getString("full_name"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("role"),
                        resultSet.getString("phone"),
                        resultSet.getString("email"),
                        resultSet.getTimestamp("created_at")
                );
                userList.add(user);
            }
        }catch(SQLException e){
            System.out.println("Error fetching Users!");
            e.printStackTrace();
        }
        return userList;
    }
    //Add Users
    public boolean addUser(User user){
        String sql = """
                INSERT INTO users
                (full_name, username, password, role, phone, email)
                VALUES(?,?,?,?,?,?)
                """;
        try(
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
                ){
            preparedStatement.setString(1, user.getFull_name());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getRole());
            preparedStatement.setString(5, user.getPhone());
            preparedStatement.setString(6, user.getEmail());

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;

        }catch(SQLException e){
            System.out.println("Error fetching users!");
            if(e.getMessage().contains("Duplicate")){
                System.out.println("Username already exists!");
            }else{
                e.printStackTrace();
            }
        }
        return false;
    }
}
