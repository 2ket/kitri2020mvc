<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<c:set var="root" value="${pageContext.request.contextPath }"/> 
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="top.jsp"/>
	<jsp:include page="left.jsp"/>
	<!-- 가장 나중에 고쳐준다 -->
	<c:if test="${viewPage==null }">
		<div align="center">
			<h2>Welcome Homepage</h2>
		</div>
	</c:if>
	
	<c:if test="${viewPage!=null }">
		<jsp:include page="${viewPage }"/>
	</c:if>
	
	<jsp:include page="bottom.jsp"/>
</body>
</html>