package com.java.board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.java.database.ConnectionProvider;
import com.java.database.JdbcUtil;
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

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = "";

	public int insert(BoardDto boardDto) {
		int value = 0;
		writeNumber(boardDto);

		try {
			session=sqlSessionFactory.openSession();
			value=session.insert("board_insert", boardDto);
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
		
		Map<String, Integer> hMap = new HashMap<String, Integer>();
		hMap.put("groupNumber", groupNumber);
		hMap.put("sequenceNumber", sequenceNumber);
		try {
			if (boardNumber == 0) { // root.
				
				session=sqlSessionFactory.openSession();
				int max = session.selectOne("board_group_number_max");
				
				if(max!=0) {
					boardDto.setGroupNumber(max+1);
				}
				
			} else { // 자식글, 답글 : 글순서, 글레벨
				session=sqlSessionFactory.openSession();
				session.update("board_reNumber", hMap);
				session.commit();

				sequenceNumber+=1;
				sequenceLevel+=1;
				
				boardDto.setSequenceNumber(sequenceNumber);
				boardDto.setSequenceLevel(sequenceLevel);
			}
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
			value=session.selectOne("board_count");
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return value;
	}

	public List<BoardDto> getBoardList(int startRow, int endRow) {
		HashMap<String, Integer> hMap=new HashMap<String, Integer>();
		hMap.put("startRow", startRow);
		hMap.put("endRow", endRow);
		List<BoardDto> boardList=null;
		
		try {
			sql="select b.* From (select rownum rnum, a.* From (select * from board order by group_number desc, sequence_number asc) a) b where b.rnum>=? and b.rnum<=?";
			session=sqlSessionFactory.openSession();
			
			boardList=session.selectList("board_list", hMap);
			
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
			session=sqlSessionFactory.openSession();
			session.update("board_view", boardNumber);
			boardDto=session.selectOne("board_read", boardNumber);
			session.commit();
		}catch(Exception e) {
			session.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return boardDto;
	}

	public int delete(int boardNumber, String password) {
		int value=0;
		HashMap<String, Object> hMap=new HashMap<String, Object>();
		hMap.put("boardNumber", boardNumber);
		hMap.put("password", password);
		
		//root글을 삭제할경우 답글이 붕 떠버리기때문에 이에 대한 해결책이 필요함. 혼자서 고민해볼것
		try {
			session=sqlSessionFactory.openSession();
			value=session.delete("board_delete", hMap);
			session.commit();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return value;
	}

	public int update(BoardDto boardDto) {
		int value=0;
		
		try {
			session=sqlSessionFactory.openSession();
			value=session.update("board_update", boardDto);
			session.commit();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return value;
	}
}
