package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;


public class Main extends Application {
	
	public static void main(String[] arg) {
		Application.launch(arg);
	}
	 double x,y = 0;
	@Override
	public void start(Stage primaryStage) throws Exception {
		Pane mainPane = (Pane) FXMLLoader.load(Main.class.getResource("Schermata.fxml"));
		
		/*Parent root = FXMLLoader.load(getClass().getResource("Titolare.fxml"));
        primaryStage.initStyle(StageStyle.UNDECORATED);

        root.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });

        root.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - x);
            primaryStage.setY(event.getScreenY() - y);
        });

        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.show();*/
    
		primaryStage.setTitle("ElectroLuc ShopOnline");
		primaryStage.setScene(new Scene(mainPane));
		primaryStage.show();
		
		
	}
}
