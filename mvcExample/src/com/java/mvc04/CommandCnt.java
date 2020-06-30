package com.java.mvc04;

import java.io.IOException;
import java.util.HashMap;

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
    private HashMap<String, Object> commandMap=new HashMap<String, Object>();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommandCnt() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public void init() throws ServletException {
    	//if(cmd.equals("/write.act")) {
		//	commandAction=new WriteAction();
    	//이부분 계속 반복해서 쓰는게 코드낭비이므로 init에 Map으로 지정해줘서 자동으로 연결되게 할거에요
    	//맨위에 전역에         private HashMap<String, Object> commandMap=new HashMap<String, Object>();
    	
    	
    	WriteAction write=new WriteAction();
    	commandMap.put("/write.act", write);
    	
    	ListAction list=new ListAction();
    	commandMap.put("/list.act", list);
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd=request.getRequestURI();
		cmd=cmd.substring(request.getContextPath().length());
		System.out.println(cmd);	//HashMap의 key값

		
		CommandAction commandAction=(CommandAction) commandMap.get(cmd);
		//HashMap commandMap의 값이 Object이므로 Object를 상속받는 Interface CommandAction으로 받으면 공통적으로 쓸 수 있다.
		
		//	HashMap commandMap 을 이용하면 이렇게 매번 if로 설정 안해도 된다.
//		String view=null;
//		CommandAction commandAction=null;
//		if(cmd.equals("/write.act")) {
//			commandAction=new WriteAction();
//		}else if(cmd.equals("/list.act")) {
//			commandAction=new ListAction();
//		}	
		
		String view=commandAction.actionDo(request, response);	//Interface를 사용하여 매 클래스들의 overriding한 함수를 한번에 view설정
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
