<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%-- <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	fmt : format tag --%>

	<fmt:requestEncoding value="utf-8"/>
	<h3>${param.msg }</h3>
	
	<c:forEach var="fruit" items="${paramValues.fruit }">
		${fruit }
	</c:forEach>
	<br><br>
	
	<h3>숫자를 문자열 변환</h3>
	number:
		<fmt:formatNumber value="12345.678" type="number"/>
		
	currency:
		<fmt:formatNumber value="123456.78" type="currency"/>
		<fmt:formatNumber value="123456.647" type="currency" currencySymbol="$"/>
		
	percent:
		<fmt:formatNumber value="123456.674" type="percent"/>
		
	pattern:
		<fmt:formatNumber value="123456.648" pattern=".0000"/>	<%-- #, 0 --%>
	<br>
	
	<h3>문자열을 숫자 변환</h3>
	<c:set var="str" value="${'1000' }"/>
	<fmt:parseNumber var="pNum" value="${str }"/>
	${pNum+4 }
	<fmt:parseNumber var="isu" value="55.7" integerOnly="true"/>
	${isu }
	
	<h3>날짜를 문자열 변환</h3>
	<jsp:useBean id="date" class="java.util.Date"/>
	
	날짜: <fmt:formatDate value="${date }" type="date"/><br>
	시간: <fmt:formatDate value="${date }" type="time"/><br>
	날짜시간: <fmt:formatDate value="${date }" type="both"/><br><br>
	
	날짜시간: <fmt:formatDate value="${date }" type="both" dateStyle="short" timeStyle="short"/><br>
	날짜시간: <fmt:formatDate value="${date }" type="both" dateStyle="long" timeStyle="long"/><br>
	
	패턴:	<fmt:formatDate value="${date }" pattern="yyyy-MM-dd HH:mm:ss"/> <br>
	
	<h3>문자열을 날짜 변환</h3>
	<c:set var="nowdate" value="2019-12-25 12:00:21"/>
	<fmt:parseDate var="now" value="${nowdate }" pattern="yyyy-MM-dd HH:mm:ss"/>
	<%-- <fmt:formatDate value="${now }" pattern="date"/> --%>
</body>
</html>