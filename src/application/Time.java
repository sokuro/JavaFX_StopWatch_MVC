package application;

import java.util.Date;
import java.util.Observable;

public class Time extends Observable implements Runnable {
	
	private long time;
	
	public Time() {
//		this.time = (int)(System.currentTimeMillis()/1000) % (60*60*24);
		this.time = System.currentTimeMillis();
		
		(new Thread(this)).start();
	}
	
	public void run() {
		while (true) {
			try {
				Thread.sleep(1000);
				this.increaseTime();
			} catch (InterruptedException e) {}
		}
	}
	
	private void increaseTime() {
		
		this.time += 1000;	//necessary to show the changed time
		//Call setChanged() before calling notifyObservers()
		this.setChanged();
		this.notifyObservers();
	}
	
	public long getTime() {
		return this.time;
	}

	public String getTimeToString() {
		Date currentDate = new Date(this.time);
		String s = String.valueOf(currentDate);
		return s;
	}
}
