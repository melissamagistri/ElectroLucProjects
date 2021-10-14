package application;

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
    private ListView<?> ProductListView;

    @FXML
    private TextField SearchBar;

    @FXML
    private Button SearchProductButton;


    @FXML
    void OnClickGoBack(ActionEvent event) throws IOException {
    	Main.changeWindow("EployeeWindow.fxml");
    }

    @FXML
    void OnClickSearchProduct(ActionEvent event) {

    }

}

