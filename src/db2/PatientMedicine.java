package db2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static db2.Patient.ExecuteStatement;
import static db2.Patient.connectDB;

public class PatientMedicine {
    private int medicineID;
    private int patientID;

    public PatientMedicine(int medicineID, int patientID) {
        this.medicineID = medicineID;
        this.patientID = patientID;
    }

    public int getMedicineID() {
        return medicineID;
    }

    public int getPatientID() {
        return patientID;
    }

    public void setMedicineID(int medicineID) {
        this.medicineID = medicineID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public static void insertData(PatientMedicine rc) {
        Connection con;
        try {
            System.out.println("Insert into PatientMedicine values("+
                    rc.getMedicineID()+","
                    + rc.getPatientID() +")"
            );

            con = connectDB();
            ExecuteStatement("Insert into PatientMedicine values("+
                    rc.getMedicineID()+","
                    + rc.getPatientID() +")"
            );

            con.close();
            System.out.println("Connection closed");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void getData(ArrayList<PatientMedicine> data) throws SQLException, ClassNotFoundException {
        String SQL;

        Connection con = connectDB();
        System.out.println("Connection established");

        SQL = "select * from PatientMedicine";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(SQL);


        while (rs.next())
            data.add(new PatientMedicine(
                    Integer.parseInt(rs.getString(1)),
                    Integer.parseInt(rs.getString(2)))
            );
        rs.close();
        stmt.close();
        con.close();
        System.out.println("Connection closed" + data.size());

    }

}
