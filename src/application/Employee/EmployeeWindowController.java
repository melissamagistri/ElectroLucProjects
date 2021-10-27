package application.Employee;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class EmployeeWindowController {

    @FXML
    private Button CheckAvailabilityButton;

    @FXML
    private Button LogOutButton;

    @FXML
    private Button ValidWarrantyButton;

    @FXML
    void OnClickCheckAvailability(ActionEvent event) throws IOException {
    	EmployeeMain.changeWindow("AvaliabilityProduct.fxml");
    }

    @FXML
    void OnClickLogOut(ActionEvent event) throws IOException {
    	EmployeeMain.changeWindow("EmployeeLogin.fxml");
    }

    @FXML
    void OnClickValidWarranty(ActionEvent event) throws IOException {
    	EmployeeMain.changeWindow("ValidWarranty.fxml");
    }

}

