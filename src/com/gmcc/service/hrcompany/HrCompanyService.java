package com.gmcc.service.hrcompany;

import java.util.List;

import com.gmcc.dto.OrgAuthorityDTO;
import com.gmcc.model.Element;
import com.gmcc.model.HrCompany;
import com.gmcc.model.User;
import com.ibm.service.IOperateManager;

public interface HrCompanyService extends IOperateManager<HrCompany,Long>{

	public HrCompany merge(HrCompany hrCompany)throws Exception;
	
	public List<HrCompany> del(Long hrCompanyId)throws Exception;
	
	public User getUserById(Long id)throws Exception;
	
	public List<HrCompany> delBatch(String[] hrCompanyId) throws Exception;
	
	public HrCompany getHrCompanyByType(Element orgType)throws Exception;
	
	public HrCompany getHrCompanyByOrgId(Element orgType,Long orgSysId)throws Exception;
	
	public Element getElementById(Long eleId)throws Exception;
	
	public Long getCompTypeById(Long compId)throws Exception;
	
	public HrCompany getHrCompanyByName(String name)throws Exception;
	/**
	 * 返回用户可查看组织ID整形数组
	 * @param orgType 组织类型
	 * @return Long[]
	 * @throws Exception
	 */
	public Long[] getOrgSysId(Element orgType)throws Exception;
	/**
	 * 返回用户可查看组织ID对象列表
	  * @param orgType 组织类型
	 * @return List<OrgAuthorityDTO>
	 */
	public List<OrgAuthorityDTO> getOrgAuthorityList(Element orgType)throws Exception;
	/**
	 * 返回用户可查看组织ID字符串列表
	 * @param orgType 组织类型
	 * @return List<String>
	 * @throws Exception
	 */
	public List<String> getOrgAuthorityStrList(Element orgType)throws Exception;
	/**
	 * 批量往组织表插入数据
	 * @param sql
	 * @param orgType
	 * @param orgTypePro(是否采购组织，是否销售组织，是否物流组织，是否仓储组织)
	 * @throws Exception
	 */
	public void insertIntoHrCompany(String sql,Element orgType,String[] orgTypePro)throws Exception;
}
