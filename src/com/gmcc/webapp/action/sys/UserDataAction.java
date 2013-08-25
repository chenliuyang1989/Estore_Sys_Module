package com.gmcc.webapp.action.sys;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.hibernate.annotations.common.util.StringHelper;
import org.springframework.security.providers.encoding.PasswordEncoder;

import com.gmcc.cons.element.Constants;
import com.gmcc.dto.TreeDTO;
import com.gmcc.dto.TreeParamDTO;
import com.gmcc.model.Element;
import com.gmcc.model.HrCompany;
import com.gmcc.model.Role;
import com.gmcc.model.User;
import com.gmcc.service.AuthorityManager;
import com.gmcc.service.tree.TreeService;
import com.gmcc.util.AppConst;
import com.gmcc.util.AppContentGmcc;
import com.gmcc.util.SpringSecurityUtils;
import com.gmcc.webapp.action.base.BaseBusinessLogAction;
import com.ibm.service.IOperateManager;
import com.ibm.util.annotation.*;

/**
 * 用户明细信息类
 */
public class UserDataAction extends BaseBusinessLogAction<User, Long> {

	// /////////////////////////////////////////////属性///////////////////////////////////////////
	private static final long serialVersionUID = 2803408971231003363L;
	private PasswordEncoder passwordEncoder;
	private TreeService treeService;
	/**
	 * 实体主码,接收ID参数用于加载实体
	 */
	private Long id;
	/**
	 * 用户密码，用于保存编辑前的密码做备份使用
	 */
	private String psw;
	/**
	 * 用户实体对象
	 */
	private User user;

	/**
	 * 全部角色类型
	 */
	private List<Role> availableRoles = new ArrayList<Role>();

	private String[] userRoles = new String[0];

	protected IOperateManager<Role, Long> roleOperateManager;
	protected IOperateManager<User, Long> operateManager;
	protected IOperateManager<HrCompany, Long> compOperateManager;

	private AuthorityManager authorityManager;
	

	// /////////////////////////////////////////////存取方法///////////////////////////////////////////

	// protected IOperateManager<User, Long> userOperateManager;

	public String getPsw() {
		return psw;
	}

	public String[] getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(String[] userRoles) {
		this.userRoles = userRoles;
	}

	public IOperateManager<Role, Long> getRoleOperateManager() {
		return roleOperateManager;
	}

	public TreeService getTreeService() {
		return treeService;
	}

	public void setTreeService(TreeService treeService) {
		this.treeService = treeService;
	}

	public void setOperateManager(IOperateManager<User, Long> operateManager) {
		this.operateManager = operateManager;
		// 指定操作类型,获取来自子类指定泛型类型，并传送给服务层（服务层会再传给DAO层)
		this.operateManager.setEntityClass(User.class);
		this.operateManager.setPKClass(Long.class);
	}

	public IOperateManager<User, Long> getOperateManager() {
		return this.operateManager;
	}

	public void setRoleOperateManager(
			IOperateManager<Role, Long> roleOperateManager) {
		this.roleOperateManager = roleOperateManager;
		// 指定操作类型,获取来自子类指定泛型类型，并传送给服务层（服务层会再传给DAO层)
		this.roleOperateManager.setEntityClass(Role.class);
		this.roleOperateManager.setPKClass(Long.class);
	}
	public void setCompOperateManager(
			IOperateManager<HrCompany, Long> compOperateManager) {
		this.compOperateManager = compOperateManager;
		this.compOperateManager.setEntityClass(HrCompany.class);
		this.compOperateManager.setPKClass(Long.class);
	}
	public List<Role> getAvailableRoles() {
		return availableRoles;
	}

	public void setAvailableRoles(List<Role> availableRoles) {
		this.availableRoles = availableRoles;
	}

	public void setPsw(String psw) {
		this.psw = psw;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public PasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}

	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	/**
	 * @return the authorityManager
	 */
	public AuthorityManager getAuthorityManager() {
		return authorityManager;
	}

	/**
	 * @param authorityManager
	 *            the authorityManager to set
	 */
	public void setAuthorityManager(AuthorityManager authorityManager) {
		this.authorityManager = authorityManager;
	}

	// /////////////////////////////////////////////构造方法///////////////////////////////////////////

	public UserDataAction() {
	}

	// /////////////////////////////////////////////方法///////////////////////////////////////////

