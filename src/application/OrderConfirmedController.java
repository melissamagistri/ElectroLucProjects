package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class OrderConfirmedController {

    @FXML
    private Button GoBackButton;

    @FXML
    private TextField OrderNumberTextField;

    @FXML
    void OnClickGoBack(ActionEvent event) throws IOException {
    	Main.changeWindow("BuyProduct.fxml");
    }

}

