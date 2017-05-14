package com.erik.GreenThink.Demos;

public class Memory {
	public static final String DEHUMIDIFYER_NAME="";
	public static final String AC_NAME="";
	public static final String LIGHTS_NAME="";
	public static final String FANS_NAME="";
	public static final String PH_DOWN_NAME="";
	public static final String NUTREANT_NAME="";
	
	
	AurduinoCommInterface io;
	
	SensorMemory tempSensors;
	SensorMemory humiditySensors;
	SensorMemory pHSensor;
	SensorMemory nutreiantSensors;
	
	boolean DHOn;
	boolean ACOn;
	boolean lightsOn;
	boolean fansOn;
	boolean pHDownOn;
	boolean nutreantsOn;
	
	public Memory(AurduinoCommInterface io)
	{
		this.io=io;
	}

	public void readAll(double dt)
	{
		//TODO implement read all once form of io.getValueMap() is known
	}

	public boolean getDehumidiferIsOn() {
		return DHOn;
	}

	public boolean getACIsOn() {
		return ACOn;
	}
	
	public boolean getLightsIsOn() {
		return lightsOn;
	}

	public boolean getFansIsOn() {
		return fansOn;
	}

	public void setDesiredStatesIfNeeded(boolean DH,
			boolean AC, boolean lights,
			boolean fans, boolean pHDown, boolean nutreants) {
		if(DH&&!DHOn)
		{
			io.setState(DEHUMIDIFYER_NAME, true);
			DHOn=true;
		}
		else if(!DH&&DHOn)
		{
			io.setState(DEHUMIDIFYER_NAME, false);
			DHOn=false;
		}
		
		if(AC&&!ACOn)
		{
			io.setState(AC_NAME, true);
			ACOn=true;
		}
		else if(!AC&&ACOn)
		{
			io.setState(AC_NAME, false);
			ACOn=false;
		}

		if(lights&&!lightsOn)
		{
			io.setState(LIGHTS_NAME, true);
			lightsOn=true;
		}
		else if(!lights&&lightsOn)
		{
			io.setState(LIGHTS_NAME, false);
			lightsOn=false;
		}

		if(fans&&!fansOn)
		{
			io.setState(FANS_NAME, true);
			fansOn=true;
		}
		else if(!fans&&fansOn)
		{
			io.setState(FANS_NAME, false);
			fansOn=false;
		}

		if(pHDown&&!pHDownOn)
		{
			io.setState(PH_DOWN_NAME, true);
			pHDownOn=true;
		}
		else if(!pHDown&&pHDownOn)
		{
			io.setState(PH_DOWN_NAME, false);
			pHDownOn=false;
		}
		
		if(nutreants&&!nutreantsOn)
		{
			io.setState(NUTREANT_NAME, true);
			nutreantsOn=true;
		}
		else if(!nutreants&&nutreantsOn)
		{
			io.setState(NUTREANT_NAME, false);
			nutreantsOn=false;
		}
	}
}
