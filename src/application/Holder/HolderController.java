package application.Holder;

import java.io.IOException;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class HolderController {

    @FXML
    private VBox VBoxButtons;

   
    @FXML
    void OnClickAddEmployee(ActionEvent event) throws IOException {
    	HolderMain.changeWindow("AddEmployee.fxml");
    }

    @FXML
    void OnClickDiscount(ActionEvent event) throws IOException{
    	HolderMain.changeWindow("ApplyDiscount.fxml");
    }

    @FXML
    void OnClickModifyEmployee(ActionEvent event) throws IOException{
    	HolderMain.changeWindow("ModifyEmployee.fxml");
    }

    @FXML
    void OnClickProducts(ActionEvent event) throws IOException {
    	HolderMain.changeWindow("ConfirmSupplier.fxml");
    }
    
    @FXML
    void OnClickQuantityButton(ActionEvent event) throws IOException {
    	HolderMain.changeWindow("QuantityWindow.fxml");
    }
   
}