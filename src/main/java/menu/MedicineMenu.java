package menu;

import dao.MedicineDAO;
import model.Medicine;

import java.util.List;
import java.util.Scanner;

public class MedicineMenu {

    private final MedicineDAO medicineDAO = new MedicineDAO();
    private final Scanner scanner = new Scanner(System.in);

    public void showMenu() {

        while (true) {
            System.out.println("""
                    =================================
                    Medical Store Management System
                    =================================
                    """);
            System.out.println("1. View All Medicine");
            System.out.println("2. Add Medicine");
            System.out.println("3. Update Medicine");
            System.out.println("4. Delete Medicine");
            System.out.println("5. Search Medicine");
            System.out.println("0. Exit");

            System.out.print("Enter Your Choice : ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    viewAllMedicines();
                    break;

                case 2:
                    addMedicine();
                    break;

                case 3:
                    System.out.println("Update Medicine - Soon");
                    break;

                case 4:
                    System.out.println("Delete Medicine - Soon");
                    break;

                case 5:
                    System.out.println("Search Medicine - Soon");
                    break;

                case 0:
                    System.out.println("Thnk You");
                    return;

                default:
                    System.out.println("Invalid Choice");
            }
        }
    }

    //View All Medicines
    private void viewAllMedicines() {
        List<Medicine> medicineList = medicineDAO.getAllMedicines();

        if (medicineList.isEmpty()) {
            System.out.println("No Medicine Found");

            return;
        }
        for (Medicine medicine : medicineList) {
            System.out.println(medicine);
        }
    }

    //Add Medicine
    private void addMedicine() {

        System.out.println("""
                =====================
                ---Add Medicines---
                =====================
                """);
        System.out.print("Medicine Name : ");
        String medicineName = scanner.nextLine();

        System.out.print("Formula : ");
        String formula = scanner.nextLine();

        System.out.print("Category : ");
        String category = scanner.nextLine();

        System.out.print("Batch No. : ");
        String batchNo = scanner.nextLine();

        System.out.print("Manufacture Date(yyyy-mm-dd) : ");
        String manufactureDate = scanner.nextLine();

        System.out.print("Expiry Date(yyyy-mm-dd) : ");
        String expiryDate = scanner.nextLine();

        System.out.print("Purchase Price : ");
        double purchasePrice = scanner.nextDouble();

        System.out.print("Selling Price : ");
        double sellingPrice = scanner.nextDouble();

        System.out.print("Quantity In Stock : ");
        int quantityInStock = scanner.nextInt();

        scanner.nextLine();

        System.out.print("Rack No. : ");
        String rackNo = scanner.nextLine();

        System.out.print("Supplier Id : ");
        int supplierId = scanner.nextInt();

        scanner.nextLine();

        Medicine medicine = new Medicine(
                0,
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
        boolean inserted = medicineDAO.addMedicine(medicine);

        if (inserted) {
            System.out.println("Medicine Added Successfully");
        } else {
            System.out.println("Failed to add Medicine");
        }
    }
}