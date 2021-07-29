<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="com.koreait.db.Dbconn"%>
<jsp:useBean class="com.koreait.board.BoardDAO" id="dao"/>
<jsp:useBean class="com.koreait.board.BoardDTO" id="board"/>
<jsp:setProperty property="title" param="b_title" name="board"/>
<jsp:setProperty property="content" param="b_content" name="board"/>
<jsp:setProperty property="file" param="b_file" name="board"/>
<jsp:setProperty property="regdate" param="b_regdate" name="board"/>
<jsp:setProperty property="hit" param="b_hit" name="board"/>
<jsp:setProperty property="like" param="b_like" name="board"/>
<%
	int b_idx = Integer.parseInt(request.getParameter("b_idx"));
	board.setIdx(b_idx);

	if(dao.view(board) == null){
%>
<script>
	alert('잘못된 접속입니다');
	location.href = 'login.jsp';
</script>
<%
	}else{
		String b_userid = board.getUserid();
		String b_title = board.getTitle();
		String b_content = board.getContent();
		String b_regdate = board.getRegdate();
		String b_file = board.getFile();
		int b_like = board.getLike();
		int b_hit = board.getHit();
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
			<td>파일</td>
			<td>
				<%
					if(b_file != null && !b_file.equals("")){
						out.println("첨부파일");
						out.println("<img src='../upload/"+b_file+"' alt='첨부파일' width='150'>");
					}
				%>
			</td>
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
	Connection conn = Dbconn.getConnection();
	String sql = "select re_idx, re_userid, re_content, re_regdate from tb_reply where re_boardidx=? order by re_idx desc";
	PreparedStatement pstmt = conn.prepareStatement(sql);
	pstmt.setInt(1, b_idx);
	ResultSet rs = pstmt.executeQuery();
	
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
}
%>
</body>
</html>