package com.java.mvc03;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WriteAction {
	public String actionDo(HttpServletRequest request, HttpServletResponse response) {
		//글쓰기 작업 - DAO, DTO
		
		request.setAttribute("message", "글쓰기 - (DB에서 넘어오는 값들)");
		
		return "/mvc/write.jsp";
	}
}
