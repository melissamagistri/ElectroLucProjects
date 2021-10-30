package application.Holder;

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
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class DiscountController {

    @FXML
    private Button ApplyDiscountButton;

    @FXML
    private Button GoBackButton;

    @FXML
    private TextField SearchBar;

    @FXML
    private Button SearchProductButton;
    
    @FXML
    private TextField txModel;

    @FXML
    private TextField txPrice;
    
    @FXML
    private TextField txNewDiscount;

    @FXML
    private TextField txOldDiscoun;

    @FXML
    void OnClickApplyDiscount(ActionEvent event) throws IOException {
    	Alert alert;
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
		if(!CheckInteger.isNumeric(txNewDiscount.getText())) {
    			alert = new Alert(AlertType.ERROR, "Discount must be an integer number");
    			alert.show();
    			return;
    	}
        try {
        	if(Integer.valueOf(txNewDiscount.getText()) == 0) {
        		this.removeDiscount(conn, Integer.valueOf(txModel.getText()));
        		return;
        	}
        	this.applyDiscount(conn, Integer.valueOf(txModel.getText()), 
					Integer.valueOf(txOldDiscoun.getText()));
		} catch (SQLException e) {
			e.printStackTrace();
       	return;
    	}
    }

    @FXML
    void OnClickGoBack(ActionEvent event) throws IOException {
    	HolderMain.changeWindow("Holder.fxml");
    }

    @FXML
    void OnClickSearchProductButton(ActionEvent event) {

    }
    
    @FXML
    void OnClickDeleteDiscount(ActionEvent event) {

    }

    private void applyDiscount(final Connection conn, final int modelID, final int discount) throws SQLException {

		//check if it has been insert discount = 0
		if(discount == 0) {
			this.removeDiscount(conn, modelID);
			return;
		}

		String query = "UPDATE models SET Discount = ? WHERE ModelID = ?";
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		preparedStmt.setInt(1, discount);
		preparedStmt.setInt(2, modelID);

		//execute query
		preparedStmt.executeUpdate();

	}

	private void removeDiscount(final Connection conn,final int modelID) throws SQLException {
		String query = "UPDATE models SET Discount = null WHERE ModelID = ?";
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		preparedStmt.setInt(1, modelID);

		//execute query
		preparedStmt.executeUpdate();
	}


}
