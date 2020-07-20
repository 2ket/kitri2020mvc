<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="../jquery-3.5.1.js"></script>
<script type="text/javascript">
	/* var obj=new jQuery(document); 		//선택자 또는 자바스크립트 객체
	obj.ready(testFun);	//body on load와 동일
	
	function testFun(){
		alert("jQuery 시작");
	} */
	
	//위 주석내용 한줄로 표현
/* 	new jQuery(document).ready(function(){
		alert("제이쿼리 시작입니다");
	}); */
	$(document).ready(function(){alert("jQuery 시작입니다.")});	//new 객체생성 등을 $로 표현 셋다 같다.
</script>
</head>
<body>

</body>
</html>