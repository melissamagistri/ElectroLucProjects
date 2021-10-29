package application.Holder;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class RemoveProductController {

    @FXML
    void OnClickGoBack(ActionEvent event) throws IOException {
    	HolderMain.changeWindow("Holder.fxml");
    }

    @FXML
    void OnClickRemove(ActionEvent event) {

    }

    @FXML
    void OnClickSerach(ActionEvent event) {

    }

}

