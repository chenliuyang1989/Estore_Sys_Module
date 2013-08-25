package com.gmcc.dao.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import com.gmcc.dao.model.AbstractUser;
import com.gmcc.model.Element;
import com.gmcc.model.HrCompany;
import com.gmcc.model.Role;
import com.ibm.util.annotation.DisplayColumn;

@MappedSuperclass
public abstract class AbstractUser {

	protected Long userId;
	@DisplayColumn(property = "username", titleKey = "user.username", initFlag = "1")
	protected String username; // 用户名

	protected String roleMark;

	@Transient
	public String getRoleMark() {
		return roleMark;
	}

	public void setRoleMark(String roleMark) {
		this.roleMark = roleMark;
	}

	protected String cityName;

	@Transient
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	protected String channelName;

	@Transient
	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	protected String password; // 密码
	@DisplayColumn(property = "fullname", titleKey = "user.fullname", initFlag = "1")
	protected String fullname; // 用户名
	@DisplayColumn(property = "phoneNumber", titleKey = "user.phoneNumber", initFlag = "1")
	protected String phoneNumber;// 电话
	protected boolean accountExpired;// 是否过期
	protected String sex = "男";
	protected Long entId; // 企业ID号
	protected String identifyNumber; // 身份证号码
	protected Date recordedTime; // 备案时间
	@DisplayColumn(property = "createdTime", titleKey = "user.createdTime", decorator = "com.gmcc.decorator.LongTimeWrapper", initFlag = "1")
	protected Date createdTime; // 创建时间
	@DisplayColumn(property = "lastUpdatedTime", titleKey = "user.lastUpdatedTime", decorator = "com.gmcc.decorator.LongTimeWrapper", initFlag = "1")
	protected Date lastUpdatedTime; // 最后更新时间
	@DisplayColumn(property = "lastUpdatedBy", titleKey = "user.lastUpdatedBy", initFlag = "1")
	private String lastUpdatedBy; // 最后更新人
	protected String remark;

	@DisplayColumn(property = "citySysID", titleKey = "field.city", decorator = "com.gmcc.decorator.CityWrapper", initFlag = "1")
	protected String citySysID;
	@DisplayColumn(property = "channel", titleKey = "field.channel", decorator = "com.gmcc.decorator.ChannelWrapper", initFlag = "1")
	protected String channel;

	private Element userType;
	@DisplayColumn(property = "userTypeName", titleKey = "user.type", initFlag = "1")
	private String userTypeName;
	@DisplayColumn(property = "activateTime", initFlag = "1", titleKey = "user.enabled.time", decorator = "com.gmcc.decorator.LongTimeWrapper", sortable = true)
	private Date activateTime;// 激活时间
	protected boolean enabled;// 是否活动
	protected Set<Role> roles = new HashSet<Role>();// 角色
	protected Set<HrCompany> hrCompanys = new HashSet<HrCompany>();// 组织

	private Long roleId;
	private Long compId;
	@DisplayColumn(property = "roleStr", titleKey = "user.role", initFlag = "1")
	private String roleStr;
	@DisplayColumn(property = "compStr", titleKey = "user.comp", initFlag = "1")
	private String compStr;
	@DisplayColumn(property = "isPhoneCheck", titleKey = "user.isPhoneCheck", decorator = "com.gmcc.decorator.IsNeedInvoice", initFlag = "1")
	protected Long isPhoneCheck;
	@DisplayColumn(property = "status", titleKey = "user.enabled", decorator = "com.gmcc.decorator.IsNeedInvoice", initFlag = "1")
	private String status;

	@DisplayColumn(property = "lastLoginTime", titleKey = "user.lastLoginTime", initFlag = "1", decorator = "com.gmcc.decorator.LongTimeWrapper")
	protected Date lastLoginTime; // 最后登录时间
	private Long errorNum;
	private String phoneMsg;
	private Date phoneSendTime;

	public AbstractUser() {
	}

