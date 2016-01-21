package com.kirdow.sortcsv.util;

//There is already a Timer in java.util.Timer but I felt I'll create my own which has what I need.
public class Timer {
	
	private String name;
	private int interval;
	private long time;
	private ITimerTick tick;
	private boolean _running;
	
	private Timer(String name, int interval, ITimerTick tick) {
		this.name = name;
		this.interval = interval;
		this.tick = tick;
		this.time = interval * 1000000;
	}
	
	private void tick(long delta) {
		if (!_running) return;
		this.time -= delta;
	}
	
	private void _start() {
		_running = true;
	}
	
	private void _stop() {
		_running = false;
	}
	
	private static Timer[] timers = new Timer[256];
	
	
	private static final TimerRun threadClass = new TimerRun();
	private static final Thread timerThread = new Thread(threadClass);
	
	private static void start() {
		if (threadClass.running)
			return;
		timerThread.start();
	}
	
	public static void stop() {
		threadClass.running = false;
		try {
			timerThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private static class TimerRun implements Runnable {
		
		private boolean running = false;
		public void run() {
			running = true;
			long lastTick = System.nanoTime();
			while (running) {
				long now = System.nanoTime();
				long delta = now - lastTick;
				for (int i = 0; i < timers.length; i++) {
					if (timers[i] == null) continue;
					if (!timers[i]._running) {
						timers[i] = null;
						continue;
					}
					timers[i].tick(delta);
					if (timers[i].time <= 0) {
						timers[i].time += timers[i].interval * 1000000;
						timers[i].tick.timer_tick();
					}
				}
			}
		}
	}
	
	public static Timer startTimer(String name, int interval, ITimerTick tick) {
		if (hasTimerByName(name))
			return null;
		if (!threadClass.running) {
			start();
		}
		Timer timer = new Timer(name, interval, tick);
		boolean full = true;
		for (int i = 0; i < timers.length; i++) {
			if (timers[i] == null) {
				timer._start();
				timers[i] = timer;
				full = false;
				break;
			}
		}
		if (full) {
			timer = null;
			return null;
		}
		return timer;
	}
	
	public static Timer getTimerByName(String name) {
		for (int i = 0; i < timers.length; i++) {
			if (timers[i] == null) continue;
			if (timers[i].name.equals(name))
				return timers[i];
		}
		return null;
	}
	
	public static boolean hasTimerByName(String name) {
		for (int i = 0; i < timers.length; i++) {
			if (timers[i] == null) continue;
			if (timers[i].name.equals(name))
				return true;
		}
		return false;
	}
	
	public static boolean stopTimerByName(String name) {
		for (int i = 0; i < timers.length; i++) {
			if (timers[i] == null) continue;
			if (timers[i].name.equals(name)) {
				timers[i]._stop();
				return true;
			}
		}
		return false;
	}
	
	private static boolean removeTimerByName(String name) {
		for (int i = 0; i < timers.length; i++) {
			if (timers[i] == null) continue;
			if (timers[i].name.equals(name)) {
				if (timers[i]._running) {
					timers[i]._stop();
				}
				timers[i] = null;
				return true;
			}
		}
		return false;
	}
	
}
