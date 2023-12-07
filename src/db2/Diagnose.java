package db2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static db2.Patient.ExecuteStatement;
import static db2.Patient.connectDB;

public class Diagnose {
    private static Connection con;
    private final int diagnoseNumber;
    private String details;
    private String severity;
    private String diagnoseDate;
    private int doctorID;
    private int patientID;

    public Diagnose(int diagnoseNumber, String details, String severity, String diagnoseDate, int doctorID, int patientID) {
        this.diagnoseNumber = diagnoseNumber;
        this.details = details;
        this.severity = severity;
        this.diagnoseDate = diagnoseDate;
        this.doctorID = doctorID;
        this.patientID = patientID;
    }

    public static void getData(ArrayList<Diagnose> data) throws SQLException, ClassNotFoundException, ParseException {
        String SQL;

        con = connectDB();
        System.out.println("Connection established");

        SQL = "select * from Diagnose order by diagnoseNumber";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(SQL);


        while (rs.next())
            data.add(new Diagnose(
                    Integer.parseInt(rs.getString(1)),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    Integer.parseInt(rs.getString(5)),
                    Integer.parseInt(rs.getString(6)))
            );
        rs.close();
        stmt.close();
        con.close();
        System.out.println("Connection closed" + data.size());

    }

    public static void insertData(Diagnose rc) {

        try {
            System.out.println("Insert into Diagnose values(" +
                    rc.getDiagnoseNumber() + ",'"
                    + rc.getDetails() + "','"
                    + rc.getSeverity() + "','"
                    + rc.getDiagnoseDate() + "',"
                    + rc.getDoctorID() + ","
                    + rc.getPatientID() + ")");

            con = connectDB();
            ExecuteStatement("Insert into Diagnose values(" +
                    rc.getDiagnoseNumber() + ",'"
                    + rc.getDetails() + "','"
                    + rc.getSeverity() + "','"
                    + rc.getDiagnoseDate() + "',"
                    + rc.getDoctorID() + ","
                    + rc.getPatientID() + ")");

            con.close();
            System.out.println("Connection closed");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public int getDiagnoseNumber() {
        return diagnoseNumber;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getDiagnoseDate() {
        return diagnoseDate;
    }

    public void setDiagnoseDate(String diagnoseDate) {
        this.diagnoseDate = diagnoseDate;
    }

    public int getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(int doctorID) {
        this.doctorID = doctorID;
    }

    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public void deleteRow(Diagnose row) {

        try {
            System.out.println("delete from  Diagnose where diagnoseNumber=" + row.getDiagnoseNumber() + ";");
            con = connectDB();
            ExecuteStatement("delete from  Diagnose where diagnoseNumber=" + row.getDiagnoseNumber() + ";");
            con.close();
            System.out.println("Connection closed");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updateDetails(int diagnoseNumber, String details) {
        try {
            System.out.println("update  Diagnose set details = '" + details + "' where diagnoseNumber = " + diagnoseNumber);
            con = connectDB();
            ExecuteStatement("update  Diagnose set details = '" + details + "' where diagnoseNumber = " + diagnoseNumber + ";");
            con.close();
            System.out.println("Connection closed");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updateSeverity(int diagnoseNumber, String severity) {
        try {
            System.out.println("update  Diagnose set severity = '" + severity + "' where diagnoseNumber = " + diagnoseNumber);
            con = connectDB();
            ExecuteStatement("update  Diagnose set severity = '" + severity + "' where diagnoseNumber = " + diagnoseNumber + ";");
            con.close();
            System.out.println("Connection closed");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updateDate(int diagnoseNumber, String diagnoseDate) {
        try {
            System.out.println("update  Diagnose set details = '" + diagnoseDate + "' where diagnoseNumber = " + diagnoseNumber);
            con = connectDB();
            ExecuteStatement("update  Diagnose set details = '" + diagnoseDate + "' where diagnoseNumber = " + diagnoseNumber + ";");
            con.close();
            System.out.println("Connection closed");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updateDoctorID(int diagnoseNumber, int doctorID) {
        try {
            System.out.println("update  Diagnose set doctorID = '" + doctorID + "' where diagnoseNumber = " + diagnoseNumber);
            con = connectDB();
            ExecuteStatement("update  Diagnose set doctorID = '" + doctorID + "' where diagnoseNumber = " + diagnoseNumber + ";");
            con.close();
            System.out.println("Connection closed");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updatePatientID(int diagnoseNumber, int patientID) {
        try {
            System.out.println("update  Diagnose set patientID = '" + patientID + "' where diagnoseNumber = " + diagnoseNumber);
            con = connectDB();
            ExecuteStatement("update  Diagnose set patientID = '" + patientID + "' where diagnoseNumber = " + diagnoseNumber + ";");
            con.close();
            System.out.println("Connection closed");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
