package model;

public class Bill{
    private int billId;
    private String billDate;
    private int customerId;
    private int userId;
    private double totalAmount;
    private double discount;
    private double finalAmount;

    public Bill(int billId,
                String billDate,
                int customerId,
                int userId,
                double totalAmount,
                double discount,
                double finalAmount
                ){
        this.billId = billId;
        this.billDate = billDate;
        this.customerId = customerId;
        this.userId = userId;
        this.totalAmount = totalAmount;
        this.discount = discount;
        this.finalAmount = finalAmount;
    }

    //getter & getter
    //1
    public int getBillId(){
        return billId;
    }
    public void setBillId(int billId){
        this.billId = billId;
    }

    //2
    public String getBillDate(){
        return billDate;
    }
    public void setBillDate(String billDate){
        this.billDate = billDate;
    }

    //3
    public int getCustomerId(){
        return customerId;
    }
    public void setCustomerId(int customerId){
        this.customerId = customerId;
    }

    //4
    public int getUserId(){
        return userId;
    }
    public void setUserId(int userId){
        this.userId = userId;
    }

    //5
    public double getTotalAmount(){
        return totalAmount;
    }
    public void setTotalAmount(double totalAmount){
        this.totalAmount = totalAmount;
    }

    //6
    public double getDiscount(){
        return discount;
    }
    public void setDiscount(double discount){
        this.discount = discount;
    }

    //7
    public double getFinalAmount(){
        return finalAmount;
    }
    public void setFinalAmount(double finalAmount){
        this.finalAmount = finalAmount;
    }

    //toString
    @Override
    public String toString(){
        return "Bill{" +
                "billId=" +billId+ '\''+
                ", billDate=" +billDate+ '\''+
                ", customerId=" +customerId+ '\''+
                ", userId=" +userId+ '\''+
                ", totalAmount=" +totalAmount+ '\''+
                ", discount=" +discount+ '\''+
                ", finalAmount=" +totalAmount+
                '}';
    }
}