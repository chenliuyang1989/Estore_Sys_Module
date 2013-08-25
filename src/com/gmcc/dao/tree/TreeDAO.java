package com.gmcc.dao.tree;

import java.util.List;

import com.gmcc.dto.TreeDTO;

public interface TreeDAO {

	public List<TreeDTO> getChildNode(boolean hasParent,Long parentId, String sql,String parentIdColumnName);
	
	public List<TreeDTO> getChildTable(String sql,Long forkeyId);
//	public TreeDTO getSelf(String id, String entityName);
	
//	public boolean is_leaf(String id,String entityName);
	
	public List<TreeDTO> getChildTable(String sql);
	
	public List<TreeDTO> getObjectList(String sql);
	
//	public boolean hasSubItem(String sql);
}
