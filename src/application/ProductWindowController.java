package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ProductWindowController {

    @FXML
    private Button AddModelButton;

    @FXML
    private Button GoBackButton;

    @FXML
    private Button QuantityButton;

    @FXML
    void OnActionQuantity(ActionEvent event) {

    }

    @FXML
    void OnClcikGoBack(ActionEvent event) throws IOException {
    	Main.changeWindow("Holder.fxml");
    }

    @FXML
    void OnClickAddModel(ActionEvent event) throws IOException {
    	Main.changeWindow("AddProduct.fxml");
    }

}
