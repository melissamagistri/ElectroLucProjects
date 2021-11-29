package application.Holder;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class WarehouseController {

    @FXML
    private TextField txCompartament;

    @FXML
    private TextField txID;

    @FXML
    private TextField txLane;

    @FXML
    private TextField txShelf;

    @FXML
    void OnClickApply(ActionEvent event) {

    }

    @FXML
    void OnClickGoBack(ActionEvent event) throws IOException {
    	HolderMain.changeWindow("HolderController.fxml");
    }

}
