package com.java.servlet;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author : 김경은
 * @Date : 2020. 6. 5.
 * @Description : Servlet
 * 서블릿 : 웹환경을 사용할 수 있게끔 해주는 java파일=서블릿파일. HttpServlet을 받아준다.
 *
 * 서버의 메모리가 날라가는 경우 1. 서버가 종료되었을때, 2. 소스가 수정되어 업데이트 되는 동안
 * 
 * 톰캣서버 폴더에 저장된 경로들 알고있어야함
 * 
 * 최초 한번 init으로 페이지(컴파일클래스) 생성 후 서비스()로 스레드 로직은 기본탑재라 내가 만들필요x
 * 
 * init() : 서블릿 파일 초기화(처음 요청시 단 한번)
 * service(혹은 doGet) : 새로고침시, 요청할때마다
 * destroy() : 서버 종료시, 서비스나 doGet 소스 수정후 적용업데이트 할때 실행
 */
//웹을 하려면 Http를 상속해야함.
public class Example01 extends HttpServlet{
	private static final long serialVersionUID=1L;
	
	//서버 메모리 : Content 단위로 올라간다. 각각의 Content 안에 Context(webTesting 프로젝트)있다.
	public Example01() {
		super();
	}
	
	//생명 주기 - 초기화 - 서비스 - 소멸
	public void init() {	//초기화
		System.out.println("서블릿 파일 초기화(처음 요청시 단 한번)");
	}
	public void service(HttpServletRequest request, HttpServletResponse response ) {
		//서비스 - get, post 함수 호출 : 요 두 함수로 시작함
		doGet(request, response);
	}
	//HttpRequest??
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("수정한 서블릿 내용 doGet 함수입니다.");
		Cookie[] cookies=request.getCookies();
		System.out.println("cookie 갯수 : "+cookies.length);
	}
	 public void destroy() {
		 //소멸
		 System.out.println("서블릿 파일 소멸");
	 }
}
