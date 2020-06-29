<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>url-pattern : 디렉토리 패턴방식</h3>
	<a href="http://localhost:8181/mvcExample/com/java/mvc01/Directory">Directory</a><br> <!-- class 경로와 같게. 보안에 취약 -->
	<a href="http://localhost:8181/mvcExample/aa/bb/cc/ABC">Directory</a><br> <!-- class 경로와 무관한 url패턴으로 지정. 위보다 보안강화 -->
	<a href="http://localhost:8181/mvcExample/xyz">Directory</a><br>
	
	
	<h3>url-pattern : 확장자 패턴 방식</h3>
	<a href="http://localhost:8181/mvcExample/xyz.nhn">Directory</a><br>	<!-- 확장자로 처리하여 해당 확장자로 끝나는 url패턴은 전부 넘어가게 지정. 서블릿 선언 줄일 수 있음(서블릿은 프로젝트당 딱 하나만 나와야한다, 많아야 두개) -->
	<a href="http://localhost:8181/mvcExample/abc.nhn">Directory</a><br>
	<a href="http://localhost:8181/mvcExample/kkk/xxx/yyy/ijk.nhn">Directory</a><br>
</body>
</html>