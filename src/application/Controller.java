package application;

public class Controller {
	
	private final Timer timer;
	
	public Controller(Timer timer) {
		this.timer = timer;
	}
	
	public void start() {
		this.timer.start();
	}
	
	public void stop() {
		this.timer.stop();
	}
	
	public void reset() {
		this.timer.reset();
	}
	
}

