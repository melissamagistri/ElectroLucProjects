package application.Holder;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ChangeSalaryController {

    @FXML
    private TextField txEmployeeID;

    @FXML
    private TextField txNewSalary;

    @FXML
    private TextField txOldSalary;

    @FXML
    void OnClickChange(ActionEvent event) {
    	
    }

    @FXML
    void OnClickGoBack(ActionEvent event) throws IOException {
    	HolderMain.changeWindow("Holder.fxml");
    }

}

