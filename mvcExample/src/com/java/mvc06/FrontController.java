package com.java.mvc06;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FrontController
 */
//@WebServlet("/FrontController")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public static final Logger logger=Logger.getLogger(FrontController.class.getName());
	public static final String logMsg="logMsg>>>>";
	private HashMap<String, Object> commandMap=new HashMap<String, Object>();
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontController() {
        super();
        // TODO Auto-generated constructor stub
    }
    @Override
    public void init(ServletConfig config) throws ServletException {
    	String configFile=config.getInitParameter("configFile");
    	logger.info(logMsg+configFile);
    	
    	String path=config.getServletContext().getRealPath(configFile);	//이거 나중에 이미지 파일 불러오는것도 그렇고 유용함 자주씀
    	logger.info(logMsg+ path);
    	
    	FileInputStream fis=null;
    	BufferedInputStream bis=null;
    	Properties pro=new Properties();
    	
    	try {
    		fis=new FileInputStream(path);
    		bis=new BufferedInputStream(fis, 1024);
    		pro.load(bis);
    	}catch(IOException e) {
    		e.printStackTrace();
    	}finally {
    		try {
    			if(bis!=null) bis.close();
    			if(fis!=null) fis.close();
    		}catch(IOException e) {
    			e.printStackTrace();
    		}
    	}
    	
    	Iterator<Object> keyIter=pro.keySet().iterator();
    	while(keyIter.hasNext()) {
    		String key=(String)keyIter.next();
    		String value=pro.getProperty(key);
    		logger.info(logMsg+"[key: "+key+", \t value :"+value+"]");
    		
    		try {
				Class<?> handlerClass=Class.forName(value);
				Object handlerObj=handlerClass.getDeclaredConstructor().newInstance();
				commandMap.put(key, handlerObj);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd=request.getServletPath();
		logger.info(logMsg+cmd);
		
		Command com=(Command)commandMap.get(cmd);
		String view=null;
		try {
			view=com.proRequest(request, response);	//interface를 상속받은 클래스(doGet함수 실행할때 입력된 cmd에 맞는 클래스명을 가진) 실행
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(view!=null) {
			RequestDispatcher rd=request.getRequestDispatcher(view);
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
