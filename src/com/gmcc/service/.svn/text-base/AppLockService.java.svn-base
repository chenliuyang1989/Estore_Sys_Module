package com.gmcc.service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


public interface AppLockService {
	
	/***
	 * 锁定指定数据锁
	 * @param code     指定锁编码
	 * @param waitFlag 是否需要等待解锁
	 * @param waitTime 循环等待时间
	 * @return
	 */
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRES_NEW)
	public boolean lock(String code,boolean waitFlag ,int waitTime, String user);
	
	
	/**
	 * 解锁指定数据锁 
	 * @param code 指定锁编码 
	 * @return
	 */
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRES_NEW)
	public boolean unLock(String code, String user);
}
