package com.gmcc.webapp.action.sys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;

import com.gmcc.cons.element.Constants;
import com.gmcc.dto.LogDTO;
import com.gmcc.service.LogService;
import com.gmcc.util.AppContentGmcc;
import com.gmcc.util.SpringSecurityUtils;
import com.gmcc.model.BusinessLog;
import com.gmcc.model.Element;
import com.gmcc.model.User;
import com.gmcc.webapp.action.base.DisplayTagQueryAction;

/**
 * 用户列表子类，依赖父类DisplayTagQueryAction实现全部display标签支持, 泛型指定操作实体和主码类型
 */
public class BusinessLogListAction extends DisplayTagQueryAction<LogDTO, Long> {

	// /////////////////////////////////////////////属性///////////////////////////////////////////
	private static final long serialVersionUID = 2179464314530133059L;

	static HashMap<String, String> collMap = new HashMap<String, String>();
	private BusinessLog businessLog;
	private LogService logService;
	static{
		collMap.put("id", "LoggingLog.id");
		collMap.put("logType", "LoggingLog.type");
		collMap.put("cityName", "field.cityName");
		collMap.put("operName", "LoggingLog.operatorName");
		collMap.put("loginIp", "businessLog.loginIp");
		collMap.put("operTime", "LoggingLog.operatorTime");
		
	}
	
	public BusinessLogListAction(){
		this.setCollMap(collMap);
	}

	@Override
	public void setCollMap(HashMap<String, String> collMap) {
		super.setCollMap(collMap);
	}
	
	public BusinessLog getBusinessLog() {
		return businessLog;
	}
	public void setBusinessLog(BusinessLog businessLog) {
		this.businessLog = businessLog;
	}
	

	public LogService getLogService() {
		return logService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	public String queryPage() throws Exception {
		this.setCollMap(collMap);
		super.displaytagCheck();
		super.saveQueryCondtion(this);
		if (super.getPageSize() == 0) {
			super.setPageSize(this.configPageSize());
		}
		// Display标签支持,获取需要跳转的页码
		String paraName = new ParamEncoder("resultList").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		Object pobject = (String) getRequest().getParameter((paraName));// 页数
		String exportName = getRequest().getParameter(TableTagParameters.PARAMETER_EXPORTING);

		// 把页码转为整形
		int pageNo;// 需要跳转到第pageNo页
		
		if (pobject == null) {
			pageNo = 1;
		} else {
			pageNo = Integer.valueOf(pobject.toString());
		}
		if (exportName != null) {
			pageNo = 1;
			super.setPageSize(super.queryManager.getMaxPageSize());
		}
		Element logType=this.getItemsOfElement(Constants.ELE_TYPE_LOG, Constants.ELE_TYPE_LOG_1);
		businessLog.setType(logType);
		List<LogDTO> list=logService.getLogList(pageNo, super.getPageSize(), businessLog);
		Long count=logService.getLogCount(businessLog);
		super.setTotalRows(count.intValue());
		super.setResultList(list);
		initCookie();
		super.currentUrl="query";
		return SUCCESS;
	}

	@SuppressWarnings("rawtypes")
	public List getAllCityList(){
		List<Element> cityList=new ArrayList();
		try{
			User user = SpringSecurityUtils.getCurrentUser();
			if(user.getCitySysID()==null || user.getCitySysID().equals("") || user.getCitySysID().equals(AppContentGmcc.CITY_PROVINCE_CODE)){
				cityList=this.getItemsOfGroup(AppContentGmcc.CITY_GROUPNAME);
			}else{
				Element city=this.getItemsOfElement(AppContentGmcc.CITY_GROUPNAME, user.getCitySysID());
				cityList.add(city);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return cityList;
	}
}
