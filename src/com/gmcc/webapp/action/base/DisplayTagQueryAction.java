package com.gmcc.webapp.action.base;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;

import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

import com.gmcc.model.Element;
import com.gmcc.sso.TokenUtils;
import com.gmcc.util.AppContentGmcc;
import com.ibm.dao.hibernate.support.Page;
import com.ibm.dto.ColumnBean;
import com.ibm.model.BaseObject;
import com.ibm.service.IQueryManager;
import com.ibm.util.StringUtil;
import com.ibm.util.annotation.CacheCondtion;
import com.ibm.util.annotation.DisplayColumn;

/**
 * display标签支持父类 要求JSP页面固定配置：
 * 
 * 
 * <display:table name="resultList" size="totalRows" pagesize="${pageSize}" />
 * 
 * @param <T>实体类型
 * @param <PK>主码类型
 */
@SuppressWarnings("serial")
public abstract class DisplayTagQueryAction<T, PK extends Serializable> extends BaseAction<T, PK> {

	// --------------------------------------------------------------------------------
	// 新增属性，用于接收checkbox参数
	private String[] columnlist;
	
	// 新增属性，用于指定上一次访问操作
	protected String currentUrl;

	public String getCurrentUrl() {
		return currentUrl;
	}

	public void setColumnlist(String[] columnlist) {
		this.columnlist = columnlist;
	}
	
	private Long operatorPK;
	private String operatorContent;
	
	public Long getOperatorPK() {
		return operatorPK;
	}
	public void setOperatorPK(Long operatorPK) {
		this.operatorPK = operatorPK;
	}

	public String getOperatorContent() {
		return operatorContent;
	}
	public void setOperatorContent(String operatorContent) {
		this.operatorContent = operatorContent;
	}

	/**
	 * 每页显示记录的数目
	 */
	private int pageSize;

	/**
	 * 总记录数目
	 */
	private int totalRows;
	
	private String displayTabPara;
	public String getDisplayTabPara() {
		return displayTabPara;
	}
	public void setDisplayTabPara(String displayTabPara) {
		this.displayTabPara = displayTabPara;
	}
	private static final String pageParam = "d-2677329-e";
	
	/**
	 * 数据列表
	 */
	protected List<T> resultList;

	protected Boolean initial;

	protected List<ColumnBean> collist;
	public List<ColumnBean> getCollist() {
		return collist;
	}

	public void setCollist(List<ColumnBean> collist) {
		this.collist = collist;
	}
	
	protected HashMap<String, String> collMap;
	public HashMap<String, String> getCollMap() {
		return collMap;
	}
	public void setCollMap(HashMap<String, String> collMap) {
		this.collMap = collMap;
	}

