package com.gmcc.service.intf.sms;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import org.apache.log4j.Logger;

/**
 * 短信发送队列客户端应用接口
 * <p>
 * 例子：
 * <p>
 * try{ SmSendQueue queue = new SmSendQueue("10.185.15.68", 8282);
 * <p>
 * String dest="接收短信的手机号";
 * <p>
 * boolean ret=queue.sendMsg("01350",dest,"你好！"
 * ,(long)5000,3,1,"test","13707871966"); if(ret) System.out.println("SMS has
 * been send successfully."); else System.out.println("Fail to send SMS");
 * <p>
 * }catch(SMGateWatConnectException se){ <br>
 * //error message <br>} finally{
 * <p>
 * queue.close();
 * </p>}
 * <p>
 * Creation date: 4/27/2001
 * 
 * @author: Jim, Yang Jiang Ming * 修改纪录： 日期 原因 修改内容 修改人
 *          --------------------------------------------------------------------------------------------------
 *          2003/11/12 新短信服务器需要能够自己定义收费模式和费用 添加方法:send 邓明 sendMsg submitMsg
 *          2005/4/29 修改可以获取返回 YeGuoHua 代码 ，增加方法：getRtnCode()
 *          --------------------------------------------------------------------------------------------------
 */
public class SmSendQueue {
	private static final Logger logger = Logger.getLogger(SmSendQueue.class);

	String nRtnCode = "0";

	/** 缺省发送服务器端口号 */
	static final int SMSENDER_PORT = 8289;

	private boolean isConnect = false;

	String m_IP = null;

	int m_PORT = 0;

	Socket m_sk = null;

	InputStream m_is = null;

	OutputStream m_os = null;

	/**
	 * 构造函数：连接指定的发送服务器
	 */
	public SmSendQueue(String ip, int port) {
		if (connect(ip, port)) {
			isConnect = true;
		} else {
			logger.error("没有连接上SMS Socket Server，isConnect is false");
		}
	}

