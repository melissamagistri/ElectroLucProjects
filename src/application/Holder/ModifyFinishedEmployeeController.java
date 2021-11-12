package application.Holder;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import db.connection.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;

public class ModifyFinishedEmployeeController {
	
	    @FXML
	    private TableColumn<?, ?> IDColumn;

	    @FXML
	    private TableColumn<?, ?> contractColumn;

	    @FXML
	    private TableColumn<?, ?> endDateColumn;

	    @FXML
	    private TableColumn<?, ?> hireDateColumn;

	    @FXML
	    private TableColumn<?, ?> nameColumn;

	    @FXML
	    private TableColumn<?, ?> salaryColumn;

	    @FXML
	    private TableColumn<?, ?> surmaneColumn;

	    @FXML
	    private TableView<?> tableview;

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
	    				+ "where EmployeeID = '" + this.IDColumn.getText() + "'";
	    	
	    	Statement statement = connection.createStatement();
			statement.execute(sql);
			
			sql= "Delete from `negozio elettronica`.contract "
					+ "where EmployeeID = '" + this.IDColumn.getText()  + "'";
		
			statement.execute(sql);
			
			sql= "Delete from `negozio elettronica`.employees "
					+ "where EmployeeID = '" + this.IDColumn.getText()  + "'";
		
			statement.execute(sql);
			
			Alert alert = new Alert(AlertType.INFORMATION, "The Employee was dismiss");
			alert.show();
	    }

	    @FXML
	    void OnClickRenew(ActionEvent event) {
	    	
	    }

}
