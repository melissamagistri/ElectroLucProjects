package application.Holder;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.connection.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.ChoiceBox;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class AddEmployeeController{

	@FXML
    private Button AddButton;
	
	@FXML
	private CheckBox checkBox;

    @FXML
    private ChoiceBox<String> ChoiceBox;

    private ObservableList<String> contractList = FXCollections.observableArrayList("FullTime Determinated", 
    		"PartTime Determinated","FullTime Indeterminated", "PartTime Indeterminated");

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
    private TextField txEndDate;

    @FXML
    private TextField txHireDate;

    @FXML
    void OnClickAddEmployee(ActionEvent event) throws SQLException {
	
		  Connection connection; 
		  try { 
			  connection = new DBConnection().getMySQLConnection().get(); 
		  } catch (ClassNotFoundException |SQLException e) {
		 System.out.println("there was a problem with the db connection"); 
		 return; 
		 }
		 
		 if(this.CodeTextField.getText().equals("") ||
		 this.FiscalCodeTextField.getText().equals("") ||
		 this.NameTextField.getText().equals("") ||
		 this.PasswordTextField.getText().equals("") ||
		 this.SurnameTextField.getText().equals("") ||
		 this.txHireDate.getText().equals("")) { 
			 
			 Alert alert = new Alert(AlertType.ERROR, "You must write the datas"); 
			 alert.show(); 
			 return; 
		}
		  
		if(!this.checkDigits(this.CodeTextField.getText())) { 
			 Alert alert = new Alert(AlertType.ERROR, "You must write only digits characters in code");
			 alert.show(); 
			 return;
		}
		
		if(this.checkCode()) {
			Alert alert = new Alert(AlertType.ERROR, "You can't write a code that already exist");
			 alert.show(); 
			 return;
		}
		
		if(!this.checkOrderDate(this.txEndDate.getText()) || !this.checkOrderDate(this.txHireDate.getText())) {
			Alert alert = new Alert(AlertType.ERROR, "You didn't write the date in the correct format yyyy-mm-dd");
			 alert.show(); 
			 return;
		}
		  
		 if((this.ChoiceBox.getValue().toString() == "PartTime Determinated" ||
		  this.ChoiceBox.getValue().toString() == "FullTime Determinated") &&
		  this.txEndDate.getText().equals("")) { 
			 Alert alert = new Alert(AlertType.ERROR, "You must write an end date"); 
			 alert.show(); 
			 return; 
		 }
		  
		  if((this.ChoiceBox.getValue() == "PartTime Indeterminated" ||
		  this.ChoiceBox.getValue() == "FullTime Indeterminated") &&
		  !this.txEndDate.getText().equals("")) { 
			  Alert alert = new Alert(AlertType.ERROR, "You can't write an end date"); 
			  alert.show(); 
			  return;
		  }
		  
		  if(this.ChoiceBox.getValue() == "PartTime Indeterminated" || 
				  this.ChoiceBox.getValue() == "FullTime Indeterminated") {
		  
			  String sql="Insert into `negozio elettronica`.employees (`FirstName`, `LastName`, `EmployeeID`,`Fiscalcode`,  `Salary`)"
					  + " values ('" + this.NameTextField.getText()+"', '" +
					  this.SurnameTextField.getText()+"', '" + this.CodeTextField.getText()+ "','"
					  + this.FiscalCodeTextField.getText() + "', '" +
					  Float.parseFloat(this.getMinWage()) + "')" ;
		  
			  Statement statement = connection.createStatement();
			  statement.executeUpdate(sql);
		  
			  sql= "Insert into `negozio elettronica`.contracts (`EmployeeID`,`HireDate`, `ContractType`)"
					  + " values ('" + this.CodeTextField.getText()+ "','" +
					  this.txHireDate.getText() + "','" + this.ChoiceBox.getValue() + "')" ;
		  
			  statement = connection.createStatement(); statement.executeUpdate(sql);
		  
			  sql= "Insert into `negozio elettronica`.employees_account (`EmployeeID`,`Password`)"
					  + " values ('" + this.CodeTextField.getText()+ "','" +
					  this.PasswordTextField.getText()+ "')" ;
		 
			  statement = connection.createStatement(); statement.executeUpdate(sql);
		  
			  Alert alert = new Alert(AlertType.INFORMATION,"You have successfully insert a new employee"); alert.show();
		  
		  } else { 
			  if(!this.checkDate(this.txEndDate.getText(),this.txHireDate.getText())) {
				  Alert alert = new Alert(AlertType.ERROR,"You can't write a end date that is before to the hire date");
				  alert.show(); 
				  return; } 
			  else { 
				  String sql="Insert into `negozio elettronica`.employees (`FirstName`, `LastName`, `EmployeeID`,`Fiscalcode`,  `Salary`)"
						  + " values ('" + this.NameTextField.getText()+"', '" + this.SurnameTextField.getText()+"', '" + this.CodeTextField.getText()+ "','" 
						  + this.FiscalCodeTextField.getText() + "', '" +
						  Float.parseFloat(this.getMinWage()) + "')" ; 
		  
				  Statement statement = connection.createStatement(); 
				  statement = connection.createStatement(); 
				  statement.executeUpdate(sql);
		 
				  sql= "Insert into `negozio elettronica`.contracts (`EmployeeID`,`HireDate`, `EndDate`, `ContractType`)"
						  + " values ('" + this.CodeTextField.getText()+ "','" +
						  this.txHireDate.getText() + "','" +
						  this.txEndDate.getText() + "','" + this.ChoiceBox.getValue() + "')" ;
		  
				  statement = connection.createStatement(); statement.executeUpdate(sql);
		  
				  sql= "Insert into `negozio elettronica`.employees_account (`EmployeeID`,`Password`)"
						  + " values ('" + this.CodeTextField.getText()+ "','" +
						  this.PasswordTextField.getText()+ "')" ;
		  
				  statement = connection.createStatement(); statement.executeUpdate(sql);
		  	
				  Alert alert = new Alert(AlertType.INFORMATION,
						  "You have successfully insert a new employee"); 
				  alert.show(); 
			  } 
		}
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
    
    private boolean checkDate(String enddate, String hiredate) {
		String[] parts1 = enddate.split("-");
		String[] parts2 = hiredate.split("-");
		
		if(Integer.parseInt(parts1[0]) < Integer.parseInt(parts2[0])) {
			return false;
		} else if(Integer.parseInt(parts1[1]) < Integer.parseInt(parts2[1])) {
			return false;
		} else if(Integer.parseInt(parts1[2]) < Integer.parseInt(parts2[2])) {
			return false;
		} else {
			return true;
		}
	
    }
    
    private boolean checkOrderDate(String date) {
    	if(date.length() != 10) {
    		return false;
    	} else {
    		String[] parts = date.split("-");
    		if(parts[0].length() != 4 || parts[1].length() != 2 || parts[2].length() != 2) {
    			return false;
    		}else {
    			return true;
    		}
    		
    	}
    }
    
    private String getMinWage(){
    	Connection connection;
    	try {
			connection = new DBConnection().getMySQLConnection().get();
			String salary = "select MinWage from `negozio elettronica`.contract_types"
	    			+ " Where TypeName = '" + this.ChoiceBox.getValue().toString() + "'";
			
			Statement statement = connection.createStatement();
		
			ResultSet resultSet = statement.executeQuery(salary);
			resultSet.next();
			
			return resultSet.getString("MinWage");
			
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("there was a problem with the db connection man");
		}
		return "error";
    }
    
    private boolean  checkCode() {
    	Connection connection;
    	try {
			connection = new DBConnection().getMySQLConnection().get();
			String salary = "select EmployeeID from `negozio elettronica`.employees"
	    			+ " Where EmployeeID = '" + this.CodeTextField.getText() + "'";
			
			Statement statement = connection.createStatement();
		
			ResultSet resultSet = statement.executeQuery(salary);
			
			if(resultSet.next()) {
				return true; 
			}
			
			return false;
			
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("there was a problem with the db connection min");
		}
		return true;
    }
}

