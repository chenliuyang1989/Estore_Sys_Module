package com.gmcc.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gmcc.model.Element;
import com.gmcc.model.ElementGroup;
import com.gmcc.service.ElementManager;
import com.ibm.dao.hibernate.base.IBaseDao;

public class ElementManagerImpl implements ElementManager {

	private IBaseDao<ElementGroup,Long> eleGroupDao;
	private IBaseDao<Element,Long> elementDao;
	
	@SuppressWarnings("unchecked")
	public String deleteGroup(Long id) throws Exception {
		ElementGroup eleGroup = this.eleGroupDao.get(id);
		if(eleGroup==null){
			throw new Exception("element.eleGroupName.notexist");
		}
		String hql = "from Element a where a.elementGroup.id=?";
		List<Element> eleList = this.elementDao.find(hql, id);
		for(Element element :eleList){
			this.elementDao.setEntityClass(Element.class);
			this.elementDao.removeById(element.getId(), true);
		}
		this.eleGroupDao.setEntityClass(ElementGroup.class);
		this.eleGroupDao.removeById(eleGroup.getId(), true);
		return "list";
	}
	@SuppressWarnings("unchecked")
	public ElementGroup deleteGroupObj(ElementGroup eleGroup) throws Exception {
//		ElementGroup eleGroup = this.eleGroupDao.get(id);
		
		String hql = "from Element a where a.elementGroup.id=?";
		List<Element> eleList = this.elementDao.find(hql, eleGroup.getId());
		for(Element element :eleList){
			this.elementDao.setEntityClass(Element.class);
			this.elementDao.removeById(element.getId(), true);
		}
		this.eleGroupDao.setEntityClass(ElementGroup.class);
		this.eleGroupDao.removeById(eleGroup.getId(), true);
		return eleGroup;
	}
	public ElementGroup getElemetGroup(Long id)throws Exception {
		eleGroupDao.setEntityClass(ElementGroup.class);
		return this.eleGroupDao.get(id);
	}
	@Transactional
	public ElementGroup saveGroup(Long id) throws Exception {
		eleGroupDao.setEntityClass(ElementGroup.class);
		ElementGroup eleGroup = this.eleGroupDao.get(id);
		eleGroup = this.eleGroupDao.merge(eleGroup);
		return eleGroup;
	}
	
	@Transactional
	public ElementGroup merge(ElementGroup elementGroup) throws Exception {
		this.eleGroupDao.setEntityClass(ElementGroup.class);
		ElementGroup eleGroupTmp=null;
		if(elementGroup.getId()!=null){
			eleGroupTmp=this.eleGroupDao.get(elementGroup.getId());
			eleGroupTmp.setEleGroupName(elementGroup.getEleGroupName());
			eleGroupTmp.setEleGroupRemark(elementGroup.getEleGroupRemark());
			eleGroupTmp.setVersion(elementGroup.getVersion());
		}else{
			eleGroupTmp=elementGroup;
		}
		
		return this.eleGroupDao.merge(eleGroupTmp);
	}

	public IBaseDao<Element, Long> getElementDao() {
		return elementDao;
	}

	public void setElementDao(IBaseDao<Element, Long> elementDao) {
		this.elementDao = elementDao;
	}

	public IBaseDao<ElementGroup, Long> getEleGroupDao() {
		return eleGroupDao;
	}

	public void setEleGroupDao(IBaseDao<ElementGroup, Long> eleGroupDao) {
		this.eleGroupDao = eleGroupDao;
	}

	public List<Element> getItemsByGroupId(Long groupId)throws Exception{
		String hql = "from Element a where a.elementGroup.id=?";
		List<Element> eleList = this.elementDao.find(hql, groupId);
		return eleList;
	}
}
