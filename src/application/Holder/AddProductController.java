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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class AddProductController {

    @FXML
    private TextField DescriptionTextField;

    @FXML
    private Button GoBackButton;

    @FXML
    private TextField ModelTextField;

    @FXML
    private TextField PriceTextField;

    @FXML
    private TextField UnitStockTextField;

    @FXML
    private ChoiceBox<?> choiceBox;

    @FXML
    void OnClickAddButton(ActionEvent event) {
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
    	if(!CheckInteger.isNumeric(ModelTextField.getText())) {
    		alert = new Alert(AlertType.ERROR, "Error: model's id must be an integer number");
    		alert.show();
    		return;
    	}


    	//insert the queries to add the model to the database

    	  	




    	try {
			this.insertInSalesCatalog(conn, Integer.valueOf(ModelTextField.getText()));
		} catch (SQLException e) {
			alert = new Alert(AlertType.ERROR, "Error: " +e.getMessage());
    		alert.show();
		}
    }

    @FXML
    void OnClickGoBack(ActionEvent event) throws IOException {
    	HolderMain.changeWindow("Holder.fxml");
    }

    private void insertInSalesCatalog(final Connection conn, final int modelID) throws SQLException {

		String query = "UPDATE models SET SalesCatalogMembership = true WHERE ModelID = ?";
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		preparedStmt.setInt(1, modelID);

		//execute query
		preparedStmt.executeUpdate();

	}

}




