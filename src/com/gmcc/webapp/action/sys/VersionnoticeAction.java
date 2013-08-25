package com.gmcc.webapp.action.sys;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.gmcc.model.Element;
import com.gmcc.model.Versionnotice;
import com.gmcc.service.VersionnoticeService;
import com.gmcc.util.AppContentWms;
import com.gmcc.util.DateUtil;
import com.gmcc.webapp.action.base.DisplayTagQueryAction;


/**
 * @title VersionnoticeAction.java
 * @desc com.gmcc.estore.wms.action.baseinfo
 * @company www.ibm.com.cn
 * @date Jul 15, 2011 
 * @author Shine.He
 * @version 1.4
 */

public class VersionnoticeAction extends DisplayTagQueryAction<Versionnotice, Long> {
	
	private Versionnotice versionnotice; 
	private VersionnoticeService verNotiService;
	private String saveFlag;
	
	public String findVerNotice() throws Exception {
		try {
			versionnotice.setVisibletime1(DateUtil.dateToStr(versionnotice.getVisibletime(), "yyyy-MM-dd"));
			versionnotice.setDeadline1(DateUtil.dateToStr(versionnotice.getDeadline(), "yyyy-MM-dd"));
			
		} catch (Exception ex) {
			log.error("转换异常： ", ex);
		}
		versionnotice = verNotiService.getVersionnotice(versionnotice);
		return "findVerNotice";
	}
	
	public String findVerNoticeShow() throws Exception {
		try {
			versionnotice.setVisibletime1(DateUtil.dateToStr(versionnotice.getVisibletime(), "yyyy-MM-dd"));
			versionnotice.setDeadline1(DateUtil.dateToStr(versionnotice.getDeadline(), "yyyy-MM-dd"));
			
		} catch (Exception ex) {
			log.error("转换异常： ", ex);
		}
		versionnotice = verNotiService.getVersionnotice(versionnotice);
		return "findVerNoticeShow";
	}
	
	
	public String findVersionnotice() throws Exception {
		return "findVersionnotices";
		
	}
	
	public String findScrollNotice() throws Exception {
		queryPage();
		return "findScrollNotice";
	}
	
	public String findVersionnotices() throws Exception {
		queryPage();
		return "findVersionnotices";
	}
	
	public String findNotices() throws Exception {
		queryPage();
		return "findNotices";
	}

	public String saveVersionnotice() throws Exception {
		return "saveVersionnotice";
	}
	
	public String saveVersionnotices() throws Exception {
		try {
			versionnotice.setVisibletime(DateUtil.strToDate(versionnotice.getVisibletime1(), "yyyy-MM-dd"));
			versionnotice.setDeadline(DateUtil.strToDate(versionnotice.getDeadline1() + " 23:59:59", "yyyy-MM-dd HH:mm:ss"));
			
		} catch (Exception ex) {
			log.error("转换异常: ", ex);
		}
		
		try {
			verNotiService.saveVersionnotice(versionnotice);
			
		} catch (Exception vex) {
			this.addActionError("系统消息重复,保存失败!");
			return "saveVersionnotice";
			
		}
		this.addActionError("保存成功!");
		return "findNotices";
	}
	
	public String updVersionnotice() throws Exception {
		try {
			versionnotice.setVisibletime(DateUtil.strToDate(versionnotice.getVisibletime1(), "yyyy-MM-dd"));
			versionnotice.setDeadline(DateUtil.strToDate(versionnotice.getDeadline1() + " 23:59:59", "yyyy-MM-dd HH:mm:ss"));
			
		} catch (Exception ex) {
			log.error("转换异常: ", ex);
		}
		verNotiService.updVersionnotice(versionnotice);
		return "findVerNotice";
		
	}
	
