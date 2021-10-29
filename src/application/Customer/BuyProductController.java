package application.Customer;

import java.io.IOException;
import java.sql.SQLException;
import db.actions.ModelAction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.Model;

public class BuyProductController {

    @FXML
    private Button BuyButton;

    @FXML
    private Button GoBackButton;

    @FXML
    private ListView<Model> ProductListView;

    @FXML
    private Button SearchProductButtot;

    @FXML
    private TextField SearchProductTextField;

    @FXML
    void OnClickBuy(ActionEvent event) throws IOException {
    	CustomerMain.changeWindow("OrderConfirmedWindow.fxml");
    }

    @FXML
    void OnClickGoBSck(ActionEvent event) throws IOException {
    	CustomerMain.changeWindow("ClientWindow.fxml");
    }

    @FXML
    void OnClickSearchProduct(ActionEvent event) {
    	ProductListView = new ListView<Model>();
    	ObservableList<Model> items = FXCollections.observableArrayList ();
    	try {
			ModelAction.searchModelInSalesCatalog(SearchProductTextField.getText()).forEach(e -> items.add(e));
		} catch (SQLException e) {
			Alert alert = new Alert(AlertType.ERROR, e.getMessage());
    		alert.show();
		}
    	ProductListView.setItems(items);
    }

}

