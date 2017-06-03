package com.erik.GreenThink.Demos;

import java.util.ArrayList;
import java.util.List;

import com.fazecast.jSerialComm.SerialPort;

public class SerialI{
	//SENSOR_TYPE_CODE,SENSOR_INDEX,VALUExM_SIZE,END_SIGxEND_SIG_TIMES
	List<Byte> upstreamBuf = new ArrayList<>();
	private void process(byte[] data)
	{
		for(byte b: data)
		{
			upstreamBuf.add(b);
			if(endSig(upstreamBuf))
				processMessage(upstreamBuf);
			upstreamBuf.clear();
		}
	}
	public static final byte END_SIG=0x55;
	public static final int END_SIG_TIMES=2;
	public static final int M_SIZE_BYTES=4;
	private boolean endSig(List<Byte> upstreamBuf)
	{
		if(upstreamBuf.size()<END_SIG_TIMES)
			return false;
		for(int i=0; i < END_SIG_TIMES; i++)
		{
			if(upstreamBuf.get(upstreamBuf.size()-1-i)!=END_SIG)
				return false;
		}
		return true;
	}
	private void processMessage(List<Byte> bytes)
	{
		if(bytes.size()!=2+M_SIZE_BYTES+END_SIG_TIMES)//SENSOR_TYPE_CODE,SENSOR_INDEX,VALUExM_SIZE,END_SIGxEND_SIG_TIMES
			return;
		byte sensorTypeB=bytes.get(0);
		byte sensorIndexB=bytes.get(1);
		byte[] message=new byte[4];
		for(int i =0; i <4 ; i++)
			message[i]=bytes.get(2+i);
	}
	public class ReadCycle extends Thread
	{
		public static final int SLEEP_TIME_MILLIS=10;
		public ReadCycle()
		{
			
		}
		
		public void run()
		{
			SerialPort comPort = SerialPort.getCommPorts()[0];
			comPort.openPort();
			try {
			   while (true)
			   {
			      while (comPort.bytesAvailable() == 0)
			         Thread.sleep(SLEEP_TIME_MILLIS);

			      byte[] readBuffer = new byte[comPort.bytesAvailable()];
			      int numRead = comPort.readBytes(readBuffer, readBuffer.length);
			      process(readBuffer);
			   }
			} catch (Exception e) { e.printStackTrace(); }
			comPort.closePort();
		}
	}
}
