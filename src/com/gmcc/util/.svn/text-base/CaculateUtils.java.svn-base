package com.gmcc.util;

import java.math.BigDecimal;

public class CaculateUtils {
	private static final int DEF_DIV_SCALE = 3;
	
	
	public static boolean isValidStr(String str) {
		if (str == null || str.equalsIgnoreCase("")	|| str.equalsIgnoreCase("null")) {
			return false;
		} else {
			return true;
		}
	}
	
	public static double doubleFormat(double d, String style) {
		if (!isValidStr(style)) {
			style = "#.##";
		}
		java.text.DecimalFormat df = new java.text.DecimalFormat(style);
		return Double.parseDouble(df.format(d));
	}
	
	public static double doubleFormat(BigDecimal d, String style) {
		if (!isValidStr(style)) {
			style = "#.##";
		}
		java.text.DecimalFormat df = new java.text.DecimalFormat(style);
		return Double.parseDouble(df.format(d));
	}
	
	/**
	 * 
	 * @param v1
	 * @param v2
	 * @return Double
	 */
	public static Double add(Double v1, Double v2) {
		BigDecimal b1 = new BigDecimal(v1.toString());
		BigDecimal b2 = new BigDecimal(v2.toString());
		BigDecimal b3 = b1.add(b2);		
		return b3.doubleValue();
	}

	/**
	 * 
	 * @param v1
	 * @param v2
	 * @return Double
	 */
	public static Double add(Double v1, Double v2, String style) {
		BigDecimal b1 = new BigDecimal(v1.toString());
		BigDecimal b2 = new BigDecimal(v2.toString());
		BigDecimal b3 = b1.add(b2);
		return doubleFormat(b3, style);
	}
	
	/**
	 * 
	 * @param v1
	 * @param v2
	 * @return Double
	 */
	public static Double sub(Double v1, Double v2) {
		BigDecimal b1 = new BigDecimal(v1.toString());
		BigDecimal b2 = new BigDecimal(v2.toString());
		BigDecimal b3 = b1.subtract(b2);		
		return b3.doubleValue();
	}

	/**
	 * 
	 * @param v1
	 * @param v2
	 * @return Double
	 */
	public static Double sub(Double v1, Double v2, String style) {
		BigDecimal b1 = new BigDecimal(v1.toString());
		BigDecimal b2 = new BigDecimal(v2.toString());
		BigDecimal b3 = b1.subtract(b2);
		return doubleFormat(b3, style);
	}
	
	/**
	 * 
	 * @param v1
	 * @param v2
	 * @return Double
	 */
	public static Double mul(Double v1, Double v2) {
		BigDecimal b1 = new BigDecimal(v1.toString());
		BigDecimal b2 = new BigDecimal(v2.toString());
		BigDecimal b3 = b1.multiply(b2);		
		return b3.doubleValue();
	}

	/**
	 * 
	 * @param v1
	 * @param v2
	 * @return Double
	 */
	public static Double mul(Double v1, Double v2, String style) {
		BigDecimal b1 = new BigDecimal(v1.toString());
		BigDecimal b2 = new BigDecimal(v2.toString());
		BigDecimal b3 = b1.multiply(b2);
		return doubleFormat(b3, style);
	}
	
	/**
	 * 
	 * @param v1
	 * @param v2
	 * @return Double
	 */
	public static Double div(Double v1, Double v2) {
		BigDecimal b1 = new BigDecimal(v1.toString());
		BigDecimal b2 = new BigDecimal(v2.toString());
		BigDecimal b3 = b1.divide(b2, DEF_DIV_SCALE, BigDecimal.ROUND_FLOOR);		
		return b3.doubleValue();
	}

	/**
	 * 
	 * @param v1
	 * @param v2
	 * @return Double
	 */
	public static Double div(Double v1, Double v2, String style) {
		BigDecimal b1 = new BigDecimal(v1.toString());
		BigDecimal b2 = new BigDecimal(v2.toString());
		BigDecimal b3 = b1.divide(b2, DEF_DIV_SCALE, BigDecimal.ROUND_FLOOR);
		return doubleFormat(b3, style);
	}

	/**
	 * 
	 * @param v1
	 * @param v2
	 * @param scale
	 * @return Double
	 */
	public static Double div(Double v1, Double v2, int scale, String style) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(v1.toString());
		BigDecimal b2 = new BigDecimal(v2.toString());
		BigDecimal b3 = b1.divide(b2, scale, scale);
		return doubleFormat(b3, style);
	}
	
	/**	 
	 * 
	 * @param v1
	 * @param v2
	 * @return if v1 > v2 1
	 * @return if v1 = v2 0
	 * @return if v1 < v2 -1
	 */
	public static int compareTo(Double v1, Double v2) {			
		return v1.compareTo(v2);
	}

}
