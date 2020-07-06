package com.java.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.command.Command;
import com.java.member.model.MemberDao;

public class DeleteOkCommand implements Command {

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");

		logger.info(logMsg + id + "\t" + pw);

		int check = MemberDao.getInstance().delete(id, pw);
		logger.info(logMsg + "삭제결과: " + check);

		request.setAttribute("check", check);
		return "/WEB-INF/views/member/deleteOk.jsp";
	}

}
