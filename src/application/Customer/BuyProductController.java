package application.Customer;

import java.io.IOException;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.Model;

public class BuyProductController {

    @FXML
    private Button BuyButton;

    @FXML
    private Button GoBackButton;

    @FXML
    private Button SearchProductButtot;

    @FXML
    private TextField SearchProductTextField;
    
    @FXML
    private TableColumn<Model, String> categorycolumn;

    @FXML
    private ChoiceBox<String> choicebox;
    
    private ObservableList<String> categoryList = FXCollections.observableArrayList("Smartphone", 
    		"Computer","Auricolari", "Tablet","Smartwatch", "TV", "Monitor");

    @FXML
    private TableColumn<Model, String> descriptioncolumn;

    @FXML
    private TableColumn<Model, String> namecolumn;

    @FXML
    private TableColumn<Model, String> pricecolumn;
    
    @FXML
    private TableColumn<Model, String> IDColumn;
    
    private int variable = 1;

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
    	Connection connection;
    	try {
    		connection = new DBConnection().getMySQLConnection().get();
    	} catch (ClassNotFoundException e) {
			alert = new Alert(AlertType.ERROR, "Error: Driver not found");
    		alert.show();
    		return;
		} catch (SQLException e) {
			alert = new Alert(AlertType.ERROR, "Error: unable to connect with the database");
    		alert.show();
    		return;
		}
    	
    	/*String sql="Insert into `negozio elettronica`.orders (`OrderID`, `OrderDate`,`PaymentMEthod`, `OrderType` , `EmployeeID` , `Email`)"
				 + " values ('" + this.IDColumn.getCellData() + "', '" + this.ModelNameTextField.getText() +"', '" + this.BrandTextField.getText() + "','"
				 + this.DescriptionTextField.getText() + "', '" + this.choiceBox.getValue() + "', '" + this.PriceTextField.getText() + "', '" 
				 + this.UnitStockTextField.getText() + "')" ;
				  
		  Statement statement = conn.createStatement();
		  statement.executeUpdate(sql); */
    }

    
    @FXML
    void OnActionSearchCategory(ActionEvent event) {

    }
}

