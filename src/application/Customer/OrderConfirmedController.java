package application.Customer;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class OrderConfirmedController implements Initializable{

    @FXML
    private Button GoBackButton;
    
    @FXML
    private Label label;
    
    private Random random = new Random();

    @FXML
    void OnClickGoBack(ActionEvent event) throws IOException {
    	CustomerMain.changeWindow("BuyProduct.fxml");
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.label.setText("" + (random.nextInt(1000000000) + 1000));
		
	}

}

