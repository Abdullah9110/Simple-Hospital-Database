package db2.StatMenu;

import db2.Bill;
import db2.Diagnose;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static db2.Patient.connectDB;

public class Stat {

    @FXML
    private TableColumn<Bill, Integer> amountCol;

    @FXML
    private TableColumn<Bill, String> billDate;

    @FXML
    private DatePicker billEndDate;

    @FXML
    private TableColumn<Bill, Integer> billNumberCol;

    @FXML
    private TableColumn<Bill, Integer> billPatientIDCol;

    @FXML
    private TableColumn<Bill, String> billPatientNameCol;

    @FXML
    private DatePicker billStartDate;

    @FXML
    private TableView<CustomBill> billTable;

    @FXML
    private TableColumn<Diagnose, String> detailsCol;

    @FXML
    private TableColumn<Diagnose, String> diagnoseDate;

    @FXML
    private DatePicker diagnoseEndDate;

    @FXML
    private TableColumn<Diagnose, Integer> diagnoseNumCol;

    @FXML
    private TableColumn<Diagnose, Integer> diagnosePatientIDCol;

    @FXML
    private TableColumn<Diagnose, String> diagnosePatientNameCol;

    @FXML
    private DatePicker diagnoseStartDate;

    @FXML
    private TableView<Diagnose> diagnoseTable;

    @FXML
    private TextField numberOFBillsField;

    @FXML
    private TextField percentOfFemaleField;

    @FXML
    private TextField percentOfMaleField;

    @FXML
    private TextField roomNumberField;

    @FXML
    private TextField roomResult;

    @FXML
    private TextField totalNumOfStaffField;

    @FXML
    private TextField totalStaffSalariesField;

    @FXML
    private TableColumn<Diagnose, Integer> doctorIDCol;

    @FXML
    void billDuringDates() throws SQLException, ClassNotFoundException {
        ArrayList<CustomBill> billData = new ArrayList<>();
        String str = "select b.billNumber, b.amount, b.patientID, p.name, b.billDate " +
                "from bill b, patient p where b.patientID = p.patientID and b.billDate >= '"
                +billStartDate.getValue().toString()+"' and b.billDate <= '"+billEndDate.getValue().toString()+"';";
        try {
            CustomBill.getDataWithCon(billData, str);
            ObservableList<CustomBill> data = FXCollections.observableArrayList(billData);

            billNumberCol.setCellValueFactory(new PropertyValueFactory<>("billNumber"));
            amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
            billPatientIDCol.setCellValueFactory(new PropertyValueFactory<>("patientID"));
            billPatientNameCol.setCellValueFactory(new PropertyValueFactory<>("patientName"));
            billDate.setCellValueFactory(new PropertyValueFactory<>("billDate"));

            billTable.setItems(data);

        } catch (Exception e) {
            System.out.println("No Bills");
        }

        str = "select Count(*) from bill b, patient p where b.patientID = p.patientID and b.billDate >= '"
                +billStartDate.getValue().toString()+"' and b.billDate <= '"+billEndDate.getValue().toString()+"';";
        Connection con = connectDB();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(str);
        rs.next();
        numberOFBillsField.setText("Total Bills During this period: "+rs.getString(1));
    }

    @FXML
    void diagnoseDuringDates() throws SQLException, ClassNotFoundException {
        ArrayList<Diagnose> data = new ArrayList<>();
        String SQL = "select d.diagnoseNumber, d.details, p.name,  d.diaDate, d.doctorID, d.patientID " +
                "from diagnose d, patient p " +
                "where d.patientID = p.patientID and d.diaDate >= '"+diagnoseStartDate.getValue().toString()+"' and d.diaDate <= '"+diagnoseEndDate.getValue().toString()+"';";
        Connection con = connectDB();
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

        try {
            ObservableList<Diagnose> finalData = FXCollections.observableArrayList(data);

            diagnoseNumCol.setCellValueFactory(new PropertyValueFactory<>("diagnoseNumber"));
            detailsCol.setCellValueFactory(new PropertyValueFactory<>("details"));
            diagnosePatientNameCol.setCellValueFactory(new PropertyValueFactory<>("severity"));
            diagnoseDate.setCellValueFactory(new PropertyValueFactory<>("diagnoseDate"));
            doctorIDCol.setCellValueFactory(new PropertyValueFactory<>("doctorID"));
            diagnosePatientIDCol.setCellValueFactory(new PropertyValueFactory<>("patientID"));

            diagnoseTable.setItems(finalData);

        } catch (Exception e) {
            System.out.println("No Diagnoses");
        }

    }