	/**
	 * 初始化列表
	 */
	private void initSelectAvailableRoles() {
		List<Role> rolelist = (new ArrayList<Role>());
		this.availableRoles = this.roleOperateManager.findByExample(new Role());
		rolelist.addAll(this.availableRoles);
		for (Role role : rolelist) {
			if (this.user.getRoles().contains(role)) {
				this.availableRoles.remove(role);
			}
		}
	}

	/**
	 * 加载数据
	 * 
	 * @return
	 */
	public String load() throws Exception {
		if (id != null) {
			if (id == 3) {
				throw new Exception("无法查看");
			}
			this.operateManager.setEntityClass(User.class);
			this.user = this.operateManager.findById(this.id);
			this.psw = this.user.getPassword();
			if(this.getRequest().getParameter("enable")!=null && "true".equals(this.getRequest().getParameter("enable")))
				this.user.setEnabled(true);
		} else {
			this.user = new User();
			this.user.setEnabled(true);
		}

		this.initSelectAvailableRoles();
		setCity2Request();
		return SUCCESS;
	}

	@SkipValidation
	public String loadRoles() throws Exception {
		
		this.getResponse().setHeader("Cache-Control", "no-cache");

		this.getResponse().setContentType("text/json;charset=UTF-8");

		this.getResponse().setCharacterEncoding("UTF-8");

		PrintWriter out = this.getResponse().getWriter();

//		String root = this.getRequest().getParameter("root");
		String roleStr=",";
		this.operateManager.setEntityClass(User.class);
		Long userId=null;
		if(this.id!=null){
			userId=this.id;
		}else if(this.user!=null && user.getUserId()!=null){
			userId=this.user.getUserId();
		}
		String roleStrTmp=this.getRequest().getParameter("roleStr");
		if(roleStrTmp!=null && !"".equals(roleStrTmp)){
			roleStr+=roleStrTmp;
		}
		else if(userId!=null){
			if(user==null || user.getHrCompanys()==null || user.getRoles().size()<=0){
				this.user = this.operateManager.findById(userId);
			}
			this.user = this.operateManager.findById(userId);
			Set<Role> roleSet=this.user.getRoles();
			Iterator<Role> it=roleSet.iterator();
			while(it.hasNext()){
				roleStr=roleStr+it.next().getRoleId()+",";
			}
		}
		
//		String sql = "select t.comp_id id,t.comp_code code,t.comp_level objLevel,t.comp_name name,t.parent_id parentId "
//			+ "from tb_hr_company t "
//			+ "where t.enabled="
//			+ AppConst.ENABLED;
		String sql="select t.id id, t.ELE_NAME name " +
				"from TB_APP_ELEMENT t,TB_APP_ELEMENT_GROUP tg " +
				"where t.ELE_GROUP_ID=tg.id and t.STATUS_FLAG='1' and tg.STATUS_FLAG='1' and tg.ELE_GROUP_NAME='"+AppContentGmcc.ROLE_TYPE_GROUPNAME+"'";
		String sqlRole = "select t.id id,t.name name,t.role_type type "
			+ "from tb_app_role t "
			+ "where t.status_flag="+ AppConst.ENABLED;
		TreeParamDTO paramDTO=new TreeParamDTO();
		paramDTO.setHasParent(false);
		paramDTO.setParentId(null);
		paramDTO.setSql(sql);
		paramDTO.setParentIdColumnName("parent_id");
		paramDTO.setChStr(roleStr);
		paramDTO.setCheckBoxName("role_type_tree_chk");
		
		paramDTO.setChildTableSql(sqlRole+" and t.role_type=");
		paramDTO.setHasChildTable(true);
		paramDTO.setChildTabCheckBoxName("role_tree_chk");
		paramDTO.setTypeName("typeName");
//		paramDTO.setHasRadio(true);
		List<TreeDTO> treeList = this.treeService.getTreeNodeList(paramDTO);
		String json = null;
		JSONArray baseArray = null;
		baseArray = JSONArray.fromObject(treeList);
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
//	@SkipValidation
//	public String loadHrCompanys2() throws Exception {
//		
//		this.getResponse().setHeader("Cache-Control", "no-cache");
//
//		this.getResponse().setContentType("text/json;charset=UTF-8");
//
//		this.getResponse().setCharacterEncoding("UTF-8");
//
//		PrintWriter out = this.getResponse().getWriter();
//
////		String root = this.getRequest().getParameter("root");
//		String compStr=",";
//		this.operateManager.setEntityClass(User.class);
//		Long userId=null;
//		if(this.id!=null){
//			userId=this.id;
//		}else if(this.user!=null && user.getUserId()!=null){
//			userId=this.user.getUserId();
//		}
//		String compStrTmp=this.getRequest().getParameter("compStr");
//		if(compStrTmp!=null && !"".equals(compStrTmp)){
//			compStr+=compStrTmp;
//		}
//		else if(userId!=null){
////			if(user==null || user.getHrCompanys()==null || user.getHrCompanys().size()<=0){
//				this.user = this.operateManager.findById(userId);
////			}
//			Set<HrCompany> compSet=this.user.getHrCompanys();
//			Iterator<HrCompany> it=compSet.iterator();
//			while(it.hasNext()){
//				compStr=compStr+it.next().getId()+",";
//			}
//		}
//		
//		String sql = "select t.comp_id id,t.comp_code code,t.comp_level objLevel,t.comp_name name,t.parent_id parentId "
//			+ "from tb_hr_company t "
//			+ "where t.enabled="
//			+ AppConst.ENABLED;
//		TreeParamDTO paramDTO=new TreeParamDTO();
////		paramDTO.setHasParent(true);
////		paramDTO.setParentId(null);
//		paramDTO.setSql(sql);
////		paramDTO.setParentIdColumnName("parent_id");
////		paramDTO.setChStr(compStr);
////		paramDTO.setCheckBoxName("comp_tree_chk");
////		paramDTO.setHasChildTable(false);
////		List<TreeDTO> treeList = this.treeService.getParentList(paramDTO);
//		  String viewBox = this.getRequest().getParameter("viewBox");
//          String root = this.getRequest().getParameter("root");
//          Long parentId=null;
//          if(!"source".equals(root)){
//             parentId=Long.valueOf(root);
//          }
//          String output= getNode(parentId,viewBox,paramDTO);
//          
////		String json = null;
////		JSONArray baseArray = null;
////		baseArray = JSONArray.fromObject(treeList);
////		if (baseArray != null)
////			json = baseArray.toString();
////		else
////			json = "[]";
////		System.out.println(json);
//		out.print(output);
//
//		out.flush();
//
//		out.close();
//		return null;
//	}
//	private String getNode(Long parentId,String viewBox,TreeParamDTO param) throws Exception{
//        StringBuffer sb = new StringBuffer();
//        sb.append("[");
//        List<TreeDTO> temp = null;
//        String sql="";
//        if(parentId==null){
//        	sql=param.getSql()+" and nvl(t.parent_id,0)=0";
//        }else{
//        	sql=param.getSql()+" and t.parent_id="+parentId;
//        }
//        param.setSql(sql);
//       
////        if(treeService.hasSubItem(sql)){//有孩子执行
//            temp = treeService.getParentList(param);//根据code查询其孩子
//            int j=0;
//           for (int i = 0; i < temp.size(); i++,j++) {
//               if(j>0){
//                   sb.append(",");
//               }
//                sb.append(" {");
//                TreeDTO item = (TreeDTO)temp.get(i);
//                String countSql="select count(*) from tb_hr_company where parent_id="+item.getId();
//                if(treeService.hasSubItem(countSql)){//有孩子
//                    sb.append("  \"text\": \""+item.getName()+"\""); //这里是显示的名称
//                    sb.append(",");
//                    sb.append(" \"hasChildren\": true");  //设为true预留会显示+号
//                }else{//叶子节点 显示按钮
//                    sb.append("  \"text\": ");
//                    if("1".equals(viewBox)){
//                        sb.append("\"<input type='checkbox' value='");
//                    }else{
//                        sb.append("\"<input type='radio' name='s' value='");
//                    }
//                    sb.append(item.getId());
//                    sb.append("' />");
//                    sb.append(item.getName());
//                    sb.append("\"");
//                }
//                sb.append(",");
//                sb.append("  \"expanded\": false ");
//                sb.append(",");
//                sb.append(" \"id\": \""+item.getId()+"\"");//给<li>添加id此处为code在树中唯一，以后点击节点动态加载时根据此rot=id的值进行查询
//                sb.append(" }"); 
//            }
////        }
//        sb.append("]");
//        return sb.toString();
//	} 
	@SkipValidation
	public String loadHrCompanys() throws Exception {
		
		this.getResponse().setHeader("Cache-Control", "no-cache");

		this.getResponse().setContentType("text/json;charset=UTF-8");

		this.getResponse().setCharacterEncoding("UTF-8");

		PrintWriter out = this.getResponse().getWriter();

//		String root = this.getRequest().getParameter("root");
		String compStr=",";
		this.operateManager.setEntityClass(User.class);
		Long userId=null;
		if(this.id!=null){
			userId=this.id;
		}else if(this.user!=null && user.getUserId()!=null){
			userId=this.user.getUserId();
		}
		String compStrTmp=this.getRequest().getParameter("compStr");
		if(compStrTmp!=null && !"".equals(compStrTmp)){
			compStr+=compStrTmp;
		}
		else if(userId!=null){
//			if(user==null || user.getHrCompanys()==null || user.getHrCompanys().size()<=0){
				this.user = this.operateManager.findById(userId);
//			}
			Set<HrCompany> compSet=this.user.getHrCompanys();
			Iterator<HrCompany> it=compSet.iterator();
			while(it.hasNext()){
				compStr=compStr+it.next().getId()+",";
			}
		}
		
		String sql = "select t.comp_id id,t.comp_code code,t.comp_level objLevel,t.comp_name name,t.parent_id parentId "
			+ "from tb_hr_company t "
			+ "where t.enabled="
			+ AppConst.ENABLED;
		TreeParamDTO paramDTO=new TreeParamDTO();
		paramDTO.setHasParent(true);
		paramDTO.setParentId(null);
		paramDTO.setSql(sql);
		paramDTO.setParentIdColumnName("parent_id");
		paramDTO.setChStr(compStr);
		paramDTO.setCheckBoxName("comp_tree_chk");
		paramDTO.setHasChildTable(false);
		List<TreeDTO> treeList = this.treeService.getTreeNodeList(paramDTO);
		String json = null;
		JSONArray baseArray = null;
		baseArray = JSONArray.fromObject(treeList);
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
	 * 
	 * @return
	 */
	@LogBusinessKey(objectType = Constants.ELE_TYPE_LOG_2, objectKey = "log_sys_user", methodKey = "save")
	public String save() throws Exception{
		try {
			// 权限信息设定
			user.getRoles().clear();
			user.getHrCompanys().clear();
			String[] roles=this.getRequest().getParameterValues("role_tree_chk");
			String[] comps=this.getRequest().getParameterValues("comp_tree_chk");
			String roleStr="";
			String compStr="";
//			for (int i = 0; this.userRoles != null && i < this.userRoles.length; i++) {
//				String roleId = userRoles[i];
//				Role role = this.roleOperateManager.findUniqueBy("id",
//						Long.valueOf(roleId));
//				user.addRole(role);
//			}
			if(comps!=null){
				for(int j=0;j<comps.length;j++){
					this.compOperateManager.setEntityClass(HrCompany.class);
					HrCompany comp=this.compOperateManager.findById(Long.valueOf(comps[j]));
					compStr=compStr+comps[j]+",";
					user.addComp(comp);
				}
			}
			if(roles==null || roles.length<=0){
				this.addActionError(this.getText("user.norole.error"));
				this.getRequest().setAttribute("compStr", compStr);
				this.getRequest().setAttribute("roleStr", roleStr);
				
				return INPUT;
			}else{
				for(int i=0;i<roles.length;i++){
					this.roleOperateManager.setEntityClass(Role.class);
					Role role=this.roleOperateManager.findById(Long.valueOf(roles[i]));
					roleStr=roleStr+roles[i]+",";
					user.addRole(role);
				}
			}
			
			

			// 验证：确认密码
			if (!this.user.getPassword().equals(this.user.getConfirmPassword())) {
				this.addActionError(this.getText("user.comfirmpassword.error"));
				this.getRequest().setAttribute("compStr", compStr);
				this.getRequest().setAttribute("roleStr", roleStr);
				
				return INPUT;
			}

			// 新增时
			if (this.user.getUserId() == null
					|| this.user.getUserId().longValue() == 0) {
//				this.user.setIsPhoneCheck(1L);
				this.user.setCreatedTime(new Date());// 设定创建时间
				this.user.setActivateTime(new Date());//激活时间
				// 验证:密码必填
				if (StringHelper.isEmpty(this.user.getPassword())) {
					this.addActionError(this
							.getText("user.password.error.notnull"));
					this.getRequest().setAttribute("compStr", compStr);
					this.getRequest().setAttribute("roleStr", roleStr);
					return INPUT;
				}

				// 验证：用户名不能重复
				this.operateManager.setEntityClass(User.class);
				if (this.user.getUserId() == null
						|| this.user.getUserId().longValue() == 0) {
//					this.operateManager.
//					if (this.operateManager.ifExistBy("username",
//							this.user.getUsername())) {
					if(this.authorityManager.getUserbyName(this.user.getUsername())!=null){
						this.addActionError(this.getText("user.username.exist"));
						this.getRequest().setAttribute("compStr", compStr);
						this.getRequest().setAttribute("roleStr", roleStr);
						return INPUT;
					}
				}
				// 验证：一个地市的一个用户只能创建一个账户
				if(user.getCitySysID()!=null && !"".equals(user.getCitySysID().trim()) && user.getFullname()!=null && !"".equals(user.getFullname()))
				{
					if(this.authorityManager.getUserByFullNameAndCityId(this.user.getFullname(), this.user.getCitySysID())!=null)
					{
						this.addActionError("一个地市的一个用户姓名只能创建一个账号");
						return INPUT;
					}
					
				}
				
				// 密码加密
				user.setPassword(passwordEncoder.encodePassword(
						user.getPassword(), null));

			}
			// 编辑时
			else {
				// 验证密码
				if (StringHelper.isEmpty(this.user.getPassword())) {
					this.user.setPassword(this.psw);// 为空时保持原来值
				} else {
					user.setPassword(passwordEncoder.encodePassword(
							user.getPassword(), null));// 不为空则加密
				}
			}
			if(StringHelper.isEmpty(user.getCitySysID())){
				this.addActionError(this
						.getText("user.city.error"));
				this.getRequest().setAttribute("compStr", compStr);
				this.getRequest().setAttribute("roleStr", roleStr);
				return INPUT;
			}
			if(StringHelper.isEmpty(user.getChannel())){
				this.addActionError(this
						.getText("user.channel.error"));
				this.getRequest().setAttribute("compStr", compStr);
				this.getRequest().setAttribute("roleStr", roleStr);
				return INPUT;
			}
			user.setLastUpdatedBy(SpringSecurityUtils.getCurrentUserName());
			user.setLastUpdatedTime(new Date());
			try {
				// 用户信息保存
				user.setIsPhoneCheck(user.getIsPhoneCheck()==null?0L:user.getIsPhoneCheck());
				user.setErrorNum(0L);
				this.user = (User) this.operateManager.merge(user);
//				// 标识该用户权限已经作更新
				authorityManager.saveAuthorityChanged(user.getUsername());

				
				this.saveMessage(getText("message.save.success"));
			} catch (Exception e) {
				this.addActionError(getText("error.save"));
				this.getRequest().setAttribute("compStr", compStr);
				this.getRequest().setAttribute("roleStr", roleStr);
				return INPUT;
//				this.addActionError(e.getMessage());
			}
			this.setOperatorPK(this.user.getUserId());
			this.setOperatorContent(this.getText("user.save.log")+
					this.getText("user.username")+
					user.getUsername());
			return SUCCESS;
		} finally {
			setCity2Request();
			this.initSelectAvailableRoles();
		}

	}

	private void setCity2Request()throws Exception{
		List<Element> cityList=super.getItemsOfGroup(AppContentGmcc.CITY_GROUPNAME);
		this.getRequest().setAttribute("cityList", cityList);
		List<Element> channelList=super.getItemsOfGroup(AppContentGmcc.CHANNEL_GROUPNAME);
		this.getRequest().setAttribute("channelList", channelList);
		List<Element> userTypeList=super.getItemsOfGroup(AppContentGmcc.USER_TYPE_GROUP);
		this.getRequest().setAttribute("userTypeList", userTypeList);
	}
	
	
	@SkipValidation
	public String getUserByName()throws Exception{
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse res = ServletActionContext.getResponse();
		res.setContentType("text/json;charset=UTF-8");
		res.setHeader("Cache-Control", "no-cache");
//		StringBuffer strJson = new StringBuffer("");
		try{
//			String jobId=req.getParameter("jobId");
			String userName=req.getParameter("userName");
			User userTmp=null;
			if(userName!=null && !"".equals(userName))
				userTmp = this.authorityManager.getUserByName(userName, false);
			if(userTmp!=null)
				res.getWriter().write(userTmp.getId());    
			else
				res.getWriter().write("");  
			res.getWriter().flush();
			res.getWriter().close();
		}catch (Exception e) {
			res.getWriter().write("");
			res.getWriter().flush();
			res.getWriter().close();
//			this.addActionError(getText("error.delete"));
//			log.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return null;
	}
		

}
