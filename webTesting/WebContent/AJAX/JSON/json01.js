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
 	xhr.open("GET", "json01.txt", true);
 	xhr.send();
 	xhr.onreadystatechange=resultProcess;
 }
 function resultProcess(){
 	if(xhr.readyState==4 && xhr.status==200){
 		arr.push(xhr.responseText);
 		//alert(arr.join("\n"));
 		
 		var obj=JSON.parse(xhr.responseText);
 		
 		var disp=document.getElementById("disp");
 		var ul=document.createElement("ul");
 		var liNode1=document.createElement("li");
 		var liNode2=document.createElement("li");
 		var liNode3=document.createElement("li");
 		
 		liNode1.innerHTML=obj.name;
 		liNode2.innerHTML=obj.age;
 		liNode3.innerHTML=obj.ki;
 		
 		ul.appendChild(liNode1);
 		ul.appendChild(liNode2);
 		ul.appendChild(liNode3);
 		disp.appendChild(ul);
 	}
 }