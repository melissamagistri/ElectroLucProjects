package application.Holder;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import db.actions.CheckInteger;
import db.connection.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class AddProductController implements Initializable{

    @FXML
    private TextField DescriptionTextField;

    @FXML
    private Button GoBackButton;

    @FXML
    private TextField BrandTextField;
    
    @FXML
    private TextField ModelIDTextField;

    @FXML
    private TextField ModelNameTextField;
    
    @FXML
    private TextField SupplierTextField;

    @FXML
    private TextField PriceTextField;

    @FXML
    private TextField UnitTextField;

    @FXML
    private ChoiceBox<String> choiceBox;
    
    private int insale = 1;
    
    private ObservableList<String> categoryList = FXCollections.observableArrayList("Smartphone", 
    		"Computer","Pods", "Tablet","Smartwatch", "TV", "Monitor");

    @FXML
    void OnClickAddButton(ActionEvent event) throws IOException, SQLException {
    	Alert alert;
    	Connection conn;
    	
    	if(!CheckInteger.isNumeric(ModelIDTextField.getText()) && !CheckInteger.isNumeric(PriceTextField.getText())) {
    		alert = new Alert(AlertType.ERROR, "Error: model's id must be an integer number");
    		alert.show();
    		return;
    	}
    	
    	if(this.BrandTextField.getText().equals("") ||
    			 this.DescriptionTextField.getText().equals("") ||
    			 this.ModelIDTextField.getText().equals("") ||
    			 this.ModelNameTextField.getText().equals("") ||
    			 this.PriceTextField.getText().equals("") ||
    			 this.SupplierTextField.getText().equals("") || this.UnitTextField.getText().equals("")) { 
    				 
    				 Alert alert2 = new Alert(AlertType.ERROR, "You must write the datas"); 
    				 alert2.show(); 
    				 return; 
    			}
    	try {
    		conn = new DBConnection().getMySQLConnection().get();
    		
    		String sql = "Insert into `negozio elettronica`.models (`ModelID`, `ModelName`, `Brand`,`Description`, `Category` , `UnitPrice` , `UnitInStock`, `InSale`)"
   				 + " values ('" + this.ModelIDTextField.getText()+ "', '" + this.ModelNameTextField.getText() +"', '" + this.BrandTextField.getText() + "','"
   				 + this.DescriptionTextField.getText() + "', '" + this.choiceBox.getValue() + "', '" + this.PriceTextField.getText() + "', '" 
   				 + this.UnitTextField.getText() + "', '" + this.insale + "')" ;
   				  
   		  Statement statement = conn.createStatement();
   		  statement.executeUpdate(sql); 
   		  
   		int orderID = this.getNewID();
   		  sql = "Insert into `negozio elettronica`.purchase_invoices (`InvoicesID` ,`OrderID`, IssueDate, TotalAmount, SupplierID)"
				 + " values ('" + orderID + "', '" + orderID + "', '" + this.getCurrentDate()+ 
				 "', '" + this.getTotalAmount() + "', '" + AddSupplierController.supplierID + "')" ;
		
   		  statement.executeUpdate(sql);
   		  
   		  Alert alert1 = new Alert(AlertType.INFORMATION, "Insert corretly a new model"); 
   		  alert1.show(); 
    	} catch (ClassNotFoundException | SQLException e) {
			alert = new Alert(AlertType.ERROR, "Error: Driver not found");
    		alert.show();
    		return;
		}
    }

    @FXML
    void OnClickGoBack(ActionEvent event) throws IOException {
    	HolderMain.changeWindow("Holder.fxml");
    }
    
    private String getCurrentDate() {
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-mm-dd");  
    	LocalDateTime now = LocalDateTime.now();  
    	return now.toString();
    }
    
    private Integer getTotalAmount() {
		return Integer.parseInt(this.UnitTextField.getText()) * Integer.parseInt(this.PriceTextField.getText());
    	
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.SupplierTextField.setText(AddSupplierController.supplierID);
    	this.choiceBox.setItems(categoryList);
		
	} 

}




