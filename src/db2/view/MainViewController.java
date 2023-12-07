package db2.view;

import db2.Main;
import javafx.fxml.FXML;

import java.io.IOException;

public class MainViewController {
    @FXML
    private void goStaff() throws IOException {
        Main.showStaffReg();
    }
    @FXML
    private void goStatisticsMenu() throws IOException {
        Main.showStatMenu();
    }
    @FXML
    private void goPatientMenu() throws IOException {
        Main.showLoginMenu();
    }
}
