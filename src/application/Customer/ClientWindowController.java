package application.Customer;

import java.io.IOException;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ClientWindowController {

    @FXML
    private Button BuyProductButton;

    @FXML
    private Button LogOutButton;

    @FXML
    private Button ViewOrdersButton;

    @FXML
    void OnClickBuyProduct(ActionEvent event) {

    }

    @FXML
    void OnClickLogOut(ActionEvent event) throws IOException {
    	Main.changeWindow("Login.fxml");
    }

    @FXML
    void OnClickViewOrders(ActionEvent event) {

    }

}

