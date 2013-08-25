package com.gmcc.webapp.action.sys;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.gmcc.model.Element;
import com.gmcc.webapp.action.base.DisplayTagQueryAction;


/**
 * 用户列表子类，依赖父类DisplayTagQueryAction实现全部display标签支持, 泛型指定操作实体和主码类型

 */
public class ElementListAction extends DisplayTagQueryAction<Element, Long> {


	/**
	 * 
	 */
	private static final long serialVersionUID = -336583750629305332L;
	
	/**
	 * 查询条件：主表ID
	 */
	private Long gid;
	

	// /////////////////////////////////////////////存取方法///////////////////////////////////////////
	public Long getGid() {
		return gid;
	}

	public void setGid(Long gid) {
		this.gid = gid;
	}
	
	

	@Override
	public String queryPage() throws Exception {
		super.setPageSize(50);
		return super.queryPage();
	}

	// ///////////////////////////////重写父类DisplayTagQueryAction的方法//////////////////////////////
	/*
	 * 查询条件
	 * 
	 * @see com.gmcc.webapp.action.base.DisplayTagQueryAction#filters()
	 */
	@Override
	protected List<Criterion> filters() throws Exception {
		List<Criterion> list = super.filters();
		list.add(Restrictions.eq("elementGroup.id", this.gid ));		
		return list;
	}

	/*
	 * 排序条件
	 * 
	 * @see com.gmcc.webapp.action.base.DisplayTagQueryAction#orders()
	 */
	@Override
	protected List<Order> orders() {
		List<Order> list = super.orders();
		list.add(Order.asc("orderNum"));
		return list;
	}

	// ///////////////////////////////响应请求方法//////////////////////////////
	/**
	 * "新增"按钮跳转
	 * 
	 * @return
	 */
	public String add() {
		return "add";
	}
	
	
}
