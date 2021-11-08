package application.Holder;
import java.io.IOException;
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
    void OnClickAdd(ActionEvent event) throws IOException {
    	
    	if(this.textfield.getText().isBlank() || !this.isInt(this.textfield.getText())) {
    		Alert alert = new Alert(AlertType.ERROR, "You must to insert an integer");
    		alert.show();
    		return;
    	} else if(Integer.parseInt(this.textfield.getText())<1){
    		Alert alert = new Alert(AlertType.ERROR, "You must to insert an integer grater than 0");
    		alert.show();
    		return;
    	}else {
    	
    	
    	Connection connection; 
		  try { 
			  connection = new DBConnection().getMySQLConnection().get(); 
			  String sql="Update `negozio elettronica`.models "
			  		+ "SET `UnitInStock` = '"+(Integer.parseInt(this.textfield.getText()) + 
			  		QuantityWindowController.oldQuantity)+"' WHERE (`ModelID` = '"
			  				+ QuantityWindowController.modelIdToUpdate+"')";
						 
				Statement statement = connection.createStatement();
		    	statement.executeUpdate(sql);
		    	HolderMain.changeWindow("QuantityWindow.fxml");
			  
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
