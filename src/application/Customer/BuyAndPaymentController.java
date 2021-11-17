package application.Customer;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import db.connection.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Alert.AlertType;

public class BuyAndPaymentController {

    private int var = 1;
    
    private String orderType = "online"; 
    
    private ObservableList<String> paymentList = FXCollections.observableArrayList("Carta di credito", 
    		"Bancomat","Bonifico");

	    @FXML
	    private ChoiceBox<String> choicebox;

	    @FXML
	    void OnClickBuyProduct(ActionEvent event) throws SQLException, IOException {
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
	    	
	    	
	    	String query = "select * from models where ModelID = " +BuyProductController.ID +";";
	    	Statement statement = connection.createStatement();
			ResultSet res = statement.executeQuery(query);
			
			res.next();
			int discount = Optional.ofNullable(res.getInt("Discount")).isEmpty() ? 0 : res.getInt("Discount");
			BigDecimal pr = res.getBigDecimal("UnitSellingPrice");
			BigDecimal price = pr.subtract(pr.multiply(BigDecimal.valueOf(discount)).divide(BigDecimal.valueOf(100),RoundingMode.HALF_UP));
	    	
			
	    	int orderID = this.getNewID();
	    	String sql="Insert into `negozio elettronica`.orders (`OrderID`, `OrderDate`,`ModelID`,TotalAmount,`PaymentMethod`, `OrderType`, `Email`)"
					 + " values ('" + orderID + "', '" + this.getCurrentDate() +"', '" + BuyProductController.ID + "', '"
					 + price + "', '"+ this.choicebox.getValue() + "','"
					 + this.orderType + "', '" + CustomerMain.CustomerEmail + "')" ;
					  
			statement = connection.createStatement();
			statement.executeUpdate(sql);
			  
			sql = "Update `negozio elettronica`.models "
			  		+ "SET `UnitInStock` = '" + this.UnitInStock() 
			  		+ "' WHERE (`ModelID` = '" + BuyProductController.ID +"')";
			
			statement.executeUpdate(sql);
			
			sql = "Insert into `negozio elettronica`.purchase_certificate (`CertificateID` ,`OrderID`)"
					 + " values ('" + orderID + "', '" + orderID + "')" ;
			
			statement.executeUpdate(sql);
			 
	    	CustomerMain.changeWindow("OrderConfirmedWindow.fxml");
	    }

	    @FXML
	    void OnClickGoBack(ActionEvent event) throws IOException {
	    	CustomerMain.changeWindow("BuyProduct.fxml");
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
	    
	    private String getCurrentDate() {
	    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-mm-dd");  
	    	   LocalDateTime now = LocalDateTime.now();  
	    	   return now.toString();  
	    }
	    
	    private int UnitInStock() {
	    	Connection connection; 
			  try { 
				  connection = new DBConnection().getMySQLConnection().get(); 
				  String sql="SELECT UnitInStock from `negozio elettronica`.models " + 
						  "where ModelID = " + BuyProductController.ID;
							 
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
	    
	    @FXML 
	    private void initialize() {
	    	this.choicebox.setItems(paymentList);
	    }

}
