/**
 * 
 */
 
 var xhr=null;
 var arr=new Array();
 
 function toServer(){
 	if(window.XMLHttpRequest){
 		xhr=new XMLHttpRequest;
 	}else{
 		xhr=new ActiveXObject("Microsoft.XMLHTTP");
 	}
 	//arr.push(xhr);
 	//alert(arr);
 	
 	xhr.open("GET", "json02.txt", true);
 	xhr.send();
 	xhr.onreadystatechange=resultProcess;
 }
 function resultProcess(){
 	//arr.push(xhr.responseText);
 	
 	var obj=JSON.parse(xhr.responseText);
 	arr.push(obj.name+", "+obj.age+", "+obj.cars.length+", "+obj.cars[0].name+", "+obj.cars[0].length);
 	alert(arr.join("\n"));
 }
 