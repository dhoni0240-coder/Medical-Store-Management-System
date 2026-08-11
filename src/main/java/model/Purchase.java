package model;

import java.time.LocalDate;

public class Purchase{

    private int purchaseId;
    private int supplierId;
    private int userId;
    private LocalDate purchaseDate;
    private double totalAmount;

    //Constructor
    public Purchase(
            int purchaseId,
            int supplierId,
            int userId,
            LocalDate purchaseDate,
            double totalAmount
    ){
        this.purchaseId = purchaseId;
        this.supplierId = supplierId;
        this.userId = userId;
        this.purchaseDate = purchaseDate;
        this.totalAmount = totalAmount;
    }

    //getter and setter

    //1
    public int getPurchaseId(){
        return purchaseId;
    }
    public void setPurchaseId(int purchaseId){
        this.purchaseId = purchaseId;
    }

    //2
    public int getSupplierId(){
        return supplierId;
    }
    public void setSupplierId(int supplierId){
        this.supplierId = supplierId;
    }

    //3
    public int getUserId(){
        return userId;
    }
    public void setUserId(int userId){
        this.userId = userId;
    }

    //4
    public LocalDate getPurchaseDate(){
        return purchaseDate;
    }
    public void setPurchaseDate(LocalDate purchaseDate){
        this.purchaseDate = purchaseDate;
    }

    //5
    public double getTotalAmount(){
        return totalAmount;
    }
    public void setTotalAmount(double totalAmount){
        this.totalAmount = totalAmount;
    }

    //toString
    @Override
    public String toString(){
        return "Purchase{" +
                "PurchaseId=" +purchaseId+ '\''+
                "SupplierId=" +supplierId+ '\''+
                "User Id=" +userId+ '\''+
                "PurchaseDate=" +purchaseDate+ '\''+
                "totalAmount=" +totalAmount+
                '}';
    }
}