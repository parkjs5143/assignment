package com.koreait.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.koreait.db.Dbconn;

public class MemberDAO {
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	String sql = "";
	
	public int join(MemberDTO member) {
		try {
			conn = Dbconn.getConnection();
			sql += "insert into tb_member(mem_userid, mem_userpw, mem_name, ";
			sql += "mem_hp, mem_email, mem_hobby, mem_ssn1, mem_ssn2, mem_zipcode,";
			sql += "mem_address1, mem_address2, mem_address3) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getUserid());
			pstmt.setString(2, member.getUserpw());
			pstmt.setString(3, member.getUsername());
			pstmt.setString(4, member.getHp());
			pstmt.setString(5, member.getEmail());
			String hobbystr = "";
			for(String hobby : member.getHobby()){
				hobbystr = hobbystr + hobby + " ";
			}
			pstmt.setString(6, hobbystr);
			pstmt.setString(7, member.getSsn1());
			pstmt.setString(8, member.getSsn2());
			pstmt.setString(9, member.getZipcode());
			pstmt.setString(10, member.getAddress1());
			pstmt.setString(11, member.getAddress2());
			pstmt.setString(12, member.getAddress3());
			if(pstmt.executeUpdate()>0) {
				return 1;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public MemberDTO login(MemberDTO member) {
		try {
			conn = Dbconn.getConnection();
			sql = "select mem_idx, mem_userid, mem_name from tb_member where mem_userid=? and mem_userpw=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getUserid());
			pstmt.setString(2, member.getUserpw());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				member.setIdx(rs.getInt("mem_idx"));
				member.setUserid(rs.getString("mem_userid"));
				member.setUsername(rs.getString("mem_name"));
				return member;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public MemberDTO info(MemberDTO member) {
		try {
			sql = "select mem_idx, mem_userid, mem_name, mem_hp, mem_email, mem_hobby, mem_ssn1, mem_ssn2, mem_zipcode, mem_address1, mem_address2, mem_address3 from tb_member where mem_idx=?";
			conn = Dbconn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, member.getIdx());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				member.setUsername(rs.getString("mem_name"));
				member.setEmail(rs.getString("mem_email"));
				member.setHp(rs.getString("mem_hp"));
				String hobby[] = rs.getString("mem_hobby").split(" ");
				member.setHobby(hobby);
				member.setSsn1(rs.getString("mem_ssn1"));
				member.setSsn2(rs.getString("mem_ssn2"));
				member.setZipcode(rs.getString("mem_zipcode"));
				member.setAddress1(rs.getString("mem_address1"));
				member.setAddress2(rs.getString("mem_address2"));
				member.setAddress3(rs.getString("mem_address3"));
				return member;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int edit(MemberDTO member) {
		try {
			sql = "update tb_member set mem_name=?, mem_hp=?, mem_email=?, mem_hobby=?, mem_ssn1=?, mem_ssn2=?, mem_zipcode=?, mem_address1=?, mem_address2=?, mem_address3=? where mem_idx=?";
			conn = Dbconn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getUsername());
			pstmt.setString(2, member.getHp());
			pstmt.setString(3, member.getEmail());
			String hobbystr = "";
			for(String hobby : member.getHobby()) {
				hobbystr = hobbystr + hobby + " ";
			}
			pstmt.setString(4, hobbystr);
			pstmt.setString(5, member.getSsn1());
			pstmt.setString(6, member.getSsn2());
			pstmt.setString(7, member.getZipcode());
			pstmt.setString(8, member.getAddress1());
			pstmt.setString(9, member.getAddress2());
			pstmt.setString(10, member.getAddress3());
			pstmt.setInt(11, member.getIdx());
			if(pstmt.executeUpdate() >= 1) {
				return 1;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}
