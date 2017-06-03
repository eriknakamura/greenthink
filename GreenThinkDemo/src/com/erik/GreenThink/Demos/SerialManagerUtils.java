package com.erik.GreenThink.Demos;

//jSerialComm Import
import com.fazecast.jSerialComm.SerialPort;
//Java Imports
public class SerialManagerUtils {
	public static SerialPort[] findPorts(){
		return SerialPort.getCommPorts();
	}
	public static String[] getCommomNames(SerialPort[] ports){
		String[] portCommonNames = new String[ports.length];
		for(int i=0;i<ports.length;i++){
			portCommonNames[i] = getCommomName(ports[i]);
		}
		return portCommonNames;
	}
	public static String getCommomName(SerialPort port){
		return port.getDescriptivePortName();
	}
	public static String getSystemName(SerialPort port){
		return port.getSystemPortName();
	}
	public static String[] getSystemNames(SerialPort[] ports){
		String [] portSystemNames = new String[ports.length];
		for(int i=0;i<ports.length;i++){
			portSystemNames[i] = getSystemName(ports[i]);
		}
		return portSystemNames;
	}
}
