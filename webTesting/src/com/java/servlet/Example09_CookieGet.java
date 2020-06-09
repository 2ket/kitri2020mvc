package com.java.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Example09_CookieGet
 */
public class Example09_CookieGet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Example09_CookieGet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Cookie[] cookies=request.getCookies();
		System.out.println(cookies.length);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		out.print("<html>");
		out.print("<head><title>Cookie</title>");
		out.print("</head>");
		out.print("<body>");
		
		String contextPath=request.getContextPath();
		if(cookies!=null) {
			for(int i=0; i<cookies.length; i++) {
				out.print("<h3>"+cookies[i].getName()+"</h3>");
				out.print("<h3>"+cookies[i].getValue()+"</h3>");
				
				String imgStr=contextPath+"/img/"+cookies[i].getValue()+".jpg";
				System.out.println(imgStr);
				out.print("<img src='"+imgStr+"' width='150px' height='100px'/>");
//				out.print("<img src='"+request.getContextPath()+"/img/"+cookies[i].getValue()+".jpg'>");
						
			}
		}
		out.print("</body>");
		out.print("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
