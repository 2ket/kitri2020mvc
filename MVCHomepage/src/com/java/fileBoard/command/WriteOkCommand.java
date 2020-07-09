package com.java.fileBoard.command;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.java.fileBoard.model.BoardDao;
import com.java.fileBoard.model.BoardDto;
import com.java.command.Command;

public class WriteOkCommand implements Command {

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		DiskFileItemFactory factory=new DiskFileItemFactory();		// 파일 보관 객체
		ServletFileUpload upload=new ServletFileUpload(factory);			// 요청 처리 객체
		List<FileItem> list=upload.parseRequest(request);
		Iterator<FileItem> iter=list.iterator();
		
		while(iter.hasNext()) {
			FileItem fileItem=iter.next();
			if(fileItem.isFormField()) {	// text(유저 입력이나 hidden속성으로 넣은 text형태들 : writer, groupNumber, subject...
				String name=fileItem.getFieldName();
				
				logger.info(logMsg+ "text : "+name);
			}else {							// text가 아닌 것들(파일관련) : file
				String name=fileItem.getFieldName();
				logger.info(logMsg+ "binary : "+name+", "+fileItem.getName()+", "+fileItem.getSize());
			}
		}
		return "/WEB-INF/views/fileBoard/writeOk.jsp";
	}

}
