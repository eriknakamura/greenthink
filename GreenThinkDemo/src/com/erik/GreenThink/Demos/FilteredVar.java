package com.erik.GreenThink.Demos;

public abstract class FilteredVar
{
	public abstract void putValue(double val);
	public abstract double read();
	
	public class MeanFilter extends FilteredVar
	{
		int LEN=100;
		double[] vals;
		public MeanFilter()
		{
			vals=new double[LEN];
		}
		
		@Override
		public void putValue(double val) {
			double[] nvals= new double[LEN];
			System.arraycopy(vals, 0, nvals, 1, 99);
			nvals[0]=val;
			vals=nvals;
		}

		@Override
		public double read() {
			double avg=0;
			for(double v:vals)
			{
				avg+=v;
			}
			return avg/LEN;
		}
	}
}