package com.java.fileBoard.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.fileBoard.model.BoardDao;
import com.java.fileBoard.model.BoardDto;
import com.java.command.Command;

public class UpdateOkCommand implements Command {

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		
		BoardDto boardDto=new BoardDto();
		
		boardDto.setBoardNumber(Integer.parseInt(request.getParameter("boardNumber")));
		boardDto.setEmail(request.getParameter("email"));
		boardDto.setSubject(request.getParameter("subject"));
		boardDto.setContent(request.getParameter("content"));
		
		int check=BoardDao.getInstance().update(boardDto);
		logger.info(logMsg+"수정결과: "+check);
		
		request.setAttribute("pageNumber", request.getParameter("pageNumber"));
		request.setAttribute("boardNumber", request.getParameter("boardNumber"));
		request.setAttribute("check", check);
		return "/WEB-INF/views/fileBoard/updateOk.jsp";
	}

}
