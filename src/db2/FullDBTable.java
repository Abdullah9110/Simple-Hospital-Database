package db2;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.converter.IntegerStringConverter;

import java.sql.*;
import java.util.ArrayList;

public class FullDBTable extends Application {

    private ObservableList<Medicine> dataList;

    public static void main(String[] args) {

        Application.launch(args);
    }


    @Override
    public void start(Stage stage) {
        ArrayList<Medicine> MedicineData = new ArrayList<>();

        try {
            Medicine.getData(MedicineData);
            //convert data from arraylist to observable arraylist
            dataList = FXCollections.observableArrayList(MedicineData);
            tableView(stage);
            stage.show();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    @SuppressWarnings("unchecked")

    private void tableView(Stage stage) {

        TableView<Medicine> myDataTable = new TableView<>();

        Scene scene = new Scene(new Group());
        stage.setTitle("Medicine Table");
        stage.setWidth(550);
        stage.setHeight(500);

        Label label = new Label("Medicine Table");
        label.setFont(new Font("Arial", 20));
        label.setTextFill(Color.BROWN);

        myDataTable.setEditable(true);
        myDataTable.setMaxHeight(200);
        myDataTable.setMaxWidth(408);

        // name of column for display
        TableColumn<Medicine, Integer> medicineIDCol = new TableColumn<>("medicineID");
        medicineIDCol.setMinWidth(50);

        // to get the data from specific column
        medicineIDCol.setCellValueFactory(new PropertyValueFactory<>("medicineID"));


        TableColumn<Medicine, String> medicineNameCol = new TableColumn<>("medicineName");
        medicineNameCol.setMinWidth(100);
        medicineNameCol.setCellValueFactory(new PropertyValueFactory<>("medicineName"));


        medicineNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        medicineNameCol.setOnEditCommit(
                (CellEditEvent<Medicine, String> t) -> {
                    t.getTableView().getItems().get( t.getTablePosition().getRow()).setMedicineName(t.getNewValue()); //display only
                    t.getRowValue().updateMedicineName(t.getRowValue().getMedicineID(), t.getNewValue());
                });

        TableColumn<Medicine, Integer> priceCol = new TableColumn<>("price");
        priceCol.setMinWidth(50);
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));


        priceCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        priceCol.setOnEditCommit(
                (CellEditEvent<Medicine, Integer> t) -> {
                    t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).setPrice(t.getNewValue());
                    t.getRowValue().updatePrice(t.getRowValue().getMedicineID(), t.getNewValue());
                });





        myDataTable.setItems(dataList);

        myDataTable.getColumns().addAll(medicineIDCol, medicineNameCol, priceCol);


        final TextField addMedicineId = new TextField();
        addMedicineId.setPromptText("Medicine_ID");
        addMedicineId.setMaxWidth(medicineIDCol.getPrefWidth());

        final TextField addName = new TextField();
        addName.setMaxWidth(medicineNameCol.getPrefWidth());
        addName.setPromptText("Medicine_Name");

        final TextField addPrice = new TextField();
        addPrice.setMaxWidth(priceCol.getPrefWidth());
        addPrice.setPromptText("Price");


        final Button addButton = new Button("Add");
        addButton.setOnAction((ActionEvent e) -> {
            Medicine rc;
            rc = new Medicine(
                    Integer.parseInt(addMedicineId.getText()),
                    addName.getText(),
                    Integer.parseInt(addPrice.getText()));
            dataList.add(rc);
            rc.insertData(rc);
            addMedicineId.clear();
            addName.clear();
            addPrice.clear();
        });

        final HBox hb = new HBox();


        final Button deleteButton = new Button("Delete");
        deleteButton.setOnAction((ActionEvent e) -> {

        });


        hb.getChildren().addAll(addMedicineId, addName, addPrice, addButton, deleteButton);
        hb.setSpacing(3);


        final Button refreshButton = new Button("Refresh");
        refreshButton.setOnAction((ActionEvent e) -> myDataTable.refresh());

        final Button clearButton = new Button("Clear All");
        clearButton.setOnAction((ActionEvent e) -> showDialog(stage, myDataTable));


        final HBox hb2 = new HBox();
        hb2.getChildren().addAll(clearButton, refreshButton);
        hb2.setAlignment(Pos.CENTER_RIGHT);
        hb2.setSpacing(3);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(label, myDataTable, hb);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);
        stage.setScene(scene);
    }

    private void showDialog(Window owner, TableView<Medicine> table) {
        // Create a Stage with specified owner and modality
        Stage stage = new Stage();
        stage.initOwner(owner);
        stage.initModality(Modality.NONE);
        //	Label modalityLabel = new Label(modality.toString());

        Button yesButton = new Button("Confirm");
        yesButton.setOnAction(e -> {
            for (Medicine row : dataList) {
                row.deleteRow(row);
                table.refresh();
            }
            table.getItems().removeAll(dataList);
            stage.close();
        });

        Button noButton = new Button("Cancel");
        noButton.setOnAction(e -> stage.close());

        HBox root = new HBox();
        root.setPadding(new Insets(10, 10, 10, 10));
        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);

        root.getChildren().addAll(yesButton, noButton);
        Scene scene = new Scene(root, 200, 100);
        stage.setScene(scene);
        stage.setTitle("Confirm Delete?");
        stage.show();
    }
}

    /*private void insertData(Medicine rc) {
        try {
            System.out.println("Insert into medicine (medicine_ID, medicine_Name,price) values(" +
                    rc.getMedicineID() + ",'"
                    + rc.getMedicineName() + "',"
                    + rc.getPrice() + ");");

            connectDB();
            ExecuteStatement("Insert into medicine (medicine_ID, medicine_Name,price) values("
                    + rc.getMedicineID() + ",'"
                    + rc.getMedicineName() + "',"
                    + rc.getPrice() + ");");
            con.close();
            System.out.println("Connection closed" + data.size());

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }




    private void connectDB() throws ClassNotFoundException, SQLException {

        String URL = "127.0.0.1";
        String port = "3306";
        String dbName = "hospital";
        String dbURL = "jdbc:mysql://" + URL + ":" + port + "/" + dbName + "?verifyServerCertificate=false";
        Properties p = new Properties();
        String dbUsername = "root";
        p.setProperty("user", dbUsername);
        String dbPassword = "2263";
        p.setProperty("password", dbPassword);
        p.setProperty("useSSL", "false");
        p.setProperty("autoReconnect", "true");
        Class.forName("com.mysql.jdbc.Driver");

        con = DriverManager.getConnection(dbURL, p);

    }


    public void ExecuteStatement(String SQL) throws SQLException {

        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(SQL);
            stmt.close();
        } catch (SQLException s) {
            System.out.println(SQL);
            s.printStackTrace();
            System.out.println("SQL statement is not executed!");
        }
    }

    public void updateName(int medicine_Id, String name) {
        try {
            System.out.println("update  medicine set medicine_Name = '" + name + "' where medicine_Id = " + medicine_Id);
            connectDB();
            ExecuteStatement("update  medicine set medicine_Name = '" + name + "' where medicine_Id = " + medicine_Id + ";");
            con.close();
            System.out.println("Connection closed");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updatePrice(int medicine_id, int price) {
        try {
            System.out.println("update  medicine set price = " + price + " where medicine_id = " + medicine_id);
            connectDB();
            ExecuteStatement("update  medicine set price = " + price + " where medicine_id = " + medicine_id + ";");
            con.close();
            System.out.println("Connection closed");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }



  */

