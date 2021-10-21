package application.Employee;

import java.io.IOException;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class AvaliabilityProductController {

    @FXML
    private Button GoBackButton;

    @FXML
    private TableColumn<?, ?> NameTableColuomn;

    @FXML
    private TableColumn<?, ?> NumberTableColoumn;

    @FXML
    private TableColumn<?, ?> PriceTableColuomn;

    @FXML
    private TableView<?> ProductTableView;

    @FXML
    private TextField SearchBar;

    @FXML
    private Button SearchProductButton;

    @FXML
    private TableColumn<?, ?> StateTableColumn;

    @FXML
    void OnCLickGoBack(ActionEvent event) throws IOException {
    	Main.changeWindow("EmployeeWindow.fxml");
    }

    @FXML
    void OnClickSearchButton(ActionEvent event) {

    }

}

