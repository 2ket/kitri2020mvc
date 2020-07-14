package com.java.board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.board.model.BoardDao;
import com.java.board.model.BoardDto;
import com.java.command.Command;

public class UpdateCommand implements Command {

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		int boardNumber=Integer.parseInt(request.getParameter("boardNumber"));
		int pageNumber=Integer.parseInt(request.getParameter("pageNumber"));
		
		BoardDto boardDto=BoardDao.getInstance().select(boardNumber);
		
		request.setAttribute("boardNumber", boardNumber);
		request.setAttribute("pageNumber", pageNumber);
		request.setAttribute("boardDto", boardDto);
		
		logger.info(logMsg+"수정버튼 누르고 수정입력란에서"+boardDto.getPassword());
		return "/WEB-INF/views/board/update.jsp";
	}

}
