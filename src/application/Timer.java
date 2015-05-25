package application;

import java.util.Observable;

public class Timer extends Observable implements Runnable {
	
	/**
	 * The number of ticks.
	 */
	private int ticks;

	/**
	 * The time interval (in milliseconds) of a tick.
	 */
	private int interval;

	/**
	 * The thread which triggers the ticks. Is null if the timer is not running.
	 */
	private Thread thread;

	/**
	 * Creates a Timer for the given interval.
	 * 
	 * @param interval
	 *            the time interval (in milliseconds) of the timer
	 */
	public Timer(int interval) {
		this.interval = interval;
	}

	/**
	 * Gets the time of the timer.
	 * 
	 * @return the time (in seconds) of the timer.
	 */
	public final double getTime() {
		return ticks * interval / 1000.0;
	}

	/**
	 * Gets the time of the timer as String.
	 * 
	 * @return the time (in seconds) converted to a String.
	 */
	public final String getTimeString() {
		return String.valueOf(this.getTime());
	}

	/**
	 * Returns true if timer is running.
	 * 
	 * @return true if the timer is running, otherwise false.
	 */
	public final boolean isRunning() {
		return thread != null;
	}

	/**
	 * Starts the timer.
	 */
	public final void start() {
		if (thread == null) {
			thread = new Thread(this);
			thread.setDaemon(true);
			thread.setPriority(Thread.MAX_PRIORITY);
			thread.start();
			//empfohlen zu aktualisieren
			this.updateObservers();
		}
	}

	/**
	 * Stops the timer.
	 */
	public final void stop() {
		if (thread != null) {
			thread = null;
			this.updateObservers();
		}
	}

	/**
	 * Resets the time of the timer.
	 */
	public final void reset() {
		this.ticks = 0;
		this.updateObservers();
	}

	/**
	 * Updates the observers
	 */
	private final void updateObservers() {
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Increments ticks at the expiration of the time interval.
	 */
	@Override
	public final void run() {
		while (thread != null) {
			try {
				Thread.sleep(interval);
			} catch (InterruptedException e) {
				// do nothing
			}
			if (thread != null) {
				ticks++;
				System.out.println(ticks);
				this.updateObservers();
			}
		}

	}
}