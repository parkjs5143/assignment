package com.koreait.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.koreait.db.Dbconn;
import com.koreait.member.MemberDTO;

public class BoardDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "";
	List<BoardDTO> boardList = new ArrayList();
	List<ReplyDTO> replyList = new ArrayList();
	
	public List<BoardDTO> selectBoard(){
		Connection conn = null;
	      PreparedStatement pstmt = null;
	      String sql = "";
	      
		try {
			conn = Dbconn.getConnection();
			sql = "select b_idx, b_userid, b_title, b_regdate, b_hit, b_like, b_file from tb_board order by b_idx desc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardDTO board = new BoardDTO();
				board.setIdx(rs.getInt("b_idx"));
				board.setUserid(rs.getString("b_userid"));
				board.setTitle(rs.getString("b_title"));
				board.setHit(rs.getInt("b_hit"));
				board.setLike(rs.getInt("b_like"));
				board.setRegdate(rs.getString("b_regdate"));
				board.setFile(rs.getString("b_file"));
				boardList.add(board);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return boardList;
	}
	
	public BoardDTO view(BoardDTO board) {
		try{
			conn = Dbconn.getConnection();
			if(conn != null){
				sql = "update tb_board set b_hit = b_hit + 1 where b_idx=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, board.getIdx());
				pstmt.executeUpdate();
				
				sql = "select b_userid, b_title, b_content, b_regdate, b_like, b_hit, b_file from tb_board where b_idx=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, board.getIdx());
				rs = pstmt.executeQuery();
				
				if(rs.next()){
					board.setUserid(rs.getString("b_userid"));
					board.setTitle(rs.getString("b_title"));
					board.setContent(rs.getString("b_content"));
					board.setRegdate(rs.getString("b_regdate"));
					board.setLike(rs.getInt("b_like"));
					board.setHit(rs.getInt("b_hit"));
					board.setFile(rs.getString("b_file"));
					return board;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public int write(BoardDTO board) {
		Connection conn = null;
	      PreparedStatement pstmt = null;
	      String sql = "";
	        
		try {
	         conn = Dbconn.getConnection();
	         if (conn != null) {
	            sql = "insert into tb_board(b_userid, b_title, b_content, b_file) values(?, ?, ?, ?)";
	            pstmt = conn.prepareStatement(sql);
	            pstmt.setString(1, board.getUserid());
	            pstmt.setString(2, board.getTitle());
	            pstmt.setString(3, board.getContent());
	            pstmt.setString(4, board.getFile());
	            if(pstmt.executeUpdate() > 0);{
	               return 1;
	            }
	         }
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	      return 0;
	   }
	
	 public int delete(BoardDTO board) {
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      String sql = "";
	      
	      try{
	         conn = Dbconn.getConnection();
	         if(conn != null){
	            sql = "delete from tb_board where b_idx=?";
	            pstmt  = conn.prepareStatement(sql);
	            pstmt.setLong(1, board.getIdx());
	            
	            if(pstmt.executeUpdate() > 0) {
	               return 1;
	            }
	         }
	      }catch(Exception e){
	         e.printStackTrace();
	      }
	      return 0;
	   }
	 public int likeOk(BoardDTO board) {
		 Connection conn = null;
	      PreparedStatement pstmt = null;
	      String sql = "";
	      int b_like = 0;
	      try {
	         conn = Dbconn.getConnection();
	              if(conn != null){
	                sql = "update tb_board set b_like = b_like + 1 where b_idx=?";
	                pstmt  = conn.prepareStatement(sql);
	                pstmt.setInt(1, board.getIdx());
	                pstmt.executeUpdate();
	                
	                sql = "select b_like from tb_board where b_idx=?";
	                pstmt  = conn.prepareStatement(sql);
	                pstmt.setInt(1, board.getIdx());
	                rs = pstmt.executeQuery();
	                
	                if(rs.next()){
	                  b_like = rs.getInt("b_like");
	               }
	            }
	        }catch(Exception e) {
	               e.printStackTrace();
	      }return b_like;
	   }
	 public int writeReply(ReplyDTO reply) {
		 try {
	         conn = Dbconn.getConnection();
	         if (conn != null) {
	            sql = "insert into tb_reply(re_userid, re_content, re_boardIdx) values(?, ?, ?)";
	            pstmt = conn.prepareStatement(sql);
	            pstmt.setString(1, reply.getUserid());
	            pstmt.setString(2, reply.getContent());
	            pstmt.setInt(3, reply.getBoardIdx());
	            if(pstmt.executeUpdate() > 0);{
	               return 1;
	            }
	         }
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	      return 0;
	 }
	 
	 public List<ReplyDTO> selectReply(){
		 try {
				conn = Dbconn.getConnection();
				sql = "select re_idx, re_userid, re_content, re_regdate, re_boardIdx from tb_reply order by re_idx desc";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					ReplyDTO reply = new ReplyDTO();
					reply.setIdx(rs.getInt("re_idx"));
					reply.setUserid(rs.getString("re_userid"));
					reply.setContent(rs.getString("re_content"));
					reply.setRegdate(rs.getString("re_regdate"));
					reply.setBoardIdx(rs.getInt("re_boardIdx"));
					replyList.add(reply);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		 return replyList;
	 }
	 
	 public int deleteReply(ReplyDTO reply) {
		 Connection conn = null;
	      PreparedStatement pstmt = null;
	      String sql = "";
	      
	      try{
	         conn = Dbconn.getConnection();
	         if(conn != null){
	            sql = "delete from tb_reply where re_idx=?";
	            pstmt  = conn.prepareStatement(sql);
	            pstmt.setLong(1, reply.getIdx());
	            
	            if(pstmt.executeUpdate() > 0) {
	               return 1;
	            }
	         }
	      }catch(Exception e){
	         e.printStackTrace();
	      }
	      return 0;
	 }
	 
	 public BoardDTO edit(BoardDTO board) {
		 Connection conn = null;
	      PreparedStatement pstmt = null;
	      String sql = "";
	      
		 try {
		 conn = Dbconn.getConnection();
			if(conn != null){
				sql = "select b_title, b_content, b_file from tb_board where b_idx=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, board.getIdx());
				rs = pstmt.executeQuery();
				
				if(rs.next()){
					board.setTitle(rs.getString("b_title"));
					board.setContent(rs.getString("b_content"));
					board.setFile( rs.getString("b_file"));
					return board;
				}
			}
		 }catch(Exception e) {
				e.printStackTrace();
		}
		return null;
	}
	 
	 public int edit_ok(BoardDTO board) {
			try{
				conn = Dbconn.getConnection();
				
				if(conn != null){
					if(board.getFile() != null && !board.getFile().equals("")){
						sql = "update tb_board set b_title=?, b_content=?, b_file=? where b_idx=?";
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, board.getTitle());
						pstmt.setString(2, board.getContent());
						pstmt.setString(3, board.getFile());
						pstmt.setLong(4, board.getIdx());
					}else{
						sql = "update tb_board set b_title=?, b_content=? where b_idx=?";
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, board.getTitle());
						pstmt.setString(2, board.getContent());
						pstmt.setLong(3, board.getIdx());
					}
					if(pstmt.executeUpdate() >= 1)	return 1;
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			return 0;
	 }
}
