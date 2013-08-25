package com.gmcc.webapp.action.demo;

import java.util.List;

import com.ibm.util.annotation.LogBusinessKey;
import com.gmcc.cons.element.Constants;
import com.gmcc.model.DemoBill;
import com.gmcc.service.demo.DemoService;
import com.gmcc.model.Element;
import com.gmcc.webapp.action.base.BaseBusinessLogAction;

public class BaseDemoDataAction extends BaseBusinessLogAction<DemoBill, Long> {
	private static final long serialVersionUID = 1L;
	private DemoBill baseDemo;
	private DemoService demoManager;
	
	public void setBaseDemo(DemoBill baseDemo) {
		this.baseDemo = baseDemo;
	}
	public DemoBill getBaseDemo() {
		return baseDemo;
	}

	public void setDemoManager(DemoService demoManager) {
		this.demoManager = demoManager;
	}
	
	public String load() throws Exception {
		if(this.baseDemo.getId()!=null){
			this.baseDemo = this.demoManager.findById(this.baseDemo.getId());
		}
		return SUCCESS;
	}
	
	@LogBusinessKey(objectType=Constants.ELE_TYPE_LOG_2, objectKey="log_save_summarybill", methodKey="save")
	public String save() {
		//invoke service
		try{
			this.baseDemo = this.demoManager.saveDemoBill(baseDemo);
		}catch (Exception e) {
			this.addActionError(getText("error.save"));
			log.error(e.getMessage(), e);
			return INPUT;
		}
		
		//message
		this.saveMessage(getText("message.save.success"));
		
		//log2db
		this.setOperatorPK(this.baseDemo.getId());
		this.setOperatorContent(this.baseDemo.getBillNum());
		
		return SUCCESS;
	}
	
	@LogBusinessKey(objectType=Constants.ELE_TYPE_LOG_2, objectKey="log_delete_summarybill", methodKey="delete")
	public String delete() {
		//log2db
		this.setOperatorPK(this.baseDemo.getId());
		this.setOperatorContent(this.baseDemo.getBillNum());
		
		//save
		try{
			this.demoManager.delDemoBill(baseDemo.getId());
		}catch (Exception e) {
			this.addActionError(getText("error.delete"));
			log.error(e.getMessage(), e);
			return INPUT;
		}
		return super.backToList();
	}	
	
	public List<Element> getBillStatusSet() {
		return super.getItemsOfGroup("billStatus");
	}
	
}
