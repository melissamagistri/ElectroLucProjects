package application.Customer;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.connection.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class LoginController {

    @FXML
    private TextField tx_Password;

    @FXML
    private TextField tx_email;

    @FXML
    void OnClickLogin(ActionEvent event) throws IOException, SQLException {
    	Alert alert;
    	Connection connection;

    	//controllo sulla connesione con il database
    	try {
    		connection = new DBConnection().getMySQLConnection().get();
    	} catch (ClassNotFoundException e) {
			alert = new Alert(AlertType.ERROR, "Error: Driver not found");
    		alert.show();
    		return;
		} catch (SQLException e) {
			alert = new Alert(AlertType.ERROR, "Error: unable to connect with the database");
    		alert.show();
    		return;
		}


    	if(this.tx_email.getText().equals("")) {
    		alert = new Alert(AlertType.ERROR,"You need to insert an email");
    		alert.show();
    	} else if(this.tx_Password.getText().equals("")){
    		alert = new Alert(AlertType.ERROR, "You need to insert a password");
    		alert.show();
    	} else {
    		String sql = "select * from `negozio elettronica`.customers_accounts where Email = '"
    		+ this.tx_email.getText() + "'" + " and Password ='" + this.tx_Password.getText() + "'";
    		Statement statement = connection.createStatement();
    		ResultSet resultSet = statement.executeQuery(sql);
    		
    		if(!resultSet.isBeforeFirst()) {
    			alert = new Alert(AlertType.ERROR, "Your credential are wrong");
        		alert.show();
    		} else {
    			CustomerMain.changeWindow("ClientWindow.fxml");
    		}	
    	}
    }

    @FXML
    void OnClickSignIn(ActionEvent event) throws IOException {
    	CustomerMain.changeWindow("Register.fxml");
    }

}