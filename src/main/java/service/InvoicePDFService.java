package service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
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
            PdfFont unicodeFont = PdfFontFactory.createFont(
                    "C:/Windows/Fonts/arial.ttf",
                    PdfEncodings.IDENTITY_H
            );
            document.setFont(unicodeFont);


            // ==============================
            // STORE HEADER
            // ==============================

            Paragraph storeName = new Paragraph("MEDICAL STORE").setFontSize(20);

            document.add(storeName);

            Paragraph storeSubtitle = new Paragraph("Medical Store Management System");
            document.add(storeSubtitle);

            document.add(new Paragraph("==================================================="));

            // ==============================
            // BILL INFORMATION
            // ==============================

            document.add(new Paragraph("INVOICE INFORMATION").setFontSize(14));
            document.add(new Paragraph("Invoice No. :" +bill.getBillId()+
                    "        Date : " + bill.getBillDate()));

            document.add(new Paragraph("---------------------------------------------------"));

            // ==============================
            // CUSTOMER INFORMATION
            // ==============================

            document.add(new Paragraph("CUSTOMER DETAILS").setFontSize(14));

            document.add(new Paragraph("-----------------------------------------------"));

            if(customer != null){
                document.add(new Paragraph("Name    : " + customer.getCustomerName()));

                document.add(new Paragraph("Phone   : " + customer.getPhone()));

                document.add(new Paragraph("Email   : " + customer.getEmail()));

                document.add(new Paragraph("Address : " + customer.getAddress()));

            }else{
                document.add(new Paragraph("Customer information not available."));
            }
            document.add(new Paragraph("-----------------------------------------------"));

            // ==============================
            // ITEMS TABLE
            // ==============================

            document.add(new Paragraph("\nPurchased Medicines").setFontSize(14));

            Table table = new Table(new float[]{1, 4, 1.5f, 2, 2});

            table.addHeaderCell(new Cell().add(new Paragraph("No."))
                            .setTextAlignment(com.itextpdf.layout.properties.TextAlignment.CENTER));

            table.addHeaderCell(new Cell().add(new Paragraph("Medicine"))
                            .setTextAlignment(com.itextpdf.layout.properties.TextAlignment.CENTER));

            table.addHeaderCell(new Cell().add(new Paragraph("Qty"))
                            .setTextAlignment(com.itextpdf.layout.properties.TextAlignment.CENTER));

            table.addHeaderCell(new Cell().add(new Paragraph("Price"))
                            .setTextAlignment(com.itextpdf.layout.properties.TextAlignment.CENTER));

            table.addHeaderCell(new Cell().add(new Paragraph("Subtotal"))
                            .setTextAlignment(com.itextpdf.layout.properties.TextAlignment.CENTER));

            int itemNumber = 1;

            for (BillItem item : billItems) {

                Medicine medicine = medicineDAO.getMedicineById(item.getMedicineId());

                String medicineName;

                if (medicine != null) {
                    medicineName = medicine.getMedicineName();
                }else{
                    medicineName = "Unknown Medicine";
                }

                // No. - Center
                table.addCell(new Cell().add(new Paragraph(String.valueOf(itemNumber++)))
                                .setTextAlignment(com.itextpdf.layout.properties.TextAlignment.CENTER));

                // Medicine - Left
                table.addCell(new Cell().add(new Paragraph(medicineName))
                                .setTextAlignment(com.itextpdf.layout.properties.TextAlignment.LEFT));

                // Quantity - Center
                table.addCell(new Cell().add(new Paragraph(String.valueOf(item.getQuantity())))
                                .setTextAlignment(com.itextpdf.layout.properties.TextAlignment.CENTER));

                // Price - Right
                table.addCell(new Cell().add(new Paragraph(String.format("₹%.2f", item.getPrice())))
                                .setTextAlignment(com.itextpdf.layout.properties.TextAlignment.RIGHT));

                // Subtotal - Right
                table.addCell(new Cell().add(new Paragraph(String.format("₹%.2f", item.getSubTotal())))
                        .setTextAlignment(com.itextpdf.layout.properties.TextAlignment.RIGHT));
            }

            document.add(table);

            // ==============================
            // BILL SUMMARY
            // ==============================

            document.add(new Paragraph("BILL SUMMARY").setFontSize(14));

            document.add(new Paragraph("-----------------------------------------------"));
            document.add(new Paragraph(String.format("Total Amount : ₹%.2f", bill.getTotalAmount()))
                            .setTextAlignment(
                                    com.itextpdf.layout.properties.TextAlignment.RIGHT));

            document.add(new Paragraph(String.format("Discount     : ₹%.2f", bill.getDiscount()))
                            .setTextAlignment(
                                    com.itextpdf.layout.properties.TextAlignment.RIGHT));

            document.add(new Paragraph(String.format("FINAL AMOUNT : ₹%.2f", bill.getFinalAmount()))
                            .setFontSize(16)
                            .setTextAlignment(
                                    com.itextpdf.layout.properties.TextAlignment.RIGHT));

            document.add(new Paragraph("-----------------------------------------------"));

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