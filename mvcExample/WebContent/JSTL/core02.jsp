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
	<h3>제어문 - if</h3>

	<c:set var="fruit" value="apple"/>
	
	<c:if test="${fruit=='apple' }">
		<h3>${fruit }</h3>
	</c:if>
	
	<c:if test="${fruit!='apple' }">
		<h3>${fruit }는 apple 아님</h3>
	</c:if>
<%--c:if는 else가 없음 --%>

<c:if test="${fruit=='apple' }">
	<c:out value="${fruit }"/>	<%-- 출력함수 c:out --%>
</c:if>

<c:out value="하하하1"/>

${"하하하2" }


	<h3>제어문 - choose / when</h3>
	<c:choose>
		<c:when test="${fruit=='apple' }">
			${fruit }
		</c:when>
		<c:when test="${fruit=='banana' }">
			<c:out value="${fruit }"/>
		</c:when>
		<c:when test="${fruit=='orange' }">
			<c:out value="${fruit }"/>
		</c:when>
		
		<c:otherwise>기타등등</c:otherwise>
	</c:choose>


</body>
</html>