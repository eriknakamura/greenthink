package com.erik.GreenThink.Demos;

public class Memory {
	public static final String DEHUMIDIFYER_NAME="";
	public static final String AC_NAME="";
	public static final String LIGHTS_NAME="";
	public static final String FANS_NAME="";
	
	
	AurduinoCommInterface io;
	
	SensorMemory tempSensors;
	SensorMemory humiditySensors;
	SensorMemory pHSensor;
	SensorMemory nutreiantSensors;
	
	public Memory(AurduinoCommInterface io)
	{
		this.io=io;
	}

	public void readAll(double dt)
	{
		// TODO Auto-generated method stub
		
	}

	public boolean getDehumidiferIsOn() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean getACIsOn() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean getLightsIsOn() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean getFansIsOn() {
		// TODO Auto-generated method stub
		return false;
	}

	public void setDesiredStatesIfNeeded(boolean DH,
			boolean AC, boolean lights,
			boolean fans) {
		if(DH&&!getDehumidiferIsOn())
			io.setState(DEHUMIDIFYER_NAME, true);
		else if(!DH&&getDehumidiferIsOn())
			io.setState(DEHUMIDIFYER_NAME, false);
		
		if(AC&&!getACIsOn())
			io.setState(AC_NAME, true);
		else if(!AC&&getACIsOn())
			io.setState(AC_NAME, false);

		if(lights&&!getLightsIsOn())
			io.setState(LIGHTS_NAME, true);
		else if(!lights&&getLightsIsOn())
			io.setState(LIGHTS_NAME, false);

		if(fans&&!getFansIsOn())
			io.setState(FANS_NAME, true);
		else if(!fans&&getFansIsOn())
			io.setState(FANS_NAME, false);
		
	}

	
	
}