	public AbstractUser(final String username) {
		this.username = username;
	}

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "channel")
	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	@Column(name = "citySysID")
	public String getCitySysID() {
		return citySysID;
	}

	public void setCitySysID(String citySysID) {
		this.citySysID = citySysID;
	}

	@Column(name = "FULL_NAME")
	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	@Column(name = "ep_id")
	public Long getEntId() {
		return entId;
	}

	public void setEntId(Long entId) {
		this.entId = entId;
	}

	@Column(name = "identify_num")
	public String getIdentifyNumber() {
		return identifyNumber;
	}

	public void setIdentifyNumber(String identifyNumber) {
		this.identifyNumber = identifyNumber;
	}

	@Column(name = "created_time")
	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	@Column(name = "last_updated_time")
	public Date getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	public void setLastUpdatedTime(Date lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}

	@Column(name = "last_login_time")
	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	// @Temporal(TemporalType.TIMESTAMP)
	@Column(name = "activate_time")
	public Date getActivateTime() {
		return activateTime;
	}

	public void setActivateTime(Date activateTime) {
		this.activateTime = activateTime;
	}

	@Column(name = "last_updated_By")
	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Column(name = "username", nullable = false, length = 50, unique = true)
	public String getUsername() {
		return username;
	}

	@Column(nullable = false)
	public String getPassword() {
		return password;
	}

	@Column(name = "TELEPHONE")
	public String getPhoneNumber() {
		return phoneNumber;
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "TR_APP_USER_ROLE", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = @JoinColumn(name = "role_id"))
	public Set<Role> getRoles() {
		return roles;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "TB_R_ORG_USER", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = @JoinColumn(name = "comp_id"))
	public Set<HrCompany> getHrCompanys() {
		return hrCompanys;
	}

	public void setHrCompanys(Set<HrCompany> hrCompanys) {
		this.hrCompanys = hrCompanys;
	}

	@Column(name = "account_enabled")
	public boolean isEnabled() {
		return enabled;
	}

	@Column(name = "account_expired", nullable = false)
	public boolean isAccountExpired() {
		return accountExpired;
	}

	@Transient
	public boolean isAccountNonExpired() {
		return !isAccountExpired();
	}

	@Column(name = "account_locked", nullable = false)
	public boolean isAccountLocked() {
		return !enabled;
	}

	@Transient
	public boolean isAccountNonLocked() {
		return !isAccountLocked();
	}

	@Column(name = "credentials_expired", nullable = false)
	public boolean isCredentialsExpired() {
		return !enabled;
	}

	@Transient
	public boolean isCredentialsNonExpired() {
		return !!enabled;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void setAccountExpired(boolean accountExpired) {
		this.accountExpired = accountExpired;
	}

	public void setAccountLocked(boolean accountLocked) {
		this.enabled = !accountLocked;
	}

	public void setCredentialsExpired(boolean credentialsExpired) {
		this.enabled = !credentialsExpired;
	}

	@Column(name = "ERRORNUM")
	public Long getErrorNum() {
		return errorNum;
	}

	public void setErrorNum(Long errorNum) {
		this.errorNum = errorNum;
	}

	@Column(name = "ISPHONECHECK")
	public Long getIsPhoneCheck() {
		return isPhoneCheck;
	}

	public void setIsPhoneCheck(Long isPhoneCheck) {
		this.isPhoneCheck = isPhoneCheck;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_type")
	public Element getUserType() {
		return userType;
	}

	public void setUserType(Element userType) {
		this.userType = userType;
	}

	@Transient
	public String getUserTypeName() {
		return userTypeName;
	}

	public void setUserTypeName(String userTypeName) {
		this.userTypeName = userTypeName;
	}

	@Transient
	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	@Transient
	public Long getCompId() {
		return compId;
	}

	public void setCompId(Long compId) {
		this.compId = compId;
	}

	@Transient
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Transient
	public String getRoleStr() {
		return roleStr;
	}

	public void setRoleStr(String roleStr) {
		this.roleStr = roleStr;
	}

	@Transient
	public String getCompStr() {
		return compStr;
	}

	public void setCompStr(String compStr) {
		this.compStr = compStr;
	}

	@Column(name = "PHONE_MSG")
	public String getPhoneMsg() {
		return phoneMsg;
	}

	public void setPhoneMsg(String phoneMsg) {
		this.phoneMsg = phoneMsg;
	}

	@Column(name = "PHONE_SEND_TIME")
	public Date getPhoneSendTime() {
		return phoneSendTime;
	}

	public void setPhoneSendTime(Date phoneSendTime) {
		this.phoneSendTime = phoneSendTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 18;
		result = prime * result
				+ ((fullname == null) ? 0 : fullname.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (getClass() != obj.getClass())
			return false;
		AbstractUser other = (AbstractUser) obj;
		if (fullname == null) {
			if (other.fullname != null)
				return false;
		} else if (!fullname.equals(other.fullname))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AbstractUser [fullname=" + fullname + ", id=" + userId
				+ ", username=" + username + "]";
	}

	@Transient
	public String getId() {
		return userId.toString();
	}

	@Transient
	public String getBusinessEmail() {
		return phoneNumber;
	}

	@Transient
	public String getFamilyName() {
		return fullname;
	}

	@Transient
	public String getGivenName() {
		return fullname;
	}

	private String updateTimeStart;
	private String updateTimeEnd;
	private String createTimeStart;
	private String createTimeEnd;
	private String loginTimeStart;
	private String loginTimeEnd;

	@Transient
	public String getLoginTimeStart() {
		return loginTimeStart;
	}

	public void setLoginTimeStart(String loginTimeStart) {
		this.loginTimeStart = loginTimeStart;
	}

	@Transient
	public String getLoginTimeEnd() {
		return loginTimeEnd;
	}

	public void setLoginTimeEnd(String loginTimeEnd) {
		this.loginTimeEnd = loginTimeEnd;
	}

	@Transient
	public String getUpdateTimeStart() {
		return updateTimeStart;
	}

	public void setUpdateTimeStart(String updateTimeStart) {
		this.updateTimeStart = updateTimeStart;
	}

	@Transient
	public String getUpdateTimeEnd() {
		return updateTimeEnd;
	}

	public void setUpdateTimeEnd(String updateTimeEnd) {
		this.updateTimeEnd = updateTimeEnd;
	}

	@Transient
	public String getCreateTimeStart() {
		return createTimeStart;
	}

	public void setCreateTimeStart(String createTimeStart) {
		this.createTimeStart = createTimeStart;
	}

	@Transient
	public String getCreateTimeEnd() {
		return createTimeEnd;
	}

	public void setCreateTimeEnd(String createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}
}
