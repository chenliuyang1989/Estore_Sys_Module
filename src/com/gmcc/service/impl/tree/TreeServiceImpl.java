package com.gmcc.service.impl.tree;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.gmcc.dao.tree.TreeDAO;
import com.gmcc.dto.TreeDTO;
import com.gmcc.dto.TreeTabColNameDTO;
import com.gmcc.service.tree.TreeService;
import com.gmcc.util.AppConst;
import com.ibm.service.impl.OperateManagerImp;
import com.gmcc.dto.TreeParamDTO;

public class TreeServiceImpl extends OperateManagerImp<TreeDTO,Long> implements TreeService{

	private final static String INITSTR="----";
	
	private TreeDAO treeDAO;
//	private StringBuffer newSql;
	public TreeDAO getTreeDAO() {
		return treeDAO;
	}

	public void setTreeDAO(TreeDAO treeDAO) {
		this.treeDAO = treeDAO;
	}	

//	public StringBuffer getNewSql() {
//		return newSql;
//	}
//
//	public void setNewSql(StringBuffer newSql) {
//		this.newSql = newSql;
//	}

	public List<TreeDTO> getCommTreeList(Long parentId, int leave, String sql,
			String parentIdColumnName) throws Exception {
		// TODO Auto-generated method stub
		List<TreeDTO> list =new ArrayList();
//		this.newSql=new StringBuffer(sql);
		this.getSelectTree(list,parentId,parentIdColumnName, leave, "", sql);
		return list;
	}
	/**
	 * Description: 字符串第一次传空
	 */
	private void getSelectTree(List<TreeDTO> list,Long parentId,String parentIdColumnName,int leave,String str, String sql){
		List<TreeDTO> treeList=getChildNode(true,parentId,parentIdColumnName, sql);//得到所有孩子
		if(treeList!=null){
			int zeng=1;  //递增字段
			Iterator ite = treeList.iterator(); 
			while(ite.hasNext()){
				Map map = (Map)ite.next(); 
				TreeDTO dto=new TreeDTO();
				String nextsign=str+this.appendSign(leave,zeng);//得到符号
				StringBuffer sb=new StringBuffer(INITSTR);
				for(int i=0;i<leave-1;i++){
					sb.append("-");//黏贴几个符号
				}
				dto.setTname((String)map.get("name"));
				dto.setName(nextsign+sb.toString()+(String)map.get("name"));
				if(map.get("code")!=null)
					dto.setCode((String)map.get("code"));
				BigDecimal id=(BigDecimal) map.get("id");
				dto.setId(id.longValue());
				if( map.get("parentId")!=null){
					BigDecimal pId=(BigDecimal) map.get("parentId");
					dto.setParentId(pId.longValue());
				}
				if(map.get("objLevel")!=null){
					dto.setElvel((String)map.get("objLevel"));
				}
				list.add(dto);
				getSelectTree(list,id.longValue(),parentIdColumnName,leave+1,nextsign,sql);
				zeng=zeng+1;
			}
		
		}
	}
	private String appendSign(int leave,int zeng){	
		StringBuffer sb=new StringBuffer();
		  if(leave!=1){
			  sb.append(".");
		  }
			sb.append(String.valueOf(zeng));
		return sb.toString();
	}
	private List getChildNode(boolean hasParent,Long parentId,String parentIdColumnName, String sql) {
//		System.out.println("newsql:"+this.newSql.toString());
		List list=this.treeDAO.getChildNode(hasParent,parentId, sql, parentIdColumnName);
		return list;
	}
	
	public List<TreeDTO> getTreeNodeList(TreeParamDTO paramDTO)throws Exception {
//		System.out.println("sql:"+paramDTO.getSql());
//		this.newSql= new StringBuffer(paramDTO.getSql());		
		return this.getTreeViewList(paramDTO,paramDTO.getParentId());
	}

