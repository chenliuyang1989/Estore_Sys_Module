package com.gmcc.webapp.interceptor;

import javax.xml.namespace.QName;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.Phase;
import org.apache.log4j.Logger;

import org.springframework.security.Authentication;
import org.springframework.security.providers.*;
import org.springframework.security.context.*;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.gmcc.model.Authority;


/**
 * web service server interceptor used to
 * 从SOAP 报文中取出报文头
 * * @author dengpenggg - 2009-12-31
 *
 */

public class WSServerInterceptor extends AbstractSoapInterceptor {

	private Logger log = Logger.getLogger(this.getClass());

	public WSServerInterceptor() {
		super(Phase.PRE_INVOKE);
	}

	@SuppressWarnings("static-access")
	public void handleMessage(SoapMessage message) throws Fault {

		String userName="";
		String password=""; 
		log.debug("UserCredentialInterceptor handleMessage invoked");
		QName qnameCredentials = new QName("WSCredentials");
		
		// Get header based on QNAME
		if (message.hasHeader(qnameCredentials)) {
			Header header = message.getHeader(qnameCredentials);
		
			//MessageHeader elementOrderCredential=(MessageHeader) header.getObject();
			Element elementOrderCredential = (Element) header.getObject();
			Node nodeUser = elementOrderCredential.getFirstChild();
			Node nodePassword = elementOrderCredential.getLastChild();

			if (nodeUser != null) {
				userName = nodeUser.getAttributes().getNamedItem("value").getNodeValue();
			}
			if (nodePassword != null) {
				password = nodePassword.getAttributes().getNamedItem("value").getNodeValue();
			}
		}

		log.debug("userName reterived from SOAP Header is " + userName);
		log.debug("password reterived from SOAP Header is " + password+message.WSDL_OPERATION);
		
		Authority[] grantedAuthority =new Authority[]{new Authority()};
		Authentication authResult =	new UsernamePasswordAuthenticationToken(userName,password,grantedAuthority);
		
		SecurityContextHolder.getContext().setAuthentication(authResult);
	}

}