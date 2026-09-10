package model;

public class MedicineSalesStats{
    private int medicineId;
    private String medicineName;
    private int totalSold;

    public MedicineSalesStats(int medicineId, String medicineName, int totalSold){
        this.medicineId = medicineId;
        this.medicineName = medicineName;
        this.totalSold = totalSold;
    }

    public int getMedicineId(){
        return medicineId;
    }

    public String getMedicineName(){
        return medicineName;
    }

    public int getTotalSold(){
        return totalSold;
    }

    @Override
    public String toString(){
        return "MedicineSalesStats={" +
                "medicineId=" +medicineId+
                ",medicineName='" +medicineName+ '\''+
                ",totalSold=" +totalSold+
                '}';
    }
}