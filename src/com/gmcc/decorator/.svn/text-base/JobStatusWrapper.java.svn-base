package com.gmcc.decorator;

import javax.servlet.jsp.PageContext;

import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.exception.DecoratorException;
import org.displaytag.properties.MediaTypeEnum;

public class JobStatusWrapper implements DisplaytagColumnDecorator {

	@SuppressWarnings("rawtypes")
	public Object decorate(Object arg0, PageContext arg1, MediaTypeEnum arg2)
			throws DecoratorException {
		if(arg0 != null && "0".equals(arg0)){
			return "结束";
		}else if(arg0 != null && "1".equals(arg0)){
			return "运行";
		}else if(arg0 != null && "2".equals(arg0)){
			return "暂停";
		}else if(arg0 != null && "3".equals(arg0)){
			return "开启";
		}else if(arg0 != null && "4".equals(arg0)){
			return "关闭";
		}
		return "未知状态";
	}

}
