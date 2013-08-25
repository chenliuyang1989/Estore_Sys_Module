package com.gmcc.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static Date strToDate(String str, String formatStr)throws Exception{
		SimpleDateFormat sdf =   new SimpleDateFormat(formatStr);
		return sdf.parse(str);
	}
	public static String dateToStr(Date date,String formatStr)throws Exception{
		SimpleDateFormat sdf =   new SimpleDateFormat(formatStr);
		return sdf.format(date);
	}
	
}
