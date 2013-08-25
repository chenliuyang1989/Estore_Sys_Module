package com.gmcc.service.intf.sms;

public abstract class SConfig {
	
	protected static final String SVCNUM = "1008618|10.243.255.72:5000";
	protected static final String USEMOCK = "false";
	public SConfig() {
		load();
	}

	protected String svcnum;
	protected String useMock;

	/**
	 * @return Returns the svcnum.
	 */
	public String getSvcnum() {
		return svcnum;
	}
	/**
	 * @param svcnum The svcnum to set.
	 */
	public void setSvcnum(String svcnum) {
		this.svcnum = svcnum;
	}
	/**
	 * @return Returns the useMock.
	 */
	public String getUseMock() {
		return useMock;
	}
	/**
	 * @param useMock The useMock to set.
	 */
	public void setUseMock(String useMock) {
		this.useMock = useMock;
	}
	
	//abstract����������չ
	protected abstract void load();
	
}
