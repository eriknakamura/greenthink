package com.erik.GreenThink.Demos;

public class DripLockManager {
	boolean inDripLockCycle;
	double lockEndTime;//in secounds
	double dripEndTime;
	
	boolean outputUp;//true if actulator increases value, false if it decreases value
	
	final double THRESHOLD;
	final double CYCLE_LEN;//seconds
	final double DRIP_LEN;//secound
	
	public DripLockManager(double threshold)
	{
		this.THRESHOLD=threshold;
		this.CYCLE_LEN=60*1.5;//seconds
		this.DRIP_LEN=4;
	}
	public DripLockManager(double threshold, double cycleLen, double dripLen)
	{
		this.THRESHOLD=threshold;
		this.CYCLE_LEN=cycleLen;
		this.DRIP_LEN=dripLen;
	}
	
	public boolean getShouldBeOn(Memory mem) {
		double time=((double)System.currentTimeMillis())/1000d;
		if(inDripLockCycle)
		{
			if(time<dripEndTime)
				return true;
			else if(time<lockEndTime)
				return false;
			else
				inDripLockCycle=false;
		}
		//not in drip lock cycle
		if(!outputUp&&mem.pHSensor.avg()>THRESHOLD)
		{
			inDripLockCycle=true;
			lockEndTime=time+CYCLE_LEN;
			dripEndTime=time+DRIP_LEN;
			return true;
		}
		if(outputUp&&mem.pHSensor.avg()<THRESHOLD)
		{
			inDripLockCycle=true;
			lockEndTime=time+CYCLE_LEN;
			dripEndTime=time+DRIP_LEN;
			return true;
		}
		else return false;
	}

}
