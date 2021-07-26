<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="com.koreait.db.Dbconn"%>
<%
	String userid = request.getParameter("userid");
	String userpw = request.getParameter("userpw");

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "";

	try {
		conn = Dbconn.getConnection();
		if (conn != null) {
			sql = "select mem_idx, mem_name from tb_member where mem_userid=? and mem_userpw=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.setString(2, userpw);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				// 로그인 성공
				session.setAttribute("userid", userid);
				session.setAttribute("idx", rs.getString("mem_idx"));
				session.setAttribute("name", rs.getString("mem_name"));
%>
<script>
	alert('로그인 되었습니다');
	location.href = 'login.jsp'; // 새로고침
</script>
<%
	} else {
				// 로그인 실패
%>
<script>
	alert('아이디 또는 비밀번호를 확인하세요');
	history.back(); // 히스토리, 캐시
</script>
<%
	}
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
%>
