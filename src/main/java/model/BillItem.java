package model;

public class BillItem{
    private int billItemId;
    private int billId;
    private int medicineId;
    private int quantity;
    private double price;
    private double subTotal;

    //Constructor
    public BillItem(
            int billItemId,
            int billId,
            int medicineId,
            int quantity,
            double price,
            double subTotal
    ){
        this.billItemId = billItemId;
        this.billId =billId;
        this.medicineId = medicineId;
        this.quantity = quantity;
        this.price = price;
        this.subTotal = subTotal;
    }

    //getter  setter
    public int getBillItemId() {
        return billItemId;
    }
    public void setBillItemId(int billItemId) {
        this.billItemId = billItemId;
    }

    public int getBillId() {
        return billId;
    }
    public void setBillId(int billId) {
        this.billId = billId;
    }

    public int getMedicineId() {
        return medicineId;
    }
    public void setMedicineId(int medicineId) {
        this.medicineId = medicineId;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public double getSubTotal() {
        return subTotal;
    }
    public void setSubTotal(double subTotal){
        this.subTotal = subTotal;
    }

    //toString
    @Override
    public String toString() {
        return "BillItem{" +
                "billItemId=" + billItemId + '\''+
                ", billId=" + billId + '\''+
                ", medicineId=" + medicineId + '\''+
                ", quantity=" + quantity + '\''+
                ", price=" + price + '\''+
                ", subtotal=" + subTotal +
                '}';
    }
}