package application.Holder;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import db.actions.ActionsOnProduct;
import db.actions.CheckInteger;
import db.connection.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.Model;


public class RemoveProductController {

	@FXML
	private TextField searchText;

    @FXML
    void OnClickGoBack(ActionEvent event) throws IOException {
    	HolderMain.changeWindow("Holder.fxml");
    }

    @FXML
    void OnClickRemove(ActionEvent event) {
    	Alert alert;
    	Connection conn;
    	if(searchText.getText().isBlank()) {
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
//    	ActionsOnProduct.deleteProduct(conn, modelID);
    }

    @FXML
    void OnClickSerach(ActionEvent event) {
    	Alert alert;
    	Connection conn;
    	if(searchText.getText().isBlank()) {
    		alert = new Alert(AlertType.ERROR, "the search text field cannot be empty");
    		alert.show();
    		return;
    	}
    	if(!CheckInteger.isNumeric(searchText.getText())) {
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
			Optional<Model> model = this.search(conn, Integer.valueOf(searchText.getText()));
			if(model.isEmpty()) {
				alert = new Alert(AlertType.ERROR, "model not found");
	    		alert.show();
	    		return;
			}
		} catch (SQLException e) {}
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

