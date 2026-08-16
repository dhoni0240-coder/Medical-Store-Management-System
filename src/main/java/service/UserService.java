package service;

import model.User;
import dao.UserDAO;

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
}