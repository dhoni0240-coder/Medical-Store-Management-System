package service;

import model.User;
import dao.UserDAO;
import java.util.List;

public class UserService{

    private final UserDAO userDAO;

    public UserService(){
        userDAO = new UserDAO();
    }

    //Login Page
    public User Login(String username, String password){
        if(username == null || username.trim().isEmpty()){
            System.out.println("Username cannot be empty !");
            return null;
        }

        if(password == null || password.isEmpty()){
            System.out.println("Password cannot be Empty");
            return null;
        }

        return userDAO.login(
                username.trim(),
                password);

    }
    //View all Users
    public List<User> getAllUsers(){
        return userDAO.getAllUsers();
    }

    //Add new users
    public boolean addUser(User user){
        if(user.getFull_name() == null || user.getFull_name().trim().isEmpty()){
            System.out.println("Full name cannot be empty !");
            return false;
        }
        if(user.getUsername() == null || user.getUsername().trim().isEmpty()){
            System.out.println("Username cannot be Empty!");
            return false;
        }
        if(user.getPassword() == null || user.getPassword().isEmpty()){
            System.out.println("Password cannot be empty!");
            return false;
        }
        if(user.getRole() == null ||
                (!user.getRole().equals("ADMIN") && !user.getRole().equals("PHARMACIST") && !user.getRole().equals("STAFF"))){
            System.out.println("Invalid Role!");
            return false;
        }
        return userDAO.addUser(user);
    }
}