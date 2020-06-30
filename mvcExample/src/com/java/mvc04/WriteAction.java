package com.java.mvc04;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WriteAction {
	public String DoWrite(HttpServletRequest request, HttpServletResponse response) {
		//DB작업들
		request.setAttribute("message", "act-write작업");
		return "/mvc/write.jsp";
	}
}
