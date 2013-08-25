package com.gmcc.service.job;

import java.io.Serializable;
import java.text.ParseException;
import org.springframework.scheduling.quartz.CronTriggerBean;

public class InitializingCronTrigger extends CronTriggerBean implements Serializable {
	private static final long serialVersionUID = 1L;
	//0/180 * * * * ?
	//0 00 01 * * ?
	private static final String iniVal = "0 10 17 * * ? 2099";
	
	public InitializingCronTrigger() throws ParseException{
		setCronExpression(iniVal);
	}
}
