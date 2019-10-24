package common;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.es.cache.CacheMgr;

/**
 * Application Lifecycle Listener implementation class ContemtListener
 *
 */
@WebListener
public class ContemtListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public ContemtListener() {
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  {
    	CacheMgr.stopClearThread();
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  { 
         return;
    }
	
}
