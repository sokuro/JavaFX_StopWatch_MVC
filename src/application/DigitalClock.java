package application;

import java.util.Observable;
import java.util.Observer;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class DigitalClock extends Stage implements Observer {

	private Time time;
	private Label timeLabel;
	
	public DigitalClock(Time time) {
		this.time = time;
		this.time.addObserver(this);
	}
	
	//implication on the grid-scene (new constructor)
	public DigitalClock(Time time, Label label) {
		this.time = time;
		this.timeLabel = label;
		this.time.addObserver(this);
	}
	
	
	@Override
	public void update(Observable o, Object arg) {
		//GUI updated
		Platform.runLater(() -> {
			String s = this.time.getTimeToString();
			this.timeLabel.setText(this.time.getTimeToString());
			System.out.println(s);
		});
	}
}
