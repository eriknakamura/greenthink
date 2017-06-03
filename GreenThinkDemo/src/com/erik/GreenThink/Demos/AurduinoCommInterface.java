package com.erik.GreenThink.Demos;


public interface AurduinoCommInterface {
	/**
	 * @param sensor
	 * @param state True is on, False is off
	 */
	public void setState(String sensor,boolean state);
}
