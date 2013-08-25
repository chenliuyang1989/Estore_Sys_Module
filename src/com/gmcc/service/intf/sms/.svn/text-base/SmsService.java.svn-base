package com.gmcc.service.intf.sms;

/**
 * <p>
 * Name: SmsService
 * </p>
 * <p>
 * Description: All SMS related service
 * </p>
 * 
 * @author Paul Yu
 * @version $Revision: 1.1.2.1 $ Nov 3, 2009
 */
public interface SmsService {

	public static final String DEFAULT_SMS_ENCODING = "GBK";

	/**
	 * Send SMS message
	 * @param destAddr
	 * @param msg
	 * @param timeToWait
	 * @param encoding
	 * @return
	 */
	boolean sendMsg(String destAddr, String msg, long timeToWait, String encoding);

	/**
	 * Send SMS message
	 * @param destAddr
	 * @param msg
	 * @param encoding
	 * @return
	 */
	boolean sendMsg(String destAddr, String msg, String encoding);
}
