/*
 * Created on 2010-6-28
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.gmcc.service.intf.sms;

import java.io.IOException;
import java.util.Properties;


/**
 * @author jacker
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SmsConfig extends SConfig{
	
	public SmsConfig() {
		load();
	}

	protected void load() {
		Properties p = new Properties();
		try {
			p.load(this.getClass().getResourceAsStream("smsConfig.cfg"));
			svcnum = p.getProperty("svcnum", SVCNUM);
			useMock = p.getProperty("useMock", USEMOCK);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		SConfig conf = new SmsConfig();
		System.out.println(conf.getSvcnum());
		System.out.println(conf.getUseMock());
	}
}
