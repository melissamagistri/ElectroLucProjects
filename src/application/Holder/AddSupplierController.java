package application.Holder;

import java.io.IOException;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddSupplierController {

    @FXML
    private TextField AgencyAdrress;

    @FXML
    private TextField AgencyName;

    @FXML
    private Button ContinueButton;

    @FXML
    private TextField EmailAdress;

    @FXML
    private Label PhoneNumber;

    @FXML
    private TextField VATNumber;

    @FXML
    void OnClickContinue(ActionEvent event) throws IOException {
    	Main.changeWindow("AddProduct.fxml");
    }

}

