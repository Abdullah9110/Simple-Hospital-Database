package db2.Staff;

import db2.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import java.util.ArrayList;

public class StaffController {
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
    private TextField doctorNameField;
    @FXML
    private TextField doctorIdField;
    @FXML
    private TextField doctorAddressField;
    @FXML
    private TextField phoneNumberFieldDoctor;
    @FXML
    private TextField specField;
    @FXML
    private TextField doctorSalaryField;
    @FXML
    private RadioButton doctorMaleButton;
    @FXML
    private RadioButton doctorFemaleButton;
    /************************************/
    @FXML
    private TextField nurseNameField;
    @FXML
    private TextField nurseIdField;
    @FXML
    private TextField nurseAddressField;
    @FXML
    private TextField phoneNumberFieldNurse;
    @FXML
    private TextField shiftField;
    @FXML
    private TextField nurseSalaryField;
    @FXML
    private RadioButton nurseMaleButton;
    @FXML
    private RadioButton nurseFemaleButton;



    @FXML
    private void addDoctor(){
        boolean male = doctorMaleButton.isSelected();
        boolean female = doctorFemaleButton.isSelected();
        String gender="none";
        if(male)
            gender = "male";
        else if(female)
            gender = "female";
        Doctor d = new Doctor(
                Integer.parseInt(doctorIdField.getText()),
                doctorNameField.getText(),
                Integer.parseInt(doctorSalaryField.getText()),
                specField.getText(),
                doctorAddressField.getText(),
                gender,
                phoneNumberFieldDoctor.getText()
        );
        doctorNameField.clear();
        doctorIdField.clear();
        doctorAddressField.clear();
        phoneNumberFieldDoctor.clear();
        specField.clear();
        doctorSalaryField.clear();
        doctorMaleButton.setSelected(true);
        doctorFemaleButton.setSelected(false);
        Doctor.insertData(d);
    }

    @FXML
    private void addNurse(){
        boolean male = nurseMaleButton.isSelected();
        boolean female = nurseFemaleButton.isSelected();
        String gender="none";
        if(male)
            gender = "male";
        else if(female)
            gender = "female";
        Nurse n = new Nurse(
                Integer.parseInt(nurseIdField.getText()),
                nurseNameField.getText(),
                Integer.parseInt(nurseSalaryField.getText()),
                shiftField.getText(),
                nurseAddressField.getText(),
                gender,
                phoneNumberFieldNurse.getText()
        );
        nurseNameField.clear();
        nurseIdField.clear();
        nurseAddressField.clear();
        phoneNumberFieldNurse.clear();
        shiftField.clear();
        nurseSalaryField.clear();
        nurseMaleButton.setSelected(false);
        nurseFemaleButton.setSelected(true);
        Nurse.insertData(n);
    }

    @FXML
    private TextField floor;

    @FXML
    private TextField nurseIDRoom;

    @FXML
    private TextField type;

    @FXML
    private TextField roomNumber;

    @FXML
    private void addRoom(){
        Room room = new Room(
                Integer.parseInt(roomNumber.getText()),
                type.getText(),
                Integer.parseInt(nurseIDRoom.getText()),
                Integer.parseInt(floor.getText())
        );
        roomNumber.clear();
        type.clear();
        nurseIDRoom.clear();
        floor.clear();
        Room.insertData(room);
    }
    /******************************/

    @FXML
    private TableView<Room> roomTable;

    @FXML
    private TableColumn<Room, Integer> floorCol;

    @FXML
    private TableColumn<Room, Integer> nurseIDCol;

    @FXML
    private TableColumn<Room, Integer> roomNumberCol;

    @FXML
    private TableColumn<Room, String> typeCol;


    @FXML
    private void refreshTable(){
        ArrayList<Room> roomData = new ArrayList<>();
        roomTable.setEditable(true);
        try{
            Room.getData(roomData);
            ObservableList<Room> data = FXCollections.observableArrayList(roomData);
            roomNumberCol.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
            typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
            nurseIDCol.setCellValueFactory(new PropertyValueFactory<>("nurseID"));
            floorCol.setCellValueFactory(new PropertyValueFactory<>("floor"));


            typeCol.setCellFactory(TextFieldTableCell.forTableColumn());
            typeCol.setOnEditCommit(
                    (TableColumn.CellEditEvent<Room, String> t) -> {
                        t.getTableView().getItems().get( t.getTablePosition().getRow()).setType(t.getNewValue()); //display only
                        t.getRowValue().updateType(t.getRowValue().getRoomNumber(), t.getNewValue());
                    });

            floorCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
            floorCol.setOnEditCommit(
                    (TableColumn.CellEditEvent<Room, Integer> t) -> {
                        t.getTableView().getItems().get(t.getTablePosition().getRow()).setFloor(t.getNewValue());
                        t.getRowValue().updateFloor(t.getRowValue().getRoomNumber(), t.getNewValue());
                    });

            nurseIDCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
            nurseIDCol.setOnEditCommit(
                    (TableColumn.CellEditEvent<Room, Integer> t) -> {
                        t.getTableView().getItems().get(t.getTablePosition().getRow()).setNurseID(t.getNewValue());
                        t.getRowValue().updateNurseID(t.getRowValue().getRoomNumber(), t.getNewValue());
                    });
            roomTable.setItems(data);



        }catch (Exception e){
            e.printStackTrace();
            System.out.println("error");
        }
    }

    @FXML
    private void deleteRow(){
        ObservableList<Room> selectedRows = roomTable.getSelectionModel().getSelectedItems();
        ArrayList<Room> rows = new ArrayList<>(selectedRows);
        rows.forEach(row -> {
            roomTable.getItems().remove(row);
            row.deleteRow(row);
            roomTable.refresh();
        });
    }

}
