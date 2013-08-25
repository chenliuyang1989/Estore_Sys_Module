package com.gmcc.decorator;

import org.displaytag.decorator.ColumnDecorator;
import org.displaytag.exception.DecoratorException;

public class LongValueWrapper implements ColumnDecorator {

	public String decorate(Object arg0) throws DecoratorException {
		if(arg0==null){
			return "0";
		}
		return (String)arg0.toString();
	}


}