	@Override
	protected List<Criterion> filters() throws Exception {

		List<Criterion> list = super.filters();
		if(null!=versionnotice) {
			if (versionnotice.getNote()!=null && !"".equals(versionnotice.getNote())) {
				list.add(Restrictions.like("note", "%" + versionnotice.getNote() + "%"));
			}
			
			if (versionnotice.getPriority()!=null && !"".equals(versionnotice.getPriority()) && !"-1".equals(versionnotice.getPriority())) {
				list.add(Restrictions.eq("priority", versionnotice.getPriority()));
			}
			
			if (versionnotice.getEnabled()!=null && !"".equals(versionnotice.getEnabled()) && !"-1".equals(versionnotice.getEnabled())) {
				list.add(Restrictions.eq("enabled", versionnotice.getEnabled()));
			}
			
			if (versionnotice.getNotetype()!=null && !"".equals(versionnotice.getNotetype()) && !"-1".equals(versionnotice.getNotetype())) {
				list.add(Restrictions.eq("notetype", versionnotice.getNotetype()));
			}
			
			// 显示开始时间
			if (versionnotice.getVisibletime1()!=null && !"".equals(versionnotice.getVisibletime1()) 
					&& versionnotice.getVisibletime2()!=null && !"".equals(versionnotice.getVisibletime2())){
				try {
					list.add(Restrictions.between("visibletime", DateUtil.strToDate(versionnotice.getVisibletime1(), "yyyy-MM-dd"), DateUtil.strToDate(versionnotice.getVisibletime2() + " 23:59:59", "yyyy-MM-dd HH:mm:ss")));
				} catch (Exception ex) {
					log.error(ex.toString());
				}
				
				
			} else if(versionnotice.getVisibletime1()!=null && !"".equals(versionnotice.getVisibletime1()) 
					&& (versionnotice.getVisibletime2()==null || "".equals(versionnotice.getVisibletime2()))) {
				try {
					if ("1".equals(versionnotice.getToflag())) {
						list.add(Restrictions.le("visibletime", DateUtil.strToDate(versionnotice.getVisibletime1(), "yyyy-MM-dd")));
						
					} else {
						list.add(Restrictions.ge("visibletime", DateUtil.strToDate(versionnotice.getVisibletime1(), "yyyy-MM-dd")));
						
					}
				
				} catch (Exception ex) {
					log.error(ex.toString());
				}
				
				
			} else if((versionnotice.getVisibletime1()==null || "".equals(versionnotice.getVisibletime1())) 
						&& versionnotice.getVisibletime2()!=null && !"".equals(versionnotice.getVisibletime2())) {
				try {
					list.add(Restrictions.le("visibletime", DateUtil.strToDate(versionnotice.getVisibletime2() + " 23:59:59", "yyyy-MM-dd HH:mm:ss")));
				 
				} catch (Exception ex) {
					log.error(ex.toString());
				}
				
			}
			
			// 显示结束时间
			if (versionnotice.getDeadline1()!=null && !"".equals(versionnotice.getDeadline1()) 
					&& versionnotice.getDeadline2()!=null && !"".equals(versionnotice.getDeadline2())){
				try {
					list.add(Restrictions.between("deadline", DateUtil.strToDate(versionnotice.getVisibletime1(), "yyyy-MM-dd"), DateUtil.strToDate(versionnotice.getVisibletime2() + " 23:59:59", "yyyy-MM-dd HH:mm:ss")));
				} catch (Exception ex) {
					log.error(ex.toString());
				}
				
				
			} else if(versionnotice.getDeadline1()!=null && !"".equals(versionnotice.getDeadline1()) 
						&& (versionnotice.getDeadline2()==null || "".equals(versionnotice.getDeadline2()))) {
				try {
					list.add(Restrictions.ge("deadline", DateUtil.strToDate(versionnotice.getDeadline1(), "yyyy-MM-dd")));
					
				} catch (Exception ex) {
					log.error(ex.toString());
				}
				
				
			} else if((versionnotice.getDeadline1()==null || "".equals(versionnotice.getDeadline1())) 
						&& versionnotice.getDeadline2()!=null && !"".equals(versionnotice.getDeadline2())) {
				try {
					if ("1".equals(versionnotice.getToflag())) {
						list.add(Restrictions.ge("deadline", DateUtil.strToDate(versionnotice.getDeadline2()+ " 23:59:59", "yyyy-MM-dd HH:mm:ss")));
						
					} else {
						list.add(Restrictions.le("deadline", DateUtil.strToDate(versionnotice.getDeadline2()+ " 23:59:59", "yyyy-MM-dd HH:mm:ss")));
						
					}
				 
				} catch (Exception ex) {
					log.error(ex.toString());
				}
				
			}
		}
		return list;
	}
	
	@Override
	protected List<Order> orders() {
		List<Order> list = super.orders();
		list.add(Order.desc("priority"));
		list.add(Order.asc("deadline"));
		return list;
	}
	
	/**
	 * @desc 优先级
	 * @return List<Element>
	 */
	public List<Element> getPriority() {
		List<Element> eleList = new ArrayList<Element>();
		Element ele0 = new Element(0L, "低");
		Element ele1 = new Element(1L, "中");
		Element ele2 = new Element(2L, "高");
		eleList.add(ele0);
		eleList.add(ele1);
		eleList.add(ele2);
		return eleList;
	}
	
	/**
	 * @desc 是否有效
	 * @return List<Element>
	 */
	public List<Element> getEnabled() {
		List<Element> eleList = new ArrayList<Element>();
		Element ele0 = new Element(0L, "否");
		Element ele1 = new Element(1L, "是");
		eleList.add(ele1);
		eleList.add(ele0);
		
		return eleList;
	}
	
	/**
	 * @desc 通告类型
	 * @return List<Element>
	 */
	public List<Element> getSysNoticeTypeList() {
		List<Element> list = super.getItemsOfGroup(AppContentWms.SYSTEM_NOTICE_TYPE);
		return list;
		
	}

	public Versionnotice getVersionnotice() {
		return versionnotice;
	}

	public void setVersionnotice(Versionnotice versionnotice) {
		this.versionnotice = versionnotice;
	}

	public VersionnoticeService getVerNotiService() {
		return verNotiService;
	}

	public void setVerNotiService(VersionnoticeService verNotiService) {
		this.verNotiService = verNotiService;
	}
	
	
}
