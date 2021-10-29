package application.Holder;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class ProductMenuController {

	  @FXML
	    void OnClickAddProduct(ActionEvent event) throws IOException {
		  HolderMain.changeWindow("ConfirmSupplier.fxml");
	    }

	    @FXML
	    void OnClickRemoveproduct(ActionEvent event) throws IOException {
	    	HolderMain.changeWindow("RemoveProduct.fxml");
	    }
}
