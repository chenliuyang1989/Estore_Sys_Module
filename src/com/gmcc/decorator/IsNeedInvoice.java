package com.gmcc.decorator;

import javax.servlet.jsp.PageContext;

import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.exception.DecoratorException;
import org.displaytag.properties.MediaTypeEnum;

public class IsNeedInvoice implements DisplaytagColumnDecorator{
	
	public Object decorate(Object arg0, PageContext arg1, MediaTypeEnum arg2)
		throws DecoratorException {
		if(arg0 != null && !"".equals(arg0)){
			return "0".equals(arg0+"") == true ? "否" :"是" ;
		}
		return "";
	}

}
