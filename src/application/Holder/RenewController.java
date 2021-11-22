package application.Holder;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import db.connection.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class RenewController implements Initializable{

    @FXML
    private TextField endtx;

    @FXML
    private TextField hiretx;
    
    @FXML
    private ChoiceBox<String> choicebox;
    
    private ObservableList<String> contractList;

    @FXML
    void OnCLickRenew(ActionEvent event) throws SQLException {
    	
    	Connection connection;
    	
    	try { 
			  connection = new DBConnection().getMySQLConnection().get(); 
		  } catch (ClassNotFoundException |SQLException e) {
		 System.out.println("there was a problem with the db connection"); 
		 return; 
		 }
    	
    	if(this.hiretx.getText().isBlank()) {
    		Alert alert = new Alert(AlertType.ERROR, "Need to insert a hire date");
    		alert.show();
    		return;
    	}
    	
    	if((this.choicebox.getValue().equals("PartTime Determinated") || 
    			this.choicebox.getValue().equals("FullTime Determinated")) && this.endtx.getText().isBlank()) {
    		Alert alert = new Alert(AlertType.ERROR, "Need to insert a end date");
    		alert.show();
    		return;
    	}
    	
    	if((this.choicebox.getValue().equals("PartTime Indeterminated") || 
    			this.choicebox.getValue().equals("FullTime Indeterminated")) && !this.endtx.getText().isBlank()){
    		Alert alert = new Alert(AlertType.ERROR, "You can't insert an end date");
    		alert.show();
    		return;	
    	}
    	
    	if(!this.checkOrderDate(this.hiretx.getText())) {
    		Alert alert = new Alert(AlertType.ERROR,"You didn't write the date in the correct format yyyy-mm-dd");
    		alert.show();
    		return;	
    	}
    	
    	
    	if(this.choicebox.getValue().equals("PartTime Indeterminated") || 
    			this.choicebox.getValue().equals("FullTime Indeterminated")) {
    		
    		this.removeContractsID();
    		 String sql= "Insert into `negozio elettronica`.contracts (`EmployeeID`,`HireDate`,`ContractType`) "
					  + " values ('" + ModifyFinishedEmployeeController.data.getEmployeeID() + "','" +
					  this.hiretx.getText() + "','" + this.choicebox.getValue()
					  + "')" ;
		  
			  Statement statement = connection.createStatement(); 
			  statement.executeUpdate(sql);
			  
			  sql = "Update `negozio elettronica`.employees "
	    		  	+ "SET `Salary` = '" + this.getMinWage() 
	    		  	+ "' WHERE (`EmployeeID` = '" + ModifyFinishedEmployeeController.data.getEmployeeID() +"')";
			  
			  statement.executeUpdate(sql);
			  
			  Alert alert = new Alert(AlertType.INFORMATION, "The operation was done!");
			  alert.show();
			  return;
    	} else {
    		if(this.choicebox.getValue().equals("PartTime Determinated") || 
        			this.choicebox.getValue().equals("FullTime Determinated")) {
        		
        		if(this.checkOrderDate(this.endtx.getText())) {
        			
        			if(this.checkDate(this.endtx.getText(), this.hiretx.getText())) {
        				
        				this.removeContractsID();
        	    		 String sql= "Insert into `negozio elettronica`.contracts (`EmployeeID`,`HireDate`, `EndDate`,`ContractType`) "
        						  + " values ('" + ModifyFinishedEmployeeController.data.getEmployeeID() + "','" +
        						  this.hiretx.getText() + "','" + this.endtx.getText()+ "','" + this.choicebox.getValue()
        						  + "')" ;
        			  
        				  Statement statement = connection.createStatement(); 
        				  statement.executeUpdate(sql);
        				  
        				  sql = "Update `negozio elettronica`.employees "
        		    		  	+ "SET `Salary` = '" + this.getMinWage() 
        		    		  	+ "' WHERE (`EmployeeID` = '" + ModifyFinishedEmployeeController.data.getEmployeeID() +"')";
        				  
        				  statement.executeUpdate(sql);
        				  
        				  Alert alert = new Alert(AlertType.INFORMATION, "The operation was done!");
        				  alert.show();
        				  return;
        			}else {
        				Alert alert = new Alert(AlertType.ERROR, "You need to insert an end date greater than the hire date");
      				  	alert.show();
      				  	return;
        			}
        			
        		}else {
        			Alert alert = new Alert(AlertType.ERROR, "You didn't write the date in the correct format yyyy-mm-dd");
    				 alert.show();
    				 return;
        		}
        		
        	}
    	}
    }

    @FXML
    void OnClickGoBack(ActionEvent event) throws IOException {
    	HolderMain.changeWindow("ModifyFinishedEmployee.fxml");
    }
    
    private ObservableList<String> getContractList(){
		Connection connection; 
		this.contractList = FXCollections.observableArrayList();
		  try { 
			  connection = new DBConnection().getMySQLConnection().get(); 
			  String sql="SELECT TypeName from `negozio elettronica`.contract_types ";
						 
				Statement statement = connection.createStatement();
		    	ResultSet resultSet = statement.executeQuery(sql);
		    	while(resultSet.next()) {
		    		this.contractList.add(resultSet.getString("TypeName"));
		    	}
			  
		  } catch (ClassNotFoundException |SQLException e) {
			  System.out.println("there was a problem with the db connection");
		  }
		return this.contractList;
		
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.choicebox.setItems(this.getContractList());
		
	}
	
	private void removeContractsID() throws SQLException {
		Connection connection;
    	try {
			connection = new DBConnection().getMySQLConnection().get();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("there was a problem with the db connection");
			return;
		}
		String sql= "Delete from `negozio elettronica`.contracts "
				+ "where EmployeeID = '" + ModifyFinishedEmployeeController.data.getEmployeeID() + "'";

		Statement statement = connection.createStatement();
		statement.execute(sql);
	}
	
	private String getMinWage(){
    	Connection connection;
    	try {
			connection = new DBConnection().getMySQLConnection().get();
			String salary = "select MinWage from `negozio elettronica`.contract_types "
	    			+ " Where TypeName = '" + this.choicebox.getValue() + "'";
			
			Statement statement = connection.createStatement();
		
			ResultSet resultSet = statement.executeQuery(salary);
			resultSet.next();
			
			return resultSet.getString("MinWage");
			
		} catch (ClassNotFoundException | SQLException e) {
			return "error";
		}
    }

}

