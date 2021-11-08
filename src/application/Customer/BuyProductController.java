package application.Customer;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import db.connection.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Model;
import model.OrderTable;

public class BuyProductController {

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
    
    private int var = 1;

    @FXML
    void OnClickBuy(ActionEvent event) throws IOException, SQLException {
    	Alert alert;
    	Connection connection;
    	try {
    		connection = new DBConnection().getMySQLConnection().get();
    	} catch (ClassNotFoundException e) {
			alert = new Alert(AlertType.ERROR, "Error: Driver not found");
    		alert.show();
    		return;
		} catch (SQLException e) {
			alert = new Alert(AlertType.ERROR, "Error: unable to connect with the database");
    		alert.show();
    		return;
		}
    	
    	String sql="Insert into `negozio elettronica`.orders (`OrderID`, `OrderDate`,`ModelID`,`PaymentMethod`, `OrderType`, `Email`)"
				 + " values ('" + this.getID() + "', '" + this.getCurrentDate() +"', '" + this.clickedColumn()+ "', '" + this.var + "','"
				 + this.var+ "', '" + CustomerMain.CustomerEmail + "')" ;
				  
		  Statement statement = connection.createStatement();
		  statement.executeUpdate(sql);
    	CustomerMain.changeWindow("OrderConfirmedWindow.fxml");
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
						new BigDecimal(resultSet.getString("UnitPrice")), null, 0, resultSet.getString("Category"), false));
			}
			this.namecolumn.setCellValueFactory(new PropertyValueFactory<Model, String>("modelName"));
			this.categorycolumn.setCellValueFactory(new PropertyValueFactory<Model, String>("category"));
			this.IDColumn.setCellValueFactory(new PropertyValueFactory<Model, Integer>("modelID"));
			this.pricecolumn.setCellValueFactory(new PropertyValueFactory<Model, BigDecimal>("unitPrice"));
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
						new BigDecimal(resultSet.getString("UnitPrice")), null, 0, resultSet.getString("Category"), false));
			}
			this.namecolumn.setCellValueFactory(new PropertyValueFactory<Model, String>("modelName"));
			this.categorycolumn.setCellValueFactory(new PropertyValueFactory<Model, String>("category"));
			this.IDColumn.setCellValueFactory(new PropertyValueFactory<Model, Integer>("modelID"));
			this.pricecolumn.setCellValueFactory(new PropertyValueFactory<Model, BigDecimal>("unitPrice"));
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
    String clickedColumn() {
    	TablePosition tablePosition = this.tableView.getSelectionModel().getSelectedCells().get(0);
    	int row = tablePosition.getRow();
    	Model item = this.tableView.getItems().get(row);
    	TableColumn tableColumn = tablePosition.getTableColumn();
    	return tableColumn.getCellObservableValue(item).getValue().toString();
    }
    
    @FXML 
    private void initialize() {
    	this.choicebox.setItems(categoryList);
    	
    	this.tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    	this.tableView.getSelectionModel().setCellSelectionEnabled(true);
    }
    
    private int getID() {
    	Connection connection; 
		  try { 
			  connection = new DBConnection().getMySQLConnection().get(); 
			  String sql="SELET OrderID from `negozio elettronica`.orders " + 
					  "order by OrderID desc" + 
					  "limit 1";
						 
				Statement statement = connection.createStatement();
		    	ResultSet resultSet = statement.executeQuery(sql);
		    	
				return Integer.parseInt(resultSet.toString()) + 1;
			  
		  } catch (ClassNotFoundException |SQLException e) {
		 System.out.println("there was a problem with the db connection");
		  }
		return 0;
    } 
    
    private String getCurrentDate() {
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
    	   LocalDateTime now = LocalDateTime.now();  
    	   return now.toString();  
    }
    
    private int getModelID() {
    	Connection connection; 
		  try { 
			  connection = new DBConnection().getMySQLConnection().get(); 
			  String sql="SELET ModelID from `negozio elettronica`.models " + 
					  "where " + 
					  "limit 1";
						 
				Statement statement = connection.createStatement();
		    	ResultSet resultSet = statement.executeQuery(sql);
		    	
				return Integer.parseInt(resultSet.toString()) + 1;
			  
		  } catch (ClassNotFoundException |SQLException e) {
		 System.out.println("there was a problem with the db connection");
		  }
		return 0;
    }
}

