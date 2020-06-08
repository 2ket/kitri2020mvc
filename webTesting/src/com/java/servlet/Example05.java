package com.java.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Example05 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int su=Integer.parseInt(req.getParameter("su"));
		
		String buho=req.getParameter("buho");
//		char ch=buho.charAt(0);	//if(ch=='+'){} 이런식으로 char로 비교하는 방법도 있다.
		
		int value=Integer.parseInt(req.getParameter("value"));
		
		System.out.println(su +"\t"+buho+"\t"+value);	//제대로 출력되는지 중간에 찍어서 확인해보기
		
		String result="";
		if(buho.equals("+")) {
			/*	절대 까먹으면 안됨!! 형변환
			 * Integer r=su+value; 
			 * result=r.toString();
			 */
			
			result=String.valueOf(su+value);	//방식은 toString, valueOf 두가지 방법
		}else if(buho.equals("-")) {
			result=String.valueOf(su-value);
		}else if(buho.equals("*")) {
			result=String.valueOf(su*value);
		}else if(buho.equals("/")) {
			result=String.valueOf((float)su/value);
		}
		
		System.out.println("result= "+result);
		// 잘못 입력했을때의 처리는 view단에서 처리하는것이 좋다.
		// 자바는 그거 아니더라도 수많은 오류를 잡기 바쁘다..
		
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out=resp.getWriter();
		
		out.print("<html>");
		out.print("<head><title>사칙연산</title></head>");
		out.print("<body>");
		out.print("<h3>결과 : "+result+"</h3>");
		out.print("</body>");
		out.print("</html>");
		out.close();
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
