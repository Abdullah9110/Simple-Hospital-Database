package db2.PatientMenu;

import db2.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static db2.Patient.connectDB;

public class Login {

    @FXML
    private TextField DoctorField;

    @FXML
    private TextField NurseField;

    @FXML
    private TextField billField;

    @FXML
    private TableColumn<CustomMedicine, Integer> countCol;

    @FXML
    private TextArea diagnoseField;

    @FXML
    private TableColumn<CustomMedicine, Integer> medIDCol;

    @FXML
    private TableColumn<CustomMedicine, String> medNameCol;

    @FXML
    private TableView<CustomMedicine> medicineTable;

    @FXML
    private TextField roomNumberField;

    @FXML
    private TextField selectPatientID;

    @FXML
    private TableColumn<CustomMedicine, Integer> priceCol;

    @FXML
    private TableColumn<CustomMedicine, Integer> totalCol;

    @FXML
    private void readPatientID() throws IOException, SQLException, ClassNotFoundException {
        Connection con;
        ArrayList<Integer> patientsID = new ArrayList<>();
        String SQL = "select patientID from Patient";
        con = connectDB();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(SQL);
        PatientID.patient = Integer.parseInt(selectPatientID.getText());
        while (rs.next()) {
            patientsID.add(Integer.parseInt(rs.getString(1)));
        }
        if (!patientsID.contains(PatientID.patient)) {
            Main.showLoginMenu();
            System.out.println("Patient Doesn't exists!");
        } else Main.showPatientMenu();
    }

    @FXML
    private void fillPage() throws SQLException, ClassNotFoundException {
        ArrayList<CustomMedicine> medicineData = new ArrayList<>();
        String str = "select m.medicineID,m.medicineName,m.price, Count(*) , Count(*)*m.price " +
                "from patient p, medicine m, patientMedicine pm " +
                "where p.patientID =  pm.patientID and m.medicineID = pm.medicineID and p.patientID = " + PatientID.patient + " group by m.medicineID;";
        try {
            CustomMedicine.getDataWithCon(medicineData, str);
            ObservableList<CustomMedicine> data = FXCollections.observableArrayList(medicineData);

            medIDCol.setCellValueFactory(new PropertyValueFactory<>("medicineID"));
            medNameCol.setCellValueFactory(new PropertyValueFactory<>("medicineName"));
            priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
            countCol.setCellValueFactory(new PropertyValueFactory<>("count"));
            totalCol.setCellValueFactory(new PropertyValueFactory<>("total"));

            medicineTable.setItems(data);

        } catch (Exception e) {
            System.out.println("No Medicine Purchased");
        }

        String roomNumberSQL = "select r.roomNumber from room r, patient p " +
                "where p.roomnumber = r.roomnumber and p.patientID = " + PatientID.patient + ";";

        String doctorSQL = "select d.doctorID, d.name from doctor d, patient p " +
                "where p.doctorID = d.doctorID and p.patientID = " + PatientID.patient + ";";

        String nurseSQL = "select n.nurseID, n.name from nurse n, patient p,room r " +
                "where p.roomnumber = r.roomnumber and n.nurseID = r.nurseID and p.patientID = " + PatientID.patient + ";";

        String diaSQL = "select d.details from Diagnose d, patient p " +
                "where d.patientID = p.patientID and p.patientID = " + PatientID.patient + ";";

        String medBillSQL = "select Sum(m.price) from medicine m, patient p, patientMedicine pm " +
                "where p.patientID =  pm.patientID and m.medicineID = pm.medicineID and p.patientID = " + PatientID.patient + ";";
        String billSQL = "select b.amount from  patient p, Bill b " +
                "where b.patientID = p.patientID and p.patientID = "+ PatientID.patient + ";";

        Connection con = connectDB();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(roomNumberSQL);
        rs.next();
        int roomNumber = Integer.parseInt(rs.getString(1));
        roomNumberField.setText("Your Room is " + roomNumber);

        rs = stmt.executeQuery(doctorSQL);
        rs.next();
        int doctorID = Integer.parseInt(rs.getString(1));
        String doctorName = rs.getString(2);
        DoctorField.setText("Doctor Name is " + doctorName + ", With ID = " + doctorID);


        rs = stmt.executeQuery(nurseSQL);
        rs.next();
        int nurseID = Integer.parseInt(rs.getString(1));
        String nurseName = rs.getString(2);
        NurseField.setText("Nurse Name is " + nurseName + ", With ID = " + nurseID);

        rs = stmt.executeQuery(medBillSQL);
        rs.next();

        Connection con2 = connectDB();
        Statement stmt2 = con.createStatement();
        ResultSet rs2 = stmt2.executeQuery(billSQL);
        rs2.next();

        int billSum = 0;
        try {
            billSum += Integer.parseInt(rs.getString(1));
        } catch (NumberFormatException ignored) {

        }
        try {
            billSum += Integer.parseInt(rs2.getString(1));
        } catch (NumberFormatException ignored) {

        }

        finally {
            billField.setText("Your Final Bill = " + billSum);
        }


        rs = stmt.executeQuery(diaSQL);
        rs.next();
        String diagDetails = "";
        boolean flag = true;
        try {
            diagDetails = rs.getString(1);
        } catch (Exception ignored) {
            diagnoseField.setText("You don't have any Diagnoses");
            flag = false;
        } finally {
            if (flag)
                diagnoseField.setText("Diagnose Details: " + diagDetails);
        }


        con.close();
        con2.close();


    }
}
