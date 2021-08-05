<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="com.koreait.db.Dbconn"%>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>
<%@ page import="com.oreilly.servlet.MultipartRequest"%>
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
<jsp:useBean class="com.koreait.board.BoardDTO" id="board"/>
<jsp:useBean class="com.koreait.board.BoardDAO" id="dao"/>
<%
		board.setUserid((String)session.getAttribute("userid"));

		String uploadPath = request.getRealPath("/upload");
		System.out.println(uploadPath);
		int size = 1024 * 1024 * 10;
		
		try{
			MultipartRequest multi = new MultipartRequest(request, uploadPath, size, "UTF-8", new DefaultFileRenamePolicy());
			board.setTitle(multi.getParameter("b_title"));
			board.setContent(multi.getParameter("b_content"));
			board.setFile(multi.getFilesystemName("b_file"));
			board.setIdx(Integer.parseInt(String.valueOf(multi.getParameter("b_idx"))));
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if(dao.edit_ok(board) == 1){
%>
	<script>
	alert('수정되었습니다');
	location.href='view.jsp?b_idx=<%=board.getIdx()%>';
	</script>
<%
		}else{
%>
		<script>
			alert('수정실패!');
			history.back();
		</script>
<%
	}
}
%>
