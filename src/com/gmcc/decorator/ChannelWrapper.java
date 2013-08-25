package com.gmcc.decorator;


import javax.servlet.jsp.PageContext;
import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.exception.DecoratorException;
import org.displaytag.properties.MediaTypeEnum;

public class ChannelWrapper implements DisplaytagColumnDecorator {

	public Object decorate(Object arg0, PageContext arg1, MediaTypeEnum arg2)
			throws DecoratorException {
		String[] channelCode=new String[]{"-1","0","1","2","3","4","5","6","7","8","9","10","11","12"};
		String[] channelName=new String[]{"全渠道","互联网-易买网","互联网-门户","WAP","电话营销","社会渠道","营业厅","互联网-全网",
				"自助终端","自有渠道-客户经理","互联网-淘宝网/商城","深圳特有渠道","互联网-掌上客户端","自有服务厅"};
		for(int i=0;i<channelCode.length;i++){
			if(arg0!=null && arg0.equals(channelCode[i])){
				return channelName[i];
			}
		}
		return arg0;
	}
	
}
