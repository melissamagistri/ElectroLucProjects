package application.Employee;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class ChangePasswordController {

    @FXML
    void OnClickChange(ActionEvent event) {

    }

    @FXML
    void OnClickGoBack(ActionEvent event) throws IOException {
    	EmployeeMain.changeWindow("EmployeeWindow.fxml");
    }

}

