package com.gmcc.webapp.action.sys;

import org.hibernate.annotations.common.util.StringHelper;
import org.springframework.security.providers.encoding.PasswordEncoder;

import com.gmcc.model.User;
import com.gmcc.util.SpringSecurityUtils;
import com.gmcc.webapp.action.base.BaseBusinessLogAction;
import com.ibm.service.IOperateManager;
import com.ibm.util.annotation.*;
import com.gmcc.cons.element.Constants;

public class PasswordDataAction extends BaseBusinessLogAction<User, Long> {

	private static final long serialVersionUID = -6246614978237614099L;
	private PasswordEncoder passwordEncoder;
	private IOperateManager<User, Long> operateManager;
	private Long id;
	private User user;

	public PasswordDataAction() {
	}
	
	public void setOperateManager(IOperateManager<User, Long> operateManager) {
		this.operateManager = operateManager;
		this.operateManager.setEntityClass(User.class);
		this.operateManager.setPKClass(Long.class);
	}

	public IOperateManager<User, Long> getOperateManager() {
		return this.operateManager;
	}
	
	public PasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}

	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
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

	public String load() throws Exception {
		this.user = SpringSecurityUtils.getCurrentUser();
		this.getRequest().setAttribute("pass", this.getRequest().getParameter("pass"));
		return SUCCESS;
	}

	@LogBusinessKey(objectType=Constants.ELE_TYPE_LOG_2, objectKey="log_sys_password", methodKey="save")
	public String save() {
		User temp = SpringSecurityUtils.getCurrentUser();
		String newpsw = this.user.getNewPassword();
		this.getRequest().setAttribute("pass", this.getRequest().getParameter("pass"));
		if (!passwordEncoder.encodePassword(this.user.getPassword(), null).equals(temp.getPassword())) {
			this.addActionError(this.getText("password.error.old"));
//			this.getRequest().setAttribute("success", "false");
			return INPUT;
		}
		
		if (StringHelper.isEmpty(this.user.getNewPassword())) {
			this.addActionError(this.getText("password.error.new"));
//			this.getRequest().setAttribute("success", "false");
			return INPUT;
		}

		if (!this.user.getNewPassword().equals(this.user.getConfirmPassword())) {
			this.addActionError(this.getText("password.error.comfirmpassword"));
//			this.getRequest().setAttribute("success", "false");
			return INPUT;
		}
		try{
			//save
			this.user = temp;
			user.setPassword(passwordEncoder.encodePassword(newpsw, null));
			this.user = (User) operateManager.merge(user);
		}catch(Exception e){
			this.addActionError(e.getMessage());
//			this.getRequest().setAttribute("success", "false");
			return INPUT;
		}
		
		//log2db
		super.setOperatorPK(this.user.getUserId());
		super.setOperatorContent(this.getText("password.save.log"));
//		this.getRequest().setAttribute("success", "true");
		this.saveMessage(getText("message.save.success"));
		return SUCCESS;
	}
}
