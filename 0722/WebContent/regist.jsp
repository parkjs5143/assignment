<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원가입</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    <script src="./js/script.js"></script>
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script>
        function sample6_execDaumPostcode() {
            new daum.Postcode({
                oncomplete: function(data) {
                    // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                    // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                    // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                    var addr = ''; // 주소 변수
                    var extraAddr = ''; // 참고항목 변수

                    //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                    if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                        addr = data.roadAddress;
                    } else { // 사용자가 지번 주소를 선택했을 경우(J)
                        addr = data.jibunAddress;
                    }

                    // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                    if(data.userSelectedType === 'R'){
                        // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                        // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                        if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                            extraAddr += data.bname;
                        }
                        // 건물명이 있고, 공동주택일 경우 추가한다.
                        if(data.buildingName !== '' && data.apartment === 'Y'){
                            extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                        }
                        // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                        if(extraAddr !== ''){
                            extraAddr = ' (' + extraAddr + ')';
                        }
                        // 조합된 참고항목을 해당 필드에 넣는다.
                        document.getElementById("sample6_extraAddress").value = extraAddr;
                    
                    } else {
                        document.getElementById("sample6_extraAddress").value = '';
                    }

                    // 우편번호와 주소 정보를 해당 필드에 넣는다.
                    document.getElementById('sample6_postcode').value = data.zonecode;
                    document.getElementById("sample6_address").value = addr;
                    // 커서를 상세주소 필드로 이동한다.
                    document.getElementById("sample6_detailAddress").focus();
                }
            }).open();
        }
    </script>
    <script>
        // function sendit(){
        //     const frm = document.regform;
        //     if(frm.userid.value == ''){
        //         alert('아이디를 입력하세요');
        //         return false;
        //     }
        //     frm.submit();
        // }
    </script>
</head>
<body>
    <h2>회원가입</h2>
    <form action="regist_ok.jsp" name="regform" id="regform" method="post" onsubmit="return sendit()">
        <input type="hidden" name="isidcheck" id="isidcheck" value="n">
        <input type="hidden" name="isssn" id="isssn" value="n">
        <p><label>아이디 : <input type="text" name="userid" id="userid" maxlength="20"></label>
        <input type="button" id="btnIdCheck" value="아이디 중복체크">
        </p>
        <p id="idcheckmsg"></p>
        <p><label>비밀번호 : <input type="password" name="userpw" id="userpw" maxlength="20"></label></p>
        <p><label>비밀번호 확인 : <input type="password" name="userpw_re" id="userpw_re" maxlength="20"></label></p>
        <p><label>이름 : <input type="text" name="name" id="name"></label></p>
        <p><label>휴대폰 번호 : <input type="text" name="hp" id="hp"></label></p>
        <!-- <p><input type="button" value="가입완료" onclick="sendit()"> <input type="reset" value="다시작성"></p> -->
        <p><label>이메일 : <input type="text" name="email" id="email"></label></p>
        <p>취미 : <label>드라이브<input type="checkbox" name="hobby" value="드라이브"></label> <label>영화감상<input type="checkbox" name="hobby" value="영화감상"></label> <label>쇼핑<input type="checkbox" name="hobby" value="쇼핑"></label> <label>요가<input type="checkbox" name="hobby" value="요가"></label> <label>헬스<input type="checkbox" name="hobby" value="헬스"></label></p>
        <p>주민등록번호 : <input type="text" name="ssn1" id="ssn1" maxlength="6"> - <input type="password" name="ssn2" id="ssn2"> <input type="button" value="주민등록번호 검증" id="ssnBtn"></p>
        <p><label>우편번호 : <input type="text" name="zipcode" id="sample6_postcode" readonly></label> <input type="button" value="우편번호 검색" onclick="sample6_execDaumPostcode()"></p>
        <p><label>주소 : <input type="text" name="address1" id="sample6_address"></label></p>
        <p><label>상세주소 : <input type="text" name="address2" id="sample6_detailAddress"></label></p>
        <p><label>참고항목 : <input type="text" name="address3" id="sample6_extraAddress"></label></p>
        <p><input type="submit" value="가입완료"> <input type="reset" value="다시작성"></p>
    </form>
</body>
</html>