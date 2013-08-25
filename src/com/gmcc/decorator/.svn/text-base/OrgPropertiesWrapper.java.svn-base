package com.gmcc.decorator;

import javax.servlet.jsp.PageContext;

import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.exception.DecoratorException;
import org.displaytag.properties.MediaTypeEnum;

public class OrgPropertiesWrapper implements DisplaytagColumnDecorator{
	
	public Object decorate(Object arg0, PageContext arg1, MediaTypeEnum arg2)
		throws DecoratorException {
		if(arg0 != null && !"".equals(arg0)){
			return "1".equals(arg0) == true ? "内部组织" :"外部组织" ;
		}
		return "";
	}

}
