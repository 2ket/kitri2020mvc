package com.java.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Example07
 */
public class Example07 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Example07() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* response객체
		 * 문서타입, 한글 : setContentType() 설정 
		 * 출력 : getWriter()
		 * 쿠키 저장(설정) 기능, 헤더 설정(파일 업로드시) 기능
		 * 페이지 이동 : sendRedirect , 인크루드, 포웨이드
		 * */
		
		
		request.setCharacterEncoding("utf-8");
		String id=request.getParameter("id");
		String pwd=request.getParameter("pwd");
		System.out.println(id+"\t"+pwd);
		
		//원래 실제 db에 연동해야 하는데 없어서 여기서 임의로 만듦
		String dbId="abc123";
		String dbPwd="abc123";
		
		//URL, URI
		if(id.equals(dbId) && pwd.equals(dbPwd)) {
			//sendRedirect는 데이터 공유는 x 페이지 이동해도 데이터 공유하고싶다면 포웨이드, 인크루드. 혹은 주소경로 뒤 get방식으로 붙여줘야한다.
			response.sendRedirect("/webTesting/com/java/servlet/Example07_Success?id="+id);
			
		}else {
			response.sendRedirect("/webTesting/com/java/servlet/Example07_Fail");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
