<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	if(session.getAttribute("userid") == null){
%>
	<script>
		alert('로그인 후 이용하세요');
		location.href='login.jsp';
	</script>
<%
	}else{
		request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean class="com.koreait.member.MemberDTO" id="member"/>
<jsp:setProperty property="*" name="member"/>
<jsp:setProperty property="username" param="name" name="member"/>
<jsp:useBean class="com.koreait.member.MemberDAO" id="dao"/>
<%
	member.setIdx(Integer.parseInt(String.valueOf(session.getAttribute("idx"))));
	member.setUserid((String)session.getAttribute("userid"));

	if(dao.edit(member) == 1){
%>
	<script>
		alert('회원정보가 수정되었습니다.');
		location.href='info.jsp';
	</script>
<%
	}else{
%>
	<script>
		alert('회원정보 수정실패!');
		history.back();
	</script>
<%
	}
}
%>