package com.erik.GreenThink.Demos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public abstract class FilteredVar
{
	public final int LEN;
	CycleArray<Double> vals;
	public FilteredVar(int len)
	{
		LEN=len;
		vals=new CycleArray<>(LEN);
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
			for(double v:vals.vals())
			{
				avg+=v;
			}
			return avg/LEN;
		}
	}
	public class NoOutlierMeanFilter extends FilteredVar
	{
		double percent=0;
		public NoOutlierMeanFilter(int len, double percent)
		{
			super(len);
			this.percent=percent;
		}
		@Override
		public double read() {
			List<Double> l = vals.sorted();
			double avg=0;
			int cut=(int)(l.size()*percent);
			int n=0;
			for(int i=cut; i < l.size()-cut; i++)
			{
				avg+=l.get(i);
				n++;
			}
			return avg/n;
		}
	}
	
	public class CycleArray<E extends Comparable<? super E>>
	{
		int len;
		E[] array;
		LinkedList<E> queue;
		
		public CycleArray(int len)
		{
			this.len=len;
			queue=new LinkedList<>();
			for(int i = 0; i < len; i++)
				queue.add(null);
		}
		
		public List<E> vals() {
			return queue;
		}

		public void add(E e) {
			queue.poll();
			queue.add(e);
		}
		
		public E get(int pos)
		{
			return queue.get(pos);
		}
		//TODO make faster. keep l and keep sorted.
		public List<E> sorted()
		{
			List<E> l = new ArrayList<>();
			l.addAll(queue);
			Collections.sort(l);
			return l;
		}
	}
}