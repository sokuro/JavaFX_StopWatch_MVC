package application;
	
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;


public class Watch extends Application {

	final Label label = new Label("");

	public Time time = new Time();
	
	@Override
	public void start(Stage primaryStage) throws Exception {

//		new AnalogClock(time);
		
		BorderPane root = new BorderPane();
		try {
			final VBox vbox = new VBox(50);
			new DigitalClock(time, label);
			label.setFont(new Font("Arial", 20));
			vbox.getChildren().addAll(label);
			vbox.setAlignment(Pos.CENTER);
			root.setCenter(vbox);
			
			new ControllerView();
				
			Scene scene = new Scene(root, 500, 500);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Watch");
			primaryStage.setScene(scene);
			primaryStage.setX(200);
			primaryStage.setY(100);
			primaryStage.show();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void update() {
		Platform.runLater(() -> {
			this.label.setText(this.time.getTimeToString());
		});
	}
}