	@SuppressWarnings("unchecked")
	public List<ColumnBean> getDefaultCollist() {
		List<ColumnBean> allCollist = new ArrayList<ColumnBean>();
		Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		if(entityClass.getSuperclass()!=null){
			if(!entityClass.getSuperclass().equals(BaseObject.class)){
				entityClass = (Class<T>)entityClass.getSuperclass();
			}
		}
		
		boolean filter = false;
		if(collMap!=null && collMap.size()>0){
			filter = true;
		}
		for (Field field : entityClass.getDeclaredFields()) {
			if (field.getAnnotation(DisplayColumn.class) != null) {
				DisplayColumn displayColumn = field.getAnnotation(DisplayColumn.class);
				if(filter && !collMap.containsKey(displayColumn.property())){
					continue;
				}
				ColumnBean columnBean = new ColumnBean();
				if (displayColumn.property() == null || "".equals(displayColumn.property())) {
					columnBean.setProperty(field.getName());
				} else {
					columnBean.setProperty(displayColumn.property());
				}
				if (!"".equals(displayColumn.format())) {
					columnBean.setFormat(displayColumn.format());
				}
				if (!"".equals(displayColumn.decorator())) {
					columnBean.setDecorator(displayColumn.decorator());
				}
				if (displayColumn.sortable()) {
					columnBean.setSortable(displayColumn.sortable());
				}
				if (displayColumn.group()>0) {
					columnBean.setGroup(displayColumn.group());
				}
				if (!"".equals(displayColumn.initFlag())) {
					columnBean.setInitFlag(displayColumn.initFlag());
				}
				if(filter && collMap.containsKey(displayColumn.property())){
					if(StringUtil.isValidString(collMap.get(displayColumn.property()))){
						columnBean.setTitleKey(collMap.get(displayColumn.property()));
					}else{
						columnBean.setTitleKey(displayColumn.titleKey());
					}
				}else{
					columnBean.setTitleKey(displayColumn.titleKey());
				}
				columnBean.setHref(displayColumn.href());
				columnBean.setParamId(displayColumn.paramId());
				columnBean.setParamProperty(displayColumn.paramProperty());
				columnBean.setMedia(displayColumn.media());
				columnBean.setStatus("A");
				String uri=this.getRequest().getRequestURI();
				columnBean.setTabUrl(this.getRequest().getContextPath()+"/"+uri.split("/")[2]+"/"+displayColumn.href());
				columnBean.setTabTitle(this.getText(displayColumn.tabTitle()));
				allCollist.add(columnBean);
			}
		}
		return allCollist;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalRows() {
		return totalRows;
	}

	public Boolean getInitial() {
		return initial;
	}

	public void setInitial(Boolean initial) {
		this.initial = initial;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	public List<T> getResultList() {
		return resultList;
	}

	public void setResultList(List<T> resultList) {
		this.resultList = resultList;
	}

	public IQueryManager<T, PK> getQueryManager() {
		return queryManager;
	}

	// /////////////////////////////////////////////缓存查询条件机制///////////////////////////////////////////

	public void saveQueryCondtion(Object action) throws IllegalArgumentException, IllegalAccessException {
		// 本类
		for (Field field : action.getClass().getDeclaredFields()) {
			// 需要缓存条件
			if (field.getAnnotation(CacheCondtion.class) != null) {
				String key = action.getClass().getName() + "_" + field.getName();
				field.setAccessible(true);
				if (field.get(action) != null) {
					super.setQuerryCondition(key, field.get(action));
				} else {
					super.setQuerryCondition(key, null);
				}
			}
		}
		// 父类
		for (Field field : action.getClass().getSuperclass().getDeclaredFields()) {
			// 需要缓存条件
			if (field.getAnnotation(CacheCondtion.class) != null) {
				String key = action.getClass().getName() + "_" + field.getName();
				field.setAccessible(true);
				if (field.get(action) != null) {
					super.setQuerryCondition(key, field.get(action));
				} else {
					super.setQuerryCondition(key, null);
				}
			}
		}
		Element ele=getItemsOfElement(AppContentGmcc.EXPORT_ROWS_GROUPNAME, AppContentGmcc.EXPORT_ROWS_CODE);
		if(ele!=null && ele.getEleCode()!=null && !ele.getEleCode().equals(""))
			this.queryManager.setMaxPageSize(Integer.valueOf(ele.getEleName()));
	}

	public boolean loadQuerycondtion(Object action) throws IllegalArgumentException, IllegalAccessException {
		boolean flag = false;// 是否存在条件

		// 初始化移除全部查询条件
		if (this.initial != null && this.initial.booleanValue()) {
			super.removeQuerryCondition();
			return false;
		}

		for (Field field : action.getClass().getDeclaredFields()) {
			// 需要缓存条件
			if (field.getAnnotation(CacheCondtion.class) != null) {
				String key = action.getClass().getName() + "_" + field.getName();
				if (super.getQuerryCondition(key) != null) {
					field.setAccessible(true);
					field.set(action, super.getQuerryCondition(key));
					flag = true;
				}
			}
		}
		for (Field field : action.getClass().getSuperclass().getDeclaredFields()) {
			// 需要缓存条件
			if (field.getAnnotation(CacheCondtion.class) != null) {
				String key = action.getClass().getName() + "_" + field.getName();
				if (super.getQuerryCondition(key) != null) {
					field.setAccessible(true);
					field.set(action, super.getQuerryCondition(key));
					flag = true;
				}
			}
		}
		return flag;
	}

	// //////////////////////////////////////////////方法///////////////////////////////////////////

	/**
	 * 过滤条件，封装条件到Criterion对象列表
	 * 
	 * 比较运算符
	 * 
	 * 
	 * HQL运算符 QBC运算符 含义
	 * 
	 * = Restrictions.eq() 等于
	 * 
	 * <> Restrictions.not(Exprission.eq()) 不等于
	 * 
	 * 
	 * > Restrictions.gt() 大于
	 * 
	 * >= Restrictions.ge() 大于等于
	 * 
	 * < Restrictions.lt() 小于
	 * 
	 * <= Restrictions.le() 小于等于
	 * 
	 * is null Restrictions.isnull() 等于空值
	 * 
	 * 
	 * is not null Restrictions.isNotNull() 非空值
	 * 
	 * 
	 * like Restrictions.like() 字符串模式匹配
	 * 
	 * 
	 * and Restrictions.and() 逻辑与
	 * 
	 * 
	 * and Restrictions.conjunction() 逻辑与
	 * 
	 * 
	 * or Restrictions.or() 逻辑或
	 * 
	 * 
	 * or Restrictions.disjunction() 逻辑或
	 * 
	 * 
	 * not Restrictions.not() 逻辑非
	 * 
	 * 
	 * in(列表) Restrictions.in() 等于列表中的某一个值
	 * 
	 * 
	 * ont in(列表) Restrictions.not(Restrictions.in())不等于列表中任意一个值
	 * 
	 * 
	 * between x and y Restrictions.between() 闭区间xy中的任意值
	 * 
	 * 
	 * not between x and y Restrictions.not(Restrictions..between()) 小于值X或者大于值y
	 * 
	 * @return
	 */
	protected List<Criterion> filters() throws Exception {
		return new ArrayList<Criterion>();
	}

	/**
	 * 关联查询的别名定义 Map<表名,别名>
	 * 
	 * @return
	 * @throws Exception
	 */
	protected Map<String, String> alias() throws Exception {
		return new HashMap<String, String>();
	}

	/**
	 * 排序条件，封装条件到Order对象列表
	 * 
	 * @return
	 * @throws Exception
	 */
	protected List<Order> orders() {
		ArrayList<Order> list = new ArrayList<Order>();
		return list;
	}

	/*
	 * 第一次打开页面，不做任何查询
	 * 
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@SuppressWarnings("static-access")
	@Override
	public String execute() throws Exception {
		displaytagCheck();
		if (this.loadQuerycondtion(this)) {
			return this.queryPage();
		} else {
			// --------------------------------------------------------------------------------------------
			initCookie();
			this.currentUrl = "init";
			// --------------------------------------------------------------------------------------------
			return super.SUCCESS;
		}
	}

	protected void initCookie() {
		this.collist = getDefaultCollist();
		if (this.collist.size() == 0)
			return;

		// logger.info("--------------------------"+this.getClass().getName());
		String path = this.getClass().getName();// getRequest().getServletPath();
//		logger.debug("Cookie name:" + path);
		String value = null;
		Cookie[] cookies = getRequest().getCookies();
		if(cookies!=null){
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals(path)) {
					value = cookies[i].getValue();
					break;
				}
			}
		}		
		if (value == null) {
//			logger.debug("没有获得Cookie,读取Annotation");
			StringBuffer cookievalue = new StringBuffer();
			for (int i = 0; i < this.collist.size(); i++) {
				ColumnBean cb = (ColumnBean) this.collist.get(i);
				if(cb.getInitFlag()!=null && cb.getInitFlag().equals("1")){
					cookievalue.append(cb.getProperty() + ":A|");
				}else{
					cookievalue.append(cb.getProperty() + ":B|");
				}
			}

//			logger.debug("读Cookie从Annotation:" + cookievalue);
			String contextPath = this.getRequest().getContextPath();
			String cookieName = path;
			Cookie cookie = new Cookie(cookieName, cookievalue.toString());
			cookie.setPath(contextPath != null && contextPath.length() > 0 ? contextPath : "/");
			cookie.setMaxAge(TokenUtils.TOKEN_COOKIE_MAXAGE);
			getResponse().addCookie(cookie);
			value = cookie.getValue();
		} else {
			if (this.columnlist != null) {
				StringBuffer cookievalue = new StringBuffer();
				for (int i = 0; i < this.collist.size(); i++) {
					ColumnBean cb = (ColumnBean) this.collist.get(i);
					boolean flag = false;
					for (int j = 0; j < this.columnlist.length; j++) {
						if (cb.getProperty().equals(columnlist[j])) {
							flag = true;
							break;
						}
					}
					if (flag) {
						cookievalue.append(cb.getProperty() + ":A|");
						cb.setStatus("A");
					} else {
						cookievalue.append(cb.getProperty() + ":B|");
						cb.setStatus("B");
					}
				}
				String cookieName = path;
				String contextPath = this.getRequest().getContextPath();
				Cookie cookie = new Cookie(cookieName, cookievalue.toString());
				cookie.setPath(contextPath != null && contextPath.length() > 0 ? contextPath : "/");
				cookie.setMaxAge(TokenUtils.TOKEN_COOKIE_MAXAGE);
				getResponse().addCookie(cookie);
				value = cookie.getValue();
			}
		}
//		logger.debug("获得Cookie,直接写入属性:" + value);
		if(value!=null){
			String[] lists = value.split("\\|");
			if(this.collist!=null){
				for (int i = 0; i < this.collist.size(); i++) {
					ColumnBean cb = (ColumnBean) this.collist.get(i);						
					for (int j = 0; j < lists.length; j++) {
						String[] list = lists[j].split(":");
						if(list.length>1){
							if (cb!=null && cb.getProperty().equals(list[0])) {
								cb.setStatus(list[1]);
								if(StringUtil.isValidString(this.displayTabPara)){
									String temp = this.getRequest().getParameter(pageParam);
									if("2".equals(temp)){
										cb.setMedia("csv excel");
										cb.setStatus("A");
									}
								}
								this.collist.set(i, cb);
								break;
							}
						}
					}
				}
			}
		}
	}

	/**
	 * 按条件查询(带有泛型)
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings( { "static-access" })
	public String queryPage() throws Exception {
		displaytagCheck();

		this.saveQueryCondtion(this);
		// 每页行数
		// if not setup pageSize in subclass ,it will use the pageSize value
		// from spring bean in the file db.properties
		if (this.pageSize == 0) {
			this.pageSize = this.configPageSize();
		}
		// Display标签支持,获取需要跳转的页码
		String paraName = new ParamEncoder("resultList").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		Object pobject = (String) getRequest().getParameter((paraName));// 页数
		String exportName = getRequest().getParameter(TableTagParameters.PARAMETER_EXPORTING);

		// 把页码转为整形
		int pageNo;// 需要跳转到第pageNo页

		if (pobject == null) {
			pageNo = 1;
		} else {
			pageNo = Integer.valueOf(pobject.toString());
		}
		if (exportName != null) {
			pageNo = 1;
			this.setPageSize(super.queryManager.getMaxPageSize());
		}
		// 调用服务层得到当前页的列表
		Page<T, PK> page = super.queryManager.pagedQuery(pageNo, this.alias(), this.filters(), this.orders(), this.pageSize);
		// 设置总记录数
		this.totalRows = (int) page.getTotalCount();
		// 设置列表对象
		this.resultList = page.getResult();
		// 翻译字段
		// this.resultList=tranForEleGroup(this.resultList);

		// ------------------------------------------------------------------------------------------
		initCookie();
		this.currentUrl = "query";
		// ------------------------------------------------------------------------------------------
		// 返回

		return super.SUCCESS;
	}

	/**
	 * 设置页码大小
	 * 
	 * @return
	 */
	public int configPageSize() {
		return super.queryManager.getPageSize();
	}

	protected boolean stringIsNotEmpty(String str) {
		if (str != null && !"".equals(str.trim()))
			return true;
		else
			return false;
	}	

}
