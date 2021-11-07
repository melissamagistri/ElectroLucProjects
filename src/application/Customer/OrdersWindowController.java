package application.Customer;

import java.io.IOException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.connection.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Employee;
import model.OrderTable;

public class OrdersWindowController {

    @FXML
    private Button GoBackButton;

    @FXML
    private Button OrderStateButton;

    @FXML
    private ListView<OrderTable> OrdersListView;
    @FXML
    void OnClickGoBack(ActionEvent event) throws IOException {
    	CustomerMain.changeWindow("ClientWindow.fxml");
    }

    @FXML
    void OnClickOrderState(ActionEvent event) throws SQLException {
    	Connection connection;
		String sql = "SELECT FirstName, LastName, Fiscalcode, c.EmployeeID, Salary "+ 
				"FROM `negozio elettronica`.contract c " +
				"join `negozio elettronica`.employees e on (c.EmployeeID = e.EmployeeID) " +
				"where EndDate > CURDATE() " + 
				"or EndDate is NULL";
		ObservableList<Employee> list = FXCollections.observableArrayList();
		
		try {
			connection = new DBConnection().getMySQLConnection().get();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				list.add(new Employee(resultSet.getString("FirstName"), resultSet.getString("LastName"),
						resultSet.getString("Fiscalcode"),Integer.parseInt(resultSet.getString("EmployeeID")), 
								Double.parseDouble(resultSet.getString("Salary"))));
			}
			this.IDColumn.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("employeeID"));
			this.fiscalCodeColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("fiscalCode"));
			this.nameColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("firstName"));
			this.surnameColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("secondName"));
			this.salaryColumn.setCellValueFactory(new PropertyValueFactory<Employee, Double>("salary"));
			this.tableView.setItems(list);
		} catch (ClassNotFoundException | SQLException e) {
			Alert alert = new Alert(AlertType.ERROR, "there was a problem with the db connection");
			alert.show();
			return;
		}
    

}
