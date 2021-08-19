package com.koreait.day2.repository;

import com.koreait.day2.model.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    // select * from users where userid=?
    Optional<Users> findByUserid(String userid);

    // select * from users where userid=? and userpw=?
    Optional<Users> findByUseridAndUserpw(String userid, String userpw);

    // select * from users where rownum <= 1 order by id desc
    Users findFirstByHpOrderByIdDesc(String hp);
}
