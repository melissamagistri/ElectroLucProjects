package application.Customer;

import java.io.IOException;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class OrdersWindowController {

    @FXML
    private Button GoBackButton;

    @FXML
    private Button OrderStateButton;

    @FXML
    private ListView<?> OrdersListView;

    @FXML
    void OnClickGoBack(ActionEvent event) throws IOException {
    	Main.changeWindow("ClientWindow.fxml");
    }

    @FXML
    void OnClickOrderState(ActionEvent event) {

    }

}
