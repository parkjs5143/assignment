package com.koreait.day3.repository;

import com.koreait.day3.model.entity.DtUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DtUserRepository extends JpaRepository<DtUser, Long> {
    // select * from dt_user where userid=?
    // 메소드명과 전달할 파라미터 순서 맞춰주기
    Optional<DtUser> findByUserid(String userid);

    // select * from dt_user where userid=? and userpw=? 예외처리 쉽게하기 위해 wrap함
    Optional<DtUser> findByUseridAndUserpw(String userid, String userpw);

    // select * from dt_user where hp = ? and rownum <= 1 order by id desc
    DtUser findFirstByHpOrderByIdDesc(String hp);
}
