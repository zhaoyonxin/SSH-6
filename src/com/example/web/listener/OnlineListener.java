package com.example.web.listener;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 在线用户监听器
 */
public class OnlineListener implements ServletContextListener,
	HttpSessionListener, HttpSessionAttributeListener  {
	public static Map<Object, OnlineModel> onlines = new HashMap<>();
	
    public OnlineListener() {
    	
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent se)  { 
    	if("user".equals(se.getName())) {
    		OnlineModel model = (OnlineModel) se.getValue();
    		Object userId = model.getUserId();
    		if(!onlines.containsKey(userId)) {
    			onlines.remove(userId);
    		}
    	}
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent se)  { 
    	if("user".equals(se.getName())) {
    		OnlineModel model = (OnlineModel) se.getValue();
    		Object userId = model.getUserId();
    		if(!onlines.containsKey(userId)) {
    			onlines.put(userId, model);
    		}
    	}
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent se)  { 
    	
    }
	
    @Override
    public void contextInitialized(ServletContextEvent sce) {
		ServletContext application = sce.getServletContext();
		application.setAttribute("onlines", onlines);
	}

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}
    
}
