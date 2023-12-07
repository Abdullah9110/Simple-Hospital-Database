package db2.Staff;

import db2.Diagnose;
import db2.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import java.util.ArrayList;

public class DiagnoseController {
    @FXML
    private TableColumn<Diagnose, String> dateCol;
    @FXML
    private DatePicker dateField;
    @FXML
    private TableColumn<Diagnose, String> detailsCol;
    @FXML
    private TextField detailsField;
    @FXML
    private TextField diaNumFiled;
    @FXML
    private TableColumn<Diagnose, Integer> diaNumberCol;
    @FXML
    private TableColumn<Diagnose, Integer> doctorIDCol;
    @FXML
    private TextField doctorIDField;
    @FXML
    private TextField patientIDField;
    @FXML
    private TableColumn<Diagnose, Integer> patientIdCol;
    @FXML
    private TableColumn<Diagnose, String> severityCol;
    @FXML
    private TextField severityField;
    @FXML
    private TableView<Diagnose> diagnoseTable;

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
    void addDiagnose() {
        Diagnose diagnose = new Diagnose(
                Integer.parseInt(diaNumFiled.getText()),
                detailsField.getText(),
                severityField.getText(),
                dateField.getValue().toString(),
                Integer.parseInt(doctorIDField.getText()),
                Integer.parseInt(patientIDField.getText())
        );
        diaNumFiled.clear();
        detailsField.clear();
        severityField.clear();
        dateField.getEditor().clear();
        doctorIDField.clear();
        patientIDField.clear();
        Diagnose.insertData(diagnose);

    }

    @FXML
    void deleteRow() {
        ObservableList<Diagnose> selectedRows = diagnoseTable.getSelectionModel().getSelectedItems();
        ArrayList<Diagnose> rows = new ArrayList<>(selectedRows);
        rows.forEach(row -> {
            diagnoseTable.getItems().remove(row);
            row.deleteRow(row);
            diagnoseTable.refresh();
        });
    }

    @FXML
    void refreshDiagnoseTable() {
        ArrayList<Diagnose> diagnoseData = new ArrayList<>();
        diagnoseTable.setEditable(true);
        try {
            Diagnose.getData(diagnoseData);
            ObservableList<Diagnose> data = FXCollections.observableArrayList(diagnoseData);
            diaNumberCol.setCellValueFactory(new PropertyValueFactory<>("diagnoseNumber"));
            detailsCol.setCellValueFactory(new PropertyValueFactory<>("details"));
            severityCol.setCellValueFactory(new PropertyValueFactory<>("severity"));
            dateCol.setCellValueFactory(new PropertyValueFactory<>("diagnoseDate"));
            doctorIDCol.setCellValueFactory(new PropertyValueFactory<>("doctorID"));
            patientIdCol.setCellValueFactory(new PropertyValueFactory<>("patientID"));

            detailsCol.setCellFactory(TextFieldTableCell.forTableColumn());
            detailsCol.setOnEditCommit(
                    (TableColumn.CellEditEvent<Diagnose, String> t) -> {
                        t.getTableView().getItems().get(t.getTablePosition().getRow()).setDetails(t.getNewValue()); //display only
                        t.getRowValue().updateDetails(t.getRowValue().getDiagnoseNumber(), t.getNewValue());
                    });

            severityCol.setCellFactory(TextFieldTableCell.forTableColumn());
            severityCol.setOnEditCommit(
                    (TableColumn.CellEditEvent<Diagnose, String> t) -> {
                        t.getTableView().getItems().get(t.getTablePosition().getRow()).setSeverity(t.getNewValue());
                        t.getRowValue().updateSeverity(t.getRowValue().getDiagnoseNumber(), t.getNewValue());
                    });

            dateCol.setCellFactory(TextFieldTableCell.forTableColumn());
            dateCol.setOnEditCommit(
                    (TableColumn.CellEditEvent<Diagnose, String> t) -> {
                        t.getTableView().getItems().get(t.getTablePosition().getRow()).setDiagnoseDate(t.getNewValue()); //display only
                        t.getRowValue().updateDate(t.getRowValue().getDiagnoseNumber(), t.getNewValue());
                    });

            doctorIDCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
            doctorIDCol.setOnEditCommit(
                    (TableColumn.CellEditEvent<Diagnose, Integer> t) -> {
                        t.getTableView().getItems().get(t.getTablePosition().getRow()).setDoctorID(t.getNewValue());
                        t.getRowValue().updateDoctorID(t.getRowValue().getDiagnoseNumber(), t.getNewValue());
                    });

            patientIdCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
            patientIdCol.setOnEditCommit(
                    (TableColumn.CellEditEvent<Diagnose, Integer> t) -> {
                        t.getTableView().getItems().get(t.getTablePosition().getRow()).setPatientID(t.getNewValue()); //display only
                        t.getRowValue().updatePatientID(t.getRowValue().getDiagnoseNumber(), t.getNewValue());
                    });

            diagnoseTable.setItems(data);


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error");
        }

    }

}

