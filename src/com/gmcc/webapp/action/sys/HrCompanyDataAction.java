package com.gmcc.webapp.action.sys;

import java.io.PrintWriter;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.gmcc.cons.element.Constants;
import com.gmcc.dto.TreeDTO;
import com.gmcc.dto.TreeParamDTO;
import com.gmcc.service.hrcompany.HrCompanyService;
import com.gmcc.service.tree.TreeService;
import com.gmcc.model.Element;
import com.gmcc.model.HrCompany;
import com.gmcc.model.JobConfig;
import com.gmcc.model.User;
import com.gmcc.util.AppConst;
import com.gmcc.util.AppContentGmcc;
import com.gmcc.webapp.action.base.BaseBusinessLogAction;
import com.ibm.util.annotation.LogBusinessKey;

public class HrCompanyDataAction extends BaseBusinessLogAction<HrCompany,Long>{

	private static final long serialVersionUID = 1L;
	
	private HrCompany hrCompany;
	private TreeService treeService;
	private HrCompanyService hrCompanyService;
	public HrCompany getHrCompany() {
		return hrCompany;
	}

	public void setHrCompany(HrCompany hrCompany) {
		this.hrCompany = hrCompany;
	}
	public TreeService getTreeService() {
		return treeService;
	}

	public void setTreeService(TreeService treeService) {
		this.treeService = treeService;
	}

	public HrCompanyService getHrCompanyService() {
		return hrCompanyService;
	}

	public void setHrCompanyService(HrCompanyService hrCompanyService) {
		this.hrCompanyService = hrCompanyService;
	}

