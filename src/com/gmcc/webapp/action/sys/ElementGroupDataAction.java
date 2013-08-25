package com.gmcc.webapp.action.sys;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.gmcc.model.Element;
import com.gmcc.model.ElementGroup;
import com.gmcc.model.User;
import com.gmcc.service.ElementManager;
import com.gmcc.util.AppConst;
import com.gmcc.webapp.action.base.BaseAction;
import com.ibm.service.IOperateManager;

/**
 * 数据字典明细信息类
 */
public class ElementGroupDataAction extends BaseAction<ElementGroup, Long>{

	// /////////////////////////////////////////////属性///////////////////////////////////////////
	private static final long serialVersionUID = 2803408971231003363L;

	/**
	 * 实体主码,接收ID参数用于加载实体
	 */
	private Long id;
	
	private String ttype;
	/**
	 * 用户实体对象
	 */
	private ElementGroup eleGroup;
	private List<Element> elements = new ArrayList<Element>();
    protected IOperateManager<ElementGroup, Long> elementGroupManager;
    protected ElementManager eleManager;
    
	// /////////////////////////////////////////////属性///////////////////////////////////////////

	public void setElementGroupManager(IOperateManager<ElementGroup, Long> elementGroupManager) {
		this.elementGroupManager = elementGroupManager;
		// 指定操作类型,获取来自子类指定泛型类型，并传送给服务层（服务层会再传给DAO层)
		this.elementGroupManager.setEntityClass(ElementGroup.class);
		this.elementGroupManager.setPKClass(Long.class);
	}
	
	public ElementManager getEleManager() {
		return eleManager;
	}
	public void setEleManager(ElementManager eleManager) {
		this.eleManager = eleManager;
	}
	
	public List<Element> getElements() {
		return elements;
	}

	public void setElements(List<Element> elements) {
		this.elements = elements;
	}

	public ElementGroup getEleGroup() {
		return eleGroup;
	}

	public void setEleGroup(ElementGroup eleGroup) {
		this.eleGroup = eleGroup;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTtype(String ttype) {
		this.ttype = ttype;
	}

	public String getTtype() {
		return ttype;
	}
	// /////////////////////////////////////////////构造方法///////////////////////////////////////////

	public ElementGroupDataAction() {
	}

	// /////////////////////////////////////////////方法///////////////////////////////////////////


	/**
	 * 加载数据
	 * 
	 * @return
	 */
	public String load() {
		if (id != null) {
			this.elementGroupManager.setEntityClass(ElementGroup.class);
			this.eleGroup = this.elementGroupManager.findById(this.id);
		}else {
			this.eleGroup = new ElementGroup();
			if(ttype==null || "".equals(ttype)) ttype="0";
			this.eleGroup.setTtype(Long.parseLong(ttype));
		}
		return SUCCESS;
	}

	/**
	 * 保存:插入或编辑
	 * 
	 * 
	 * @return
	 */
	public String save() {
		if(this.eleGroup.getId()==null||this.eleGroup.getId()<=0L){			
			if(this.elementGroupManager.ifExistBy("eleGroupName", this.eleGroup.getEleGroupName())){
				this.addActionError(this.getText("element.eleGroupName.exist"));
				return INPUT;
			}
		}else{
			this.eleGroup.setVersion(this.eleGroup.getVersion()+1);
		}
		if(AppConst.ENABLED.equals(eleGroup.getKeyFlag())){
			this.saveMessage(getText("element.eleGroupName.key"));
			return INPUT;
		}
		try {
//			if(id!=null){
//				this.eleGroup = (ElementGroup) this.eleManager.saveGroup(id);
//			}else{
//				this.eleGroup = (ElementGroup) this.eleManager.merge(eleGroup);
//			}		
			this.eleGroup = (ElementGroup) this.eleManager.merge(eleGroup);
			this.freshCache(this.eleGroup.getEleGroupName());
			this.saveMessage(getText("message.save.success"));
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.fillInStackTrace());
			this.saveMessage(getText("element.eleGroupName.saveerror"));
			return INPUT;
		}
	}
	@SkipValidation
	public String getEleGroupByName()throws Exception{
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse res = ServletActionContext.getResponse();
		res.setContentType("text/json;charset=UTF-8");
		res.setHeader("Cache-Control", "no-cache");
//		StringBuffer strJson = new StringBuffer("");
		try{
//			String jobId=req.getParameter("jobId");
			String eleGroupName=req.getParameter("eleGroupName");
			String eleGroupId=req.getParameter("eleGroupId");
//			ElementGroup eleGroupTmp=null;
			List<ElementGroup> list=null;
			if(eleGroupName!=null && !"".equals(eleGroupName)){
				list= this.elementGroupManager.findBy("eleGroupName", eleGroupName);
			}
			if(list!=null && list.size()>0){
				res.getWriter().write("1"); 
			}else{
				res.getWriter().write("0"); 
			}
//			boolean flag=false;
//			if(list!=null && list.size()>0){
//				if(eleGroupId!=null){
//					for(ElementGroup eg:list){
//						if(eg.getId()!=Long.valueOf(eleGroupId)){
//							flag=true; 
//							break;
//						}
//					}
////					res.getWriter().write("0");
//				}else{
//					flag=true; 
//				}
////				else{
////					res.getWriter().write("0");
////				}
//				
//			}
//			if(flag){
//				res.getWriter().write("1"); 
//			}else{
//				res.getWriter().write("0"); 
//			}
				 
			res.getWriter().flush();
			res.getWriter().close();
		}catch (Exception e) {
			res.getWriter().write("-1");
			res.getWriter().flush();
			res.getWriter().close();
//			this.addActionError(getText("error.delete"));
//			log.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return null;
	}
	@SkipValidation
	public String getElementByCode()throws Exception{
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse res = ServletActionContext.getResponse();
		res.setContentType("text/json;charset=UTF-8");
		res.setHeader("Cache-Control", "no-cache");
//		StringBuffer strJson = new StringBuffer("");
		try{
//			String jobId=req.getParameter("jobId");
			String eleCode=req.getParameter("eleCode");
			String groupName=req.getParameter("groupName");
//			ElementGroup eleGroupTmp=null;
			Element ele=null;
			if(eleCode!=null && !"".equals(eleCode)){
				ele=this.getItemsOfElement(groupName, eleCode);
			}
			if(ele!=null ){
				res.getWriter().write("1");
			}else{
				res.getWriter().write("0"); 
			}
				 
			res.getWriter().flush();
			res.getWriter().close();
		}catch (Exception e) {
			res.getWriter().write("-1");
			res.getWriter().flush();
			res.getWriter().close();
//			this.addActionError(getText("error.delete"));
//			log.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return null;
	}
}
