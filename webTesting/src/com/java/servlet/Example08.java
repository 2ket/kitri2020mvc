package com.java.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Example08
 */
public class Example08 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Example08() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// include
		request.setCharacterEncoding("utf-8");
		String message=request.getParameter("message");
		System.out.println(message);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		out.print("<html>");
		out.print("<head><title>Include</title></header>");
		out.print("<body>");
		
		//파라미터 통해서 가는건 getParameter
		//내가 임의로 데이터 추가해서 보내주는건 setAttribute 받는건 getAttribute
		request.setAttribute("name", "홍길동");
		request.setAttribute("phone", "010-123-1234");

		//기존 redirect 방식은 웹브라우저가 보내준 response가 가지고있는 함수로 사용했다면
		//RequestDispatcher 클래스(혹은 인터페이스)가 가진 추상함수에 request와 response를 우리가 담아 보냄으로써 데이터 공유가 가능하다.
		RequestDispatcher rd=request.getRequestDispatcher("/com/java/servlet/Example08_Sub");
		
		rd.include(request, response);	//데이터 공유 가능
		

		out.print("<hr color='pink' width='60%'>");
		out.print("<h3>include는 다시 돌아온다. 제어권을 넘겨주지 않는다.</h3>");
		out.print("<h3> 서블릿에서 데이터는 pageScope, requestScope, sessionScope, applicationScope</h3>");
		out.print("</body>");
		out.print("</html>");
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// forward
		
		request.setCharacterEncoding("utf-8");
		String message=request.getParameter("message");
		System.out.println(message);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		out.print("<html>");
		out.print("<head><title>Include</title></header>");
		out.print("<body>");
		
		//파라미터 통해서 가는건 getParameter
		//내가 임의로 데이터 추가해서 보내주는건 setAttribute 받는건 getAttribute
		request.setAttribute("name", "홍길동");
		request.setAttribute("phone", "010-123-1234");

		//기존 redirect 방식은 웹브라우저가 보내준 response가 가지고있는 함수로 사용했다면
		//RequestDispatcher 클래스(혹은 인터페이스)가 가진 추상함수에 request와 response를 우리가 담아 보냄으로써 데이터 공유가 가능하다.
		RequestDispatcher rd=request.getRequestDispatcher("/com/java/servlet/Example08_Sub");
		
		rd.forward(request, response);	//데이터 공유 가능
		// forward는 돌아오지 않는다. 아래 코드들이 실행되지 않는다.

		out.print("<hr color='pink' width='60%'>");
		out.print("<h3>include는 다시 돌아온다. 제어권을 넘겨주지 않는다.</h3>");
		out.print("<h3> 서블릿에서 데이터는 pageScope, requestScope, sessionScope, applicationScope</h3>");
		out.print("</body>");
		out.print("</html>");
		out.close();
	}

}
