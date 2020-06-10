<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>EL - 2. setMethod, getMethod</h3>
	<jsp:useBean id="member" class="com.java.el.MemberInfo"/>
	<%-- setMethod --%>
	${member.setName("이영자") }
	${member.setId("lee1234") }
	${member.setPwd("1234") }
	
	
	<h3>${member.toString() }</h3>
	
	<%-- getMethod / 거의 사용안함 --%>
	<h3>${member.getName() }</h3>
	<h3>${member.getId() }</h3>
	<h3>${member.getPwd() }</h3>
	<br><br>
	
	<!--  -->
	<h3>${member.name }</h3> <%--getMethod 호출	/섞인 코드에는 jsp 주석으로..! --%>
	<h3>${member.id }</h3>
	<h3>${member.pwd }</h3>
</body>
</html>