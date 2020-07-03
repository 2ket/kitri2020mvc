package com.java.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.command.Command;

public class LoginOkCommand implements Command {

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		String id=request.getParameter("id");
		String pw=request.getParameter("pw");
		
		logger.info(logMsg+id+"\t"+pw);
		return null;
	}

}