	private void getTreeList()throws Exception{
		String sql="select t.comp_id id,t.comp_code code,t.comp_level objLevel,t.comp_name name,t.parent_id parentId " +
			"from tb_hr_company t " +
			"where t.enabled="+AppConst.ENABLED;
		List<TreeDTO> treeList=this.treeService.getCommTreeList(null, 1, sql, "parent_id");
		this.getRequest().setAttribute("treeList", treeList);
	}
	public String load() throws Exception {
		if(this.hrCompany==null || this.hrCompany.getId()==null){
			hrCompany=new HrCompany();
			this.hrCompany.setEnabled(AppConst.ENABLED);
		}else{
			this.hrCompanyService.setEntityClass(HrCompany.class);
			this.hrCompany=this.hrCompanyService.findById(this.hrCompany.getId());
		}
		List<Element> orgTypeList=super.getItemsOfGroup(AppContentGmcc.ORG_TYPE_GROUPNAME);
		this.getRequest().setAttribute("orgTypeList", orgTypeList);
//		getTreeList();
		return SUCCESS;
	}
	@LogBusinessKey(objectType=Constants.ELE_TYPE_LOG_2, objectKey="log_save_company", methodKey="save")
	public String save()throws Exception {
		try{	
			hrCompany=this.hrCompanyService.merge(hrCompany);
			List<Element> orgTypeList=super.getItemsOfGroup(AppContentGmcc.ORG_TYPE_GROUPNAME);
			this.getRequest().setAttribute("orgTypeList", orgTypeList);
//			getTreeList();
		}catch (Exception e) {
			List<Element> orgTypeList=super.getItemsOfGroup(AppContentGmcc.ORG_TYPE_GROUPNAME);
			this.getRequest().setAttribute("orgTypeList", orgTypeList);
			this.addActionError(getText("error.save"));
			log.error(e.getMessage(), e);
			return INPUT;
		}
		//message
		this.addActionMessage(getText("message.save.success"));
		//log2db
		this.setOperatorPK(this.hrCompany.getId());
		this.setOperatorContent(this.getText("company.save.log")+
				this.getText("company.compCode")+
				this.hrCompany.getCompCode());
//		hrCompany=null;
		return SUCCESS;
	}
	@LogBusinessKey(objectType=Constants.ELE_TYPE_LOG_2, objectKey="log_delete_company", methodKey="delete")
	public String delete() throws Exception {
		try{
			if(hrCompany.getId()!=null){
				List<HrCompany> list=this.hrCompanyService.del(hrCompany.getId());	
//				String ids="";
//				String codes="";
				for(HrCompany com:list){
//					ids=ids+com.getId()+"";
//					codes=codes+com.getCompCode()+",";
					this.setOperatorPK(com.getId());
					this.setOperatorContent(this.getText("company.delete.log")+
							this.getText("company.compCode")+
							com.getCompCode());
				}
//				this.setOperatorPK(Long.valueOf(ids));
//				this.setOperatorContent(this.getText("company.delete.log")+
//						this.getText("company.compCode")+
//						codes.substring(0,codes.length()-1));
			}
		}catch (Exception e) {
			this.addActionError(getText("error.delete"));
			log.error(e.getMessage(), e);
			return super.backToList();
		}
		//message
		this.addActionMessage(getText("message.delete.success"));
		//log2db
//		this.setOperatorPK(this.hrCompany.getId());
//		this.setOperatorContent(this.getText("company.delete.log")+
//				this.getText("company.compCode")+
//				this.hrCompany.getCompCode());
		hrCompany=null;
		return super.backToList();
	}
	@LogBusinessKey(objectType=Constants.ELE_TYPE_LOG_2, objectKey="log_delete_company", methodKey="delete")
	public String deleteBatch()throws Exception{
		
		try{
			String[] dtlIds=this.getRequest().getParameterValues("_chk");
			if(dtlIds!=null){
				List<HrCompany> list=hrCompanyService.delBatch(dtlIds);
				for(HrCompany com:list){
					this.setOperatorPK(com.getId());
					this.setOperatorContent(this.getText("company.delete.log")+
							this.getText("company.compCode")+
							com.getCompCode());
				}
				
			}
			
		}catch(Exception ex){
			this.addActionError(getText("error.delete"));
			log.error(ex.getMessage(), ex);
			return super.backToList();
		}
		this.saveMessage(getText("message.delete.success"));
		return super.backToList();
		
	}
	@SkipValidation
	public String loadHrCompanys() throws Exception {
		
		this.getResponse().setHeader("Cache-Control", "no-cache");

		this.getResponse().setContentType("text/json;charset=UTF-8");

		this.getResponse().setCharacterEncoding("UTF-8");

		PrintWriter out = this.getResponse().getWriter();

//		String root = this.getRequest().getParameter("root");
		String compStr=",";
		if(this.getRequest().getParameter("id")!=null && !"".equals(this.getRequest().getParameter("id"))){
			User user=this.hrCompanyService.getUserById(Long.valueOf(this.getRequest().getParameter("id")));
			Set<HrCompany> compSet=user.getHrCompanys();
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
	@SkipValidation
	public String getHrCompanyByName()throws Exception{
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse res = ServletActionContext.getResponse();
		res.setContentType("text/json;charset=UTF-8");
		res.setHeader("Cache-Control", "no-cache");
//		StringBuffer strJson = new StringBuffer("");
		try{
			String companyName=req.getParameter("companyName");
			HrCompany com=this.hrCompanyService.getHrCompanyByName(companyName);
			HrCompany comTmp=null;
			if(com!=null){
				comTmp=new HrCompany();
				comTmp.setAddr(com.getAddr());
				comTmp.setCompCode(com.getCompCode());
				comTmp.setCompFullname(com.getCompFullname());
				comTmp.setCompLevel(com.getCompLevel());
				comTmp.setCompName(com.getCompName());
				comTmp.setCompSubname(com.getCompSubname());
				comTmp.setEnabled(com.getEnabled());
				comTmp.setFax(com.getFax());
				comTmp.setId(com.getId());
				comTmp.setIsLogistics(com.getIsLogistics());
				comTmp.setIsPurchase(com.getIsPurchase());
				comTmp.setIsSales(com.getIsSales());
				comTmp.setIsWareHouse(com.getIsWareHouse());
				comTmp.setNote(com.getNote());
				comTmp.setOrgProperties(com.getOrgProperties());
				comTmp.setOrgSysId(com.getOrgSysId());
//				comTmp.setOrgType(com.getOrgType());
//				comTmp.setParentId(com.getParentId());
				if(com.getParentId()!=null){
					comTmp.setParId(com.getParentId().getId());
					comTmp.setParName(com.getParentId().getCompName());
				}else{
					comTmp.setParId(null);
				}
				if(com.getOrgType()!=null){
					comTmp.setOrgTypeId(com.getOrgType().getId());
				}else{
					comTmp.setParId(null);
				}
				comTmp.setTel(com.getTel());
				
			}
			
			JSONObject array=JSONObject.fromObject(comTmp);
			System.out.println("["+array.toString()+"]");
			res.getWriter().write("["+array.toString()+"]");    
			res.getWriter().flush();
			res.getWriter().close();
		}catch (Exception e) {
			res.getWriter().write("-1");   
			res.getWriter().flush();
			res.getWriter().close();
//			this.addActionError(getText("error.delete"));
//			log.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return null;
	
	}
	@SkipValidation
	public String getHrCompanyLevelByType()throws Exception{
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse res = ServletActionContext.getResponse();
		res.setContentType("text/json;charset=UTF-8");
		res.setHeader("Cache-Control", "no-cache");
		try{
			String orgTypeId=req.getParameter("orgTypeId");
			String parentId=req.getParameter("parentId");
			String compLevel=req.getParameter("compLevel");
			Element orgTypeEle=this.hrCompanyService.getElementById(Long.valueOf(orgTypeId));
			if(compLevel!=null && "1".equals(compLevel)){
//				Element orgTypeEle=this.hrCompanyService.getElementById(Long.valueOf(orgTypeId));
				if(orgTypeEle!=null && orgTypeEle.getEleCode()!=null 
						&& (AppContentGmcc.ORG_TYPE_WAREHOUSE.equals(orgTypeEle.getEleCode())
								|| AppContentGmcc.ORG_TYPE_WAREHOUSE.equals(orgTypeEle.getEleCode())
								|| AppContentGmcc.ORG_TYPE_LOGISTICS.equals(orgTypeEle.getEleCode())
								|| AppContentGmcc.ORG_TYPE_PROVIDER.equals(orgTypeEle.getEleCode())
								)){
					res.getWriter().write("1");   
				}else{
					res.getWriter().write("0");   
				}
			}else{
				Long orgType=hrCompanyService.getCompTypeById(Long.valueOf(parentId));
				if((orgType==null || orgType==0l) && orgTypeEle!=null && !AppContentGmcc.ORG_TYPE_HR.equals(orgTypeEle.getEleCode())){
					res.getWriter().write("2");//组织类型必须与上级组织的的组织类型相同
				}else if(orgTypeId!=null && orgType.longValue()!=Long.valueOf(orgTypeId).longValue()){
					res.getWriter().write("2");//组织类型必须与上级组织的的组织类型相同
				}else{
					res.getWriter().write("0");
				}
			}
			
		}catch (Exception e) {
			res.getWriter().write("-1");   
			res.getWriter().flush();
			res.getWriter().close();
			e.printStackTrace();
		}
		return null;
	}
}
