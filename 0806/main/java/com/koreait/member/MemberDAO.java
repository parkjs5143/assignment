package com.koreait.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.koreait.db.SqlMapConfig;

public class MemberDAO {
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	String sql = "";
	
	SqlSessionFactory ssf = SqlMapConfig.getSqlMapInstance();
	SqlSession sqlsession;
	
	public MemberDAO() {
		sqlsession = ssf.openSession(true);	// openSession(true) 설정시 자동 commit
		System.out.println("마이바티스 설정 성공!");
	}
	
	public boolean idCheck(String userid) {
		if((Integer)sqlsession.selectOne("Member.idCheck", userid) == 1) {
			return true;
		}
		return false;
	}
	
	public int join(MemberDTO member) {
		HashMap<String, String> dataMap = new HashMap();
		String hobbystr = "";
		for(String hobby : member.getHobby()) {
			hobbystr = hobbystr + hobby + " ";
		}
		dataMap.put("mem_userid", member.getUserid());
		dataMap.put("mem_userpw", member.getUserpw());
		dataMap.put("mem_name", member.getUsername());
		dataMap.put("mem_hp", member.getHp());
		dataMap.put("mem_email", member.getEmail());
		dataMap.put("mem_hobby", hobbystr);
		dataMap.put("mem_ssn1", member.getSsn1());
		dataMap.put("mem_ssn2", member.getSsn2());
		dataMap.put("mem_zipcode", member.getZipcode());
		dataMap.put("mem_address1", member.getAddress1());
		dataMap.put("mem_address2", member.getAddress2());
		dataMap.put("mem_address3", member.getAddress3());
		return sqlsession.insert("Member.join", dataMap);
	}
	
	public MemberDTO login(MemberDTO member) {
		HashMap<String, String> dataMap = new HashMap();
		dataMap.put("mem_userid", member.getUserid());
		dataMap.put("mem_userpw", member.getUserpw());
		dataMap = sqlsession.selectOne("Member.login", dataMap);
		
		if(dataMap != null) {
			member.setIdx(Integer.parseInt(String.valueOf(dataMap.get("mem_idx"))));
			member.setUserid(dataMap.get("mem_userid"));
			member.setUsername(dataMap.get("mem_name"));
			return member;
		}
		return null;
	}
	
	public MemberDTO info(MemberDTO member) {
		HashMap<String, String> dataMap = new HashMap();
		dataMap.put("mem_idx", String.valueOf(member.getIdx()));
		dataMap.put("mem_userid", member.getUserid());
		dataMap = sqlsession.selectOne("Member.info", dataMap);
		
		if(dataMap != null) {
			member.setIdx(Integer.parseInt(String.valueOf(dataMap.get("mem_idx"))));
			member.setUserid(dataMap.get("mem_userid"));
			member.setUsername(dataMap.get("mem_name"));
			member.setHp(dataMap.get("mem_hp"));
			member.setEmail(dataMap.get("mem_email"));
			String hobby[] = dataMap.get("mem_hobby").split(" ");
			member.setHobby(hobby);
			member.setSsn1(dataMap.get("mem_ssn1"));
			member.setSsn2(dataMap.get("mem_ssn2"));
			member.setZipcode(dataMap.get("mem_zipcode"));
			member.setAddress1(dataMap.get("mem_address1"));
			member.setAddress2(dataMap.get("mem_address2"));
			member.setAddress3(dataMap.get("mem_address3"));
			return member;	
		}
		return null;
	}
	
	public int edit(MemberDTO member) {
		HashMap<String, String> dataMap = new HashMap();
		String hobbystr = "";
		for(String hobby : member.getHobby()) {
			hobbystr = hobbystr + hobby + " ";
		}
		
		dataMap.put("mem_idx", String.valueOf(member.getIdx()));
		dataMap.put("mem_name", member.getUsername());
		dataMap.put("mem_hp", member.getHp());
		dataMap.put("mem_email", member.getEmail());
		dataMap.put("mem_hobby", hobbystr);
		dataMap.put("mem_ssn1", member.getSsn1());
		dataMap.put("mem_ssn2", member.getSsn2());
		dataMap.put("mem_zipcode", member.getZipcode());
		dataMap.put("mem_address1", member.getAddress1());
		dataMap.put("mem_address2", member.getAddress2());
		dataMap.put("mem_address3", member.getAddress3());
		return sqlsession.update("Member.edit", dataMap);
	}
}
