package application.Holder;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import db.connection.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class ChangeSalaryController implements Initializable{

    @FXML
    private TextField txEmployeeID;

    @FXML
    private TextField txNewSalary;

    @FXML
    private TextField txOldSalary;

    @FXML
    void OnClickChange(ActionEvent event) {
    	Alert alert;
    	Connection connection;
    	
    	if(Double.parseDouble(this.txNewSalary.getText()) < Double.parseDouble(this.getMinWage())) {
    		alert = new Alert(AlertType.ERROR, "You must write a new salary that is greater than min wage");
    		alert.show();
    		return;
    	}
    	try {
    		connection = new DBConnection().getMySQLConnection().get();

    		String sql = "Update `negozio elettronica`.employees "
    		  		+ "SET `Salary` = '" + this.txNewSalary.getText() 
    		  		+ "' WHERE (`EmployeeID` = '" + ModifyUnfinishedEmployeeController.ID +"')";
    				  
    		Statement statement = connection.createStatement();
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
    	HolderMain.changeWindow("Holder.fxml");
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	this.txEmployeeID.setText(ModifyUnfinishedEmployeeController.ID);
    	this.txOldSalary.setText(ModifyUnfinishedEmployeeController.oldSalary);
		
	}
	
	
	private String getMinWage(){
    	Connection connection;
    	try {
			connection = new DBConnection().getMySQLConnection().get();
			String salary = "select MinWage from `negozio elettronica`.contract_types"
	    			+ " Where TypeName = '" + ModifyUnfinishedEmployeeController.contractType + "'";
			
			Statement statement = connection.createStatement();
		
			ResultSet resultSet = statement.executeQuery(salary);
			resultSet.next();
			
			return resultSet.getString("MinWage");
			
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("there was a problem with the db connection man");
		}
		return "error";
    }

}

