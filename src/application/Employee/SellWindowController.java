package application.Employee;

import java.io.IOException;
import java.math.BigDecimal;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Model;

public class SellWindowController {
	
	 	@FXML
	    private TableColumn<Model, Integer> IDcolumn;

	    @FXML
	    private TableColumn<Model, String> descriptionColumn;

	    @FXML
	    private TableColumn<Model, String> nameColumn;

	    @FXML
	    private TableColumn<Model, BigDecimal> priceColumn;

	    @FXML
<<<<<<< HEAD
	    private TableColumn<Model, Integer> quantityColumn;
=======
	    private TableColumn<?, ?> quantityColumn;
	    
	    @FXML
	    private TableColumn<?, ?> discountColumn;
>>>>>>> 0ed131fe200a41719a16d0ef4196c7485217a7e8

	    @FXML
	    private TextField searchTextField;

	    @FXML
	    private TableView<Model> tableview;

    @FXML
    void OnClickGoBack(ActionEvent event) throws IOException {
    	EmployeeMain.changeWindow("EmployeeWindow.fxml");
    }

    @FXML
    void OnClickSell(ActionEvent event) throws IOException {
    	
    	EmployeeMain.changeWindow("ReciptWindow.fxml");
    }
    
    @FXML
    void OnCLickSearch(ActionEvent event) throws IOException {
    	Connection connection;
		
		ObservableList<Model> list = FXCollections.observableArrayList();
		
		try {
			connection = new DBConnection().getMySQLConnection().get();
			String sql= "SELECT ModelName, ModelID, UnitInStock, Description, UnitPrice, Discount "+ 
					"FROM `negozio elettronica`.models " + "where ModelId = '"
							+this.searchTextField.getText()+ "' "
									+ "and InSale = 1";
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			if(this.hasDiscount()) {
				while(resultSet.next()) {
					list.add(new Model(Integer.parseInt(resultSet.getString("ModelID")), resultSet.getString("ModelName"), 
							sql, resultSet.getString("Description"), 
							new BigDecimal(resultSet.getString("UnitPrice")), 
							Optional.of(Integer.parseInt(resultSet.getString("Discount"))),
							Integer.parseInt(resultSet.getString("UnitInStock")), sql, false));
					}
			} else {
				while(resultSet.next()) {
					list.add(new Model(Integer.parseInt(resultSet.getString("ModelID")), resultSet.getString("ModelName"), 
							sql, resultSet.getString("Description"), 
							new BigDecimal(resultSet.getString("UnitPrice")), 
							Optional.of(0),
							Integer.parseInt(resultSet.getString("UnitInStock")), sql, false));
					}	
			}
			this.IDcolumn.setCellValueFactory(new PropertyValueFactory<Model, Integer>("modelID"));
			this.nameColumn.setCellValueFactory(new PropertyValueFactory<Model, String>("modelName"));
			this.quantityColumn.setCellValueFactory(new PropertyValueFactory<Model, Integer>("unitInStock"));
			this.priceColumn.setCellValueFactory(new PropertyValueFactory<Model, BigDecimal>("price"));
			this.descriptionColumn.setCellValueFactory(new PropertyValueFactory<Model, String>("description"));
			this.tableview.setItems(list);
		} catch (ClassNotFoundException | SQLException e) {
			Alert alert = new Alert(AlertType.ERROR, "there was a problem with the db connection");
			alert.show();
			return;
		}
    }
    
    private boolean hasDiscount() {
    	Connection connection;
    	String sql = "SELECT Discount "+ 
				"FROM `negozio elettronica`.models " + "where ModelId = '"
						+this.searchTextField.getText()+ "' "
								+ "and InSale = 1";
    	try {
    		connection = new DBConnection().getMySQLConnection().get();
    		Statement statement = connection.createStatement();
    		ResultSet resultSet = statement.executeQuery(sql);
    		if(resultSet.next()) {
    			Optional<String> var = Optional.ofNullable(resultSet.getString("Discount"));
    			if(var.isEmpty()) {
    				return false;
    			} else {
    				return true;
    			}
    		}else {
    			return false;
    		}
    		
    	} catch (ClassNotFoundException | SQLException e) {
			return false;
		}
    }

}