package com.gmcc.webapp.action.demo;

import java.util.List;
import java.util.ArrayList;

import com.ibm.util.annotation.LogBusinessKey;
import com.gmcc.cons.element.Constants;
import com.gmcc.model.DemoBill;
import com.gmcc.model.DemoBillDetail;
import com.gmcc.service.demo.DemoService;
import com.gmcc.model.Element;
import com.gmcc.webapp.action.base.BaseBusinessLogAction;

public class AdvanceDemoDataAction extends BaseBusinessLogAction<DemoBill, Long> {
	
	private static final long serialVersionUID = 1L;
	private DemoBill advanceDemo;
	private List<DemoBillDetail> detailList;
	private DemoService demoManager;
	private String view;
	
	public DemoBill getAdvanceDemo() {
		return advanceDemo;
	}
	public void setAdvanceDemo(DemoBill advanceDemo) {
		this.advanceDemo = advanceDemo;
	}

	public void setDemoManager(DemoService demoManager) {
		this.demoManager = demoManager;
		this.demoManager.setEntityClass(DemoBill.class);
		this.demoManager.setPKClass(Long.class);
	}
	
	public void setDetailList(List<DemoBillDetail> detailList) {
		this.detailList = detailList;
	}
	
	public String getView() {
		return view;
	}
	public void setView(String view) {
		this.view = view;
	}
	
	public String load() throws Exception {		
		if(this.advanceDemo.getId()!=null){
			super.displaytagCheck();
			detailList = new ArrayList<DemoBillDetail>();
			this.advanceDemo = this.demoManager.findById(this.advanceDemo.getId());
			int index = 0;
			for(DemoBillDetail dtl : advanceDemo.getDemoBillDetails()){
				dtl.setIndex(index++);
				detailList.add(dtl);
			}
		}
		return SUCCESS;
	}
	
	@LogBusinessKey(objectType=Constants.ELE_TYPE_LOG_2, objectKey="log_save_summarybill", methodKey="save")
	public String save() {
		//set data
		super.displaytagCheck();
		try{
			//invoke service			
			this.advanceDemo = this.demoManager.saveDemoBill(advanceDemo, detailList);
			//set data
			detailList = new ArrayList<DemoBillDetail>();
			int index = 0;
			for(DemoBillDetail dtl : advanceDemo.getDemoBillDetails()){
				dtl.setIndex(index++);
				detailList.add(dtl);
			}
		}catch (Exception e) {
			this.addActionError(getText("error.save"));
			log.error(e.getMessage(), e);
			return INPUT;
		}
		
		//message
		this.saveMessage(getText("message.save.success"));
		
		//log2db
		this.setOperatorPK(this.advanceDemo.getId());
		this.setOperatorContent(this.advanceDemo.getBillNum());
		
		return SUCCESS;
	}
	
	@LogBusinessKey(objectType=Constants.ELE_TYPE_LOG_2, objectKey="log_delete_summarybill", methodKey="delete")
	public String delete() {
		//log2db
		this.setOperatorPK(this.advanceDemo.getId());
		this.setOperatorContent("disabled");
		try{
			this.demoManager.delDemoBill(this.advanceDemo.getId());
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
	
	public List<DemoBillDetail> getDetailList(){
		return detailList;
	}
	
}
