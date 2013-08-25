/**
 * 
 */
package com.gmcc.service.impl.sms;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.gmcc.dao.sms.SMSSendDAO;
import com.gmcc.model.SMSSendQueue;
import com.gmcc.service.intf.sms.SmsSender;
import com.gmcc.service.sms.SMSSender;
import com.gmcc.service.sms.SMSTiming;

/**
 * @author Xiaoming Niu
 *
 */
public class SMSSenderImpl implements SMSSender {
	
	private SMSSendDAO smsSendDao;

	public boolean sendMsg(String phoneNum, String content,
			SMSTiming sMsTiming, String times, String days, Date designateTime) throws Exception {
		
		valid(phoneNum, content, sMsTiming, times, days, designateTime);
		
		if (sMsTiming.equals(SMSTiming.IMMEDIATE)) {
			SmsSender smsSender = new SmsSender();
			boolean rest = smsSender.sendMsg(phoneNum, content, "GBK");
			saveMsgToQueue(phoneNum, content, sMsTiming, times, days, designateTime, "1");
			return rest;
		} else {
			return saveMsgToQueue(phoneNum, content, sMsTiming, times, days, designateTime, "0");
		}
	}

	private void valid(String phoneNum, String content, SMSTiming sMsTiming,
			String times, String days, Date designateTime) throws Exception {

		if (StringUtils.isBlank(phoneNum) || StringUtils.isBlank(content)) {
			throw new Exception("Phone number and content cann't be blank");
		}
		
		Pattern p = Pattern.compile("\\d{11}");
		Matcher m = p.matcher(phoneNum);
		if (!m.matches()) {
			throw new Exception("Phone number is invalid");
		}
		
		if (sMsTiming.equals(SMSTiming.CUSTOM)) {
			if (StringUtils.isBlank(times) && designateTime == null) {
				throw new Exception("times and designateTime cann't both be blank in case of SMSTiming is CUSTOM");
			}
			
			if (designateTime != null) {
				Calendar c = Calendar.getInstance();
				c.setTime(designateTime);
				if (c.before(Calendar.getInstance()) || c.equals(Calendar.getInstance())) {
					throw new Exception("designateTime must be a future time");
				}
			}
		}
		
	}

	private boolean saveMsgToQueue(String phoneNum, String content,
			SMSTiming sMsTiming, String times, String days, Date designateTime, String dealStatus) throws Exception {
		
		String category = String.valueOf(sMsTiming.ordinal());
		SMSSendQueue queue = new SMSSendQueue(phoneNum, content, category, times, days, designateTime, dealStatus);
		smsSendDao.save(queue);
		return true;
	}

	public SMSSendDAO getSmsSendDao() {
		return smsSendDao;
	}

	public void setSmsSendDao(SMSSendDAO smsSendDao) {
		this.smsSendDao = smsSendDao;
		this.smsSendDao.setEntityClass(SMSSendQueue.class);
		this.smsSendDao.setPKClass(Long.class);
	}
	
	

}
