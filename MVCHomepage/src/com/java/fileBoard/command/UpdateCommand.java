package com.java.fileBoard.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.fileBoard.model.BoardDao;
import com.java.fileBoard.model.BoardDto;
import com.java.command.Command;

public class UpdateCommand implements Command {

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		int boardNumber=Integer.parseInt(request.getParameter("boardNumber"));
		int pageNumber=Integer.parseInt(request.getParameter("pageNumber"));
		
		BoardDto boardDto=BoardDao.getInstance().select(boardNumber);
		
		if(boardDto.getFileSize()!=0) {
			boardDto.setFileName(boardDto.getFileName().substring(boardDto.getFileName().indexOf('_')+1));
		}
		request.setAttribute("boardNumber", boardNumber);
		request.setAttribute("pageNumber", pageNumber);
		request.setAttribute("boardDto", boardDto);
		
		
		return "/WEB-INF/views/fileBoard/update.jsp";
	}

}
