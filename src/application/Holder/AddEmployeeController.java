package application.Holder;

import java.io.IOException;

import application.Main;
import javafx.event.ActionEvent;

import javafx.scene.control.ChoiceBox;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddEmployeeController {

    @FXML
    private Button AddButton;


    @FXML
    private ChoiceBox<?> ChoiceBox;
    
    @FXML
    private TextField CodeTextField;

    @FXML
    private Label EmailTextField;

    @FXML
    private Button GoBack;

    @FXML
    private Label NameTextField;

    @FXML
    private Label ShopEmailTextField;

    @FXML
    private Label SurnameTextField;

    @FXML
    void OnClickAddEmployee(ActionEvent event) {

    }

    @FXML
    void OnClickGoBack(ActionEvent event) throws IOException {
    	Main.changeWindow("Holder.fxml");
    }

}

