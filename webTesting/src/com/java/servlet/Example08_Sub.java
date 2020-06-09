package com.java.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Example08_Sub
 */
public class Example08_Sub extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Example08_Sub() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String message=request.getParameter("message");
		String name=(String)request.getAttribute("name"); //setAttribute(name, Object)였기 때문에 Object로 업캐스팅된 상태이므로 다시 String으로 다운캐스팅 해준다.
		String phone=(String)request.getAttribute("phone");
		System.out.println("Example08_Sub message : "+ message);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
//		out.print("<html>");
//		out.print("<head><title>Example08_Sub message</title></header>");
//		out.print("<body>");

		out.print("<h3 style='color: blue;'>"+message+"</h3>");
		out.print("<h3 style='color: blue;'>"+name+"</h3>");
		out.print("<h3 style='color: blue;'>"+phone+"</h3>");
		out.print("<hr color='red' width='80%'>");
		
//		out.print("</body>");
//		out.print("</html>");
//		out.close();
		//closee() : include 방식에서는 스트림 닫혀서 돌아갔을 때 출력이 안된다. forward는 돌아가지 않으므로 이곳에서 써줘도 된다.
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
