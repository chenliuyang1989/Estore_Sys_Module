package com.gmcc.dto;


public class OrgAuthorityDTO {

	private static final long serialVersionUID = 7955689943502060631L;
	
	private Long id; //系统ID
	private String code; //编码
	private Long orgSysId;//
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Long getOrgSysId() {
		return orgSysId;
	}
	public void setOrgSysId(Long orgSysId) {
		this.orgSysId = orgSysId;
	}
	
}
