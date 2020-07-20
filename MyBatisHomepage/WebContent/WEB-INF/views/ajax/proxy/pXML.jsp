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
<script type="text/javascript" src="${root }/xhr/xhr.js"></script>
<script type="text/javascript">
	function toServer(root){
		var url=root+"/pXML.do";
		//http://www.kma.go.kr/weather/forecast/mid-term-rss3.jsp?stnId=109
		sendRequest("GET", url, null, fromServer);
	}
	function fromServer(){
		alert(xhr.readyState+","+xhr.status);	//4, 200 넘어와야 쓸수 있음
	}
</script>
</head>
<body>
	<input type="button" value="오늘의 날씨" onclick="toServer('${root}')">
</body>
</html>