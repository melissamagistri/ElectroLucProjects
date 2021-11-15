package application.Employee;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import application.Holder.ModifyUnfinishedEmployeeController;
import db.connection.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

public class ChangePasswordController implements Initializable{

    @FXML
    private TextField txNewPassword;

    @FXML
    private TextField txOldPassword; 
    
    @FXML
    void OnClickChange(ActionEvent event) {
    	Alert alert;
    	Connection connection;
    	
    	if(this.txOldPassword.getText().equals(this.txNewPassword.getText())) {
    		alert = new Alert(AlertType.ERROR, "You must write a new password that is fdifferent from the old password");
    		alert.show();
    	}
    	try {
    		connection = new DBConnection().getMySQLConnection().get();
        	
        	Statement statement = connection.createStatement();

    		String sql = "Update `negozio elettronica`.employees_account "
    		  		+ "SET `Password` = '" + this.txNewPassword.getText()
    		  		+ "' WHERE (`EmployeeID` = '" + LoginController.employeeId +"')";
    				  
    		connection.createStatement();
    		statement.executeUpdate(sql);
    		
    		alert = new Alert(AlertType.INFORMATION, "The operation was done!");
    		alert.show();
    		
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
    void OnClickGoBack(ActionEvent event) throws IOException {
    	EmployeeMain.changeWindow("EmployeeWindow.fxml");
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Alert alert;
    	Connection connection;
		try {
    		connection = new DBConnection().getMySQLConnection().get();
    	
        	String sql = "SELECT Password FROM `negozio elettronica`.employees_account "
        			+ "WHERE EmployeeID = '" + LoginController.employeeId + "'";
        	
        	Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
        	
			if(resultSet.next()) {
	        	this.txOldPassword.setText(resultSet.getString("Password"));
			}
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

}

