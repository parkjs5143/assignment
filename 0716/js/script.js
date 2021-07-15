function sendit(){

    const userid = document.getElementById('userid');
    const userpw = document.getElementById('userpw');
    const userpw_re = document.getElementById('userpw_re');
    const name = document.getElementById('name');
    const hp = document.getElementById('hp');
    const email = document.getElementById('email');
    const hobby = document.getElementsByName('hobby');
    const ssn1 = document.getElementById('ssn1');
    const ssn2 = document.getElementById('ssn2');
    const isssn = document.getElementById('isssn');

    
    // 정규표현식
    /*
        참조 : https://developer.mozilla.org/ko/docs/Web/JavaScript/Guide/Regular_Expressions
        {1} : 1회
        {1,} : 1회 이상, 무한 이하
        {1,2} : 1회 이상, 2회 이하
        + : 1회 이상
            {1,}
        * : 0회 이상
    */
    
    const expNameText = /[가-힣]+$/
        // , expHpText = /^\d{3}-\d{3,4}-\d{4}$/
        , expEmailText = /^[A-Za-z0-9\.\-]+@[A-Za-z0-9\.\-]+\.[A-Za-z0-9]+$/
        // 비밀번호 : 숫자, 대소문자, 특수문자
        , expPwd = /^[A-Za-z0-9~!@#$%^&*()_+?/-=<>//`]{5,}$/
        // 전화번호 : -을 빼고 010을 포함, 중간은 3자리 또는 4자리, 마지막은 4자리로 표현
        , expHpText = /^010[0-9]{3,4}[0-9]{4}$/
        ;

    // 아이디를 입력하지 않았을 경우
    if(userid.value == ''){
        alert('아이디를 입력하세요');
        userid.focus();
        return false;
    }

    // 아이디가 4자 미만 또는 16자를 초과하는 경우
    if(userid.value.length < 4 || userid.value.length > 16){
        alert('아이디를 4자이상 16자 이하로 입력하세요');
        userid.focus();
        return false;
    }
    
    // 비밀번호를 입력하지 않은 경우
    if(userpw.value == ''){
        alert('비밀번호를 입력하세요');
        userpw.focus();
        return false;
    }

    // 비밀번호 검증
    if(!expPwd.test(userpw.value)){
        alert('비밀번호는 숫자, 대소문자, 특수문자를 꼭 포함해야 합니다.');
        userpw.focus();
        return false;
    }

    // 비밀번호와 비밀번호 확인의 같이 다른 경우
    if(userpw.value != userpw_re.value){
        alert('비밀번호와 비밀번호 확인의 값이 다릅니다');
        userpw_re.focus();
        return false;
    }


    if(!expNameText.test(name.value)){
        alert('이름 형식을 확인하세요\n한글만 입력 가능합니다');
        name.focus();
        return false;
    }

    if(!expHpText.test(hp.value.replaceAll('-',''))){
        alert('휴대폰번호 형식을 확인하세요');
        hp.focus();
        return false;
    }

    if(!expEmailText.test(email.value)){
        alert('이메일 형식을 확인하세요');
        email.focus();
        return false;
    }

    let count = 0;
    for(let i in hobby){
        if(hobby[i].checked){
            count++;
        }
    }

    if(count == 0){
        alert('취미는 1개이상 선택하세요');
        return false;
    }

    if(isssn.value == 'n'){
        alert('주민등록번호 검증버튼을 눌러주세요');
        return false;
    }


    return true;
}



function ssnCheck(){
    const ssn1 = document.getElementById('ssn1');
    const ssn2 = document.getElementById('ssn2');
    const isssn = document.getElementById('isssn');
    
    if(ssn1.value == '' || ssn2.value == ''){
        alert('주민등록번호를 입력하세요');
        ssn1.focus();
        return false;
    }

    const ssn = ssn1.value + ssn2.value;    // 0010113068518

    const s1 = Number(ssn.substr(0, 1)) * 2;
    const s2 = Number(ssn.substr(1, 1)) * 3;
    const s3 = Number(ssn.substr(2, 1)) * 4;
    const s4 = Number(ssn.substr(3, 1)) * 5;
    const s5 = Number(ssn.substr(4, 1)) * 6;
    const s6 = Number(ssn.substr(5, 1)) * 7;
    const s7 = Number(ssn.substr(6, 1)) * 8;
    const s8 = Number(ssn.substr(7, 1)) * 9;
    const s9 = Number(ssn.substr(8, 1)) * 2;
    const s10 = Number(ssn.substr(9, 1)) * 3;
    const s11 = Number(ssn.substr(10, 1)) * 4;
    const s12 = Number(ssn.substr(11, 1)) * 5;
    const s13 = Number(ssn.substr(12, 1));
    
    let result = s1+s2+s3+s4+s5+s6+s7+s8+s9+s10+s11+s12;
    result = result % 11;
    result = 11- result;
    if(result >= 10) result = result % 10;

    if(result == s13){
        alert('유효한 주민등록번호입니다');
        isssn.value = 'y';
    }else{
        alert('유효하지 않은 주민등록번호입니다');
    }
}

function moveFocus(){
    const ssn1 = document.getElementById('ssn1');
    if(ssn1.value.length >= 6){
        document.getElementById('ssn2').focus();
    }
}

function changeSsn(){
    const isssn = document.getElementById('isssn');
    isssn.value = 'n';
}