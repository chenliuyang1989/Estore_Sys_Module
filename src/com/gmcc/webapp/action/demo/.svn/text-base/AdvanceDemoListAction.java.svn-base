package com.gmcc.webapp.action.demo;

import java.util.HashMap;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import com.gmcc.model.DemoBill;
import com.gmcc.util.AppConst;
import com.gmcc.webapp.action.base.DisplayTagQueryAction;
import com.ibm.util.annotation.CacheCondtion;

public class AdvanceDemoListAction extends DisplayTagQueryAction<DemoBill, Long> {
	private static final long serialVersionUID = 1L;
	
	public AdvanceDemoListAction(){
		super.setCollMap(collMap);
	}
	
	@CacheCondtion
	private DemoBill advanceDemo;
	
	public void setAdvanceDemo(DemoBill advanceDemo) {
		this.advanceDemo = advanceDemo;
	}
	
	public DemoBill getAdvanceDemoo() {
		return advanceDemo;
	}
	
	private static HashMap<String, String> collMap = new HashMap<String, String>();
	static{
		collMap.put("billNum", "advanceDemo.billNum");
		collMap.put("billName", "advanceDemo.billName");
		collMap.put("billStatus.eleName", "advanceDemo.billStatus");
		collMap.put("tranTime", "advanceDemo.tranTime");
		collMap.put("lastUpdatedTime", "field.lastUpdatedTime");
		collMap.put("lastUpdatedBy", "field.lastUpdatedBy");
	}
	
	@Override
	public String queryPage() throws Exception {
		super.setCollMap(collMap);
		return super.queryPage();
	}
	
	@Override
	protected List<Criterion> filters() throws Exception {
		List<Criterion> list = super.filters();
		list.add(Restrictions.eq("enabled", AppConst.ENABLED));
		if(advanceDemo.getBillStatus().getId()!=null){
			list.add(Restrictions.eq("billStatus.id", advanceDemo.getBillStatus().getId()));
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
