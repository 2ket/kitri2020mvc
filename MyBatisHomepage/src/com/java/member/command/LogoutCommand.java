package com.java.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.java.command.Command;

public class LogoutCommand implements Command {

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//java단에서 세션 끊어주기
//		HttpSession session=request.getSession();
//		if(!session.isNew()) {
//			String id=(String)session.getAttribute("id");
//			String memberLevel=(String) session.getAttribute("memberLevel");
//			logger.info(logMsg+id+"\t"+memberLevel);
//			
//			session.invalidate();	//세션끊기
//			
//		}
//		return "/index.jsp";
		return "/WEB-INF/views/member/logout.jsp";
	}

}
