<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="objFormView.jsp" method="post">
		<label>이름</label>
		<input type="text" name="name">
		<br><br>
		<label>아이디</label>
		<input type="text" name="id">
		<br><br>
		<label>비밀번호</label>
		<input type="text" name="pwd">
		<br><br>
		
		<input type="submit" value="전송">
		<input type="reset" value="취소">
	</form>
	
	<%	// 자바코드 작성 가능 (요새 거의 안쓰긴함)
		java.util.Date date=new java.util.Date();
	
	//내장 객체로 지원
	
		//pageScope : 현재 페이지에서만 데이터 공유
			pageContext.setAttribute("pName", "apple");	
		//requestScope : 요청시 데이터 공유(form, include, forward)
			request.setAttribute("rName", "banana");		
		//sessionScope : 하나의 웹어플리케이션에서 데이터공유(Cookies, Session), 웹브라우저 1개 당,
			session.setAttribute("sName", "melon");
		//applicationScope : 하나의 웹어플리케이션에서 데이터 공유, 서버가 꺼질때 까지 공유
			application.setAttribute("aName", "strawberry");
		
	%>
</body>
</html>