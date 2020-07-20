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
<%-- 내가 가지고 있는 DB를 AJAX로 뿌리는법 --%>
	<c:set var="root" value="${pageContext.request.contextPath }"/>
	<h3>사원테이블</h3>
	<a href="${root }/sawon/list.do">사원목록</a>
	<br><br>
	
	<h3>실시간 댓글</h3>
	<a href="${root }/reply/replyList.do">한줄답글</a>
	
	<br><br>
	
	<h3>오늘의 날씨</h3>
	<a href="${root }/parsing.do">오늘의 날씨</a>
	
	<br><br>
	
	<h3>Kakao Map</h3>
	<a href="${root }/map.do">Kakao Map</a>
	
	<br><br>
	
	<h3>Addr Map</h3>
	<a href="${root }/addr.do">Address Map</a>
	
	
</body>
</html>