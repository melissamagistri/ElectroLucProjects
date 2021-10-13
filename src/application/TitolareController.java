package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class TitolareController {

    @FXML
    private VBox VBoxButtons;

   
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
    	Main.changeWindow("CambioDIpDaTitolare.fxml");
    }

    @FXML
    void OnClickProducts(ActionEvent event) throws IOException {
    	Main.changeWindow("AddProduct.fxml");
    }
    
}