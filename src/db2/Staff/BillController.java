package db2.Staff;

import db2.Bill;
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

public class BillController {
    @FXML
    private TextField amount;
    @FXML
    private TableColumn<Bill, Integer> amountCol;
    @FXML
    private TableColumn<Bill, Integer> billNumberCol;
    @FXML
    private TextField billNumberField;
    @FXML
    private TableColumn<Bill, Integer> billPatientIDCol;
    @FXML
    private TextField billPatientIDField;
    @FXML
    private TableView<Bill> billTable;
    @FXML
    private TableColumn<Bill, String> billDateCol;
    @FXML
    private DatePicker billDateField;

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
    void addBill() {
        Bill bill = new Bill(
                Integer.parseInt(billNumberField.getText()),
                Integer.parseInt(billPatientIDField.getText()),
                Integer.parseInt(amount.getText()),
                billDateField.getValue().toString()
        );
        billNumberField.clear();
        billPatientIDField.clear();
        amount.clear();
        billDateField.getEditor().clear();
        Bill.insertData(bill);
    }

    @FXML
    void refreshBillTable() {
        ArrayList<Bill> billData = new ArrayList<>();
        billTable.setEditable(true);
        try {
            Bill.getData(billData);
            ObservableList<Bill> data = FXCollections.observableArrayList(billData);
            billNumberCol.setCellValueFactory(new PropertyValueFactory<>("billNumber"));
            billPatientIDCol.setCellValueFactory(new PropertyValueFactory<>("patientID"));
            amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
            billDateCol.setCellValueFactory(new PropertyValueFactory<>("billDate"));

            amountCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
            amountCol.setOnEditCommit(
                    (TableColumn.CellEditEvent<Bill, Integer> t) -> {
                        t.getTableView().getItems().get(t.getTablePosition().getRow()).setAmount(t.getNewValue()); //display only
                        t.getRowValue().updateAmount(t.getRowValue().getBillNumber(), t.getNewValue());
                    });

            billPatientIDCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
            billPatientIDCol.setOnEditCommit(
                    (TableColumn.CellEditEvent<Bill, Integer> t) -> {
                        t.getTableView().getItems().get(t.getTablePosition().getRow()).setPatientID(t.getNewValue());
                        t.getRowValue().updatePatientID(t.getRowValue().getBillNumber(), t.getNewValue());
                    });

            billDateCol.setCellFactory(TextFieldTableCell.forTableColumn());
            billDateCol.setOnEditCommit(
                    (TableColumn.CellEditEvent<Bill, String> t) -> {
                        t.getTableView().getItems().get(t.getTablePosition().getRow()).setBillDate(t.getNewValue()); //display only
                        t.getRowValue().updateBillDate(t.getRowValue().getBillNumber(), t.getNewValue());
                    });

            billTable.setItems(data);


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error");
        }
    }

    @FXML
    private void deleteRow() {
        ObservableList<Bill> selectedRows = billTable.getSelectionModel().getSelectedItems();
        ArrayList<Bill> rows = new ArrayList<>(selectedRows);
        rows.forEach(row -> {
            billTable.getItems().remove(row);
            row.deleteRow(row);
            billTable.refresh();
        });
    }
}
