package com.java.member.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
			String checkId=session.selectOne("member_id_check", id);
			
			if(checkId!=null) value=1;
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return value;
	}
	
	//우편번호 검색
	public List<ZipcodeDto> zipcodeReader(String checkDong) {
		
		List<ZipcodeDto> arrayList=null;
		
		try {
			session=sqlSessionFactory.openSession();
			arrayList=session.selectList("member_zipcode", checkDong);
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return arrayList;
	}

	public String loginCheck(String id, String pw) {
		
		String value=null;
		
		try {
			HashMap<String, String> hMap=new HashMap<String, String>();
			hMap.put("id", id);
			hMap.put("pw", pw);
			
			session=sqlSessionFactory.openSession();
			value=session.selectOne("member_login", hMap);
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return value;
	}
	public MemberDto updateId(String id) {
		
		MemberDto memberDto=null;
		
		try {
			session=sqlSessionFactory.openSession();
			memberDto=session.selectOne("member_select", id);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return memberDto;
	}
	
	public int update(MemberDto memberDto) {
		int value=0;
		
		try {
			session=sqlSessionFactory.openSession();
			value=session.update("member_update", memberDto);
			
			session.commit();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return value;
	}
	
	public int delete(String id, String pw) {
		int value=0;
		Map<String, String> hMap=new HashMap<String, String>();
		//Map은 interface, HashMap은 class이므로 부모격인 interface가 최상위 부모객체로 담아내는게 나중에 코드 다루기 좋다.
		
		try {
			session=sqlSessionFactory.openSession();
			hMap.put("id", id);
			hMap.put("pw", pw);
			value=session.delete("member_delete", hMap);
			session.commit();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return value;
	}
}
