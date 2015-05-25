package application;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ControllerView extends Stage implements Observer {
	
	public Timer timer = new Timer(10);
	public Controller controller = new Controller(this.timer);
	
	public Label timerLabel = new Label("0.00");
	
	public ControllerView() throws Exception {
		BorderPane root = new BorderPane();
		this.timer.addObserver(this);
		
		//VBox with the clock
		final VBox vbox = new VBox();
		timerLabel.setFont(new Font("Arial", 50));
		vbox.getChildren().addAll(timerLabel);
		vbox.setAlignment(Pos.CENTER);
		root.setCenter(vbox);
		
		//HBox with the buttons
		final HBox hbox = new HBox(50);
		final Button b1 = new Button("Start");
		b1.setFont(new Font ("Verdana", 14));
		b1.setOnAction((event) -> {
			Platform.runLater(() -> {
				this.controller.start();				
			});
		});
		
		final Button b2 = new Button("Stop");
		b2.setFont(new Font ("Verdana", 14));
		b2.setOnAction((event) -> {
			Platform.runLater(() -> {
				this.controller.stop();
			});
		});
		
		final Button b3 = new Button("Reset");
		b3.setFont(new Font ("Verdana", 14));
		b3.setOnAction((event) -> {
			Platform.runLater(() -> {
				this.controller.reset();
			});
		});
		
		final ArrayList<Node> node = new ArrayList<>();
		node.add(b1);
		node.add(b2);
		node.add(b3);
		hbox.getChildren().addAll(node);
		hbox.setAlignment(Pos.CENTER);
		hbox.setPrefSize(50, 50);
		root.setBottom(hbox);
		
		//set up the scene
		this.setScene(new Scene(root, 300, 150));
		this.setTitle("Stopwatch");
		this.show();
	}

	@Override
	public void update(Observable o, Object arg) {
		Platform.runLater(() -> {
			this.timerLabel.setText(this.timer.getTimeString());
		});		
	}
}
