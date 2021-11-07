package application.Holder;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import db.actions.CheckInteger;
import db.connection.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class AddProductController {

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
    private TextField UnitStockTextField;

    @FXML
    private ChoiceBox<String> choiceBox;
    
    private ObservableList<String> categoryList = FXCollections.observableArrayList("Smartphone", 
    		"Computer","Auricolari", "Tablet");

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
    			 this.ModelNameTextField.equals("") ||
    			 this.PriceTextField.getText().equals("") ||
    			 this.SupplierTextField.equals("") || this.UnitStockTextField.equals("")) { 
    				 
    				 Alert alert2 = new Alert(AlertType.ERROR, "You must write the datas"); 
    				 alert2.show(); 
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

    	String sql="Insert into `negozio elettronica`.models (`ModelID`, `ModelName`, `Brand`,`Description`, `Category` , `UnitPrice` , `UnitInStock`, SalesCatalogMembership)"
				 + " values ('" + this.ModelIDTextField.getText()+ "', '" + this.ModelNameTextField.getText() +"', '" + this.BrandTextField.getText() + "','"
				 + this.DescriptionTextField.getText() + "', '" + this.choiceBox.getValue() + "', '" + this.PriceTextField.getText() + "', '" 
				 + this.UnitStockTextField.getText() + "',true)" ;
				  
		  Statement statement = conn.createStatement();
		  statement.executeUpdate(sql); 
		  
		  Alert alert1 = new Alert(AlertType.INFORMATION, "Insert corretly a new supplier"); 
		  alert1.show(); 

    	try {
			this.insertInSalesCatalog(conn, Integer.valueOf(ModelIDTextField.getText()));
		} catch (SQLException e) {
			alert = new Alert(AlertType.ERROR, "Error: " +e.getMessage());
    		alert.show();
		}
    }

    @FXML
    void OnClickGoBack(ActionEvent event) throws IOException {
    	HolderMain.changeWindow("Holder.fxml");
    }
    
    @FXML 
    private void initialize() {
    	this.choiceBox.setItems(categoryList);
    	
    }

}




