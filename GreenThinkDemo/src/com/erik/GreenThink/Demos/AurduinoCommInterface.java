package com.erik.GreenThink.Demos;

import java.util.List;
import java.util.Map;

public interface AurduinoCommInterface {
	public List<String> getConnectedSensors();
	public List<String> getConnectedActuators();

	public double getValue(String sensor);
	public Map<String,Double> getValueMap();
	
	public boolean checkState(String sensor);
	
	/**
	 * @param sensor
	 * @param state True is on, False is off
	 */
	public void setState(String sensor,boolean state);
}
