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
                0. Back
                """);
            System.out.print("Enter your choice : ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice){
                case 1:
                    viewAllUsers();
                    break;

                case 0:
                    return;

                default :
                    System.out.println("Invalid choice");
            }
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