package com.erik.GreenThink.Demos;

public class Utils {
	public static double bytesToDouble(byte[] arr)
	{
		//convert 8 byte array to double
		int start=0;//???
		int i = 0;
		int len = 8;
		int cnt = 0;
		byte[] tmp = new byte[len];
		for (i = start; i < (start + len); i++) {
			tmp[cnt] = arr[i];
		    //System.out.println(java.lang.Byte.toString(arr[i]) + " " + i);
		    cnt++;
		}
		long accum = 0;
		i = 0;
		for ( int shiftBy = 0; shiftBy < 64; shiftBy += 8 ) {
		    accum |= ( (long)( tmp[i] & 0xff ) ) << shiftBy;
		    i++;
		}
		
		return Double.longBitsToDouble(accum);
	}
	public static int bytesToInt(byte[] b) 
	{
	    return   b[3] & 0xFF |
	            (b[2] & 0xFF) << 8 |
	            (b[1] & 0xFF) << 16 |
	            (b[0] & 0xFF) << 24;
	}
}
