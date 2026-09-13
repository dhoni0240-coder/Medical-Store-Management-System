package service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import java.awt.Desktop;
import dao.BillDAO;
import dao.CustomerDAO;
import dao.MedicineDAO;
import model.Bill;
import model.BillItem;
import model.Customer;
import model.Medicine;

import java.io.File;
import java.util.List;

public class InvoicePDFService {

    private final BillDAO billDAO;
    private final CustomerDAO customerDAO;
    private final MedicineDAO medicineDAO;

    public InvoicePDFService() {
        this.billDAO = new BillDAO();
        this.customerDAO = new CustomerDAO();
        this.medicineDAO = new MedicineDAO();
    }

    public void generateInvoice(int billId) {

        try {

            // Get bill
            Bill bill = billDAO.getBillById(billId);

            if (bill == null) {
                System.out.println("Bill not found!");
                return;
            }

            // Get customer
            Customer customer = customerDAO.getCustomerById(bill.getCustomerId());

            // Get bill items
            List<BillItem> billItems = billDAO.getBillItems(billId);

            // Create invoices folder
            File invoiceDirectory = new File("invoices");

            if (!invoiceDirectory.exists()) {
                invoiceDirectory.mkdirs();
            }

            // PDF file path
            String filePath = "invoices/invoice_" + billId + ".pdf";

            // Create PDF
            PdfWriter writer = new PdfWriter(filePath);

            PdfDocument pdfDocument = new PdfDocument(writer);

            Document document = new Document(pdfDocument);


            // ==============================
            // STORE HEADER
            // ==============================

            Paragraph storeName = new Paragraph("MEDICAL STORE").setFontSize(20);

            document.add(storeName);

            document.add(new Paragraph("Medical Store Management System"));

            document.add(new Paragraph("------------------------------------------------"));

            // ==============================
            // BILL INFORMATION
            // ==============================

            document.add(new Paragraph("Invoice No. : " + bill.getBillId()));

            document.add(new Paragraph("Date        : " + bill.getBillDate()));

            // ==============================
            // CUSTOMER INFORMATION
            // ==============================

            document.add(new Paragraph("\nCustomer Details").setFontSize(14));

            if (customer != null) {
                document.add(new Paragraph("Name    : " + customer.getCustomerName()));
                document.add(new Paragraph("Phone   : " + customer.getPhone()));
                document.add(new Paragraph("Email   : " + customer.getEmail()));
                document.add(new Paragraph("Address : " + customer.getAddress()));
            }

            // ==============================
            // ITEMS TABLE
            // ==============================

            document.add(new Paragraph("\nPurchased Medicines").setFontSize(14));

            Table table = new Table(new float[]{1, 4, 1.5f, 2, 2});

            table.addHeaderCell(new Cell().add(new Paragraph("No.")));

            table.addHeaderCell(new Cell().add(new Paragraph("Medicine")));

            table.addHeaderCell(new Cell().add(new Paragraph("Qty")));

            table.addHeaderCell(new Cell().add(new Paragraph("Price")));

            table.addHeaderCell(new Cell().add(new Paragraph("Subtotal")));


            int itemNumber = 1;

            for (BillItem item : billItems) {

                Medicine medicine = medicineDAO.getMedicineById(item.getMedicineId());

                String medicineName;

                if (medicine != null) {
                    medicineName = medicine.getMedicineName();
                } else {
                    medicineName = "Unknown Medicine";
                }

                table.addCell(new Cell().add(new Paragraph(String.valueOf(itemNumber++))));

                table.addCell(new Cell().add(new Paragraph(medicineName)));

                table.addCell(new Cell().add(new Paragraph(String.valueOf(item.getQuantity()))));

                table.addCell(new Cell().add(new Paragraph(String.format("₹%.2f", item.getPrice()))));

                table.addCell(new Cell().add(new Paragraph(String.format("₹%.2f", item.getSubTotal()))));
            }

            document.add(table);

            // ==============================
            // BILL SUMMARY
            // ==============================

            document.add(
                    new Paragraph("\n")
            );

            document.add(new Paragraph(String.format("Total Amount : ₹%.2f", bill.getTotalAmount())));

            document.add(new Paragraph(String.format("Discount     : ₹%.2f", bill.getDiscount())));

            document.add(new Paragraph(String.format("Final Amount : ₹%.2f", bill.getFinalAmount())));


            // ==============================
            // FOOTER
            // ==============================

            document.add(new Paragraph("\nThank You! Visit Again."));


            // Close PDF
            document.close();

            System.out.println("\nInvoice PDF generated successfully!");

            System.out.println("File: " + filePath);

        } catch (Exception e) {
            System.out.println("\nError generating invoice PDF!");
            e.printStackTrace();
        }
    }
    public void printInvoice(int billId) {
        String filePath = "invoices/invoice_" +billId+ ".pdf";

        File invoiceFile = new File(filePath);
        if(!invoiceFile.exists()){
            System.out.println("Invoice pdf not found!");
        }

        Desktop desktop = Desktop.getDesktop();

        try{
            if(!Desktop.isDesktopSupported()){
                System.out.println("Printing is not supported in this system!");
                return;
            }
            desktop.print(invoiceFile);
            System.out.println("Invoice file sent to printer successfully!");

        }catch(Exception e){
            System.out.println("Error printing invoice PDF!");
            e.printStackTrace();
        }
    }
}