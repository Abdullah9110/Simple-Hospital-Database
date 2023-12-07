package db2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static db2.Patient.ExecuteStatement;
import static db2.Patient.connectDB;

public class Doctor {
    private static Connection con;
    private final int doctorID;
    private String name;
    private int salary;
    private String specialty;
    private String address;
    private String gender;
    private String phoneNumber;

    public Doctor(int doctorID, String name, int salary, String specialty, String address, String gender, String phoneNumber) {
        this.doctorID = doctorID;
        this.name = name;
        this.salary = salary;
        this.specialty = specialty;
        this.address = address;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
    }

    public static void getData(ArrayList<Doctor> data) throws SQLException, ClassNotFoundException {
        String SQL;
        con = connectDB();
        System.out.println("Connection established");

        SQL = "select * from doctor order by doctorID";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(SQL);


        while (rs.next())
            data.add(new Doctor(
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

    public static void insertData(Doctor rc) {
        Connection con;
        try {
            System.out.println("Insert into doctor values(" +
                    rc.getDoctorID() + ",'"
                    + rc.getName() + "',"
                    + rc.getSalary() + ",'"
                    + rc.getSpecialty() + "','"
                    + rc.getAddress() + "','"
                    + rc.getGender() + "','"
                    + rc.getPhoneNumber() + "')"
            );

            con = connectDB();
            ExecuteStatement("Insert into doctor values(" +
                    rc.getDoctorID() + ",'"
                    + rc.getName() + "',"
                    + rc.getSalary() + ",'"
                    + rc.getSpecialty() + "','"
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

    public int getDoctorID() {
        return doctorID;
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

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
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

    public void deleteRow(Doctor row) {
        try {
            System.out.println("delete from  doctor where doctorID=" + row.getDoctorID() + ";");
            con = connectDB();
            ExecuteStatement("delete from  doctor where doctorID=" + row.getDoctorID() + ";");
            con.close();
            System.out.println("Connection closed");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updateName(int doctorID, String newName) {
        try {
            System.out.println("update  doctor set name = '" + newName + "' where doctorID = " + doctorID);
            con = connectDB();
            ExecuteStatement("update  doctor set name = '" + newName + "' where doctorID = " + doctorID + ";");
            con.close();
            System.out.println("Connection closed");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updateSalary(int doctorID, int salary) {
        try {
            System.out.println("update  doctor set salary = " + salary + " where doctorID = " + doctorID);
            con = connectDB();
            ExecuteStatement("update  doctor set salary = " + salary + " where doctorID = " + doctorID + ";");
            con.close();
            System.out.println("Connection closed");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updateSpecialty(int doctorID, String specialty) {
        try {
            System.out.println("update  doctor set specialty = '" + specialty + "' where doctorID = " + doctorID);
            con = connectDB();
            ExecuteStatement("update  doctor set specialty = '" + specialty + "' where doctorID = " + doctorID + ";");
            con.close();
            System.out.println("Connection closed");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updateGender(int doctorID, String gender) {
        try {
            System.out.println("update  doctor set gender = '" + gender + "' where doctorID = " + doctorID);
            con = connectDB();
            ExecuteStatement("update  doctor set gender = '" + gender + "' where doctorID = " + doctorID + ";");
            con.close();
            System.out.println("Connection closed");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updateAddress(int doctorID, String address) {
        try {
            System.out.println("update  doctor set address = '" + address + "' where doctorID = " + doctorID);
            con = connectDB();
            ExecuteStatement("update  doctor set address = '" + address + "' where doctorID = " + doctorID + ";");
            con.close();
            System.out.println("Connection closed");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updatePhoneNumber(int doctorID, String phoneNumber) {
        try {
            System.out.println("update  doctor set phoneNumber = '" + phoneNumber + "' where doctorID = " + doctorID);
            con = connectDB();
            ExecuteStatement("update  doctor set phoneNumber = '" + phoneNumber + "' where doctorID = " + doctorID + ";");
            con.close();
            System.out.println("Connection closed");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}
