package menu;

import java.util.Scanner;
import model.User;
import service.UserService;
import java.util.List;

public class UserMenu{
    private final Scanner scanner;
    private final UserService userService;

    public UserMenu(Scanner scanner){
        this.scanner = scanner;
        this.userService = new UserService();
    }
    public void showMenu(){

        while(true){
            System.out.println("""
                =========================================
                ------------ USER MANAGEMENT ------------
                =========================================
                1. View All Users
                2. Add new User
                0. Back
                """);
            System.out.print("Enter your choice : ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice){
                case 1:
                    viewAllUsers();
                    break;

                case 2:
                    addUser();
                    break;

                case 0:
                    return;

                default :
                    System.out.println("Invalid choice");
            }
        }
    }
    //Add New User
    private void addUser(){
        System.out.println("""
                ===================================================
                ----------------------ADD USERS--------------------
                ===================================================
                """);
        System.out.print("Enter Full Name : ");
        String fullName = scanner.nextLine();

        System.out.print("Enter Username : ");
        String username = scanner.nextLine();

        System.out.print("Enter Password : ");
        String password = scanner.nextLine();

        System.out.println("""
                Select Role :
                1. ADMIN
                2. PHARMACIST
                3. STAFF
                """);

        System.out.print("Enter Role : ");
        int roleChoice = scanner.nextInt();
        scanner.nextLine();

        String role;

        switch(roleChoice){
            case 1:
                role = "ADMIN";
                break;

            case 2:
                role = "PHARMACIST";
                break;

            case 3:
                role = "STAFF";
                break;

            default :
                System.out.println("Invalid Choice");
                return;
        }

        System.out.print("Enter Phone Number : ");
        String phoneNumber = scanner.nextLine();

        System.out.print("Enter Email Address : ");
        String emailAddress = scanner.nextLine();

        User user = new User(
                0,
                fullName,
                username,
                password,
                role,
                phoneNumber,
                emailAddress,
                null
        );
        boolean added = userService.addUser(user);

        if (added) {
            System.out.println("\nUser added successfully!");
        } else {
            System.out.println("\nFailed to add user!");
        }
    }

    private void viewAllUsers(){

        List<User> userList = userService.getAllUsers();
        if(userList.isEmpty()){
            System.out.println("\nNo user Found!");
            return;
        }
        System.out.println("""
                ========================================================================
                ---------------------------------ALL USERS------------------------------
                ========================================================================
                ID       Full Name              Username        Role          Phone
                ------------------------------------------------------------------------
                """);
        for(User user : userList){
            System.out.printf(
                    "%-8d %-23s %-15s %-13s %-15s%n",
                    user.getUser_id(),
                    user.getFull_name(),
                    user.getUsername(),
                    user.getRole(),
                    user.getPhone()
                    );
        }
        System.out.println("=====================================================================");
    }
}