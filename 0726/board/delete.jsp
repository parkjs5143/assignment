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
		String b_idx = request.getParameter("b_idx");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "";
		
		try{
			conn = Dbconn.getConnection();
			if(conn != null){
				sql = "delete from tb_board where b_idx=?";
				pstmt  = conn.prepareStatement(sql);
				pstmt.setString(1, b_idx);
				pstmt.executeUpdate();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
%>
	<script>
	alert('삭제되었습니다');
	location.href='list.jsp';
	</script>
<%
	}
%>