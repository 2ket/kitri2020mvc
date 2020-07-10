package com.java.fileBoard.command;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.java.command.Command;
import com.java.fileBoard.model.BoardDao;
import com.java.fileBoard.model.BoardDto;

public class DownLoadCommand implements Command {

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		int boardNumber=Integer.parseInt(request.getParameter("boardNumber"));
		logger.info(logMsg+boardNumber);
		
		BoardDto boardDto=BoardDao.getInstance().select(boardNumber);
		logger.info(logMsg+boardDto);
		
		int index=boardDto.getFileName().indexOf('_')+1;
		String fileName=boardDto.getFileName().substring(index);
		String utfName=new String(fileName.getBytes("utf-8"), "ISO-8859-1");
		long fileSize=boardDto.getFileSize();
		String path=boardDto.getPath();
		
		//content_Disposition : 다운로드 할때 나오는 그 창 띄우게 하는거
		response.setHeader("content-Disposition", "attachment;filename="+utfName);//정해진 고정 문구니까 외우기
		//한글 깨짐 위해 utf-8로 인코딩 해줘야한다. 혹은 //URLEncoder.encode(fileName, "utf-8")넣어준다.
		
		
		response.setContentType("application/octet-stream");	//8진수(octet) : binary형태일때
		response.setContentLengthLong(fileSize);
		
		
		BufferedInputStream bis=null;
		BufferedOutputStream bos=null;
		try {
			bis=new BufferedInputStream(new FileInputStream(path), 1024);
			bos=new BufferedOutputStream(response.getOutputStream(), 1024);
			while(true) {
				int data=bis.read();
				if(data==-1) break;
				bos.write(data);
			}
			bos.flush();
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			if(bis!=null) bis.close();
			if(bos!=null) bos.close();
		}
		
		
		return null;
	}

}
