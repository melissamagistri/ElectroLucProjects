package application.Holder;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ViewEmployeesController {

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
    

}
