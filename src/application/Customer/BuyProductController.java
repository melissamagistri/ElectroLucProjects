package application.Customer;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import db.actions.ActionsOnCategory;
import db.actions.ActionsOnProduct;
import db.connection.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Model;
import util.Pair;

public class BuyProductController {

	protected static int ID; 
	
    @FXML
    private TextField SearchProductTextField;
    
    @FXML
    private TableColumn<Model, String> categorycolumn;

    @FXML
    private ChoiceBox<String> choicebox;
    
    private ObservableList<String> categoryList;
    
    @FXML
    private TableColumn<Model, String> descriptioncolumn;

    @FXML
    private TableColumn<Model, String> namecolumn;

    @FXML
    private TableColumn<Model, BigDecimal> pricecolumn;
    
    @FXML
    private TableColumn<Model, Integer> unitInStockColumn;
    
    @FXML
    private TableView<Model> tableView;
    

    @FXML
    void OnClickContinue(ActionEvent event) throws IOException, SQLException {
    	
    	if(!(this.tableView.getSelectionModel().getSelectedItems().size() == 1)) {
    		Alert alert = new Alert(AlertType.ERROR, "You can select only one model for time");
			alert.show();
			return;
    	} else if(this.tableView.getSelectionModel().getSelectedItem().getUnitInStock() > 0){
    		BuyProductController.ID= this.tableView.getSelectionModel()
    				.getSelectedItem().getModelID();

        	CustomerMain.changeWindow("BuyAndPayment.fxml");
        	return;
    	} else {
    		Alert alert = new Alert(AlertType.ERROR, "This product is out of stock");
			alert.show();
			return;
    	}
    }

    @FXML
    void OnClickGoBSck(ActionEvent event) throws IOException {
    	CustomerMain.changeWindow("ClientWindow.fxml");
    }

    @FXML
    void OnClickSearchProduct(ActionEvent event) {
    	Connection conn;

    	try {
			conn = new DBConnection().getMySQLConnection().get();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return;
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}

    	ObservableList<Model> list = FXCollections.observableArrayList();
    	if(this.SearchProductTextField.getText().isBlank() && choicebox.getValue().isBlank()) {
    		try {
				list.addAll(ActionsOnProduct.searchAll(conn));
			} catch (SQLException e) {
				return;
			}
    	} else {
    		List<Pair<String,String>> attr = new ArrayList<>();

    		attr.add(new Pair<>("ModelName", this.SearchProductTextField.getText()));
    		attr.add(new Pair<>("Category", choicebox.getValue()));
	
    		tableView.getItems().clear();

    		try {
    			list.addAll(ActionsOnProduct.search(conn, attr));
    		} catch (SQLException e) {
    			return;
    		}
    	}
    	this.namecolumn.setCellValueFactory(new PropertyValueFactory<Model, String>("modelName"));
		this.categorycolumn.setCellValueFactory(new PropertyValueFactory<Model, String>("category"));
		this.unitInStockColumn.setCellValueFactory(new PropertyValueFactory<Model, Integer>("unitInStock"));
		this.pricecolumn.setCellValueFactory(new PropertyValueFactory<Model, BigDecimal>("unitSellingPrice"));
		this.descriptioncolumn.setCellValueFactory(new PropertyValueFactory<Model, String>("description"));
		this.tableView.setItems(list);		
    }


    @FXML 
    private void initialize() {
    	Connection conn;
    	try {
			conn = new DBConnection().getMySQLConnection().get();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
			return;
		} catch (SQLException e1) {
			e1.printStackTrace();
			return;
		}
    	
    	categoryList = FXCollections.observableArrayList();
    	try {
			categoryList.addAll(ActionsOnCategory
					.searchAll(conn)
					.stream()
					.map(e->e.getCategoryName())
					.collect(Collectors.toList()));
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
    	categoryList.add("");
    	this.choicebox.setItems(categoryList);

    	ObservableList<Model> list = FXCollections.observableArrayList();
		try {
			list.addAll(ActionsOnProduct.searchAll(conn));
			this.namecolumn.setCellValueFactory(new PropertyValueFactory<Model, String>("modelName"));
			this.categorycolumn.setCellValueFactory(new PropertyValueFactory<Model, String>("category"));
			this.unitInStockColumn.setCellValueFactory(new PropertyValueFactory<Model, Integer>("unitInStock"));
			this.pricecolumn.setCellValueFactory(new PropertyValueFactory<Model, BigDecimal>("unitSellingPrice"));
			this.descriptioncolumn.setCellValueFactory(new PropertyValueFactory<Model, String>("description"));
			this.tableView.setItems(list);
		} catch (SQLException e) {
			e.printStackTrace();
		}

    }
    
    
  
}

