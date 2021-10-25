package application.Holder;

import java.io.IOException;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
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
    private ListView<?> informationArea;

    @FXML
    void OnClickChangeSalary(ActionEvent event) {
    	//schermata di cambio sakrio al dipendente
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


