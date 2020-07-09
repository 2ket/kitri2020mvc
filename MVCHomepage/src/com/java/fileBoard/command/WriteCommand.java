package com.java.fileBoard.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.command.Command;

public class WriteCommand implements Command {

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//부모글일 때*(ROOT)
		int boardNumber=0;		// ROOT글이면 0, 
		int groupNumber=1;		// 그룹번호
		int sequenceNumber=0;	// 글 순서
		int sequenceLevel=0;	// 글 레벨
		
		//답글일때 boardNumber=> ROOT의 글번호, 그룹번호, 글순서, 글레벨
		if(request.getParameter("boardNumber")!=null) {
			//나중에
			boardNumber=Integer.parseInt(request.getParameter("boardNumber"));
			groupNumber=Integer.parseInt(request.getParameter("groupNumber"));
			sequenceNumber=Integer.parseInt(request.getParameter("sequenceNumber"));
			sequenceLevel=Integer.parseInt(request.getParameter("sequenceLevel"));
		}
		
		
		
		logger.info(logMsg+boardNumber+"\t"+groupNumber+"\t"+sequenceNumber+"\t"+sequenceLevel);
		
		request.setAttribute("boardNumber", boardNumber);
		request.setAttribute("groupNumber", groupNumber);
		request.setAttribute("sequenceNumber", sequenceNumber);
		request.setAttribute("sequenceLevel", sequenceLevel);
		
		return "/WEB-INF/views/fileBoard/write.jsp";
	}

}
