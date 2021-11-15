package application.Holder;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import db.actions.CheckInteger;
import db.connection.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.Model;

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

    	if(SearchBar.getText().isBlank()) {
			alert = new Alert(AlertType.ERROR, "You must insert the model id before apllying a discount");
			alert.show();
			return;
    	}
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
		if(!CheckInteger.isNumeric(txNewDiscount.getText()) && !txNewDiscount.getText().isBlank()) {
    		alert = new Alert(AlertType.ERROR, "Discount must be an integer number");
    		alert.show();
    		return;
    	}
        try {
        	if(Integer.valueOf(txNewDiscount.getText()) == 0 || txNewDiscount.getText().isBlank()) {
        		this.removeDiscount(conn, Integer.valueOf(txModel.getText()));
        		return;
        	}
        	this.applyDiscount(conn, Integer.valueOf(txModel.getText()), 
					Integer.valueOf(txOldDiscoun.getText()));
		} catch (SQLException e) {
			return;
    	} finally {
    		HolderMain.changeWindow("Holder.fxml");
    	}
    }

    @FXML
    void OnClickGoBack(ActionEvent event) throws IOException {
    	HolderMain.changeWindow("Holder.fxml");
    }

    @FXML
    void OnClickSearchProductButton(ActionEvent event) {
    	Alert alert;
    	Connection conn;
    	if(SearchBar.getText().isBlank()) {
    		alert = new Alert(AlertType.ERROR, "the search text field cannot be empty");
    		alert.show();
    		return;
    	}
    	if(!CheckInteger.isNumeric(SearchBar.getText())) {
    		alert = new Alert(AlertType.ERROR, "model id must be an integer number");
    		alert.show();
    		return;
    	}
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
			Optional<Model> model = this.search(conn, Integer.valueOf(SearchBar.getText()));
			if(model.isEmpty()) {
				alert = new Alert(AlertType.ERROR, "model not found");
	    		alert.show();
	    		return;
			}
			if(!model.get().isInSale()) {
				alert = new Alert(AlertType.ERROR, "model is not in sale");
	    		alert.show();
	    		return;
			}
			txModel.setText(String.valueOf(model.get().getModelID()));
			txPrice.setText(String.valueOf(model.get().getUnitSellingPrice()) +" $");
			txOldDiscoun.setText(model.get().getDiscount() == 0 ? "" :	String.valueOf(model.get().getDiscount()));
		} catch (SQLException e) {}
    }
    
    @FXML
    void OnClickDeleteDiscount(ActionEvent event) throws IOException {
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
    	if(SearchBar.getText().isBlank()) {
			alert = new Alert(AlertType.ERROR, "You must insert the model id before removing a discount");
			alert.show();
			return;
    	}
    	try {
			this.removeDiscount(conn, Integer.valueOf(txModel.getText()));
		} catch (SQLException e) {
		} finally {
			HolderMain.changeWindow("Holder.fxml");
		}
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

	private Optional<Model> search(final Connection conn,final int modelID) throws SQLException {
		String query = "SELECT * FROM models WHERE ModelID = " +modelID +";";
		PreparedStatement preparedStmt = conn.prepareStatement(query);

		// execute the query, and get a java resultset
		ResultSet rs = preparedStmt.executeQuery(query);

		Optional<Integer> discount = Optional.empty();
		Boolean sales;

		// iterate through the java resultset
		if (rs.next()) {
		    discount = Optional.ofNullable(rs.getInt("Discount"));
		    sales = (rs.getInt("InSale")==1) ? true : false;

		    return Optional.of(new Model(rs.getInt("ModelID"), rs.getString("ModelName"), rs.getString("Brand"),
		    	rs.getString("Description"), rs.getBigDecimal("UnitSellingPrice"), rs.getBigDecimal("UnitPurchasePrice"),
		    	discount, rs.getInt("UnitInStock"),	rs.getString("Category"), sales));
		}
		return Optional.empty();
	}

}
