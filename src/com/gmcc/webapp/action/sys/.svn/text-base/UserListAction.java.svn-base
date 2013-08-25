package com.gmcc.webapp.action.sys;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.interceptor.validation.SkipValidation;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.gmcc.cons.element.Constants;
import com.gmcc.dto.TreeDTO;
import com.gmcc.model.Element;
import com.gmcc.model.Role;
import com.gmcc.model.User;
import com.gmcc.service.UserService;
import com.gmcc.service.tree.TreeService;
import com.gmcc.util.AppConst;
import com.gmcc.util.AppContentGmcc;
import com.gmcc.util.SpringSecurityUtils;
import com.gmcc.webapp.action.base.DisplayTagQueryAction;
import com.ibm.util.StringUtil;
import com.ibm.util.annotation.CacheCondtion;
import com.ibm.util.annotation.LogBusinessKey;

/**
 * 用户列表子类，依赖父类DisplayTagQueryAction实现全部display标签支持, 泛型指定操作实体和主码类型
 */
public class UserListAction extends DisplayTagQueryAction<User, Long> {

	// /////////////////////////////////////////////属性///////////////////////////////////////////
	private static final long serialVersionUID = 2179464214530333059L;

	private User user;
	private TreeService treeService;
	private UserService userService;
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public TreeService getTreeService() {
		return treeService;
	}

