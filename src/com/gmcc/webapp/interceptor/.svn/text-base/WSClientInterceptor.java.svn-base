package com.gmcc.webapp.interceptor;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.binding.soap.interceptor.SoapPreProtocolOutInterceptor;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.Phase;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * web service client interceptor used to 
 * 将报文头消息加入到SOAP 报文中
 * * @author dengpeng - 2009-12-31
 *
 */
public class WSClientInterceptor extends AbstractSoapInterceptor {

	private Logger log = Logger.getLogger(this.getClass());
	
	private String userName="admin";
	private String password="d033e22ae348aeb5660fc2140aec35850c4da997";

	public WSClientInterceptor() {
		super(Phase.WRITE);
		addAfter(SoapPreProtocolOutInterceptor.class.getName());
	}

	public void handleMessage(SoapMessage message) throws Fault {
		
			log.debug(this.getClass().getName()+"start... submit webservice requeset");
		
			DocumentBuilder builder = null;
			try {
				builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			}
			Document doc = builder.newDocument();
			Element elementCredentials = doc.createElement("WSCredentials");
			Element elementUser = doc.createElement("username");
			elementUser.setNodeValue(getUserName());
			elementUser.setAttribute("value", getUserName());
			Element elementPassword = doc.createElement("password");
			elementPassword.setNodeValue(getPassword());
			elementPassword.setAttribute("value", getPassword());
			//elementPassword.setPrefix(getPassword());
			elementCredentials.appendChild(elementUser);
			elementCredentials.appendChild(elementPassword);
			
			QName qnameCredentials =  new QName("WSCredentials");
			Header header = new Header(qnameCredentials, elementCredentials);
	
			message.getHeaders().add(header);
			log.debug(message);
			log.debug("doc:"+doc);
	}

	public String getPassword() {
		
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}