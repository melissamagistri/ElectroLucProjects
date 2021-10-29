package application.Customer;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class RegisterController {

    @FXML
    private TextField txDeliveryAddress;

    @FXML
    private TextField txEmail;

    @FXML
    private TextField txFiscalCode;

    @FXML
    private TextField txName;

    @FXML
    private TextField txPassword;

    @FXML
    private TextField txPhone;

    @FXML
    private TextField txSurname;

    @FXML
    void OnClickCreate(ActionEvent event) {

    }

    @FXML
    void OnClickGoBack(ActionEvent event) throws IOException {
    	CustomerMain.changeWindow("Login.fxml");
    }

}

