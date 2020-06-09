package com.java.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Example09_CookieSet
 */
public class Example09_CookieSet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Example09_CookieSet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String[] cat=request.getParameterValues("cat");
		System.out.println(cat.length);
		
		// sessionScope : context(프로젝트 webTesting) 안의 모든 page에서 공유
		// response : 쿠기 설정(저장) , request : 쿠키 읽기
		if(cat.length != 0) {
			for(int i=0; i<cat.length; i++) {
				// key, value
				String key="cat"+(i+1);
				String value=cat[i];
				System.out.println(key+"\t"+value);
				
				Cookie cookie=new Cookie(key, value);
				//쿠키는 쿠키폴더, 세션은 서버단에 저장 (장바구니같이 덜중요한것은 쿠키, 아이디 패스워드 같이 중요한건 서버단에 저장하기 위해 세션을 이용)
				cookie.setMaxAge(60*10);	//초*분*시*일	(60*60*24*365) 사용자가 쿠키폴더 삭제하면 끝
				//설정한 쿠키 유지시간 동안, 사용자가 쿠키폴더 지우기 전까지 데이터를 공유할 수 있다.
				response.addCookie(cookie);
				
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
