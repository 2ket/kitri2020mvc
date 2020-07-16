package com.java.reply.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.command.Command;

public class ReplyWriteCommand implements Command {

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String writeReply=request.getParameter("writeReply");
		
		logger.info(logMsg+writeReply);
		
		
		/*
		 * System.out.println("요청 프로토콜: "+request.getProtocol());
		 * System.out.println("클라이언트IP 주소: "+request.getRemoteAddr());
		 * System.out.println("클라이언트가 접속한 포트: "+request.getRemotePort());
		 */
		return null;
	}

}
