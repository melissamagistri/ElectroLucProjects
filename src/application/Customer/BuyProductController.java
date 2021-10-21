package application.Customer;

import java.io.IOException;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class BuyProductController {

    @FXML
    private Button BuyButton;

    @FXML
    private Button GoBackButton;

    @FXML
    private ListView<?> ProductListView;

    @FXML
    private Button SearchProductButtot;

    @FXML
    private TextField SearchProductTextField;

    @FXML
    void OnClickBuy(ActionEvent event) {

    }

    @FXML
    void OnClickGoBSck(ActionEvent event) throws IOException {
    	Main.changeWindow("ClientWindow.fxml");
    }

    @FXML
    void OnClickSearchProduct(ActionEvent event) {

    }

}

