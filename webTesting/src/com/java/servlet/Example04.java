package com.java.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Example04 extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		String name=req.getParameter("name");
		String phone=req.getParameter("phone");
		String addr=req.getParameter("addr");
		
		System.out.println(name+"\t"+phone+"\t"+addr);
		// html, js, css 단으로 출력되는 속성들 = 컴포넌트 Component
		
//PrintWriter(출력을 위한 보조스트림) - BufferedWriter(char 문자) - OutputStreamWriter(char->byte 변환) - socket.getOutputStream() (byte binary)
		
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter pw=resp.getWriter();
		pw.print("<html>");
		pw.print("<head><title></title></head>");
		pw.print("<body>");
		pw.print("<h3>회원가입이 잘 되었습니다. </h3>");
		
		pw.print("</body></html>");
		// 이건 지저분한 하드코딩임. 예시를 들기위해 표현했을뿐
		//나중에 jsp, jstl, el로 바꿔서 출력할거임.
		pw.close();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
		//함수 중복을 막기 위해 doGet에 구현해두고 같은걸 시행하되 post형식으로 데이터 주고받고싶다면 이렇게 doGet을 호출한다
		}
}
