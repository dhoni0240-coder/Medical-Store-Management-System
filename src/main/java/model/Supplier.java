package model;
public class Supplier{
    private int supplierId;
    private String supplierName;
    private String phone;
    private String email;
    private String address;
    private String companyName;

    //Constructor
    public Supplier(int supplierId,
                    String supplierName,
                    String contactPerson,
                    String phone,
                    String email,
                    String address){
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.companyName = companyName;
    }

    //getter & setter
    //1
    public int getSupplierId(){
        return supplierId;
    }
    public void setSupplierId(int supplierId){
        this.supplierId  = supplierId;
    }

    //2
    public String getSupplierName(){
        return supplierName;
    }
    public void setSupplierName(String supplierName){
        this.supplierName = supplierName;
    }

    //3
    public String getPhone(){
        return phone;
    }
    public void setPhone(String phone){
        this.phone = phone;
    }

    //4
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    //5
    public String getAddress(){
        return address;
    }
    public void setAddress(String address){
        this.address = address;
    }

    //6
    public String getCompanyName(){
        return companyName;
    }
    public void setCompanyName(String companyName){
        this.companyName = companyName;
    }

    //toString

    @Override
    public String toString(){
        return "Supplier{" +
                "supplierId=" +supplierId+ '\''+
                "supplierName=" +supplierName+ '\''+
                "phone=" +phone+ '\''+
                "email=" +email+ '\''+
                "address=" +address+ '\''+
                "companyName=" +companyName+ '\''+
                '}';
    }
}