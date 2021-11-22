package application.Holder;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.connection.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class RenewController {

    @FXML
    private TextField endtx;

    @FXML
    private TextField hiretx;
    
    @FXML
    private ChoiceBox<String> choicebox;
    
    private ObservableList<String> contractList;

    @FXML
    void OnCLickRenew(ActionEvent event) {
    	//controllo contract con end date, controlo stringhew scritte bene , update date o  cambio contratto
    }

    @FXML
    void OnClickGoBack(ActionEvent event) throws IOException {
    	HolderMain.changeWindow("ModifyFinishedEmployee.fxml");
    }
    
    private ObservableList<String> getContractList(){
		Connection connection; 
		this.contractList = FXCollections.observableArrayList();
		  try { 
			  connection = new DBConnection().getMySQLConnection().get(); 
			  String sql="SELECT TypeName from `negozio elettronica`.contract_types ";
						 
				Statement statement = connection.createStatement();
		    	ResultSet resultSet = statement.executeQuery(sql);
		    	while(resultSet.next()) {
		    		this.contractList.add(resultSet.getString("TypeName"));
		    	}
			  
		  } catch (ClassNotFoundException |SQLException e) {
			  System.out.println("there was a problem with the db connection");
		  }
		return this.contractList;
		
	}

}

