package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;


public class Main extends Application {
	
	private static Stage primaryStage;
	
	public static void main(String[] arg) {
		Application.launch(arg);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		Main.primaryStage = primaryStage;
		Main.primaryStage.setTitle("ElectroLuc ShopOnline");
		Main.changeWindow("ClientWindow.fxml");
		
	}
	
	//the method to change the window
	public static void changeWindow(String newWindow) throws IOException {
		Pane mainPane = (Pane) FXMLLoader.load(Main.class.getResource(newWindow));
		primaryStage.setScene(new Scene(mainPane));
		primaryStage.show();
		
	}
}
