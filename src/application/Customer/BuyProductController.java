package application.Customer;

import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import db.connection.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert.AlertType;
import model.Model;

public class BuyProductController {

    @FXML
    private Button BuyButton;

    @FXML
    private Button GoBackButton;

    @FXML
    private ListView<Model> ProductListView;

    @FXML
    private Button SearchProductButtot;

    @FXML
    private TextField SearchProductTextField;

    @FXML
    void OnClickBuy(ActionEvent event) throws IOException {
    	CustomerMain.changeWindow("OrderConfirmedWindow.fxml");
    }

    @FXML
    void OnClickGoBSck(ActionEvent event) throws IOException {
    	CustomerMain.changeWindow("ClientWindow.fxml");
    }

    @FXML
    void OnClickSearchProduct(ActionEvent event) {
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

    	ProductListView = new ListView<Model>();
    	ObservableList<Model> items = FXCollections.observableArrayList ();
    	try {
			this.searchModelInSalesCatalog(conn,SearchProductTextField.getText()).forEach(e -> items.add(e));
		} catch (SQLException e) {
			alert = new Alert(AlertType.ERROR, e.getMessage());
    		alert.show();
		}
    	ProductListView.setItems(items);
    }

    private List<Model> searchModelInSalesCatalog(final Connection conn, final String modelName) throws SQLException {

		List<Model> res = new ArrayList<>();
		String query = "SELECT * FROM models WHERE ModelName = " +modelName +" AND SalesCatalogMembership = true";
		PreparedStatement preparedStmt = conn.prepareStatement(query);

		// execute the query, and get a java resultset
		ResultSet rs = preparedStmt.executeQuery(query);

		Optional<Blob> image = Optional.empty();
		Optional<Integer> discount = Optional.empty();
		Boolean sales;

		// iterate through the java resultset
		while (rs.next()) {
			image = Optional.ofNullable(rs.getBlob("ModelImage"));
		    discount = Optional.ofNullable(rs.getInt("Discount"));
		    sales = (rs.getInt("SalesCatalogMembership")==1) ? true : false;

		    res.add(new Model(rs.getInt("ModelID"), rs.getString("ModelName"), rs.getString("Brand"),
		    		rs.getString("Description"), image, rs.getBigDecimal("UnitPrice"),
		    		discount, rs.getInt("UnitInStock"), rs.getInt("MaxQuantytyPerOrder"), 
		    		rs.getString("Category"), rs.getString("Shelf"), rs.getString("Lane"), 
		    		rs.getString("Compartment"), sales));
		}
		return res;
	}
    

    @FXML
    void onClickSelectedProduct(MouseEvent event) {
    	this.ProductListView.getSelectionModel().getSelectedItem().toString();
    }

}

