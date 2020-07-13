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
<script type="text/javascript">
	function pwCheckFunc(obj){
		if(obj.pwCheck.value!=obj.password.value){
			alert("패스워드가 일치하지 않습니다.");
			return false;
		}
	}
</script>
</head>
<body>
	<div id="board"><!-- 전체 폼 -->
		<form action="${root }/board/updateOk.do" method="post" onsubmit="return pwCheckFunc(this)">
			<input type="hidden" name="pageNumber" value="${pageNumber }">
			<input type="hidden" name="boardNumber" value="${boardNumber }">
			<input type="hidden" name="password" value="${boardDto.password }">
			<div style="text-align:right;">
				<span><a href="${root }/board/list.do?pageNumber=${pageNumber}">글목록</a></span>
			</div>
			
			<div><!-- 테두리용 -->
				<div>
					<label>작성자</label>
					<input type="text" name="writer" value="${boardDto.writer }" disabled="disabled"/>
				</div>
				<div>
					<label>제목</label>
					<input type="text" style="width:400px;" name="subject" value="${boardDto.subject }">
				</div>
				<div>
					<label>이메일</label>
					<input type="text" style="width:400px;" name="email" value="${boardDto.email }">
				</div>
				<div id="contents">
					<label>내용</label>
					<textarea rows="" cols="" name="content">${boardDto.content }</textarea>
				</div>
				<div>
					<label>비밀번호</label>
					<input type="password" name="pwCheck">
				</div>
				<div style="text-align:center;"><!-- 버튼부분 -->
					<input type="submit" value="수정하기">
					<input type="reset" value="다시작성">
					<input type="button" value="목록보기" onclick="location.href='${root}/board/list.do'">
				</div>
			</div><!-- //테두리용 -->
		</form>
	</div>
</body>
</html>