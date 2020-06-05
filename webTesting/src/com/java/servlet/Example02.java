package com.java.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author : 김경은
 * @Date : 2020. 6. 5.
 * @Description :  
 */
public class Example02 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
/** 생략가능
	public void init() {
		
	}
*/
	
/** 생략가능
	public void service(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}
*/
	
	// init() - service() - doGet() - destroy()
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		String su=request.getParameter("su");
		System.out.println("su: "+su);
		
		// 문자열 -> 정수
		int value=Integer.parseInt(su);
		System.out.println(value+10);
		
		// 정수 -> 문자열
		String a=String.valueOf(value);
		System.out.println(a+10);
		
		Integer i=value;
		String b=i.toString();
		System.out.println(a+"\t"+b);
	}
	
/** 생략가능
	public void destroy() {
		
	}

* 서버페이지 요청하면 이닛 불러주고, 서비스 호출해서 요청방식에 맞춰서 두겟 두포스트 불러주고, 끄면 디스트로이 불러줌
* 없으면 디폴트값 생성
*/
	

}
