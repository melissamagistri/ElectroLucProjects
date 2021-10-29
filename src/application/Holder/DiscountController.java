package application.Holder;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class DiscountController {

    @FXML
    private Button ApplyDiscountButton;

    @FXML
    private Button GoBackButton;

    @FXML
    private TextField SearchBar;

    @FXML
    private Button SearchProductButton;
    
    @FXML
    private TextField txModel;

    @FXML
    private TextField txPrice;
    
    @FXML
    private TextField txNewDiscount;

    @FXML
    private TextField txOldDiscoun;

    @FXML
    void OnClickApplyDiscount(ActionEvent event) throws IOException {
    	HolderMain.changeWindow("ManageDiscount.fxml");
    }

    @FXML
    void OnClickGoBack(ActionEvent event) throws IOException {
    	HolderMain.changeWindow("Holder.fxml");
    }

    @FXML
    void OnClickSearchProductButton(ActionEvent event) {

    }
    
    @FXML
    void OnClickDeleteDiscount(ActionEvent event) {

    }

}
