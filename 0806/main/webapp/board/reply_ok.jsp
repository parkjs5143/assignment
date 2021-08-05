<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="com.koreait.db.Dbconn"%>
<%
	request.setCharacterEncoding("UTF-8");
	if(session.getAttribute("userid") == null){
%>
	<script>
		alert('로그인 후 이용하세요');
		location.href='../login.jsp';
	</script>
<%
	}else{
%>
<jsp:useBean id="replyDTO" class="com.koreait.board.ReplyDTO"/>
<jsp:useBean id="boardDAO" class="com.koreait.board.BoardDAO"/>
<jsp:setProperty property="content" param="re_content" name="replyDTO"/>
<jsp:setProperty property="boardIdx" param="b_idx" name="replyDTO"/>
<%
	replyDTO.setUserid((String) session.getAttribute("userid"));
	if(boardDAO.writeReply(replyDTO) == 1) {
%>
	<script>
	alert('등록되었습니다');
	location.href='view.jsp?b_idx=<%=replyDTO.getBoardIdx()%>';
	</script>
<%
	}else{
%>
	<script>
	alert('등록실패');
	location.href='view.jsp?b_idx=<%=replyDTO.getBoardIdx()%>';
	</script>
<%
	}
}
%>
