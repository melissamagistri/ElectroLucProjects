package application.Employee;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;


public class EmployeeMain extends Application {
	
	private static Stage primaryStage;
	
	public static void main(String[] arg) {
		Application.launch(arg);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		EmployeeMain.primaryStage = primaryStage;
		EmployeeMain.primaryStage.setTitle("ElectroLuc ShopOnline");
		EmployeeMain.changeWindow("EmployeeWindow.fxml");
		
	}
	
	//the method to change the window
	public static void changeWindow(String newWindow) throws IOException {
		Pane mainPane = (Pane) FXMLLoader.load(EmployeeMain.class.getResource(newWindow));
		primaryStage.setScene(new Scene(mainPane));
		primaryStage.show();
		
	}
}
