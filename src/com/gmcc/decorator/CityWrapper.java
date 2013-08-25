package com.gmcc.decorator;


import javax.servlet.jsp.PageContext;
import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.exception.DecoratorException;
import org.displaytag.properties.MediaTypeEnum;

public class CityWrapper implements DisplaytagColumnDecorator {

	public Object decorate(Object arg0, PageContext arg1, MediaTypeEnum arg2)
			throws DecoratorException {
		String[] cityCode=new String[]{"-1","200","759","752","750","751","758","755","756","754","757","753","662",
				"663","763","766","762","760","769","668","768","660"};
		String[] cityName=new String[]{"全省","广州","湛江","惠州","江门","韶关","肇庆","深圳","珠海","汕头","佛山","梅州","阳江",
				"揭阳","清远","云浮","河源","中山","东莞","茂名","潮州","汕尾"};
		for(int i=0;i<cityCode.length;i++){
			if(arg0!=null && arg0.equals(cityCode[i])){
				return cityName[i];
			}
		}
		return arg0;
	}
	
}
