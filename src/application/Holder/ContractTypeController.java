package application.Holder;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class ContractTypeController {

	@FXML
    void OnClickFiniscedContract(ActionEvent event) throws IOException {
		HolderMain.changeWindow("ModifyFinishedEmployee.fxml");
    }

    @FXML
    void OnClickUnfinishedContract(ActionEvent event) throws IOException {
    	HolderMain.changeWindow("ModifyUnfinishedEmployee.fxml");
    }
}
