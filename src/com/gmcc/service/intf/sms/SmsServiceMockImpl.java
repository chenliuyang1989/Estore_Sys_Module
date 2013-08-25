package com.gmcc.service.intf.sms;

import org.apache.log4j.Logger;

/**
 * <p>Name: SmsServiceMockImpl </p>
 * <p>Description: This is a mock implementation of the <code>SmsService</code></p>
 * 
 * @author Paul Yu
 * @version $Revision: 1.1.2.1 $ Nov 3, 2009
 */
public class SmsServiceMockImpl implements SmsService {
	
	private static final Logger logger = Logger.getLogger(SmsServiceMockImpl.class);

	/**
	 * @see com.gmcc.service.intf.sms.commerce.intf.sms.SmsService#sendMsg(java.lang.String, java.lang.String, long)
	 */
	public boolean sendMsg(String destAddr, String msg, long timeToWait, String encoding) {

		return true;
	}

	/**
	 * @see com.gmcc.service.intf.sms.commerce.intf.sms.SmsService#sendMsg(java.lang.String, java.lang.String)
	 */
	public boolean sendMsg(String destAddr, String msg, String encoding) {

		return true;
	}

}
