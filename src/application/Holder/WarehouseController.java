package application.Holder;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import db.actions.ActionsOnProduct;
import db.actions.CheckInteger;
import db.connection.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class WarehouseController {

    @FXML
    private TextField txCompartament;

    @FXML
    private TextField txID;

    @FXML
    private TextField txLane;

    @FXML
    private TextField txShelf;

    @FXML
    void OnClickApply(ActionEvent event) {
    	Connection conn;
    	Alert alert;
		try {
			conn = new DBConnection().getMySQLConnection().get();
		} catch (ClassNotFoundException | SQLException e) {
			System.err.println(e.getMessage());
			return;
		}
		if(CheckInteger.isNumeric(txID.getText())) {
			alert = new Alert(AlertType.ERROR, "Error: the id must be an integer number");
    		alert.show();
    		return;
		}
    	try {
			ActionsOnProduct.insertIntoWarehouse(conn,
					txShelf.getText(), txLane.getText(), txCompartament.getText(), 
					Integer.valueOf(txID.getText()));
		} catch (SQLException e) {
			alert = new Alert(AlertType.ERROR, e.getMessage());
    		alert.show();
    		return;
		}
    }

    @FXML
    void OnClickGoBack(ActionEvent event) throws IOException {
    	HolderMain.changeWindow("HolderController.fxml");
    }

}
