package com.gmcc.dao;

import java.util.List;

import com.gmcc.dto.OrgAuthorityDTO;
import com.gmcc.model.Element;
import com.gmcc.model.HrCompany;

public interface HrCompanyDAO {

	public List<OrgAuthorityDTO> getOrgParList(Long userId,Long orgType);
	
	public List<OrgAuthorityDTO> getOrgChildList(Long parId,Long userId);
	
	public List getOrgList(String sql);
	
	public int updateOrg(String sql);
	
	public HrCompany getHrCompanyByName(String name);
	
	public Element getElementById(Long eleId);
	
	public Long getCompTypeById(Long compId);
}
