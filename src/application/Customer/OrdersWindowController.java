package application.Customer;

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
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import model.OrderTable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class OrdersWindowController implements Initializable{

    @FXML
    private Button GoBackButton;

    @FXML
    private TableColumn<OrderTable, String> dateColumn;

    @FXML
    private TableColumn<OrderTable, String> modelNameColumn;

    @FXML
    private TableColumn<OrderTable, Integer> orderIDcolumn;

    @FXML
    private TableColumn<OrderTable, Double> priceColumn;

    @FXML
    private TableView<OrderTable> tableView;
    
    @FXML
    void OnClickGoBack(ActionEvent event) throws IOException {
    	CustomerMain.changeWindow("ClientWindow.fxml");
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1){
		Connection connection;
		String sql = "SELECT o.OrderID, o.OrderDate, m.ModelName, o.TotalAmount "+ 
				"FROM `negozio elettronica`.orders o " +
				"join `negozio elettronica`.models m on (m.ModelID = o.ModelID) "+
				"where o.Email = '"+CustomerMain.CustomerEmail+"'"  ;
		
		ObservableList<OrderTable> list = FXCollections.observableArrayList();
		
		try {
			connection = new DBConnection().getMySQLConnection().get();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				list.add(new OrderTable(Integer.parseInt(resultSet.getString("OrderID")), 
						resultSet.getString("ModelName"),
						resultSet.getString("OrderDate"), Double.parseDouble(resultSet.getString("TotalAmount"))));
			}
			this.dateColumn.setCellValueFactory(new PropertyValueFactory<OrderTable, String>("orderDate"));
			this.modelNameColumn.setCellValueFactory(new PropertyValueFactory<OrderTable, String>("modelName"));
			this.orderIDcolumn.setCellValueFactory(new PropertyValueFactory<OrderTable, Integer>("orderId"));
			this.priceColumn.setCellValueFactory(new PropertyValueFactory<OrderTable, Double>("modelPrice"));
			this.tableView.setItems(list);
		} catch (ClassNotFoundException | SQLException e) {
			Alert alert = new Alert(AlertType.ERROR, "there was a problem with the db connection");
			alert.show();
			return;
		}
		
	}
}
