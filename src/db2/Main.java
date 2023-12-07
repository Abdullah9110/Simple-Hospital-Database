package db2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.geometry.*;
import java.io.IOException;

public class Main extends Application{
    private static Stage primaryStage;
    private static BorderPane mainLayout;




    public void start(Stage primaryStage) throws IOException {
        Main.primaryStage = primaryStage;
        Main.primaryStage.setTitle("Hospital Management");
        showMain();
        showMainView();



    }

    private void showMain() throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/Main.fxml"));
        mainLayout = loader.load();
        Scene scene = new Scene(mainLayout);
        primaryStage.setScene(scene);
        primaryStage.show();
        mainLayout.setPadding(new Insets(30,30,30,30));
    }

    public static void showMainView() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/MainView.fxml"));
        BorderPane mainView = loader.load();
        mainLayout.setCenter(mainView);
        mainLayout.setPadding(new Insets(30,30,30,30));

    }

    public static void showLoginMenu() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("PatientMenu/login.fxml"));
        BorderPane mainView = loader.load();
        mainLayout.setCenter(mainView);
        mainLayout.setPadding(new Insets(30,30,30,30));

    }

    public static void showStatMenu() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("StatMenu/Queries.fxml"));
        BorderPane mainView = loader.load();
        mainLayout.setCenter(mainView);
        mainLayout.setPadding(new Insets(30,30,30,30));

    }

    public static void showPatientMenu() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("PatientMenu/patientMenu.fxml"));
        BorderPane mainView = loader.load();
        mainLayout.setCenter(mainView);
        mainLayout.setPadding(new Insets(30,30,30,30));

    }

    public static void showMedicineManagement() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("Staff/medicineMang.fxml"));
        BorderPane mainView = loader.load();
        mainLayout.setCenter(mainView);
        mainLayout.setPadding(new Insets(30,30,30,30));
    }

    public static void showBuyManagement() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("Staff/buy.fxml"));
        BorderPane mainView = loader.load();
        mainLayout.setCenter(mainView);
        mainLayout.setPadding(new Insets(30,30,30,30));
    }

    public static void showBillManagement() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("Staff/Bill.fxml"));
        BorderPane mainView = loader.load();
        mainLayout.setCenter(mainView);
        mainLayout.setPadding(new Insets(30,30,30,30));
    }

    public static void showDiagnoseManagement() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("Staff/diagnose.fxml"));
        BorderPane mainView = loader.load();
        mainLayout.setCenter(mainView);
        mainLayout.setPadding(new Insets(30,30,30,30));
    }

    public static void showStaffReg() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("Staff/staffReg.fxml"));
        BorderPane staffReg = loader.load();
        mainLayout.setCenter(staffReg);
        mainLayout.setPadding(new Insets(30,30,30,30));
    }

    public static void showStaffList() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("Staff/staffList.fxml"));
        BorderPane staffList = loader.load();
        mainLayout.setCenter(staffList);
        mainLayout.setPadding(new Insets(30,30,30,30));
    }

    public static void showRegPatient() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("PatientPackage/regPatient.fxml"));
        VBox patientReg = loader.load();
        mainLayout.setCenter(patientReg);
        mainLayout.setPadding(new Insets(30,30,30,30));
    }

    public static void showListPatient() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("PatientPackage/Patient List.fxml"));
        BorderPane patientList = loader.load();
        mainLayout.setCenter(patientList);
        mainLayout.setPadding(new Insets(30,30,30,30));

    }
    public static void showRoomManagement() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("Staff/roomManagement.fxml"));
        BorderPane room = loader.load();
        mainLayout.setCenter(room);
        mainLayout.setPadding(new Insets(30,30,30,30));
    }




    public static void main(String[] args){
        launch(args);
    }
}