package com.erik.GreenThink.Demos;

//jSerialComm Import
import com.fazecast.jSerialComm.SerialPort;

//Java Imports
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Scanner;
public class SerialManager {
	
	public  SerialPort[] findPorts(){
		SerialPort[] ports = SerialPort.getCommPorts();
		return ports;
	}
	public  String[] getCommomNames(SerialPort[] ports){
		String [] portCommonNames = null;
		for(int i=0;i<ports.length;i++){
			portCommonNames[i] = ports[i].getDescriptivePortName();
		}
		return portCommonNames;
	}
	public  String[] getSystemNames(SerialPort[] ports){
		String [] portSystemNames = null;
		for(int i=0;i<ports.length;i++){
			portSystemNames[i] = ports[i].getSystemPortName();
		}
		return portSystemNames;
	}
	//Commit change test

}
