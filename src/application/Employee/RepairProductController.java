package application.Employee;

import java.io.IOException;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class RepairProductController {

	    @FXML
	    private Button GoBackButton;

	    @FXML
	    private Button RepairButton;

	    @FXML
	    void OnClickGoBack(ActionEvent event) throws IOException {
	    	EmployeeMain.changeWindow("ValidWarranty.fxml");
	    }

	    @FXML
	    void OnClickRepair(ActionEvent event) {

	    }

	}

