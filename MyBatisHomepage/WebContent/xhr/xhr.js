/**
 * http://usejsdoc.org/
 */

function createXHR(){
	if(window.XMLHttpRequest){
		return new XMLHttpRequest;
	}else{
		return new ActiveXObject("Microsoft.XMLHTTP");
	}
}

var xhr=null;

function sendRequest(method, url, param, callback){	//넘어온 값 : post, xhr.txt, msg, fromServer()
	var httpMethod=method.toUpperCase();
	var httpUrl=url;
	var httpParam=(param==null || param=="") ? null : param
	if(httpMethod=="GET" && httpParam != null){
		httpUrl +="?"+httpParam;
	}
	
	arr.push(httpMethod+", "+httpUrl+", "+httpParam);
	xhr=createXHR();
	xhr.open(httpMethod, httpUrl, true);
	//xhr.setRequestHeader~는 POST일때만 적용되고 GET이면 점프한다
	xhr.setRequestHeader("content-type", "application/x-www-form-urlencoded");
	xhr.send(httpMethod=="POST" ? httpParam : null);
	xhr.onreadystatechange=callback;
	
}