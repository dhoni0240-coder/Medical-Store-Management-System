package app;

import dao.MedicineDAO;
import model.Medicine;

import java.util.List;

public class Main{
    public static void main(String[] args){

        MedicineDAO medicineDAO = new MedicineDAO();

        List<Medicine> medicines = medicineDAO.getAllmedicines();

        for(Medicine medicine : medicines){
            System.out.println(medicine);
        }
    }
}