	/**
	 * 连接指定的服务器
	 */
	private boolean connect(String ip, int port) {
		close();
		m_IP = ip;
		m_PORT = port;
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("开始连接SMS Socket Server");
			}
			if (null != m_IP) {
				m_IP = m_IP.trim();
			}
			m_sk = new Socket(m_IP, m_PORT);
			m_os = m_sk.getOutputStream();
			m_is = m_sk.getInputStream();
		} catch (Exception e) {
			logger.error(Thread.currentThread().getName()
					+ "-Can't connect to SMS GateWay" + m_IP + " Port:"
					+ m_PORT + " error message:" + e.getMessage(), e);
			return false;
		}
		return true;
	}

	/**
	 * 关闭服务器连接
	 */
	public void close() {
		if (m_os != null) {
			try {
				if (logger.isDebugEnabled()) {
					logger.debug("开始关闭 SMS Socket Server");
				}
				m_os.close();
				if (logger.isDebugEnabled()) {
					logger.debug("已经关闭 SMS Socket Server");
				}
			} catch (Exception e) {
				logger.error("Exception in close()", e);
			}
			m_os = null;
		}
		if (m_is != null) {
			try {
				m_is.close();
			} catch (Exception e) {
				logger.error("关闭服务器连接出现错误！", e);
			}
			m_is = null;
		}
		if (m_sk != null) {
			try {
				m_sk.close();
			} catch (Exception e) {
				logger.error("关闭服务器连接出现错误！", e);
			}
			m_sk = null;
		}
	}

	/**
	 * 向内存队列提交短信发送请求 添加费用和收费模式，业务代码和收费终端号四个参数
	 * 
	 * @param src
	 *            String 源号码
	 * @param desc
	 *            String 目的号码
	 * @param msg
	 *            String 要发送的短信
	 * @param timeToWait
	 *            String 等待发送结果时间，0表示不等待发送结果
	 * @param queueName
	 *            队列名称
	 * @param feeType
	 *            收费模式
	 * @param fee
	 *            费用
	 * @param encoding encoding of <code>msg</code>
	 */
	private void send2SocketServer(String src, String dest, String msg,
			String timeToWait, String queueName, String feeType, String fee,
			String serviceID, String feeTermNO, String encoding) throws Exception {

		byte[] b1 = { 0x32, 0 };
		m_os.write(b1);
		m_os.write(src.getBytes());
		m_os.write(0);
		m_os.write(dest.getBytes());
		m_os.write(0);
		if (encoding == null || "".equals(encoding)) {
			// use platform default encoding, not recommended
			m_os.write(msg.getBytes());
		} else {
			m_os.write(msg.getBytes(encoding));
		}
		m_os.write(0);
		m_os.write(timeToWait.getBytes());
		m_os.write(0);
		m_os.write(queueName.getBytes());
		m_os.write(0);
		m_os.write(feeType.getBytes());
		m_os.write(0);
		m_os.write(fee.getBytes());
		m_os.write(0);
		m_os.write(serviceID.getBytes());
		m_os.write(0);
		m_os.write(feeTermNO.getBytes());
		m_os.write(0);
		m_os.flush();
	}

	/**
	 * 向数据库队列提交短信发送请求
	 * 
	 * @param src
	 *            String 源号码
	 * @param desc
	 *            String 目的号码
	 * @param msg
	 *            String 要发送的短信
	 * @param startTime
	 *            String 起始发送时间
	 * @param toTime
	 *            String 终止发送时间
	 * @param queueName
	 *            队列名称
	 */
	private void send2SocketServerOverDB(String src, String dest, String msg,
			String startTime, String toTime, String queueName, String encoding) throws Exception {
		byte[] b1 = { 0x31, 0 };

		m_os.write(b1);
		m_os.write(src.getBytes());
		m_os.write(0);
		m_os.write(dest.getBytes());
		m_os.write(0);
		if (encoding == null || "".equals(encoding)) {
			// use platform default encoding, not recommended
			m_os.write(msg.getBytes());
		} else {
			m_os.write(msg.getBytes(encoding));
		}
		m_os.write(0);
		m_os.write(startTime.getBytes());
		m_os.write(0);
		m_os.write(toTime.getBytes());
		m_os.write(0);
		m_os.write(queueName.getBytes());
		m_os.write(0);
		m_os.flush();
	}

	/**
	 * 获取发送结果
	 */
	private boolean isSuccess() throws Exception {
		boolean bSuccess;

		byte[] b = new byte[100];
		int i = 0;
		int c;
		while ((c = m_is.read()) != 0 && i<100)
			b[i++] = (byte) c;
		nRtnCode = new String(b, 0, i);
		if ("0".equals(nRtnCode))
			bSuccess = true;
		else {
			bSuccess = false;
		}

		/*i = 0;
		while ((c = m_is.read()) != 0)
			b[i++] = (byte) c;*/

		return bSuccess;
	}

	/**
	 * 向指定的内存队列提交短信发送，等待发送结果。 添加收费方式和费用参数
	 * 
	 * @param sourceAddr
	 *            发送手机源地址,目前请输入01350
	 * @param destAddr
	 *            目标手机地址
	 * @param msg
	 *            短信信息
	 * @param timeToWait
	 *            等待时间，以微秒为单位
	 * @param queueName
	 *            发送的队列名称，缺省为空
	 * @param feeMode
	 *            收费模式 0，不收费；1，按条接收方收费；2，按条发送方收费；3，收取指定feeTermID方费用
	 * @param fee
	 *            费用，以分为单位
	 * @param serviceID
	 *            业务代码，参见梦网说明
	 * @param feeTermNO
	 *            模式3下的收费方 ,输入发送的收机号码
	 * @return boolean true：为发送成功，false:失败
	 */
	private boolean subMsg(String sourceAddr, String destAddr, String msg,
			long timeToWait, String queueName, int feeMode, int fee,
			String serviceID, String feeTermNO, String encoding) {

		try {

			synchronized (m_sk) {
				send2SocketServer(sourceAddr, destAddr, msg, new Long(
						timeToWait).toString(), queueName, new Integer(feeMode)
						.toString(), new Integer(fee).toString(), serviceID,
						feeTermNO, encoding);
				return isSuccess();
			}
		} catch (Throwable e) {
			logger.error("错误：", e);
			return false;
		}
	}

	/**
	 * 向指定的内存队列提交短信发送，等待发送结果。 添加收费方式和费用参数
	 * 
	 * @param sourceAddr
	 *            发送手机源地址,目前请输入01350
	 * @param destAddr
	 *            目标手机地址
	 * @param msg
	 *            短信信息
	 * @param timeToWait
	 *            等待时间，以微秒为单位
	 * @param feeType
	 *            收费模式 0，不收费；1，按条接收方收费；2，按条发送方收费；3，收取指定feeTermID方费用
	 * @param fee
	 *            费用，以分为单位
	 * @param serviceID
	 *            业务代码，参见梦网说明
	 * @param feeTermNO
	 *            模式3下的收费方 ,输入发送的收机号码
	 * @return boolean true：为发送成功，false:失败
	 */
	public boolean sendMsg(String sourceAddr, String destAddr, String msg,
			long timeToWait, int feeType, int fee, String serviceID,
			String feeTermNO, String encoding) {
		if (!isConnect) {
			logger.error("没有连接上发送Socket服务器");
			return false;
		}
		if (null == sourceAddr || "".equalsIgnoreCase(sourceAddr)) {
			logger.error("发送手机为空");
			return false;
		}
		if (null == destAddr || "".equalsIgnoreCase(destAddr)) {
			logger.error("目标手机号码为空");
			return false;
		}
		if (null == msg || "".equalsIgnoreCase(msg)) {
			logger.error("发送内容为空");
			return false;
		}
		if (null == serviceID || "".equalsIgnoreCase(serviceID)) {
			logger.error("服务号为空");
			return false;
		}
		if ((3 == feeType)
				&& (null == feeTermNO || "".equalsIgnoreCase(feeTermNO))) {
			logger.error("在指定feeTermID为３的收取费用模式下，发送手机号码为空");
			return false;
		}
		try {
			String queueName = "MEMSVC";//服务器配置固定的名称,发送的队列名称，缺省为空
			synchronized (m_sk) {
				send2SocketServer(sourceAddr, destAddr, msg, new Long(
						timeToWait).toString(), queueName, new Integer(feeType)
						.toString(), new Integer(fee).toString(), serviceID,
						feeTermNO, encoding);
				return isSuccess();
			}
		} catch (Exception e) {
			logger.error("数据传送出错", e);
			return false;
		}
	}

	/**
	 * 向数据库短信队列提交短信发送（确保发送）
	 * 
	 * @param sourceAddr
	 *            源地址
	 * @param destAddr
	 *            目标地址
	 * @param msg
	 *            信息内容
	 * @param startTime
	 *            短信发送开始时间
	 * @param toTime
	 *            短信发送结束时间
	 * @param queueName
	 *            队列名称
	 * @return boolean true为发送成功
	 */

	public boolean sendMsgOverDB(String sourceAddr, String destAddr,
			String msg, int startTime, int toTime, String queueName, String encoding) {
		if (queueName == null)
			queueName = "";
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("Starting send message...");
			}
			synchronized (m_sk) {
				send2SocketServerOverDB(sourceAddr, destAddr, msg, new Integer(
						startTime).toString(), new Integer(toTime).toString(),
						queueName, encoding);
				if (logger.isDebugEnabled()) {
					logger.debug("Sending message finished...");
				}
				
				return isSuccess();
			}
		} catch (Exception e) {
			logger.error("发送失败！", e);
		}
		return false;
	}

	/**
	 *  
	 */
	public String getRtnCode() {
		return nRtnCode;
	}
}