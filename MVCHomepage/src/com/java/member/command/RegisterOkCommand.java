package com.java.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.command.Command;

public class RegisterOkCommand implements Command {

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		
		String id=request.getParameter("id");
		String pw=request.getParameter("pw");
		String name=request.getParameter("name");
		String jumin1=request.getParameter("jumin1");
		String jumin2=request.getParameter("jumin2");
		String email=request.getParameter("email");
		String zipCode=request.getParameter("zipCode");
		String addr=request.getParameter("addr");
		
		String job=request.getParameter("job");
		String mailing=request.getParameter("mailing");
		String[] interest=request.getParameterValues("interest");
		
		logger.info(logMsg+id+"\t"+pw+"\t"+name+"\t"+jumin1+"-"+jumin2+"\t"+email+"\t"+zipCode+"\t"+addr+"\t"+job+"\t"+mailing+"\t["+interest+"]");
		
		
		
		return null;
	}

}
