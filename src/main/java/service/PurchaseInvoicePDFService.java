package service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Table;

import model.Purchase;
import model.PurchaseItem;
import model.Supplier;
import model.Medicine;

import dao.PurchaseDAO;
import dao.SupplierDAO;
import dao.MedicineDAO;
import java.io.File;

public class PurchaseInvoicePDFService {

    private final PurchaseDAO purchaseDAO;
    private final SupplierDAO supplierDAO;
    private final MedicineDAO medicineDAO;

    public PurchaseInvoicePDFService(){
        this.purchaseDAO = new PurchaseDAO();
        this.supplierDAO = new SupplierDAO();
        this.medicineDAO = new MedicineDAO();
    }

    public void generatePurchaseInvoice(int purchaseId) {

        try {
            // Get purchase information
            Purchase purchase = purchaseDAO.getPurchaseById(purchaseId);

            if (purchase == null) {
                System.out.println("Purchase not found!");
                return;
            }

            // Get supplier information
            Supplier supplier = supplierDAO.getSupplierById(purchase.getSupplierId());

            if (supplier == null) {
                System.out.println("Supplier not found!");
                return;
            }

            // Get purchased medicines
            java.util.List<PurchaseItem> purchaseItems = purchaseDAO.getPurchaseItems(purchaseId);

            if(purchaseItems.isEmpty()) {
                System.out.println("No purchase items found!");
                return;
            }

            // Create purchase-invoices folder
            File invoiceDirectory = new File("purchase-invoices");

            if (!invoiceDirectory.exists()) {
                invoiceDirectory.mkdirs();
            }

            // PDF file path
            String filePath = "purchase-invoices/purchase_invoice_" + purchaseId + ".pdf";

            // Create PDF
            PdfWriter writer = new PdfWriter(filePath);

            PdfDocument pdfDocument = new PdfDocument(writer);

            Document document = new Document(pdfDocument);

            // Store information
            document.add(new Paragraph("MEDICAL STORE"));

            document.add(new Paragraph("PURCHASE INVOICE"));

            document.add(new Paragraph("Purchase Invoice No. : " + purchase.getPurchaseId()));

            document.add(new Paragraph("Purchase Date : " + purchase.getPurchaseDate()));

            // Supplier information
            document.add(new Paragraph("SUPPLIER DETAILS"));

            document.add(new Paragraph("Supplier Name : " + supplier.getSupplierName()));

            document.add(new Paragraph("Company : " + supplier.getCompanyName()));

            document.add(new Paragraph("Phone : " + supplier.getPhone()));

            document.add(new Paragraph("Email : " + supplier.getEmail()));

            document.add(new Paragraph("Address : " + supplier.getAddress()));


            // Purchase items table
            document.add(new Paragraph("PURCHASE ITEMS"));

            Table table = new Table(5);

            table.addCell(new Cell().add(new Paragraph("No.")));

            table.addCell(new Cell().add(new Paragraph("Medicine")));

            table.addCell(new Cell().add(new Paragraph("Quantity")));

            table.addCell(new Cell().add(new Paragraph("Purchase Price")));

            table.addCell(new Cell().add(new Paragraph("Subtotal")));


            int serialNumber = 1;

            for (PurchaseItem item : purchaseItems) {

                table.addCell(new Cell().add(new Paragraph(String.valueOf(serialNumber++))));

                table.addCell(new Cell().add(new Paragraph(item.getMedicineName())));

                table.addCell(new Cell().add(new Paragraph(String.valueOf(item.getQuantity()))));

                table.addCell(new Cell().add(new Paragraph("₹" + String.format("%.2f", item.getPurchasePrice()))));

                table.addCell(new Cell().add(new Paragraph("₹" + String.format("%.2f", item.getSubTotal()))));
            }
            document.add(table);


            // Total
            document.add(new Paragraph("Total Purchase Amount : ₹" + String.format("%.2f", purchase.getTotalAmount())));

            document.add(new Paragraph("Thank you!"));
            document.close();

            System.out.println("\nPurchase Invoice PDF generated successfully!");

            System.out.println("File : " + filePath);

        }catch (Exception e) {
            System.out.println("Error generating purchase invoice PDF!");
            e.printStackTrace();
        }
    }
}