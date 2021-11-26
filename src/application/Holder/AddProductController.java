package application.Holder;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import db.actions.ActionsOnCategory;
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
    private TextField PriceTextField;

    @FXML
    private TextField UnitTextField;

    @FXML
    private ChoiceBox<String> choiceBox;
    
    @FXML
    private ChoiceBox<String> supplicerChoiceBox;
    
    private int insale = 1;
    
    private ObservableList<String> categoryList;
    
    private ObservableList<String> supplierList;

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
    			 choiceBox.getValue().isBlank() ||
    			 this.UnitTextField.getText().equals("")) { 
    				 
    				 Alert alert2 = new Alert(AlertType.ERROR, "You must insert all the datas"); 
    				 alert2.show(); 
    				 return; 
    			}
    	try {
    		conn = new DBConnection().getMySQLConnection().get();

    		ActionsOnCategory.insert(conn, choiceBox.getValue());
    		
    		int price = Integer.parseInt(this.PriceTextField.getText()) * Integer.parseInt(this.UnitTextField.getText());
    		
    		String sql = "Insert into `negozio elettronica`.models (`ModelID`, `ModelName`, `Brand`,`Description`, `Category` , `UnitSellingPrice` , `UnitInStock`, `InSale`)"
   				 + " values ('" + this.ModelIDTextField.getText()+ "', '" + this.ModelNameTextField.getText() +"', '" + this.BrandTextField.getText() + "','"
   				 + this.DescriptionTextField.getText() + "', '" + this.choiceBox.getValue() + "', '" + this.PriceTextField.getText() + "', '" 
   				 + this.UnitTextField.getText() + "', '" + this.insale + "')" ;
   				  
   		  	Statement statement = conn.createStatement();
   		  	statement.executeUpdate(sql); 
   		  	
   		  	int purchaseOrders = this.getNewOrdersID();
   		  	
   		  	sql = "Insert into `negozio elettronica`.purchase_orders (`OrderID`, `Quantity`, `UnitPurchasePrice`, `ModelID`)"
				 + " values ('" + purchaseOrders + "', '" + this.UnitTextField.getText() + "', '" + price
				 + "', '" + this.ModelIDTextField.getText() + "')" ;
		
   		  	statement.executeUpdate(sql);
   		  	
   		  	sql = "Insert into `negozio elettronica`.purchase_invoices (`InvoiceID` ,`OrderID`, `IssueDate`, `SupplierID`)"
				 + " values ('" +this.getNewInvoicesID() + "', '" + purchaseOrders + "', '" + this.getCurrentDate()
				 + "', '" + this.supplicerChoiceBox.getValue() + "')" ;
		
   		  	statement.executeUpdate(sql);
   		  
   		  	Alert alert1 = new Alert(AlertType.INFORMATION, "Insert corretly a new model"); 
   		  	alert1.show();
    	} catch (ClassNotFoundException | SQLException e) {
    		e.printStackTrace();
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
    
    
    private int getNewInvoicesID() {
    	Connection connection; 
		  try { 
			  connection = new DBConnection().getMySQLConnection().get(); 
			  String sql="SELECT InvoiceID from `negozio elettronica`.purchase_invoices " + 
					  "order by InvoiceID desc " + 
					  "limit 1";
						 
				Statement statement = connection.createStatement();
		    	ResultSet resultSet = statement.executeQuery(sql);
		    	if(resultSet.next()) {
		    		return resultSet.getInt("InvoiceID") + 1;
		    	}
			  
		  } catch (ClassNotFoundException |SQLException e) {
		 System.out.println("there was a problem with the db connection");
		  }
		return 0;
		
		
    } private int getNewOrdersID() {
    	Connection connection; 
		  try { 
			  connection = new DBConnection().getMySQLConnection().get(); 
			  String sql="SELECT OrderID from `negozio elettronica`.purchase_orders " + 
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
		this.supplicerChoiceBox.setValue(AddSupplierController.supplierID);
		categoryList = FXCollections.observableArrayList();
		supplierList = FXCollections.observableArrayList();
    	try {
			categoryList.addAll(ActionsOnCategory
					.searchAll(new DBConnection().getMySQLConnection().get())
					.stream()
					.map(e->e.getCategoryName())
					.collect(Collectors.toList()));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.choiceBox.setItems(categoryList);
		this.supplicerChoiceBox.setItems(getSupplierList());
	
	} 

	private ObservableList<String> getSupplierList(){
		Connection connection; 
		  try { 
			  connection = new DBConnection().getMySQLConnection().get(); 
			  String sql="SELECT VAT_Number from `negozio elettronica`.suppliers ";
						 
				Statement statement = connection.createStatement();
		    	ResultSet resultSet = statement.executeQuery(sql);
		    	while(resultSet.next()) {
		    		this.supplierList.add(resultSet.getString("VAT_Number"));
		    	}
			  
		  } catch (ClassNotFoundException |SQLException e) {
			  System.out.println("there was a problem with the db connection");
		  }
		return this.supplierList;
		
	}
	
}




