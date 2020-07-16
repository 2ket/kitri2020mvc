/**
 * http://usejsdoc.org/
 */

var xhr=null;
var arr=new Array();

function toServer(){
	var msg=document.getElementById("createForm").msg.value;
	//arr.push(msg);
	
	if(window.XMLHttpRequest){
		xhr=new XMLHttpRequest;		//객체선언. 함수선언이 아님
	}else{
		xhr=new ActiveXObject("Microsoft.XMLHTTP");
	}
	
	//GET방식 먼저 진행 후 POST방식 진행할것
	//GET방식
	//xhr.open("GET", "command.jsp?msg="+msg, true);		//요청방식, 서버파일, 비동기식(정적페이지)
	//xhr.send();
	
	//POST방식
	//setRequestHeader()	Adds a label/value pair to the header to be sent
	//post는 헤더로 데이터 주고받는데, 이부분을 처리해주는 작업을 해줘야함
	xhr.open("POST", "command.jsp", true);
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.send("msg="+msg);
	xhr.onreadystatechange=process;
}
function process(){
	/*
	readyState	Holds the status of the XMLHttpRequest.
		0: request not initialized
		1: server connection established
		2: request received
		3: processing request
		4: request finished and response is ready
	
	status	
		200: "OK"
		403: "Forbidden"
		404: "Page not found"
	For a complete list go to the Http Messages Reference*/
	
	if(xhr.readyState==4 && xhr.status==200){
		arr.push("aaa"+xhr.responseText);
		var disp=document.getElementById("disp");
		disp.innerText=xhr.responseText;
		
	}
	
	//alert(arr.join("\n"));	
}