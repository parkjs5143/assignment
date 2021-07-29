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
<jsp:useBean id="boardDTO" class="com.koreait.board.BoardDTO"/>
<jsp:setProperty property="like" param="b_like" name="boardDTO"/>
<jsp:useBean id="boardDAO" class="com.koreait.board.BoardDAO"/>
<%
   boardDTO.setIdx(Integer.parseInt(String.valueOf(request.getParameter("b_idx"))));
   if(boardDAO.likeOk(boardDTO)==0){
       boardDTO.setLike(Integer.parseInt(String.valueOf(request.getParameter("b_like"))));
    }else{
       out.print(boardDAO.likeOk(boardDTO));
    }
}
%>