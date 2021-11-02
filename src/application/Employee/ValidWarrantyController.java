package application.Employee;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.actions.CheckInteger;
import db.connection.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class ValidWarrantyController {

    @FXML
    private Button GoBackButton;

    @FXML
    private Button SearchProductButton;

    @FXML
    private TextField ProductIDTextBar;

    @FXML
    private TextField ReceiptIDTextBar;

    @FXML
    private Label resultLabel;
    
    @FXML
    void OnClickGoBack(ActionEvent event) throws IOException {
    	EmployeeMain.changeWindow("EmployeeWindow.fxml");
    }

    @FXML
    void OnClickSearchProduct(ActionEvent event) {
    	Alert alert;
    	if(!CheckInteger.isNumeric(ProductIDTextBar.getText()) || !CheckInteger.isNumeric(ReceiptIDTextBar.getText())) {
    		alert = new Alert(AlertType.ERROR, "ProductID and ReceiptID must both be integer");
    		alert.show();
    		return;
    	}
    	if(ProductIDTextBar.getText().isBlank() && ReceiptIDTextBar.getText().isBlank()) {
    		alert = new Alert(AlertType.ERROR, "ProductID and ReceiptID must both be defined");
    		alert.show();
    		return;
    	}
    	if(ProductIDTextBar.getText().isBlank()) {
    		alert = new Alert(AlertType.ERROR, "ProductID must be defined");
    		alert.show();
    		return;
    	}
    	if(ReceiptIDTextBar.getText().isBlank()) {
    		alert = new Alert(AlertType.ERROR, "ReceiptID must be defined");
    		alert.show();
    		return;	
    	}
    	Connection conn;
		try {
			conn = new DBConnection().getMySQLConnection().get();
		} catch (ClassNotFoundException e) {
			alert = new Alert(AlertType.ERROR, "Error: Driver not found");
    		alert.show();
    		return;
		} catch (SQLException e) {
			alert = new Alert(AlertType.ERROR, "Error: unable to connect with the database");
    		alert.show();
    		return;
		}
    	try {
    		String resultTrue = "Your product is under warranty";
    		String resultFalse = "Your product isn't under warranty";

    		resultLabel.setText(this.isInWarranty(conn, Integer.valueOf(ReceiptIDTextBar.getText()),
    				Integer.valueOf(ProductIDTextBar.getText()))? resultTrue : resultFalse);

		} catch (SQLException e) {}
    }
    
    /*@FXML
    void OnClickRepairProduct(ActionEvent event) throws IOException {
    	EmployeeMain.changeWindow("RepairProduct.fxml");
    }*/


    private Boolean isInWarranty(final Connection conn, final int receiptID, final int productID) throws SQLException {

		String query = "SELECT *"
				+ "	FROM orders o, receipts r"
				+ "	WHERE r.ReceiptID = " +receiptID
				+ "	AND TIMESTAMPDIFF(YEAR, now(), o.OrderDate) <= 2"
				+ "	AND r.OrderID = o.OrderID"
				+ "	AND o.ProductID = " +productID +";";
		
		PreparedStatement preparedStmt = conn.prepareStatement(query);

		// execute the query, and get a java resultset
		return preparedStmt.executeQuery(query).isBeforeFirst();
	}
}

