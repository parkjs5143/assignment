package com.koreait.day3.service;

import com.koreait.day3.ifs.CrudInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public abstract class BaseService<Req, Res, Entity> implements CrudInterface<Req, Res> {
    @Autowired(required = false)
    protected JpaRepository<Entity, Long> baseRepository;
    // 베이스 리포지토리 자체가 jpa와 연결하기 위해 쓰는 것이다.
    // 객체를 jpa와 연결할 수 있는 base를 생성한 것.
}
