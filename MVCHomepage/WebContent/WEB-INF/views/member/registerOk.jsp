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
	<c:set var="root" value="${pageContext.request.contextPath }"/>
	${root }
	<c:if test="${check>0 }">
		<script type="text/javascript">
			alert("회원가입이 완료되었습니다.");
			 location.href="${root}/member/register.do";
		</script>
		<h4>회원가입 완료</h4>
	</c:if>
	
	<c:if test="${check==0 }">
		<script type="text/javascript">
			alert("회원가입이 되지 않습니다.");
			location.href="${root}/member/register.do";
			
		</script>
		<h4>회원가입 실패</h4>
	</c:if>
</body>
</html>