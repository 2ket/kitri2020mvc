<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<c:set var="root" value="${pageContext.request.contextPath }"/>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${root }/javaScript/board/board.js"></script>
<link type="text/css" rel="stylesheet" href="${root }/CSS/board/boardStyle.css">
</head>
<body>
	<div id="board"><!-- 전체 폼 -->
		<form action="${root }/board/writeOk.do" method="post" onsubmit="return boardCheck(this)">
			<input type="hidden" name="boardNumber" value="${boardNumber }">
			<input type="hidden" name="groupNumber" value="${groupNumber }">
			<input type="hidden" name="sequenceNumber" value="${sequenceNumber }">
			<input type="hidden" name="sequenceLevel" value="${sequenceLevel }">
			<div style="text-align:right;">
				<span><a href="#">글목록</a></span>
			</div>
			
			<div><!-- 테두리용 -->
				<div>
					<label>작성자</label>
					<input type="text" name="writer"/>
				</div>
				<div>
					<label>제목</label>
					<input type="text" style="width:400px;" name="subject">
				</div>
				<div>
					<label>이메일</label>
					<input type="text" style="width:400px;" name="email">
				</div>
				<div id="contents">
					<label>내용</label>
					<textarea rows="" cols="" name="content"></textarea>
				</div>
				<div>
					<label>비밀번호</label>
					<input type="password" name="password">
				</div>
				<div style="text-align:center;"><!-- 버튼부분 -->
					<input type="submit" value="글쓰기">
					<input type="reset" value="다시작성">
					<input type="button" value="목록보기">
				</div>
			</div><!-- //테두리용 -->
		</form>
	</div>
</body>
</html>