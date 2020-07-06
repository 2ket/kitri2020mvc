package com.java.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.java.command.Command;
import com.java.member.model.MemberDao;
import com.java.member.model.MemberDto;

public class UpdateOkCommand implements Command {

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		MemberDto memberDto=new MemberDto();
		
		
//		HttpSession session=request.getSession();
//		memberDto.setId((String)session.getAttribute("id"));	//input disabled 면 데이터가 안넘어온다. 그래서 세션id를 받아온다.
		
		memberDto.setNum(Integer.parseInt(request.getParameter("num")));
		
		memberDto.setPw(request.getParameter("pw"));
		memberDto.setEmail(request.getParameter("email"));
		memberDto.setZipCode(request.getParameter("zipCode"));
		memberDto.setAddr(request.getParameter("addr"));
		memberDto.setJob(request.getParameter("job"));
		memberDto.setMailing(request.getParameter("mailing"));
		memberDto.setInterest(request.getParameter("resultInterest"));
		
		logger.info(logMsg+"수정 후 데이터 : "+memberDto.toString());
		
		int check=MemberDao.getInstance().update(memberDto);
		logger.info(logMsg+check);
		
		return null;
	}

}
