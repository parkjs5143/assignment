<%@page import="com.koreait.board.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="com.koreait.db.Dbconn"%>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat"%>

<jsp:useBean id="board" class="com.koreait.board.BoardDTO"/>
<jsp:useBean id="dao" class="com.koreait.board.BoardDAO"/>

<%
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	ResultSet rs_reply = null;
	String sql = "";
	
	Date from = new Date();
	SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
	String to = fm.format(from);	// 2021-08-02
	
	int totalCount = dao.totCount();	// 총 글 개수 불러오는 메소드
	int pagePerCount = 10;	// 페이지당 글개수
	int start = 0;	// 시작 글번호
	
	String pageNum = request.getParameter("pageNum");
	if(pageNum != null && !pageNum.equals("")){
		start = (Integer.parseInt(pageNum)-1) * pagePerCount;	// 2 * 10
	}else{
		pageNum = "1";
		start=0;
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리스트</title>
</head>
<body>
	<h2>리스트</h2>
	<p>게시글 : <%=totalCount%>개</p>
	
	<table border="1" width="800">
		<tr>
			<th width="50">번호</th>
			<th width="300">제목</th>
			<th width="100">글쓴이</th>
			<th width="75">조회수</th>
			<th width="200">날짜</th>
			<th width="75">좋아요</th>
		</tr>
<%
		
		for(BoardDTO b : dao.selectBoard(pagePerCount, start)){
			String newDateStr = "";	// 오늘 날짜로 올린 게시글의 경우 new이미지 출력하는 문자열
			String fileStr = "";	// 첨부파일저장된 게시글의 경우 첨부파일 이미지 출력하는 문자열
			
			int b_idx = b.getIdx();
			String b_userid = b.getUserid();
			String b_title = b.getTitle();
			String b_content = b.getContent();
			int b_hit = b.getHit();
			int b_like = b.getLike();
			String b_regdate = b.getRegdate().substring(0, 10);
			String b_file = b.getFile();
			
			if(to.equals(b_regdate)){
				newDateStr = "<img src='./new.png' alt='새글'>";
			}
			if(b_file != null && !b_file.equals("")){
				fileStr = "<img src='./file.png' alt='파일'>";
			}
			
%>
		<tr>
			<td><%=b_idx%></td>
			<td><a href="./view.jsp?b_idx=<%=b_idx%>"><%=b_title%></a> <%=dao.replyCount(b_idx)%> <%=fileStr%> <%=newDateStr%></td>
			<td><%=b_userid%></td>
			<td><%=b_hit%></td>
			<td><%=b_regdate%></td>
			<td><%=b_like%></td>
		</tr>
<%
		}
	
	int pageNums = 0;
	if(totalCount % pagePerCount == 0){
		pageNums = (totalCount / pagePerCount);	// 20 / 10
	}else{
		pageNums = (totalCount / pagePerCount) + 1;
	}
%>
	<tr>
		<td colspan="6" align="center">
			<%
				for(int i=1; i<=pageNums; i++){
					out.print("<a href='list.jsp?pageNum="+i+"'>["+i+"]</a> ");
				}
			%>
		</td>
	</tr>
	</table>
	<p><input type="button" value="글쓰기" onclick="location.href='write.jsp'"> <input type="button" value="메인" onclick="location.href='../login.jsp'"></p>
</body>
</html>
