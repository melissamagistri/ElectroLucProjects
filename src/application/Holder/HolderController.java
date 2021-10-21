package application.Holder;

import java.io.IOException;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class HolderController {

    @FXML
    private VBox VBoxButtons;
    
    @FXML
    private Button OnClickLogOutButton;

   
    @FXML
    void OnClickAddEmployee(ActionEvent event) throws IOException {
    	Main.changeWindow("AddEmployee.fxml");
    }

    @FXML
    void OnClickDiscount(ActionEvent event) throws IOException{
    	Main.changeWindow("ApplyDiscount.fxml");
    }

    @FXML
    void OnClickModifyEmployee(ActionEvent event) throws IOException{
    	Main.changeWindow("ModifyEmployee.fxml");
    }

    @FXML
    void OnClickProducts(ActionEvent event) throws IOException {
    	Main.changeWindow("ConfirmSupplier.fxml");
    }
    
    @FXML
    void OnClickLogOutButton(ActionEvent event) throws IOException{
    	Main.changeWindow("Login.fxml"); //Oppure chiudi l'applicazione?ß
    }
}