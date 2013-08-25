package com.gmcc.webapp.action.sys;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.struts2.ServletActionContext;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.gmcc.dto.TreeDTO;
import com.gmcc.model.Element;
import com.gmcc.model.Role;
import com.gmcc.service.RoleService;
import com.gmcc.service.tree.TreeService;
import com.gmcc.util.AppContentGmcc;
import com.gmcc.webapp.action.base.DisplayTagQueryAction;

/**
 * 用户列表子类，依赖父类DisplayTagQueryAction实现全部display标签支持, 泛型指定操作实体和主码类型
 */
public class RoleMenuListAction extends DisplayTagQueryAction<Role, Long> {

	private TreeService treeService;
	// /////////////////////////////////////////////属性///////////////////////////////////////////
	private static final long serialVersionUID = 2179464214530333059L;
	private static final int PAGE_SIZE=15;
	private Role role;
	private RoleService roleSerivce;
	private String listString;
	
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getListString() {
		return listString;
	}

	public void setListString(String listString) {
		this.listString = listString;
	}

	public TreeService getTreeService() {
		return treeService;
	}

	public void setTreeService(TreeService treeService) {
		this.treeService = treeService;
	}

	public RoleService getRoleSerivce() {
		return roleSerivce;
	}

	public void setRoleSerivce(RoleService roleSerivce) {
		this.roleSerivce = roleSerivce;
	}

	// /////////////////////////////////////////////构造方法///////////////////////////////////////////
	public RoleMenuListAction() {
	}

	// ///////////////////////////////重写父类DisplayTagQueryAction的方法//////////////////////////////
	
	/**
	 * override super class 's method
	 * setup the page size of transport item list to 12
	 */
	@Override
	public String queryPage() throws Exception {
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
		List<Role> list=roleSerivce.getRoleList(role,pageNo,super.getPageSize());
		Long count=roleSerivce.getRoleCount(role);
		super.setTotalRows(count.intValue());
		super.setResultList(list);
		initCookie();
		this.currentUrl="query";
		return SUCCESS;
	}
	
	public String queryRoleMenuList()throws Exception
	{
		StringBuffer sb = new StringBuffer();
		List list = roleSerivce.getRoleMenuList(role, 1, super.getPageSize());
		for(int i=0 ; i<list.size() ; i++)
		{
		  ListOrderedMap dto = (ListOrderedMap)list.get(i);
		  sb.append(dto.get("menu_name").toString()).append(",");
		  sb.append(dto.get("firstLevel").toString()).append(",");
		  sb.append(dto.get("secondLevel").toString()).append(",");
		  sb.append(dto.get("thirdLevel").toString()).append(",");
		  sb.append(dto.get("four").toString()).append(",");
		  sb.append(dto.get("fiveLevel").toString()).append(",");
		  sb.append(dto.get("id").toString()).append("");

		  if(i==list.size()-1)
		  {
		  }
		  else
		  {
			  sb.append("|");

		  }
		}
		  this.listString = sb.toString();
		  System.out.println(listString);
		  HttpServletResponse response = ServletActionContext.getResponse();
		    response.setContentType("text/xml;charset=utf-8"); //更改字符编码  
		   response.getWriter().println(this.listString);  

     
		return null;
		
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
//	@SuppressWarnings("rawtypes")
//	public List getAllMenulList(){
//		List<TreeDTO> mentList=new ArrayList();
//		try{
//			String sql="select t.id id,menu_name name,t.menu_level objLevel,t.parent_id parentId from tb_app_menu t where t.status_flag=1 and is_menu=1 ";
//			mentList=treeService.getCommTreeList(0L, 1, sql, "parent_id ");
//		}catch(Exception ex){
//			ex.printStackTrace();
//		}
//		return mentList;
//	}
	/**
	 * 角色类型下拉框中，来源于数据字典组roleType的数据项
	 */
	@SuppressWarnings("rawtypes")
	public List getAllRoleTypes() {
		List allRoleTypes = super.getItemsOfGroup("roleType");
		return allRoleTypes;
	}
}
