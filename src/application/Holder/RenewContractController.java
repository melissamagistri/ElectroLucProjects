package application.Holder;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class RenewContractController {

    	private ObservableList<String> contractList = FXCollections.observableArrayList("FullTime Determinated", 
    		"PartTime Determinated","FullTime Indeterminated", "PartTime Indeterminated");
    
	    @FXML
	    private ChoiceBox<String> choiceBox;

	    @FXML
	    private TextField txEndDate;

	    @FXML
	    private TextField txHireDate;

	    @FXML
	    void OnClickGoBack(ActionEvent event) throws IOException {
	    	HolderMain.changeWindow("ModifyFinishedEmployee.fxml");
	    }

	    @FXML
	    void OnClickRenew(ActionEvent event) {
	    	
	    }

	}
