package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class OrderStateWindowController {

    @FXML
    private Button GoBackButton;

    @FXML
    private Label OrderCodeTextField;

    @FXML
    private TextField OrderDateTextField;

    @FXML
    private Label OrderPriceTextField;

    @FXML
    private TextField OrderStateTextField;

    @FXML
    private TextField Produc;

    @FXML
    void OnClickGoBack(ActionEvent event) throws IOException {
    	Main.changeWindow("OrdersWindow.fxml");
    }

}

