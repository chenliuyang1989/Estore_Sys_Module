package com.gmcc.service;

import java.util.List;

import com.gmcc.model.Element;
import com.gmcc.model.ElementGroup;

public interface ElementManager {
	
	/**
	 * 删除
	 * 
	 * @param id
	 * @throws Exception 
	 */
	public abstract String deleteGroup(Long id) throws Exception;
	public abstract ElementGroup saveGroup(Long id) throws Exception;
	public abstract ElementGroup merge(ElementGroup elementGroup) throws Exception;
	
	public ElementGroup deleteGroupObj(ElementGroup eleGroup) throws Exception;
	public ElementGroup getElemetGroup(Long id)throws Exception;
	
	public List<Element> getItemsByGroupId(Long groupId)throws Exception;
}
