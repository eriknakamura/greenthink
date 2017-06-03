package com.erik.GreenThink.Demos;

import java.util.HashMap;
import java.util.Map;

public class Memory {
	
	AurduinoCommInterface io;
	
	SensorMemory tempSensors;
	SensorMemory humiditySensors;
	SensorMemory pHSensor;
	SensorMemory nutreiantSensors;
	
	public static final Map<Byte,SensorMemory> fMap = new HashMap<>();
	
	boolean DHOn;
	boolean ACOn;
	boolean lightsOn;
	boolean fansOn;
	boolean pHDownOn;
	boolean nutreantsOn;
	
	public Memory(AurduinoCommInterface io)
	{
		this.io=io;
		fMap.put(MDMap.TEMP_BYTE,tempSensors);
		fMap.put(MDMap.HUMIDITY_BYTE,humiditySensors);
		fMap.put(MDMap.PH_BYTE,pHSensor);
		fMap.put(MDMap.NUTREIANT_BYTE,nutreiantSensors);
	}
	
	public synchronized void write(byte sensorType, byte sensorIndex, byte[] data/*len 4*/)
	{
		SensorMemory mem = fMap.get(sensorType);
		//TODO not just read as INT
		int val = Utils.bytesToInt(data);
		mem.sensors[(int)sensorIndex].putValue(val);
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
			io.setState(MDMap.DEHUMIDIFYER_NAME, true);
			DHOn=true;
		}
		else if(!DH&&DHOn)
		{
			io.setState(MDMap.DEHUMIDIFYER_NAME, false);
			DHOn=false;
		}
		
		if(AC&&!ACOn)
		{
			io.setState(MDMap.AC_NAME, true);
			ACOn=true;
		}
		else if(!AC&&ACOn)
		{
			io.setState(MDMap.AC_NAME, false);
			ACOn=false;
		}

		if(lights&&!lightsOn)
		{
			io.setState(MDMap.LIGHTS_NAME, true);
			lightsOn=true;
		}
		else if(!lights&&lightsOn)
		{
			io.setState(MDMap.LIGHTS_NAME, false);
			lightsOn=false;
		}

		if(fans&&!fansOn)
		{
			io.setState(MDMap.FANS_NAME, true);
			fansOn=true;
		}
		else if(!fans&&fansOn)
		{
			io.setState(MDMap.FANS_NAME, false);
			fansOn=false;
		}

		if(pHDown&&!pHDownOn)
		{
			io.setState(MDMap.PH_DOWN_NAME, true);
			pHDownOn=true;
		}
		else if(!pHDown&&pHDownOn)
		{
			io.setState(MDMap.PH_DOWN_NAME, false);
			pHDownOn=false;
		}
		
		if(nutreants&&!nutreantsOn)
		{
			io.setState(MDMap.NUTREANT_NAME, true);
			nutreantsOn=true;
		}
		else if(!nutreants&&nutreantsOn)
		{
			io.setState(MDMap.NUTREANT_NAME, false);
			nutreantsOn=false;
		}
	}
}
