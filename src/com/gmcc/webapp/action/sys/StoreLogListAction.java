package com.gmcc.webapp.action.sys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.gmcc.cons.element.Constants;
import com.gmcc.dto.LogDTO;
import com.gmcc.model.BusinessLog;
import com.gmcc.model.Element;
import com.gmcc.model.User;
import com.gmcc.service.LogService;
import com.gmcc.util.AppContentGmcc;
import com.gmcc.util.DateUtil;
import com.gmcc.util.SpringSecurityUtils;
import com.gmcc.webapp.action.base.DisplayTagQueryAction;
import com.ibm.util.StringUtil;

public class StoreLogListAction extends DisplayTagQueryAction<LogDTO,Long>{
	
	static HashMap<String, String> collMap = new HashMap<String, String>();
	static{
		collMap.put("id", "businessLog.id");
		collMap.put("logType", "businessLog.type");
		collMap.put("cityName", "field.cityName");
		collMap.put("operTime", "businessLog.operatorTime");
		collMap.put("operName", "businessLog.operatorName");
		collMap.put("operMothod", "businessLog.operatorMethodKey");
//		collMap.put("operatorObjectKey", "businessLog.operatorObjectKey");
		collMap.put("content", "businessLog.operatorContent");
	}
	
	public StoreLogListAction(){
		this.setCollMap(collMap);
	}

	private static final long serialVersionUID = 1L;
	
	private BusinessLog businessLog;
	private String logTypeCode;
	private LogService logService;
	public BusinessLog getBusinessLog() {
		return businessLog;
	}

	public void setBusinessLog(BusinessLog businessLog) {
		this.businessLog = businessLog;
	}
	

	public String getLogTypeCode() {
		return logTypeCode;
	}

	public void setLogTypeCode(String logTypeCode) {
		this.logTypeCode = logTypeCode;
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
		Element logType=this.getItemsOfElement(Constants.ELE_TYPE_LOG, logTypeCode);
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
//			if(user.getRoleTypeCode().equals(AppContentGmcc.DATA_AUTHORITY_PROVINCE)||
//					user.getRoleTypeCode().equals(AppContentGmcc.DATA_AUTHORITY_SYSMGR)){
//				cityList=this.getItemsOfGroup(AppContentGmcc.CITY_GROUPNAME);
//			}else{
//				Element city=this.getItemsOfElement(AppContentGmcc.CITY_GROUPNAME, user.getCitySysID());
//				cityList.add(city);
//			}
			if(user.getCitySysID()==null || user.getCitySysID().equals("") || user.getCitySysID().equals(AppContentGmcc.CITY_PROVINCE_CODE)){
				cityList=this.getItemsOfGroup(AppContentGmcc.CITY_GROUPNAME);
			}else{
				Element city=this.getItemsOfElement(AppContentGmcc.CITY_GROUPNAME, user.getCitySysID());
				cityList.add(city);
			}
//			Long[] orgSysIds=(Long[]) user.getAuthoritiesData().get(AppContentGmcc.DATA_AUTHORITY_CITY);
//			if(orgSysIds!=null && orgSysIds.length>0){
//				for(int i=0;i<orgSysIds.length;i++){
//					Element city=this.getItemsOfElement(AppContentGmcc.CITY_GROUPNAME, orgSysIds[i]+"");
//					cityList.add(city);
//				}
//			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return cityList;
	}
}
