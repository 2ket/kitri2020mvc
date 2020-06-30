package com.java.mvc05;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
	// Command pattern : 요청에 대해서 FrontController(서블릿)에서 직접 작업을 처리하지 않고, 해당 클래스가 처리하도록 한다.
	public String actionDo(HttpServletRequest request, HttpServletResponse response) throws Throwable;	//에러생성시 상속하는 클래스에서 처리하도록
}
