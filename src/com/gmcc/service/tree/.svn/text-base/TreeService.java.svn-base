package com.gmcc.service.tree;

import java.util.List;

import com.gmcc.dto.TreeDTO;
import com.gmcc.dto.TreeParamDTO;
import com.ibm.service.IOperateManager;

public interface TreeService extends IOperateManager<TreeDTO,Long>{

	/**
	 * 下列框树形列表
	 * @param parentId
	 * @param leave
	 * @param sql
	 * @param parentIdColumnName
	 * @return
	 * @throws Exception
	 */
	public List<TreeDTO> getCommTreeList(Long parentId,int leave,String sql,String parentIdColumnName)throws Exception ;
	/**
	 * 使用JQUERYVIEW调用的树形
	 * @param hasParent 对应表是否是从属关系表
	 * @param parentId 父ID
	 * @param sql  查询SQL
	 * @param parentIdColumnName 对应表的父ID的字段名
	 * @param chStr   
	 * @return
	 */
	public List<TreeDTO> getTreeNodeList(TreeParamDTO paramDTO)throws Exception ;
	
//	public boolean hasSubItem(String sql)throws Exception ;
//	
//	public List<TreeDTO> getParentList(TreeParamDTO paramDTO)throws Exception;
	
	/**
	 * 仓库树形
	 * @return
	 * @throws Exception
	 */
	public List<TreeDTO> getWarehouseNodeList()throws Exception ;
	/**
	 * 库区树形
	 * @return
	 * @throws Exception
	 */
	public List<TreeDTO> getStockareaNodeList()throws Exception ;
	/**
	 * 货架树形
	 * @return
	 * @throws Exception
	 */
	public List<TreeDTO> getGoodsrackNodeList()throws Exception ;
}
