package application.Employee;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

import db.connection.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import model.AvaibilityTable;
import model.Model;

public class AvaliabilityProductController {

    @FXML
    private Button GoBackButton;

    @FXML
    private TableColumn<AvaibilityTable, String> NameTableColuomn;

    @FXML
    private TableColumn<AvaibilityTable, Integer> NumberTableColoumn;

    @FXML
    private TableColumn<AvaibilityTable, Integer> PriceTableColuomn;
    
    @FXML
    private TableColumn<AvaibilityTable, String> compartamentColumn;

    @FXML
    private TableColumn<AvaibilityTable, String> laneColumn;

    @FXML
    private TableColumn<AvaibilityTable, String> shelfColumn;

    @FXML
    private TableView<AvaibilityTable> ProductTableView;

    @FXML
    private TextField SearchBar;

    @FXML
    private Button SearchProductButton;

    @FXML
    private TableColumn<AvaibilityTable, Boolean> StateTableColumn;

    @FXML
    void OnCLickGoBack(ActionEvent event) throws IOException {
    	EmployeeMain.changeWindow("EmployeeWindow.fxml");
    }

    @FXML
    void OnClickSearchButton(ActionEvent event) {
    	Connection connection;
		String sql = "Select * from `negozio elettronica`.warehouse where ModelId = "+ this.SearchBar.getText();
		
		ObservableList<AvaibilityTable> list = FXCollections.observableArrayList();
		
		try {
			connection = new DBConnection().getMySQLConnection().get();
			String lane;
			String shelf;
			String compartment;
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			
			if(resultSet.next()) {
				lane = resultSet.getString("Lane");
				shelf = resultSet.getString("Shelf");
				compartment = resultSet.getString("Compartment");
			} else {
				lane = "null";
				shelf = "null";
				compartment = "null";
			}
			
			sql = "SELECT ModelId, UnitInStock, ModelName, UnitSellingPrice, InSale, Discount "+ 
					"FROM `negozio elettronica`.models " +
					"where ModelID = " + this.SearchBar.getText();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			
			while(resultSet.next()) {
				double discount = Optional.ofNullable(resultSet.getInt("Discount")).isEmpty() ? 0 : resultSet.getInt("Discount"); 
				double price = resultSet.getDouble("UnitSellingPrice");
				double ris = price - ((price * discount)/100);
				BigDecimal ris2 = new BigDecimal(ris).setScale(2, RoundingMode.HALF_UP);
				
				list.add(new AvaibilityTable(resultSet.getString("ModelName"), ris2, resultSet.getInt("UnitInStock"),
						resultSet.getBoolean("InSale"), lane, shelf, compartment));
			}
			
			this.NumberTableColoumn.setCellValueFactory(new PropertyValueFactory<AvaibilityTable, Integer>("unitInStock"));
			this.NameTableColuomn.setCellValueFactory(new PropertyValueFactory<AvaibilityTable, String>("name"));
			this.PriceTableColuomn.setCellValueFactory(new PropertyValueFactory<AvaibilityTable, Integer>("unitSellingPrice"));
			this.StateTableColumn.setCellValueFactory(new PropertyValueFactory<AvaibilityTable, Boolean>("state"));
			this.shelfColumn.setCellValueFactory(new PropertyValueFactory<AvaibilityTable, String>("shelf"));
			this.compartamentColumn.setCellValueFactory(new PropertyValueFactory<AvaibilityTable, String>("compartment"));
			this.laneColumn.setCellValueFactory(new PropertyValueFactory<AvaibilityTable, String>("lane"));
			this.ProductTableView.setItems(list);
			
		} catch (ClassNotFoundException | SQLException e) {
			Alert alert = new Alert(AlertType.ERROR, "there was a problem with the db connection");
			alert.show();
			return;
		}
		
    }

}

