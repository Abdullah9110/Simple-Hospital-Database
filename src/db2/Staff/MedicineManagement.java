package db2.Staff;

import db2.Main;
import db2.Medicine;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import java.util.ArrayList;

public class MedicineManagement {
    @FXML
    private TextField medicineID;
    @FXML
    private TableColumn<Medicine, Integer> medicineIDCol;
    @FXML
    private TextField medicineName;
    @FXML
    private TableColumn<Medicine, String> medicineNameCol;
    @FXML
    private TableColumn<Medicine, Integer> medicinePriceCol;
    @FXML
    private TableView<Medicine> medicineTable;
    @FXML
    private TextField price;

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
    private void addMedicine() {
        Medicine medicine = new Medicine(
                Integer.parseInt(medicineID.getText()),
                medicineName.getText(),
                Integer.parseInt(price.getText())
        );
        medicineID.clear();
        medicineName.clear();
        price.clear();
        Medicine.insertData(medicine);
    }

    @FXML
    private void refreshMedicineTable() {
        ArrayList<Medicine> medicineData = new ArrayList<>();
        medicineTable.setEditable(true);
        try {
            Medicine.getData(medicineData);
            ObservableList<Medicine> data = FXCollections.observableArrayList(medicineData);
            medicineIDCol.setCellValueFactory(new PropertyValueFactory<>("medicineID"));
            medicineNameCol.setCellValueFactory(new PropertyValueFactory<>("medicineName"));
            medicinePriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

            medicineNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
            medicineNameCol.setOnEditCommit(
                    (TableColumn.CellEditEvent<Medicine, String> t) -> {
                        t.getTableView().getItems().get(t.getTablePosition().getRow()).setMedicineName(t.getNewValue()); //display only
                        t.getRowValue().updateMedicineName(t.getRowValue().getMedicineID(), t.getNewValue());
                    });

            medicinePriceCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
            medicinePriceCol.setOnEditCommit(
                    (TableColumn.CellEditEvent<Medicine, Integer> t) -> {
                        t.getTableView().getItems().get(t.getTablePosition().getRow()).setPrice(t.getNewValue());
                        t.getRowValue().updatePrice(t.getRowValue().getMedicineID(), t.getNewValue());
                    });
            medicineTable.setItems(data);


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error");
        }
    }

    @FXML
    private void deleteRow() {
        ObservableList<Medicine> selectedRows = medicineTable.getSelectionModel().getSelectedItems();
        ArrayList<Medicine> rows = new ArrayList<>(selectedRows);
        rows.forEach(row -> {
            medicineTable.getItems().remove(row);
            row.deleteRow(row);
            medicineTable.refresh();
        });
    }


}
