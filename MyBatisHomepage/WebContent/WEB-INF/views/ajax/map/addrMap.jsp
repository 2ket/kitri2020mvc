<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<c:set var="root" value='${pageContext.request.contextPath}' />
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${root }/xhr/xhr.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=510fab40683050bcc2d7e6a16f243d1e"></script>
<script type="text/javascript">
	var arr=new Array();
	function toServer(){
		var addr=document.getElementById("addr").value;
		//arr.push(addr);
		
		var url="https://dapi.kakao.com/v2/local/search/address.json";
		var param="query="+addr;
		
		sendRequest("GET", url, param, fromServer);
	}
	function fromServer(){
		//arr.push(xhr.readyState+","+xhr.status);
		if(xhr.readyState==4 && xhr.status==200){
			//arr.push(xhr.responseText);
			processJson();
		}
		//alert(arr);
	}
	function processJson(){
		var obj=JSON.parse(xhr.responseText);
		var y=obj.documents[0].y;
		var x=obj.documents[0].x;
		//arr.push(x+", "+y);
		alert(arr);
		
		var container = document.getElementById('map');
		var options = {
			center: new kakao.maps.LatLng(y, x),
			level: 3
		};

		var map = new kakao.maps.Map(container, options);
		
	}
</script>
</head>
<body>
	<h3>주소</h3>
	<input type="text" id="addr">
	<input type="button" value="주소검색" onclick="toServer()">
	
	<div id="map" style="width:500px;height:400px;"></div>
</body>
</html>