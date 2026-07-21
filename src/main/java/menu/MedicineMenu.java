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
                    updateMedicine();
                    break;

                case 4:
                    deleteMedicine();
                    break;

                case 5:
                    System.out.println("Search Medicine - Soon");
                    break;

                case 0:
                    System.out.println("Thank You");
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

    //Update Medicine
    private void updateMedicine(){
        System.out.println("""
                ===========================
                ---Update Medicine ---
                ===========================
                """);
        System.out.print("Enter Medicine ID : ");
        int medicineId = scanner.nextInt();
        scanner.nextLine();

        Medicine medicine = medicineDAO.getMedicineById(medicineId);

        if(medicine == null){
            System.out.println("Medicine Not Found");
            return;
        }
        System.out.println("\nCurrent Medicine Details : ");
        System.out.println(medicine);

        System.out.print("\nEnter New Purchase Price : ");
        double newPurchasePrice = scanner.nextDouble();

        System.out.print("\nEnter New Selling Price : ");
        double newSellingPrice = scanner.nextDouble();

        System.out.print("\nEnter New Quantity : ");
        int newQuantityInStock = scanner.nextInt();

        scanner.nextLine();

        System.out.print("Enter Rack No : ");
        String newRackNo = scanner.nextLine();

        medicine.setPurchasePrice(newPurchasePrice);
        medicine.setSellingPrice(newSellingPrice);
        medicine.setQuantityInStock(newQuantityInStock);
        medicine.setRackNo(newRackNo);

        boolean updated = medicineDAO.updateMedicine(medicine);
        if(updated){
            System.out.println("\nMedicine Updated Successfully!");
        }else{
            System.out.println("\nFailed to Update Medicine!");
        }
    }
    private void deleteMedicine(){
        System.out.println("""
                =======================
                ---Delete Medicine---
                =======================
                """);
        System.out.print("Enter Medicine ID : ");
        int medicineId = scanner.nextInt();
        scanner.nextLine();

        Medicine medicine = medicineDAO.getMedicineById(medicineId);

        if(medicine == null){
            System.out.println("Medicine Not Found");
            return;
        }
        //Show medicine details
        System.out.print("\nCurrent Medicine Details : ");
        System.out.println(medicine);

        //Confirmation before deleting
        System.out.print("\nAre you sure want to delete this Medicine ?(Y/N) : ");
        String choice = scanner.nextLine();

        if(!choice.equalsIgnoreCase("Y")){
            System.out.println("Delete Operation Cancelled.");
            return;
        }
        //Delete Medicine
        boolean deleted = medicineDAO.deleteMedicine(medicineId);

        if(deleted){
            System.out.println("\nMedicine deleted Successfully!");
        }else{
            System.out.println("\nFailed to delete Medicine!");
        }
    }
}