    @FXML
    void numberOfPatientsInRoom() throws SQLException, ClassNotFoundException {
        int roomNumber = Integer.parseInt(roomNumberField.getText());
        String SQL = "select Count(*) from Room r, Patient p " +
                "where  r.roomNumber = p.roomNumber and r.roomNumber = " + roomNumber + ";";

        Connection con = connectDB();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(SQL);
        rs.next();
        int numberOfPatients = Integer.parseInt(rs.getString(1));
        roomResult.setText("Number of Patients: " + numberOfPatients);
    }

    @FXML
    void percentOfFemale() throws SQLException, ClassNotFoundException {
        String SQL1 = "select distinct Count(*) from Doctor ;";
        String SQL2 = "select distinct Count(*) from nurse ;";

        Connection con = connectDB();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(SQL1);
        rs.next();
        int totalStaff = Integer.parseInt(rs.getString(1));
        rs = stmt.executeQuery(SQL2);
        rs.next();
        totalStaff += Integer.parseInt(rs.getString(1));

        String SQL3 = "select distinct Count(*) from Doctor d where d.gender = 'female';";
        String SQL4 = "select distinct Count(*) from nurse d where d.gender = 'female';";
        stmt = con.createStatement();
        rs = stmt.executeQuery(SQL3);
        rs.next();
        int totalFemales = Integer.parseInt(rs.getString(1));

        stmt = con.createStatement();
        rs = stmt.executeQuery(SQL4);
        rs.next();
        totalFemales += Integer.parseInt(rs.getString(1));

        double female = (double)totalFemales/totalStaff * 100;
        percentOfFemaleField.setText("Percent Of Female: "+String.format("%.1f",female)+"%");
        con.close();
    }

    @FXML
    void percentOfMale() throws SQLException, ClassNotFoundException {
        String SQL1 = "select distinct Count(*) from Doctor ;";
        String SQL2 = "select distinct Count(*) from nurse ;";

        Connection con = connectDB();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(SQL1);
        rs.next();
        int totalStaff = Integer.parseInt(rs.getString(1));
        rs = stmt.executeQuery(SQL2);
        rs.next();
        totalStaff += Integer.parseInt(rs.getString(1));

        String SQL3 = "select distinct Count(*) from Doctor d where d.gender = 'male';";
        String SQL4 = "select distinct Count(*) from nurse d where d.gender = 'male';";
        stmt = con.createStatement();
        rs = stmt.executeQuery(SQL3);
        rs.next();
        int totalMales = Integer.parseInt(rs.getString(1));

        stmt = con.createStatement();
        rs = stmt.executeQuery(SQL4);
        rs.next();
        totalMales += Integer.parseInt(rs.getString(1));

        double male = (double) totalMales /totalStaff * 100;
        percentOfMaleField.setText("Percent Of Female: "+String.format("%.1f", male)+"%");
        con.close();
    }

    @FXML
    void totalSalaries() throws SQLException, ClassNotFoundException {
        String SQL1 = "select Sum(d.salary) as total from Doctor d;";
        String SQL2 = "select Sum(n.salary) as total from nurse n;";

        Connection con = connectDB();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(SQL1);
        rs.next();
        int totalSalaries = Integer.parseInt(rs.getString(1));
        rs = stmt.executeQuery(SQL2);
        rs.next();
        totalSalaries += Integer.parseInt(rs.getString(1));
        totalStaffSalariesField.setText("Total Salaries: " + totalSalaries);
        con.close();

    }

    @FXML
    void totalStaffCount() throws SQLException, ClassNotFoundException {
        String SQL1 = "select distinct Count(*) from Doctor ;";
        String SQL2 = "select distinct Count(*) from nurse ;";

        Connection con = connectDB();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(SQL1);
        rs.next();
        int totalStaff = Integer.parseInt(rs.getString(1));
        rs = stmt.executeQuery(SQL2);
        rs.next();
        totalStaff += Integer.parseInt(rs.getString(1));
        totalNumOfStaffField.setText("Total Staff: " + totalStaff);
        con.close();


    }

}