	private List<TreeDTO> getTreeViewList(TreeParamDTO paramDTO,Long parentId){
		List<TreeDTO> treeList=getChildNode(paramDTO.isHasParent(),parentId,paramDTO.getParentIdColumnName(), paramDTO.getSql());//得到所有孩子
		List<TreeDTO> list=new ArrayList();
		if(treeList!=null){
			Iterator ite = treeList.iterator(); 
			while(ite.hasNext()){
				Map map = (Map)ite.next(); 
				TreeDTO dto=new TreeDTO();
				BigDecimal id=(BigDecimal) map.get("id");
				dto.setId(id.longValue());
				dto.setText((String)map.get("name"));
				if( map.get("parentId")!=null){
					BigDecimal pId=(BigDecimal) map.get("parentId");
					dto.setParentId(pId.longValue());
				}
				if(!paramDTO.isHasChildTable() && paramDTO.getChStr()!=null && 
						!"".equals(paramDTO.getChStr()) && paramDTO.getChStr().indexOf(","+id.longValue()+",")!=-1){
					dto.setChecked("checked");
				}
				if(map.get("ismenu")!=null){
					BigDecimal isMenu=(BigDecimal) map.get("ismenu");
					dto.setFlag(isMenu.intValue()+"");
				}
				if(map.get("type")!=null){
					BigDecimal type=(BigDecimal) map.get("type");
					dto.setType(type.toString());
				}
				if(map.get("objLevel")!=null){
					dto.setElvel((String)map.get("objLevel"));
				}
				dto.setCheckBoxName(paramDTO.getCheckBoxName());
				dto.setHasRadio(paramDTO.isHasRadio());
				dto.setTypeName(paramDTO.getTypeName());
				List<TreeDTO> listTmp=null;
				if(paramDTO.isHasParent()){
					listTmp=getTreeViewList(paramDTO,dto.getId());
					if(listTmp!=null && listTmp.size()>0){
						dto.setHasChildren(true);
						dto.setExpanded(true);
//						dto.setChildren(listTmp);
						dto.setLeaf(false);
					}else{
						dto.setLeaf(true);
						listTmp=setProperty(paramDTO,dto,listTmp);
					}
				}else{
					dto.setLeaf(true);
					listTmp=new ArrayList();
					listTmp=setProperty(paramDTO,dto,listTmp);
				}
				dto.setChildren(listTmp);
				list.add(dto);
			}
		
		}
		return list;
	
	}
	private List setProperty(TreeParamDTO paramDTO,TreeDTO dto,List<TreeDTO> listTmp){
		if(paramDTO.isHasChildTable()){
			List<TreeDTO> listChTab=this.treeDAO.getChildTable(paramDTO.getChildTableSql(),dto.getId());
			
			if(listChTab!=null){
				Iterator itChTab = listChTab.iterator(); 
				while(itChTab.hasNext()){
					Map mapChTab = (Map)itChTab.next(); 
					TreeDTO dtoTab=new TreeDTO();
					BigDecimal idTab=(BigDecimal) mapChTab.get("id");
					dtoTab.setId(idTab.longValue());
					dtoTab.setText((String)mapChTab.get("name"));
					dtoTab.setHasChildren(false);
					dtoTab.setExpanded(false);
					dtoTab.setParentId(dto.getId());
					dtoTab.setCheckBoxName(paramDTO.getChildTabCheckBoxName());
					dtoTab.setHasRadio(paramDTO.isHasRadio());
					if(mapChTab.get("type")!=null){
						BigDecimal type=(BigDecimal) mapChTab.get("type");
						dtoTab.setType(type.toString());
					}
					if(dto.getType()==null || dto.getType().equals("")){
						dto.setType(dtoTab.getType());
					}
					if(mapChTab.get("objLevel")!=null){
						dtoTab.setElvel((String)mapChTab.get("objLevel"));
					}
					dtoTab.setTypeName(paramDTO.getTypeName());
					if(paramDTO.getChStr()!=null && 
							!"".equals(paramDTO.getChStr()) &&
							paramDTO.getChStr().indexOf(","+dtoTab.getId()+",")!=-1){
						dtoTab.setChecked("checked");
					}
					listTmp.add(dtoTab);
				}
			}
		}
		return listTmp;
	}
	
//	public List getTreeNodeListByTabl(List<TreeDTO> allList, TreeTabColNameDTO[] properies,List<TreeDTO> childList,int ind)throws Exception{
//		String sql="";
//		TreeTabColNameDTO colDTO=properies[ind];
//			for(int j=0;j<childList.size();j++){
//				TreeDTO dto=childList.get(j);
//				sql="select "+colDTO.getIdName()+" id,"+colDTO.getNameName()+" name from "+
//					colDTO.getTableName()+" where "+colDTO.getForkeyName()+"="+dto.getId();
//				List<TreeDTO> list= this.treeDAO.getChildTable(sql);
//				if(list!=null && list.size()>0){
//					dto.setHasChildren(true);
//					dto.setExpanded(true);
//					dto.setLeaf(false);
//					dto.setChildren(list);
//				}
//				allList.clear();
//				allList.add(dto);
//				if(ind+1<properies.length)
//					getTreeNodeListByTabl(allList,properies,list,ind+1);
//			}
//		return null;
//	}
	
//	private List<TreeDTO> getParNodeList(String[] sql){
//		List<TreeDTO> list=getChildNodeList(sql[0]);
//		
//		for(TreeDTO dto:list){
//			List<TreeDTO> list
//		}
//		return list;
//	}
	private List<TreeDTO> getChildNodeList(String sql){
		List<TreeDTO> list=new ArrayList(); 
		List<TreeDTO> treeListTmp=treeDAO.getObjectList(sql);
		if(treeListTmp!=null){
			Iterator iteTmp = treeListTmp.iterator(); 
			while(iteTmp.hasNext()){
				Map mapTmp = (Map)iteTmp.next(); 
				TreeDTO dtoTmp=new TreeDTO();
				BigDecimal idTmp=(BigDecimal) mapTmp.get("id");
				dtoTmp.setId(idTmp.longValue());
				dtoTmp.setName((String)mapTmp.get("name"));
				dtoTmp.setText((String)mapTmp.get("name"));
				list.add(dtoTmp);
			}
		}
		return list;
	}
	public List<TreeDTO> getWarehouseNodeList()throws Exception {
		List<TreeDTO> list=new ArrayList(); 
		String sql="select c.citysysid id,c.cityname name from t_base_city c where c.enabled="+AppConst.ENABLED;
		String sql1="select w.whsysid id,whname name from t_base_warehouse w where  w.enabled="+AppConst.ENABLED+" and w.citysysid=";
		List<TreeDTO> listTmp=getChildNodeList(sql);
		for(TreeDTO dto:listTmp){
			List<TreeDTO> listTmp1=getChildNodeList(sql1+dto.getId());
			dto.setChildren(listTmp1);
			list.add(dto);
		}
		return list;
	}
	public List<TreeDTO> getStockareaNodeList()throws Exception {
		List<TreeDTO> list=new ArrayList(); 
		String sql="select c.citysysid id,c.cityname name from t_base_city c where c.enabled="+AppConst.ENABLED;
		String sql1="select w.whsysid id,whname name from t_base_warehouse w where  w.enabled="+AppConst.ENABLED+" and w.citysysid=";
		String sql2="select w.stockareasysid id,stockareaname name from t_base_stockarea w where  w.enabled="+AppConst.ENABLED+" and w.whsysid=";
		List<TreeDTO> listTmp=getChildNodeList(sql);
		for(TreeDTO dto:listTmp){
			List<TreeDTO> listTmp1=getChildNodeList(sql1+dto.getId());
			dto.setChildren(listTmp1);
			for(TreeDTO dto1:listTmp1){
				List<TreeDTO> listTmp2=getChildNodeList(sql2+dto1.getId());
				dto1.setChildren(listTmp2);
			}
			list.add(dto);
		}
		return list;
	}
	public List<TreeDTO> getGoodsrackNodeList()throws Exception {
		List<TreeDTO> list=new ArrayList(); 
		String sql="select c.citysysid id,c.cityname name from t_base_city c where c.enabled="+AppConst.ENABLED;
		String sql1="select w.whsysid id,whname name from t_base_warehouse w where  w.enabled="+AppConst.ENABLED+" and w.citysysid=";
		String sql2="select w.stockareasysid id,stockareaname name from t_base_stockarea w where  w.enabled="+AppConst.ENABLED+" and w.whsysid=";
		String sql3="select w.GOODSRACKSYSID id,GOODSRACKCODE name from t_base_goodsrack w where  w.enabled="+AppConst.ENABLED+" and w.STOCKAREASYSID=";
		List<TreeDTO> listTmp=getChildNodeList(sql);
		for(TreeDTO dto:listTmp){
			List<TreeDTO> listTmp1=getChildNodeList(sql1+dto.getId());
			dto.setChildren(listTmp1);
			for(TreeDTO dto1:listTmp1){
				List<TreeDTO> listTmp2=getChildNodeList(sql2+dto1.getId());
				dto1.setChildren(listTmp2);
				for(TreeDTO dto2:listTmp2){
					List<TreeDTO> listTmp3=getChildNodeList(sql3+dto2.getId());
					dto2.setChildren(listTmp3);
				}
			}
			list.add(dto);
		}
		return list;
	}
	
//	public List<TreeDTO> getParentList(TreeParamDTO paramDTO)throws Exception {
//		List<TreeDTO> list=new ArrayList(); 
//		List<TreeDTO> treeListTmp=treeDAO.getObjectList(paramDTO.getSql());
//		if(treeListTmp!=null){
//			Iterator iteTmp = treeListTmp.iterator(); 
//			while(iteTmp.hasNext()){
//				Map mapTmp = (Map)iteTmp.next(); 
//				TreeDTO dtoTmp=new TreeDTO();
//				BigDecimal idTmp=(BigDecimal) mapTmp.get("id");
//				dtoTmp.setId(idTmp.longValue());
//				dtoTmp.setName((String)mapTmp.get("name"));
//				dtoTmp.setText((String)mapTmp.get("name"));
//				if( mapTmp.get("parentId")!=null){
//					BigDecimal pId=(BigDecimal) mapTmp.get("parentId");
//					dtoTmp.setParentId(pId.longValue());
//				}
//				list.add(dtoTmp);
//			}
//		}
//		return list;
//	}
//	public List<TreeDTO> getParentList(TreeParamDTO paramDTO)throws Exception {
//		List<TreeDTO> list=new ArrayList(); 
//		List<TreeDTO> treeListTmp=treeDAO.getObjectList(paramDTO.getSql());
//		if(treeListTmp!=null){
//			Iterator iteTmp = treeListTmp.iterator(); 
//			while(iteTmp.hasNext()){
//				Map mapTmp = (Map)iteTmp.next(); 
//				TreeDTO dtoTmp=new TreeDTO();
//				BigDecimal idTmp=(BigDecimal) mapTmp.get("id");
//				dtoTmp.setId(idTmp.longValue());
//				dtoTmp.setName((String)mapTmp.get("name"));
//				dtoTmp.setText((String)mapTmp.get("name"));
//				list.add(dtoTmp);
//			}
//		}
//		return list;
//	}
//	public boolean hasSubItem(String sql)throws Exception{
//		return treeDAO.hasSubItem(sql);
//	}
}
