package com.java.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

import com.java.command.Command;
import com.java.member.model.MemberDao;
import com.java.member.model.MemberDto;

public class RegisterOkCommand implements Command {

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		
		MemberDto memberDto=new MemberDto();
		memberDto.setId(request.getParameter("id"));
		memberDto.setPw(request.getParameter("pw"));
		memberDto.setName(request.getParameter("name"));
		memberDto.setJumin1(request.getParameter("jumin1"));
		memberDto.setJumin2(request.getParameter("jumin2"));
		memberDto.setEmail(request.getParameter("email"));
		memberDto.setZipCode(request.getParameter("zipCode"));
		memberDto.setAddr(request.getParameter("addr"));
		memberDto.setJob(request.getParameter("job"));
		memberDto.setMailing(request.getParameter("mailing"));
//		memberDto.setInterest(Arrays.deepToString(request.getParameterValues("interest")));
		memberDto.setInterest(request.getParameter("interest"));
		
		memberDto.setMemberLevel("BB");	//회원등급은 자바로 넣어주는것이 좋다. 가입날짜는 DB에서 sysdate가 낫다.
		
		logger.info(logMsg+memberDto.toString());
		
		// DAO --> DB --> DAO(DTO) --> Command
		
		int check=MemberDao.getInstance().insert(memberDto);
		logger.info(logMsg+"check : "+check);
		return null;
	}

}
