package com.gmcc.decorator;

import java.util.Date;

import javax.servlet.jsp.PageContext;
import org.apache.commons.lang.time.FastDateFormat;
import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.exception.DecoratorException;
import org.displaytag.properties.MediaTypeEnum;

public class ShortDateWrapper implements DisplaytagColumnDecorator {

	private FastDateFormat dateFormat = FastDateFormat.getInstance("yyyy-MM-dd");
	
	public Object decorate(Object arg0, PageContext arg1, MediaTypeEnum arg2)
			throws DecoratorException {
		if(arg0!=null && !arg0.equals("")){
			Date date = (Date) arg0;
			return this.dateFormat.format(date);
		}
		return arg0;
	}
	
}
