package application.Holder;

import java.io.IOException;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ModifyEmployeeController {

	@FXML
    private Button ChangeSalaryButton;

    @FXML
    private Button DismissButton;

    @FXML
    private Button GoBackButton;

    @FXML
    private TextField SearchBar;

    @FXML
    private Button SearchButon;

    @FXML
    private TextField txContract;

    @FXML
    private TextField txFiscalCode;

    @FXML
    private TextField txIDCode;

    @FXML
    private TextField txName;

    @FXML
    private TextField txSurname;
    
    @FXML
    private TextField txSalary;

    /*public ModifyEmployeeController(){
    	this.txFiscalCode.setEditable(false);
    	this.txName.setEditable(false);
    	this.txSurname.setEditable(false);
    }*/
    @FXML
    void OnClickChangeSalary(ActionEvent event) {
    	
    }

    @FXML
    void OnClickDismiss(ActionEvent event) {

    }

    @FXML
    void OnClickGoBack(ActionEvent event) throws IOException {
    	HolderMain.changeWindow("Holder.fxml");
    }

    @FXML
    void OnClickSearch(ActionEvent event) {

    }

}


