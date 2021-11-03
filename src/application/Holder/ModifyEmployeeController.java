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
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class ModifyEmployeeController {

	@FXML
    private Button ChangeSalaryButton;

    @FXML
    private Button DismissButton;

    @FXML
    private Button GoBackButton;

    @FXML
    private TextField SearchBar;

    @FXML
    private Button SelectButton;

    @FXML
    private TextField txContract;

    @FXML
    private TextField txFiscalCode;

    @FXML
    private TextField txIDCode;

    @FXML
    private TextField txName;

    @FXML
    private TextField txSurname;
    
    @FXML
    private TextField txSalary;

    @FXML
    void OnClickChangeSalary(ActionEvent event) throws IOException {
    	HolderMain.changeWindow("ChangeSalary.fxml");
    }

    @FXML
    void OnClickDismiss(ActionEvent event) throws SQLException {
    	Connection connection;
    	try {
			connection = new DBConnection().getMySQLConnection().get();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("there was a problem with the db connection");
			return;
		}
    	
    	String sql= "Delete from `negozio elettronica`.employees_account "
    				+ "where EmployeeID = '" + this.txIDCode.getText() + "'";
    	
    	Statement statement = connection.createStatement();
		statement.execute(sql);
		
		String deletesql= "Delete from `negozio elettronica`.employees"
				+ "where EmployeeID = '" + this.txIDCode.getText() + "'";
	
		Statement deletestatement = connection.createStatement();
		deletestatement.execute(deletesql);
    }

    @FXML
    void OnClickGoBack(ActionEvent event) throws IOException {
    	HolderMain.changeWindow("Holder.fxml");
    }

    @FXML
<<<<<<< HEAD
    void OnClickSelect(ActionEvent event) {
    	
=======
    void OnClickSelect(ActionEvent event) throws SQLException {
    	Connection connection;
    	try {
			connection = new DBConnection().getMySQLConnection().get();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("there was a problem with the db connection");
			return;
		}
    	if(this.SearchBar.getText().equals("")) {
    		Alert alert = new Alert(AlertType.ERROR, "You need to insert an id");
    		alert.show();
    	} else {
    		String sql = "select * from `negozio elettronica`.employees where EmployeeID = '"
    				+this.SearchBar.getText()+ "'";
    		Statement statement = connection.createStatement();
    		ResultSet resultSet = statement.executeQuery(sql);
    		
    		if(!resultSet.isBeforeFirst()) {
    			Alert alert = new Alert(AlertType.ERROR, "The id that you insert doesn't match with any of the employee");
        		alert.show();
    		} else {
    			resultSet.next();
        		this.txName.setText(resultSet.getString("FirstName"));
        		this.txSurname.setText(resultSet.getString("LastName"));
        		this.txFiscalCode.setText(resultSet.getString("Fiscalcode"));
        		this.txIDCode.setText(resultSet.getString("EmployeeID"));
        		this.txSalary.setText(resultSet.getString("Salary"));
        		sql = "select * from `negozio elettronica`.contract where EmployeeID = '"
        				+this.SearchBar.getText()+ "'";
        		statement = connection.createStatement();
        		resultSet = statement.executeQuery(sql);
        		resultSet.next();
        		this.txContract.setText(resultSet.getString("ContractType"));
    		}
    	}
>>>>>>> 71e05401fb31f1edf1620da897207abdf97b2230
    }
    
    @FXML
    void OnClickViewEmployees(ActionEvent event) throws IOException {
    	HolderMain.changeWindow("ViewEmployees.fxml");
    }

}


