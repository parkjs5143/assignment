<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="com.koreait.db.Dbconn"%>
<%
	String b_idx = request.getParameter("b_idx");
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "";
	
	String b_userid = "";
	String b_title = "";
	String b_content = "";
	String b_regdate = "";
	int b_like = 0;
	int b_hit = 0;
	
	try{
		conn = Dbconn.getConnection();
		if(conn != null){
			sql = "update tb_board set b_hit = b_hit + 1 where b_idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, b_idx);
			pstmt.executeUpdate();
			
			sql = "select b_idx, b_userid, b_title, b_content, b_regdate, b_like, b_hit from tb_board where b_idx=?";
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
<script>
	function like(){
		const xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function(){
			if(xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200){
				document.getElementById('like').innerHTML = xhr.responseText;
			}
		}
		xhr.open('GET', './like_ok.jsp?b_idx=<%=b_idx%>', true);
		xhr.send();
	}
</script>
</head>
<body>
	<h2>글보기</h2>
	<table border="1" width="800">
		<tr>
			<td>제목</td><td><%=b_title%></td>
		</tr>
		<tr>
			<td>날짜</td><td><%=b_regdate%></td>
		</tr>
		<tr>
			<td>작성자</td><td><%=b_userid%></td>
		</tr>
		<tr>
			<td>조회수</td><td><%=b_hit%></td>
		</tr>
		<tr>
			<td>좋아요</td><td><span id="like"><%=b_like%></span></td>
		</tr>
		<tr>
			<td>내용</td><td><%=b_content%></td>
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
			<input type="button" value="좋아요" onclick="like()">
			<input type="button" value="리스트" onclick="location.href='./list.jsp'">
			</td>
		</tr>
	</table>
	<hr/>
	<form method="post" action="reply_ok.jsp">
		<input type="hidden" name="b_idx" value="<%=b_idx%>">
		<p><%=session.getAttribute("userid")%> : <input type="text" size="40" name="re_content"> <input type="submit" value="확인"></p>
	</form>
	<hr/>
<%
	sql = "select re_idx, re_userid, re_content, re_regdate from tb_reply where re_boardidx=? order by re_idx desc";
	pstmt = conn.prepareStatement(sql);
	pstmt.setString(1, b_idx);
	rs = pstmt.executeQuery();
	
	while(rs.next()){
		int re_idx = rs.getInt("re_idx");
		String re_userid = rs.getString("re_userid");
		String re_content = rs.getString("re_content");
		String re_regdate = rs.getString("re_regdate");
%>
		<p><%=re_userid%> : <%=re_content%> ( <%=re_regdate%> ) 
<%
	if(re_userid.equals((String)session.getAttribute("userid"))){
%>
		<input type="button" value="삭제" onclick="location.href='reply_del.jsp?re_idx=<%=re_idx%>&b_idx=<%=b_idx%>'">
<%
	}
%>		</p>
<%
	}
%>
</body>
</html>