package com.java.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Example03 extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Example03() {
		super();
	}

	@Override
	public void init() throws ServletException {
		//생략가능
		System.out.println("init() 생략가능, 서버 메모리에 처음에만 실행된다.");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String msg=req.getParameter("msg");
		System.out.println(msg);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		String msg=req.getParameter("msg");
		System.out.println(msg);
	}
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//생략가능
		System.out.println("service() 생략가능, 스레드 처리되서 반복 실행");
		//doGet(req, resp);
		doPost(req, resp);
	}

	@Override
	public void destroy() {
		//생략가능
		System.out.println("destroy() 생략가능, 서버 종료 및 업데이트 시");
	}
	
	

}
