package application.Holder;

import java.io.IOException;
import java.sql.SQLException;

import db.actions.Discount;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

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
    	try {
			Discount.applyDiscount(Integer.valueOf(CodeTextFiled.getText()), Integer.valueOf(DiscountTextField.getText()));
		} catch (SQLException e) {
			Alert alert = new Alert(AlertType.ERROR, e.getMessage());
    		alert.show();
		}
    }

    @FXML
    void OnClickGoBack(ActionEvent event) throws IOException {
    	HolderMain.changeWindow("Holder.fxml");
    }

}

