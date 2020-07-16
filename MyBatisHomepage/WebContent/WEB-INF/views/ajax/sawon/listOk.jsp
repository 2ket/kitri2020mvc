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
	<table border="1">
		<tr>
			<td align="center" bgcolor="#DEDEDE" width="100">사번</td>
			<td align="center" bgcolor="#DEDEDE" width="100">이름</td>
			<td align="center" bgcolor="#DEDEDE" width="100">입사년도</td>
			<td align="center" bgcolor="#DEDEDE" width="100">직군</td>
			<td align="center" bgcolor="#DEDEDE" width="100">부서번호</td>
			<td align="center" bgcolor="#DEDEDE" width="100">부서명</td>
		</tr>
	<c:forEach var="sawonDto" items="${sawonList }">
		<tr>
			<td align="center" width="100">${sawonDto.employee_id }</td>
			<td align="center" width="100">${sawonDto.first_name }</td>
			<td align="center" width="100">
					<fmt:formatDate value="${sawonDto.hire_date }" pattern="yyyy-MM-dd"/>
				</td>
			<td align="center" width="100">${sawonDto.job_id }</td>
			<td align="center" width="100">${sawonDto.department_id }</td>
			<td align="center" width="100">${sawonDto.department_name }</td>
		</tr>
	</c:forEach>
	</table>
</body>
</html>