package model;

public class PurchaseItem{
    private int purchaseItemId;
    private int purchaseId;
    private int medicineId;
    private int quantity;
    private double purchasePrice;
    private double subTotal;
    private String medicineName;

    public PurchaseItem(
            int purchaseItemId,
            int purchaseId,
            int medicineId,
            int quantity,
            double purchasePrice,
            double subTotal
    ){
        this.purchaseItemId = purchaseItemId;
        this.purchaseId = purchaseId;
        this.medicineId = medicineId;
        this.quantity = quantity;
        this.purchasePrice = purchasePrice;
        this.subTotal = subTotal;
    }
    //getter and setter

    //1
    public int getPurchaseItemId(){
        return purchaseItemId;
    }
    public void setPurchaseItemId(int purchaseItemId){
        this.purchaseItemId = purchaseItemId;
    }

    //2
    public int getPurchaseId(){
        return purchaseId;
    }
    public void setPurchaseId(int purchaseId){
        this.purchaseId = purchaseId;
    }

    //3
    public int getMedicineId(){
        return medicineId;
    }
    public void setMedicineId(int medicineId){
        this.medicineId = medicineId;
    }

    //4
    public int getQuantity(){
        return quantity;
    }
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    //5
    public double getPurchasePrice(){
        return purchasePrice;
    }
    public void setPurchasePrice(double purchasePrice){
        this.purchasePrice = purchasePrice;
    }

    //6
    public double getSubTotal(){
        return subTotal;
    }
    public void setSubtotal(double subTotal){
        this.subTotal = subTotal;
    }

    //7
    public String getMedicineName(){
        return medicineName;
    }
    public void setMedicineName(String medicineName){
        this.medicineName = medicineName;
    }
}