package application;

import java.util.Observable;
import java.util.Observer;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class AnalogClock extends Stage implements Observer {
	
	private final Time time;
	private final Canvas canvas;
	
	public AnalogClock(Time time) {
		BorderPane root = new BorderPane();
		this.time = time;
		this.time.addObserver(this);	
		
		this.canvas = new Canvas(240, 240);
		
		root.setCenter(canvas);
		paint();
		
		Scene scene = new Scene(root, 260, 260);
		this.setTitle("Analog Clock");
		this.setScene(scene);
		this.setX(825);
		this.setY(600);
		this.show();
	}
	
	@Override
	public void update(Observable o, Object arg) {
		Platform.runLater(()->this.paint());
	}
	
	
	private void paint() {
		
		GraphicsContext g2 = this.canvas.getGraphicsContext2D();
		g2.clearRect(0, 0, this.canvas.getWidth(), this.canvas.getHeight());
		
		int cx = (int) this.canvas.getWidth() / 2;
		int cy = (int) this.canvas.getHeight() / 2;

		// Draw numbers
		for (int i = 0; i < 12; i++) {
			int n = (i + 9) % 12;
			n = (n == 0 ? 12 : n);
			int w = i * 360 / 12;
			int y = cy - (int) (Math.sin(Math.toRadians(w)) * 80);
			int x = cx - (int) (Math.cos(Math.toRadians(w)) * 80);
			g2.setStroke(Color.BLACK);
			g2.fillText("" + n, x - 5, y + 5);
		}

		// Draw hour hand
//		int h = ((time.getHours() % 12) + 3) * 360 / 12;
//		int h = time.getHours() * 360 / 12;
		int h = (int) ((((((this.time.getTime() / 1000) / 60) / 60) % 12) + 5) * 360 / 12);
		int y = cy - (int) (Math.sin(Math.toRadians(h)) * 40);
		int x = cx - (int) (Math.cos(Math.toRadians(h)) * 40);
		g2.setStroke(new Color(0.7, 0, 0, 1));
		g2.setLineWidth(2f);
		g2.strokeLine(cx, cy, x, y);

		// draw minute hand
//		int m = (time.getMinutes() + 15) * 6;
//		int m = time.getMinutes() * 6;
		int m = (int) (((((this.time.getTime() / 1000) / 60) % 60) + 15) * 6);
		y = cy - (int) (Math.sin(Math.toRadians(m)) * 60);
		x = cx - (int) (Math.cos(Math.toRadians(m)) * 60);
		g2.setStroke(new Color(0.7, 0.7, 0, 1));
		g2.setLineWidth(2f);
		g2.strokeLine(cx, cy, x, y);

		// draw second hand
//		int s = (time.getSeconds() + 15) * 6;
//		int s = time.getSeconds() * 6;
		int s =  (int) (((this.time.getTime() / 1000) + 15) * 6);
		y = cy - (int) (Math.sin(Math.toRadians(s)) * 70);
		x = cx - (int) (Math.cos(Math.toRadians(s)) * 70);
		g2.setStroke(new Color(0, 0, 0, 1));
		g2.setLineWidth(1f);
		g2.strokeLine(cx, cy, x, y);
	}
}

