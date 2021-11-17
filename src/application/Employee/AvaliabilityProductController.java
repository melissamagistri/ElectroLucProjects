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
import model.Employee;
import model.Model;

public class AvaliabilityProductController {

    @FXML
    private Button GoBackButton;

    @FXML
    private TableColumn<Model, String> NameTableColuomn;

    @FXML
    private TableColumn<Model, Integer> NumberTableColoumn;

    @FXML
    private TableColumn<Model, Integer> PriceTableColuomn;

    @FXML
    private TableView<Model> ProductTableView;

    @FXML
    private TextField SearchBar;

    @FXML
    private Button SearchProductButton;

    @FXML
    private TableColumn<Model, Boolean> StateTableColumn;

    @FXML
    void OnCLickGoBack(ActionEvent event) throws IOException {
    	EmployeeMain.changeWindow("EmployeeWindow.fxml");
    }

    @FXML
    void OnClickSearchButton(ActionEvent event) {
    	Connection connection;
		String sql = "SELECT ModelID, ModelName, UnitPrice, InSale, Discount "+ 
				"FROM `negozio elettronica`.models " +
				"where ModelID = " + this.SearchBar.getText();
		
		ObservableList<Model> list = FXCollections.observableArrayList();
		
		try {
			connection = new DBConnection().getMySQLConnection().get();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			
			while(resultSet.next()) {
				double discount = Optional.ofNullable(resultSet.getInt("Discount")).isEmpty() ? 0 : resultSet.getInt("Discount"); 
				double price = resultSet.getInt("UnitPrice");
				double ris = price - ((price * discount)/100);
				BigDecimal ris2 = new BigDecimal(ris).setScale(2, RoundingMode.HALF_UP);
				
				list.add(new Model(resultSet.getInt("ModelID"), resultSet.getString("ModelName"), 
						sql, sql, ris2, Optional.empty(), 0, sql, resultSet.getBoolean("InSale")));
			}
			
			this.NumberTableColoumn.setCellValueFactory(new PropertyValueFactory<Model, Integer>("modelID"));
			this.NameTableColuomn.setCellValueFactory(new PropertyValueFactory<Model, String>("modelName"));
			this.PriceTableColuomn.setCellValueFactory(new PropertyValueFactory<Model, Integer>("unitSellingPrice"));
			this.StateTableColumn.setCellValueFactory(new PropertyValueFactory<Model, Boolean>("inSale"));
			this.ProductTableView.setItems(list);
			
		} catch (ClassNotFoundException | SQLException e) {
			Alert alert = new Alert(AlertType.ERROR, "there was a problem with the db connection");
			alert.show();
			return;
		}
		
    }

}

