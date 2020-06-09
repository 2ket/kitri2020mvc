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
 * Servlet implementation class Example09_CookieDel
 */
public class Example09_CookieDel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Example09_CookieDel() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies=request.getCookies();
		
		if(cookies!=null) {
			for(int i=0; i<cookies.length; i++) {
				cookies[i].setMaxAge(0);			//쿠키의 저장기간을 0으로 만들어줌
				response.addCookie(cookies[i]);		//설정한 쿠키값을 저장하여 적용시킴
			}
		}
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		out.print("<html>");
		out.print("<head><title>Cookie Del</title>");
		out.print("<script type='text/javascript'>");
		out.print("alert('장바구니를 비웠습니다..');");
		out.print("location.href='http://localhost:8181/webTesting/Servlet/09_example.html';");
		out.print("</script>");
		out.print("</head>");
		out.print("<body>");
		
		
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
