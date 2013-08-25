package com.gmcc.webapp.action.sys;


import com.gmcc.model.Element;
import com.gmcc.model.ElementGroup;
import com.gmcc.service.ElementManager;
import com.gmcc.util.AppConst;
import com.gmcc.webapp.action.base.BaseAction;
import com.ibm.service.IOperateManager;

/**
 * 数据字典明细信息类
 */
public class ElementDataAction extends BaseAction<Element, Long> {

	// /////////////////////////////////////////////属性///////////////////////////////////////////
	private static final long serialVersionUID = 2803408971231003363L;

	/**
	 * 实体主码,接收ID参数用于加载实体
	 */
	private Long id;//element 主键

	private Long gid;//elementGroup主键

	protected IOperateManager<ElementGroup ,Long> eleGroupOperateManager; 
	protected IOperateManager<Element, Long> elementManager;
	protected ElementManager eleManager;
	
	public void setElementManager(IOperateManager<Element, Long> elementManager) {
		this.elementManager = elementManager;
		this.elementManager.setEntityClass(Element.class);
		this.elementManager.setPKClass(Long.class);
	}
	
	public ElementManager getEleManager() {
		return eleManager;
	}

	public void setEleManager(ElementManager eleManager) {
		this.eleManager = eleManager;
	}
	
	/**
	 * 用户实体对象
	 */
	private Element editElement;

	private ElementGroup eleGroup;
	// /////////////////////////////////////////////属性///////////////////////////////////////////

	public Long getGid() {
		return gid;
	}

	public void setGid(Long gid) {
		this.gid = gid;
	}
	
	public ElementGroup getEleGroup() {
		return eleGroup;
	}

	public void setEleGroup(ElementGroup eleGroup) {
		this.eleGroup = eleGroup;
	}

	public Element getEditElement() {
		return editElement;
	}

	public void setEditElement(Element editElement) {
		this.editElement = editElement;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setEleGroupOperateManager(
			IOperateManager<ElementGroup, Long> eleGroupOperateManager) {
		this.eleGroupOperateManager = eleGroupOperateManager;
		this.eleGroupOperateManager.setEntityClass(ElementGroup.class);
		this.eleGroupOperateManager.setPKClass(Long.class);
	}

	public ElementDataAction() {
	}

	// /////////////////////////////////////////////方法///////////////////////////////////////////

	/**
	 * 加载数据
	 * 
	 * @return
	 */
	public String load() {
		if (id != null) {
			this.editElement = elementManager.findById(this.id);
			if(this.editElement!=null){
				this.eleGroup = this.editElement.getElementGroup();				
			}
		}else{
			editElement = new Element();
		}
		return SUCCESS;
	}

	/**
	 * 保存:插入或编辑
	 * 
	 * 
	 * @return
	 */
	public String save()throws Exception {
		if(editElement==null){
			this.load();
		}
		if(AppConst.ENABLED.equals(editElement.getKeyFlag())){
			this.addActionError(getText("element.eleGroupName.key"));
			return INPUT;
		}
		if(this.editElement.getEleCode()==null || "".equals(this.editElement.getEleCode().trim())){
		
			this.addActionError(getText("element.code.error"));

			return INPUT;
		}
		if(this.editElement.getEleName()==null || "".equals(this.editElement.getEleName().trim())){
			this.saveMessage(getText("element.name.error"));
			return INPUT;
		}
		if(this.editElement.getOrderNum()==null ){
			this.addActionError(getText("element.code.error"));
			return INPUT;
		}
		try{
			this.eleGroupOperateManager.setEntityClass(ElementGroup.class);
			eleGroup = this.eleGroupOperateManager.findById(gid);	
			eleGroup.setVersion(eleGroup.getVersion()+1);
			this.eleGroupOperateManager.merge(eleGroup);
			editElement.setElementGroup(eleGroup);
			this.elementManager.setEntityClass(Element.class);
			this.editElement = (Element) elementManager.merge(editElement);
			this.freshCache(eleGroup.getEleGroupName());
			this.saveMessage(getText("message.save.success"));
		}catch(Exception e){
			e.printStackTrace();
			this.addActionError(e.getMessage());
		}
		return "ajaxresult";
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	public String delete() {
		if(editElement==null){
			this.load();
		}
		if(AppConst.ENABLED.equals(editElement.getKeyFlag())){
			this.addActionError(this.getText("element.eleGroupName.key"));
			return INPUT;
		}
		try{
			this.elementManager.setEntityClass(Element.class);
			elementManager.delete(id, true);
			this.freshCache(eleGroup.getEleGroupName());
			this.saveMessage(getText("message.delete.success"));
		}catch(Exception e){
			e.printStackTrace();
			this.addActionError(e.getMessage());
		}
		return "ajaxresult";
	}

	/*
	 * 新建一条记录
	 */
	public String add(){

		this.editElement = new Element();
		return "ajaxresult";
	}
}
