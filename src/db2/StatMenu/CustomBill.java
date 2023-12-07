package db2.StatMenu;

import db2.Bill;
import db2.PatientMenu.CustomMedicine;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static db2.Patient.connectDB;

public class CustomBill {
    private final int billNumber;
    private int amount;
    private int patientID;
    private String patientName;
    private String billDate;

    public CustomBill(int billNumber, int amount, int patientID, String patientName, String billDate) {
        this.billNumber = billNumber;
        this.amount = amount;
        this.patientID = patientID;
        this.patientName = patientName;
        this.billDate = billDate;
    }

    public int getBillNumber() {
        return billNumber;
    }

    public int getAmount() {
        return amount;
    }

    public int getPatientID() {
        return patientID;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public static void getDataWithCon(ArrayList<CustomBill> data, String str) throws SQLException, ClassNotFoundException {
        String SQL;

        Connection con = connectDB();
        System.out.println("Connection established");

        SQL = str;
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(SQL);

        while (rs.next())
            data.add(new CustomBill(
                    Integer.parseInt(rs.getString(1)),
                    Integer.parseInt(rs.getString(2)),
                    Integer.parseInt(rs.getString(3)),
                    rs.getString(4),
                    rs.getString(5))
            );
        rs.close();
        stmt.close();
        con.close();
        System.out.println("Connection closed " + data.size());

    }
}
