package application.Holder;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

import db.actions.ActionsOnProduct;
import db.actions.CheckInteger;
import db.connection.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Model;


public class RemoveProductController {

	@FXML
	private TextField searchText;

	@FXML
	private TableView<Model> products;

	@FXML
	private TableColumn<Model, Integer> modelID;

	@FXML
	private TableColumn<Model, String> modelName;

	@FXML
	private TableColumn<Model, String> modelBrand;

	@FXML
	private TableColumn<Model, String> description;

    @FXML
    void OnClickGoBack(ActionEvent event) throws IOException {
    	HolderMain.changeWindow("Holder.fxml");
    }

    @FXML
    void OnClickRemove(ActionEvent event) {
    	Alert alert;
    	Connection conn;
    	if(searchText.getText().isBlank()) {
			alert = new Alert(AlertType.ERROR, "You must insert the model id before apllying a discount");
			alert.show();
			return;
    	}
    	try {
    		conn = new DBConnection().getMySQLConnection().get();
    	} catch (ClassNotFoundException e) {
			alert = new Alert(AlertType.ERROR, "Error: Driver not found");
    		alert.show();
    		return;
		} catch (SQLException e) {
			alert = new Alert(AlertType.ERROR, "Error: unable to connect with the database");
    		alert.show();
    		return;
		}
    	try {
			ActionsOnProduct.deleteProduct(conn, products.getSelectionModel().getSelectedItem().getModelID());
			products.getItems().clear();
		} catch (SQLException e) {
			alert = new Alert(AlertType.ERROR, "Error: " +e.getMessage());
    		alert.show();
    		return;
		}
    }

    @FXML
    void OnClickSerach(ActionEvent event) {
    	Alert alert;
    	Connection conn;
    	if(searchText.getText().isBlank()) {
    		alert = new Alert(AlertType.ERROR, "the search text field cannot be empty");
    		alert.show();
    		return;
    	}
    	if(!CheckInteger.isNumeric(searchText.getText())) {
    		alert = new Alert(AlertType.ERROR, "model id must be an integer number");
    		alert.show();
    		return;
    	}
    	try {
    		conn = new DBConnection().getMySQLConnection().get();
    	} catch (ClassNotFoundException e) {
			alert = new Alert(AlertType.ERROR, "Error: Driver not found");
    		alert.show();
    		return;
		} catch (SQLException e) {
			alert = new Alert(AlertType.ERROR, "Error: unable to connect with the database");
    		alert.show();
    		return;
		}
    	try {
			Optional<Model> model = ActionsOnProduct.searchByID(conn, Integer.valueOf(searchText.getText()));
			if(model.isEmpty()) {
				alert = new Alert(AlertType.ERROR, "model not found");
	    		alert.show();
	    		return;
			}
			if(!model.get().isInSales() && model.get().getUnitInStock() == 0) {
				alert = new Alert(AlertType.ERROR, "model has already been removed");
	    		alert.show();
	    		return;
			}
			ObservableList<Model> list = FXCollections.observableArrayList();
			list.add(model.get());
			this.modelID.setCellValueFactory(new PropertyValueFactory<Model, Integer>("modelID"));
			this.modelName.setCellValueFactory(new PropertyValueFactory<Model, String>("modelName"));
			this.modelBrand.setCellValueFactory(new PropertyValueFactory<Model, String>("brand"));
			this.description.setCellValueFactory(new PropertyValueFactory<Model, String>("description"));
			this.products.setItems(list);
		} catch (SQLException e) {}
    }

}

