package com.gmcc.service.impl.hrcompany;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.security.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import com.gmcc.service.hrcompany.HrCompanyService;
import com.gmcc.util.AppContentGmcc;
import com.gmcc.util.SpringSecurityUtils;
import com.gmcc.dao.HrCompanyDAO;
import com.gmcc.dao.UserDAO;
import com.gmcc.dto.OrgAuthorityDTO;
import com.gmcc.model.Element;
import com.gmcc.model.HrCompany;
import com.gmcc.model.JobConfig;
import com.gmcc.model.User;
import com.gmcc.service.BillSnManager;
import com.gmcc.util.AppConst;
import com.ibm.service.IOperateManager;
import com.ibm.service.impl.OperateManagerImp;

public class HrCompanyServiceImpl extends OperateManagerImp<HrCompany,Long>
	implements HrCompanyService{

	private BillSnManager billSnManager;
	private UserDAO userDAO;
	private HrCompanyDAO hrCompanyDAO;
	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public BillSnManager getBillSnManager() {
		return billSnManager;
	}

	public void setBillSnManager(BillSnManager billSnManager) {
		this.billSnManager = billSnManager;
	}

	public HrCompanyDAO getHrCompanyDAO() {
		return hrCompanyDAO;
	}

	public void setHrCompanyDAO(HrCompanyDAO hrCompanyDAO) {
		this.hrCompanyDAO = hrCompanyDAO;
	}

	@Transactional
	public HrCompany merge(HrCompany hrCompany) throws Exception {
		if(hrCompany.getParentId()==null || hrCompany.getParentId().getId()==null){
			hrCompany.setParentId(null);
		}
		if(hrCompany.getIsLogistics()==null || "".equals(hrCompany.getIsLogistics())){
			hrCompany.setIsLogistics("0");
		}
		if(hrCompany.getIsPurchase()==null || "".equals(hrCompany.getIsPurchase())){
			hrCompany.setIsPurchase("0");
		}
		if(hrCompany.getIsSales()==null || "".equals(hrCompany.getIsSales())){
			hrCompany.setIsSales("0");
		}
		if(hrCompany.getIsWareHouse()==null || "".equals(hrCompany.getIsWareHouse())){
			hrCompany.setIsWareHouse("0");
		}
		if(hrCompany.getId()==null){
			hrCompany.setCompCode(this.billSnManager.getBillSn(AppContentGmcc.HRCOMPANY_CODE));	
		}
		if(hrCompany.getCompLevel()==null || "".equals(hrCompany.getCompLevel())){
			hrCompany.setCompLevel("1");
		}
		super.setEntityClass(HrCompany.class);
		hrCompany.setEnabled(AppConst.ENABLED);
		return super.merge(hrCompany);
	}
	@Transactional
	public List<HrCompany> del(Long hrCompanyId) throws Exception {
		// TODO Auto-generated method stub
		this.setEntityClass(HrCompany.class);
		this.setPKClass(Long.class);
		HrCompany com=this.findById(hrCompanyId);
		List<HrCompany> list=new ArrayList();
		list.add(com);
		getChild(hrCompanyId,list);
//		int count=10;
		for(HrCompany hrCom:list){
//			count=;
			if(margeOrg(hrCom)>0)
				super.delete(hrCom.getId());
			else
				 throw new Exception("update hrcompany-org error");
		}
		return list;
	}
	private int margeOrg(HrCompany hrCom){
		String sql="";
		Element orgType=hrCom.getOrgType();
		if(orgType!=null && AppContentGmcc.ORG_TYPE_WAREHOUSE.equals(orgType.getEleCode()) && hrCom.getOrgSysId()!=null){
			sql="update T_BASE_WAREHOUSE set ENABLED="+AppConst.DISENABLED+" where whsysid="+hrCom.getOrgSysId();
		}else if(orgType!=null && AppContentGmcc.ORG_TYPE_LOGISTICS.equals(orgType.getEleCode())&& hrCom.getOrgSysId()!=null){
			sql="update T_BASE_LOGISTICS set ENABLED="+AppConst.DISENABLED+" where LOGISTICSCOPSYSID="+hrCom.getOrgSysId();
		}else if(orgType!=null && AppContentGmcc.ORG_TYPE_PROVIDER.equals(orgType.getEleCode())&& hrCom.getOrgSysId()!=null){
			sql="update T_BASE_PROVIDER set ENABLED="+AppConst.DISENABLED+" where PROVIDERSYSID="+hrCom.getOrgSysId();
		}
		if(sql!=null && !sql.equals(""))
			return this.hrCompanyDAO.updateOrg(sql);
		else
			return 1;
	}
	private void getChild(Long hrCompanyId,List<HrCompany> list){
//		List<HrCompany> listTmp=this.findBy("parentId.id", hrCompanyId);
		String hql="from HrCompany hc where hc.parentId.id=? and hc.enabled=?";
		List<HrCompany> listTmp=this.getGenericDao().find(hql, hrCompanyId,AppConst.ENABLED);
//		List<HrCompany> listTmp=this.hrCompanyDAO.getHrCompanyByPid(hrCompanyId);
		list.addAll(listTmp);
		for(HrCompany com:listTmp){
			getChild(com.getId(),list);
		}
	}
	@Transactional
	public List<HrCompany> delBatch(String[] hrCompanyId) throws Exception {
		// TODO Auto-generated method stub
		List<HrCompany> list=new ArrayList();
		this.setEntityClass(HrCompany.class);
		this.setPKClass(Long.class);
		for(int i=0;i<hrCompanyId.length;i++){
			HrCompany com=super.findById(Long.valueOf(hrCompanyId[i]));
			list.add(com);
			getChild(Long.valueOf(hrCompanyId[i]),list);	
		}
		for(HrCompany hrCom:list){
			if(margeOrg(hrCom)>0)
				super.delete(hrCom.getId());
			else
				 throw new Exception("update hrcompany-org error");
		}
		return list;
	}
	public User getUserById(Long id)throws Exception{
		return this.userDAO.get(id);
		
	}
	public HrCompany getHrCompanyByType(Element orgType)throws Exception{
		String hql="from HrCompany hc where hc.parentId is null and hc.orgType.id=? and hc.enabled=?";
		List<HrCompany> list=this.getGenericDao().find(hql, orgType.getId(),AppConst.ENABLED);
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	public HrCompany getHrCompanyByOrgId(Element orgType,Long orgSysId)throws Exception{
		if(orgSysId==null){
			return null;
		}
		String hql="from HrCompany hc where hc.orgType.id=? and hc.orgSysId=?";
		List<HrCompany> list=this.getGenericDao().find(hql, orgType.getId(),orgSysId);
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	public Long[] getOrgSysId(Element orgType)throws Exception{
		List<OrgAuthorityDTO> orgList=getOrgAuthorityList(orgType);
		Long[] sysId=new Long[orgList.size()];
		for(int i=0;i<orgList.size();i++){
			sysId[i]=orgList.get(i).getOrgSysId();
		}
		return sysId;
	}
	public List<String> getOrgAuthorityStrList(Element orgType)throws Exception{
		List<OrgAuthorityDTO> orgList=new ArrayList();
		User user = SpringSecurityUtils.getCurrentUser();
		List<OrgAuthorityDTO> orgParList=this.getHrCompanyDAO().getOrgParList(user.getUserId(), orgType.getId());
		if(orgParList!=null && orgParList.size()>0){
			Iterator ite = orgParList.iterator(); 
			while(ite.hasNext()){
				Map map = (Map)ite.next(); 
				BigDecimal id=(BigDecimal) map.get("id");
				getOrgNodeList(orgList,id.longValue(),user.getUserId());
			}
		}
		List<String> orgStrList=new ArrayList();
		if(orgList!=null && orgList.size()>0){
			for(OrgAuthorityDTO org:orgList){
				orgStrList.add(org.getOrgSysId()+"");
			}
		}
		return orgStrList;
	}
	public List<OrgAuthorityDTO> getOrgAuthorityList(Element orgType)throws Exception{
		List<OrgAuthorityDTO> orgList=new ArrayList();
		User user = SpringSecurityUtils.getCurrentUser();
		List<OrgAuthorityDTO> orgParList=this.getHrCompanyDAO().getOrgParList(user.getUserId(), orgType.getId());
		if(orgParList!=null && orgParList.size()>0){
			Iterator ite = orgParList.iterator(); 
			while(ite.hasNext()){
				Map map = (Map)ite.next(); 
				BigDecimal id=(BigDecimal) map.get("id");
				getOrgNodeList(orgList,id.longValue(),user.getUserId());
			}
		}
		return orgList;
	}
	private List<OrgAuthorityDTO> getOrgNodeList(List<OrgAuthorityDTO> orgList,Long parId,Long userId)throws Exception{
		List<OrgAuthorityDTO> nodeList=this.getHrCompanyDAO().getOrgChildList(parId,userId);
		if(nodeList!=null){
			Iterator ite = nodeList.iterator(); 
			while(ite.hasNext()){
				Map map = (Map)ite.next(); 
				BigDecimal id=(BigDecimal) map.get("id");
				BigDecimal orgSysId=(BigDecimal) map.get("orgSysId");
				String code=(String)map.get("code");
				OrgAuthorityDTO dto=new OrgAuthorityDTO();
				dto.setId(id.longValue());
				if(orgSysId!=null){
					dto.setOrgSysId(orgSysId.longValue());
				}
				dto.setCode(code);
				orgList.add(dto);
				getOrgNodeList(orgList,dto.getId(),userId);
			}
		
		}
		return orgList;
	}
	@Transactional
	public void insertIntoHrCompany(String sql,Element orgType,String[] orgTypePro)throws Exception{
		List dtoList=this.getHrCompanyDAO().getOrgList(sql);
		if(dtoList!=null){
			Iterator ite = dtoList.iterator(); 
			while(ite.hasNext()){
				Map map = (Map)ite.next(); 
				BigDecimal id=(BigDecimal) map.get("id");
				String name=(String)map.get("name");
				String addr=(String)map.get("addr");
				String phone=(String)map.get("phone");
				String fax=(String)map.get("fax");
				String desc=(String)map.get("desc");
				HrCompany hrCompany=getHrCompanyByOrgId(orgType, id.longValue());
				if(hrCompany==null){
					hrCompany=new HrCompany();
					hrCompany.setCompCode(billSnManager.getBillSn(AppContentGmcc.HRCOMPANY_CODE));
					hrCompany.setCompLevel("2");
					hrCompany.setIsPurchase(orgTypePro[0]);
					hrCompany.setIsSales(orgTypePro[1]);
					hrCompany.setIsLogistics(orgTypePro[2]);
					hrCompany.setIsWareHouse(orgTypePro[3]);
					hrCompany.setOrgProperties(AppContentGmcc.ORG_PROPERTIES_INNER);
					hrCompany.setOrgType(orgType);
					hrCompany.setParentId(getHrCompanyByType(orgType));
				}
				hrCompany.setAddr(addr);
				hrCompany.setCompName(name);
				hrCompany.setTel(phone);
				hrCompany.setNote(desc);
				hrCompany.setFax(fax);
				hrCompany.setOrgSysId(id.longValue());
				merge(hrCompany);

			}
		}
	}
	public HrCompany getHrCompanyByName(String name)throws Exception{
		return hrCompanyDAO.getHrCompanyByName(name);
	
	}
	
	public Element getElementById(Long eleId)throws Exception{
		return hrCompanyDAO.getElementById(eleId);
	}
	public Long getCompTypeById(Long compId)throws Exception{
		return hrCompanyDAO.getCompTypeById(compId);
	}
}
