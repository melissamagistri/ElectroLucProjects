package application.Holder;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import db.connection.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.EmployeeDate;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class ModifyFinishedEmployeeController  implements Initializable {
	
	    @FXML
	    private TableColumn<EmployeeDate, Integer> IDColumn;

	    @FXML
	    private TableColumn<EmployeeDate, String> contractColumn;

	    @FXML
	    private TableColumn<EmployeeDate, String> endDateColumn;

	    @FXML
	    private TableColumn<EmployeeDate, String> hireDateColumn;

	    @FXML
	    private TableColumn<EmployeeDate, String> nameColumn;

	    @FXML
	    private TableColumn<EmployeeDate, Double> salaryColumn;

	    @FXML
	    private TableColumn<EmployeeDate, String> surmaneColumn;

	    @FXML
	    private TableView<EmployeeDate> tableview;
	    
	    protected static EmployeeDate data;

	    @FXML
	    void OnCLickGoBack(ActionEvent event) throws IOException {
	    	HolderMain.changeWindow("Holder.fxml");
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
	    				+ "where EmployeeID = '" + this.tableview.getSelectionModel().getSelectedItem().getEmployeeID()+ "'";
	    	
	    	Statement statement = connection.createStatement();
			statement.execute(sql);
			
			for (String i: this.getOrderID()) {
				sql= "Update `negozio elettronica`.orders "
				  		+ "SET `EmployeeID` = NULL"
				  		+ " WHERE (`OrderID` = '" + i +"')";
			
				statement.execute(sql);
			}
			
			sql= "Delete from `negozio elettronica`.contracts "
					+ "where EmployeeID = '" + this.tableview.getSelectionModel().getSelectedItem().getEmployeeID() + "'";
		
			statement.execute(sql);
			
			
			sql= "Delete from `negozio elettronica`.employees "
					+ "where EmployeeID = '" + this.tableview.getSelectionModel().getSelectedItem().getEmployeeID() + "'";
		
			statement.execute(sql);
			
			
			
			
			
			Alert alert = new Alert(AlertType.INFORMATION, "The Employee was dismiss");
			alert.show();
	    }

	    @FXML
	    void OnClickRenew(ActionEvent event) throws IOException {
	    	if(this.tableview.getSelectionModel().getSelectedItems().size() == 1) {
	    		ModifyFinishedEmployeeController.data = this.tableview.getSelectionModel().getSelectedItem();
	    		HolderMain.changeWindow("Renew.fxml");
	    	}else {
	    		Alert alert = new Alert(AlertType.ERROR, "You can selected only one employee");
				alert.show();
				return;
	    	}
	    	
	    }

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			Connection connection;
			String sql = "SELECT FirstName, LastName, c.EmployeeID, ContractType, Salary, HireDate, EndDate "+ 
					"FROM `negozio elettronica`.contracts c " +
					"join `negozio elettronica`.employees e on (c.EmployeeID = e.EmployeeID) " +
					"where EndDate < CURDATE()";
			
			ObservableList<EmployeeDate> list = FXCollections.observableArrayList();
			
			try {
				connection = new DBConnection().getMySQLConnection().get();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(sql);
				while(resultSet.next()) {
					list.add(new EmployeeDate(resultSet.getString("FirstName"), resultSet.getString("LastName"),
							"",Integer.parseInt(resultSet.getString("EmployeeID")), 
									Double.parseDouble(resultSet.getString("Salary")), resultSet.getString("HireDate"), 
									resultSet.getString("EndDate"), resultSet.getString("ContractType")));
				}
				this.IDColumn.setCellValueFactory(new PropertyValueFactory<EmployeeDate, Integer>("employeeID"));
				this.nameColumn.setCellValueFactory(new PropertyValueFactory<EmployeeDate, String>("firstName"));
				this.surmaneColumn.setCellValueFactory(new PropertyValueFactory<EmployeeDate, String>("lastName"));
				this.salaryColumn.setCellValueFactory(new PropertyValueFactory<EmployeeDate, Double>("salary"));
				this.hireDateColumn.setCellValueFactory(new PropertyValueFactory<EmployeeDate, String>("hireDate"));
				this.endDateColumn.setCellValueFactory(new PropertyValueFactory<EmployeeDate, String>("endDate"));
				this.contractColumn.setCellValueFactory(new PropertyValueFactory<EmployeeDate, String>("contractType"));
				this.tableview.setItems(list);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
				Alert alert = new Alert(AlertType.ERROR, "there was a problem with the db connection");
				alert.show();
				return;
			}
			
		}
		
		private List<String> getOrderID() {
	    	Connection connection;
	    	List<String> string = new ArrayList<>();
	    	try {
				connection = new DBConnection().getMySQLConnection().get();
				String sql = "select OrderID from `negozio elettronica`.orders"
		    			+ " Where EmployeeID = '" + this.tableview.getSelectionModel().getSelectedItem().getEmployeeID() + "'";

				Statement statement = connection.createStatement();			
				ResultSet resultSet = statement.executeQuery(sql);
				resultSet.next();
				System.out.println(resultSet.next());
				
				while(resultSet.next()) {
					string.add(resultSet.getString("OrderID"));
				}
				
				return string;
				
			} catch (ClassNotFoundException | SQLException e) {
				System.out.println("there was a problem with the db connection man");
			}
			return List.of();
	    }

}
