package com.java.command;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author : 김경은
 * @Date : 2020. 7. 1.
 * @Description : Interface인 Command와 실제 액션을 하는 클래스들을 따로 한 패키지에 묶어두는게 좋다.
 */
public interface Command {
	public Logger logger=Logger.getLogger(Command.class.getName());
	public String logMsg="command logMsg>>>";
	
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable;
}
