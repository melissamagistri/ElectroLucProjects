package application.Holder;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import db.connection.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.ChoiceBox;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class AddEmployeeController{

	@FXML
    private Button AddButton;
	
	 @FXML
	 private CheckBox checkBox;

    @FXML
    private ChoiceBox<String> ChoiceBox;

    private ObservableList<String> contractList = FXCollections.observableArrayList("FullTime", "PartTime");

    @FXML
    private TextField CodeTextField;

    @FXML
    private TextField FiscalCodeTextField;

    @FXML
    private Button GoBack;

    @FXML
    private TextField NameTextField;

    @FXML
    private TextField PasswordTextField;

    @FXML
    private TextField SurnameTextField;

    @FXML
    void OnClickAddEmployee(ActionEvent event) throws SQLException {
    	Connection connection;
    	try {
			connection = new DBConnection().getMySQLConnection().get();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("there was a problem with the db connection");
			return;
		}
    	
    	String sql= "Insert into `negozio elettronica`.employees (`FirstName`, `LastName`, `EmployeeID`,`Fiscalcode`,  `Salary`)" + 
    	" values ('" + this.NameTextField.getText()+"', '" + this.SurnameTextField.getText()+"', '" + this.CodeTextField.getText()+ "','"
    			+ "'" + this.FiscalCodeTextField.getText() + "', '" + this.ChoiceBox.getValue() + "')'" ;
    	
    	Statement statement = connection.createStatement();
    	statement = connection.createStatement();
		statement.executeUpdate(sql);
		
		sql= "Insert into `negozio elettronica`.employees_account (`EmployeeID`,`Password`)" + 
		    	" values ('" + this.CodeTextField.getText()+ "','" + this.PasswordTextField.getText()+ "')'" ;
		
		statement = connection.createStatement();
		statement.executeUpdate(sql);
		
		sql= "Insert into `negozio elettronica`.employees_account (`EmployeeID`,`Password`)" + 
		    	" values ('" + this.CodeTextField.getText()+ "','" + this.PasswordTextField.getText()+ "')'" ;
		
		statement = connection.createStatement();
		statement.executeUpdate(sql);
		
    }

    @FXML
    void OnClickGoBack(ActionEvent event) throws IOException {
    	HolderMain.changeWindow("Holder.fxml");
    }
    
    @FXML
    void OnClickRandomPassword(ActionEvent event) {
    	if(this.checkBox.isSelected()) {
    		PasswordGenerator password = new PasswordGenerator();
    		this.PasswordTextField.setText(password.getPassword());
    	}
    }

    @FXML 
    private void initialize() {
    	this.ChoiceBox.setItems(contractList);
    	
    }
    

}

