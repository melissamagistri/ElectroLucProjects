package application.Customer;

import java.io.IOException;

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
    void OnClickBuyProduct(ActionEvent event) throws IOException {
    	CustomerMain.changeWindow("BuyProduct.fxml");
    }

    @FXML
    void OnClickLogOut(ActionEvent event) throws IOException {
    	CustomerMain.changeWindow("Login.fxml");
    }

    @FXML
    void OnClickViewOrders(ActionEvent event) throws IOException {
    	CustomerMain.changeWindow("OrdersWindow.fxml");
    }

}

