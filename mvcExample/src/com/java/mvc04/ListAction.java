package com.java.mvc04;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListAction {
	public String DoList(HttpServletRequest request, HttpServletResponse response) {
		//DB작업들
		request.setAttribute("message", ".act확장자 목록보기 작업");
		return "/mvc/list.jsp";
	}
}
