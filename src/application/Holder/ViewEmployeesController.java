package application.Holder;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Employee;
import modelutil.Person;

public class ViewEmployeesController implements Initializable {

    @FXML
    private TableColumn<?, ?> IDColumn;

    @FXML
    private TableColumn<?, ?> contractColumn;

    @FXML
    private TableColumn<?, ?> fiscalCodeColumn;

    @FXML
    private TableColumn<?, ?> nameColumn;

    @FXML
    private TableColumn<?, ?> salaryColumn;

    @FXML
    private TableColumn<?, ?> surnameColumn;

    @FXML
    private TableView<?> tableView;
    
    @FXML
    void OnCLickGoBack(ActionEvent event) throws IOException {
    	HolderMain.changeWindow("ModifyEmployee.fxml");
    }

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
	}
    
    

}
