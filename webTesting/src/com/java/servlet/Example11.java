package com.java.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Example11
 */
public class Example11 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Example11() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		
		// 세션 생성시간
		long createTime=session.getCreationTime();
		
		// 세션 마지막 접근 시간
		long lastTime=session.getLastAccessedTime();
				
		// 마지막 접근시간 - 생성시간 = 사이트 머문 시간
		long userTime=(lastTime-createTime)/60000;	//1초가 1000이므로 1분=60초=60000, 초당으로 적으려면 1000으로 나눠줌 
		
		
		Date date=new Date(createTime);
		System.out.println("생성시간: "+createTime+"\t"+date);
		System.out.println("마지막 접근 시간: "+lastTime+"\t"+new Date(lastTime));
		System.out.println("머문시간: "+userTime+"분");
		
//		if(userTime < 5) {
//			session.invalidate();	//5분동안 접속 안하면 자동으로 세션 끊김
//		}
		
		// 위에 주석보단 이걸 더 많이 씀.
		// 액션이 없을 경우 세션이 자동 종료(시간 설정으로)
		// 반환값이 Long형태면 1초가 1000 mili인 경우이고 int인 경우 60/60/24/365 우리가 아는 시간 개념으로 계산하면 된다.
//		session.setMaxInactiveInterval(60*20);	//20분간 반응이 없으면 세션이 끊김
		//부여하지 않으면 자동으로 30분으로 "서버가" 부여한다.
//		int max=session.getMaxInactiveInterval()/60;		// 자동으로 30분 "서버가" 부여
//		System.out.println("서버에서 자동으로 세션 저장기간 지정: "+max+"분");
		
		session.setMaxInactiveInterval(60);
		if(request.isRequestedSessionIdValid()) {
			System.out.println("세션 아이디가 유효합니다.");
		}else {
			System.out.println("세션 아이디가 유효하지 않습니다.");
		}	//우리가 콘솔로 찍기위해선 새로고침해야하는데, 새로고침 하는순간 액션이 입력되기때문에 계속 세션 유효가 될 것.
		
	}
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
