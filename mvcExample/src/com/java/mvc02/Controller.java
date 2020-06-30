package com.java.mvc02;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Controller
 */
//@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Controller doGet Function...");
		
		System.out.println("getRequestURL: "+request.getRequestURL());
		System.out.println("getRequestURI: "+request.getRequestURI());
		System.out.println("getContextPath: "+request.getContextPath());
		System.out.println("getServletPath: "+request.getServletPath());
		System.out.println();
		
		int endIndex=request.getServletPath().indexOf(".kitri");
		String key=request.getServletPath().substring(1, endIndex);
		System.out.println(key);
		
		String uri=request.getRequestURI();
		String cmd=uri.substring(request.getContextPath().length());
		System.out.println(cmd);
		
		String view=null;
		if(cmd.equals("/write.kitri")) {
			// 클래스 Write w=new Write();	--> DTO, DAO --> DB --> DTO, DAO --> Controller -->write.jsp
			request.setAttribute("message", "글쓰기");
			view="/mbc/write.jsp";	//resultView
		}else if(cmd.equals("/list.kitri")) {
			// 클래스 List i=new List(); --> DTO, DAO --> DB --> DTO, DAO --> Controller --> list.jsp
			request.setAttribute("message", "글목록");
			view="/mvc/list.jsp";
		}
		if(view!=null) {
			RequestDispatcher rd=request.getRequestDispatcher(view);	//절대 잊으면 안됨!!!!RD!!!
			rd.forward(request, response);	//resultView로 넘어가면서 데이터도 같이 넘김. 다시 돌아올필요 없는 forward
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
