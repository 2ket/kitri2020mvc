package com.java.mvc05;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WriteAction implements Command {

	@Override
	public String actionDo(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("message", "Frontcontroller-ListAction-글쓰기");
		return "/mvc/write.jsp";
	}

}
