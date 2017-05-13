package com.erik.GreenThink.Demos;

public class Memory {
	AurduinoCommInterface io;
	
	SensorMemory tempSensors;
	SensorMemory humiditySensors;
	SensorMemory pHSensor;
	SensorMemory nutreiantSensors;
	
	public Memory(AurduinoCommInterface io)
	{
		this.io=io;
	}

	public void readAll(double deltaT)
	{
		// TODO Auto-generated method stub
		
	}
	
	/*Actuators
	 * 
	 * dehumidifyer
	 * AC
	 * lights
	 * fans
	 * pH down
	 * add nutreants
	 * 
	 */
}
