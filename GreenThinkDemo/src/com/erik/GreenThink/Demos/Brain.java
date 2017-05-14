package com.erik.GreenThink.Demos;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Brain
{
	public static final double MAX_HUMIDITY=80;
	public static final double MIN_HUMIDITY=60;
	
	public static final double MAX_TEMP=80;
	public static final double MIN_TEMP_AC=60;
	
	public static final double MIN_TEMP_LIGHTS_ON=40;
	
	public static final double TIME_LIGHTS_ON_HOURS=3.25;//3:15am
	public static final double TIME_LIGHTS_OFF_HOURS=12+9.5;//9:30pm

	public static final double TIME_FANS_CYCLE_LEN=3.25;//5 minutes
	public static final double TIME_FANS_ON_LEN=1;//1 minute
	
	DripLockManager pHDown;
	DripLockManager nutreants;
	
	AurduinoCommInterface io;
	Memory mem;
	public Brain(AurduinoCommInterface io)
	{
		mem = new Memory(io);
		(new WakeCycle(this)).start();
	}
	
	public void wake(double dt) {
		mem.readAll(dt);
		
		boolean DHShouldBeOn=humidifyShouldBeOn();
		boolean ACShouldBeOn=ACShouldBeOn();
		boolean LightsShouldBeOn=LightsShouldBeOn();
		boolean FansShouldBeOn=FanShouldBeOn();
		
		mem.setDesiredStatesIfNeeded(DHShouldBeOn,ACShouldBeOn,LightsShouldBeOn,FansShouldBeOn
				,pHDown.getShouldBeOn(mem),nutreants.getShouldBeOn(mem));
	}
	
	public boolean humidifyShouldBeOn()
	{
		if(mem.humiditySensors.avg()>MAX_HUMIDITY)
			return true;
		else if (mem.humiditySensors.avg()<MIN_HUMIDITY)
			return false;
		else
			return mem.getDehumidiferIsOn();
	}
	
	public boolean ACShouldBeOn()
	{
		if(mem.humiditySensors.avg()>MAX_TEMP)
			return true;
		else if (mem.humiditySensors.avg()<MIN_TEMP_AC)
			return false;
		else
			return mem.getACIsOn();
	}
	
	public boolean LightsShouldBeOn()
	{
		Date date = new Date();   // given date
		Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
		calendar.setTime(date);   // assigns calendar to given date 
		double hour = (double)calendar.get(Calendar.HOUR_OF_DAY)+(double)calendar.get(Calendar.MINUTE)/60.0
				+(double)calendar.get(Calendar.SECOND)/3600.0; // gets hour in 24h format
		
		if(mem.tempSensors.avg()<MIN_TEMP_LIGHTS_ON)
			return true;
		else if (TIME_LIGHTS_ON_HOURS<hour && hour<TIME_LIGHTS_OFF_HOURS)
			return true;
		else
			return false;
	}
	
	public boolean FanShouldBeOn()
	{
		Date date = new Date();   // given date
		Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
		calendar.setTime(date);   // assigns calendar to given date 
		double minute = (double)calendar.get(Calendar.HOUR_OF_DAY)*60.0
				+(double)calendar.get(Calendar.MINUTE)+(double)calendar.get(Calendar.MINUTE)
				+(double)calendar.get(Calendar.SECOND)/60.0;
		
		if(mem.getDehumidiferIsOn()||mem.getACIsOn())
			return true;
		if(minute%TIME_FANS_CYCLE_LEN <TIME_FANS_ON_LEN)
			return true;
		else
			return false;
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
