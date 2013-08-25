package com.gmcc.decorator;

import javax.servlet.jsp.PageContext;

import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.exception.DecoratorException;
import org.displaytag.properties.MediaTypeEnum;

public class OrgCategoryWrapper implements DisplaytagColumnDecorator {
	
	public Object decorate(Object arg0, PageContext arg1, MediaTypeEnum arg2)
			throws DecoratorException {
		String argStr=arg0.toString();
		String argStr1=argStr.substring(0,1);
		String argStr2=argStr.substring(1,2);
		String argStr3=argStr.substring(2,3);
		String argStr4=argStr.substring(3,4);
		String result="";
		if(argStr1!=null && argStr1.equals("1")){
			result=result+"采购组织,";
		}
		if(argStr2!=null && argStr2.equals("1")){
			result=result+"销售组织,";
		}
		if(argStr3!=null && argStr3.equals("1")){
			result=result+"物流组织,";
		}
		if(argStr4!=null && argStr4.equals("1")){
			result=result+"仓储组织,";
		}
		if(result!=null && !result.equals(""))
			result=result.substring(0,result.length()-1);
		return result;
	}
	
}
