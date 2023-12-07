package db2.PatientMenu;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static db2.Patient.connectDB;

public class CustomMedicine {
    private int medicineID;
    private String medicineName;
    private int price;
    private int count;
    private int total;

    public CustomMedicine(int medicineID, String medicineName, int price, int count, int total) {
        this.medicineID = medicineID;
        this.medicineName = medicineName;
        this.price = price;
        this.count = count;
        this.total = total;
    }

    public int getMedicineID() {
        return medicineID;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public int getPrice() {
        return price;
    }

    public int getCount() {
        return count;
    }

    public int getTotal() {
        return total;
    }

    public void setMedicineID(int medicineID) {
        this.medicineID = medicineID;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public static void getDataWithCon(ArrayList<CustomMedicine> data, String str) throws SQLException, ClassNotFoundException {
        String SQL;

        Connection con = connectDB();
        System.out.println("Connection established");

        SQL = str;
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(SQL);

        while (rs.next())
            data.add(new CustomMedicine(
                    Integer.parseInt(rs.getString(1)),
                    rs.getString(2),
                    Integer.parseInt(rs.getString(3)),
                    Integer.parseInt(rs.getString(4)),
                    Integer.parseInt(rs.getString(5)))
            );
        rs.close();
        stmt.close();
        con.close();
        System.out.println("Connection closed " + data.size());

    }
}
