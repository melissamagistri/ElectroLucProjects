package application.Customer;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import application.Holder.HolderMain;
import application.Holder.QuantityWindowController;
import db.connection.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Model;

public class BuyProductController {

	protected static int ID; 
	
    @FXML
    private TextField SearchProductTextField;
    
    @FXML
    private TableColumn<Model, String> categorycolumn;

    @FXML
    private ChoiceBox<String> choicebox;
    
    private ObservableList<String> categoryList = FXCollections.observableArrayList("Smartphone", 
    		"Computer","Auricolari", "Tablet","Smartwatch", "TV", "Monitor");
    
    @FXML
    private TableColumn<Model, String> descriptioncolumn;

    @FXML
    private TableColumn<Model, String> namecolumn;

    @FXML
    private TableColumn<Model, BigDecimal> pricecolumn;
    
    @FXML
    private TableColumn<Model, Integer> IDColumn;
    
    @FXML
    private TableView<Model> tableView;
    

    @FXML
    void OnClickContinue(ActionEvent event) throws IOException, SQLException {
    	
    	if(this.tableView.getSelectionModel().getSelectedItems().size() == 1) {
    		Alert alert = new Alert(AlertType.ERROR, "You can select only one model for time");
			alert.show();
			return;
    	} else {
    		BuyProductController.ID= this.tableView.getSelectionModel()
    				.getSelectedItem().getModelID();

        	CustomerMain.changeWindow("BuyAndPayment.fxml");
    	}
    }

    @FXML
    void OnClickGoBSck(ActionEvent event) throws IOException {
    	CustomerMain.changeWindow("ClientWindow.fxml");
    }

    @FXML
    void OnClickSearchProduct(ActionEvent event) {
    	Alert alert;
    	Connection connection;
    	
    	String sql = "SELECT ModelID, ModelName, Category, UnitPrice, Description "+ 
				"FROM `negozio elettronica`.models " +
				"where ModelName= '"+ this.SearchProductTextField.getText()+"'"  ;
    	
    	ObservableList<Model> list = FXCollections.observableArrayList();
    	
    	try {
    		connection = new DBConnection().getMySQLConnection().get();
    		Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				list.add(new Model(Integer.parseInt(resultSet.getString("ModelID")), resultSet.getString("ModelName"), sql, resultSet.getString("Description"), 
						new BigDecimal(resultSet.getString("UnitPrice")), new BigDecimal(0), Optional.empty(), 0, resultSet.getString("Category"), true));
			}
			this.namecolumn.setCellValueFactory(new PropertyValueFactory<Model, String>("modelName"));
			this.categorycolumn.setCellValueFactory(new PropertyValueFactory<Model, String>("category"));
			this.IDColumn.setCellValueFactory(new PropertyValueFactory<Model, Integer>("modelID"));
			this.pricecolumn.setCellValueFactory(new PropertyValueFactory<Model, BigDecimal>("unitSellingPrice"));
			this.descriptioncolumn.setCellValueFactory(new PropertyValueFactory<Model, String>("description"));
			this.tableView.setItems(list);
    		
    	} catch (ClassNotFoundException e) {
			alert = new Alert(AlertType.ERROR, "Error: Driver not found");
    		alert.show();
    		return;
		} catch (SQLException e) {
			alert = new Alert(AlertType.ERROR, "Error: unable to connect with the database");
    		alert.show();
    		return;
		}
    }

    @FXML
    void OnActionSearchCategory(ActionEvent event) {
    	Alert alert;
    	Connection connection;
    	
    	String sql = "SELECT ModelID, ModelName, Category, UnitPrice, Description "+ 
				"FROM `negozio elettronica`.models " +
				"where Category= '"+this.choicebox.getValue()+"'"  ;
    	
    	ObservableList<Model> list = FXCollections.observableArrayList();
    	
    	try {
    		connection = new DBConnection().getMySQLConnection().get();
    		
    		Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				list.add(new Model(Integer.parseInt(resultSet.getString("ModelID")), resultSet.getString("ModelName"), sql, resultSet.getString("Description"), 
						new BigDecimal(resultSet.getString("UnitPrice")),new BigDecimal(0), Optional.empty(), 0, resultSet.getString("Category"), true));
			}
			this.namecolumn.setCellValueFactory(new PropertyValueFactory<Model, String>("modelName"));
			this.categorycolumn.setCellValueFactory(new PropertyValueFactory<Model, String>("category"));
			this.IDColumn.setCellValueFactory(new PropertyValueFactory<Model, Integer>("modelID"));
			this.pricecolumn.setCellValueFactory(new PropertyValueFactory<Model, BigDecimal>("unitSellingPrice"));
			this.descriptioncolumn.setCellValueFactory(new PropertyValueFactory<Model, String>("description"));
			this.tableView.setItems(list);
    	} catch (ClassNotFoundException e) {
			alert = new Alert(AlertType.ERROR, "Error: Driver not found");
    		alert.show();
    		return;
		} catch (SQLException e) {
			alert = new Alert(AlertType.ERROR, "Error: unable to connect with the database");
    		alert.show();
    		return;
		}
    }

    @FXML 
    private void initialize() {
    	this.choicebox.setItems(categoryList);
    }
    
    
  
}

