package menu;

import service.UserService;
import model.User;

import java.util.Scanner;

public class LoginMenu {
    private final Scanner scanner;
    private final UserService userService;

    public LoginMenu(Scanner scanner){
        this.scanner = scanner;
        this.userService = new UserService();
    }

    public User login(){
        System.out.println("""
                ===================================================
                --------------- MEDICAL STORE LOGIN ---------------
                ===================================================
                """);
        System.out.print("Enter Username : ");
        String username = scanner.nextLine();

        System.out.print("Enter Password : ");
        String password = scanner.nextLine();

        User user = userService.Login(username, password);

        if(user == null){
            System.out.println("\nInvalid Username or Password!");
            return null;
        }
        System.out.println("""
                =====================================================
                ---------------- LOGIN SUCCESSFUL -----------------
                =====================================================
                """);
        System.out.println("Welcome : " +user.getUsername());
        System.out.println("Role : " +user.getRole());
        System.out.println("User ID : " +user.getUser_id());

        return user;
    }
}
