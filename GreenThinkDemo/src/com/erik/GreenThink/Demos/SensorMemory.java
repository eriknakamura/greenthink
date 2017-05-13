package com.erik.GreenThink.Demos;

public class SensorMemory {
	int number;
	FilteredVar[] values;
	
	public double avg()
	{
		double avg=0;
		for(FilteredVar v:values)
		{
			avg+=v.read();
		}
		return avg;
	}
}
