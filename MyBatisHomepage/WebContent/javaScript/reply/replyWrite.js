/**
 * http://usejsdoc.org/
 */

var arr=new Array();
function writeToServer(root){
	var writeReply=document.getElementById("writeReply").value;
	
	arr.push(root+", "+writeReply);
	
	var url=root+"/reply/replyWrite.do";
	var param="writeReply="+writeReply;
	sendRequest("POST", url, param, writeFromServer);
	
}
function writeFromServer(){
	if(xhr.readyState==4 && xhr.status==200){
		arr.push("마지막출력 : "+xhr.responseText);
		
		var str=xhr.responseText;
		var split=str.split(",");
		
		var bunho=split[0];
		var msg=split[1];
		
		var disp=document.getElementById("disp");
		var spanBunho=document.createElement("span");
		spanBunho.innerHTML=bunho;
		var spanMsg=document.createElement("span");
		spanMsg.innerHTML=msg;
		var br=document.createElement("br");
		
		disp.appendChild(spanBunho);
		disp.appendChild(spanMsg);
		disp.appendChild(br);
		alert(arr.join("\n"));
	}
}