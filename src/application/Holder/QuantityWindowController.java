package application.Holder;

import java.io.IOException;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class QuantityWindowController {

    @FXML
    private Button GoBackButton;

    @FXML
    private Button SearchProductButton;

    @FXML
    private Button SearchQuantity;

    @FXML
    private Button UpdateButton;

    @FXML
    void OnClickGoBack(ActionEvent event) throws IOException {
    	HolderMain.changeWindow("Holder.fxml");
    }

    @FXML
    void OnClickSearchProduct(ActionEvent event) {

    }

    @FXML
    void OnClickSearchQuantity(ActionEvent event) {

    }

    @FXML
    void OnClickUpdate(ActionEvent event) {

    }

}