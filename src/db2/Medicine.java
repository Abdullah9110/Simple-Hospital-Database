package db2;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static db2.Patient.ExecuteStatement;
import static db2.Patient.connectDB;

public class Medicine {

    private final int medicineID;
    private String medicineName;
    private int price;

    private static Connection con;

    public Medicine(int medicineID, String medicineName, int price) {
        this.medicineID = medicineID;
        this.medicineName = medicineName;
        this.price = price;
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

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public static void getData(ArrayList<Medicine> data) throws SQLException, ClassNotFoundException {
        String SQL;

        con = connectDB();
        System.out.println("Connection established");

        SQL = "select * from medicine order by medicineID";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(SQL);


        while (rs.next())
            data.add(new Medicine(
                    Integer.parseInt(rs.getString(1)),
                    rs.getString(2),
                    Integer.parseInt(rs.getString(3)))
            );
        rs.close();
        stmt.close();
        con.close();
        System.out.println("Connection closed " + data.size());

    }


    public static void insertData(Medicine rc) {

        try {
            System.out.println("Insert into medicine (medicineID, medicineName,price) values("+
                    rc.getMedicineID() +",'"
                    + rc.getMedicineName() +"',"
                    + rc.getPrice()+")");

            con = connectDB();
            ExecuteStatement("Insert into medicine (medicineID, medicineName,price) values("+
                    rc.getMedicineID() +",'"
                    + rc.getMedicineName() +"',"
                    + rc.getPrice()+")");

            con.close();
            System.out.println("Connection closed");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void deleteRow(Medicine row) {

        try {
            System.out.println("delete from  medicine where medicineID="+row.getMedicineID() + ";");
            con = connectDB();
            ExecuteStatement("delete from  medicine where medicineID="+row.getMedicineID() + ";");
            con.close();
            System.out.println("Connection closed");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updateMedicineName(int medicineID, String name) {

        try {
            System.out.println("update  medicine set medicineName = '"+name + "' where medicineID = "+medicineID);
            con = connectDB();
            ExecuteStatement("update  medicine set medicineName = '"+name + "' where medicineID = "+medicineID+";");
            con.close();
            System.out.println("Connection closed");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updatePrice(int medicineID, int price) {

        try {
            System.out.println("update  medicine set price = "+ price + " where medicineID = "+medicineID);
            con = connectDB();
            ExecuteStatement("update  medicine set price = "+ price + " where medicineID = "+medicineID+";");
            con.close();
            System.out.println("Connection closed");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Medicine{" +
                "medicineID=" + medicineID +
                ", medicineName='" + medicineName + '\'' +
                ", price=" + price +
                '}';
    }
}

