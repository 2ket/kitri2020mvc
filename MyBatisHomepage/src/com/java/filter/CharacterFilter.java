package com.java.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		chain.doFilter(request, response);
		//요청이 들어오는 작업은 다 필터 작업을 거쳐서 넘어가게 됨
		//web.xml에서 /*로 슬러시가 그어진 모든 주소에 적용됨. 우선순위) DBinit-CharFilter(/*)-Controller(*.do)
	}

}
