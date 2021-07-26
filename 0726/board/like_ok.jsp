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
		ResultSet rs = null;
		String sql = "";
		int b_like = 0;
		
		try{
			conn = Dbconn.getConnection();
			if(conn != null){
				sql = "update tb_board set b_like = b_like + 1 where b_idx=?";
				pstmt  = conn.prepareStatement(sql);
				pstmt.setString(1, b_idx);
				pstmt.executeUpdate();
				
				sql = "select b_like from tb_board where b_idx=?";
				pstmt  = conn.prepareStatement(sql);
				pstmt.setString(1, b_idx);
				rs = pstmt.executeQuery();
				
				if(rs.next()){
					b_like = rs.getInt("b_like");
					out.print(b_like);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
%>
