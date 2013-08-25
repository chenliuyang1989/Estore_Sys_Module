package com.gmcc.model;

// default package

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.GrantedAuthority;
import org.springframework.security.userdetails.UserDetails;

import com.gmcc.dao.model.AbstractUser;
import com.gmcc.model.Role;
import com.ibm.service.IUpdateMassageEntity;

@Entity
@Table(name = "TB_APP_USER")
public class User extends AbstractUser implements Serializable, UserDetails,
		IUpdateMassageEntity {

	private static final long serialVersionUID = 3187692190232589269L;
	// Constructors

	private GrantedAuthority[] authorities; // 功能权限项

	private String newPassword;// 确认密码
	private String confirmPassword;// 确认密码
	
	private Map authoritiesData=new HashMap();
	private String roleTypeCode;

	/** default constructor */
	public User() {
	}

	public User(final String username) {
		super(username);
	}

	public void setAuth(GrantedAuthority[] authorities) {
		this.authorities = authorities;
	}

	@Transient
	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	@Transient
	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	@Transient
	public List<LabelValue> getRoleList() {
		List<LabelValue> userRoles = new ArrayList<LabelValue>();

		if (super.getRoles() != null) {
			for (Role role : super.getRoles()) {
				userRoles.add(new LabelValue(role.getName(), role.getName()));
			}
		}

		return userRoles;
	}

	public void addRole(Role role) {
		getRoles().add(role);
	}
	public void addComp(HrCompany comp) {
		getHrCompanys().add(comp);
	}
	@Transient
	public GrantedAuthority[] getAuthorities() {
		return authorities;
	}

	public void setCreatedBy(String createdBy) {

	}

	@Transient
	public String getCreatedBy() {
		return null;
	}

	@Transient
	public boolean getIfCanDelete() {
		if (this.getRoles() != null && this.getRoles().size() > 0) {
			return false;
		}
		return true;
	}
	@Transient
	public Map getAuthoritiesData() {
		return authoritiesData;
	}

	public void setAuthoritiesData(Map authoritiesData) {
		this.authoritiesData = authoritiesData;
	}
	@Transient
	public String getRoleTypeCode() {
		return roleTypeCode;
	}

	public void setRoleTypeCode(String roleTypeCode) {
		this.roleTypeCode = roleTypeCode;
	}

}
