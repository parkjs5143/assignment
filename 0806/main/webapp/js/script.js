function sendit(){
    // alert('sendit() 호출!');
    const expIdCheck = RegExp(/^[a-zA-Z0-9]{4,20}$/);
    const expPwCheck = RegExp(/^[a-zA-Z0-9]{4,20}$/);
    const expNameCheck = RegExp(/^[가-힣]+$/);
    const expHpCheck = RegExp(/^\d{3}-\d{3,4}-\d{4}$/);
    const expEmailCheck = RegExp(/^[a-zA-Z0-9\.\-]+@[a-zA-Z0-9\.\-]+\.[a-zA-Z0-9]+$/);

    if(!expIdCheck.test($('#userid').val())){
        alert('아이디를 형식에 맞게 입력하세요');
        $('#userid').val('');
        $('#userid').focus();
        return false;
    }
    if($('#isidcheck').val() == 'n'){
    	alert('아이디 중복체크를 확인하세요');
    	$('#userid').val('');
    	$('#userid').focus();
    	return false;
    }
    if(!expPwCheck.test($('#userpw').val())){
        alert('비밀번호를 형식에 맞게 입력하세요');
        $('#userpw').val('');
        $('#userpw').focus();
        return false;
    }
    if($('#userpw').val() != $('#userpw_re').val()){
        alert('비밀번호가 서로 다릅니다');
        $('#userpw').val('');
        $('#userpw_re').val('');
        $('#userpw').focus();
        return false;
    }
    if(!expNameCheck.test($('#name').val())){
        alert('이름은 한글로 입력하세요');
        $('#name').val('');
        $('#name').focus();
        return false;
    }
    if(!expHpCheck.test($('#hp').val())){
        alert('휴대폰 형식에 맞게 입력하세요(-포함)');
        $('#hp').val('');
        $('#hp').focus();
        return false;
    }
    if(!expEmailCheck.test($('#email').val())){
        alert('이메일을 형식에 맞게 입력하세요');
        $('#email').val('');
        $('#email').focus();
        return false;
    }

    let isHobbyCheck = false;
    for(let i=0; i<$("[name='hobby']").length; i++){
        if($("input:checkbox[name='hobby']").eq(i).is(':checked') == true){
            isHobbyCheck = true;
            break;
        }
    }
    if(!isHobbyCheck){
        alert('취미는 한개이상 선택하세요');
        return false;
    }
    if($('#isssn').val() == 'n'){
        alert('주민등록번호 검증버튼을 클릭하세요');
        return false;
    }

    return true;
}

$(function(){
    $('#ssn1').on('keyup', function(){
        if($(this).val().length >= 6){
            $('#ssn2').focus();
        }
    });

    $('#ssnBtn').on('click', function(){
        const ssn = $('#ssn1').val() + $('#ssn2').val();
        const fmt = RegExp(/^\d{6}[1234]\d{6}/);
        if(!fmt.test(ssn)){
            alert('주민등록번호 형식에 맞게 입력하세요');
            $('#ssn1').val('');
            $('#ssn2').val('');
            $('#ssn1').focus();
            return false;
        }

        let arr = new Array(13);
        for(let i=0; i<arr.length; i++){
            arr[i] = parseInt(ssn.charAt(i));
        }
        const mul = [2,3,4,5,6,7,8,9,2,3,4,5];
        let sum = 0;
        for(let i=0; i<arr.length-1; i++){
            sum += (arr[i] *= mul[i]);
        }
        if((11 - (sum % 11)) % 10 != arr[12]){
            alert('유효하지 않은 주민등록번호입니다');
            $('#ssn1').val('');
            $('#ssn2').val('');
            $('#ssn1').focus();
            return false;
        }
        alert('검증되었습니다');
        $('#isssn').val('y');
    });

    $('#ssn1, #ssn2').on('keydown', function(){
        $('#isssn').val('n');
    });
    
    $('#btnIdCheck').on('click', function(){
    	if($('#userid').val() == ""){
    		alert('아이디를 입력하세요');
    		$('#userid').focus();
    		return false;
    	}
    	
    	const xhr = new XMLHttpRequest();
    	const userid = $('#userid').val();
    	xhr.open('GET', 'idcheck.jsp?userid='+userid, true);
    	xhr.send();
    	xhr.onreadystatechange = function(){
    		if(xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200){
    			const result = xhr.responseText;
    			if(result.trim() == "ok"){
    				// 아이디를 만들 수 있다면
    				$("#idcheckmsg").html("<b style='color:blue'>사용할 수 있는 아이디입니다.</b>");
    				$("#isidcheck").val("y");
    			}else{
    				// 아이디를 만들 수 없다면
    				$("#idcheckmsg").html("<b style='color:red'>사용할 수 없는 아이디입니다.</b>");
    			}
    		}
    	}
    	
    });
    
    $("#userid").on("keyup", function(){
    	$("#isidcheck").val("n");
    	$("#idcheckmsg").html("");
    });
});
