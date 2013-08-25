package com.gmcc.service.impl;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gmcc.dao.AppLockDao;
import com.gmcc.model.AppLock;
import com.gmcc.service.AppLockService;

public class AppLockServiceImpl implements AppLockService {

	protected static final Log logger = LogFactory
			.getLog(AppLockServiceImpl.class);

	private AppLockDao appLockDao;

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
	public boolean lock(String code, boolean waitFlag, int waitTime, String user) {

		AppLock applock = null;
		boolean isLock = false;
		String lockUser=null;
		boolean isSelf=false;
		
		do {

			applock = appLockDao.getAppLockByCode(code);
			if (applock == null) {
				logger.error("锁定参数【" + code + "】未配置");
				return false;
			} else {
				isLock = AppLock.ISLOCK_Y.equals(applock.getIsLock());
				lockUser=applock.getLastUpdatedBy();
				isSelf=user.equals(lockUser);
			}

			if (isLock && !isSelf) {
				try {
					logger.info("参数【" + code + "】已被 【"+lockUser+"】锁定，暂停" + waitTime + "秒");
					Thread.sleep(waitTime * 1000);
				} catch (InterruptedException e) {
					logger.error(e);
				}
			}
			
			// 当前数据  被别人锁定，且不等待，直接返回
			if (isLock && !isSelf && !waitFlag) {
				return false;
			}
			
		} while (waitFlag && isLock && !isSelf);

		

		// 当前数据未锁定，锁定数据
		synchronized (AppLockServiceImpl.class) {

			applock = appLockDao.getAppLockByCode(code);
			isLock = AppLock.ISLOCK_Y.equals(applock.getIsLock());
			if (!isLock || isSelf ) {
				applock.setIsLock(AppLock.ISLOCK_Y);
				applock.setLastUpdatedBy(user);
				applock.setLastUpdatedTime(new Date());
				try {
					appLockDao.save(applock);
				} catch (Exception e) {
					logger.error("修改数据锁失败", e);
					return false;
				}

			} else {
				logger.info("~~~~~~~~数据锁定时发现数据已改变，不能锁定~~~~~~~~");
				return false;
			}
		}

		logger.info("参数【" + code + "】锁定");
		return true;
	}

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
	public boolean unLock(String code, String user) {

		AppLock applock = appLockDao.getAppLockByCode(code);

		if (applock == null) {
			logger.error("锁定参数【" + code + "】未配置");
			return false;
		}

		boolean isLock = AppLock.ISLOCK_Y.equals(applock.getIsLock());
		if (!isLock) {
			logger.info("参数【" + code + "】未锁定，无需解锁");
			return false;
		} 
		
		String lastUpdateBy= applock.getLastUpdatedBy();
		if(!user.equals(lastUpdateBy)){
			logger.error("锁定参数【" + code + "】锁定人为【"+lastUpdateBy+"】用户【" +user+"】 不能解锁");
			return false;
		}
		
		

		//解锁
		applock.setIsLock(AppLock.ISLOCK_N);
		applock.setLastUpdatedTime(new Date());

		try {
			appLockDao.save(applock);
		} catch (Exception e) {
			logger.error("修改数据锁失败", e);
			return false;
		}
		
		
		logger.info("参数【" + code + "】解锁");
		return true;
	}

	public void setAppLockDao(AppLockDao appLockDao) {
		this.appLockDao = appLockDao;
	}

}
