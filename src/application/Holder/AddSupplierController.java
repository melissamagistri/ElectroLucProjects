package application.Holder;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import db.connection.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class AddSupplierController {

    @FXML
    private TextField AgencyAdrress;

    @FXML
    private TextField AgencyName;

    @FXML
    private Button ContinueButton;

    @FXML
    private TextField EmailAdress;

    @FXML
    private Label PhoneNumber;

    @FXML
    private TextField VATNumber;

    @FXML
    void OnClickContinue(ActionEvent event) throws IOException, SQLException{
    	
    	Connection connection; 
		  try { 
			  connection = new DBConnection().getMySQLConnection().get(); 
			  
		  } catch (ClassNotFoundException |SQLException e) {
		 System.out.println("there was a problem with the db connection"); 
		 return; 
		 }
		  
		  if(this.AgencyAdrress.getText().equals("") || this.AgencyName.getText().equals("")|| this.EmailAdress.getText().equals("") ||
				  this.PhoneNumber.getText().equals("") || this.VATNumber.getText().equals("")){ 
						 
					Alert alert = new Alert(AlertType.ERROR, "You must write the datas"); 
					alert.show(); 
					return; 
			}
		  
		 if(!this.checkDigits(this.VATNumber.getText()) && !this.checkDigits(this.PhoneNumber.getText())) {
			 Alert alert = new Alert(AlertType.ERROR, "You can only insert a digits character in VAT number or in phone number"); 
			 alert.show();
		 }
		  
		 String sql="Insert into `negozio elettronica`.suppliers (`VAT_Number`, `CompanyName`, `Phone`,`Email`, `Address`)"
				 + " values ('" + this.VATNumber.getText()+ "', '" + this.AgencyName.getText() +"', '" + this.PhoneNumber.getText() + "','"
				 + this.EmailAdress.getText() + "', '" + this.AgencyAdrress.getText() + "')" ;
				  
		  Statement statement = connection.createStatement();
		  statement.executeUpdate(sql); 
		  
		  HolderMain.changeWindow("AddProduct.fxml");
		  
    }
    
    private boolean checkDigits(String string) {
    	if(string.isBlank()) {
    		return false;
    	}
    	for(int i=0; i<string.length(); i++) {
    		if(!Character.isDigit(string.charAt(i))) {
    			return false;
    		}
    	}
    	return true;
    }

}

