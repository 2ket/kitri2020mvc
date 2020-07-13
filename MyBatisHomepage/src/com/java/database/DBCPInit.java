package com.java.database;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class DBCPInit extends HttpServlet{
	private static final long serialVersionUID=1L;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		try {
			String jdbcDriver=config.getInitParameter("jdbcDriver");
			Class.forName(jdbcDriver);
			System.out.println("jdbc 드라이버 찾았습니다.");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
