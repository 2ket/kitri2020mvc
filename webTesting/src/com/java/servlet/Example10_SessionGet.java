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
 * Servlet implementation class Example10_SessionGet
 */
public class Example10_SessionGet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Example10_SessionGet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		
		if(!session.isNew()) {	//기존 세션일경우
			String id=(String)session.getAttribute("id"); //"id"라는 key값을 가진 value(Object)를 String으로 다운캐스팅
			String pwd=(String)session.getAttribute("pwd");
			
//			System.out.println(id + "\t" + pwd + "\t" + session.getId());
			
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out=response.getWriter();
			out.print("<html>");
			out.print("<head><title>Session</title>");
//			out.print("<script type='text/javascript'>");
//			out.print("</script>");
			out.print("</head>");
			out.print("<body>");
			out.print("<h3> 아이디: "+id+"</h3>");
			out.print("<h3> 비밀번호: "+pwd+"</h3>");
			out.print("</body>");
			out.print("</html>");
			
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
