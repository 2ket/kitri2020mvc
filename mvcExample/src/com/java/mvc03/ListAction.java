package com.java.mvc03;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListAction {
	public String actionDo(HttpServletRequest request, HttpServletResponse response) {
		//list작업
		request.setAttribute("message", "DB에서 오는 list 값들");
		return "/mvc/list.jsp";
	}
}
