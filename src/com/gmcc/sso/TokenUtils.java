package com.gmcc.sso;

import java.io.IOException;

import com.gmcc.model.Params;
import com.gmcc.service.ParamsManager;
import com.ibm.util.AppUtil;
import com.oreilly.servlet.Base64Decoder;
import com.oreilly.servlet.Base64Encoder;

public class TokenUtils {
	public static final String TOKEN_PARAMETER_KEY = "ESTORE_TOKEN";
	public static final String TOKEN_LOGOUT = "TOKEN_LOGOUT";
	
	public static final String APP_DEFAULT = "APP_DEFAULT";
	public static final String APP_PARAMETER = "APP";
	public static final String TOKEN_USERNAME = "userName";
	public static final String APP_LIST = "appList";
	
	public static final String ESTORE_NET_URL = "ESTORE_NET_URL";
	public static final String isExternalUser = "isExternalUser";
	
	public static final String TOKEN_VALIDATE_URL = "TOKEN_VALIDATE_URL";
	public static final String TOKEN_LOGIN_URL = "TOKEN_LOGIN_URL";
	public static final String CHANGE_PASSWORD = "CHANGE_PASSWORD";
	
	public static final String COOKIE_DOMAIN_INT = "COOKIE_DOMAIN_INT";
	public static final String COOKIE_DOMAIN_EXT = "COOKIE_DOMAIN_EXT";
	
	public static final String traget_代客下单 = "代客下单";
	public static final String traget_运营分析 = "运营分析";
	public static final String traget_系统管理 = "系统管理";
	public static final String ESTORE_MS_AUTHORITY = "ESTORE_MS_AUTHORITY";
	public static final String SYS_FOREGROUND="SYS_FOREGROUND";
	
	public static final int TOKEN_COOKIE_MAXAGE=3600 * 24 * 30;
	public static final String EXCEL_SHEETPERCOUNT="EXCEL_SHEETPERCOUNT";
	
	public Params getParamsByCode(String code) throws Exception {
		ParamsManager paramsManager = (ParamsManager)AppUtil.getBean("paramsManager");
		return paramsManager.getParamsByCode(code);
	}
	
	public static void logout() throws Exception {
		TokenValidator tk = new TokenValidator();
		tk.logout();
	}
		
	public static String encode(byte[] bytes) {
		return Base64Encoder.encode(bytes);
	}

	public static String encodeString(String source) {
		if (null == source) {
			return null;
		}
		return Base64Encoder.encode(source);
	}

	public static byte[] decode(String input) throws IOException {
		return Base64Decoder.decodeToBytes(input);
	}

	public static String decodeString(String input) throws IOException {
		return Base64Decoder.decode(input);
	}

}
