package application.Holder;

import java.io.IOException;


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
    	HolderMain.changeWindow("Holder.fxml");
    }

}

