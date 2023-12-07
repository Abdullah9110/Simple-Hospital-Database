package db2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static db2.Patient.ExecuteStatement;
import static db2.Patient.connectDB;

public class Bill {
    private final int billNumber;
    private int patientID;
    private int amount;
    private String billDate;

    private static Connection con;

    public Bill(int billNumber, int patientID, int amount, String billDate) {
        this.billNumber = billNumber;
        this.patientID = patientID;
        this.amount = amount;
        this.billDate = billDate;
    }

    public int getBillNumber() {
        return billNumber;
    }

    public String getBillDate() {
        return billDate;
    }

    public int getPatientID() {
        return patientID;
    }

    public int getAmount() {
        return amount;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public static void getData(ArrayList<Bill> data) throws SQLException, ClassNotFoundException {
        String SQL;

        con = connectDB();
        System.out.println("Connection established");

        SQL = "select * from bill order by billNumber";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(SQL);


        while (rs.next())
            data.add(new Bill(
                    Integer.parseInt(rs.getString(1)),
                    Integer.parseInt(rs.getString(2)),
                    Integer.parseInt(rs.getString(3)),
                    rs.getString(4)
                    )
            );
        rs.close();
        stmt.close();
        con.close();
        System.out.println("Connection closed" + data.size());

    }


    public static void insertData(Bill rc) {

        try {
            System.out.println("Insert into bill values("+
                    rc.getBillNumber() +","
                    + rc.getPatientID() +","
                    + rc.getAmount() +",'"
                    + rc.getBillDate() + "');"
            );

            con = connectDB();
            ExecuteStatement("Insert into bill values("+
                    rc.getBillNumber() +","
                    + rc.getPatientID() +","
                    + rc.getAmount() +",'"
                    + rc.getBillDate() + "');"
            );

            con.close();
            System.out.println("Connection closed");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void deleteRow(Bill row) {
        try {
            System.out.println("delete from  Bill where billNumber="+row.getBillNumber() + ";");
            con = connectDB();
            ExecuteStatement("delete from  Bill where billNumber="+row.getBillNumber() + ";");
            con.close();
            System.out.println("Connection closed");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updatePatientID(int billNum, int id) {

        try {
            System.out.println("update  bill set patientID = "+ id + " where billNumber = "+ billNum);
            con = connectDB();
            ExecuteStatement("update  bill set patientID = "+ id + " where billNumber = "+ billNum +";");
            con.close();
            System.out.println("Connection closed");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updateAmount(int billNum, int amount) {

        try {
            System.out.println("update  bill set amount = "+ amount + " where billNumber = "+ billNum);
            con = connectDB();
            ExecuteStatement("update  bill set amount = "+ amount + " where billNumber = "+ billNum +";");
            con.close();
            System.out.println("Connection closed");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updateBillDate(int billNumber, String billDate) {
        try {
            System.out.println("update  bill set billDate = '" + billDate + "' where billNumber = " + billNumber);
            con = connectDB();
            ExecuteStatement("update  bill set billDate = '" + billDate + "' where billNumber = " + billNumber + ";");
            con.close();
            System.out.println("Connection closed");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}
