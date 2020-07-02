package com.java.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.command.Command;

public class ZipcodeCommand implements Command {

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
		String checkDong=request.getParameter("dong");
		logger.info(logMsg+"dong : "+checkDong);
		
		
		if(checkDong!=null) {
			//DB
		}
		
		
		return "/WEB-INF/views/member/zipcode.jsp";
	}

}
