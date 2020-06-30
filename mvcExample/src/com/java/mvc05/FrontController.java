package com.java.mvc05;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FrontController
 */
//@WebServlet("/FrontController")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
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
    	
    	String configFile=config.getInitParameter("configFile");//web.xml에 선언한 param-name이 괄호안의 "configFile"
    	System.out.println("configFile : "+configFile);
    	
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd=request.getServletPath();
//		System.out.println(cmd);	//잘찍히는지 확인 = Map방식의 key값
		
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
