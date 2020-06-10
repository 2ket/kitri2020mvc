<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%-- 다른 jsp에서 Form action="현재 jsp"으로 넘겨준 데이터들은 param(객체)으로 받을 수 있다.
		java servlet으로 치면 request.getParameter()--%>
	<!--EL 내장객체 - HTTP 요청 파라미터 paramValues-->
	<h3>이름 : ${param.name }</h3>
	<h3>아이디 : ${param.id }</h3>
	<h3>비밀번호 : ${param.pwd }</h3>
	<br><br>
	
	<ul>
	<!--EL 내장객체 (Scope 읽기 -->
		<li>${pageScope.pName }</li>	<%-- 결과 안나옴 --%>
		<li>${requestScope.rName }</li>	<%-- 결과 안나옴 --%>
		<li>${sessionScope.sName }</li>
		<li>${applicationScope.aName }</li>
	</ul>
</body>
</html>