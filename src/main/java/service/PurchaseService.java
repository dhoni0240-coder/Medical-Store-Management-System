package service;

import config.DatabaseConnection;
import dao.PurchaseDAO;
import model.Purchase;
import model.PurchaseItem;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class PurchaseService {

    private final PurchaseDAO purchaseDAO;

    public PurchaseService() {
        purchaseDAO = new PurchaseDAO();
    }

    public int createPurchase(int supplierId,
                              int userId,
                              List<PurchaseItem> purchaseItems) {

        if (purchaseItems == null || purchaseItems.isEmpty()) {
            System.out.println("No purchase items found!");
            return -1;
        }

        double totalAmount = 0;

        // Calculate subtotal and total
        for (PurchaseItem item : purchaseItems) {

            if (item.getQuantity() <= 0) {
                System.out.println("Quantity must be greater than 0!");
                return -1;
            }

            if (item.getPurchasePrice() < 0) {
                System.out.println("Purchase price cannot be negative!");
                return -1;
            }

            double subtotal = item.getQuantity() * item.getPurchasePrice();

            item.setSubtotal(subtotal);
            totalAmount += subtotal;
        }

        Purchase purchase = new Purchase(
                0,
                supplierId,
                userId,
                LocalDate.now(),
                totalAmount
        );

        try (Connection connection =
                     DatabaseConnection.getConnection()) {

            connection.setAutoCommit(false);

            try {

                int purchaseId =
                        purchaseDAO.createPurchase(
                                connection,
                                purchase
                        );

                if (purchaseId == -1) {
                    connection.rollback();
                    return -1;
                }

                for (PurchaseItem item : purchaseItems) {

                    item.setPurchaseId(purchaseId);

                    boolean itemAdded =
                            purchaseDAO.addPurchaseItem(
                                    connection,
                                    item
                            );

                    if (!itemAdded) {
                        connection.rollback();
                        System.out.println(
                                "Failed to add purchase item!"
                        );
                        return -1;
                    }

                    boolean stockUpdated =
                            purchaseDAO.updateMedicineStock(
                                    connection,
                                    item.getMedicineId(),
                                    item.getQuantity()
                            );

                    if (!stockUpdated) {
                        connection.rollback();
                        System.out.println(
                                "Failed to update medicine stock!"
                        );
                        return -1;
                    }
                }

                connection.commit();

                System.out.println(
                        "\nPurchase created successfully!"
                );
                System.out.println(
                        "Purchase ID : " + purchaseId
                );
                System.out.printf(
                        "Total Amount : ₹%.2f%n",
                        totalAmount
                );

                return purchaseId;

            } catch (SQLException e) {

                connection.rollback();

                System.out.println(
                        "Purchase failed! Transaction rolled back."
                );

                e.printStackTrace();

            } finally {
                connection.setAutoCommit(true);
            }

        } catch (SQLException e) {

            System.out.println(
                    "Database connection error!"
            );

            e.printStackTrace();
        }

        return -1;
    }

    //Purchase History
    public List<Purchase> getAllPurchases(){
        return purchaseDAO.getAllPurchases();
    }

    //Purchase Details
    public List<PurchaseItem> getPurchaseItems(int purchaseId) {
        return purchaseDAO.getPurchaseItems(purchaseId);
    }
}

