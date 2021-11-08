package application.Holder;
import java.sql.Connection;

import java.sql.SQLException;
import java.sql.Statement;

import db.connection.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class UpdateUnitController {

    @FXML
    private TextField textfield;

    @FXML
    void OnClickAdd(ActionEvent event) {
    	
    	if(this.textfield.getText().isBlank() || !this.isInt(this.textfield.getText())) {
    		Alert alert = new Alert(AlertType.ERROR, "Ypu must to insert an integer");
    		alert.show();
    		return;
    	} else {
    	
    	Connection connection; 
		  try { 
			  connection = new DBConnection().getMySQLConnection().get(); 
			  String sql="Update `negozio elettronica`.models "
			  		+ "SET `UnitInStock` = '"+(Integer.parseInt(this.textfield.getText()) + 
			  		QuantityWindowController.oldQuantity)+"' WHERE (`ModelID` = '"
			  				+ QuantityWindowController.modelIdToUpdate+"')";
						 
				Statement statement = connection.createStatement();
		    	statement.executeUpdate(sql);
		    	
			  
		  } catch (ClassNotFoundException |SQLException e) {
			  e.printStackTrace();
		  }
    	}
    }
    
    private boolean isInt(String string) {
    	try {
    		Integer.parseInt(string);
    		return true;
    	} catch(NumberFormatException e) {
    		return false;
    	}
    }

}
