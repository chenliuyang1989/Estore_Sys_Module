package com.gmcc.webapp.action.sys;

import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.gmcc.cons.element.Constants;
import com.gmcc.dto.MenuDTO;
import com.gmcc.dto.TreeDTO;
import com.gmcc.dto.TreeParamDTO;
import com.gmcc.model.Authority;
import com.gmcc.model.Role;
import com.gmcc.model.User;
import com.gmcc.service.AuthorityManager;
import com.gmcc.service.tree.TreeService;
import com.gmcc.util.AppConst;
import com.gmcc.util.SpringSecurityUtils;
import com.gmcc.webapp.action.base.BaseBusinessLogAction;
import com.ibm.service.IOperateManager;
import com.ibm.util.annotation.LogBusinessKey;

/**
 * 用户明细信息类
 */
public class RoleDataAction extends BaseBusinessLogAction<Role, Long> {
	protected transient final Log log = LogFactory.getLog(getClass());
	// /////////////////////////////////////////////属性///////////////////////////////////////////
	private static final long serialVersionUID = 2803408971231003363L;

	/**
	 * 实体主码,接收ID参数用于加载实体
	 */
	private Long id;

	/**
	 * 用户实体对象
	 */
	private Role role;

	private List<MenuDTO> menuDTOs;

	private AuthorityManager authorityManager;

	// private List<Long> menusOfRole;

	// private IUserService webServiceClient;

	private IOperateManager<Role, Long> operateManager;

	private TreeService treeService;

	// private List allMenuList<

	// /////////////////////////////////////////////属性///////////////////////////////////////////

	public AuthorityManager getAuthorityManager() {
		return authorityManager;
	}

	public TreeService getTreeService() {
		return treeService;
	}

	public void setTreeService(TreeService treeService) {
		this.treeService = treeService;
	}

	/**
	 * @return the webServiceClient
	 */
	// public IUserService getWebServiceClient() {
	// return webServiceClient;
	// }
	//
	// /**
	// * @param webServiceClient the webServiceClient to set
	// */
	// public void setWebServiceClient(IUserService webServiceClient) {
	// this.webServiceClient = webServiceClient;
	// }

	public void setAuthorityManager(AuthorityManager authorityManager) {
		this.authorityManager = authorityManager;
	}