	public void setTreeService(TreeService treeService) {
		this.treeService = treeService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * 查询条件：用户名
	 */
	
	@CacheCondtion
	private String username;

	// /////////////////////////////////////////////存取方法///////////////////////////////////////////
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	// /////////////////////////////////////////////构造方法///////////////////////////////////////////
	public UserListAction() {
	}

    private String roleId;
 
    
	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
	private String[] rolebox;
	

	
	public String[] getRolebox() {
		return rolebox;
	}

	public void setRolebox(String[] rolebox) {
		this.rolebox = rolebox;
	}

	/**
	 * 记录日志
	 * @param ids
	 * @param content
	 */
	@LogBusinessKey(objectType = Constants.ELE_TYPE_LOG_2, objectKey = "log_sys_user", methodKey = "update")
	private void saveLog(String[] ids,String content){
		for(int i=0;i<ids.length;i++){
			userService.setEntityClass(User.class);
			User userTmp=userService.findById(Long.valueOf(ids[i]));
			this.setOperatorPK(userTmp.getUserId());
			this.setOperatorContent(content+
					this.getText("user.username")+
					userTmp.getUsername());
		}
	}
	/**
	 * 锁定用户
	 * @return
	 * @throws Exception
	 */
	@SkipValidation
	@LogBusinessKey(objectType = Constants.ELE_TYPE_LOG_2, objectKey = "log_sys_user", methodKey = "update")
	public String delete() throws Exception {
		try{
			String batch=this.getRequest().getParameter("batch");
			String[] chks=null;
			if(batch!=null && "0".equals(batch)){
				chks=new String[]{this.getRequest().getParameter("id")};
			}else{
				chks=this.getRequest().getParameterValues("_chk");
			}
			String userNames="";
			User userTmp=null;
			if(chks!=null && chks.length>0){
				userService.deleteUser(chks);
				for(int i=0;i<chks.length;i++){
					userService.setEntityClass(User.class);
					userTmp=userService.findById(Long.valueOf(chks[i]));
					userNames=userNames+userTmp.getUsername()+",";
					
				}
				userNames=userNames.substring(0,userNames.length()-1);
				this.setOperatorPK(1l);
				this.setOperatorContent(this.getText("user.delete.log")+
						this.getText("user.username")+
						userNames);
			}
		}catch(Exception e){
			this.addActionError(getText("error.save"));
			log.error(e.getMessage(), e);
			return this.queryPage();
		}
//		user=null;
		this.saveMessage(getText("message.save.success"));
		return this.queryPage();
	}
	/**
	 * 激活用户
	 * @return
	 * @throws Exception
	 */
	@SkipValidation
	@LogBusinessKey(objectType = Constants.ELE_TYPE_LOG_2, objectKey = "log_sys_user", methodKey = "enabled")
	public String userEnabledAgain() throws Exception {
		try{
			String batch=this.getRequest().getParameter("batch");
			String[] chks=null;
			if(batch!=null && "0".equals(batch)){
				chks=new String[]{this.getRequest().getParameter("id")};
			}else{
				chks=this.getRequest().getParameterValues("_chk");
			}
			String userNames="";
			User userTmp=null;
			if(chks!=null && chks.length>0){
				userService.userEnabledAgain(chks);
				for(int i=0;i<chks.length;i++){
					userService.setEntityClass(User.class);
					userTmp=userService.findById(Long.valueOf(chks[i]));
					userNames=userNames+userTmp.getUsername()+",";
				}
				userNames=userNames.substring(0,userNames.length()-1);
				this.setOperatorPK(1l);
				this.setOperatorContent(this.getText("user.enabled.log")+
						this.getText("user.username")+
						userNames);
			}
			
		}catch(Exception e){
			this.addActionError(getText("error.save"));
			log.error(e.getMessage(), e);
			return this.queryPage();
		}
//		user=null;
		this.saveMessage(getText("message.save.success"));
		return this.queryPage();
	}
	/**
	 * 开启短信验证
	 * @return
	 * @throws Exception
	 */
	@SkipValidation
	@LogBusinessKey(objectType = Constants.ELE_TYPE_LOG_2, objectKey = "log_sys_user", methodKey = "update")
	public String openPhoneCheck() throws Exception{
		try{
			String[] chks=this.getRequest().getParameterValues("_chk");
			String userNames="";
			User userTmp=null;
			if(chks!=null && chks.length>0){
				this.userService.updPhoneCheck(chks, 1l);
				for(int i=0;i<chks.length;i++){
					userService.setEntityClass(User.class);
					userTmp=userService.findById(Long.valueOf(chks[i]));
					userNames=userNames+userTmp.getUsername()+",";
					
				}
				userNames=userNames.substring(0,userNames.length()-1);
				this.setOperatorPK(1l);
				this.setOperatorContent(this.getText("user.openphonecheck")+
						this.getText("user.username")+
						userNames);
			}
			
		}catch(Exception e){
			this.addActionError(getText("error.save"));
			log.error(e.getMessage(), e);
			return this.queryPage();
		}
//		user=null;
		this.saveMessage(getText("message.save.success"));
		return this.queryPage();
	}
	/**
	 * 关闭短信验证
	 * @return
	 * @throws Exception
	 */
	@SkipValidation
	@LogBusinessKey(objectType = Constants.ELE_TYPE_LOG_2, objectKey = "log_sys_user", methodKey = "update")
	public String closePhoneCheck() throws Exception{
		try{
			String[] chks=this.getRequest().getParameterValues("_chk");
			String userNames="";
			User userTmp=null;
			if(chks!=null && chks.length>0){
				this.userService.updPhoneCheck(chks, 0l);
				for(int i=0;i<chks.length;i++){
					userService.setEntityClass(User.class);
					userTmp=userService.findById(Long.valueOf(chks[i]));
					userNames=userNames+userTmp.getUsername()+",";
					
				}
				userNames=userNames.substring(0,userNames.length()-1);
				this.setOperatorPK(userTmp.getUserId());
				this.setOperatorContent(this.getText("user.closephonecheck")+
						this.getText("user.username")+
						userNames);
			}
		}catch(Exception e){
			this.addActionError(getText("error.save"));
			log.error(e.getMessage(), e);
			return this.queryPage();
		}
//		user=null;
		this.saveMessage(getText("message.save.success"));
		return this.queryPage();
	}

	// ///////////////////////////////重写父类DisplayTagQueryAction的方法//////////////////////////////
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
		List<User> list=userService.getUserList(user,roleId,username,pageNo,super.getPageSize());
		Long count=userService.getUserCount(user,roleId,username);
		super.setTotalRows(count.intValue());
		super.setResultList(list);
		initCookie();
		this.currentUrl="query";
		return SUCCESS;
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
	/*
	 * 新增通过导入文件添加用户按钮跳转
	 */
	public String bassAdd()
	{
		return "bassAdd";
	}
	@SuppressWarnings("rawtypes")
	public List getAllCityList(){
		List<Element> cityList=new ArrayList();
		try{
			cityList=this.getItemsOfGroup(AppContentGmcc.CITY_GROUPNAME);
		}catch(Exception ex){
			log.error(ex.getMessage(), ex);
		}
		return cityList;
	}
	@SuppressWarnings("rawtypes")
	public List getAllUserTypeList(){
		List<Element> userTypeList=new ArrayList();
		try{
			userTypeList=this.getItemsOfGroup(AppContentGmcc.USER_TYPE_GROUP);
		}catch(Exception ex){
			log.error(ex.getMessage(), ex);
		}
		return userTypeList;
	}
	@SuppressWarnings("rawtypes")
	public List getAllChannelList(){
		List<Element> cityList=new ArrayList();
		try{
			cityList=this.getItemsOfGroup(AppContentGmcc.CHANNEL_GROUPNAME);
		}catch(Exception ex){
			log.error(ex.getMessage(), ex);
		}
		return cityList;
	}
	@SuppressWarnings("rawtypes")
	public List getAllCompList(){
		List<TreeDTO> comList=new ArrayList();
		try{
			String sql="select t.comp_id id,t.comp_name name,t.comp_level objLevel,t.parent_id parentId from tb_hr_company t where t.enabled=1  ";
			comList=treeService.getCommTreeList(null, 1, sql, "parent_id ");
		}catch(Exception ex){
			log.error(ex.getMessage(), ex);
		}
		return comList;
	}
	@SuppressWarnings("rawtypes")
	public List getAllRoleList(){
		List<Role> roleList=new ArrayList();
		try{
			roleList=this.userService.getAllRoleList();
		}catch(Exception ex){
			log.error(ex.getMessage(), ex);
		}
		return roleList;
	}
}
