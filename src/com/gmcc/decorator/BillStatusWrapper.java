package com.gmcc.decorator;

import javax.servlet.jsp.PageContext;

import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.exception.DecoratorException;
import org.displaytag.properties.MediaTypeEnum;

import com.gmcc.model.Element;
import com.gmcc.webapp.action.base.BaseAction;

public class BillStatusWrapper implements DisplaytagColumnDecorator {

	@SuppressWarnings("rawtypes")
	public Object decorate(Object arg0, PageContext arg1, MediaTypeEnum arg2)
			throws DecoratorException {
		if(arg0==null){
			return "";
		}
		String fieldValue = "";
		BaseAction baseAction = (BaseAction) arg1.getRequest().getAttribute("strutsAction");
		Element ele = baseAction.getItemsOfElement("billStatus", arg0.toString());
		if(ele!=null){
			fieldValue = ele.getEleName();
		}
		return fieldValue;
	}

}
