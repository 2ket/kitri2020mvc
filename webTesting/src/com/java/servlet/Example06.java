package com.java.servlet;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Example06
 */
/**
 * @author : 김경은
 * @Date : 2020. 6. 8.
 * @Description : new- servlet 파일로 만들면 이렇게 자동으로 생성된다.
 */

public class Example06 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Example06() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* request 객체 역할 : 사용자 요청
			 * 한글 Encoding 기능
			 * parameter 읽기 기능
			 * 서버와 관련된 정보 읽기
			 * 웹브라우저 관련 정보 읽기 기능
			 * 헤더 읽기 기능
			 * 쿠키 읽기 기능
			 * 속성 처리 기능
		 * */
		
		// 한글설정 - 엄청씀
		request.setCharacterEncoding("utf-8");
		
		// 파라미터 읽기 - 엄청씀
		int su=Integer.parseInt(request.getParameter("su"));
		System.out.println(su);
		
		// 서버와 관련된 정보 읽기
//		request.getServerName()
		
		//// Uniform Resource Location = URL ////
		// http://localhost:8181/webTesting/com/java/servlet/Example06
		StringBuffer URL=request.getRequestURL();	
		
		//// Uniform Resource Identifier = URI ////
		// /webTesting/com/java/servlet/Example06
		String URI=request.getRequestURI();			
		
		String contextPath=request.getContextPath();	// /webTesting : 프로젝트 명
		String servletPath=request.getServletPath();	// /com/java/servlet/Example06 : 서블릿이 포함된 풀 패키지부터 서블릿 명까지
		
		/** 4개 암기 - URL, URI, ContextPath, ServletPath*/
		
		//별로 안씀
		System.out.println("서버 이름: "+request.getServerName());
		System.out.println("서버 포트: "+request.getServerPort());
		
		
		//좀씀
		System.out.println("URL: "+URL);
		System.out.println("URI: "+URI);
		System.out.println("컨텍스트(프로젝트명) 경로: "+contextPath);
		System.out.println("서블릿 경로: "+servletPath);
		System.out.println("요청방식: "+request.getMethod());
		
		
		// 웹브라우저 관련 정보 읽기 기능
		System.out.println("====웹브라우저 관련 정보 읽기====");
		System.out.println("요청 프로토콜: "+request.getProtocol());
		System.out.println("클라이언트 주소: "+request.getRemoteAddr());
		System.out.println("클라이언트가 접속한 포트: "+request.getRemotePort());
		
		// 헤더 읽기 기능 - 지금은 안쓰고 response에서 파일 업로드시 헤더부분 바꿔야하는게 있는데 그때 씀
		System.out.println("\n====헤더읽기====");
		Enumeration<String> header=request.getHeaderNames();//헤더는 <key, Value>로 이루어짐(Map방식처럼) 따라서 key값을 추출해서 그 키값을 넣어 값을 찾는다.
		while(header.hasMoreElements()) {
			String key=header.nextElement(); //키값 뽑기
			String value=request.getHeader(key);
			System.out.println(key+":\t\t"+value);
		}
		
		// 쿠키 읽기 기능 - 쿠키 저장 기능 Response(장바구니)
		// 속성 처리 기능 - 서블릿끼리 이동해서 페이지당 데이터 주고받을때 씀
		
		// 데이터가 어디까지 살아있는 범위가 이제 중요하다.
		// 페이지별로 계속 전환해도 데이터가 남아있는 경우 이건 세션처리
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
