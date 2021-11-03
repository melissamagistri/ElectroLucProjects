package application.Customer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import db.connection.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class OrdersWindowController {

    @FXML
    private Button GoBackButton;

    @FXML
    private Button OrderStateButton;

    @FXML
    private ListView<?> OrdersListView;
    @FXML
    void OnClickGoBack(ActionEvent event) throws IOException {
    	CustomerMain.changeWindow("ClientWindow.fxml");
    }

    @FXML
    void OnClickOrderState(ActionEvent event) throws SQLException {
    	
    	Connection connection;
    	try {
			connection = new DBConnection().getMySQLConnection().get();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("there was a problem with the db connection");
			return;
		}
    	
    	String sql = "select * from `negozio elettronica`.orders where Email = '"+CustomerMain.CustomerEmail+"'";
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
    }

}
