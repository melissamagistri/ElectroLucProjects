package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
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
<<<<<<< HEAD
		Main.changeWindow("Login.fxml");
=======
		Main.changeWindow("OrderConfirmedWindow.fxml");
>>>>>>> d995d5d6620c86aa39485fa27b6feed84d6e1d0f
		
	}
	
	//the method to change the window
	public static void changeWindow(String newWindow) throws IOException {
		Pane mainPane = (Pane) FXMLLoader.load(Main.class.getResource(newWindow));
		primaryStage.setScene(new Scene(mainPane));
		primaryStage.show();
		
	}
}
