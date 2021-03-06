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
<script type="text/javascript" src="${root }/javaScript/reply/replyWrite.js"></script>
<script type="text/javascript" src="${root }/javaScript/reply/replyDelete.js"></script>
<script type="text/javascript" src="${root }/javaScript/reply/replyUpdate.js"></script>
<link type="text/css" rel="stylesheet" href="${root }/CSS/reply/reply.css">
</head>
<body>
	<div>실시간 댓글이 가능합니다.</div>
	<br><br>
	<div>
		<input id="writeReply" type="text" name="write" size="50">
		<input type="button" value="전송" onclick="writeToServer('${root}')">
	</div>
	
	<!-- 뿌려주는 공간 -->
	<div id="listAllDiv">
		<!-- 실시간 댓글(새로운 글) -->
		
		
		
		<!-- 기존 댓글 -->
		
		<c:forEach var="replyDto" items="${replyList }">
			<div class="replyDiv" id="${replyDto.bunho }">
				<span class="cssBunho">${replyDto.bunho }</span>
				<span class="cssIp">${replyDto.user_ip }</span>
				<span class="cssReply">${replyDto.line_reply }</span>
				<span class="cssUpDel">
					<a href="javascript:deleteToServer('${replyDto.bunho }', '${root }')">삭제 &nbsp;</a>
					<a href="javascript:selectToServer('${replyDto.bunho }', '${root }')">수정</a>
				</span>
			</div>
		
		</c:forEach>
	</div>
</body>
</html>