package application.Customer;
import java.io.IOException;
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
    	if(this.tx_email.getText().equals("")) {
    		Alert alert = new Alert(AlertType.ERROR,"You need to insert an email");
    		alert.show();
    	} else if(this.tx_Password.getText().equals("")){
    		Alert alert = new Alert(AlertType.ERROR, "You need to insert a password");
    		alert.show();
    	} else {
    		DBConnection connection = new DBConnection();
    		String sql = "select * from `negozio elettronica`.customers_accounts where Email = '"
    		+ this.tx_email.getText() + "'" + " and Password ='" + this.tx_Password.getText() + "'";
    		Statement statement = connection.getMySQLConnection().createStatement();
    		ResultSet resultSet = statement.executeQuery(sql);
    		
    		if(!resultSet.isBeforeFirst()) {
    			Alert alert = new Alert(AlertType.ERROR, "Your credential are wrong");
        		alert.show();
    		} else {
    			CustomerMain.changeWindow("ClientWindow.fxml");
    		}	
    	}
    }

    @FXML
    void OnClickSignIn(ActionEvent event) {

    }

}