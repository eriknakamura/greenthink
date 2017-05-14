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
	/**
	 * @return maxDiff/avg
	 */
	public double maxRelDiff()
	{
		return maxDiff()/avg();
	}
	
	public double maxDiff()
	{
		double max=Double.MIN_VALUE;
		double min=Double.MAX_VALUE;
		for(FilteredVar v:values)
		{
			max=Double.max(max, v.read());
			min=Double.min(min, v.read());
		}
		return max-min;
	}
}
