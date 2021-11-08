package application.Holder;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import db.connection.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Model;

public class QuantityWindowController{

	@FXML
    private Button GoBackButton;

    @FXML
    private Button SearchProductButton;

    @FXML
    private Button SearchQuantity;

    @FXML
    private Button UpdateButton;

    @FXML
    private TableColumn<Model, Integer> codecolumn;

    @FXML
    private TableColumn<Model, String> modelcolumn;

    @FXML
    private TableColumn<Model, Integer> quantitycolumn;

    @FXML
    private TableView<Model> tableview;

    @FXML
    private TextField txSearchModel;

    @FXML
    private TextField txSearchQuantity;
    
    public static int modelIdToUpdate;

    @FXML
    void OnClickGoBack(ActionEvent event) throws IOException {
    	HolderMain.changeWindow("Holder.fxml");
    }

    @FXML
    void OnClickSearchProduct(ActionEvent event) throws SQLException {
    	Connection connection;
		String sql = "SELECT ModelName, ModelID, UnitinStock "+ 
				"FROM `negozio elettronica`.models " + "where ModelId = '"
						+this.txSearchModel.getText()+ "'";
		ObservableList<Model> list = FXCollections.observableArrayList();
		
		try {
			connection = new DBConnection().getMySQLConnection().get();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				list.add(new Model(Integer.parseInt(resultSet.getString("ModelID")), resultSet.getString("ModelName"), sql, sql, 
						null, null, Integer.parseInt(resultSet.getString("UnitinStock")), sql, false));
			}
			this.codecolumn.setCellValueFactory(new PropertyValueFactory<Model, Integer>("modelID"));
			this.modelcolumn.setCellValueFactory(new PropertyValueFactory<Model, String>("modelName"));
			this.quantitycolumn.setCellValueFactory(new PropertyValueFactory<Model, Integer>("unitInStock"));
			this.tableview.setItems(list);
		} catch (ClassNotFoundException | SQLException e) {
			Alert alert = new Alert(AlertType.ERROR, "there was a problem with the db connection");
			alert.show();
			return;
		}
    }

    @FXML
    void OnClickSearchQuantity(ActionEvent event) throws SQLException {
    	Connection connection;
		String sql = "SELECT ModelName, ModelID, UnitinStock "+ 
				"FROM `negozio elettronica`.models " + "where UnitinStock = '"
						+this.txSearchQuantity.getText()+ "'";
		ObservableList<Model> list = FXCollections.observableArrayList();
		
		try {
			connection = new DBConnection().getMySQLConnection().get();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				list.add(new Model(Integer.parseInt(resultSet.getString("ModelID")), resultSet.getString("ModelName"), sql, sql, 
						null, null, Integer.parseInt(resultSet.getString("UnitinStock")), sql, false));
			}
			this.codecolumn.setCellValueFactory(new PropertyValueFactory<Model, Integer>("modelID"));
			this.modelcolumn.setCellValueFactory(new PropertyValueFactory<Model, String>("modelName"));
			this.quantitycolumn.setCellValueFactory(new PropertyValueFactory<Model, Integer>("unitInStock"));
			this.tableview.setItems(list);
		} catch (ClassNotFoundException | SQLException e) {
			Alert alert = new Alert(AlertType.ERROR, "there was a problem with the db connection");
			alert.show();
			return;
		}
    }
    

    @FXML
    void OnClickUpdate(ActionEvent event) throws IOException {
    	if(this.tableview.getSelectionModel().getSelectedItems().size() > 1) {
    		Alert alert = new Alert(AlertType.ERROR, "You can select only one model for time");
			alert.show();
			return;
    	} else {
    		QuantityWindowController.modelIdToUpdate = this.tableview.getSelectionModel()
    				.getSelectedItem().getModelID();
    		HolderMain.changeWindow("");
    	}
    	
    }
}
