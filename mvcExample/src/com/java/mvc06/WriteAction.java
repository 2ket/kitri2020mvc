package com.java.mvc06;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.mvc06.Command;

public class WriteAction implements Command {

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setAttribute("message", "글쓰기!");
		return "/mvc/write.jsp";
	}

}
