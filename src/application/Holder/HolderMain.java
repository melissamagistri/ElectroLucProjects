package application.Holder;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;


public class HolderMain extends Application {
	
	private static Stage primaryStage;
	
	public static void main(String[] arg) {
		Application.launch(arg);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		HolderMain.primaryStage = primaryStage;
		HolderMain.primaryStage.setTitle("ElectroLuc ShopOnline");
		HolderMain.changeWindow("HolderLogin.fxml");
	}
	
	//the method to change the window
	public static void changeWindow(String newWindow) throws IOException {
		Pane mainPane = (Pane) FXMLLoader.load(HolderMain.class.getResource(newWindow));
		primaryStage.setScene(new Scene(mainPane));
		primaryStage.show();
		
	}
}
