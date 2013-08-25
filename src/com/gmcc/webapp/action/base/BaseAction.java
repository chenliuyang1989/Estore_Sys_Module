package com.gmcc.webapp.action.base;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.mail.SimpleMailMessage;

import com.opensymphony.xwork2.ActionSupport;
import com.gmcc.model.Element;
import com.gmcc.model.User;
import com.gmcc.service.AuthorityManager;
import com.ibm.service.IQueryManager;
import com.gmcc.util.AppContentGmcc;
import com.ibm.util.mail.MailEngine;

/**
 * Implementation of <strong>ActionSupport</strong> that contains convenience
 * methods for subclasses. For example, getting the current user and saving
 * messages/errors. This class is intended to be a base class for all Action
 * classes.
 * 
 */
public class BaseAction<T, PK extends Serializable> extends ActionSupport {

	protected transient final Log log = LogFactory.getLog(getClass());
	
	private static final long serialVersionUID = 3525445612504421307L;
	private static final String SESSION_CONDITION = "sesstion_condition";
	public static final String CANCEL = "cancel";
	private String ids = "";

	protected String cancel;
	protected String from;
	protected String delete;
	protected String save;

	protected MailEngine mailEngine;
	protected SimpleMailMessage mailMessage;
	protected String templateName;

	protected AuthorityManager cacheManager;
	protected IQueryManager<T, PK> queryManager;

	public void setMailEngine(MailEngine mailEngine) {
		this.mailEngine = mailEngine;
	}
	
	public IQueryManager<T, PK> getQueryManager() {
		return queryManager;
	}

	@SuppressWarnings("unchecked")
	public void setQueryManager(IQueryManager<T, PK> queryManager) {
		this.queryManager = queryManager;
		Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
		this.queryManager.setEntityClass(entityClass);
		Class<PK> pkClass = (Class<PK>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[1];
		this.queryManager.setPKClass(pkClass);
	}

	public void setMailMessage(SimpleMailMessage mailMessage) {
		this.mailMessage = mailMessage;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	
	@SuppressWarnings("rawtypes")
	public BaseAction getStrutsAction(){
		return this;
	}
	
	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String[] getSelectIds() {
		return this.ids.split(",");
	}
	
	protected void displaytagCheck() {
		org.displaytag.decorator.CheckboxTableDecorator decorator = new org.displaytag.decorator.CheckboxTableDecorator();
		decorator.setId("id");
		decorator.setFieldName("_chk");
		getRequest().setAttribute("checkboxDecorator", decorator);
	}

	public void setFrom(String from) {
		this.from = from;
	}
	public void setDelete(String delete) {
		this.delete = delete;
	}
	public void setSave(String save) {
		this.save = save;
	}
	public String cancel() {
		return CANCEL;
	}	
	public String list() {
		return "list";
	}
	public String backToList() {
		return "list";
	}
	public String add() {
		return "add";
	}
	public String print() {
		return "print";
	}

	@SuppressWarnings("unchecked")
	protected void saveMessage(String msg) {
		List<String> messages = (List<String>) getRequest().getSession().getAttribute("messages");
		if (messages == null) {
			messages = new ArrayList<String>();
		}
		messages.add(msg);
		getRequest().getSession().setAttribute("messages", messages);
	}

	@SuppressWarnings("rawtypes")
	protected Map getConfiguration() {		
		Map config = (HashMap) getSession().getServletContext().getAttribute(AppContentGmcc.CONFIG);
		// so unit tests don't puke when nothing's been set
		if (config == null) {
			return new HashMap();
		}
		return config;
	}

	protected HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}
	protected HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}
	protected HttpSession getSession() {
		return getRequest().getSession();
	}

	protected void sendUserMessage(User user, String msg, String url) {
		if (log.isDebugEnabled()) {
			// log.debug("sending e-mail to user [" + user.getEmail() + "]...");
		}
		// mailMessage.setTo(user.getFullName() + "<" + user.getEmail() + ">");
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("user", user);
		// model.put("bundle", getTexts());
		model.put("message", msg);
		model.put("applicationURL", url);
		mailEngine.sendMessage(mailMessage, templateName, model);
	}

	/**
	 * 
	 * @param groupName数据组名称
	 *            (名称区分大小写,请与表TB_HMS_ELEMENT_GROUP中字段ELE_GROUP_NAME保持一致)
	 * @return 返回指定数据组名称的数据项列表
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Element> getItemsOfGroup(String groupName) {
		Map<String, List<Element>> cacheItem = (Map<String, List<Element>>) getSession().getAttribute(groupName);
		List<Element> items = new ArrayList<Element>();		
		if (cacheItem != null) {
			Iterator iter = cacheItem.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
//				String oldVersion = (String) entry.getKey();
//				String currentVersion = cacheManager.getCacheVersion(groupName);				
//				if (oldVersion != null && currentVersion != null && !oldVersion.equals(currentVersion)) {
//					items = freshCache(groupName);
//				}
				items = (List<Element>) entry.getValue();
			}
		} else {
			items = freshCache(groupName);
		}

		if (items == null || items.isEmpty()) {
			Element e = new Element(new Long(-1), getText("select.please.choice"));
			items = new ArrayList(1);
			items.add(e);
		}
		return items;
	}

	/**
	 * 从数据库中加载到会话缓存中
	 * 
	 * @param groupName
	 */
	public List<Element> freshCache(String groupName) {
		Map<String, List<Element>> cacheItem;
		List<Element> items = cacheManager.getCacheItems(groupName);
		String currentVersion = cacheManager.getCacheVersion(groupName);
		cacheItem = new HashMap<String, List<Element>>();
		cacheItem.put(currentVersion, items);		
		getSession().setAttribute(groupName, cacheItem);		
		return items;
	}

	/**
	 * 
	 * @param groupCode
	 * @param elementCode
	 * @return 返回指定名称的数据项
	 */
	public Element getItemsOfElement(String groupCode, String elementCode) {
		List<Element> items = this.getItemsOfGroup(groupCode);
		for (Element element : items) {
			if (elementCode.equals(element.getEleCode()))
				return element;
		}
		return null;
	}

	/**
	 * @return the cacheManager
	 */
	public AuthorityManager getCacheManager() {
		return cacheManager;
	}

	/**
	 * @param cacheManager
	 *            the cacheManager to set
	 */
	public void setCacheManager(AuthorityManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	/**
	 * 设置查询条件
	 * 
	 * @param name
	 * @param value
	 */
	@SuppressWarnings("unchecked")
	public void setQuerryCondition(String name, Object value) {
		Map<String, Object> sessionMap = (Map<String, Object>) this.
				getSession().getAttribute(SESSION_CONDITION);
		if (sessionMap == null) {
			sessionMap = new HashMap<String, Object>();
		}
		sessionMap.put(name, value);
		this.getSession().setAttribute(SESSION_CONDITION, sessionMap);
	}

	/**
	 * 读取查询条件
	 * 
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Object getQuerryCondition(String name) {
		Map<String, Object> sessionMap = (Map<String, Object>) this.
				getSession().getAttribute(SESSION_CONDITION);
		if (sessionMap == null) {
			sessionMap = new HashMap<String, Object>();
		}
		return sessionMap.get(name);
	}

	/**
	 * 移除查询条件
	 */
	public void removeQuerryCondition() {
		this.getSession().removeAttribute(SESSION_CONDITION);
	}

}
