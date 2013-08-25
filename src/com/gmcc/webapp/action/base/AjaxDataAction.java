package com.gmcc.webapp.action.base;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import com.opensymphony.xwork2.ActionSupport;
import com.gmcc.service.AuthorityManager;
import com.gmcc.model.Element;
import com.ibm.dto.AjaxDTO;

public class AjaxDataAction extends ActionSupport {

	private static final long serialVersionUID = -7909005275666709338L;
	private String name;
	protected AuthorityManager authorityManager;	
	private List<AjaxDTO> list;
	private String result;
	private String q;
	
	private AjaxDTO ajaxDTO;
	public AjaxDTO getAjaxDTO() {
		return ajaxDTO;
	}
	public void setAjaxDTO(AjaxDTO ajaxDTO) {
		this.ajaxDTO = ajaxDTO;
	}
	
	public AuthorityManager getAuthorityManager() {
		return authorityManager;
	}
	public void setAuthorityManager(AuthorityManager authorityManager) {
		this.authorityManager = authorityManager;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public List<AjaxDTO> getList() {
		return list;
	}
	public void setList(List<AjaxDTO> list) {
		this.list = list;
	}

	public String findNull() throws Exception {
		list= null;
		return SUCCESS;
	}

	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	public String getQ() {
		return q;
	}
	public void setQ(String q) {
		this.q = q;
	}

	public String findUserNames() throws Exception {
		list= authorityManager.getUserNames(name, null);
		return SUCCESS;
	}
	
	public String findElementByGroup() throws Exception {		
		String[] para = URLDecoder.decode(q, "utf-8").split(",");
		String groupName = null;
		String eleName = null;
		if(para!=null && para.length>1){
			groupName = para[0];
			eleName = para[1];
		}else{
			groupName = URLDecoder.decode(q, "utf-8");
		}
		List<Element> tempList = authorityManager.getCacheItemsByGroup(groupName, eleName);
		list = new ArrayList<AjaxDTO>();
		for(Element e: tempList){
			list.add(new AjaxDTO(e.getId(), e.getEleName()+"{"+e.getEleCode()+"}"));
		}
		
		return SUCCESS;
	}

}
