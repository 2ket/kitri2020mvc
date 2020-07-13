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

public class UpdateOkCommand implements Command {

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		BoardDto boardDto=new BoardDto();

		DiskFileItemFactory factory=new DiskFileItemFactory();		// 파일 보관 객체
		ServletFileUpload upload=new ServletFileUpload(factory);			// 요청 처리 객체
		List<FileItem> list=upload.parseRequest(request);
		Iterator<FileItem> iter=list.iterator();

		HashMap<String, String> dataMap=new HashMap<String, String>();
		
		while(iter.hasNext()) {
			FileItem fileItem=iter.next();
			if(fileItem.isFormField()) {	// text(유저 입력이나 hidden속성으로 넣은 text형태들 : writer, groupNumber, subject...

				String name=fileItem.getFieldName();
				String value=fileItem.getString("utf-8");
				logger.info(logMsg+name+":"+value);
				
				dataMap.put(name, value);
				
			}else {							// text가 아닌 것들(파일관련) : file
				if(fileItem.getFieldName().equals("file")) {
					if(fileItem.getName()==null || fileItem.getName().equals("")) continue;
					
					upload.setFileSizeMax(1024*1024*10); 	// byte*kb*mb*gb
					String fileName=System.currentTimeMillis()+"_"+fileItem.getName();
					
					//절대경로(안씀)
					String dir="C:\\Kitri2020\\mvc\\workspace\\MyBatisHomepage\\WebContent\\pds";
					File file=new File(dir, fileName);
					
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
		
		
		int delCheck=Integer.parseInt(dataMap.get("fileDelCheck"));
		
//		기존 파일삭제
		BoardDto readBoard=BoardDao.getInstance().select(boardDto.getBoardNumber());
		logger.info(logMsg+"getPath : "+readBoard.getPath());
		if(delCheck==1) {
			if(readBoard.getFileSize()!=0) {
				File file=new File(readBoard.getPath());
				if(file.exists() && file.isFile()) file.delete();
			}
		}
		
		
		
//		boardDto.setWriteDate(new Date()); 수정할때는 날짜 변경 안한다면 주석, 할거면 이부분 주석풀고, BoardDao.update() 부분 수정
		logger.info(logMsg+boardDto.toString());
		
		
		//수정dao 들어가는 부분
		int check=BoardDao.getInstance().update(boardDto, delCheck);
		logger.info(logMsg+"수정결과: "+check);
	
		
		//기존 수정 관련 속성
		request.setAttribute("pageNumber", dataMap.get("pageNumber"));
		request.setAttribute("boardNumber", boardDto.getBoardNumber());
		request.setAttribute("check", check);
		return "/WEB-INF/views/fileBoard/updateOk.jsp";
	}

}
