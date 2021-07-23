<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<button onclick="sendRequest()">요청 보내기</button>
	<p id="text"></p>
	<script>
		'use strict';
		function sendRequest(){
			const xhr = new XMLHttpRequest();
			xhr.open('GET', 'ajax_ok.jsp?userid=apple&userpw=1111', true);	// 비동기
			xhr.send();	// 백그라운드에서 보내짐
			
			// XMLHttpRequest.DONE : 4(완료), xhr.status : 200(정상적인 접근 완료)
			xhr.onreadystatechange = function(){	// readyState의 값이 바뀔때마다 호출
				if(xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200){
					document.getElementById('text').innerHTML = xhr.responseText;
				}
			}
		}
		
	</script>
</body>
</html>