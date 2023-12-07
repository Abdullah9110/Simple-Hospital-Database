package db2.view;

import db2.Main;
import javafx.fxml.FXML;

import java.io.IOException;

public class MainController {
    @FXML
    private void goHome() throws IOException {
        Main.showMainView();

    }
}
