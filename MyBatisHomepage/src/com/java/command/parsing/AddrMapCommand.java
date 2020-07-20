package com.java.command.parsing;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.command.Command;

public class AddrMapCommand implements Command {

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		// 가져오는지 확인해보고 못가져오면 프록시 작업 해줘야함
		
		
		
		return "/WEB-INF/views/ajax/map/addrMap.jsp";
	}

}
