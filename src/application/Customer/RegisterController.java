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

public class RegisterController {

    @FXML
    private TextField txDeliveryAddress;

    @FXML
    private TextField txEmail;

    @FXML
    private TextField txFiscalCode;

    @FXML
    private TextField txName;

    @FXML
    private TextField txPassword;

    @FXML
    private TextField txPhone;

    @FXML
    private TextField txSurname;

    @FXML
    void OnClickCreate(ActionEvent event) throws SQLException, IOException {
    	Connection connection;
    	try {
			connection = new DBConnection().getMySQLConnection().get();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("there was a problem with the db connection");
			return;
		}
    	
    	if(this.txDeliveryAddress.getText().equals("") || this.txDeliveryAddress.getText().equals("") ||
    			this.txDeliveryAddress.getText().equals("") || this.txDeliveryAddress.getText().equals("") ||
    			this.txDeliveryAddress.getText().equals("") || this.txDeliveryAddress.getText().equals("") ||
    			this.txDeliveryAddress.getText().equals("")) {
    		Alert alert = new Alert(AlertType.ERROR,"You need to fill all the fields");
    		alert.show();
    	} else {
    		String sql = "select * from `negozio elettronica`.customers_accounts where Email = '"+this.txEmail.getText()+"'";
    		Statement statement = connection.createStatement();
    		ResultSet resultSet = statement.executeQuery(sql);
    		if(resultSet.next()) {
    			Alert alert = new Alert(AlertType.ERROR,"There is already an account with your email or fiscal "
    					+ "code associated");
        		alert.show();
    		} else {
    			sql = "insert into `negozio elettronica`.`customers_accounts` (`FirstName`, `LastName`,`Phone`,  `DeliveryAddress`,`Email`, `Password`)"
    					+ " values ('"+this.txName.getText()+"', '"+this.txSurname.getText()+"', '"+
    					this.txPhone.getText()+"', '"+
    					this.txDeliveryAddress.getText()+"', '"+this.txEmail.getText()+"', '"+this.txPassword.getText()+"')";
    			statement = connection.createStatement();
    			statement.executeUpdate(sql);
    			Alert alert = new Alert(AlertType.INFORMATION, "The account was created");
    			alert.show();
    			CustomerMain.changeWindow("Login.fxml");
    		}
			
    	}
    }

    
    @FXML
    void OnClickGoBack(ActionEvent event) throws IOException {
    	CustomerMain.changeWindow("Login.fxml");
    }

}

