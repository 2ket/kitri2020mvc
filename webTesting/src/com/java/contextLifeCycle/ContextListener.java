package com.java.contextLifeCycle;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author : 김경은
 * @Date : 2020. 6. 10.
 * @Description : 프로젝트(컨텍스트) 생명주기 
 * 웹 어플리케이션 생명 주기(webTexting), context
 * 꽤 자주 쓴다. 서버를 처음 생성하여 초기화할 당시에 적용시킬 것들이 있기 때문이다.
 * 
 * Listener에 대한 web.xml 설정은 맨 윗쪽에 해준다.
 * WEB.XML 등록
 * <listener>
 * 	<listener-class>com.java.contextLifeCycle.ContextListener</listener-class>
 * </listener>
 */
public class ContextListener implements ServletContextListener{
	
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("contextInitialized------------------");
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("------------------contextDestroyed");		
	}
	
	

}
