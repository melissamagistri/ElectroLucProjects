package application.Employee;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class SellWindowController {
	
	 	@FXML
	    private TableColumn<?, ?> IDcolumn;

	    @FXML
	    private TableColumn<?, ?> descriptionColumn;

	    @FXML
	    private TableColumn<?, ?> nameColumn;

	    @FXML
	    private TableColumn<?, ?> priceColumn;

	    @FXML
	    private TableColumn<?, ?> quantityColumn;

	    @FXML
	    private TextField searchTextField;

	    @FXML
	    private TableView<?> tableview;

    @FXML
    void OnClickGoBack(ActionEvent event) throws IOException {
    	EmployeeMain.changeWindow("EmployeeWindow.fxml");
    }

    @FXML
    void OnClickSell(ActionEvent event) throws IOException {
    	EmployeeMain.changeWindow("ReciptWindow.fxml");
    }
    
    @FXML
    void OnCLickSearch(ActionEvent event) throws IOException {
    	
    }

}