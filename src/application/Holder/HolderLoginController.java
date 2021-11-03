package application.Holder;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

public class HolderLoginController {

    @FXML
    private TextField tx_Password;
    
    private int entryCode = -1577701266;

    @FXML
    void OnClickLogin(ActionEvent event) throws IOException {
    	if(this.tx_Password.getText().equals("")) {
    		Alert alert = new Alert(AlertType.ERROR, "You need to insert the password");
    		alert.show();
    	} else {
    		if(!(this.entryCode == this.tx_Password.getText().hashCode())) {
    			Alert alert = new Alert(AlertType.ERROR, "You had insert a wrong password");
    			alert.show();
    		} else {
    			HolderMain.changeWindow("Holder.fxml");
    		}
    	}
    }

}

