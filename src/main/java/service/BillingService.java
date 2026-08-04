package service;

import dao.BillDAO;
import dao.CustomerDAO;
import dao.MedicineDAO;
import model.BillItem;
import model.Customer;
import model.Medicine;
import model.Bill;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BillingService {

    private final BillDAO billDAO = new BillDAO();
    private final CustomerDAO customerDAO = new CustomerDAO();
    private final MedicineDAO medicineDAO = new MedicineDAO();

    private final Scanner scanner = new Scanner(System.in);

    public void generateBill() {

        // Customer Selection
        System.out.print("Enter Customer ID : ");
        int customerId = scanner.nextInt();
        scanner.nextLine();

        Customer customer = customerDAO.getCustomerById(customerId);

        if (customer == null) {
            System.out.println("Customer not found!");
            return;
        }

        System.out.println("\nCustomer Found:");
        System.out.println(customer);

        // Store all medicines of this bill
        List<BillItem> billItems = new ArrayList<>();

        while (true) {

            // Medicine Selection
            System.out.print("\nEnter Medicine ID : ");
            int medicineId = scanner.nextInt();
            scanner.nextLine();

            Medicine medicine = medicineDAO.getMedicineById(medicineId);

            if (medicine == null) {
                System.out.println("Medicine not found!");
                continue;
            }

            System.out.println("\nMedicine Found:");
            System.out.println(medicine);

            // Quantity
            System.out.print("\nEnter Quantity : ");
            int quantity = scanner.nextInt();
            scanner.nextLine();

            // Stock Validation
            if (quantity > medicine.getQuantityInStock()) {
                System.out.println("Insufficient Stock!");
                continue;
            }

            // Calculate Subtotal
            double subTotal = medicine.getSellingPrice() * quantity;

            System.out.println("Subtotal : ₹" + subTotal);

            // Create Bill Item
            BillItem billItem = new BillItem(
                    0,
                    0,
                    medicine.getMedicineId(),
                    quantity,
                    medicine.getSellingPrice(),
                    subTotal
            );

            billItems.add(billItem);

            System.out.print("\nAdd another medicine? (Y/N): ");
            String choice = scanner.nextLine();

            if (!choice.equalsIgnoreCase("Y")) {
                break;
            }
        }
        System.out.println("\nTotal Medicines Added : " + billItems.size());

        double totalAmount = 0;
        for(BillItem item : billItems){
            totalAmount += item.getSubTotal();
        }
        System.out.println("Total Amount : ₹" +totalAmount);

        System.out.print("Enter Discount : ₹");
        double discount = scanner.nextDouble();
        scanner.nextLine();

        if(discount < 0 || discount > totalAmount){
            System.out.println("Invalid Discount!");
            return;
        }
        double finalAmount = totalAmount - discount;

        System.out.println("=================================");
        System.out.println("Total Amount : ₹" + totalAmount);
        System.out.println("Discount    : ₹" + discount);
        System.out.println("Final Amount: ₹" + finalAmount);
        System.out.println("=================================");

        Bill bill = new Bill(
                0,
                null,
                customer.getCustomerId(),
                1,
                totalAmount,
                discount,
                finalAmount
        );

        int billId = billDAO.createBill(bill);

        if (billId == -1) {
            System.out.println("Failed to Create Bill!");
            return;
        }

        System.out.println("\n=================================");
        System.out.println("Bill Created Successfully!");
        System.out.println("Generated Bill ID : " + billId);
        System.out.println("=================================");

        for (BillItem item : billItems) {

            item.setBillId(billId);

            boolean inserted = billDAO.addBillItem(item);

            if (!inserted) {
                System.out.println("Failed to save Bill Item!");
                return;
            }
        }
        for(BillItem item : billItems){

            Medicine medicine = medicineDAO.getMedicineById(item.getMedicineId());

            int newStock = medicine.getQuantityInStock() - item.getQuantity();

            boolean updated = medicineDAO.updateMedicineStock(
                    medicine.getMedicineId(),
                    newStock);

            if (!updated) {
                System.out.println("Failed to Update Stock!");
                return;
            }
        }

        System.out.println("\n==========================================");
        System.out.println("       MEDICAL STORE INVOICE");
        System.out.println("==========================================");
        System.out.println("Bill ID          : " + billId);
        System.out.println("Customer         : " + customer.getCustomerName());
        System.out.println("Items Purchased  : " + billItems.size());
        System.out.println("Total Amount     : ₹" + totalAmount);
        System.out.println("Discount         : ₹" + discount);
        System.out.println("Final Amount     : ₹" + finalAmount);
        System.out.println("==========================================");

        System.out.println("All Bill Items Saved Successfully!");
        System.out.println("Billing Completed Successfully!");
        System.out.println("Medicine Stock Updated!");
        System.out.println("Thank you for visiting!");
    }

    //View all Bills
    public void viewAllBills(){

        List<Bill> billList = billDAO.getAllBills();

        if(billList.isEmpty()){
            System.out.println("No bill found!");
            return;
        }
        System.out.println("""
                ==============================================================================================
                Bill ID  Bill date            CustomerID   UserID   Total-Amount Discount   Final Amount
                ==============================================================================================
                """);

        for(Bill bill : billList){
            System.out.println(bill);
        }
        System.out.println("==========================================================================================");
    }

    //View Bill by ID
    public void viewBillById(){

        System.out.print("Enter Bill ID : ");
        int billId = scanner.nextInt();
        scanner.nextLine();

        Bill bill = billDAO.getBillById(billId);
        if(bill == null){
            System.out.println("Bill Not Found!");
            return;
        }
        System.out.println("Bill ID : " +bill.getBillId());
        System.out.println("Bill Date : " +bill.getBillDate());
        System.out.println("Customer ID : " +bill.getCustomerId());
        System.out.println("User ID :" +bill.getUserId());
        System.out.println("Total Amount : " +bill.getTotalAmount());
        System.out.println("Discount : " +bill.getDiscount());
        System.out.println("Final Amount :" +bill.getFinalAmount());

        List<BillItem> billItems = billDAO.getBillItems(billId);

        if(billItems.isEmpty()){
            System.out.println("No Medicine found for this Bill ID!");
            return;
        }
        System.out.println("""
                =====================================================
                ------------------PURCHASED MEDICINE-----------------
                =====================================================
                -----------------------------------------------------
                Medicine Name     Quantity     Price     SubTotal
                -----------------------------------------------------
                """);
        for(BillItem item : billItems){

            Medicine medicine = medicineDAO.getMedicineById(item.getMedicineId());

            System.out.printf(
                    "%-22s %-8d ₹%-11.2f ₹%.2f%n",
                    medicine.getMedicineName(),
                    item.getQuantity(),
                    item.getPrice(),
                    item.getSubTotal()
            );
        }
        System.out.println("----------------------------------------------------");
    }

    public void viewBillByCustomerName(){

        System.out.println("Enter Customer Name : ");
        String customerName = scanner.nextLine();

        List<Bill> billList = billDAO.searchBillByCustomerName(customerName);

        if(billList.isEmpty()){
            System.out.println("Bill Not Found!");
            return;
        }
        System.out.println("""
            =========================================================================================
            Bill ID   Bill Date           CustomerID  User ID   Total        Discount    Final
            =========================================================================================
            """);
        for (Bill bill : billList) {
            System.out.println(bill);
        }
        System.out.println("===================================================================================");
    }
}