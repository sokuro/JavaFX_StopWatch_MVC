package application;

public class Controller {
	
	private final Timer timer;
	
	public Controller(Timer timer) {
		this.timer = timer;
	}
	
	public void start() {
		System.out.println("Starting...");
		this.timer.start();
		System.out.println("..started!");
	}
	
	public void stop() {
		System.out.println("Stopping...");
		this.timer.stop();
		System.out.println("..stopped!");
	}
	
	public void reset() {
		System.out.println("Resetting...");
		this.timer.reset();
		System.out.println("..resetted!");
	}
	
}

