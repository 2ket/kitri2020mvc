package com.java.fileBoard.command;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
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
		
		BoardDto boardDto=new BoardDto();
		HashMap<String, String> dataMap=new HashMap<String, String>();
		
		while(iter.hasNext()) {
			FileItem fileItem=iter.next();
			if(fileItem.isFormField()) {	// text(유저 입력이나 hidden속성으로 넣은 text형태들 : writer, groupNumber, subject...
				//필드 이름 찍기
//				String name=fileItem.getFieldName();
//				logger.info(logMsg+ "text : "+name);
				//맵방식이 아닌 기존의 경우 아래와 같이 필드마다 dto에 넣어줘야함
//				if(fileItem.getFieldName().equals("boardNumber")) {
//					boardDto.setBoardNumber(Integer.parseInt(fileItem.getString()));
//				}
				
				String name=fileItem.getFieldName();
				String value=fileItem.getString("utf-8");
				logger.info(logMsg+name+":"+value);
				
				dataMap.put(name, value);
				
			}else {							// text가 아닌 것들(파일관련) : file
//				String name=fileItem.getFieldName();
//				logger.info(logMsg+ "binary : "+name+", "+fileItem.getName()+", "+fileItem.getSize());
				if(fileItem.getFieldName().equals("file")) {
					//파일명 fileItem.getName() / 파일사이즈 fileItem.getSize() / getInputStream()
					if(fileItem.getName()==null || fileItem.getName().equals("")) continue;
					
					upload.setFileSizeMax(1024*1024*10); 	// byte*kb*mb*gb
					String fileName=System.currentTimeMillis()+"_"+fileItem.getName();
					
					String dir="C:\\Kitri2020\\mvc\\workspace\\MVCHomepage\\WebContent\\pds\\";
					File file=new File(dir, fileName);
					
//					logger.info(logMsg+file.getAbsolutePath());
					BufferedInputStream bis=null;	// 클라이언트의 파일을 읽어서
					BufferedOutputStream bos=null; 	// 서버에 저장
					try {
						bis=new BufferedInputStream(fileItem.getInputStream(), 1024);
						bos=new BufferedOutputStream(new FileOutputStream(file), 1024);
						
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
					boardDto.setFileName(fileName);
					boardDto.setFileSize(fileItem.getSize());
					boardDto.setPath(file.getAbsolutePath());
				}
				
			}
			
		}
		boardDto.setDataMap(dataMap);
		boardDto.setWriteDate(new Date());
		logger.info(logMsg+boardDto.toString());
		
		int check=BoardDao.getInstance().insert(boardDto);
		logger.info(logMsg+check);
		
		return "/WEB-INF/views/fileBoard/writeOk.jsp";
	}

}
