package db2.PatientPackage;

import db2.Main;
import db2.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import java.util.ArrayList;

public class PatientController {
    @FXML
    private TextField patientIdField;
    @FXML
    private TextField pNameField;
    @FXML
    private TextField doctorIdField;
    @FXML
    private TextField pAddressField;
    @FXML
    private TextField phoneNumberField;
    @FXML
    private TextField roomNumberField;
    @FXML
    private RadioButton maleButton;
    @FXML
    private RadioButton femaleButton;
    @FXML
    private TableColumn<Patient, String> addressCol;
    @FXML
    private TableColumn<Patient, String> genderCol;
    @FXML
    private TableColumn<Patient, Integer> doctorIDCol;
    @FXML
    private TableColumn<Patient, String> nameCol;
    @FXML
    private TableColumn<Patient, Integer> patientIDCol;
    @FXML
    private TableColumn<Patient, String> phoneNumberCol;
    @FXML
    private TableColumn<Patient, Integer> roomNumberCol;
    @FXML
    private TableView<Patient> patientTable = new TableView<>();

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
    private void addPatient() {
        boolean male = maleButton.isSelected();
        boolean female = femaleButton.isSelected();
        String gender = "none";
        if (male)
            gender = "male";
        else if (female)
            gender = "female";

        Patient p = new Patient(
                Integer.parseInt(patientIdField.getText()),
                pNameField.getText(),
                Integer.parseInt(roomNumberField.getText()),
                pAddressField.getText(), gender,
                phoneNumberField.getText(),
                Integer.parseInt(doctorIdField.getText())
        );
        patientIdField.clear();
        pNameField.clear();
        doctorIdField.clear();
        pAddressField.clear();
        phoneNumberField.clear();
        roomNumberField.clear();
        maleButton.setSelected(true);
        femaleButton.setSelected(false);
        Patient.insertData(p);
    }

    @FXML
    private void refreshTable() {
        ArrayList<Patient> patientData = new ArrayList<>();
        patientTable.setEditable(true);
        try {
            Patient.getData(patientData);
            ObservableList<Patient> data = FXCollections.observableArrayList(patientData);

            patientIDCol.setCellValueFactory(new PropertyValueFactory<>("patientId"));
            nameCol.setCellValueFactory(new PropertyValueFactory<>("patientName"));
            roomNumberCol.setCellValueFactory(new PropertyValueFactory<>("roomNum"));
            addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
            genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
            phoneNumberCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
            doctorIDCol.setCellValueFactory(new PropertyValueFactory<>("doctorId"));


            nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
            nameCol.setOnEditCommit(
                    (TableColumn.CellEditEvent<Patient, String> t) -> {
                        t.getTableView().getItems().get(t.getTablePosition().getRow()).setPatientName(t.getNewValue()); //display only
                        t.getRowValue().updateName(t.getRowValue().getPatientId(), t.getNewValue());
                    });

            roomNumberCol.setCellFactory(TextFieldTableCell.forTableColumn((new IntegerStringConverter())));
            roomNumberCol.setOnEditCommit(
                    (TableColumn.CellEditEvent<Patient, Integer> t) -> {
                        t.getTableView().getItems().get(t.getTablePosition().getRow()).setRoomNum(t.getNewValue()); //display only
                        t.getRowValue().updateRoomNo(t.getRowValue().getPatientId(), t.getNewValue());
                    });


            addressCol.setCellFactory(TextFieldTableCell.forTableColumn());
            addressCol.setOnEditCommit(
                    (TableColumn.CellEditEvent<Patient, String> t) -> {
                        t.getTableView().getItems().get(t.getTablePosition().getRow()).setAddress(t.getNewValue()); //display only
                        t.getRowValue().updateAddress(t.getRowValue().getPatientId(), t.getNewValue());
                    });

            genderCol.setCellFactory(TextFieldTableCell.forTableColumn());
            genderCol.setOnEditCommit(
                    (TableColumn.CellEditEvent<Patient, String> t) -> {
                        t.getTableView().getItems().get(t.getTablePosition().getRow()).setGender(t.getNewValue()); //display only
                        t.getRowValue().updateGender(t.getRowValue().getPatientId(), t.getNewValue());
                    });

            phoneNumberCol.setCellFactory(TextFieldTableCell.forTableColumn());
            phoneNumberCol.setOnEditCommit(
                    (TableColumn.CellEditEvent<Patient, String> t) -> {
                        t.getTableView().getItems().get(t.getTablePosition().getRow()).setPhoneNumber(t.getNewValue()); //display only
                        t.getRowValue().updatePhoneNumber(t.getRowValue().getPatientId(), t.getNewValue());
                    });

            doctorIDCol.setCellFactory(TextFieldTableCell.forTableColumn((new IntegerStringConverter())));
            doctorIDCol.setOnEditCommit(
                    (TableColumn.CellEditEvent<Patient, Integer> t) -> {
                        t.getTableView().getItems().get(t.getTablePosition().getRow()).setDoctorId(t.getNewValue()); //display only
                        t.getRowValue().updateDoctorID(t.getRowValue().getPatientId(), t.getNewValue());
                    });

            patientTable.setItems(data);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error");
        }
    }

    @FXML
    private void deleteRow() {
        ObservableList<Patient> selectedRows = patientTable.getSelectionModel().getSelectedItems();
        ArrayList<Patient> rows = new ArrayList<>(selectedRows);
        rows.forEach(row -> {
            patientTable.getItems().remove(row);
            row.deleteRow(row);
            patientTable.refresh();
        });
    }
}
