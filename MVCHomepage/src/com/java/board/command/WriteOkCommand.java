package com.java.board.command;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.board.model.BoardDao;
import com.java.board.model.BoardDto;
import com.java.command.Command;

public class WriteOkCommand implements Command {

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("utf-8");
		
		BoardDto boardDto=new BoardDto();
		boardDto.setBoardNumber(Integer.parseInt(request.getParameter("boardNumber")));
		boardDto.setGroupNumber(Integer.parseInt(request.getParameter("groupNumber")));
		boardDto.setSequenceNumber(Integer.parseInt(request.getParameter("sequenceNumber")));
		boardDto.setSequenceLevel(Integer.parseInt(request.getParameter("sequenceLevel")));
		
		boardDto.setWriter(request.getParameter("writer"));
		boardDto.setSubject(request.getParameter("subject"));
		boardDto.setEmail(request.getParameter("email"));
		boardDto.setContent(request.getParameter("content"));
		boardDto.setPassword(request.getParameter("password"));
		boardDto.setWriteDate(new Date());		//DB에 넣을때 시간으로 바꿔서 넣어줘야함
		
		
		logger.info(logMsg+boardDto);
		int check=BoardDao.getInstance().insert(boardDto);
		logger.info(logMsg+check);
		
		return "/WEB-INF/views/board/writeOk.jsp";
	}

}
