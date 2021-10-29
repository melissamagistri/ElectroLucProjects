package application.Employee;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ValidWarrantyController {

    @FXML
    private Button GoBackButton;

    @FXML
    private Button SearchProductButton;


    @FXML
    void OnClickGoBack(ActionEvent event) throws IOException {
    	EmployeeMain.changeWindow("EmployeeWindow.fxml");
    }

    @FXML
    void OnClickSearchProduct(ActionEvent event) {

    }
    
    /*@FXML
    void OnClickRepairProduct(ActionEvent event) throws IOException {
    	EmployeeMain.changeWindow("RepairProduct.fxml");
    }*/

}

