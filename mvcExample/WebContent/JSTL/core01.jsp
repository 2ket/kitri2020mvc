<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- JSTL (JSP Standard Tag Library) : Java를 태그로 만든것 
		CORE, XML, FMT, SQL, Function
	--%>
	
	<h3>변수 선언</h3>
	<c:set var="su" value="10" scope="page"/>
	<c:set var="imsi" value="${20 }"/>	<%--EL 소스로 데이터값 불러와서 저장 가능 --%>
	
	<h3>${su }/${imsi }</h3>
	
	<c:set var="msg" value="${'Hi' }" scope="page"/>
	 <!-- scope=page(기본값), request, session, application 중 선택 -->
	<c:set var="age" value="23"/>
	
	<div>
		메시지 : ${msg }
		나이 : ${age }
	</div>
	<br><br>

	<jsp:useBean id="member" class="com.java.el.MemberInfo"/>
	<c:set target="${member }" property="name" value="홍길동"/>	<!-- value="${param.name}" -->
	<c:set target="${member }" property="id" value="abc123"/>
	<c:set target="${member }" property="pwd" value="qwer"/>
<%--target은 객체(클래스)개념, property는 속성(필드) 개념 --%>
	
	<h3>${member.name }</h3>
	<h4>${member.id }</h4>
	<h4>${member.pwd }</h4>
	
	<c:set var="id" value="${member.id }" scope="session"/> <!-- 이렇게 scope="session"선언 시 id를 세션내에서 계속 쓸 수 있다. -->
	<h3>${sessionScope.id }, ${id }</h3>
	<br><br>
	
	<h3>변수 제거</h3>
	<c:remove var="age" scope="page"/>
<%-- 	<c:remove var="id"/>	defalut가 page이기 때문에 원래 지우려는 변수가 scope가 session이라면 현재 페이지에서만 지워짐. 그러므로 원래 변수의 scope확인하여 똑같이 지워준다. --%>
	<c:remove var="id" scope="session"/>
	<div>${age }, ${id }</div>
</body>
</html>