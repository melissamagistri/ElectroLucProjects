package application.Holder;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.connection.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ModifyEmployeeController {

	@FXML
    private Button ChangeSalaryButton;

    @FXML
    private Button DismissButton;

    @FXML
    private Button GoBackButton;

    @FXML
    private TextField SearchBar;

    @FXML
    private Button SelectButton;

    @FXML
    private TextField txContract;

    @FXML
    private TextField txFiscalCode;

    @FXML
    private TextField txIDCode;

    @FXML
    private TextField txName;

    @FXML
    private TextField txSurname;
    
    @FXML
    private TextField txSalary;

    @FXML
    void OnClickChangeSalary(ActionEvent event) throws IOException {
    	HolderMain.changeWindow("ChangeSalary.fxml");
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
    				+ "where EmployeeID = '" + this.txIDCode.getText() + "'";
    	
    	Statement statement = connection.createStatement();
		statement.execute(sql);
    }

    @FXML
    void OnClickGoBack(ActionEvent event) throws IOException {
    	HolderMain.changeWindow("Holder.fxml");
    }

    @FXML
    void OnClickSelect(ActionEvent event) {

    }
    
    @FXML
    void OnClickViewEmployees(ActionEvent event) throws IOException {
    	HolderMain.changeWindow("ViewEmployees.fxml");
    }

}


