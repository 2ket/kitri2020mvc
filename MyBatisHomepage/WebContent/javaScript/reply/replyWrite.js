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
	
	alert(arr.join("\n"));
}
function writeFromServer(){
	
}