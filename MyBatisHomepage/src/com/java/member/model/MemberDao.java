package com.java.member.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.java.database.ConnectionProvider;
import com.java.database.JdbcUtil;
import com.java.myBatis.SqlManager;

public class MemberDao {	// Data Access Object
	private static SqlSessionFactory sqlSessionFactory=SqlManager.getInstance();//초기화
	private SqlSession session;	//쿼리문은 계속 바뀌므로 초기화 하지 않는다.
	
	// Singleton pattern : 단 한개의 객체만을 가지고 구현(설계)
	private static MemberDao instance=new MemberDao();
	public static MemberDao getInstance() {
		return instance;
	}
	
	public int insert(MemberDto memberDto) {

		int check=0;
		
		try {
			session=sqlSessionFactory.openSession();
			
			check=session.insert("member_insert", memberDto);
			session.commit();
		
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return check;
	}
	
	//SELECT문은 함수 return이 다양하다.
	//자료형 하나를 보내주는 경우, 두개를 가져오는경우(배열), 레코드 한행이 다 넘어와야 하는경우(Dto로 받기), 여러개의 행이 넘어와야 하는경우
	
	//아이디 중복확인
	public int idCheck(String id) {
		int value=0;
		
		try {
			session=sqlSessionFactory.openSession();
			String checkId=session.selectOne("id_check", id);
			
			if(checkId!=null) value=1;
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return value;
	}
	
	//우편번호 검색
	public ArrayList<ZipcodeDto> zipcodeReader(String checkDong) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<ZipcodeDto> arrayList=null;
		
		try {
			String sql="select * from zipcode where dong=?";
			conn=ConnectionProvider.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, checkDong);
			rs=pstmt.executeQuery();
			
			arrayList=new ArrayList<ZipcodeDto>();
			while(rs.next()) {
				ZipcodeDto address=new ZipcodeDto();
				address.setZipcode(rs.getString("zipcode"));
				address.setSido(rs.getString("sido"));
				address.setGugun(rs.getString("gugun"));
				address.setDong(rs.getString("dong"));
				address.setRi(rs.getString("ri"));
				address.setBunji(rs.getString("bunji"));
				arrayList.add(address);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return arrayList;
	}

	public String loginCheck(String id, String pw) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String value=null;
		
		try {
			String sql="select member_level from member where id=? and pw=?";
			conn=ConnectionProvider.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs=pstmt.executeQuery();
			if(rs.next()) value=rs.getString("member_level");
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return value;
	}
	public MemberDto updateId(String id) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		MemberDto memberDto=null;
		
		try {
			String sql="select * from member where id=?";
			conn=ConnectionProvider.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				memberDto=new MemberDto();
				memberDto.setNum(rs.getInt("num"));
				memberDto.setId(rs.getString("id"));
				memberDto.setPw(rs.getString("pw"));
				memberDto.setName(rs.getString("name"));
				memberDto.setJumin1(rs.getString("jumin1"));
				memberDto.setJumin2(rs.getString("jumin2"));
				memberDto.setEmail(rs.getString("email"));
				memberDto.setZipCode(rs.getString("zipcode"));
				memberDto.setAddr(rs.getString("addr"));
				memberDto.setJob(rs.getString("job"));
				memberDto.setMailing(rs.getString("mailing"));
				memberDto.setInterest(rs.getString("interest"));
				memberDto.setMemberLevel(rs.getString("member_level"));
				
				//DB와 자바 사이에서 날짜를 주고 받을 때는 시간형태(숫자형태)로 변환해서 주고 받아야 한다.
				//시간은 long형태로 변환하면 날짜나 계산하기 위한 캘린더형식으로 변환하기 좋다.
				/*
				Timestamp ts=rs.getTimestamp("register_date");
				long time=ts.getTime();
				Date date=new Date(time);
				memberDto.setRegisterDate(date);*/
				
				
				memberDto.setRegisterDate(new Date(rs.getTimestamp("register_date").getTime()));
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return memberDto;
	}
	
	public int update(MemberDto memberDto) {
		int value=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		try {
			String sql="update member set pw=?, email=?, zipcode=?, addr=?, job=?, mailing=?, interest=? where num=?";
			conn=ConnectionProvider.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, memberDto.getPw());
			pstmt.setString(2, memberDto.getEmail());
			pstmt.setString(3, memberDto.getZipCode());
			pstmt.setString(4, memberDto.getAddr());
			pstmt.setString(5, memberDto.getJob());
			pstmt.setString(6, memberDto.getMailing());
			pstmt.setString(7, memberDto.getInterest());
			pstmt.setInt(8, memberDto.getNum());
			value=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		return value;
	}
	
	public int delete(String id, String pw) {
		int value=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		try {
			String sql="delete from member where id=? and pw=?";
			conn=ConnectionProvider.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			value=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		return value;
	}
}
