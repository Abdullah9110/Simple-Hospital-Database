package db2.Staff;

import db2.Main;
import db2.PatientMedicine;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.ArrayList;

public class BuyController {
    @FXML
    private TextField patientIDField;
    @FXML
    private TextField medicineIDField;
    @FXML
    private TableView<PatientMedicine> buyTable;
    @FXML
    private TableColumn<PatientMedicine, Integer> medicineIDCol;
    @FXML
    private TableColumn<PatientMedicine, Integer> patientIDCol;

    @FXML
    private void goStaffReg() throws IOException {
        Main.showStaffReg();
    }

    @FXML
    private void goStaffList() throws IOException {
        Main.showStaffList();
    }

    @FXML
    private void goRoomManagement() throws IOException {
        Main.showRoomManagement();
    }

    @FXML
    private void goMedicineManagement() throws IOException {
        Main.showMedicineManagement();
    }

    @FXML
    private void goDiagnoseManagement() throws IOException {
        Main.showDiagnoseManagement();
    }

    @FXML
    private void goBillManagement() throws IOException {
        Main.showBillManagement();
    }

    @FXML
    private void goBuyManagement() throws IOException {
        Main.showBuyManagement();
    }

    @FXML
    private void goPatientReg() throws IOException {
        Main.showRegPatient();
    }

    @FXML
    private void goPatientList() throws IOException {
        Main.showListPatient();
    }

    @FXML
    void addPurchase() {
        PatientMedicine buy = new PatientMedicine(
                Integer.parseInt(medicineIDField.getText()),
                Integer.parseInt(patientIDField.getText())
        );
        medicineIDField.clear();
        patientIDField.clear();
        PatientMedicine.insertData(buy);
    }

    @FXML
    void refreshBuyTable() {
        ArrayList<PatientMedicine> buyData = new ArrayList<>();
        buyTable.setEditable(true);
        try {
            PatientMedicine.getData(buyData);
            ObservableList<PatientMedicine> data = FXCollections.observableArrayList(buyData);
            medicineIDCol.setCellValueFactory(new PropertyValueFactory<>("medicineID"));
            patientIDCol.setCellValueFactory(new PropertyValueFactory<>("patientID"));
            buyTable.setItems(data);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error");
        }

    }

}
