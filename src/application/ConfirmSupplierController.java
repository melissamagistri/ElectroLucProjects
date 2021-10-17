package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ConfirmSupplierController {

    @FXML
    private Button NoButton;

    @FXML
    private Button YesButton;

    @FXML
    void OnClickNoButton(ActionEvent event) throws IOException {
    	Main.changeWindow("AddProduct.fxml");
    }

    @FXML
    void OnClickYes(ActionEvent event) throws IOException {
    	Main.changeWindow("AddSupplier.fxml");
    }

}

