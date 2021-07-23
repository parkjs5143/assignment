<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.sql.*" %>
<%@ page import="com.koreait.db.Dbconn"%>

<%
   String b_idx = request.getParameter("b_idx");
   String b_userid = "";
   String b_title= "";
   String b_content = "";
   String b_regdate = "";
   int b_like = 0;
   int b_hit = 0;

   Connection conn = null;
   PreparedStatement pstmt = null;
   ResultSet rs = null;
   String sql = "";
   
   try{
      conn = Dbconn.getConnection();
      if(conn != null){
         sql = "update tb_board set b_hit = b_hit + 1 where b_idx=?";
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, b_idx);
         pstmt.executeUpdate();
         
         sql = "select * from tb_board where b_idx=?";
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, b_idx);
         rs = pstmt.executeQuery();
         
         if(rs.next()){
            b_userid = rs.getString("b_userid");
            b_title = rs.getString("b_title");
            b_content = rs.getString("b_content");
            b_regdate = rs.getString("b_regdate");
            b_like = rs.getInt("b_like");
            b_hit = rs.getInt("b_hit");
         }
      }
   }catch(Exception e){
      e.printStackTrace();
   }

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글보기</title>
</head>
<body>
   <h2>글보기</h2>
   <table border="1" width="800">
      <tr>
         <td>제목</td>
         <td><%=b_title%></td>
      </tr>
      <tr>
         <td>날짜</td>
         <td><%=b_regdate%></td>
      </tr>
      <tr>
         <td>작성자</td>
         <td><%=b_userid%></td>
      </tr>
      <tr>
         <td>조회수</td>
         <td><%=b_hit%></td>
      </tr>
      <tr>
         <td>좋아요</td>
         <td id="text"><%=b_like%></td>
      </tr>
      <tr>
         <td>내용</td>
         <td><%=b_content%></td>
      </tr>
      <tr>
         <td colspan="2">
            <%
            if(b_userid.equals((String)session.getAttribute("userid"))){
            %>
               <input type="button" value="수정" onclick="location.href='./edit.jsp?b_idx=<%=b_idx%>'">
               <input type="button" value="삭제" onclick="location.href='./delete.jsp?b_idx=<%=b_idx%>'">
            <%
            }
            %>
            <input type="button" value="좋아요" onclick="sendRequest()">
            <script>
               'use strict';
               function sendRequest(){
                  const xhr = new XMLHttpRequest();
                  xhr.open('GET', './like_ok.jsp?b_idx=<%=b_idx%>',true); 
                  xhr.send();
	              xhr.onreadystatechange = function(){
	                  if(xhr.readyState == XMLHttpRequest.DONE && xhr.status ==200){
	                     document.getElementById('text').innerHTML = xhr.responseText;
	                  }
	              }
               }
            </script>
            <input type="button" value="리스트" onclick="location.href='./list.jsp'">
         </td>
      </tr>
   </table>
</body>
</html>