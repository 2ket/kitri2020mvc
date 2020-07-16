<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<c:set var="root" value="${pageContext.request.contextPath }"/>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>실시간 댓글이 가능합니다.</div>
	<br><br>
	<div>
		<input id="writeReply" type="text" name="write" size="50">
		<input type="button" value="전송" onclick="">
	</div>
	
	<!-- 뿌려주는 공간 -->
	<div>
		<!-- 실시간 댓글(새로운 글) -->
		
		
		
		<!-- 기존 댓글 -->
	</div>
</body>
</html>