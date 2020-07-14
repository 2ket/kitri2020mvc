package com.java.fileBoard.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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
		
		try {
			
			if(delCheck==0) {	//파일 삭제 필요 없을경우
				session=sqlSessionFactory.openSession();
				session.update("fileBoard_update", boardDto);
				session.commit();
			}else {				//파일 삭제 필요할경우(파일삭제는 command단에서 먼저 실행됬음)
				if(boardDto.getFileSize()==0) {		//새로운 파일은 없는경우
					sql="update board set email=?, subject=?, content=?, file_name='', path='', file_size=0 where board_number=?";
					conn=ConnectionProvider.getConnection();
					pstmt=conn.prepareStatement(sql);
					pstmt.setString(1, boardDto.getEmail());
					pstmt.setString(2, boardDto.getSubject());
					pstmt.setString(3, boardDto.getContent().replace("\r\n", "<br/>"));
					pstmt.setInt(4, boardDto.getBoardNumber());
					
					value=pstmt.executeUpdate();
				}else {								//새 파일 등록해서 수정해야 하는 경우
					sql="update board set email=?, subject=?, content=?, file_name=?, path=?, file_size=? where board_number=?";
					conn=ConnectionProvider.getConnection();
					pstmt=conn.prepareStatement(sql);
					pstmt.setString(1, boardDto.getEmail());
					pstmt.setString(2, boardDto.getSubject());
					pstmt.setString(3, boardDto.getContent().replace("\r\n", "<br/>"));
					pstmt.setString(4, boardDto.getFileName());
					pstmt.setString(5, boardDto.getPath());
					pstmt.setLong(6, boardDto.getFileSize());
					pstmt.setInt(7, boardDto.getBoardNumber());
					
					value=pstmt.executeUpdate();
				}
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		
		return value;
	}
	
	public BoardDto select(int boardNumber) {
		
		BoardDto boardDto=new BoardDto();
		
		try {
			conn=ConnectionProvider.getConnection();
			
			sql="select * from board where board_number=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, boardNumber);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				boardDto.setBoardNumber(boardNumber);
				boardDto.setWriter(rs.getString("writer"));
				boardDto.setSubject(rs.getString("subject"));
				boardDto.setEmail(rs.getString("email"));
				boardDto.setContent(rs.getString("content"));
				boardDto.setPassword(rs.getString("password"));
				
				boardDto.setWriteDate(new Date(rs.getTimestamp("write_date").getTime()));
				boardDto.setReadCount(rs.getInt("read_count"));
				boardDto.setGroupNumber(rs.getInt("group_number"));
				boardDto.setSequenceNumber(rs.getInt("sequence_number"));
				boardDto.setSequenceLevel(rs.getInt("sequence_level"));
				
				boardDto.setFileName(rs.getString("file_name"));
				boardDto.setPath(rs.getString("path"));
				boardDto.setFileSize(rs.getLong("file_size"));
				
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return boardDto;
	}
	
}
