package com.erik.GreenThink.Demos;

public abstract class FilteredVar
{
	public final int LEN;
	CycleArray<Double> vals;
	public FilteredVar(int len)
	{
		LEN=len;
		vals=new CycleArray<>(new Double[LEN]);
	}
	public FilteredVar()
	{
		this(100);
	}
	
	public void putValue(double val) {
		vals.add(val);
	}
	public abstract double read();
	
	
	public class MeanFilter extends FilteredVar
	{
		@Override
		public double read() {
			double avg=0;
			for(double v:vals.getUnorderedArray())
			{
				avg+=v;
			}
			return avg/LEN;
		}
	}
	public class NoOutlierMeanFilter extends FilteredVar
	{
		@Override
		public double read() {
			double avg=0;
			for(double v:vals.getUnorderedArray())
			{
				avg+=v;
			}
			return avg/LEN;
		}
	}
	
	public class CycleArray<E extends Number>
	{
		int len;
		E[] array;
		int loc=0;
		
		public CycleArray(E[] backing)
		{
			this.len=backing.length;
			array=backing;
		}
		
		public void add(E e) {
			array[loc]=e;
			loc++;
			loc%=len;
		}
		
		public E get(int pos)
		{
			int x=(pos-loc);
			if(x<0)
				x+=len;
			return array[x];
		}
		
		public E[] getUnorderedArray()
		{
			return array;
		}
	}
}