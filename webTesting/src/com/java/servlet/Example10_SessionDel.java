package com.java.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Example10_SessionDel
 */
public class Example10_SessionDel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Example10_SessionDel() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그인 했을 때
		if(request.isRequestedSessionIdValid()) {
			System.out.println("세션 아이디가 유효합니다.");
		}else {
			System.out.println("세션 아이디가 유효하지 않습니다.");
		}
		
		HttpSession session=request.getSession();
		
		if(!session.isNew()) {
			session.invalidate();//세션종료
			if(request.isRequestedSessionIdValid()) {
				System.out.println("세션 아이디가 유효합니다.");
			}else {
				System.out.println("세션 아이디가 유효하지 않습니다.");
			}
		}
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		out.print("<html>");
		out.print("<head><title>Session 삭제</title>");
		out.print("</head>");
		out.print("<body>");
		out.print("<h3>로그아웃 되었습니다.</h3>");
		
		out.print("</body>");
		out.print("</html>");
		
		
		/** 세션은 브라우저 끄면 연결 끊김 SessionScope
		 * 애플리케이션은 브라우저 꺼도 연결되어있음. applicationScope
		 * 
		 *  세션은 서버 끌 때, 브라우저 종료할 떄, 로그아웃 할 때 끊김*/
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