	public void setOperateManager(IOperateManager<Role, Long> operateManager) {
		this.operateManager = operateManager;
		// 初始化
		this.operateManager.setEntityClass(Role.class);
		this.operateManager.setPKClass(Long.class);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<MenuDTO> getMenuDTOs() {
		return menuDTOs;
	}

	public void setMenuDTOs(List<MenuDTO> menuDTOs) {
		this.menuDTOs = menuDTOs;
	}

	/**
	 * @return the menusOfRole
	 */

	public IOperateManager<Role, Long> getOperateManager() {
		return operateManager;
	}

	// /////////////////////////////////////////////构造方法///////////////////////////////////////////

	public RoleDataAction() {
	}

	// /////////////////////////////////////////////方法///////////////////////////////////////////

	/**
	 * 加载数据
	 * 
	 * @return
	 */
	public String load() throws Exception {
		if (id != null) {
			this.role = this.operateManager.findById(this.id);
		}
		return SUCCESS;
	}

	// private List<TreeDTO> getTreeList(String isMenu,String
	// checkBoxName)throws Exception{
	// String menuStr=",";
	// Role roleTmp=this.operateManager.findById(this.id);
	// List<Authority> menuList=roleTmp.getAuthoritys();
	// for(Authority menu:menuList){
	// menuStr=menuStr+menu.getId()+",";
	// }
	// String sql = "select t.id id,t.menu_name name,t.parent_id parentId "
	// + " from tb_app_menu t "
	// + " where t.is_menu="+isMenu;
	// // + " and t.status_flag="+ AppConst.ENABLED;
	// TreeParamDTO paramDTO=new TreeParamDTO();
	// paramDTO.setHasParent(true);
	// paramDTO.setParentId(0l);
	// paramDTO.setSql(sql);
	// paramDTO.setParentIdColumnName("parent_id");
	// paramDTO.setChStr(menuStr);
	// paramDTO.setCheckBoxName(checkBoxName);
	// return this.treeService.getTreeNodeList(paramDTO);
	// }
	
	@SkipValidation
	public String loadMenus() throws Exception {
		this.getResponse().setHeader("Cache-Control", "no-cache");
		this.getResponse().setContentType("text/json;charset=UTF-8");
		this.getResponse().setCharacterEncoding("UTF-8");

		PrintWriter out = this.getResponse().getWriter();
		String menuStr = ",";
		this.operateManager.setEntityClass(Role.class);
		Long roleId=null;
		if(this.id!=null){
			roleId=this.id;
		}else if(this.role!=null && this.role.getRoleId()!=null){
			roleId=role.getRoleId();
		}
		if(roleId!=null){
			Role roleTmp = this.operateManager.findById(roleId);
			List<Authority> menus = roleTmp.getAuthoritys();
			for (Authority menu : menus) {
				menuStr = menuStr + menu.getId() + ",";
			}
		}
		
		String sqlMenu = "select t.id id,t.menu_name name,t.parent_id parentId,t.is_menu ismenu "
				+ " from tb_app_menu t "
				+ " where t.is_menu='1' and  t.status_flag="
				+ AppConst.ENABLED;
		TreeParamDTO paramDTO = new TreeParamDTO();
		paramDTO.setHasParent(true);
		paramDTO.setParentId(null);
		paramDTO.setSql(sqlMenu);
		paramDTO.setParentIdColumnName("parent_id");
		paramDTO.setChStr(menuStr);
		paramDTO.setCheckBoxName("menu_tree_chk");
		paramDTO.setHasChildTable(false);
		List<TreeDTO> menuList = this.treeService.getTreeNodeList(paramDTO);// getTreeList("1","menu_tree_chk");
		// List<TreeDTO> listTmp=new ArrayList();
		// for(int i=0;i<menuList.size();i++){
		// TreeDTO dtoTmp=menuList.get(i);
		// if(dtoTmp.getFlag()!=null && dtoTmp.getFlag().equals("1")){
		// listTmp.add(dtoTmp);
		// }
		// }
		String json = null;
		JSONArray baseArray = null;
		baseArray = JSONArray.fromObject(menuList);
		if (baseArray != null)
			json = baseArray.toString();
		else
			json = "[]";
		System.out.println(json);
		out.print(json);
		out.flush();
		out.close();
		return null;
	}
	
	@SkipValidation
	public String loadAuthoritys() throws Exception {
		this.getResponse().setHeader("Cache-Control", "no-cache");
		this.getResponse().setContentType("text/json;charset=UTF-8");
		this.getResponse().setCharacterEncoding("UTF-8");
		PrintWriter out = this.getResponse().getWriter();
		String menuStr = ",";
		this.operateManager.setEntityClass(Role.class);
		Long roleId=null;
		if(this.id!=null){
			roleId=this.id;
		}else if(this.role!=null && this.role.getRoleId()!=null){
			roleId=role.getRoleId();
		}
		if(roleId!=null){
			Role roleTmp = this.operateManager.findById(roleId);
			List<Authority> menuList = roleTmp.getAuthoritys();
			for (Authority menu : menuList) {
				menuStr = menuStr + menu.getId() + ",";
			}
		}
		
		String sqlMenu = "select t.id id,t.menu_name name,t.parent_id parentId,t.is_menu ismenu "
				+ " from tb_app_menu t "
				+ " where t.is_menu='0' and  t.status_flag="
				+ AppConst.ENABLED;
		TreeParamDTO paramDTO = new TreeParamDTO();
		paramDTO.setHasParent(true);
		paramDTO.setParentId(null);
		paramDTO.setSql(sqlMenu);
		paramDTO.setParentIdColumnName("parent_id");
		paramDTO.setChStr(menuStr);
		paramDTO.setCheckBoxName("auth_tree_chk");
		paramDTO.setHasChildTable(false);
		List<TreeDTO> authorityList = this.treeService.getTreeNodeList(paramDTO);
		// List<TreeDTO> listTmp=new ArrayList();
		// for(int i=0;i<authorityList.size();i++){
		// TreeDTO dtoTmp=authorityList.get(i);
		// if(dtoTmp.getFlag()!=null && dtoTmp.getFlag().equals("0")){
		// listTmp.add(dtoTmp);
		// }
		// }
		String json = null;
		JSONArray baseArray = null;
		baseArray = JSONArray.fromObject(authorityList);
		if (baseArray != null)
			json = baseArray.toString();
		else
			json = "[]";
		System.out.println(json);
		out.print(json);
		out.flush();
		out.close();
		return null;
	}

	/**
	 * 保存:插入或编辑
	 * 
	 * @return
	 */
	@LogBusinessKey(objectType = Constants.ELE_TYPE_LOG_2, objectKey = "log_sys_role", methodKey = "save")
	public String save() throws Exception {
		Authority menu;
		if (role != null && role.getRoleId() == null) {// new role to add
			List<Role> l = this.operateManager.findBy("name", role.getName());
			if (l != null && !l.isEmpty()) {
				this.addActionError(getText("errors.existing.name"));
				return INPUT;
			}
		}
		try {

			String[] menus = this.getRequest().getParameterValues(
					"menu_tree_chk");
			String[] authoritys = this.getRequest().getParameterValues(
					"auth_tree_chk");
			if (menus != null) {
				for (int i = 0; i < menus.length; i++) {
					menu = this.authorityManager.get(Long.valueOf(menus[i]));
					role.addAuthority(menu);
				}
			}

			if (authoritys != null) {
				for (int j = 0; j < authoritys.length; j++) {
					menu = this.authorityManager.get(Long
							.valueOf(authoritys[j]));
					role.addAuthority(menu);
				}
			}

			role.setLastUpdatedBy(SpringSecurityUtils.getCurrentUserName());
			role.setLastUpdatedTime(new Date());
			role.setStatusFlag(1);
			this.operateManager.merge(role);

			// log2db
			super.setOperatorPK(this.role.getRoleId());
			this.setOperatorContent(this.getText("role.save.log")+
					this.getText("role.name")+":"+
					this.role.getName());

			// 权限项变更时，拥有该权限的用户都被标识，以便
			// authorityManager.saveAuthorityChanged(role);
			// log.debug(menusOfRole);
			this.saveMessage(getText("message.save.success"));

			return SUCCESS;
		} catch (Exception e) {
			this.addActionError(getText("error.save"));
			log.error(e.getMessage(), e);
			return INPUT;
		}
	}

	@LogBusinessKey(objectType = Constants.ELE_TYPE_LOG_2, objectKey = "log_sys_role", methodKey = "delete")
	public String delete() throws Exception {
		if (this.id != null) {
			this.operateManager.setEntityClass(Role.class);
			Role roleTmp = this.operateManager.findById(this.id);
//			role.setEnabled(Integer.valueOf(AppConst.DISENABLED));
//			operateManager.merge(role);
			try {
				List<User> users = authorityManager.getUsersbyRoleId(""
						+ this.id);
				if (users == null || users.isEmpty()) {
					roleTmp.setStatusFlag(0);
					this.operateManager.merge(roleTmp);
//					this.operateManager.delete(this.id);
					this.saveMessage(getText("message.delete.success"));
				} else {
					this.addActionError(getText("error.delete")+getText("role.delete.users.error"));
				}
				
			} catch (Exception ex) {
				this.saveMessage(getText("message.delete.error"));
			}
			super.setOperatorPK(roleTmp.getRoleId());
			this.setOperatorContent(this.getText("role.delete.log")+
					this.getText("role.name")+
					roleTmp.getName());
		}
		return "list";
	}

	/**
	 * 角色类型下拉框中，来源于数据字典组roleType的数据项
	 */
	@SuppressWarnings("rawtypes")
	public List getAllRoleTypes() {
		List allRoleTypes = super.getItemsOfGroup("roleType");
		return allRoleTypes;
	}
}
