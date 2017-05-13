package com.erik.GreenThink.Demos;

public abstract class FilteredVar
{
	public abstract void putValue(double val);
	public abstract double read();
	
	public class NoOutlierMeanFilter extends FilteredVar
	{
		public NoOutlierMeanFilter()
		{
			
		}
		
		@Override
		public void putValue(double val) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public double read() {
			// TODO Auto-generated method stub
			return 0;
		}
		
	}
}
/*
x_k=F_k*x_{k-1}+B_k*u_k+w_k
w_k=N(0,Q_k)
z_k=H_k*x_k+v_k
v_k=N(0,R_k)



x
 */