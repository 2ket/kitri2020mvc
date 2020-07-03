/**
 * 
 */
//interest 여러개 찍은거 한줄 String으로 만들어서 데이터 보내주기
function createForm(obj){

//미입력시 뜨는 문구
	//아이디 미입력시 출력문구
	if(obj.id.value==""){
		alert("아이디를 반드시 입력하세요.");
		obj.id.focus();
		return false;
	}
	//비밀번호 미입력시
	if(obj.pw.value==""){
		alert("비밀번호를 반드시 입력하세요.");
		obj.pw.focus();
		return false;
	}
	//이름 미입력시
	if(obj.name.value=""){
		alert("이름을 반드시 입력하세요.");
		obj.name.focus();
		return false;
	}
	//주민번호 미입력시
	if(obj.jumin1.value=""){
		alert("주민번호를 입력하세요");
		obj.jumin1.focus();
		return false;
	}
	if(obj.jumin2.value=""){
		alert("주민번호를 입력하세요");
		obj.jumin2.focus();
		return false;
	}
	//이름 미입력시
	if(obj.name.value=""){
		alert("이름을 반드시 입력하세요.");
		obj.name.focus();
		return false;
	}
//그 외 유효성 검사
	//비밀번호 확인 틀릴시
	if(obj.pw.value!=obj.pwCheck.value){
		alert("비밀번호를 확인하세요.");
		obj.pw.focus();
		return false;
	}
	//비밀번호 7글자 이상
	if(obj.pw.value.length<7){
		alert("비밀번호는 7자 이상이어야 합니다..");
		obj.pw.focus();
		return false;
	}
	
	
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

//아이디 중복체크 팝업으로 뜨게
function idCheck(obj, root){	
	var url=root+"/member/idCheck.do?id="+obj.id.value;

	window.open(url,"","width=400, height=300");
}

//우편번호 검색 버튼 누르면 팝업뜨게
function zipcodeReader(root){
	var url=root+"/member/zipcode.do";
	window.open(url, "", "width=400, height=500, scrollbars=yes");
}

//우편번호 클릭시 일어나는 기능
function sendAddress(zipcode, sido, gugun, dong, ri, bunji){
	var address=sido+gugun+dong+ri+bunji;
	opener.createForm1.zipcode.value=zipcode;
	opener.createForm1.addr.value=address;
}