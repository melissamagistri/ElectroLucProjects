package application;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField tx_Password;

    @FXML
    private TextField tx_email;

    @FXML
    void OnClickLogin(ActionEvent event) throws IOException {
    	Main.changeWindow("Holder.fxml");
    }

    @FXML
    void OnClickSignIn(ActionEvent event) {

    }

}