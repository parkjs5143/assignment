<%@page import="java.sql.*"%>
<%@ page import="com.koreait.db.Dbconn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%
   request.setCharacterEncoding("UTF-8");
   if (session.getAttribute("userid") == null) {
%>
<script>
   alert('로그인 후 이용하세요');
   location.href = '../login.jsp';
</script>
<%
   } else {
%>
<jsp:useBean id="boardDTO" class="com.koreait.board.BoardDTO"/>
<jsp:setProperty property="title" param="b_title" name="boardDTO"/>
<jsp:setProperty property="content" param="b_content" name="boardDTO"/>
<jsp:setProperty property="file" param="b_file" name="boardDTO"/>
<jsp:useBean id="boardDAO" class="com.koreait.board.BoardDAO"/>
<%
     boardDTO.setUserid((String) session.getAttribute("userid"));
      if (boardDAO.write(boardDTO) == 1) {
%>
<script>
   alert('등록되었습니다.');
   location.href = 'list.jsp';
</script>

<%
   } else {
%>
<script>
   alert('등록실패');
   history.back();
</script>
<%
}
   }
%>
