package com.java.mvc05;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

/**
 * Servlet implementation class FrontController
 */
//@WebServlet("/FrontController")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final Logger logger=Logger.getLogger(FrontController.class.getName());	//class 이름 반환String
	public static String logMsg="logMsg >>>>> ";
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
    	// 환경설정을 불러오는 init메소드(properties로 Map 불러와서 controller에 중복코딩 줄이는 방법)
    	// web.xml의 해당 컨트롤러 servlet 선언 안쪽에 init-param에 선언해줘야한다.
    	
    	String configFile=config.getInitParameter("configFile");//web.xml에 선언한 param-name이 괄호안의 "configFile"의 경로
//    	System.out.println("configFile : "+configFile);
    	logger.info(logMsg+configFile);
    	
    	FileInputStream fis=null;
    	BufferedInputStream bis=null;
    	Properties prop=new Properties();
    	try {
    		fis=new FileInputStream(configFile);
    		bis=new BufferedInputStream(fis, 512);
    		prop.load(bis);	//configFile 경로에 있는 .properties파일을 읽어와서 Properties객체에 넣어줌(Map형태=key,value)
    		
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
    	
    	Iterator<Object> keyIter=prop.keySet().iterator();
    	while(keyIter.hasNext()) {
    		String key=(String) keyIter.next();
    		String value=prop.getProperty(key);
//    		System.out.println("key: "+key+"\t value: "+value);
    		logger.info(logMsg+key+"\t"+value);
    		
			try {
				Class<?> handleClass = Class.forName(value);	//String형태의 클래스명을 가진 class 찾기 
				Object handleObj=handleClass.getDeclaredConstructor().newInstance();	//class를 Object로 객체 생성(new Obejct)
				commandMap.put(key, handleObj);	//Map에 Value로 넣어줌
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd=request.getServletPath();
		System.out.println(cmd);	//잘찍히는지 확인 = Map방식의 key값
		
		String view=null;
		try {
			Command comm=(Command) commandMap.get(cmd);
			view=comm.actionDo(request, response);
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
