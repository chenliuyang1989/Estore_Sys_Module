package com.gmcc.webapp.action.demo;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import com.gmcc.model.DemoBill;
import com.gmcc.util.AppConst;
import com.gmcc.webapp.action.base.DisplayTagQueryAction;
import com.ibm.util.StringUtil;
import com.ibm.util.annotation.CacheCondtion;

public class BaseDemoListAction extends DisplayTagQueryAction<DemoBill, Long> {
	private static final long serialVersionUID = 1L;
	
	@CacheCondtion
	private DemoBill baseDemo;
	public void setBaseDemo(DemoBill baseDemo) {
		this.baseDemo = baseDemo;
	}
	public DemoBill getBaseDemo() {
		return baseDemo;
	}

	@Override
	protected List<Criterion> filters() throws Exception {
		List<Criterion> list = super.filters();
		list.add(Restrictions.eq("enabled", AppConst.ENABLED));
		if(StringUtil.isValidString(this.baseDemo.getBillNum())){
			list.add(Restrictions.like("billNum", "%" + this.baseDemo.getBillNum() + "%"));
		}
		return list;
	}
	
	@Override
	protected List<Order> orders() {
		List<Order> list = super.orders();
		list.add(Order.asc("id"));
		return list;
	}
	
	public String add() {
		return super.add();
	}
	
}
