package com.gmcc.service.intf.sms;


import java.util.StringTokenizer;
/**
 * 短信特服号配置
 * @author guoyuhua
 *
 */
public class SvcNum {
	class ServerCfg {
		/** 服务器IP */
		public String m_strIP;
		/** 服务器端口号 */
		public int m_iPort;
	}

	/** 特服号码 */
	String m_strSvcNum;
	/** 短信发送服务器配置组 */
	ServerCfg[] m_arrCfgs;
	/** 当前启用的发送服务器配置编号 */
	int m_iCurrCfg;

	public SvcNum(String init) throws Exception {
		StringTokenizer st = new StringTokenizer(init, "| ");
		int c = st.countTokens();
		if (c < 2)
			throw new Exception("Init format error.");
		m_strSvcNum = st.nextToken();
		m_arrCfgs = new ServerCfg[c - 1];
		for (int i = 0; i < m_arrCfgs.length; i++) {
			String str = st.nextToken();
			int idx = str.indexOf(":");
			if (idx == -1)
				throw new Exception("Init format error.");
			m_arrCfgs[i] = new ServerCfg();
			m_arrCfgs[i].m_strIP = str.substring(0, idx);
			m_arrCfgs[i].m_iPort = Integer.parseInt(str.substring(idx + 1));
		}
	}

	public String getSvcNum() {
		return m_strSvcNum;
	}

	public String getServer() {
		return m_arrCfgs[m_iCurrCfg].m_strIP;
	}

	public int getPort() {
		return m_arrCfgs[m_iCurrCfg].m_iPort;
	}

	public void nextServer() {
		m_iCurrCfg++;
		if (m_iCurrCfg >= m_arrCfgs.length)
			m_iCurrCfg = 0;
	}
}
