package application;


import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class AddProductController {

    @FXML
    private TextField DescriptionTextField;

    @FXML
    private Button GoBackButton;

    @FXML
    private TextField ModelTextField;

    @FXML
    private TextField PriceTextField;

    @FXML
    private TextField UnitStockTextField;

    @FXML
    private ChoiceBox<?> choiceBox;

    @FXML
    void OnClickAddButton(ActionEvent event) {

    }

    @FXML
    void OnClickGoBack(ActionEvent event) throws IOException {
    	Main.changeWindow("Holder.fxml");
    }

}




