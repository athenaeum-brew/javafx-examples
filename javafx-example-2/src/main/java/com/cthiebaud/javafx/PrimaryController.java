package com.cthiebaud.javafx;

import java.io.IOException;
import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        HelloJavaFXMLWorld.setRoot("secondary");
    }
}
