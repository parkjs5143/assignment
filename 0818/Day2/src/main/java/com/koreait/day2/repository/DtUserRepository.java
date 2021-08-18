package com.koreait.day2.repository;

import com.koreait.day2.model.entity.DtUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DtUserRepository extends JpaRepository<DtUser, Long> {

}
