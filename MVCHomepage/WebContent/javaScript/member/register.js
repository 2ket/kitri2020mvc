/**
 * 
 */

function createForm(obj){
	var str="";
	for(var i=0; i<obj.interest.length; i++){
		if(obj.interest[i].checked==true){
			str += obj.interest[i].value + ",";
			
		}
	}
//	alert(str);
	obj.resultInterest.value=str;
	// Spring에서는 JSP파일에서의 name과 Dto.java 파일내 필드명과 DB 테이블의 필드명 세개가 전부 동일해야 값을 주고받을 수 있다.
}

function idCheck(obj, root){
//	alert(obj.id.value);
	
	if(obj.id.value==""){
		alert("아이디를 반드시 검색하세요.");
		obj.id.value.focus();
		return false;
	}
	
	var url=root+"/member/idCheck.do?id="+obj.id.value;
	alert(url);

}