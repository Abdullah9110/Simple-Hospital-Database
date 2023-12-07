package db2;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class Patient {
    private static Connection con;
    /***************************/
    private final int patientId;
    private String patientName;
    private int roomNum;
    private String address;
    private String gender;
    private String phoneNumber;
    private int doctorId;

    public Patient(int patientId, String patientName, int roomRoomNum, String address, String gender, String phoneNumber, int doctorId) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.roomNum = roomRoomNum;
        this.address = address;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.doctorId = doctorId;
    }

    public static Connection connectDB() throws ClassNotFoundException, SQLException {
        String URL = "127.0.0.1";
        String port = "3306";
        String dbName = "hospital";
        String dbURL = "jdbc:mysql://" + URL + ":" + port + "/" + dbName + "?verifyServerCertificate=false";
        Properties p = new Properties();
        String dbUsername = "root";
        p.setProperty("user", dbUsername);
        String dbPassword = "2263";
        p.setProperty("password", dbPassword);
        p.setProperty("useSSL", "false");
        p.setProperty("autoReconnect", "true");
        Class.forName("com.mysql.jdbc.Driver");

        con = DriverManager.getConnection(dbURL, p);
        return con;
    }

    public static void ExecuteStatement(String SQL) throws SQLException {
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(SQL);
            stmt.close();
        } catch (SQLException s) {
            s.printStackTrace();
            System.out.println("SQL statement is not executed!");
        }
    }

    public static void getData(ArrayList<Patient> data) throws SQLException, ClassNotFoundException {
        String SQL;
        con = connectDB();
        System.out.println("Connection established");

        SQL = "select * from patient order by patientID";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(SQL);

        while (rs.next())
            data.add(new Patient(
                            Integer.parseInt(rs.getString(1)),
                            rs.getString(2),
                            Integer.parseInt(rs.getString(3)),
                            rs.getString(4),
                            rs.getString(5),
                            rs.getString(6),
                            Integer.parseInt(rs.getString(7))
                    )
            );

        rs.close();
        stmt.close();

        con.close();
        System.out.println("Connection closed" + data.size());
    }
    public static void getDataWithCon(ArrayList<Patient> data, String s) throws SQLException, ClassNotFoundException {
        String SQL;

        System.out.println("Connection established");

        SQL = "select * from patient "+s+"order by patientID";
        con = connectDB();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(SQL);

        while (rs.next())
            data.add(new Patient(
                            Integer.parseInt(rs.getString(1)),
                            rs.getString(2),
                            Integer.parseInt(rs.getString(3)),
                            rs.getString(4),
                            rs.getString(5),
                            rs.getString(6),
                            Integer.parseInt(rs.getString(7))
                    )
            );

        rs.close();
        stmt.close();

        con.close();
        System.out.println("Connection closed" + data.size());
    }

    public static void insertData(Patient rc) {

        try {
            System.out.println("Insert into patient values(" +
                    rc.getPatientId() + ",'"
                    + rc.getPatientName() + "',"
                    + rc.getRoomNum() + ",'"
                    + rc.getAddress() + "','"
                    + rc.getGender() + "','"
                    + rc.getPhoneNumber() + "',"
                    + rc.getDoctorId() + ")"
            );

            con = connectDB();
            ExecuteStatement("Insert into patient values(" +
                    rc.getPatientId() + ",'"
                    + rc.getPatientName() + "',"
                    + rc.getRoomNum() + ",'"
                    + rc.getAddress() + "','"
                    + rc.getGender() + "','"
                    + rc.getPhoneNumber() + "',"
                    + rc.getDoctorId() + ")"
            );

            con.close();
            System.out.println("Connection closed");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public int getPatientId() {
        return patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public int getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(int roomNum) {
        this.roomNum = roomNum;
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

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public void deleteRow(Patient row) {
        try {
            System.out.println("delete from  patient where patientId=" + row.getPatientId() + ";");
            con = connectDB();
            ExecuteStatement("delete from  patient where patientId=" + row.getPatientId() + ";");
            con.close();
            System.out.println("Connection closed");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void updateName(int patientId, String newName) {
        try {
            System.out.println("update  patient set name = '" + newName + "' where patientId = " + patientId);
            con = connectDB();
            ExecuteStatement("update  patient set name = '" + newName + "' where patientId = " + patientId + ";");
            con.close();
            System.out.println("Connection closed");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updateGender(int patientId, String gender) {
        try {
            System.out.println("update  patient set gender = '" + gender + "' where patientId = " + patientId);
            con = connectDB();
            ExecuteStatement("update  patient set gender = '" + gender + "' where patientId = " + patientId + ";");
            con.close();
            System.out.println("Connection closed");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updateAddress(int patientId, String newAddress) {
        try {
            System.out.println("update  patient set address = '" + newAddress + "' where patientId = " + patientId);
            con = connectDB();
            ExecuteStatement("update  patient set address = '" + newAddress + "' where patientId = " + patientId + ";");
            con.close();
            System.out.println("Connection closed");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updateDoctorID(int patientId, int doctorID) {
        try {
            System.out.println("update  patient set doctorID = " + doctorID + " where patientId = " + patientId);
            con = connectDB();
            ExecuteStatement("update  patient set doctorID = " + doctorID + " where patientId = " + patientId + ";");
            con.close();
            System.out.println("Connection closed");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updateRoomNo(int patientId, int roomNumber) {
        try {
            System.out.println("update  patient set roomNumber = " + roomNumber + " where patientId = " + patientId);
            con = connectDB();
            ExecuteStatement("update  patient set roomNumber = " + roomNumber + " where patientId = " + patientId + ";");
            con.close();
            System.out.println("Connection closed");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updatePhoneNumber(int patientId, String phoneNumber) {
        try {
            System.out.println("update  patient set phoneNumber = '" + phoneNumber + "' where patientId = " + patientId);
            con = connectDB();
            ExecuteStatement("update  patient set phoneNumber = '" + phoneNumber + "' where patientId = " + patientId + ";");
            con.close();
            System.out.println("Connection closed");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Patient{" +
                "patientId=" + patientId +
                ", patientName='" + patientName + '\'' +
                ", roomNum=" + roomNum +
                ", address='" + address + '\'' +
                ", gender='" + gender + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", doctorId=" + doctorId +
                '}';
    }
}
