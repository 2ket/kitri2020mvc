package com.java.board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.board.model.BoardDao;
import com.java.board.model.BoardDto;
import com.java.command.Command;

public class UpdateOkCommand implements Command {

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		
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
		return "/WEB-INF/views/board/updateOk.jsp";
	}

}
