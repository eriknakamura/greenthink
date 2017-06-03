package com.erik.GreenThink.Demos;

/**
 * meta data map
 */
public class MDMap {

	SensorMemory tempSensors;
	SensorMemory humiditySensors;
	SensorMemory pHSensor;
	SensorMemory nutreiantSensors;
	public static final String DEHUMIDIFYER_NAME="DHM";
	public static final String AC_NAME="AC";
	public static final String LIGHTS_NAME="LT";
	public static final String FANS_NAME="FN";
	public static final String PH_DOWN_NAME="PHD";
	public static final String NUTREANT_NAME="NUT";
	
	public static final byte TEMP_BYTE=0x1;
	public static final byte HUMIDITY_BYTE=0x2;
	public static final byte PH_BYTE=0x3;
	public static final byte NUTREIANT_BYTE=-0x4;
}
