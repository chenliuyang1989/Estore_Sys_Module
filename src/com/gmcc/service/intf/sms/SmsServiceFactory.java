package com.gmcc.service.intf.sms;
import org.apache.log4j.Logger;
/**
 * <p>Name: SmsServiceFactory</p>
 * <p>Description: A factory for getting <code>SmsService</code></p>
 * 
 * @author Paul Yu
 * @version $Revision: 1.1.2.1 $ Nov 3, 2009
 */
public class SmsServiceFactory{
	
	private static final Logger logger = Logger.getLogger(SmsServiceFactory.class);

	private static SmsServiceFactory instance = new SmsServiceFactory();
	
	private SConfig sConfig;

	private String useMock2;
	
	private SmsService service = new SmsSender();
	
	protected SmsServiceFactory() {
		initialize();
		//ECApplicationConfigRegistry.getInstance().addRefreshRegistry(this);
	}
	
	public static SmsServiceFactory getInstance() {
		return instance;
	}
	
	/**
	 * Return an intance of the <code>SmsService</code>
	 * @return
	 */
	public SmsService getSmsService() {
		return service;
	}

	/**
	 * @see com.ibm.commerce.registry.Registry#initialize()
	 */
	public synchronized void initialize() {
		boolean useMock = false;
		try {
			sConfig = new SmsConfig();
			
			//String useMockStr = ECApplicationConfigRegistry.getInstance().getValue("smsIntegration/useMock");
			String useMockStr = sConfig.getUseMock();;
			//String useMockStr = "false";
			if ("true".equalsIgnoreCase(useMockStr)) {
				useMock = true;
			}
		} catch (Exception e) {
			logger.error("Exception in constructor of SmsServiceFactory()", e);
		}
		if (useMock) {
			service = new SmsServiceMockImpl();
		} else {
			service = new SmsSender();
		}

	}

	/**
	 * @see com.ibm.commerce.registry.Registry#refresh()
	 */
	public synchronized void refresh() throws Exception {
		initialize();
	}
	
	/**
	 * @return Returns the useMock2.
	 */
	public String getUseMock2() {
		return useMock2;
	}
	/**
	 * @param useMock2 The useMock2 to set.
	 */
	public void setUseMock2(String useMock2) {
		this.useMock2 = useMock2;
	}
}
