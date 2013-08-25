/*
 * Created on 2009-9-25
 *
 * 
 */
package com.gmcc.service.intf.sms;




//import com.ibm.commerce.ext.registry.ECSystemConfigRegistry;

/**
 * @author guoyuhua
 *  
 */

public class SmsSender implements SmsService {

	public String defaultIspCode = null;
	
	private String svcnum;
	
	private SConfig sConfig;
	
	public SmsSender() {
		//defaultIspCode = "10086100|10.243.255.71:5000;";
		sConfig = new SmsConfig();
		this.svcnum = sConfig.getSvcnum();
		defaultIspCode = this.svcnum;
		int index = defaultIspCode.indexOf("|");
		if (index > 0) {
			defaultIspCode = defaultIspCode.substring(0, index);
		}
	}

	/**
	 * Return the defaultIspCode
	 * 
	 * @return
	 */
	private String getDefaultIspCode() {
		return defaultIspCode;
	}

	
	
	/**
	 * ���Ͷ���
	 * 
	 * @param destAddr
	 *            �����ֻ����
	 * @param msg
	 *            �������ݣ�����Ϊ���ַ�
	 * @param timeToWait
	 *            �ȴ��ͽ���ʱ�䣬�Ժ���Ϊ��λ����Ϊ0�����ʾ���ȴ��豣֤���ŷ��ͳɹ���������
	 *            �ֻ������ȷ�Ļ�������Ϊ0��ʾָ���ȴ��ʱ�䣬���ȴ�ʱ�䵽��������Ϊ���������ٷ��ͣ�ֱ�ӷ��ط���ʧ�ܵĽ��
	 * @param encoding the encoding of msg, if empty then default to <code>DEFAULT_SMS_ENCODING</code>
	 * @return ���ͽ��true��ʾ���ͳɹ���false��ʾ����ʧ��
	 */
	public boolean sendMsg(String destAddr, String msg, long timeToWait, String encoding) {
		//String str = "10086100|10.243.255.71:5000;";
		//String str = ECSystemConfigRegistry.getInstance().getValue(
		//		"smsConfig/svcnum");
		
		String str = this.svcnum;

		String ip = str.substring(str.indexOf("|") + 1, str.lastIndexOf(":"));
		String port = str.substring(str.lastIndexOf(":") + 1);
		if (encoding == null || "".equals(encoding)) {
			encoding = DEFAULT_SMS_ENCODING;
		}
		if (timeToWait == 0)
			return new SmSendQueue(ip, Integer.parseInt(port)).sendMsg(
					getDefaultIspCode(), destAddr, msg, 0, 1, 0, "0002", "", encoding);
		else
			return new SmSendQueue(ip, Integer.parseInt(port)).sendMsg(
					getDefaultIspCode(), destAddr, msg, timeToWait, 1, 0,
					"0002", "", encoding);
	}

	/**
	 * ���Ͷ���
	 * 
	 * @param destAddr
	 *            �����ֻ����
	 * @param msg
	 *            �������ݣ�����Ϊ���ַ�
	 * @param timeToWait
	 *            �ȴ��ͽ���ʱ�䣬�Ժ���Ϊ��λ����Ϊ0�����ʾ���ȴ��豣֤���ŷ��ͳɹ���������
	 *            �ֻ������ȷ�Ļ�������Ϊ0��ʾָ���ȴ��ʱ�䣬���ȴ�ʱ�䵽��������Ϊ���������ٷ��ͣ�ֱ�ӷ��ط���ʧ�ܵĽ��
	 * @return ���ͽ��true��ʾ���ͳɹ���false��ʾ����ʧ��
	 * 
	 * public static boolean send10086Msg( String destAddr, String msg, long
	 * timeToWait) { if (timeToWait == 0) return SmSendQueue.submitMsg("10086",
	 * destAddr, msg); else return SmSendQueue.submitMsg("10086", destAddr, msg,
	 * timeToWait); }
	 *  
	 */

	/**
	 * ���Ͷ��ţ����ȴ��ͽ����Ҫ��֤���ͳɹ����������ֻ������ȷ�Ļ���
	 * 
	 * @param destAddr
	 *            �����ֻ����
	 * @param msg
	 *            �������ݣ�����Ϊ���ַ�
	 * @param encoding the encoding of msg, if empty then default to <code>DEFAULT_SMS_ENCODING</code>
	 * @return ���ͽ��true��ʾ���ͳɹ���false��ʾ����ʧ��
	 */
	public boolean sendMsg(String destAddr, String msg, String encoding) {
		return sendMsg(destAddr, msg, 0, encoding);
	}

	public static void main(String[] argv) {
		SmsSender ss = new SmsSender();
		ss.sendMsg("13423658723", "test from estore", "UTF-8");
	}
	/**
	 * @return Returns the svcnum.
	 */
	public String getSvcnum() {
		return svcnum;
	}
	/**
	 * @param svcnum The svcnum to set.
	 */
	public void setSvcnum(String svcnum) {
		this.svcnum = svcnum;
	}
}
