package com.java.board.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.board.model.BoardDao;
import com.java.board.model.BoardDto;
import com.java.command.Command;

public class ListCommand implements Command {

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//페이징
		String pageNumber=request.getParameter("pageNumber");
		if(pageNumber==null) pageNumber="1";
		
		int currentPage=Integer.parseInt(pageNumber);	//요청한 페이지
		
		int boardSize=10;	//[1] start:1, end:10 [2] s:11, e:20
		
		int startRow=(currentPage-1)*boardSize+1;		// 시작번호 1 (1~10)
		int endRow=currentPage*boardSize;				// 끝번호 10

		//count 사용해서 글이 아예 없는경우 페이징 안보이게
		int count=BoardDao.getInstance().getCount();
		logger.info(logMsg+count);
		List<BoardDto> boardList=null;
		if(count>0) {
			//startRow, endRow
			boardList=BoardDao.getInstance().getBoardList(startRow, endRow);
			logger.info(logMsg+"boardList.size():"+boardList.size());
		}
		request.setAttribute("boardList", boardList);
		//db에 있는 애들 없는 애들 구분 잘해서 넘겨야 함
		request.setAttribute("boardSize", boardSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("count", count);
		
		
		return "/WEB-INF/views/board/list.jsp";
	}

}
