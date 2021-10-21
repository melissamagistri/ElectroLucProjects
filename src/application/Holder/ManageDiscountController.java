package application.Holder;

import java.io.IOException;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ManageDiscountController {

    @FXML
    private Button ApplyButton;

    @FXML
    private TextField CodeTextFiled;

    @FXML
    private TextField DiscountTextField;

    @FXML
    private Button GoBackButton;

    @FXML
    void OnClickApply(ActionEvent event) {

    }

    @FXML
    void OnClickGoBack(ActionEvent event) throws IOException {
    	Main.changeWindow("Holder.fxml");
    }

}

