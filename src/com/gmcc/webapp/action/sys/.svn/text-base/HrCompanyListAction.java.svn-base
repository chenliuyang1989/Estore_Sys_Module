package com.gmcc.webapp.action.sys;

import java.util.List;

import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.gmcc.dto.TreeDTO;
import com.gmcc.service.tree.TreeService;
import com.gmcc.model.Element;
import com.gmcc.model.HrCompany;
import com.gmcc.model.Role;
import com.gmcc.util.AppConst;
import com.gmcc.util.AppContentGmcc;
import com.gmcc.webapp.action.base.DisplayTagQueryAction;
import com.ibm.dao.hibernate.support.Page;
import com.ibm.util.StringUtil;

public class HrCompanyListAction extends DisplayTagQueryAction<HrCompany,Long>{

	private static final long serialVersionUID = 1L;
	
	private HrCompany hrCompany;
	private TreeService treeService;
	
	public HrCompany getHrCompany() {
		return hrCompany;
	}

	public void setHrCompany(HrCompany hrCompany) {
		this.hrCompany = hrCompany;
	}
	
	public TreeService getTreeService() {
		return treeService;
	}

	public void setTreeService(TreeService treeService) {
		this.treeService = treeService;
	}
	
	@Override
	protected List<Criterion> filters() throws Exception {
		List<Criterion> list = super.filters();
		list.add(Restrictions.eq("enabled", AppConst.ENABLED));
		if(StringUtil.isValidString(hrCompany.getCompCode())){
			list.add(Restrictions.like("compCode", "%"+hrCompany.getCompCode().trim()+"%"));
		}
		if(StringUtil.isValidString(hrCompany.getCompName())){
			list.add(Restrictions.like("compName", "%"+hrCompany.getCompName().trim()+"%"));
		}
		if(StringUtil.isValidString(hrCompany.getOrgProperties())){
			list.add(Restrictions.eq("orgProperties", hrCompany.getOrgProperties()));
		}
		if(StringUtil.isValidString(hrCompany.getOrgType().getId()+"")){
			list.add(Restrictions.eq("orgType.id", hrCompany.getOrgType().getId()));
		}
		if(StringUtil.isValidString(hrCompany.getIsLogistics())){
			list.add(Restrictions.eq("isLogistics", hrCompany.getIsLogistics()));
		}
		if(StringUtil.isValidString(hrCompany.getIsPurchase())){
			list.add(Restrictions.eq("isPurchase", hrCompany.getIsPurchase()));
		}
		if(StringUtil.isValidString(hrCompany.getIsSales())){
			list.add(Restrictions.eq("isSales", hrCompany.getIsSales()));
		}
		if(StringUtil.isValidString(hrCompany.getIsWareHouse())){
			list.add(Restrictions.eq("isWareHouse", hrCompany.getIsWareHouse()));
		}
		List<Element> orgTypeList=super.getItemsOfGroup(AppContentGmcc.ORG_TYPE_GROUPNAME);
		this.getRequest().setAttribute("orgTypeList", orgTypeList);
		return list;
	}
	
	@Override
	protected List<Order> orders() {
		List<Order> list = super.orders();
		list.add(Order.asc("id"));
		return list;
	}
	public String init()throws Exception{
		String sql="select t.comp_id id,t.comp_code code,t.comp_level elvel,t.comp_name name,t.parent_id parentId " +
		"from tb_hr_company t " +
		"where t.enabled="+AppConst.ENABLED;
		List<TreeDTO> treeList=this.treeService.getCommTreeList(null, 1, sql, "parent_id");
		this.getRequest().setAttribute("treeList", treeList);
		return super.execute();
	}
	@SuppressWarnings("rawtypes")
	public List getAllOrgTypeList(){
	 List statusList=super.getItemsOfGroup(AppContentGmcc.ORG_TYPE_GROUPNAME);
	       return statusList;
	}
}
