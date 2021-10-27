package application.Employee;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class ReciptWindowController {

    @FXML
    void OnClickReturn(ActionEvent event) throws IOException {
    	EmployeeMain.changeWindow("EmployeeWindow.fxml");
    }

}

