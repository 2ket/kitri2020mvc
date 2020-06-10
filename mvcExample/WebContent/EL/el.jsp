<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div> JAVA 명령어는 JSTL, EL 사용해야 한다. </div>
	<!-- HTML 주석 -->
	<%-- JSP 주석 --%>
	<%-- 차이점이 뭐냐면 JSP주석은 소스이기 때문에 front 단에서 개발자도구 등으로 볼 수 없다. --%>
	<%--C:\Kitri2020\mvc\apache-tomcat-9.0.35\work\Catalina\localhost\mvcExample\org\apache\jsp\EL
		JSP 생성후 컴파일 시 해당 장소에 자동으로 servlet 파일로 .java, .class 파일이 생성되어있다. --%>
		
	<%-- 데이터, 연산자 --%>
	<h3>EL - 연산자, 직접 데이터 사용가능, 변수(JSTL)</h3>
	<ol>
		<li>수 출력 : ${10 }, ${99.99 }</li>
		<li>문자(문자열) 출력 : ${"apple" }, ${'apple' }</li>
		<li>연산 ${10+20 }, ${10-20 }, ${4/5 }, ${5%7} </li>
		<li>작다 : ${2<3 }, ${2 lt 3}</li>
		<li>크다 : ${3>2 }, ${3 gt 3 }</li>
		<li>작거나 같다 : ${2.5<=3.7 }, ${2 le 3}</li>
		<li>크거나 같다 : ${2.5>=3.7 }, ${2 ge 3}</li>
		<li>논리연산자 : ${" &&, AND, ||, OR, !, NOT  " }</li>
		<li>빈문자열 ${null }</li><%--java에서 null 출력하면 NullExeption 오류 뜨는데 JSP에선 예외처리가 되서 빈칸으로 출력된다. --%>
		
	</ol>
</body>
</html>