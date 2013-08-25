package com.gmcc.webapp.action.sys;

import java.util.ArrayList;
import java.util.List;

import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.util.StringHelper;

import com.gmcc.model.Element;
import com.gmcc.model.ElementGroup;
import com.gmcc.service.ElementManager;
import com.gmcc.util.AppConst;
import com.gmcc.webapp.action.base.DisplayTagQueryAction;
import com.ibm.dao.hibernate.support.Page;

/**
 * 用户列表子类，依赖父类DisplayTagQueryAction实现全部display标签支持, 泛型指定操作实体和主码类型
 */
public class ElementGroupListAction extends DisplayTagQueryAction<ElementGroup, Long> {

	// /////////////////////////////////////////////属性///////////////////////////////////////////
	private static final long serialVersionUID = 2179464214530333059L;

	/**
	 * 查询条件：组名、备注
	 */
	private ElementGroup eleGroup;
	private String ttype;
	private ElementManager eleManager;
	public ElementGroup getEleGroup() {
		return eleGroup;
	}


	public void setEleGroup(ElementGroup eleGroup) {
		this.eleGroup = eleGroup;
	}


	public String getTtype() {
		return ttype;
	}


	public void setTtype(String ttype) {
		this.ttype = ttype;
	}


	public ElementManager getEleManager() {
		return eleManager;
	}


	public void setEleManager(ElementManager eleManager) {
		this.eleManager = eleManager;
	}


	// /////////////////////////////////////////////构造方法///////////////////////////////////////////
	public ElementGroupListAction() {
		
	}
	public String queryPage() throws Exception {
		displaytagCheck();

		this.saveQueryCondtion(this);
		// 每页行数
		// if not setup pageSize in subclass ,it will use the pageSize value
		// from spring bean in the file db.properties
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
			this.setPageSize(super.queryManager.getMaxPageSize());
		}
		// 调用服务层得到当前页的列表
		Page<ElementGroup, Long> page = super.queryManager.pagedQuery(pageNo, this.alias(), this.filters(), this.orders(), super.getPageSize());
		// 设置总记录数
//		super.totalRows = (int) page.getTotalCount();
//		// 设置列表对象
//		super.resultList = page.get Result();
		List<ElementGroup> list=new ArrayList();
		List<Element> listTmp=new ArrayList();
		for(ElementGroup ele:page.getResult()){
			listTmp.clear();
			listTmp=eleManager.getItemsByGroupId(ele.getId());
			for(Element e:listTmp){
				if(e.getId()!=-1l)
					ele.setEleStr((ele.getEleStr()==null?"":ele.getEleStr())+(e.getEleName()==null?"":e.getEleName())+"{"+(e.getEleCode()==null?"":e.getEleCode())+"},");
			}
			if(ele.getEleStr()!=null && !"".equals(ele.getEleStr()))
				ele.setEleStr(ele.getEleStr().substring(0,ele.getEleStr().length()-1));
			list.add(ele);
		}
		super.setTotalRows((int) page.getTotalCount());
		super.setResultList(list);
		// 翻译字段
		// this.resultList=tranForEleGroup(this.resultList);

		// ------------------------------------------------------------------------------------------
		initCookie();
		this.currentUrl = "query";
		// ------------------------------------------------------------------------------------------
		// 返回

		return super.SUCCESS;
	}

	// ///////////////////////////////重写父类DisplayTagQueryAction的方法//////////////////////////////
	/*
	 * 查询条件
	 * 
	 * @see com.gmcc.webapp.action.base.DisplayTagQueryAction#filters()
	 */
	@Override
	protected List<Criterion> filters() throws Exception {
		List<Criterion> list = super.filters();
		if(eleGroup!=null && StringHelper.isNotEmpty(this.eleGroup.getEleGroupName())){
			list.add(Restrictions.like("eleGroupName", "%" + this.eleGroup.getEleGroupName().trim() + "%"));			
		}
		if(eleGroup!=null && StringHelper.isNotEmpty(this.eleGroup.getEleGroupRemark())){
			list.add(Restrictions.like("eleGroupRemark", "%" + this.eleGroup.getEleGroupRemark().trim() + "%"));
		}
		if(ttype!=null && StringHelper.isNotEmpty(ttype)){
			list.add(Restrictions.eq("ttype", Long.parseLong(ttype)));
		}
		return list;
	}

	/*
	 * 排序条件
	 * 
	 * @see com.gmcc.webapp.action.base.DisplayTagQueryAction#orders()
	 */
	@Override
	protected List<Order> orders() {
		List<Order> list = super.orders();
		list.add(Order.desc("id"));
		return list;
	}

	// ///////////////////////////////响应请求方法//////////////////////////////
	/**
	 * "新增"按钮跳转
	 * 
	 * @return
	 */
	public String add() {
		return "add";
	}
	/**
	 * 删除
	 * 删除主表时同时级联删除子表记录
	 * @return
	 */
	public String delete() throws Exception{
		try{
			String id=this.getRequest().getParameter("id");
			ElementGroup eleGroupTmp=eleManager.getElemetGroup(Long.valueOf(id));
			if(eleGroupTmp==null){
				this.saveMessage(getText("element.eleGroupName.notexist.error")+":"+eleGroupTmp.getEleGroupName());
				return this.queryPage();
			}
			if(AppConst.ENABLED.equals(eleGroupTmp.getKeyFlag())){
				this.addActionError(this.getText("element.eleGroupName.key"));
				return this.queryPage();
			}
			this.getSession().removeAttribute(eleGroupTmp.getEleGroupName());			
			eleManager.deleteGroupObj(eleGroupTmp);			
			this.saveMessage(getText("message.delete.success"));
			return this.queryPage();
		}catch(Exception e){
			e.printStackTrace();
			this.addActionError(this.getText("element.eleGroupName.deleteerror"));
			return this.queryPage();
		}
	}
}
