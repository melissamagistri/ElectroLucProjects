package application.Employee;

import java.io.IOException;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

import application.Holder.HolderMain;
import application.Holder.QuantityWindowController;
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
	    private TableColumn<Model, Integer> quantityColumn;
	    
	    @FXML
	    private TableColumn<Model, Integer> discountColumn;

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
    	if(this.tableview.getSelectionModel().getSelectedItems().size() > 1) {
    		Alert alert = new Alert(AlertType.ERROR, "You can select only one model for time");
			alert.show();
			return;
    	} else {
    		QuantityWindowController.modelIdToUpdate = this.tableview.getSelectionModel()
    				.getSelectedItem().getModelID();
    		QuantityWindowController.oldQuantity = this.tableview.getSelectionModel().
    				getSelectedItem().getUnitInStock();
    		
    		//creare lo scontrino, abbassare le unità dell'oggetto, creare un istanza in orders
    		
    		HolderMain.changeWindow("UpdateUnit.fxml");
    	}
    	EmployeeMain.changeWindow("ReciptWindow.fxml");
    }
    
    @FXML
    void OnCLickSearch(ActionEvent event) throws IOException {
    	Connection connection;
		
		ObservableList<Model> list = FXCollections.observableArrayList();
		
		try {
			connection = new DBConnection().getMySQLConnection().get();
			String sql= "SELECT ModelName, ModelID, UnitInStock, Description, UnitPrice, Discount "+ 
					"FROM `negozio elettronica`.models " + "where ModelName = '"
							+this.searchTextField.getText()+ "' "
									+ "and InSale = 1";
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			
			while(resultSet.next()) {
				list.add(new Model(Integer.parseInt(resultSet.getString("ModelID")), resultSet.getString("ModelName"), 
						sql, resultSet.getString("Description"), 
						new BigDecimal(resultSet.getString("UnitPrice")),
						Optional.ofNullable((resultSet.getInt("Discount"))),
						Integer.parseInt(resultSet.getString("UnitInStock")), sql, false));
			}
			this.IDcolumn.setCellValueFactory(new PropertyValueFactory<Model, Integer>("modelID"));
			this.nameColumn.setCellValueFactory(new PropertyValueFactory<Model, String>("modelName"));
			this.quantityColumn.setCellValueFactory(new PropertyValueFactory<Model, Integer>("unitInStock"));
			this.priceColumn.setCellValueFactory(new PropertyValueFactory<Model, BigDecimal>("unitSellingPrice"));
			this.descriptionColumn.setCellValueFactory(new PropertyValueFactory<Model, String>("description"));
			this.discountColumn.setCellValueFactory(new PropertyValueFactory<Model, Integer>("discount"));
			this.tableview.setItems(list);
			
			
		} catch (ClassNotFoundException | SQLException e) {
			Alert alert = new Alert(AlertType.ERROR, "there was a problem with the db connection");
			alert.show();
			return;
		}
    }
    
}