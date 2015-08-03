package com.systran.thread;


public class BackgroundThreadWrapper extends Thread{
	private BackgroundThread rable;

	public BackgroundThreadWrapper(BackgroundThread rable) {
		this.rable = rable;
	}
	
	public void pause() {
		rable.pause();
	}
	
	public void revive() {
		rable.resume();
	}
	
	@Override
	public void run() {
		rable.run();
	}	
};