package com.koreait.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.koreait.db.Dbconn;
import com.koreait.db.SqlMapConfig;

public class BoardDAO {
	List<BoardDTO> boardList = new ArrayList();
	List<ReplyDTO> replyList = new ArrayList();
	
	SqlSessionFactory ssf = SqlMapConfig.getSqlMapInstance();
	SqlSession sqlsession;
	
	public BoardDAO() {
		sqlsession = ssf.openSession(true);	// openSession(true) 설정시 자동 commit
		System.out.println("마이바티스 설정 성공!");
	}
	
	
	public List<BoardDTO> selectBoard(int pagePerCount, int start){
		HashMap<String, String> dataMap = new HashMap();
		dataMap.put("pagePerCount", String.valueOf(pagePerCount));
		dataMap.put("start", String.valueOf(start));
		
		return sqlsession.selectList("Board.select_list", dataMap);
	}

//	public List<BoardDTO> selectBoard(int pagePerCount, int start){
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		String sql = "";
//		
//		try {
//			conn = Dbconn.getConnection();
//			sql = "select b_idx, b_userid, b_title, b_regdate, b_hit, b_like, b_file from tb_board order by b_idx desc limit ?, ?";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, start);
//			pstmt.setInt(2, pagePerCount);
//			rs = pstmt.executeQuery();
//			
//			while(rs.next()) {
//				BoardDTO board = new BoardDTO();
//				board.setIdx(rs.getInt("b_idx"));
//				board.setUserid(rs.getString("b_userid"));
//				board.setTitle(rs.getString("b_title"));
//				board.setHit(rs.getInt("b_hit"));
//				board.setLike(rs.getInt("b_like"));
//				board.setRegdate(rs.getString("b_regdate"));
//				board.setFile(rs.getString("b_file"));
//				boardList.add(board);
//			}
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//		return boardList;
//	}
	
	
	public String replyCount(int b_idx) {
		int replycnt = 0;
		String replycnt_str = "";
		
		HashMap<String, Integer> dataMap = new HashMap();
		dataMap.put("b_idx", b_idx);
		dataMap = sqlsession.selectOne("Board.replyCount", dataMap);
		
		if (dataMap != null) {
			replycnt = Integer.parseInt(String.valueOf((dataMap.get("replycnt"))));
			if (replycnt > 0) {
				replycnt_str = "[" + replycnt + "]";
			}
		}
		return replycnt_str;
	}
	
	
	public int write(BoardDTO board) {
		HashMap<String, String> dataMap = new HashMap();
		
		dataMap.put("b_userid", board.getUserid());
		dataMap.put("b_title", board.getTitle());
		dataMap.put("b_content", board.getContent());
		dataMap.put("b_file", board.getFile());
		
		return sqlsession.insert("Board.write", dataMap);
	}
	
	public BoardDTO view(BoardDTO board) {
		HashMap<String, String> dataMap = new HashMap();
		dataMap.put("b_idx", String.valueOf(board.getIdx()));
		sqlsession.update("Board.update_view", dataMap);

		dataMap = sqlsession.selectOne("Board.select_view", dataMap);
		if (dataMap != null) {
			board.setIdx(Integer.parseInt(String.valueOf(dataMap.get("b_idx"))));
			board.setUserid(dataMap.get("b_userid"));
			board.setTitle(dataMap.get("b_title"));
			board.setContent(dataMap.get("b_content"));
			board.setRegdate(String.valueOf(dataMap.get("b_regdate")));
			board.setLike(Integer.parseInt(String.valueOf(dataMap.get("b_like"))));
			board.setHit(Integer.parseInt(String.valueOf(dataMap.get("b_hit"))));
			board.setFile(dataMap.get("b_file"));
			return board;
		}
		return null;
	}
	

	
	 public int delete(BoardDTO board) {
		 String b_idx = String.valueOf(board.getIdx());
		 return sqlsession.delete("Board.delete", b_idx);
	 }
	 
	 public int likeOk(BoardDTO board) {
		 HashMap<String, Integer> dataMap = new HashMap();
		 dataMap.put("b_idx", board.getIdx());
		 sqlsession.update("Board.update_like_ok", dataMap);
		 dataMap = sqlsession.selectOne("Board.select_like_ok", dataMap);
		 if(dataMap != null) {
			 board.setLike(dataMap.get("b_like"));
			 return board.getLike();
		 }
		 return 0;
	 }
	 
	 public int writeReply(ReplyDTO reply) {
		 HashMap<String, String> dataMap = new HashMap();
		 dataMap.put("re_userid", reply.getUserid());
		 dataMap.put("re_content", reply.getContent());
		 dataMap.put("re_boardIdx", String.valueOf(reply.getBoardIdx()));
		 return sqlsession.insert("Board.writeReply", dataMap);
	 }
	 
	 public List<ReplyDTO> selectReply(int b_idx){
		 return sqlsession.selectList("Board.selectReply", b_idx);
	 }
	 
	 public int deleteReply(ReplyDTO reply) {
		 return sqlsession.delete("Board.deleteReply", reply);
	 }

	 public int edit_ok(BoardDTO board) {
		 HashMap<String, String> dataMap = new HashMap();
		 dataMap.put("b_title", board.getTitle());
		 dataMap.put("b_content", board.getContent());
		 dataMap.put("b_file", board.getFile());
		 dataMap.put("b_idx", String.valueOf(board.getIdx()));
		 if(board.getFile() != null && !board.getFile().equals("")){
			 return sqlsession.update("Board.edit_ok_file", dataMap);
		 }else{
			 return sqlsession.update("Board.edit_ok_file_null", dataMap);
		 }
	 }
	 
	 public int totCount() {
		 int total = (Integer)sqlsession.selectOne("Board.totCount");
		 return total;
	 }
}
