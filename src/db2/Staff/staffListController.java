package db2.Staff;

import db2.Doctor;
import db2.Main;
import db2.Nurse;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import java.util.ArrayList;

public class staffListController {
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
    private void goBillManagement() throws IOException{
        Main.showBillManagement();
    }
    @FXML
    private void goBuyManagement() throws IOException{
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
    private TableColumn<Doctor, String> doctorAddressCol;

    @FXML
    private TableColumn<Doctor, String> doctorGenderCol;

    @FXML
    private TableColumn<Doctor, Integer> doctorIDCol;

    @FXML
    private TableColumn<Doctor, String> doctorNameCol;

    @FXML
    private TableColumn<Doctor, String> doctorPhoneNumberCol;

    @FXML
    private TableColumn<Doctor, Integer> doctorSalaryCol;

    @FXML
    private TableColumn<Doctor, String> doctorSpecialtyCol;

    @FXML
    private TableView<Doctor> doctorTable;

    @FXML
    private TableColumn<Nurse, String> nurseAddressCol;

    @FXML
    private TableColumn<Nurse, String> nurseGenderCol;

    @FXML
    private TableColumn<Nurse, Integer> nurseIDCol;

    @FXML
    private TableColumn<Nurse, String> nurseNameCol;

    @FXML
    private TableColumn<Nurse, String> nursePhoneNumberCol;

    @FXML
    private TableColumn<Nurse, Integer> nurseSalaryCol;

    @FXML
    private TableColumn<Nurse, String> nurseShiftCol;

    @FXML
    private TableView<Nurse> nurseTable;

    @FXML
    private void refreshDoctorTable(){
        ArrayList<Doctor> doctorData = new ArrayList<>();
        doctorTable.setEditable(true);
        try{
            Doctor.getData(doctorData);
            ObservableList<Doctor> data = FXCollections.observableArrayList(doctorData);

            doctorIDCol.setCellValueFactory(new PropertyValueFactory<>("doctorID"));
            doctorNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
            doctorSalaryCol.setCellValueFactory(new PropertyValueFactory<>("salary"));
            doctorSpecialtyCol.setCellValueFactory(new PropertyValueFactory<>("specialty"));
            doctorAddressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
            doctorGenderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
            doctorPhoneNumberCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));


            doctorNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
            doctorNameCol.setOnEditCommit(
                    (TableColumn.CellEditEvent<Doctor, String> t) -> {
                        t.getTableView().getItems().get( t.getTablePosition().getRow()).setName(t.getNewValue()); //display only
                        t.getRowValue().updateName(t.getRowValue().getDoctorID(), t.getNewValue());
                    });

            doctorSalaryCol.setCellFactory(TextFieldTableCell.forTableColumn((new IntegerStringConverter())));
            doctorSalaryCol.setOnEditCommit(
                    (TableColumn.CellEditEvent<Doctor, Integer> t) -> {
                        t.getTableView().getItems().get( t.getTablePosition().getRow()).setSalary(t.getNewValue()); //display only
                        t.getRowValue().updateSalary(t.getRowValue().getDoctorID(), t.getNewValue());
                    });

            doctorSpecialtyCol.setCellFactory(TextFieldTableCell.forTableColumn());
            doctorSpecialtyCol.setOnEditCommit(
                    (TableColumn.CellEditEvent<Doctor, String> t) -> {
                        t.getTableView().getItems().get( t.getTablePosition().getRow()).setSpecialty(t.getNewValue()); //display only
                        t.getRowValue().updateSpecialty(t.getRowValue().getDoctorID(), t.getNewValue());
                    });

            doctorAddressCol.setCellFactory(TextFieldTableCell.forTableColumn());
            doctorAddressCol.setOnEditCommit(
                    (TableColumn.CellEditEvent<Doctor, String> t) -> {
                        t.getTableView().getItems().get( t.getTablePosition().getRow()).setAddress(t.getNewValue()); //display only
                        t.getRowValue().updateAddress(t.getRowValue().getDoctorID(), t.getNewValue());
                    });

            doctorGenderCol.setCellFactory(TextFieldTableCell.forTableColumn());
            doctorGenderCol.setOnEditCommit(
                    (TableColumn.CellEditEvent<Doctor, String> t) -> {
                        t.getTableView().getItems().get( t.getTablePosition().getRow()).setGender(t.getNewValue()); //display only
                        t.getRowValue().updateGender(t.getRowValue().getDoctorID(), t.getNewValue());
                    });

            doctorPhoneNumberCol.setCellFactory(TextFieldTableCell.forTableColumn());
            doctorPhoneNumberCol.setOnEditCommit(
                    (TableColumn.CellEditEvent<Doctor, String> t) -> {
                        t.getTableView().getItems().get( t.getTablePosition().getRow()).setPhoneNumber(t.getNewValue()); //display only
                        t.getRowValue().updatePhoneNumber(t.getRowValue().getDoctorID(), t.getNewValue());
                    });

            doctorTable.setItems(data);

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("error");
        }
    }

    @FXML
    private void deleteDoctorRow(){
        ObservableList<Doctor> selectedRows = doctorTable.getSelectionModel().getSelectedItems();
        ArrayList<Doctor> rows = new ArrayList<>(selectedRows);
        rows.forEach(row -> {
            doctorTable.getItems().remove(row);
            row.deleteRow(row);
            doctorTable.refresh();
        });
    }
    @FXML
    private void refreshNurseTable(){
        ArrayList<Nurse> nurseData = new ArrayList<>();
        nurseTable.setEditable(true);
        try{
            Nurse.getData(nurseData);
            ObservableList<Nurse> data = FXCollections.observableArrayList(nurseData);

            nurseIDCol.setCellValueFactory(new PropertyValueFactory<>("nurseID"));
            nurseNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
            nurseSalaryCol.setCellValueFactory(new PropertyValueFactory<>("salary"));
            nurseShiftCol.setCellValueFactory(new PropertyValueFactory<>("shift"));
            nurseAddressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
            nurseGenderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
            nursePhoneNumberCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));


            nurseNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
            nurseNameCol.setOnEditCommit(
                    (TableColumn.CellEditEvent<Nurse, String> t) -> {
                        t.getTableView().getItems().get( t.getTablePosition().getRow()).setName(t.getNewValue()); //display only
                        t.getRowValue().updateName(t.getRowValue().getNurseID(), t.getNewValue());
                    });

            nurseSalaryCol.setCellFactory(TextFieldTableCell.forTableColumn((new IntegerStringConverter())));
            nurseSalaryCol.setOnEditCommit(
                    (TableColumn.CellEditEvent<Nurse, Integer> t) -> {
                        t.getTableView().getItems().get( t.getTablePosition().getRow()).setSalary(t.getNewValue()); //display only
                        t.getRowValue().updateSalary(t.getRowValue().getNurseID(), t.getNewValue());
                    });

            nurseShiftCol.setCellFactory(TextFieldTableCell.forTableColumn());
            nurseShiftCol.setOnEditCommit(
                    (TableColumn.CellEditEvent<Nurse, String> t) -> {
                        t.getTableView().getItems().get( t.getTablePosition().getRow()).setShift(t.getNewValue()); //display only
                        t.getRowValue().updateShift(t.getRowValue().getNurseID(), t.getNewValue());
                    });

            nurseAddressCol.setCellFactory(TextFieldTableCell.forTableColumn());
            nurseAddressCol.setOnEditCommit(
                    (TableColumn.CellEditEvent<Nurse, String> t) -> {
                        t.getTableView().getItems().get( t.getTablePosition().getRow()).setAddress(t.getNewValue()); //display only
                        t.getRowValue().updateAddress(t.getRowValue().getNurseID(), t.getNewValue());
                    });

            nurseGenderCol.setCellFactory(TextFieldTableCell.forTableColumn());
            nurseGenderCol.setOnEditCommit(
                    (TableColumn.CellEditEvent<Nurse, String> t) -> {
                        t.getTableView().getItems().get( t.getTablePosition().getRow()).setGender(t.getNewValue()); //display only
                        t.getRowValue().updateGender(t.getRowValue().getNurseID(), t.getNewValue());
                    });

            nursePhoneNumberCol.setCellFactory(TextFieldTableCell.forTableColumn());
            nursePhoneNumberCol.setOnEditCommit(
                    (TableColumn.CellEditEvent<Nurse, String> t) -> {
                        t.getTableView().getItems().get( t.getTablePosition().getRow()).setPhoneNumber(t.getNewValue()); //display only
                        t.getRowValue().updatePhoneNumber(t.getRowValue().getNurseID(), t.getNewValue());
                    });

            nurseTable.setItems(data);

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("error");
        }
    }

    @FXML
    private void deleteNurseRow(){
        ObservableList<Nurse> selectedRows = nurseTable.getSelectionModel().getSelectedItems();
        ArrayList<Nurse> rows = new ArrayList<>(selectedRows);
        rows.forEach(row -> {
            nurseTable.getItems().remove(row);
            row.deleteRow(row);
            nurseTable.refresh();
        });
    }
}
