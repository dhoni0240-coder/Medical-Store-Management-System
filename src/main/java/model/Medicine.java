package model;

public class Medicine {
    private int medicineId;
    private String medicineName;
    private String formula;
    private String category;
    private String batchNo;
    private String manufactureDate;
    private String expiryDate;
    private double purchasePrice;
    private double sellingPrice;
    private int quantityInStock;
    private String rackNo;
    private int supplierId;

    //Constructor
    public Medicine(int medicineId, String medicineName, String formula, String category, String batchNo, String manufactureDate, String expiryDate, double purchasePrice, double sellingPrice, int quantityInStock, String rackNo, int supplierId){
        this.medicineId = medicineId;
        this.medicineName = medicineName;
        this.formula  = formula;
        this.category = category;
        this.batchNo = batchNo;
        this.manufactureDate = manufactureDate;
        this.expiryDate = expiryDate;
        this.purchasePrice = purchasePrice;
        this.sellingPrice = sellingPrice;
        this.quantityInStock = quantityInStock;
        this.rackNo = rackNo;
        this.supplierId = supplierId;
    }


    //Getter and Setter
   public int getMedicineId(){
        return medicineId;
   }
   public void setMedicineId(int medicineId){
        this.medicineId = medicineId;
   }

   public String getMedicineName(){
        return medicineName;
   }
   public void setMedicineName(String medicineName){
        this.medicineName = medicineName;
   }

   public String getFormula(){
        return formula;
   }

   public String getCategory(){
        return category;
   }
   public void setCategory(String category){
        this.category = category;
   }

   public String getBatchNo(){
        return batchNo;
   }
   public void setBatchNo(String batchNo){
        this.batchNo = batchNo;
   }

   public String getManufactureDate(){
        return manufactureDate;
   }
   public void setManufactureDate(String manufactureDate){
        this.manufactureDate = manufactureDate;
   }

   public String getExpiryDate(){
        return expiryDate;
   }
   public void setExpiryDate(String expiryDate){
        this.expiryDate = expiryDate;
   }

   public double getPurchasePrice(){
       return purchasePrice;
   }
   public void setPurchasePrice(double purchasePrice){
        this.purchasePrice = purchasePrice;
   }

   public double getSellingPrice(){
        return sellingPrice;
   }
   public void setSellingPrice(double sellingPrice){
        this.sellingPrice = sellingPrice;
   }

   public int getQuantityInStock(){
        return quantityInStock;
   }
   public void setQuantityInStock(int quantityInStock){
        this.quantityInStock = quantityInStock;
   }

   public String getRackNo(){
        return rackNo;
   }
   public void setRackNo(String rackNo){
        this.rackNo = rackNo;
   }

   public int getSupplierId(){
        return supplierId;
   }
   public void setSupplierId(int supplierId){
        this.supplierId = supplierId;
   }

   //toString
    @Override
    public String toString() {
        return "Medicine{" +
                "medicineId=" + medicineId +
                ", medicineName='" + medicineName + '\'' +
                ", formula='" + formula + '\'' +
                ", category='" + category + '\'' +
                ", batchNo='" + batchNo + '\'' +
                ", manufactureDate='" + manufactureDate + '\'' +
                ", expiryDate='" + expiryDate + '\'' +
                ", purchasePrice=" + purchasePrice +
                ", sellingPrice=" + sellingPrice +
                ", quantityInStock=" + quantityInStock +
                ", rackNo='" + rackNo + '\'' +
                ", supplierId=" + supplierId +
                '}';
    }
}

