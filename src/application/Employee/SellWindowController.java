package application.Employee;

import java.io.IOException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Optional;

import application.Customer.BuyProductController;
import application.Customer.CustomerMain;
import application.Holder.HolderMain;
import application.Holder.QuantityWindowController;
import db.connection.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Model;

public class SellWindowController {
	
	 	@FXML
	    private TableColumn<Model, Integer> IDcolumn;

	    @FXML
	    private TableColumn<Model, String> descriptionColumn;

	    @FXML
	    private TableColumn<Model, String> nameColumn;

	    @FXML
	    private TableColumn<Model, BigDecimal> priceColumn;

	    @FXML
	    private TableColumn<Model, Integer> quantityColumn;
	    
	    @FXML
	    private TableColumn<Model, Integer> discountColumn;

	    @FXML
	    private TextField searchTextField;

	    @FXML
	    private TableView<Model> tableview;

    @FXML
    void OnClickGoBack(ActionEvent event) throws IOException {
    	EmployeeMain.changeWindow("EmployeeWindow.fxml");
    }

    @FXML
    void OnClickSell(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
    	if(this.tableview.getSelectionModel().getSelectedItems().size() > 1) {
    		Alert alert = new Alert(AlertType.ERROR, "You can select only one model for time");
			alert.show();
			return;
    	} else {
    		Connection connection;
    		connection = new DBConnection().getMySQLConnection().get();
    		
    		int orderID = this.getNewID();
    		
    		int modelIdToUpdate = this.tableview.getSelectionModel()
    				.getSelectedItem().getModelID();
    		
    		var modelPrice = this.tableview.getSelectionModel().getSelectedItem().getUnitSellingPrice();
    		int discount = Optional.ofNullable(this.tableview.getSelectionModel().getSelectedItem().getDiscount())
    						.isEmpty() ? 0 : this.tableview.getSelectionModel().getSelectedItem().getDiscount();
			BigDecimal price = modelPrice.subtract(modelPrice.multiply(BigDecimal.valueOf(discount)).divide(BigDecimal.valueOf(100),RoundingMode.HALF_UP));
	    	
    		String sql="Insert into `negozio elettronica`.orders (`OrderID`, `OrderDate`,`ModelID`,TotalAmount,`PaymentMethod`, `OrderType`, `EmployeeID`)"
					 + " values ('" + orderID + "', '" + this.getCurrentDate() +"', '" + modelIdToUpdate+ "', '"
					 + price + "', '"+ "cash" + "','"
					 + "in store" + "', '" + LoginController.employeeId + "')" ;
					  
			Statement statement = connection.createStatement();
			statement.executeUpdate(sql);
			
			sql = "Insert into `negozio elettronica`.purchase_certificates (`CertificateID` ,`OrderID`)"
					 + " values ('" + getNewPurchaseID() + "', '" + orderID + "')" ;
			
			statement.executeUpdate(sql);
			
			sql = "Update `negozio elettronica`.models "
			  		+ "SET `UnitInStock` = '" + this.UnitInStock(""+modelIdToUpdate) 
			  		+ "' WHERE (`ModelID` = '" + modelIdToUpdate +"')";
    		statement = connection.createStatement();
    		statement.executeUpdate(sql);
    		
    		Alert alert = new Alert(AlertType.INFORMATION, "Your sell was done correctly");
    		alert.show();
    	}
    	
    }
    
    @FXML
    void OnCLickSearch(ActionEvent event) throws IOException {
    	Connection connection;
		
		ObservableList<Model> list = FXCollections.observableArrayList();
		
		try {
			connection = new DBConnection().getMySQLConnection().get();
			String sql= "SELECT ModelName, ModelID, UnitInStock, Description, UnitSellingPrice, Discount "+ 
					"FROM `negozio elettronica`.models " + "where ModelName = '"
							+this.searchTextField.getText()+ "' "
									+ "and InSale = 1 "+
							"and UnitInStock > 0";
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			
			while(resultSet.next()) {
				list.add(new Model(Integer.parseInt(resultSet.getString("ModelID")), resultSet.getString("ModelName"), 
						sql, resultSet.getString("Description"), 
						new BigDecimal(resultSet.getString("UnitSellingPrice")),
						Optional.ofNullable((resultSet.getInt("Discount"))),
						Integer.parseInt(resultSet.getString("UnitInStock")), sql, false));
			}
			this.IDcolumn.setCellValueFactory(new PropertyValueFactory<Model, Integer>("modelID"));
			this.nameColumn.setCellValueFactory(new PropertyValueFactory<Model, String>("modelName"));
			this.quantityColumn.setCellValueFactory(new PropertyValueFactory<Model, Integer>("unitInStock"));
			this.priceColumn.setCellValueFactory(new PropertyValueFactory<Model, BigDecimal>("unitSellingPrice"));
			this.descriptionColumn.setCellValueFactory(new PropertyValueFactory<Model, String>("description"));
			this.discountColumn.setCellValueFactory(new PropertyValueFactory<Model, Integer>("discount"));
			this.tableview.setItems(list);
			
			
		} catch (ClassNotFoundException | SQLException e) {
			Alert alert = new Alert(AlertType.ERROR, "there was a problem with the db connection");
			alert.show();
			return;
		}
    }
    
    private int getNewID() {
    	Connection connection; 
		  try { 
			  connection = new DBConnection().getMySQLConnection().get(); 
			  String sql="SELECT OrderID from `negozio elettronica`.orders " + 
					  "order by OrderID desc " + 
					  "limit 1";
						 
				Statement statement = connection.createStatement();
		    	ResultSet resultSet = statement.executeQuery(sql);
		    	if(resultSet.next()) {
		    		return resultSet.getInt("OrderID") + 1;
		    	}
			  
		  } catch (ClassNotFoundException |SQLException e) {
		 System.out.println("there was a problem with the db connection");
		  }
		return 0;
    } 
    
    private int getNewPurchaseID() {
    	Connection connection; 
		  try { 
			  connection = new DBConnection().getMySQLConnection().get(); 
			  String sql="SELECT CertificateID from `negozio elettronica`.purchase_certificates " + 
					  "order by CertificateID desc " + 
					  "limit 1";
						 
				Statement statement = connection.createStatement();
		    	ResultSet resultSet = statement.executeQuery(sql);
		    	if(resultSet.next()) {
		    		return resultSet.getInt("CertificateID") + 1;
		    	}
			  
		  } catch (ClassNotFoundException |SQLException e) {
		 System.out.println("there was a problem with the db connection");
		  }
		return 0;
    } 
    
    private String getCurrentDate() {
 	   LocalDateTime now = LocalDateTime.now();  
 	   return now.toString();  
    }
    
    private int UnitInStock(String ID) {
    	Connection connection; 
		  try { 
			  connection = new DBConnection().getMySQLConnection().get(); 
			  String sql="SELECT UnitInStock from `negozio elettronica`.models " + 
					  "where ModelID = " + ID;
						 
				Statement statement = connection.createStatement();
		    	ResultSet resultSet = statement.executeQuery(sql);
		    	
		    	if(resultSet.next()) {
		    		return resultSet.getInt("UnitInStock") - 1;
		    	}
			  
		  } catch (ClassNotFoundException |SQLException e) {
		 System.out.println("there was a problem with the db connection unit");
		  }
		return 0;
    }
    
    
}