package com.java.board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import com.java.database.ConnectionProvider;
import com.java.database.JdbcUtil;

public class BoardDao {
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
		writeNumber(boardDto, conn);

		try {
			sql = "insert into board values(board_num_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, boardDto.getWriter());
			pstmt.setString(2, boardDto.getSubject());
			pstmt.setString(3, boardDto.getEmail());
			pstmt.setString(4, boardDto.getContent());
			pstmt.setString(5, boardDto.getPassword());
			
			/* Date date=boardDto.getWriteDate();
			long time=date.getTime();
			Timestamp ts=new Timestamp(time);
			pstmt.setTimestamp(6, ts); */
			
			pstmt.setTimestamp(6, new Timestamp(boardDto.getWriteDate().getTime()));
			
			pstmt.setInt(7, boardDto.getReadCount());
			pstmt.setInt(8, boardDto.getGroupNumber());
			pstmt.setInt(9, boardDto.getSequenceNumber());
			pstmt.setInt(10, boardDto.getSequenceLevel());

			value=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}

		return value;
	}

	public void writeNumber(BoardDto boardDto, Connection conn) {
		// 그룹번호(ROOT), 글순서(자식), 글 레벨(자식)
		int boardNumber = boardDto.getBoardNumber();
		int groupNumber = boardDto.getGroupNumber();
		int sequenceNumber = boardDto.getSequenceNumber();
		int sequenceLevel = boardDto.getSequenceLevel();

		try {
			if (boardNumber == 0) { // root.
				sql = "select max(group_number) as max_group_num from board";
				conn = ConnectionProvider.getConnection();
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					int max=rs.getInt(1);
					boardDto.setGroupNumber(max+1);
				}
				
			} else { // 자식글, 답글 : 글순서, 글레벨
				sql="update board set sequence_number=sequence_number+1 where group_number=? and sequence_number > ?";
				
				conn=ConnectionProvider.getConnection();
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, groupNumber);
				pstmt.setInt(2, sequenceNumber);
				pstmt.executeUpdate();
				
				sequenceNumber+=1;
				sequenceLevel+=1;
				
				boardDto.setSequenceNumber(sequenceNumber);
				boardDto.setSequenceLevel(sequenceLevel);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
	}
	
	public int getCount() {
		int value=0;
		
		try {
			sql="select count(*) from board";
			conn=ConnectionProvider.getConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				value=rs.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		return value;
	}

	public ArrayList<BoardDto> getBoardList(int startRow, int endRow) {
		ArrayList<BoardDto> boardList=null;
		
		try {
			sql="select b.* From "
					+ "(select rownum rnum, a.* From "
						+ "(select * from board order by group_number desc, sequence_number asc) a) b "
				+ "where b.rnum>=? and b.rnum<=?";
			conn=ConnectionProvider.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs=pstmt.executeQuery();
			
			boardList=new ArrayList<BoardDto>();
			while(rs.next()) {
				BoardDto boardDto= new BoardDto();
				boardDto.setBoardNumber(rs.getInt("board_number"));
				boardDto.setWriter(rs.getString("writer"));
				if(rs.getInt("sequence_level")==0) {
					boardDto.setSubject(rs.getString("subject"));
				}else if(rs.getInt("sequence_level")==1) {
					boardDto.setSubject("[답글]"+rs.getString("subject"));
				}else if(rs.getInt("sequence_level")==2) {
					boardDto.setSubject("[답글][답글]"+rs.getString("subject"));
				}else{
					boardDto.setSubject("[답글][답글][답글]"+rs.getString("subject"));
				}
				boardDto.setEmail(rs.getString("email"));
				boardDto.setContent(rs.getString("content"));
				
				boardDto.setPassword(rs.getString("password"));
				boardDto.setWriteDate(new Date(rs.getTimestamp("write_date").getTime()));
				//memberDao에서도 날짜->시간 했던 것처럼 하나로 합쳐줌
				boardDto.setReadCount(rs.getInt("read_Count"));
				boardDto.setGroupNumber(rs.getInt("group_number"));
				boardDto.setSequenceNumber(rs.getInt("sequence_number"));
				boardDto.setSequenceLevel(rs.getInt("sequence_level"));
				
				System.out.println(boardDto); //logger관련해서 프론트컨트롤러에서 static으로 안해놔서 그냥 프린트로 찍어봄
				
				boardList.add(boardDto);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return boardList;
	}

	public BoardDto read(int boardNumber) {
		String sqlUpdate=null;
		BoardDto boardDto=new BoardDto();
		
		try {
			conn=ConnectionProvider.getConnection();
			//DML query문이 2개이상일 경우 autoCommit 해제해서 둘 중 하나가 실패하면 둘 다 실행 안되게 해야한다.
			conn.setAutoCommit(false);
			sqlUpdate="update board set read_count=read_count+1 where board_number=?";
			pstmt=conn.prepareStatement(sqlUpdate);
			pstmt.setInt(1, boardNumber);
			int value=pstmt.executeUpdate();
			if(value>0) JdbcUtil.close(pstmt);
			
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
			}
			//여기까지 오류가 없었다면 Commit한다
			conn.commit();
		}catch(SQLException e) {
			e.printStackTrace();
			JdbcUtil.rollBack(conn);
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return boardDto;
	}
}
