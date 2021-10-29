package application.Employee;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class SellWindowController {

    @FXML
    void OnClickGoBack(ActionEvent event) throws IOException {
    	EmployeeMain.changeWindow("EmployeeWindow.fxml");
    }

    @FXML
    void OnClickSell(ActionEvent event) throws IOException {
    	EmployeeMain.changeWindow("ReciptWindow.fxml");
    }
    
    @FXML
    void OnCLickSearch(ActionEvent event) throws IOException {
    	
    }

}