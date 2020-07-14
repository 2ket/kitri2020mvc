package com.java.fileBoard.model;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;


import com.java.myBatis.SqlManager;

public class BoardDao {
	private static SqlSessionFactory sqlSessionFactory=SqlManager.getInstance();
	private SqlSession session;
	// 싱글톤. BoardDao 계속 부를때마다 생성하지 않고 한번 인스턴스 생성하여,
	// getInstance함수로 그 인스턴스를 전달하여 한번의 객체생성으로 사용할수 있게함
	private static BoardDao instance = new BoardDao();
	public static BoardDao getInstance() {
		return instance;
	}

	public int insert(BoardDto boardDto) {
		int value = 0;
		writeNumber(boardDto);
		
		boardDto.setContent(boardDto.getContent().replace("\r\n", "<br/>"));
		boardDto.setWriteDate(new Timestamp(boardDto.getWriteDate().getTime()));
		
		try {
			
			if(boardDto.getFileSize()==0) {
				
				session=sqlSessionFactory.openSession();
				value=session.insert("fileBoard_insert", boardDto);
				
				/* Date date=boardDto.getWriteDate();
				long time=date.getTime();
				Timestamp ts=new Timestamp(time);
				pstmt.setTimestamp(6, ts); */
				
			}else {
				session=sqlSessionFactory.openSession();
				value=session.insert("fileBoard_insert_file", boardDto);
				
			}
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return value;
	}

	public void writeNumber(BoardDto boardDto) {
		// 그룹번호(ROOT), 글순서(자식), 글 레벨(자식)
		int boardNumber = boardDto.getBoardNumber();
		int groupNumber = boardDto.getGroupNumber();
		int sequenceNumber = boardDto.getSequenceNumber();
		int sequenceLevel = boardDto.getSequenceLevel();

		try {
			if (boardNumber == 0) { // root.
				session=sqlSessionFactory.openSession();
				int max=session.selectOne("fileBoard_maxGroup");
				
				if(max!=0) {
					boardDto.setGroupNumber(max+1);
				}
				
			} else { // 자식글, 답글 : 글순서, 글레벨
				
				Map<String, Integer> map=new HashMap<String, Integer>();
				map.put("groupNumber", groupNumber);
				map.put("sequenceNumber", sequenceNumber);
				
				session=sqlSessionFactory.openSession();
				session.update("fileBoard_update_number", map);
				
				sequenceNumber+=1;
				sequenceLevel+=1;
				
				boardDto.setSequenceNumber(sequenceNumber);
				boardDto.setSequenceLevel(sequenceLevel);
			}
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public int getCount() {
		int value=0;
		
		try {
			session=sqlSessionFactory.openSession();
			value=session.selectOne("fileBoard_getCount");
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return value;
	}

	public List<BoardDto> getBoardList(int startRow, int endRow) {
		List<BoardDto> boardList=null;
		Map<String, Integer> map=new HashMap<String, Integer>();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		
		try {
			session=sqlSessionFactory.openSession();
			boardList=session.selectList("fileBoard_list", map);
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return boardList;
	}

	public BoardDto read(int boardNumber) {
		BoardDto boardDto=new BoardDto();
		
		try {
			//조회수 올리기
			session=sqlSessionFactory.openSession();
			int value=session.update("fileBoard_view", boardNumber);
			
			if(value>0) session.close();
			
			//선택한 게시글 상세 가져오기
			session=sqlSessionFactory.openSession();
			boardDto=session.selectOne("fileBoard_read", boardNumber);
			
			session.commit();
		}catch(Exception e) {
			e.printStackTrace();
			session.rollback();
		}finally {
			session.close();
		}
		return boardDto;
	}

	public int delete(int boardNumber, String password) {
		int value=0;
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("boardNumber", boardNumber);
		map.put("password", password);
		//root글을 삭제할경우 답글이 붕 떠버리기때문에 이에 대한 해결책이 필요함. 혼자서 고민해볼것
		
		try {
			session=sqlSessionFactory.openSession();
			value=session.delete("fileBoard_delete", map);
			session.commit();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return value;
	}

	public int update(BoardDto boardDto, int delCheck) {
		int value=0;
		boardDto.setContent(boardDto.getContent().replace("\r\n", "<br/>"));
		
		try {
			session=sqlSessionFactory.openSession();
			if(delCheck==0) {	//파일 삭제 필요 없을경우
				value=session.update("fileBoard_update", boardDto);
			}else {				//파일 삭제 필요할경우(파일삭제는 command단에서 먼저 실행됬음)
				if(boardDto.getFileSize()==0) {		//새로운 파일은 없는경우
					value=session.update("fileBoard_update_delFile", boardDto );
				}else {								//새 파일 등록해서 수정해야 하는 경우
					value=session.update("fileBoard_update_file", boardDto);
				}
			}
			session.commit();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return value;
	}
	
	public BoardDto select(int boardNumber) {
		
		BoardDto boardDto=new BoardDto();
		
		try {
			session=sqlSessionFactory.openSession();
			boardDto=session.selectOne("fileBoard_read", boardNumber);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return boardDto;
	}
	
}
