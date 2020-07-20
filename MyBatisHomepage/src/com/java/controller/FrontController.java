package com.java.controller;

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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.command.Command;

/**
 * Servlet implementation class FrontController
 */
//@WebServlet("/FrontController")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Logger logger=Logger.getLogger(FrontController.class.getName());
    public String logMsg="logMsg>>> ";
    private HashMap<String, Object> commandMap = new HashMap<String, Object>();
    		
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
    	String path=config.getServletContext().getRealPath(configFile);
    	logger.info(logMsg+path);
    	
    	FileInputStream fis=null;
		BufferedInputStream bis=null;
		Properties pro = new Properties();
		
		try {
			fis=new FileInputStream(path);
			bis=new BufferedInputStream(fis, 1024);
			pro.load(bis);
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if(bis!=null)bis.close();
				if(fis!=null)fis.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		Iterator<Object> keyIter=pro.keySet().iterator();
		while(keyIter.hasNext()) {
			String command=(String)keyIter.next();
			String className=pro.getProperty(command);
			logger.info(logMsg+"[commandKey : "+command+", nameValue : "+className+"]");
			
			try {
				Class<?> handlerClass=Class.forName(className);
				Object handlerInstance=handlerClass.getDeclaredConstructor().newInstance();
				commandMap.put(command, handlerInstance);
				
			}catch(Exception e) {
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
		RequestDispatcher rd=null;
		String viewPage=null;
		try {
			Command comm=(Command)commandMap.get(cmd);
			viewPage=comm.proRequest(request, response);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//원래 그냥 하던 페이지
//		if(viewPage!=null) {
//			RequestDispatcher rd=request.getRequestDispatcher(viewPage);
//			rd.forward(request, response);
//		}
		
		//template 적용
		if(viewPage!=null) {
			if(viewPage.equals("/WEB-INF/views/member/zipcode.jsp")||viewPage.equals("/WEB-INF/views/member/idCheck.jsp")) {
				rd=request.getRequestDispatcher(viewPage);
			}else {
			request.setAttribute("viewPage", viewPage);
			rd=request.getRequestDispatcher("/template/templateIndex.jsp");
			}
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
