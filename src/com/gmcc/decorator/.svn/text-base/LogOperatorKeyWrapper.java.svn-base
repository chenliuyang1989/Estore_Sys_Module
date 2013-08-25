package com.gmcc.decorator;

import javax.servlet.jsp.PageContext;

import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.exception.DecoratorException;
import org.displaytag.properties.MediaTypeEnum;

public class LogOperatorKeyWrapper implements DisplaytagColumnDecorator{
	
	public Object decorate(Object arg0, PageContext arg1, MediaTypeEnum arg2)
		throws DecoratorException {
		if(arg0 != null && !"".equals(arg0)){
			if("save".equals(arg0)){
				return "保存";
			}else if("delete".equals(arg0)){
				return "删除";
			}else if("enabled".equals(arg0)){
				return "激活";
			}else if("update".equals(arg0)){
				return "修改";
			}else if("approve".equals(arg0)){
				return "审核";
			}else if("CancelOrder".equals(arg0)){
				return "订单取消";
			}else if("auditedSuccess".equals(arg0)){
				return "审核通过";
			}else if("auditedFail".equals(arg0)){
				return "审核不通过";
			}else if("unlockCampaign".equals(arg0)){
				return "营销案解锁";
			}else if("manualOpen".equals(arg0)){
				return "手动业务开通";
			}else if("confPayMent".equals(arg0)){
				return "确认收款";
			}else if("cancelOverTimeOrder".equals(arg0)){
				return "取消超时定单";
			}else if("gsRepeal".equals(arg0)){
				return "补货撤销";
			}else if("supplyCancel".equals(arg0)){
				return "供货取消";
			}
			
		}
		return "";
	}

}
