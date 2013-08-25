package com.gmcc.webapp.listener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gmcc.dao.UserDAO;
import com.gmcc.model.Element;
import com.gmcc.model.ElementGroup;
import com.gmcc.util.AppContentGmcc;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * <p>StartupListener class used to initialize and database settings
 * and populate any application-wide drop-downs.
 * <p/>
 * <p>Keep in mind that this listener is executed outside of OpenSessionInViewFilter,
 * so if you're using Hibernate you'll have to explicitly initialize all loaded data at the
 * GenericDao or service level to avoid LazyInitializationException. Hibernate.initialize() works
 * well for doing this.
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public class StartupListener implements ServletContextListener {
    private static final Log log = LogFactory.getLog(StartupListener.class);

    @SuppressWarnings({"unchecked"})
    public void contextInitialized(ServletContextEvent event) {
        log.debug("Initializing context...");

        ServletContext context = event.getServletContext();

        // Orion starts Servlets before Listeners, so check if the config
        // object already exists
        Map<String, Object> config = (HashMap<String, Object>) context.getAttribute(AppContentGmcc.CONFIG);

        if (config == null) {
            config = new HashMap<String, Object>();
        }
        
        if (context.getInitParameter(AppContentGmcc.CSS_THEME) != null) {
            config.put(AppContentGmcc.CSS_THEME, context.getInitParameter(AppContentGmcc.CSS_THEME));
        }

        context.setAttribute(AppContentGmcc.CONFIG, config);

        //setupContext(context);
    }

    /**
     * This method uses the LookupManager to lookup available roles from the data layer.
     * @param context The servlet context
     */
    public static void setupContext(ServletContext context) {
        ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
        UserDAO userDAO = (UserDAO) ctx.getBean("userDAO");
        log.debug("Drop-down initialization starting...");
        List<ElementGroup> groups = userDAO.getAllGroups();
        for(ElementGroup g:groups){
        	List<Element> items = userDAO.getItemsOfGroup(g.getEleGroupName());
        	context.setAttribute(g.getEleGroupName(), items);
        }
        // get list of possible roles
        //context.setAttribute(Constants.AVAILABLE_ROLES, mgr.getAllRoles());
        log.debug("Drop-down initialization complete [OK]");
    }

    /**
     * Shutdown servlet context (currently a no-op method).
     *
     * @param servletContextEvent The servlet context event
     */
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        //LogFactory.release(Thread.currentThread().getContextClassLoader());
        //Commented out the above call to avoid warning when SLF4J in classpath.
        //WARN: The method class org.apache.commons.logging.impl.SLF4JLogFactory#release() was invoked.
        //WARN: Please see http://www.slf4j.org/codes.html for an explanation.
    }
}
