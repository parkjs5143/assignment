<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="com.koreait.db.Dbconn"%>
<%
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
<jsp:useBean id="dao" class="com.koreait.board.BoardDAO"/>
<jsp:setProperty property="*" name="replyDTO"/>
<%
	replyDTO.setBoardIdx(Integer.parseInt(String.valueOf(request.getParameter("b_idx"))));
	replyDTO.setIdx(Integer.parseInt(String.valueOf(request.getParameter("re_idx"))));
   	if(dao.deleteReply(replyDTO) ==1){
%>
   <script>
   alert('삭제되었습니다');
   location.href='view.jsp?b_idx=<%=request.getParameter("b_idx")%>';
   </script>
<%
   }
      }
%>