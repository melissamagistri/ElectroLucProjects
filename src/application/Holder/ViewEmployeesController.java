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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Employee;

public class ViewEmployeesController implements Initializable {

    @FXML
    private TableColumn<Employee, Integer> IDColumn;

    @FXML
    private TableColumn<String, String> contractColumn;

    @FXML
    private TableColumn<Employee, String> fiscalCodeColumn;

    @FXML
    private TableColumn<Employee, String> nameColumn;

    @FXML
    private TableColumn<Employee, Double> salaryColumn;

    @FXML
    private TableColumn<Employee, String> surnameColumn;

    @FXML
    private TableView<Employee> tableView;
    
    @FXML
    void OnCLickGoBack(ActionEvent event) throws IOException {
    	HolderMain.changeWindow("ModifyEmployee.fxml");
    }

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		Connection connection;
		String sql = "SELECT FirstName, LastName, Fiscalcode, c.EmployeeID, Salary "+ 
				"FROM `negozio elettronica`.contract c " +
				"join `negozio elettronica`.employees e on (c.EmployeeID = e.EmployeeID) " +
				"where EndDate > CURDATE()";
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
    
    

}
