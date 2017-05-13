package com.erik.GreenThink.Demos;

public class Brain
{
	AurduinoCommInterface io;
	Memory mem;
	public Brain(AurduinoCommInterface io)
	{
		mem = new Memory(io);
	}
	
	public void wake(double d) {
		mem.readAll();
	}
	
	
	public class WakeCycle extends Thread
	{
		public static final int SLEEP_TIME_MILLIS=100;
		Brain brain;
		public WakeCycle(Brain b)
		{
			brain=b;
		}
		
		public void run()
		{
			double lastT=System.currentTimeMillis();
			while(true)
			{
				try {
					Thread.sleep(SLEEP_TIME_MILLIS);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				double time=System.currentTimeMillis();
				brain.wake((double)(time-lastT)/1000.0);
				lastT=System.currentTimeMillis();
			}
		}
	}
}
