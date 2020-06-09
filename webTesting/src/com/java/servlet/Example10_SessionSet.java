package com.java.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Example10_SessionSet
 */
/**
 * @author : 김경은
 * @Date : 2020. 6. 9.
 * @Description : Session
 */
public class Example10_SessionSet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Example10_SessionSet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * 
	 * 
	 *  * SessionScope *
	 * 웹브라우저에서 서버로 어떤 데이터를 요청하면, 서버측에서는 알맞은 로직을 수행한 후
	 * 데이터를 웹브라우저에 응답한 후 서버는 웹브라우저와의 관계를 종료한다.
	 * 연결이 끊어졌을 때 어떤 정보를 지속적으로 유지하기 위한 수단으로 쿠키라는 방식을 사용한다.
	 * 쿠키는 서버에서 생성하여 서버가 아닌 클라이언트측에 특정 정보를 저장한다. (4KB 용량 제한)
	 * 
	 * 세션도 쿠키와 마찬가지로 서버와의 관계를 유지하기 위한 수단입니다.
	 * 단, 쿠키와 달리 클라이언트의 특정 위치에 저장되는 것이 아니라, 서버 상에 객체로 존재한다.
	 * 따라서 세션은 서버에서만 접근이 가능하여 보안에 좋고, 데이터 용량 한계가 없다.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id=request.getParameter("id");
		String pwd=request.getParameter("pwd");
		
		System.out.println(id+"\t"+pwd);
		
		// 세션은 서버에서 자동으로 부여해준다.
		// 현재 session이 존재하면 기존 session을 반환(리턴)하고, 존재하지 않으면 새로운 session을 리턴해준다.
		HttpSession session=request.getSession();
		
		String msg="";
		if(session.isNew()) { //새로운 세션
			msg="새로운 세션이 생성";
			session.setAttribute("id", id);	//session에 "id"라는 key값에 String id값(value)를 저장해준다.
			session.setAttribute("pwd", pwd);
			
		}else {	//기존세션
			
			msg="기존 세션이 반환";
			session.setAttribute("id", id);	//session에 "id"라는 key값에 String id값(value)를 저장해준다.
			session.setAttribute("pwd", pwd);
			
		}
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		out.print("<html>");
		out.print("<head><title>Session</title>");
		out.print("<script type='text/javascript'>");
		out.print("alert('"+msg+" : 로그인 성공');");
		out.print("</script>");
		out.print("</head>");
		out.print("<body>");
		
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
