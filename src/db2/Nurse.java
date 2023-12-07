package db2;

import java.sql.*;
import java.util.ArrayList;

import static db2.Patient.ExecuteStatement;
import static db2.Patient.connectDB;

public class Nurse {
    private static Connection con;
    private final int nurseID;
    private String name;
    private int salary;
    private String shift;
    private String address;
    private String gender;
    private String phoneNumber;

    public Nurse(int nurseID, String name, int salary, String shift, String address, String gender, String phoneNumber) {
        this.nurseID = nurseID;
        this.name = name;
        this.salary = salary;
        this.shift = shift;
        this.address = address;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
    }

    public static void insertData(Nurse rc) {
        Connection con;
        try {
            System.out.println("Insert into nurse values(" +
                    rc.getNurseID() + ",'"
                    + rc.getName() + "',"
                    + rc.getSalary() + ",'"
                    + rc.getShift() + "','"
                    + rc.getAddress() + "','"
                    + rc.getGender() + "','"
                    + rc.getPhoneNumber() + "')"
            );

            con = connectDB();
            ExecuteStatement("Insert into nurse values(" +
                    rc.getNurseID() + ",'"
                    + rc.getName() + "',"
                    + rc.getSalary() + ",'"
                    + rc.getShift() + "','"
                    + rc.getAddress() + "','"
                    + rc.getGender() + "','"
                    + rc.getPhoneNumber() + "')"
            );

            con.close();
            System.out.println("Connection closed");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void getData(ArrayList<Nurse> data) throws SQLException, ClassNotFoundException {
        String SQL;
        con = connectDB();
        System.out.println("Connection established");

        SQL = "select * from nurse order by nurseID";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(SQL);

        while (rs.next())
            data.add(new Nurse(
                    Integer.parseInt(rs.getString(1)),
                    rs.getString(2),
                    Integer.parseInt(rs.getString(3)),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7))
            );

        rs.close();
        stmt.close();

        con.close();
        System.out.println("Connection closed" + data.size());
    }

    public int getNurseID() {
        return nurseID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void deleteRow(Nurse row) {
        try {
            System.out.println("delete from  nurse where nurseID=" + row.getNurseID() + ";");
            con = connectDB();
            ExecuteStatement("delete from  nurse where nurseID=" + row.getNurseID() + ";");
            con.close();
            System.out.println("Connection closed");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updateName(int nurseID, String newName) {
        try {
            System.out.println("update  nurse set name = '" + newName + "' where nurseID = " + nurseID);
            con = connectDB();
            ExecuteStatement("update  nurse set name = '" + newName + "' where nurseID = " + nurseID + ";");
            con.close();
            System.out.println("Connection closed");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updateSalary(int nurseID, int salary) {
        try {
            System.out.println("update  nurse set salary = " + salary + " where nurseID = " + nurseID);
            con = connectDB();
            ExecuteStatement("update  nurse set salary = " + salary + " where nurseID = " + nurseID + ";");
            con.close();
            System.out.println("Connection closed");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updateShift(int nurseID, String shift) {
        try {
            System.out.println("update  nurse set shift = '" + shift + "' where nurseID = " + nurseID);
            con = connectDB();
            ExecuteStatement("update  nurse set shift = '" + shift + "' where nurseID = " + nurseID + ";");
            con.close();
            System.out.println("Connection closed");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updateGender(int nurseID, String gender) {
        try {
            System.out.println("update  nurse set gender = '" + gender + "' where nurseID = " + nurseID);
            con = connectDB();
            ExecuteStatement("update  nurse set gender = '" + gender + "' where nurseID = " + nurseID + ";");
            con.close();
            System.out.println("Connection closed");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updateAddress(int nurseID, String address) {
        try {
            System.out.println("update  nurse set address = '" + address + "' where nurseID = " + nurseID);
            con = connectDB();
            ExecuteStatement("update  nurse set address = '" + address + "' where nurseID = " + nurseID + ";");
            con.close();
            System.out.println("Connection closed");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updatePhoneNumber(int nurseID, String phoneNumber) {
        try {
            System.out.println("update  nurse set phoneNumber = '" + phoneNumber + "' where nurseID = " + nurseID);
            con = connectDB();
            ExecuteStatement("update  nurse set phoneNumber = '" + phoneNumber + "' where nurseID = " + nurseID + ";");
            con.close();
            System.out.println("Connection closed");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

