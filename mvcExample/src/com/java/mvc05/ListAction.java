package com.java.mvc05;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListAction implements Command {

	@Override
	public String actionDo(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setAttribute("message", "Frontcontroller-ListAction-글목록");
		return "/mvc/list.jsp";
	}

}
