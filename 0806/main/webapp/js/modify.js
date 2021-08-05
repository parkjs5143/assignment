function sendit(){
    // alert('sendit() 호출!');
    const expNameCheck = RegExp(/^[가-힣]+$/);
    const expHpCheck = RegExp(/^\d{3}-\d{3,4}-\d{4}$/);
    const expEmailCheck = RegExp(/^[a-zA-Z0-9\.\-]+@[a-zA-Z0-9\.\-]+\.[a-zA-Z0-9]+$/);

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
});
