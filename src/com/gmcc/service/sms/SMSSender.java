/**
 * 
 */
package com.gmcc.service.sms;

import java.util.Date;

/**
 * @author Xiaoming Niu
 *
 */
public interface SMSSender {
	
	/**
	 * 发送短信
	 * @param phoneNum 手机号码
	 * @param content 短信内容
	 * @param sMsTiming 
	 *   IMMEDIATE, 立即发送
	 *   NORMAL, 正常时间发送（8:00-19:00，可配置
	 *   CUSTOM, 自定义发送时间范围
	 * @param times 指定发送时间范围
	 *   格式为：startTime,endTime， 例如0800:1830，表示上午8点到下午6点30
	 * @param days 排除日期
	 *   周日至周六分别为1, 2, 3, 4, 5, 6, 7，如果为空表示每天都可发送 
	 * @param  designateTime 指定发送时间，指定在未来唯一时间点发送
	 * @return
	 */
	boolean sendMsg(String phoneNum, String content, SMSTiming sMsTiming, String times, String days, Date designateTime) throws Exception;
	
}
