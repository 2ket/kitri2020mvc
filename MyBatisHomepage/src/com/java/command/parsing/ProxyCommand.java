package com.java.command.parsing;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;

import com.java.command.Command;

public class ProxyCommand implements Command {

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		// 프록시 서버 :	시스템에 방화벽을 가지고 있는 경우, 접근할 수 없다. 외부와 통신을 위해 만들어 놓은 가상 서버
		//			방화벽 안쪽에 있는 서버들의 외부 연결은 프록시 서버를 통해 이루어진다.
		
		
		String url="http://www.kma.go.kr/weather/forecast/mid-term-rss3.jsp?stnId=109";
		//외부에서 오는건 다 GET방식. POST일 수 없다. GET방식은 특수문자 못읽음.숫자와 영어만.
		GetMethod method=new GetMethod(url);
		
		HttpClient client=new HttpClient();
		//client.executeMethod(method);	//api보면 나와여
		int statusCode=client.executeMethod(method);
		logger.info(logMsg+"statusCode : "+statusCode);	//200번, 혹은 400번 나오면 정상
		
		if(statusCode==HttpStatus.SC_OK) {
			String result=method.getResponseBodyAsString();
			//logger.info(logMsg+"result"+result);
		}
		return null;
	}

}
