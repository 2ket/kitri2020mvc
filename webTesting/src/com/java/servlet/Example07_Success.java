package com.java.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Example07_Success
 */
public class Example07_Success extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Example07_Success() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("성공");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		out.print("<html>");
		out.print("<head><title>로그인 성공</title></head>");
		out.print("<body>");
		//주소경로에서 get방식으로 id를 보내줬기 때문에 request로 받을 수 있다. sendRedirect로는 이렇게밖에 못씀
		out.print("<h3> "+request.getParameter("id")+"님 방문을 환영합니다. </h3>");
		
		out.print("</body>");
		out.print("</html>");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
