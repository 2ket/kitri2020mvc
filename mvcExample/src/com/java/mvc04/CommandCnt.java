package com.java.mvc04;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CommandCnt
 */
//@WebServlet("/CommandCnt") 어노테이션 나중에!
public class CommandCnt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommandCnt() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd=request.getRequestURI();
		cmd=cmd.substring(request.getContextPath().length());
		System.out.println(cmd);
		
		String view=null;
		CommandAction commandAction=null;
		if(cmd.equals("/write.act")) {
			commandAction=new WriteAction();
		}else if(cmd.equals("/list.act")) {
			commandAction=new ListAction();
		}
		view=commandAction.actionDo(request, response);	//Interface를 사용하여 한번에 view설정
		if(view!=null) {
			RequestDispatcher rd=request.getRequestDispatcher(view);
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
